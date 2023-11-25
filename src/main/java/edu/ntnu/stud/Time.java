package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;

/**
 * This class represents the Time for the register.
 * Goal: To get better cohesion in the system, so the classes have a more specific purpose.

 * @since 0.5
 * @author Ingrid Midtmoen Døvre
 * @version 0.2
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
   * throws an exception if it is null, or not in the correct format to be parsed.

   * @param exampleTime The time input from the user in the format HH:MM.
   * @param parameterName The name of the parameter that is being checked.
   * @throws DateTimeException If the exampleTime is null, not in the correct format or not
   *      between 00:00 and 23:59.
   */
  public void verifyInputOfTime(String exampleTime, String parameterName) throws DateTimeException {
    LocalTime inputTime = LocalTime.parse(exampleTime);
    if (inputTime.isBefore(LocalTime.parse("00:00"))
        || inputTime.isAfter(LocalTime.parse("23:59")) || exampleTime.isEmpty()) {
      throw new DateTimeException("Invalid input: " + exampleTime + " for the " + parameterName);
    }
  }
}
