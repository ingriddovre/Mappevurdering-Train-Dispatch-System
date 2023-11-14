package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;
// TODO: begrunn i rapporten hvorfor du valgte å bruke ArrayList i denne klassen.
// TODO: Se over alle javadoc-kommentarer, de er ikke ferdige.

/**
 * This is the DepartureRegister class. This class is a subclass of the TrainDeparture class.
 * It is a register for all the train departures, and handles the methods for the user-interface.
 * The DepartureRegister class is used to register new departures, remove departures, set track to
 * departures, set delay to departures, search for departures by train number and destination,
 * and to update the time. The DepartureRegister class is used by the UserInterface class.
 * The constructor initialized a public ArrayList for all the departures.
 * All new departures are added to this ArrayList, and removed when it has departed.
 * The ArrayList is public because it is used in the UserInterface and Departure Register classes.
 */
public class DepartureRegister {
  public ArrayList<TrainDeparture> allDepartures;
  private LocalTime time;

  /**
   * The constructor initializes a public ArrayList for all the departures.
   */
  public DepartureRegister() {
    allDepartures = new ArrayList<>();
  }

  /**
   * This method is used to get all the departures registered.
   * @return an ArrayList of all the departures.
   */
  public ArrayList<TrainDeparture> getAllDepartures() {
    return allDepartures;
  }

  /**
   * This is a method...
   */
  public void newDeparture(String departureTime, String line, int trainNumber, String destination,
                           int track, int delay) {
    TrainDeparture departure = new TrainDeparture(departureTime, line, trainNumber, destination,
            track, delay);
    allDepartures.add(departure);
  }

  /**
   * This method is used to check if a departure already exists, after the user has entered a new
   * departure time and line. If the departure already exists, the user is asked to enter a new
   * departure time and line in the user-interface class.
   *
   * @param departureTime
   * @param line
   * @return an Integer value 1 if the departure exists, or 0 if it does not exist.
   */
  public boolean checkIfDepartureExists(String departureTime, String line) {
    AtomicBoolean exists = new AtomicBoolean(false);
    allDepartures.forEach((departure -> {
      if (line.equals(departure.getLine()) && departureTime.equals(departure.getDepartureTime())) {
        exists.set(true);
      }
    }));
    return exists.get();
  }

  /**
   * This method is used to show a list of all departures, sorted from the top,
   * the first departure being the one that is leaving the soonest. If the current time
   * has passed a departure, it will be removed from the list.
   */
  public void showAllDepartures() {
    String[][] overviewOfDepartures = new String[allDepartures.size()][5];
    allDepartures.sort(Comparator.comparing(dep -> String.valueOf(dep.getDepartureTime()))); // wtf just happened here

    allDepartures.forEach(departure -> {
      if (getTime().isAfter(departure.getDepartureTime())) {
        allDepartures.remove(departure);
      }
    });
    for (int i = 0; i < allDepartures.size(); i++) {
      overviewOfDepartures[i][0] = String.valueOf(allDepartures.get(i).getDepartureTime());
      overviewOfDepartures[i][1] = allDepartures.get(i).getLine();
      overviewOfDepartures[i][2] = String.valueOf(allDepartures.get(i).getTrainNumber());
      overviewOfDepartures[i][3] = allDepartures.get(i).getDestination();
      overviewOfDepartures[i][4] = String.valueOf(allDepartures.get(i).getTrack());
    }
  }

  /**
   * This method is used to set  a track to a departure by searching with the train number.
   * @param trainNumber The unique train number of the departure.
   * @param track The track number for the departure.
   */
  public void setTrackToDeparture(int trainNumber, int track) { // nydelig <3 <3 <3
    allDepartures.forEach(departure -> {
      if (trainNumber == departure.getTrainNumber()) {
        departure.setTrack(track);
      }
    });
  }

  /**
   * This method is used to set the delay for a departure, by searching with the train number.
   * @param trainNumber The unique train number of the departure.
   * @param delay The delay of the departure.
   */
  public void setDelayToDeparture(int trainNumber, int delay) {
    allDepartures.forEach(departure -> {
      if (trainNumber == departure.getTrainNumber()) {
        departure.setDelay(delay);
      }
    });
  }

  /**
   * This method is used to search for a department in the register.
   * IT searches for the department in the register, and prints out the information with the
   * toString method from the TrainDeparture class.
   * @param trainNumber The train number of the departure.
   */
  public void searchByTrainNumber(int trainNumber) {
    allDepartures.forEach(departure -> {
      if (trainNumber == departure.getTrainNumber()) {
        System.out.println(departure.toString());
      }
    });
  }

  /**
   * This method is used to search for departures going to a destination. The method prints out
   * a list of all the departures going to the destination. The list is not sorted, but the
   * departures listed have not departed yet.
   * @param destination The destination of the departure.
   */
  public void searchByDestination(String destination) {
    ArrayList<TrainDeparture> toDestination = new ArrayList<>();
    String [][] departuresToDestination = new String[toDestination.size()][5];
    allDepartures.forEach(departure -> {
      if (destination.equals(departure.getDestination()) && getTime().isBefore(departure.getDepartureTime())) {
        toDestination.add(departure);
        for (int i = 0; i < toDestination.size(); i++) {
          departuresToDestination[i][0] = String.valueOf(departure.getDepartureTime());
          departuresToDestination[i][1] = departure.getLine();
          departuresToDestination[i][2] = String.valueOf(departure.getTrainNumber());
          departuresToDestination[i][3] = departure.getDestination();
          departuresToDestination[i][4] = String.valueOf(departure.getTrack());
        }
      }
    });
  }

  /**
   * This method is used to update the time. The time is updated every minute, and the method
   * is called from the UserInterface class.
   * @param newTime The new chosen time.
   * @throws NullPointerException if the time parameter is null or empty.
   */
  public void setTime(String newTime) {
    if (newTime == null || newTime.isEmpty()) {
      throw new NullPointerException("Time is null or empty");
    }
    this.time = LocalTime.parse(newTime);
  }

  /**
   * This method is used to get the current time. This method gets the set time from the user, not
   * the system time.
   * @return a LocalTime object of the current time, selected by the user.
   * @see LocalTime
   * @throws NullPointerException if the time is not set.
   */
  public LocalTime getTime() {
    if (time == null) {
      throw new NullPointerException("Time is not set");
    }
    return time;
  }
}


