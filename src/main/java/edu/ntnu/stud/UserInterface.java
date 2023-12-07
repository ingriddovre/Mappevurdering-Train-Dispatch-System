package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class is the {@code UserInterface} for the train dispatch application. The user interface
 * has 2 methods that is used in the {@link TrainDispatchApp} class to start the application.
 *The {@code init()} method is the menu the user is seeing, and the {@code start()} method
 * has a switch statement that is used to handle the users input.
 * <p>Goal: Handle all the communication with the user and to make sure all input is correct.</p>
 *
 * @since 0.3
 * @author Ingrid Midtmoen Døvre
 * @version 1.0
 */

public class UserInterface {
  DepartureRegister depReg = new DepartureRegister();
  Time time = new Time();
  Scanner scanner = new Scanner(System.in);

  /**
   * This method shows the menu of options for the user. The menu is shown inside the
   * {@code start()} method after the current time is registered.
   */
  private void init() {
    System.out.println("""
        Please choose one of the following options:
        1. Register a new departure.
        2. Set track to a departure.
        3. Set a new departure time for a departure.
        4. Set delay to a departure.
        5. Search for a departure by train number.
        6. Search for departures by destination.
        7. Update the time.
        8. Show all departures.
        9. Show departures leaving after a chosen time.
        10. Exit.""");
  }

  /**
   * This method is used to initialize the {@link UserInterface}. The user is asked to register
   * the current time, and is then presented with the menu of options.
   * The user can choose all options from the menu in the {@code init()} method. The user is asked
   * to choose an option until the user chooses to exit the application. The switch statement
   * calls every other method in the class, depending on the users input.
   */
  public void start() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the Train Dispatch Application!");
    System.out.println("First, register you current time in the format (HH:MM): ");
    String currentTime = scanner.nextLine();
    boolean correctTime = false;
    while (!correctTime) {
      try {
        time.setCurrentTime(currentTime);
        correctTime = true;
        System.out.println("You set the current time to be: " + time.getCurrentTime());
      } catch (DateTimeException e) {
        System.out.println("Please enter a valid time in the format: (HH:MM)");
        currentTime = scanner.nextLine();
      }
    }
    DepartureRegister depReg = new DepartureRegister();
    depReg.getAllDepartures().add(new TrainDeparture("12:00", "L1", 1, "Oslo", 2, 0));
    depReg.getAllDepartures().add(new TrainDeparture("13:00", "L2", 2, "Trondheim", 3, 15));
    depReg.getAllDepartures().add(new TrainDeparture("14:00", "L3", 3, "Bergen", 4, 0));
    depReg.getAllDepartures().add(new TrainDeparture("15:00", "L4", 4, "Stavanger", 5, 5));

    System.out.println("Here is a list of all departures available for you: " + "\n"
        + depReg.showListOfDepartures(depReg.getAllDepartures()));
    boolean exit = false;
    while (!exit) {
      init();
      try {
        int choice = scanner.nextInt();
        switch (choice) {
          case 1: {
            uiNewDeparture();
            break;
          }
          case 2: {
            uiSetTrack();
            break;
          }
          case 3: {
            uiSetNewDepartureTime();
            break;
          }
          case 4: {
            uiSetDelay();
            break;
          }
          case 5: {
            uiSearchByTrainNumber();
            break;
          }
          case 6: {
            uiSearchByDestination();
            break;
          }
          case 7: {
            uiSetNewCurrentTime();
            break;
          }
          case 8: {
            System.out.println(depReg.showListOfDepartures(depReg.getAllDepartures()));
            break;
          }
          case 9: {
            uiRemoveDeparturesBeforeChosenTime();
            break;
          }
          case 10: {
            System.out.println("Exiting the Train Dispatch Application.");
            exit = true;
            break;
          }
          default:
            System.out.println("The number: " + choice + " is not a valid option. Please try "
                + "again.");
            break;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid input. Please try again:");
      }
    }
  }
  /**
   * This method is used to verify the train number input from the user.
   * This method was made to avoid duplicate code in several of the methods in this class.
   * The method checks if the input is valid, and throws an {@link InputMismatchException} if it
   * is not. The method also checks if the input is negative.
   *
   * @return The train number input from the user, if correct.
   * @throws InputMismatchException If the input is not an integer.
   */

  private int verifyInputOfTrainNumber() throws InputMismatchException {
    int trainNumber = 0;
    boolean correctInput = false;
    while (!correctInput) {
      try {
        trainNumber = scanner.nextInt();
        while (trainNumber < 0) {
          System.out.println("The train number cannot be negative.");
          trainNumber = scanner.nextInt();
        }
        correctInput = true;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input for train number. Please try again.");
        scanner.nextLine();
      }
    }
    return trainNumber;
  }

