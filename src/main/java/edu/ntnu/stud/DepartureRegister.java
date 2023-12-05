package edu.ntnu.stud;

<<<<<<< HEAD
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
=======
import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This is the {@code DepartureRegister} class.
 * It is a register for all the {@link TrainDeparture} objects, and handles the methods for the
 * {@link UserInterface}. Since it handles the {@link TrainDeparture} objects, it is a composition
 * of the {@link TrainDeparture} class.
 * <p>The {@code DepartureRegister} is used to register new departures, remove
 * departures, set track to departures, set delay to departures, search for departures
 * by train number and destination, and to update the time. </p>
 *
 * @since 0.2
 * @author Ingrid Midtmoen Døvre
 * @version 0.7
 */
public class DepartureRegister {
  /**
   * The {@link ArrayList} that holds all {@link TrainDeparture} objects. It is set as private final
   * to prevent other classes from changing it.
   */
  private ArrayList<TrainDeparture> allDepartures;
  Time time = new Time();

  /**
   * The constructor initializes a private {@link ArrayList} that holds all the departures
   * registered. All new departures are added to this {@link ArrayList}.
   * The {@link ArrayList} is private, but has a public accessor method because it is used in the
   * {@link UserInterface}. It is set as final to prevent other classes from changing it.
>>>>>>> 71dcbdf (Javadoc for all classes with links)
   */
  public DepartureRegister() {
    allDepartures = new ArrayList<>();
  }

  /**
<<<<<<< HEAD
   * This method is used to get all the departures registered.
   * @return an ArrayList of all the departures.
=======
   * This is the accessor method for the {@code allDepartures} {@link ArrayList}.
   *
   * @return {@code allDepartures}, an {@link ArrayList} of all the departures registered.
>>>>>>> 71dcbdf (Javadoc for all classes with links)
   */
  public ArrayList<TrainDeparture> getAllDepartures() {
    return allDepartures;
  }

