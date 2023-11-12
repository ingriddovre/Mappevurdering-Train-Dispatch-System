package edu.ntnu.stud;
/**
 * This class is the user-interface for the train dispatch application.
 *
 */
import java.time.LocalTime;
import java.util.Scanner;

/**
 * This class is the user-interface for the train dispatch application.
 *
 */
public class UserInterface {

  /**
   * This method is used to test the TrainDeparture class.
   */
  public void start() {
    TrainDeparture train1 = new TrainDeparture(1200, "L1", 1, "Oslo", 2, 0);
    TrainDeparture train2 = new TrainDeparture(1300, "L2", 2, "Trondheim", 3, 15);
    TrainDeparture train3 = new TrainDeparture(1400, "L3", 3, "Bergen", 4, 0);
    TrainDeparture train4 = new TrainDeparture(1500, "L4", 4, "Stavanger", 5, 5);
    System.out.println(train1.toString());
    System.out.println(train2.toString());
    System.out.println(train3.toString());
    System.out.println(train4.toString());
  }

  /**
   * This method is used to initialize the user-interface.
   * The user is asked to register the current time, and is then presented with a menu of options.
   * The user can choose to register a new departure, remove a departure, set track to a departure, set delay to a departure,
   * search for departures by train number and destination, update the time, show all departures, or exit the application.
   * The user is asked to choose an option until the user chooses to exit the application.
   * This method is used in the main method in the TrainDispatchApp class.

   * @see TrainDispatchApp The Train Dispatch Application class.
   *
   */
  public void init() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the train dispatch application.");
    System.out.println("First, register you current time (hhmm): ");
    int currentTime = scanner.nextInt();
    LocalTime time = LocalTime.of(currentTime / 100, currentTime % 100);
    System.out.println("You set the current time to be: " + time);
    System.out.println("""
      Please choose one of the following options:
      1. Register a new departure.
      2. Remove a departure.
      3. Set track to a departure.
      4. Set delay to a departure.
      5. Search for departures by train number.
      6. Search for departures by destination.
      7. Update the time.
      8. Show all departures.
      9. Exit.""");
  }



  /**
  int choice = scanner.nextInt();
    switch (choice) {
    case 1: {
      System.out.println("When is the departure? (hhmm) ");
      int departureTime = scanner.nextInt();
      System.out.println("Which line is the departure going? ");
      String line = scanner.nextLine();

      //check if the departure already exists.
      DepartureRegister.checkIfDepartureExists(departureTime, line);
      while (DepartureRegister.checkIfDepartureExists(departureTime, line) == 1) {
        System.out.println("This departure already exists. \n Please register a new departure time and/or line.");
        choice = 1;
      }

      System.out.println("What is the train number? "); //skjønner ikke helt greia med train number men det skal være unikt for et 24 timers tidsrom
      int trainNumber = scanner.nextInt();
      // må muligens ha en sjekk her for å se om trainNumber allerede eksisterer eller om det har gått 24 timer aner ikke

      System.out.println("What is the destination for this train departure? ");
      String destination = scanner.nextLine();
      System.out.println("What track is the departure going from? \nType 0 if you don't know yet.");
      int track = scanner.nextInt();
      System.out.println("Does the departure have any delay? \nType the delay (hhmm) if yes, or 0 if no. ");
      int delay = scanner.nextInt();

      DepartureRegister.newDeparture(departureTime, line, trainNumber, destination, track, delay);
      if (DepartureRegister.newDeparture(departureTime, line, trainNumber, destination, track, delay) == 1) {
        System.out.println("The departure has been registered.");
      }
      break;
    }
    case 2: {
      //kode
      break;
    }
  }
  */

}
