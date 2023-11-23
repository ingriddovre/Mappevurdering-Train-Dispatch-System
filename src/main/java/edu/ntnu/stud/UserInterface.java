package edu.ntnu.stud;
/**
 * This class is the user-interface for the train dispatch application.
 *
 * @since 0.3
 * @author Ingrid Midtmoen Døvre
 * @version 0.4
 */
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * This class is the user-interface for the train dispatch application.
 *
 */
public class UserInterface {
  DepartureRegister depReg = new DepartureRegister();
  Time time = new Time();
  Scanner scanner = new Scanner(System.in);
  /**
   * This method is used to initialize the user-interface.
   * The user is asked to register the current time, and is then presented with a menu of options.
   * The user can choose to register a new departure, remove a departure, set track to a departure, set delay to a departure,
   * search for departures by train number and destination, update the time, show all departures, or exit the application.
   * The user is asked to choose an option until the user chooses to exit the application.
   * This method is used in the main method in the TrainDispatchApp class.
   *
   * @see TrainDispatchApp The Train Dispatch Application class.
   */

  public void init() {

    System.out.println("""
        Please choose one of the following options:
        1. Register a new departure.
        2. Set track to a departure.
        3. Set delay to a departure.
        4. Search for departures by train number.
        5. Search for departures by destination.
        6. Update the time.
        7. Show all departures.
        8. Show departures leaving after a chosen time.
        8. Exit.""");
  }
  /**
   * This method is used to start the user-interface and the Train Dispatch Application.
   */

  public void start() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the Train Dispatch Application!");
    System.out.println("First, register you current time in the format (HH:MM): ");
    String currentTime = scanner.nextLine();
    time.setCurrentTime(currentTime);
    System.out.println("You set the current time to be: " + time.getCurrentTime());

    DepartureRegister depReg = new DepartureRegister();
    depReg.getAllDepartures().add(new TrainDeparture("12:00", "L1", 1, "Oslo", 2, 0));
    depReg.getAllDepartures().add(new TrainDeparture("13:00", "L2", 2, "Trondheim", 3, 15));
    depReg.getAllDepartures().add(new TrainDeparture("14:00", "L3", 3, "Bergen", 4, 0));
    depReg.getAllDepartures().add(new TrainDeparture("15:00", "L4", 4, "Stavanger", 5, 5));