  /**
   * This method is used to verify the input of the delay. The method checks if the input is valid,
   * and throws an {@link InputMismatchException} if it is not. For the delay input to be valid,
   * it has to be between 0 and 59 minutes.
   *
   * @return The delay input from the user, if correct.
   * @throws InputMismatchException If the input is not an integer.
   */
  private int verifyInputDelay() throws InputMismatchException {
    int delay = 0;
    boolean correctDelay = false;
    while (!correctDelay) {
      try {
        delay = scanner.nextInt();
        while (delay < 0) {
          System.out.println("Invalid delay. Please try again. \nType 0 if there is no delay.");
          delay = scanner.nextInt();
        }
        correctDelay = true;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input for delay. Please try again.");
        scanner.nextLine();
      }
    }
    return delay;
  }

  /**
   * This method is used to communicate with the user before registering a new departure.
   * The method asks the user to enter the departure time, line, train number, destination, and
   * possible track and delay. As well as checking if the departure already exists in the departure
   * register.<p>The user will be presented with a message saying if the train is registered or not.
   * The method also checks the user input to make sure it is valid with the
   * {@code verifyInputOfTrainNumber()} method.</p>
   */

  private void uiNewDeparture() {
    System.out.println("What is the train number? ");
    int trainNumber = verifyInputOfTrainNumber();
    if (depReg.checkIfDepartureExists(trainNumber, "", null)) {
      System.out.println("This train is already registered.");
      System.out.println(depReg.showListOfDepartures(depReg.getAllDepartures()));

    } else {
      System.out.println("When is the departure leaving? (HH:MM)");
      scanner.nextLine();
      String departureTime = scanner.nextLine();
      boolean validInput = false;
      while (!validInput) {
        try {
          time.verifyInputOfTime(departureTime, "departure time");
          time.inputIsAfterCurrentTime(departureTime);
          validInput = true;
        } catch (DateTimeException e) {
          System.out.println("Please enter the time in the correct format (HH:MM).");
          departureTime = scanner.nextLine();
        }
      }
      System.out.println("Which line is the departure going? (example: L1)");
      String line = scanner.nextLine();
      while (line == null || line.isEmpty()) {
        System.out.println("Please enter a line.");
        line = scanner.nextLine();
      }
      if (depReg.checkIfDepartureExists(trainNumber, line, LocalTime.parse(departureTime))) {
        System.out.println("This train is already registered.");
        System.out.println(depReg.showListOfDepartures(depReg.getAllDepartures()));

      } else {
        System.out.println("What is the destination for this train departure? ");
        String destination = scanner.nextLine();
        while (destination == null || destination.isEmpty()) {
          System.out.println("Please enter a destination.");
          destination = scanner.nextLine();
        }
        System.out.println("What track is the departure going from?\n"
            + "Type 0 if you don't know yet.");
        int track = 0;
        boolean correctTrack = false;
        while (!correctTrack) {
          try {
            track = scanner.nextInt();
            while (track < 0) {
              System.out.println("Enter a valid track. If you don't know the track yet, type 0");
              track = scanner.nextInt();
            }
            correctTrack = true;
          } catch (InputMismatchException e) {
            System.out.println("Invalid input for track. Please try again.");
            scanner.nextLine();
          }
        }
        System.out.println("""
                Does the departure have any delay?\s
                If yes: Type the amount of minutes
                If no: Type 0.""");
        int delay = 0;
        boolean correctDelay = false;
        while (!correctDelay) {
          try {
            delay = scanner.nextInt();
            while (delay < 0 || delay > 59) {
              System.out.println("Invalid delay. Please try again. \nType 0 if there is no delay.");
              delay = scanner.nextInt();
            }
            correctDelay = true;
          } catch (InputMismatchException e) {
            System.out.println("Invalid input for delay. Please try again.");
            scanner.nextLine();
          }
        }
        try {
          depReg.newDeparture(departureTime, line, trainNumber, destination, track, delay);
          System.out.println("The departure has been registered:");
          System.out.println(depReg.showListOfDepartures(depReg.getAllDepartures()));
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid input for the departure. Please try again.");
        }
      }
    }
  }
  /**
   * This method communicates with the user about which track to set to a train departure.
   * The user is asked to enter the train number of the departure, and the track number.
   * The method verifies the input of the train number with the {@code verifyInputOfTrainNumber()}
   * method, and then checks if the train number exists. If it does, the track is set to the
   * departure with {@code setTrackToDeparture()} in the {@link DepartureRegister}.
   * If the train number does not exist, the user is asked to enter a valid train number.
   */

