package infopulse.DataBase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import infopulse.ComponentsOfTrain.RailwayCarriage;
import infopulse.Depot;
import infopulse.Lines.Line;
import infopulse.MainComponents.Metro;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class describe work with DB
 *
 * @author Ishchenko Vladyslav
 * @version 1.0
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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

        metro.setLinesInSubway(queryBuilderLine.query().stream().collect(Collectors.toCollection(ArrayList::new)));

        for (Line line : metro.getLinesInSubway()) {
            line.setDepotOnThisLine(daoDepot.queryForId(line.getId()));

            List<RailwayCarriage> carriages = queryBuilderRailwayCarriage.where().eq("depotId", line.getDepotOnThisLine().getNumberOfDepot()).query();

            line.getDepotOnThisLine().setSimpleRailwayCarriages(carriages.stream().filter(carriage -> !carriage.isMainCarriage()).collect(Collectors.toCollection(ArrayList::new)));
            line.getDepotOnThisLine().setMainRailwayCarriages(carriages.stream().filter(carriage -> carriage.isMainCarriage()).collect(Collectors.toCollection(ArrayList::new)));
        }
    }
}