    System.out.println("Here is a list of all departures: ");
    depReg.showListOfDepartures(depReg.getAllDepartures());
    init();
    int choice = scanner.nextInt();
    while (choice != 9) {
      init();
      choice = scanner.nextInt();
      switch (choice) {
        case 1 -> uiNewDeparture();
        case 2 -> uiSetTrack();
        case 3 -> uiSetDelay();
        case 4 -> uiSearchByTrainNumber();
        case 5 -> uiSearchByDestination();
        case 6 -> uiSetNewTime();
        case 7 -> {
          System.out.println(depReg.showListOfDepartures(depReg.getAllDepartures()));
        }
        case 8 -> uiRemoveDeparturesBeforeChosenTime();
        default -> System.out.println("Invalid input. Please try again.");
      }
    }
    System.out.println("Exiting the Train Dispatch Application.");
  }

  /**
   * This method is used to communicate with the user before registering a new departure.
   * The method asks the user to enter the departure time, line, train number, destination, and
   * possible track and delay. As well as checking if the departure already exists in the departure
   * register. The method also checks the user input to make sure it is valid. If it already
   * exists a departure with the same train number as the user input, the departure will not be
   * registered.
   */
  private void uiNewDeparture() {
    System.out.println("What is the train number? ");
    int trainNumber = scanner.nextInt();
    while (trainNumber < 0) {
      System.out.println("The train number cannot be negative.");
      trainNumber = scanner.nextInt();
    }
    if (depReg.checkIfDepartureExists(trainNumber)) { // TODO: skriv i rapport hvorfor du velger å gi bruker tilbakemelding om toget finnes...
      System.out.println("The train with train number: " + trainNumber + " is already registered.");
      System.out.println(depReg.showListOfDepartures(depReg.getAllDepartures()));

    } else {
      System.out.println("When is the departure leaving? (HH:MM)");
      String departureTime = scanner.nextLine();
      try {
        time.verifyInputOfTime(departureTime, "departure time");
      } catch (DateTimeException e) {
        System.out.println("Please enter the time in the correct format (HH:MM).");
      } finally {
        departureTime = scanner.nextLine();
      }

      System.out.println("Which line is the departure going? (example: L1)");
      String line = scanner.nextLine();
      while (line == null || line.isEmpty()) {
        System.out.println("Please enter a line.");
        line = scanner.nextLine();
      }

      System.out.println("What is the destination for this train departure? ");
      String destination = scanner.nextLine();
      while (destination == null || destination.isEmpty()) {
        System.out.println("Please enter a destination.");
        destination = scanner.nextLine();
      }
      System.out.println("What track is the departure going from?\nType -1 if you don't know yet.");
      int track = scanner.nextInt();
      while (track < -1) {
        System.out.println("Enter a valid track. If you don't know the track yet, type -1");
        track = scanner.nextInt();
      }
      System.out.println("""
              Does the departure have any delay?\s
              If yes: Type the amount of minutes
              If no: Type 0.""");
      int delay = scanner.nextInt();
      while (delay < 0 || delay > 59) {
        System.out.println("Invalid delay. Please try again. \nType 0 if there is no delay.");
        delay = scanner.nextInt();
      }
      depReg.newDeparture(String.valueOf(departureTime), line, trainNumber, destination, track, delay);
      System.out.println("The departure has been registered.");

    }
  }

  /**
   * This method communicates with the user about which track to set to a train departure.
   * The user is asked to enter the train number of the departure, and the track number.
   * The method checks if the train number exists, and if it does, the track is set to the
   * departure with the setTrackToDeparture method. If the train number does not exist,
   * the user is asked to enter a valid train number.
   *
   */
  private void uiSetTrack() {
    System.out.println("What is the train number for the department?");
    int trainNumber = scanner.nextInt();
    while (trainNumber < 0) {
      System.out.println("The train number cannot be negative. Try again.");
      trainNumber = scanner.nextInt();
    }

    if (depReg.checkIfDepartureExists(trainNumber)) {
      System.out.println("Which track would you like to register for this departure?");
      int track = scanner.nextInt();
      while (track < 0) {
        System.out.println("Please enter a valid track.");
        track = scanner.nextInt();
      }
      depReg.setTrackToDeparture(trainNumber, track);
    } else {
      System.out.println("The train number does not exist. Please try again.");
      uiSetTrack();
    }
  }

  /**
   * This method is used to ask the user for what delay to set to a departure.
   * I will search for the departure by using the checkIfDepartureExists() method, and if it exists,
   * it will call the setDelayToDeparture method from the DepartureRegister class.
   * If the departure does not exist, the user is asked to enter a valid train number.
   */
  public void uiSetDelay() {
    System.out.println("What is the train number for the departure?");
    int trainNumber = scanner.nextInt();

    if (depReg.checkIfDepartureExists(trainNumber)) {
      System.out.println("How much delay would you like to register for this departure?");
      int delay = scanner.nextInt();
      while (delay < 0 || delay > 59) {
        System.out.println("Invalid delay. Please try again. \nType 0 if there is no delay.");
        delay = scanner.nextInt();
      }
      depReg.setDelayToDeparture(trainNumber, delay);
    } else {
      System.out.println("The train number does not exist. Please try again.");
      uiSetDelay();
    }
  }

  /**
   * This method is used to only show departures leaving after a chosen time by the user.
   * The user is asked to enter a time, and the method will remove all departures leaving before
   * that time, using the removeDeparturesBeforeChosenTime() method in the DepartureRegister class.
   */
  private void uiRemoveDeparturesBeforeChosenTime() {
    System.out.println("Please specify the time for your preferred earliest departure: ");
    String chosenTime = scanner.nextLine();
    LocalTime preferredTime = null;
    try {
      preferredTime = LocalTime.parse(chosenTime);
    } catch (DateTimeException | NullPointerException e) {
      System.out.println("Please enter the time in the correct format (HH:MM).\nThe time can not be"
          + "less than 00:00 or more than 23:59.");
      uiRemoveDeparturesBeforeChosenTime();
    }
    depReg.removeDeparturesBeforeChosenTime(preferredTime);
  }

  /**
   * This method asks the user to enter a train number, and then asks to enter a track to set for
   * the departure with that train number. The method checks if the departure exists, and if it
   * does, the track is set to the departure with the setTrackToDeparture method. If the departure
   * does not exist, the user is asked to enter a valid train number.
   */
  public void uiSearchByTrainNumber() {
    System.out.println("What is the train number for the departure?");
    int trainNumber = scanner.nextInt();
    if (depReg.checkIfDepartureExists(trainNumber)) {
      depReg.searchByTrainNumber(trainNumber);
    } else {
      System.out.println("The train number does not exist. Please try again.");
      uiSearchByTrainNumber();
    }
  }

  /**
   * This method is used to search for departures by destination. The user is asked to enter a
   * wanted destination, then it checks if the destination exists with the
   * checkIfDestinationExists() method from the DepartureRegister class. If it exists, it will
   * call the searchByDestination() method from the DepartureRegister class.
   * If the destination does not exist, the user is asked to enter a valid destination.
   */
  public void uiSearchByDestination() {
    System.out.println("What is the destination you would like to search for?");
    String destination = scanner.nextLine();
    while (destination == null || destination.isEmpty()) {
      System.out.println("Please enter a destination.");
      destination = scanner.nextLine();
    }
    if (depReg.checkIfDestinationExists(destination)) {
      depReg.searchByDestination(destination);
    } else {
      System.out.println("The destination does not exist. Please try again.");
      uiSearchByDestination();
    }
  }

  /**
   * This method is used to update the time. The user will select what the current time is, and
   * the time will be updated in the DepartureRegister class.
   */
  public void uiSetNewTime() {
    System.out.println("What is the current time? (HH:MM)");
    String newTime = scanner.nextLine();
    time.setCurrentTime(newTime);
  }

}