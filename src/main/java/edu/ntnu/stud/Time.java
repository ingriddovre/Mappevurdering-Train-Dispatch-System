package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * This class represents the Time for the register.
 * Goal: To get better cohesion in the system, so the classes have a more specific purpose.

 * @since 0.5
 * @author Ingrid Midtmoen Døvre
 * @version 0.3
 */

public class Time {
  private static LocalTime currentTime;

  /**
   * This is the constructor for the Time class. The constructor is used to initialize the Time
   * class. The constructor is empty because the time is set by the user, not by the system time.

   * @see LocalTime
   */
  public Time() {
  }

  /**
   * This method is used to get the current time. This method gets the set time from the user, not
   * the system time.

   * @return a LocalTime object of the current time selected by the user.
   */
  public LocalTime getCurrentTime() {
    return currentTime;
  }

  /**
   * This method is used to update the time. The time is updated every minute, and the method
   * is called from the UserInterface class.

   * @param newTime The new chosen time.
   * @throws DateTimeException if the time is invalid.
   */
  public void setCurrentTime(String newTime) {
    verifyInputOfTime(newTime, "new time");
    currentTime = LocalTime.parse(newTime);
  }

  /**
   * This method is used to verify the input of the time. It checks if the input is valid, and
   * throws an exception if it is empty, or not in the correct format to be parsed. <p>The LocalTime
   * parse method already throws a DateTimeParseException if the input is invalid, so we do not
   * need to check if the input is in the correct format separately afterwords.
   * The DateTimeParseException is a subclass of DateTimeException, so we don't need to catch it
   * separately.</p>

   * @param exampleTime The time input from the user in the format HH:MM.
   * @param parameterName The name of the parameter that is being checked.
   * @throws DateTimeException If the exampleTime is null, not in the correct format and
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
   * This method is used to verify the input of the departure time. It checks if the input of time
   * is before the current set time of the system. This method is needed to check the departure time
   * inputs, outside the verifyInputOfTime method, because we also need to check if the input of
   * current time is correct.
   * <p>If we use the verify method to check if the input is before the current time, we wil get a
   * Null Pointer Exception because the current time is not set yet.</p>

   * @param exampleTime The time input from the user in the format HH:MM.
   * @throws DateTimeException If the exampleTime is before the current set time.
   */
  public void inputIsAfterCurrentTime(String exampleTime) throws DateTimeException {
    if (LocalTime.parse(exampleTime).isBefore(currentTime)) {
      throw new DateTimeException("Invalid input: " + exampleTime + " for the time");
    }
  }
}
