package edu.ntnu.stud;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

import static java.time.temporal.ChronoUnit.MINUTES;
// TODO: begrunn i rapporten hvorfor du valgte å bruke ArrayList i denne klassen.
// TODO: Se over alle javadoc-kommentarer, de er ikke ferdige.

/**
 * This is the DepartureRegister class. This class is a subclass of the TrainDeparture class.
 * It is a register for all the train departures, and handles the methods for the user-interface.
 * The DepartureRegister class is used to register new departures, remove departures, set track to
 * departures, set delay to departures, search for departures by train number and destination,
 * and to update the time. The DepartureRegister class is used by the UserInterface class.
 *
 * <p>The constructor initializes a private ArrayList for all the departures.
 * All new departures are added to this ArrayList.
 * The ArrayList is private, but has a public accessor method because it is used in the
 * UserInterface class.
 *
 * @since 0.2
 * @author Ingrid Midtmoen Døvre
 * @version 0.6
 */
public class DepartureRegister {
  private ArrayList<TrainDeparture> allDepartures;
  Time time = new Time();

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
   * This is a method for creating new departures.
   */
  public void newDeparture(String departureTime, String line, int trainNumber, String destination,
                           int track, int delay) throws DateTimeException, NullPointerException,
                            IllegalArgumentException {
    TrainDeparture departure = new TrainDeparture(departureTime, line, trainNumber, destination,
            track, delay);
    allDepartures.add(departure);
  }

  /**
   * This method is used to check if a departure already exists, after the user has entered a new
   * train number. If the departure already exists, the user is asked to enter a new, or another,
   * train number in the user-interface class.

   * @param trainNumber The unique train number of the departure.
   * @return a boolean value of true if the departure exists, or false, if otherwise.
   */
  public boolean checkIfDepartureExists(int trainNumber) {
    boolean exists = false;
    for (TrainDeparture departure : allDepartures) {
      if (trainNumber == departure.getTrainNumber()) {
        exists = true;
        break;
      }
    }
    return exists;
  }

  /**
   * This method is used to show departures that leaves after a chosen time. It initializes a new
   * ArrayList, and then checks if the departure time of departures in allDepartures is before the
   * chosen time. If it is, the departure is added to the new list. Then the list is sorted, and
   * showed to the user via the sortListByTime() and showListOfDepartures() method.

   * @param chosenTime LocalTime object of the chosen time by the user.
   * @return a String object with all information about the departures leaving after the
   *      chosenTime, as a list.
   */
  public String removeDeparturesBeforeChosenTime(LocalTime chosenTime) {
    ArrayList<TrainDeparture> departuresBeforeTime = new ArrayList<>();
    allDepartures.forEach(departure -> {
      if (departure.getDepartureTime().isAfter(chosenTime)) {
        departuresBeforeTime.add(departure);
      }
    });
    return showListOfDepartures(departuresBeforeTime);
  }

