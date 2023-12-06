package edu.ntnu.stud;

/**
 * This is the main class for the Train Dispatch Application.
 * <p>Goal: to start the application.</p>
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
