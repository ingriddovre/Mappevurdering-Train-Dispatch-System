package edu.ntnu.stud;

import java.util.Scanner;
public class UserInterface {
    public void start() {
        //Bruk start-metoden til å skrive enkel testkode for å teste at Train-Departure-klassen
        //fungerer iht spek ved f.eks. å opprette 3-4 instanser av klassen, og skrive ut objektene.
    }
    public void init() {
        //aner ikke hva dette liksom skal være
        //meny kanskje?
    }

    //jeg tror egt denne klassen ikke skal ha psvm at all, fordi d er applikasjonsklassen som har det...

    Scanner scanner = new Scanner(System.in);
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
            System.out.println("What track is the departure going from? \nType 0 if you don't know yet. ");
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
}