  private void uiSetTrack() {
    System.out.println("What is the train number for the departure?");
    int trainNumber = verifyInputOfTrainNumber();
    if (depReg.checkIfDepartureExists(trainNumber, "", null)) {
      System.out.println("Which track would you like to register for this departure? If you don't "
          + "know yet, type 0.");
      int track = scanner.nextInt();
      while (track < 0) {
        System.out.println("Please enter a valid track.");
        track = scanner.nextInt();
      }
      try {
        depReg.setTrackToDeparture(trainNumber, track);
        System.out.println("The track has been registered.");
        System.out.println(depReg.showListOfDepartures(depReg.getAllDepartures()));
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid input for the track. Please try again.");
        uiSetTrack();
      }
    } else {
      System.out.println("This train number does not exist. Please try again.\n");
      uiSetTrack();
    }
  }

  /**
   * This method is used to ask the user for what delay to set to a departure. First it asks for
   * the train number and verify it with the {@code verifyInputOfTrainNumber()} method.
   * It will search for the departure by using the {@code checkIfDepartureExists()} method,
   * if it exists, it will call the {@code setDelayToDeparture()} from the
   * {@link DepartureRegister}. If the departure does not exist, the user is asked to enter
   * another valid train number.
   */
  private void uiSetDelay() {
    System.out.println("What is the train number for the departure?");
    int trainNumber = verifyInputOfTrainNumber();
    if (depReg.checkIfDepartureExists(trainNumber, "", null)) {
      System.out.println("How much delay would you like to register for this departure? (minutes)");

      int delay = verifyInputDelay();
      boolean delayIsCorrect = false;
      while (!delayIsCorrect) {
        if (delay > 59) {
          System.out.println("""
              For delays over 59 minutes, please register a new departure time instead.\s
              You can find this option in the main menu.\s
              Do you want to set a new departure time instead?\s
              Type 1 for yes, or 2 for no.""");
          int choice = 0;
          boolean correctChoice = false;
          while (!correctChoice) {
            try {
              choice = scanner.nextInt();
              while (choice != 1 && choice != 2) {
                System.out.println("Please choose one of the options: 1 (yes) or 2 (no).");
                choice = scanner.nextInt();
              }
              correctChoice = true;
            } catch (InputMismatchException e) {
              System.out.println("Invalid input. Please try again.");
            }
          }
          if (choice == 1) {
            System.out.println("You chose to set a new departure time.");
            uiSetNewDepartureTime();
            break;
          } else {
            System.out.println("You chose to set a delay after all.\n"
                + "How much delay would you like to register for this departure?");
            delay = verifyInputDelay();
            while (delay > 59) {
              System.out.println("The delay cannot be over 59 minutes. Please try again.");
              delay = verifyInputDelay();
            }
          }
        } else {
          delayIsCorrect = true;
        }
      }
      if (delayIsCorrect) {
        try {
          depReg.setDelayToDeparture(trainNumber, delay);
          System.out.println("The delay has been registered.");
          System.out.println(depReg.showListOfDepartures(depReg.getAllDepartures()));
        } catch (IllegalArgumentException e) {
          System.out.println("Please try again.");
          uiSetDelay();
        }
      }
    } else {
      System.out.println("The train number does not exist. Please try again.");
      uiSetDelay();
    }
  }

  /**
   * This method is used to ask the user for a new departure time. The method will ask for the
   * wanted train number, and verify it with the {@code verifyInputOfTrainNumber()} method.
   * It will then call the {@code checkIfDepartureExists()} method from the
   * {@link DepartureRegister} to check if the departure exists. If it does, the user is asked to
   * enter a new departure time, and the method will call {@code setNewDepartureTime()} from
   * the {@link DepartureRegister}. If the departure does not exist, the user is asked to enter a
   * new train number.
   */
  private void uiSetNewDepartureTime() {
    System.out.println("What is the train number for the departure?");
    int trainNumber = verifyInputOfTrainNumber();
    if (depReg.checkIfDepartureExists(trainNumber, "", null)) {
      System.out.println("What is the new departure time? (HH:MM)");
      scanner.nextLine();
      String newDepartureTime = scanner.nextLine();
      boolean validInput = false;
      while (!validInput) {
        try {
          time.inputIsAfterCurrentTime(newDepartureTime);
          time.verifyInputOfTime(newDepartureTime, "new departure time");
          validInput = true;
        } catch (DateTimeException e) {
          System.out.println("Please enter the time in the correct format (HH:MM).\n"
              + "Your current time is: " + time.getCurrentTime() + ". \nYour chosen time cannot be"
              + " earlier than this. Please try again.");
          newDepartureTime = scanner.nextLine();
        }
      }
      depReg.setNewDepartureTime(trainNumber, newDepartureTime);
      System.out.println("The new departure time has been registered.");
      System.out.println(depReg.showListOfDepartures(depReg.getAllDepartures()));

    } else {
      System.out.println("The train number does not exist. Please try again.");
      uiSetNewDepartureTime();
    }
  }

