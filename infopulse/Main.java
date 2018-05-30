package infopulse;

import infopulse.ComponentsOfTrain.Train;
import infopulse.DataBase.DataBase;
import infopulse.Exceptions.NegativeValueException;
import infopulse.Lines.Lobby;
import infopulse.Lines.Station;
import infopulse.MainComponents.Metro;
import infopulse.Runnables.RunEscalator;
import infopulse.Runnables.RunLobby;
import infopulse.Runnables.RunTrain;
import infopulse.people.Driver;
import infopulse.people.Experience;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Metro metro = new Metro();

        try {
            DataBase dataBase = new DataBase(metro, "vla16dys01lav99", "root", "jdbc:mysql://localhost/subway?" );

            dataBase.getData();

            for(int i = 0; i < metro.countLines(); i++) {
                metro.getLinesInSubway(i).buildAllPossibleTrain();
            }

            /*Station akademmistechko = new Station(1, "Akademmistechko", true);
            akademmistechko.setLobby(new Lobby("Akademmistechko Lobby"));
            metro.getLinesInSubway(0).buildStation(akademmistechko);

            Thread lobbyAkademmistechko = new Thread(new RunLobby(akademmistechko, 2000), "Akademmistechko Lobby");
            Thread firstEscalatorAkademmistechko = new Thread(new RunEscalator(akademmistechko, akademmistechko.getLobby(), 0, 3000), "First Escalator Akademmistechko");
            Thread secondEscalatorAkademmistechko = new Thread(new RunEscalator(akademmistechko, akademmistechko.getLobby(), 1, 3000), "Second Escalator Akademmistechko");

            Station zhytomyrska = new Station(2, "Zhytomyrska", false);
            zhytomyrska.setLobby(new Lobby("Zhytomyrska Lobby"));
            metro.getLinesInSubway(0).buildStation(zhytomyrska);

            Thread lobbyZhytomyrska = new Thread(new RunLobby(zhytomyrska, 2000), "Zhytomyrska Lobby");
            Thread firstEscalatorZhytomyrska = new Thread(new RunEscalator(zhytomyrska, zhytomyrska.getLobby(),0, 3000), "First Escalator Zhytomyrska");
            Thread secondEscalatorZhytomyrska = new Thread(new RunEscalator(zhytomyrska, zhytomyrska.getLobby(),1, 3000), "Second Escalator Zhytomyrska");

            Station sviatoshyn = new Station(3, "Sviatoshyn", false);
            sviatoshyn.setLobby(new Lobby("Sviatoshyn Lobby"));
            metro.getLinesInSubway(0).buildStation(sviatoshyn);

            Thread lobbySviatoshyn = new Thread(new RunLobby(sviatoshyn, 2000), "Sviatoshyn Lobby");
            Thread firstEscalatorSviatoshyn = new Thread(new RunEscalator(sviatoshyn, sviatoshyn.getLobby(),0, 3000), "First Escalator Sviatoshyn");
            Thread secondEscalatorSviatoshyn = new Thread(new RunEscalator(sviatoshyn, sviatoshyn.getLobby(),1, 3000), "Second Escalator Sviatoshyn");

            Station nyvky = new Station(4, "Nyvky", false);
            nyvky.setLobby(new Lobby("Nyvky Lobby"));
            metro.getLinesInSubway(0).buildStation(nyvky);

            Thread lobbyNyvky = new Thread(new RunLobby(nyvky, 2000), "Nyvky Lobby");
            Thread firstEscalatorNyvky = new Thread(new RunEscalator(nyvky, nyvky.getLobby(),0, 3000), "First Escalator Nyvky");
            Thread secondEscalatorNyvky = new Thread(new RunEscalator(nyvky, nyvky.getLobby(),1, 3000), "Second Escalator Nyvky");

            Station beresteiska = new Station(5, "Beresteiska", false);
            beresteiska.setLobby(new Lobby("Beresteiska Lobby"));
            metro.getLinesInSubway(0).buildStation(beresteiska);

            Thread lobbyBeresteiska = new Thread(new RunLobby(beresteiska, 2000), "Beresteiska Lobby");
            Thread firstEscalatorBeresteiska = new Thread(new RunEscalator(beresteiska, beresteiska.getLobby(),0, 3000), "First Escalator Beresteiska");
            Thread secondEscalatorBeresteiska = new Thread(new RunEscalator(beresteiska, beresteiska.getLobby(),1, 3000), "Second Escalator Beresteiska");

            Station shuliavska = new Station(6, "Shuliavska", false);
            shuliavska.setLobby(new Lobby("Shuliavska Lobby"));
            metro.getLinesInSubway(0).buildStation(shuliavska);

            Thread lobbyShuliavska = new Thread(new RunLobby(shuliavska, 2000), "Shuliavska Lobby");
            Thread firstEscalatorShuliavska = new Thread(new RunEscalator(shuliavska, shuliavska.getLobby(),0, 3000), "First Escalator Shuliavska");
            Thread secondEscalatorShuliavska = new Thread(new RunEscalator(shuliavska, shuliavska.getLobby(),1, 3000), "Second Escalator Shuliavska");

            Station kpi = new Station(7, "KPI", true);
            kpi.setLobby(new Lobby("KPI Lobby"));
            metro.getLinesInSubway(0).buildStation(kpi);

            Thread lobbyKPI = new Thread(new RunLobby(kpi, 2000), "KPI Lobby");
            Thread firstEscalatorKPI = new Thread(new RunEscalator(kpi, kpi.getLobby(),0, 3000), "First Escalator KPI");
            Thread secondEscalatorKPI = new Thread(new RunEscalator(kpi, kpi.getLobby(),1, 3000), "Second Escalator KPI");


            metro.getLinesInSubway(0).addDriver(new Driver(1, "Nikolay", "Kozlov", Experience.Middle));
            metro.getLinesInSubway(0).addDriver(new Driver(2, "Alex", "Nosov", Experience.Senior));
            metro.getLinesInSubway(0).addDriver(new Driver(3, "Sergey", "Izotov", Experience.Senior));
            metro.getLinesInSubway(0).addDriver(new Driver(4, "Artem", "Korolenko", Experience.Junior));
            metro.getLinesInSubway(0).addDriver(new Driver(5, "Ivan", "Portianoy", Experience.Middle));

            lobbyAkademmistechko.start();
            firstEscalatorAkademmistechko.start();
            secondEscalatorAkademmistechko.start();

            lobbyZhytomyrska.start();
            firstEscalatorZhytomyrska.start();
            secondEscalatorZhytomyrska.start();

            lobbySviatoshyn.start();
            firstEscalatorSviatoshyn.start();
            secondEscalatorSviatoshyn.start();

            lobbyNyvky.start();
            firstEscalatorNyvky.start();
            secondEscalatorNyvky.start();

            lobbyBeresteiska.start();
            firstEscalatorBeresteiska.start();
            secondEscalatorBeresteiska.start();

            lobbyShuliavska.start();
            firstEscalatorShuliavska.start();
            secondEscalatorShuliavska.start();

            lobbyKPI.start();
            firstEscalatorKPI.start();
            secondEscalatorKPI.start();*/

            System.out.println(metro.getLinesInSubway(0).getTrains().size());

            Train train = metro.getLinesInSubway(0).getTrain(0);

/*            Thread threadTrain = new Thread(new RunTrain(train, metro, 0));
            threadTrain.start();*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
