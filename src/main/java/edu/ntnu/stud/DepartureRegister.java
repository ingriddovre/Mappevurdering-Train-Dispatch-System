package edu.ntnu.stud;
import java.util.ArrayList;
import java.util.Scanner;
public class DepartureRegister {
    private static ArrayList<TrainDeparture> allDepartures; // hvorfor vil jeg ha denne statisk?? den skal være en dynamisk liste
    public DepartureRegister() {
        allDepartures = new ArrayList<>();
    }
    public void newDeparture(int departureTime, String line, int trainNumber, String destination, int track, int delay) {
        TrainDeparture departure = new TrainDeparture(departureTime, line, trainNumber, destination, track, delay);
        allDepartures.add(departure);
    }
    public int checkIfDepartureExists(int departureTime, String line) {
        boolean exists = false;
        for (TrainDeparture departure : allDepartures) {
            if(line.equalsIgnoreCase(departure.getLine()) && departureTime == departure.getDepartureTime()) {
                exists = true;
                break;
            }
        }
        if (exists) {
            return 1;
        } else {
            return 0;
        }
    }
    public void showAllDepartures() {
        //oversikt over alle alle togavganger
        //hvis tiden passerer en avgang skal den fjernes fra oversikten
    }
    public void removeDeparture() {
        //fjerne en avgang fra oversikten

    }
    public void setTrackToDeparture() {
        //tildele spor til en avgang
        //har allerede setTrack metode i edu.ntnu.stud.TrainDeparture klassen tho
    }
    public void setDelayToDeparture() {
        //tildele forsinkelse til en avgang
        //har allerede setDelay metode i edu.ntnu.stud.TrainDeparture klassen tho
    }
    public void searchByTrainNumber() {
        //søke etter en avgang etter tognummer
    }
    public void searchByDestination() {
        //søke etter en avgang etter destinasjon
    }
    public void setTime() {
        //oppdatere klokken ved å spørre bruker etter nytt klokkeslett
    }

}


