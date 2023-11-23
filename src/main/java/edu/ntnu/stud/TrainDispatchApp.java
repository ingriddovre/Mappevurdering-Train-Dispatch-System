package edu.ntnu.stud;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.

  // trenger ikke nødvendigvis teste get metoder, disse har ingen logiske ting, og ingen throws
 // todo: se over all javadoc, sjekk om du kan bruke HTML linker for å linke til spesifike metoder
//      du har referert til i javadocer for andre metoder

  /**
   * This is the main method for the train dispatch application.

   * @param args lll
   */
  public static void main(String[] args) {
    UserInterface ui = new UserInterface();
    ui.start();
    ui.init();
  }
}
