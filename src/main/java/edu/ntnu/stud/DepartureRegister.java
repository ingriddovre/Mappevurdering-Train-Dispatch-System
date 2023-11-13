package edu.ntnu.stud;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the DepartureRegister class. This class is a subclass of the TrainDeparture class.
 * It is a register for all the train departures, and handles the methods for the user-interface.
 * The DepartureRegister class is used to register new departures, remove departures, set track to departures, set delay to departures,
 * search for departures by train number and destination, and to update the time.
 * The DepartureRegister class is used by the UserInterface class.
 * The DepartureRegister class uses the TrainDeparture class.
 * The constructor initialized an ArrayList for all the departures. All new departures are added to this ArrayList, and removed when the train has departed.
 * The constructor
 */
public class DepartureRegister {
  private ArrayList<TrainDeparture> allDepartures;

  public DepartureRegister() {
    allDepartures = new ArrayList<>();
  }

  public void newDeparture(int departureTime, String line, int trainNumber, String destination, int track, int delay) {
    TrainDeparture departure = new TrainDeparture(departureTime, line, trainNumber, destination, track, delay);
    allDepartures.add(departure);
  }

/**
 * This method is used to check if a departure already exists, after the user has entered a new departure time and line.
 * If the departure already exists, the user is asked to enter a new departure time and line.
 * @param departureTime
 * @param line
 * @return
 */

/**
public int checkIfDepartureExists(int departureTime, String line) {
    boolean exists = false;
    for (TrainDeparture departure : allDepartures) {
      if (line.equalsIgnoreCase(departure.getLine()) && departureTime == departure.getDepartureTime()) {
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
*/

  public void showAllDepartures() {
    //oversikt over alle alle togavganger
    //hvis tiden passerer en avgang skal den fjernes fra oversikten

  }

  public void removeDeparture() {
    //fjerne en avgang fra oversikten

  }

  public void setTrackToDeparture(int trainNumber) {
    //tildele spor til en avgang ved å søke opp tognummer
    //har allerede setTrack metode i TrainDeparture klassen tho
    //lambda løkke for å finne riktig togavgang i listen
    //TrainDeparture.setTrack();
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