  /**
   * This method is used to set  a track to a departure by searching with the train number.
   *

   * @param trainNumber The unique train number of the departure.
   * @param track The track number for the departure.
   */
  public void setTrackToDeparture(int trainNumber, int track) {
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
   * This method is used before setting a new departure time, to find the departure with the right
   * train number. Then the method calls the setNewDepartureTime() method in the Train Departure
   * class to set the new departure time.

   * @param trainNumber The unique train number of the departure.
   * @param newDepartureTime The new departure time of the departure.
   */
  public void setNewDepartureTime(int trainNumber, String newDepartureTime) {
    allDepartures.forEach(departure -> {
      if (trainNumber == departure.getTrainNumber()) {
        departure.setNewDepartureTime(newDepartureTime);
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
  protected ArrayList<TrainDeparture> sortListByTime(ArrayList<TrainDeparture> listOfDepartures) {
    ArrayList<TrainDeparture> departuresAfterCurrentTime = new ArrayList<>();
    listOfDepartures.forEach(departure -> {
      if (time.getCurrentTime().isBefore(departure.getDepartureTime())) {
        departuresAfterCurrentTime.add(departure);
      }
    });
    departuresAfterCurrentTime.sort(Comparator.comparing(TrainDeparture::getDepartureTime));
    return departuresAfterCurrentTime;
  }

  /**
   * This method creates a list of departures from a given ArrayList of departures.

   * @param chosenDepartures The ArrayList of chosen departures.
   * @return a String object with all information about the departures, as a list.
   */
  public String showListOfDepartures(ArrayList<TrainDeparture> chosenDepartures) {
    StringBuilder listOfDepartures = new StringBuilder();
    listOfDepartures.append("------------------------------------------------------------"
        + "----------------------------\n");
    listOfDepartures.append(String.format("| %-13s | %-14s | %-5s | %20s | %-5s | %12s |",
        "Train number", "Departure time", "Line", "Destination", "Track", "Time left"));
    listOfDepartures.append("""

        ----------------------------------------------------------------------------------------
        """);
    ArrayList<TrainDeparture> newSortedListOfDepartures = sortListByTime(chosenDepartures);
    newSortedListOfDepartures.forEach(departure -> {
      listOfDepartures.append(String.format("| %13s | %14s | %5s | %20s | %5s | %12s |\n",
          departure.getTrainNumber(), departure.getDepartureTime(), departure.getLine(),
          departure.getDestination(), departure.getTrack(),
          minutesUntilDeparture(departure.getDepartureTime(), departure.getTrainNumber())));
    });
    listOfDepartures.append("------------------------------------------------------------"
        + "----------------------------\n");
    return listOfDepartures.toString();
  }

  /**
   * This method is used to search for a department in the register with the train number.
   * It searches for the department in the register, and adds it to a new ArrayList.
   * Then calls the showListOfDepartures() method to show the list of departures.

   * @param trainNumber The train number of the departure.
   */
  public String searchByTrainNumber(int trainNumber) {
    ArrayList<TrainDeparture> departureWithTrainNumber = new ArrayList<>();
    allDepartures.forEach(departure -> {
      if (trainNumber == departure.getTrainNumber()
          && time.getCurrentTime().isBefore(departure.getDepartureTime())) {
        departureWithTrainNumber.add(departure);
      }
    });
    return showListOfDepartures(departureWithTrainNumber);
  }

  /**
   * This method is used to search for departures going to a destination. The method checks the
   * allDepartures ArrayList for the departures going to the specified destination.
   * Then adds those departures into a new ArrayList which is used to make the visual list for
   * the user, by calling the showListOfDepartures() method.

   * @param destination The destination of the departure.
   */
  public String searchByDestination(String destination) {
    ArrayList<TrainDeparture> toDestination = new ArrayList<>();
    allDepartures.forEach(departure -> {
      if (destination.equals(departure.getDestination())
          && time.getCurrentTime().isBefore(departure.getDepartureTime())) {
        toDestination.add(departure);
      }
    });
    return showListOfDepartures(toDestination);
  }

  /**
   * This method is used to check if any train departures are going to a specific destination.
   * The method is used in the user interface class, to check if a destination exists in the
   * register, before continuing with the search.

   * @param chosenDestination The destination input from the user of the wanted departure.
   * @return boolean value if the destination exists or not.
   */
  public boolean checkIfDestinationExists(String chosenDestination) {
    boolean exists = false;
    for (TrainDeparture departure : allDepartures) {
      if (departure.getDestination().equalsIgnoreCase(chosenDestination)) {
        exists = true;
        break;
      }
    }
    return exists;
  }

  /**
   * This method calculates the minutes left before departure time. If the train does not exist
   * in the register, the method returns null.
   * <p>It is set to protected because it is only used in this class inside the
   * searchByDestination() and showAllDepartures() methods. As well as in the
   * DepartureRegisterTest class. Therefore, it cant be private.</p>

   * @param departureTime the departure time of the departure.
   * @param trainNumber the train number of the departure.
   * @return a String representation of the time left before departure.
   */
  protected String minutesUntilDeparture(LocalTime departureTime, int trainNumber) {
    double timeLeft = 0.0;
    boolean departureExists = false;
    String hoursAndMin = "";
    for (TrainDeparture departure : allDepartures) {
      if (departure.getDepartureTime().equals(departureTime) && departure.getTrainNumber()
          == trainNumber) {
        timeLeft = MINUTES.between(time.getCurrentTime(), departureTime);
        departureExists = true;
      }
    }
    if (!departureExists || timeLeft < 0) {
      hoursAndMin = "";
    } else if (timeLeft < 60) {
      hoursAndMin = (int) timeLeft + " min";
    } else if (timeLeft > 60) {
      int hours = (int) timeLeft / 60;
      int minutes = (int) Math.round((timeLeft % 60) % 60);
      hoursAndMin = hours + "h " + minutes + " min";
    }
    return hoursAndMin;
  }
}


