package edu.ntnu.stud;

/**
 * This is the main class for the Train Dispatch Application.
 * <p>Goal: to start the application.</p>
 *
 * @since 0.1
 * @author Ingrid Midtmoen Døvre
 * @version 1.0
 */
public class TrainDispatchApp {
  /**
   * This is the main method for the train dispatch application. It initializes the
   * {@link UserInterface} and calls its {@code start} and {@code init} methods.
   */
  public static void main(String[] args) {
    UserInterface ui = new UserInterface();
    ui.start();
  }
}