  /**
   * This method is used to only show departures leaving after a chosen time.
   * The user is asked to enter a time, then the method will check the input if it is correct.
   * And then the method will call the {@code removeDeparturesBeforeChosenTime()} method from the
   * {@link DepartureRegister}.
   */
  private void uiRemoveDeparturesBeforeChosenTime() {
    System.out.println("Please specify the time for your preferred earliest departure: ");
    String chosenTime = scanner.nextLine();
    boolean validInput = false;
    while (!validInput) {
      try {
        time.verifyInputOfTime(chosenTime, "chosen time");
        time.inputIsAfterCurrentTime(chosenTime);
        validInput = true;
      } catch (DateTimeException e) {
        System.out.println("Please enter the time in the correct format (HH:MM).\n"
            + "Your current time is: " + time.getCurrentTime() + ". \nYour chosen time cannot be"
            + " earlier than this. Please try again.");
        chosenTime = scanner.nextLine();
      }
    }
    System.out.println(depReg.removeDeparturesBeforeChosenTime(LocalTime.parse(chosenTime)));
  }

  /**
   * This method asks the user to enter a train number, and then asks to enter a track to set for
   * the departure with that train number. <p>The method will use the
   * {@code verifyInputOfTrainNumber()} method to check if the input is valid before it checks
   * if the departure exists with {@code checkIfDepartureExists()} from the
   * {@link DepartureRegister}.</p>
   * If the departure exists, the track is set to the departure with the
   * {@code setTrackToDeparture()}. If the departure does not exist, the user is asked to enter
   * a valid train number.
   */
  private void uiSearchByTrainNumber() {
    System.out.println("What is the train number for the departure?");
    int trainNumber = verifyInputOfTrainNumber();
    if (depReg.checkIfDepartureExists(trainNumber, "", null)) {
      System.out.println(depReg.searchByTrainNumber(trainNumber));
    } else {
      System.out.println("The train number does not exist. Please try again.");
      uiSearchByTrainNumber();
    }
  }

  /**
   * This method is used to search for departures by destination. The user is asked to enter a
   * wanted destination, then it checks if the destination exists with
   * {@code checkIfDestinationExists()} from the {@link DepartureRegister}. If it exists, it will
   * call {@code searchByDestination()} also from the {@link DepartureRegister}.
   * If the destination does not exist, the user is asked to enter a valid destination.
   */
  private void uiSearchByDestination() {
    System.out.println("What is the destination you would like to search for?");
    String destination = scanner.nextLine();
    while (destination == null || destination.isEmpty()) {
      System.out.println("Please enter a destination.");
      destination = scanner.nextLine();
    }
    if (depReg.checkIfDestinationExists(destination)) {
      System.out.println(depReg.searchByDestination(destination));
    } else {
      System.out.println("The destination does not exist. Please try again.");
      uiSearchByDestination();
    }
  }

  /**
   * This method is used to update the time. The user will select what the current time is, and
   * the time will be updated in the {@link Time} class. The input will be verified if
   * it is after the past set current time, and if it is in the correct format. Then the time will
   * be updated with the {@code setCurrentTime()} method from the {@link Time} class.
   */
  private void uiSetNewCurrentTime() {
    System.out.println("What is the current time? (HH:MM)");
    String newTime = scanner.nextLine();
    try {
      time.inputIsAfterCurrentTime(newTime);
      time.setCurrentTime(newTime);
      System.out.println("The time has been updated to: " + time.getCurrentTime());
    } catch (DateTimeException e) {
      System.out.println("Please enter a valid time in the format: (HH:MM)\n"
          + "Your current time is: " + time.getCurrentTime() + ". \nYour chosen time cannot be"
          + " earlier than this. Please try again.");
      uiSetNewCurrentTime();
    }
  }
}