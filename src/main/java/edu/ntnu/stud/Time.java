package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;

/**
 * This class handles the time for the register.
 * <p> The {@code current time} is set by the user, not by the system time. This class handles
 * the verification of time input from the user, and throws {@link DateTimeException} if it is
 * wrong.</p>
 * Goal: To get better cohesion in the system, so the classes get more specific purposes.
 *
 * @see LocalTime
 * @since 0.5
 * @author Ingrid Midtmoen Døvre
 * @version 0.4
 */

public class Time {
  /**
   * The {@code current time } of the system, chosen by the user, but can not be set backwards in
   * time. It is set to static because we only want one time for the whole system.
   */
  private static LocalTime currentTime;

  /**
   * This is the constructor for the {@link Time} class. The constructor is used to initialize the
   * class. It is empty because the time is not set in the constructor, but by a separate method.
   * The purpose of the constructor is to initialize the class.
   */
  public Time() {
  }

  /**
   * This method is used to access the {@code current time}.
   *
   * @return a {@link LocalTime} object of the {@code current time} selected by the user.
   */
  public LocalTime getCurrentTime() {
    return currentTime;
  }

  /**
   * This method is used to update the {@code current time}. The method is called from the
   * {@link UserInterface} class. It calls {@code verifyInputOfTime} to check if the input is valid
   * before setting it as the new {@code current time}.
   *
   * @param time The chosen time for the system.
   * @throws DateTimeException if the time is invalid or in the wrong format.
   */
  public void setCurrentTime(String time) throws DateTimeException {
    verifyInputOfTime(time, "current time");
    currentTime = LocalTime.parse(time);
  }

  /**
   * This method is used to verify the input of the time. It checks if the input is valid, and
   * throws a {@link DateTimeException} if it is empty, or not in the correct format to be parsed.
   * <p>The {@link LocalTime} parse method already throws a {@code DateTimeParseException }
   * if the input is invalid, so we do not need to check if the input is in the correct format
   * separately afterwords. The {@code DateTimeParseException} is a subclass of
   * {@link DateTimeException}, so we don't need to catch it separately.</p>
   *
   * @param exampleTime The time input from the user in the format HH:MM.
   * @param parameterName The name of the parameter that is being checked.
   * @throws DateTimeException If the exampleTime is {@code null}, not in the correct format and
   *      therefore cannot be parsed, or not between 00:00 and 23:59.
   */
  public void verifyInputOfTime(String exampleTime, String parameterName) throws DateTimeException {
    if (LocalTime.parse(exampleTime).isBefore(LocalTime.parse("00:00"))
        || LocalTime.parse(exampleTime).isAfter(LocalTime.parse("23:59"))
        || exampleTime.isEmpty()) {
      throw new DateTimeException("Invalid input: " + exampleTime + " for the " + parameterName);
    }
  }

  /**
   * This method is used to verify if the input of the departure time is after the
   * {@code current time}. This method is needed to check the departure time inputs
   * outside the {@code verifyInputOfTime()} method, because we also need to check if the input of
   * the {@code current time} is correct.
   * <p>If we use the verify method to check if the input is before the {@code current time}, we
   * will get a {@link NullPointerException} because the {@code current time} is not set yet.</p>
   *
   * @param exampleTime The time input from the user in the format HH:MM.
   * @throws DateTimeException If the {@code exampleTime} is before the set {@code current time}.
   */
  public void inputIsAfterCurrentTime(String exampleTime) throws DateTimeException {
    if (LocalTime.parse(exampleTime).isBefore(currentTime)) {
      throw new DateTimeException("Invalid input: " + exampleTime + " for the time");
    }
  }
}
