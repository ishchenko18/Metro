package infopulse.resources;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import infopulse.partOfTrain.RailwayCarriage;
import infopulse.depot;
import infopulse.lines.Line;
import infopulse.lines.Lobby;
import infopulse.lines.Station;
import infopulse.mainComponents.Metro;
import infopulse.people.Driver;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

/**
 * Class describe work with DB
 *
 * @author Ishchenko Vladyslav
 * @version 1.1
 * @since 1.0
 */
public class DataBase {
    /**
     * Object of a subway
     */
    private Metro metro;

    /**
     * Connection to a DB
     */
    private ConnectionSource source;

    /**
     * Password for connection to a DB
     */
    private String password;

    /**
     * Login for connection to a DB
     */
    private String login;

    /**
     * URL for connection to a DB
     */
    private String dataBaseURL;

    /**
     * DAO for receiving lines from DB
     */
    private Dao<Line, Integer> daoLine;

    /**
     * DAO for receiving depots from DB
     */
    private Dao<Depot, Integer> daoDepot;

    /**
     * DAO for receiving railway carriages from DB
     */
    private Dao<RailwayCarriage, Integer> daoRailwayCarriage;

    /**
     * DAO for receiving stations from DB
     */
    private Dao<Station, Integer> daoStation;

    /**
     * DAO for receiving lobbies from DB
     */
    private Dao<Lobby, String> daoLobby;

    /**
     * DAO for receiving drivers from DB
     */
    private Dao<Driver, Integer> daoDriver;

    /**
     * Constructor of initializing
     *
     * @param metro       Object of a subway
     * @param password    Password for connection to a DB
     * @param login       Login for connection to a DB
     * @param dataBaseURL URL for connection to a DB
     */
    public DataBase(Metro metro, String password, String login, String dataBaseURL) {
        this.metro = metro;
        this.password = password;
        this.login = login;
        this.dataBaseURL = dataBaseURL;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            source = new JdbcConnectionSource(dataBaseURL, login, password);

            daoLine = DaoManager.createDao(source, Line.class);
            daoDepot = DaoManager.createDao(source, Depot.class);
            daoRailwayCarriage = DaoManager.createDao(source, RailwayCarriage.class);
            daoStation = DaoManager.createDao(source, Station.class);
            daoLobby = DaoManager.createDao(source, Lobby.class);
            daoDriver = DaoManager.createDao(source, Driver.class);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for receiving data from a DB
     *
     * @throws SQLException
     */
    public void getData() throws SQLException {
        QueryBuilder<Line, Integer> queryBuilderLine = daoLine.queryBuilder();
        QueryBuilder<RailwayCarriage, Integer> queryBuilderRailwayCarriage = daoRailwayCarriage.queryBuilder();
        QueryBuilder<Station, Integer> queryBuilderStation = daoStation.queryBuilder();
        QueryBuilder<Driver, Integer> queryBuilderDriver = daoDriver.queryBuilder();

        //Get lines from DB
        metro.setLinesInSubway(new ArrayList<>(queryBuilderLine.query()));

        for (Line line : metro.getLinesInSubway()) {
            //get depot for the line
            line.setDepotOnThisLine(daoDepot.queryForId(line.getId()));

            //get railway carriages for the line
            List<RailwayCarriage> carriages = queryBuilderRailwayCarriage.where().eq("depotId", line.getDepotOnThisLine().getNumberOfDepot()).query();

            line.getDepotOnThisLine().setSimpleRailwayCarriages(carriages.stream().filter(carriage -> !carriage.isMainCarriage()).collect(Collectors.toCollection(ArrayList::new)));
            line.getDepotOnThisLine().setMainRailwayCarriages(carriages.stream().filter(RailwayCarriage::isMainCarriage).collect(Collectors.toCollection(ArrayList::new)));

            //get stations, which built on the line from DB
            line.setStations(new LinkedList<>(queryBuilderStation.query()));

            //get lobbies for all stations on a line
            line.getStations().stream().forEach((station) -> {
                try {
                    station.setLobby(daoLobby.queryForId(station.getName()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            line.setDrivers(new PriorityBlockingQueue<>(queryBuilderDriver.query()));
        }
    }
}
