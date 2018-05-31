package infopulse;

import infopulse.ComponentsOfTrain.Train;
import infopulse.DataBase.DataBase;

import infopulse.Lines.Station;
import infopulse.MainComponents.Metro;
import infopulse.Runnables.RunTrain;

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

            for(Station station : metro.getLinesInSubway(0).getStations()) {
                station.getLobbyThread().start();
                station.getFirstEscalatorThread().start();
                station.getSecondEscalatorThread().start();
            }

            Train train = metro.getLinesInSubway(0).getTrain(0);

            Thread threadTrain = new Thread(new RunTrain(train, metro, 0));
            threadTrain.start();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