  /**
<<<<<<< HEAD
   * This is a method...
   */
  public void newDeparture(String departureTime, String line, int trainNumber, String destination,
                           int track, int delay) {
=======
   * This method is used to register new objects of {@link TrainDeparture} in the
   * {@code allDepartures} {@link ArrayList}. It will first take inn all the parameters to be
   * verified before adding the new departure to {@code allDepartures}.
   *
   * @param departureTime The departure time of the departure.
   * @param line The line of the departure.
   * @param trainNumber The unique train number of the departure.
   * @param destination The destination of the departure.
   * @param track The track number of the departure.
   * @param delay The delay of the departure.
   * @throws DateTimeException if the departure time is not in the correct format.
   * @throws NullPointerException if the departure time is null.
   * @throws IllegalArgumentException if the departure time is not in the correct
   */
  public void newDeparture(String departureTime, String line, int trainNumber, String destination,
                           int track, int delay) throws DateTimeException, NullPointerException,
                            IllegalArgumentException {
>>>>>>> 71dcbdf (Javadoc for all classes with links)
    TrainDeparture departure = new TrainDeparture(departureTime, line, trainNumber, destination,
            track, delay);
    allDepartures.add(departure);
  }

  /**
<<<<<<< HEAD
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
=======
   * This method is used to check if a departure already exists after the user has entered a
   * train number, line and departure time. It is used in the {@link UserInterface} to prevent
   * the user from entering the same departure twice. The method can be used to only check if
   * a train number exists, or if a line and departure time exists with another train number.
   * <p>The method will return true if a train number doesn't exists, but the line and departure
   * time does. Or if the train number exists, but not the line and departure time.</p>
   * If the departure already exists, the user is asked to enter a new, or another,
   * train number, line or departure time in the {@link UserInterface}.
   *
   * @param trainNumber The unique train number of the departure.
   * @param line The line of the departure.
   * @param departureTime The departure time of the departure.
   * @return a boolean value of true if the departure exists, or false, if otherwise.
   */
  public boolean checkIfDepartureExists(int trainNumber, String line, LocalTime departureTime) {
    boolean exists = false;
    for (TrainDeparture departure : allDepartures) {
      if (trainNumber == departure.getTrainNumber()) {
        exists = true;
        break;
      } else if (line.equalsIgnoreCase(departure.getLine())
          && departureTime == departure.getDepartureTime()) {
        exists = true;
        break;
      }
    }
    return exists;
  }

  /**
   * This method is used to show departures that leaves after a chosen time. It initializes a new
   * {@link ArrayList}, and then checks if the departure time of departures in {@code allDepartures}
   * is before the chosen time. If it is, the departure is added to the new list.
   * Then the list is sorted, and showed to the user via the {@code sortListByTime()}
   * and {@code showListOfDepartures()} methods.
   *
   * @param chosenTime {@link LocalTime} object of the chosen time by the user.
   * @return a String object with all information about the departures leaving after the
   *      {@code chosenTime}, as a list.
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
   * This method is used to set a track to a departure by searching for the train number.
   * It uses a for-each loop to check all the departures in the register, and if the train number
   * matches, it calls the departure in the {@link TrainDeparture} class to set the track.
   *
   * @param trainNumber The unique train number of the departure.
   * @param track The track number for the departure.
   */
  public void setTrackToDeparture(int trainNumber, int track) {
>>>>>>> 71dcbdf (Javadoc for all classes with links)
    allDepartures.forEach(departure -> {
      if (trainNumber == departure.getTrainNumber()) {
        departure.setTrack(track);
      }
    });
  }

  /**
   * This method is used to set the delay for a departure, by searching with the train number.
<<<<<<< HEAD
=======
   * It uses a for-each loop to check all the departures in the register, and if the train number
   * matches, it calls the departure in the {@link TrainDeparture} class to set the delay.
   *
>>>>>>> 71dcbdf (Javadoc for all classes with links)
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
<<<<<<< HEAD
   * This method is used to search for a department in the register.
   * IT searches for the department in the register, and prints out the information with the
   * toString method from the TrainDeparture class.
   * @param trainNumber The train number of the departure.
   */
  public void searchByTrainNumber(int trainNumber) {
    allDepartures.forEach(departure -> {
      if (trainNumber == departure.getTrainNumber()) {
        System.out.println(departure.toString());
=======
   * This method is used before setting a new departure time, to find the departure with the right
   * train number with a for-each loop. Then the method calls the {@code setNewDepartureTime()}
   * method in the {@link TrainDeparture} class to set the new departure time.
   *
   * @param trainNumber The unique train number of the departure.
   * @param newDepartureTime The new departure time of the departure.
   */
  public void setNewDepartureTime(int trainNumber, String newDepartureTime) {
    allDepartures.forEach(departure -> {
      if (trainNumber == departure.getTrainNumber()) {
        departure.setNewDepartureTime(newDepartureTime);
>>>>>>> 71dcbdf (Javadoc for all classes with links)
      }
    });
  }

  /**
<<<<<<< HEAD
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
=======
   * This method is sorting an {@link ArrayList} by time before it is shown to the user via the
   * {@code showListOfDepartures()} method. First, it checks all the departures in the
   * {@code listOfDepartures} list if the current time is before the departure time.
   * If it is, the departure is added to the new {@link ArrayList departuresAfterCurrentTime}.
   * Then the {@code departuresAfterCurrentTime} list is sorted by time, and returned as a new
   * {@link ArrayList}.
   *
   * @param listOfDepartures An {@link ArrayList} of {@link TrainDeparture} to be sorted.
   * @return a new sorted {@link ArrayList} of {@link TrainDeparture}.
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
   * This method creates a list of departures from a given {@link ArrayList} of departures.
   * It uses the {@code sortListByTime()} method to sort the list by time before showing it to
   * the user. First it creates a StringBuilder object, and then adds the header of the list.
   * Then it uses a for-each loop to add all the departures to the list, and then adds the footer
   * of the list. The method returns the list as a String object.
   * <p>The list is showing the train number, departure time, line, destination, track and the
   * minutes left until the departure.</p>
   * <p>The method is created with support from ChatGPT to find the String.format() method
   * that can create exact sized columns for the list of departures.</p>
   *
   * @param chosenDepartures The {@link ArrayList} of chosen departures.
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
          minutesUntilDeparture(departure.getTrainNumber())));
    });
    listOfDepartures.append("------------------------------------------------------------"
        + "----------------------------\n");
    return listOfDepartures.toString();
  }

  /**
   * This method is used to search for a departure in {@code allDepartures} with a given train
   * number. It searches for the departure with the same train number in {@code allDepartures},
   * and adds it to a new {@link ArrayList}, {@code departureWithTrainNumber}. It will only be added
   * if the train number is the same, and the departure time is after the current time.
   * It then calls the {@code showListOfDepartures()} method to show the list of departures.
   *
   * @param trainNumber The train number of the departure.
   * @return a String object with all information about the departures with the train number,
   *      as a list.
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
   * This method is used to search for departures going to a specific destination.
   * The method checks the {@code allDepartures} {@link ArrayList} for the departures going to the
   * specified destination, and has departure time after the current time. It then adds those
   * departures into a new {@link ArrayList} which is used to make the visual list for
   * the user, by calling {@code showListOfDepartures()}.
   *
   * @param destination The final destination of the departure.
   * @return a String object with all information about the departures going to the destination,
   *     as a list.
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
   * The method is used in the {@link UserInterface}, to check if a destination exists in the
   * register, before continuing with the search.
   *
   * @param chosenDestination The destination input from the user.
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
   * in the register, the method returns an empty String.
   * <p>It is set to protected because it is only used in this class inside the
   * {@code searchByDestination()} and {@code showAllDepartures()} methods. As well as in the
   * {@code DepartureRegisterTest} class. Therefore, it can not be private.</p>
   *
   * @param trainNumber the train number of the departure.
   * @return a String representation of the time left before departure. Example: 1h 30 min.
   */
  protected String minutesUntilDeparture(int trainNumber) {
    double timeLeft = 0.0;
    boolean departureExists = false;
    String hoursAndMin = "";
    for (TrainDeparture departure : allDepartures) {
      if (departure.getTrainNumber() == trainNumber) {
        timeLeft = MINUTES.between(time.getCurrentTime(), departure.getDepartureTime());
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
>>>>>>> 71dcbdf (Javadoc for all classes with links)
  }
}


