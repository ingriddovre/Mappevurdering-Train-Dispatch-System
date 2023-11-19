package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;
// TODO: begrunn i rapporten hvorfor du valgte å bruke ArrayList i denne klassen.
// TODO: Se over alle javadoc-kommentarer, de er ikke ferdige.
// todo: i lista som viser togavgangene, har jeg tatt med delay i getDepartureTime, er dette er problem siden det står at man
//  ikke skal gjøre det, eller er det greit å ha det med?

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
   * This is an accessor method for the allDepartures ArrayList.
   * @return allDepartures, an ArrayList of all the departures registered.
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
   * @param departureTime The departure time of the departure.
   * @param line The line of the departure.
   * @return an Integer value 1 if the departure exists, or 0 if it does not exist.
   */
  public boolean checkIfDepartureExists(LocalTime departureTime, String line) {
    boolean exists = false;
    for (TrainDeparture departure : allDepartures) {
      if (line.equals(departure.getLine()) && departureTime.equals(departure.getDepartureTime())) {
        exists = true;
      }
    }
    return exists;
  }

  /**
   * This method is used to show departures that left before a chosen time. It initializes a new
   * ArrayList of all departures registered, and then checks if the departure time is before the
   * chosen time. If it is, the departure is removed from the new list. Then the list is sorted, and
   * showed to the user via the sortListByTime() and showListOfDepartures() method.
   * @param chosenTime LocalTime object of the chosen time by the user.
   */
  // todo: er denne metoden samme som skal fjerne avganger fra hovedlisten, eller er denne ment for
  //  å være en egen metode i menyen?
  public void removeDeparturesBeforeChosenTime(LocalTime chosenTime) {
    ArrayList<TrainDeparture> departuresBeforeTime = new ArrayList<>();
    departuresBeforeTime.addAll(allDepartures);
    departuresBeforeTime.forEach(departure -> {
      if (departure.getDepartureTime().isBefore(chosenTime)) {
        departuresBeforeTime.remove(departure);
      }
    });
    sortListByTime(departuresBeforeTime);
    showListOfDepartures(departuresBeforeTime);
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
   * This method is sorting ArrayLists by time before they are shown to the user via the
   * showListOfDepartures() method. First, it checks all the departures in the list if the current
   * time is after the departure time. If it is, the departure is removed from the list. Then the
   * list is sorted by time, and shown to the user via the showListOfDepartures() method.
   * @param listOfDepartures An ArrayList of Train departures to be sorted.
   */
  public void sortListByTime(ArrayList<TrainDeparture> listOfDepartures) {
    listOfDepartures.forEach(departure -> {
      if (getCurrentTime().isAfter(departure.getDepartureTime())) {
        listOfDepartures.remove(departure);
      }
    });
    listOfDepartures.sort(Comparator.comparing(TrainDeparture::getDepartureTime));
  }

  /**
   * This method creates a list of departures from a given ArrayList of departures.
   * @param chosenDepartures The ArrayList of chosen departures.
   * @return a String object with all information about the departures, as a list.
   */
  public String showListOfDepartures(ArrayList<TrainDeparture> chosenDepartures) {
    StringBuilder listOfDepartures = new StringBuilder();
    listOfDepartures.append("---------------------------------------------------------------"
        + "-------\n");
    listOfDepartures.append(String.format("| %-10s | %-5s | %-25s | %-5s | %10s |",
        "Departure Time", "Line", "Destination", "Track", " "));
    listOfDepartures.append("---------------------------------------------------------------"
        + "-------\n");
    chosenDepartures.forEach(departure -> {
      listOfDepartures.append(String.format("| %-10s | %-5s | %-25s | %-5s | %-10s |",
          departure.getDepartureTime(), departure.getLine(), departure.getDestination(),
          departure.getTrack(), minutesUntilDeparture(departure.getDepartureTime(),
              departure.getTrainNumber())));
    });
    listOfDepartures.append("---------------------------------------------------------------"
        + "-------\n");
    return listOfDepartures.toString();
  }

  /**
   * This method is used to search for a department in the register with the train number.
   * It searches for the department in the register, and adds it to a new ArrayList.
   * Then calls the showListOfDepartures() method to show the list of departures.
   * @param trainNumber The train number of the departure.
   */
  public void searchByTrainNumber(int trainNumber) {
    ArrayList<TrainDeparture> departureWithTrainNumber = new ArrayList<>();
    allDepartures.forEach(departure -> {
      if (trainNumber == departure.getTrainNumber()) {
        departureWithTrainNumber.add(departure);
      }
    });
    sortListByTime(departureWithTrainNumber);
    showListOfDepartures(departureWithTrainNumber);
  }

  /**
   * This method is used to search for departures going to a destination. The method checks the
   * allDepartures ArrayList for the departures going to the specified destination.
   * Then adds those departures into a new ArrayList which is used to make the visual list for
   * the user, by calling the showListOfDepartures() method.
   * @param destination The destination of the departure.
   */
  public void searchByDestination(String destination) {
    ArrayList<TrainDeparture> toDestination = new ArrayList<>();
    allDepartures.forEach(departure -> {
      if (destination.equals(departure.getDestination())
          && getCurrentTime().isBefore(departure.getDepartureTime())) {
        toDestination.add(departure);
      }
    });
    sortListByTime(toDestination);
    showListOfDepartures(toDestination);
  }

  /**
   * This method calculates the minutes left before departure time. It is set to private because it
   * is only used in this class inside the searchByDestination() and showAllDepartures() methods.
   * @param departureTime the departure time of the departure.
   * @param trainNumber the train number of the departure.
   * @return an Integer value of the minutes left before departure.
   */
  private int minutesUntilDeparture(LocalTime departureTime, int trainNumber) {
    int minutesLeft = 0;
    for (TrainDeparture departure : allDepartures) {
      if (departure.getDepartureTime().equals(departureTime) && departure.getTrainNumber() == trainNumber) {
        minutesLeft = getCurrentTime().compareTo(departure.getDepartureTime());
      }
    }
    return minutesLeft;
  }

  /**
   * This method is used to update the time. The time is updated every minute, and the method
   * is called from the UserInterface class.
   * @param newTime The new chosen time.
   * @throws NullPointerException if the time parameter is null or empty.
   * @throws DateTimeException if the time is invalid.
   */
  public void setCurrentTime(String newTime) {
    try {
      this.time = LocalTime.parse(newTime);
      if (newTime == null || newTime.isEmpty()) {
        throw new NullPointerException("Time is null or empty");
      }
    } catch (NullPointerException | DateTimeException e) {
      System.out.println("Invalid time : " + newTime + "Please enter a valid time.");
    }
  }

  /**
   * This method is used to get the current time. This method gets the set time from the user, not
   * the system time.
   * @return a LocalTime object of the current time selected by the user.
   * @see LocalTime
   * @throws NullPointerException if the time is not set.
   * @throws DateTimeException if the time is invalid.
   */
  public LocalTime getCurrentTime() {
    try {
      time = LocalTime.parse(String.valueOf(time));
      if (time == null) {
        throw new NullPointerException("Invalid time : " + time);
      }
    } catch (NullPointerException | DateTimeException e) {
      System.out.println("Please enter a valid time.");
    }
    return time;
  }
}


