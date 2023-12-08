package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * This class represents one specific {@code Train Departure}. The class is used by the
 * {@link DepartureRegister}, therefore the {@link DepartureRegister} is a composition of the
 * {@link TrainDeparture} class. A train departure has a {@code departure time}, a {@code line}, a
 * {@code train number}, a {@code destination}, a {@code track} and possibly a {@code delay}.
 * The class has a constructor, and several accessor and mutator methods and a toString method.
 * The constructor initializes new objects of the {@link TrainDeparture} class.
 * <p>Goal: To represent a specific train departure.</p>
 *
 * @since 0.1
 * @author Ingrid Midtmoen Døvre
 * @version 1.0
 */

public class TrainDeparture {
  /**
   * The time the train will depart.
   */
  private LocalTime departureTime;
  /**
   * The line the train will go.
   */
  private final String line;
  /**
   * The train number is a unique number for each train.
   */
  private final int trainNumber;
  /**
   * The final destination for the train.
   */
  private final String destination;
  /**
   * The track the train will depart from.
   */
  private int track;
  /**
   * The delay for the departure.
   */
  private int delay;
  /**
   * This is the constructor for the {@link TrainDeparture} class. It initializes an object of
   * {@link TrainDeparture}. The constructor takes the following parameters:
   *
   * @param departureTime The time of departure. The time is a {@link LocalTime} object in the
   *                     format HH:MM, it is not set as final because it can be changed if there is
   *                     delay. If a departure is set to 08:30 it will disappear from the
   *                    {@link UserInterface} after 08:30, also after the delay has passed.
   *                     <p>This depends on what current time is given from the user.
   *                     The parameter is set as a {@link LocalTime} object so that it can be
   *                     calculated correctly, in example: 11:55 + 10 minutes = 12:05, not 11:65.
   *                     </p>
   * @param line The line the train will go. Several trains can go on the same {@code line}, but
   *            obviously not at the same time. The line is given as a final String in the format
   *            "L1", "L2", "L3" and so on. It will be displayed accordingly in the
   *            {@link UserInterface}.<p>This parameter is set as a String because it contains both
   *            letters and numbers,and will not be needed for further mathematical calculations.
   *             </p>
   * @param trainNumber The {@code train number} is a unique number for each train. The train number
   *                    is given as an Integer. The number is unique within a 24-hour period.<p>
   *                    The {@code train number} is set as a final Integer because it will not be
   *                    changed, and the number will only contain Natural(N) numbers.</p>
   * @param destination The final {@code destination} for the train. The {@code destination} is
   *                    given as a String, and is displayed accordingly.<p>Setting the parameter as
   *                    a final String is most appropriate because it is a word. For example, a
   *                    train departure from Trondheim to Oslo, has Oslo as its final
   *                    {@code destination}, and this will not change.</p>
   * @param track The {@code track} the train will depart from. The {@code track} is given as an
   *             Integer because it is a one-digit number. <p>It is not set as a final parameter
   *             because not all trains have a {@code track}, in these cases the track is set to -1,
   *             and it can be changed, or set, later with a mutator method.</p>
   * @param delay The {@code delay} for the departure. The {@code delay} is given as an Integer,
   *             since the {@code delay} time only contains Natural(N) numbers. The delay is
   *             usually not longer than a few minutes, therefore it is given in the format M
   *             (how many minutes), not HH:MM,like the {@code departure time}. If there is no
   *             {@code delay}, it will not be displayed. If there is a {@code delay}, it will
   *             <p>Not all trains have a delay, or the delay will come later, therefore it is
   *             not a final Integer, and it can be changed, or set, later with a mutator method.
   *             </p> The {@code delay} will be displayed as HH:MM in the {@link UserInterface}
   *             within the {@code departure time}.
   */

  public TrainDeparture(String departureTime, String line, int trainNumber, String destination,
                        int track, int delay) {
    verifyInputLocalTime(departureTime);
    verifyInputString(line, "line");
    verifyInputIntegers(trainNumber, "trainNumber");
    verifyInputString(destination, "destination");
    verifyInputIntegers(track, "track");
    verifyInputDelay(delay);

    this.departureTime = LocalTime.parse(departureTime);
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = delay;
  }

  /**
   * This method is used to verify input from the user of values that are supposed to be parsed into
   * {@link LocalTime} objects. These Strings cannot be {@code null} or empty, and they have to be
   * in the format HH:MM. The method initializes the {@link Time} class and calls the
   * {@code verifyInputOfTime} method to verify the input.
   *
   * @param exampleTime a String variable that is supposed to be verified before being parsed to an
   *                    {@link LocalTime} object. The String is supposed to be in the format HH:MM.
   * @throws DateTimeException If the String cannot be parsed to a {@link LocalTime} object or
   *          is {@code null}, or if the String is before 00:00 or after 23:59.
   */
  private void verifyInputLocalTime(String exampleTime) throws DateTimeException {
    Time time = new Time();
    time.verifyInputOfTime(exampleTime, "departure time");
  }

  /**
   * This method is another method that can verify {@link LocalTime} objects. The difference is that
   * this method also checks if the input is after the current time. This method is used to verify
   * the new departure time, and not the original departure time.
   *
   * @param exampleTime a String variable that is supposed to be verified before being parsed to an
   *                    {@link LocalTime} object. The String is supposed to be in the format HH:MM.
   * @throws DateTimeException If the String cannot be parsed to a {@link LocalTime} object, or if
   *                    the input is before 00:00, after 23:59, or before the current time.
   */
  private void verifyNewDepartureTime(String exampleTime) throws DateTimeException {
    Time time = new Time();
    time.inputIsAfterCurrentTime(exampleTime);
    time.verifyInputOfTime(exampleTime, "new departure time");
  }

  /**
   * This method is used to verify the input from the user of values not supposed to be {@code null}
   * or empty. This method is used to verify the {@code line} and the {@code destination}.
   *
   * @param exampleString a String variable that is supposed to be verified.
   * @param parameterName the name of the parameter that is supposed to be verified.
   * @throws NullPointerException if the String is empty or {@code null}.
   */
  private void verifyInputString(String exampleString, String parameterName) throws
      NullPointerException {
    if (exampleString == null || exampleString.isBlank()) {
      throw new NullPointerException("Invalid string: " + exampleString + " for " + parameterName);
    }
  }

  /**
   * This method is used to verify the {@code delay}. It can obviously not be negative, and it
   * should not be more than 59 minutes. If the delay is more than 59 minutes, it is more useful to
   * set a new {@code departure time} instead. This can be done with {@code setNewDepartureTime()}.
   *
   * @param exampleDelay an Integer variable that is supposed to be verified.
   * @throws IllegalArgumentException if the delay is not between 0 and 59.
   */
  private void verifyInputDelay(int exampleDelay) throws IllegalArgumentException {
    if (exampleDelay < 0 || exampleDelay > 59) {
      throw new IllegalArgumentException("Invalid delay time: " + exampleDelay + " for delay");
    }
  }

  /**
   * This method is used to verify the input from the user of values not supposed to be less than 0.
   * It is used to verify the {@code train number}, and the {@code track}.
   *
   * @param exampleInteger an Integer variable that is supposed to be verified.
   * @param parameterName the name of the parameter that is supposed to be verified.
   * @throws IllegalArgumentException if the Integer is less than 0.
   */
  private void verifyInputIntegers(int exampleInteger, String parameterName) throws
      IllegalArgumentException {
    if (exampleInteger < 0) {
      throw new IllegalArgumentException("Invalid integer: " + exampleInteger + " for "
          + parameterName);
    }
  }

  /**
   * This method is used to get the {@code departure time}. The {@code departure time} is
   * a {@link LocalTime} object, therefore the method returns an object of the type
   * {@link LocalTime}. If the departure has a {@code delay}, it is added to the
   * {@code departure time}. <p>It uses the {@link LocalTime} class from the java.time.
   * package to summarize the {@code departure time} and the {@code delay} correctly.
   * For example, if the departure time is 11:55, and the delay is 10
   * minutes, the departure time will be 12:05, and not 11:65.</p>
   *
   * @return summarized {@link LocalTime} object of {@code departure time} and {@code delay}.
   */
  public LocalTime getDepartureTime() {
    return departureTime.plusMinutes(delay);
  }

  /**
   * This method is used to set a new {@code departure time} for a departure. The method is
   * useful if a departure has more than 59 min of delay. The method takes in a String parameter
   * of the new {@code departure time} in the format HH:MM. It then calls the
   * {@code verifyNewDepartureTime()} method to verify the input, and then parses the String to
   * a {@link LocalTime} object before setting the new time. It will also set the {@code delay}
   * to 0, since the {@code departure time} is changed.
   *
   * @param newDepartureTime The new {@code departure time} in the format HH:MM.
   * @throws DateTimeParseException if the String cannot be parsed to a {@link LocalTime} object.
   */
  public void setNewDepartureTime(String newDepartureTime) throws DateTimeException {
    verifyNewDepartureTime(newDepartureTime);
    this.departureTime = LocalTime.parse(newDepartureTime);
    setDelay(0);
  }

  /**
   * This method is used to set the {@code delay}. If there is no {@code delay}, it is set to 0.
   * The {@code delay} is given as an Integer as a count of minutes after the
   * {@code departure time}.
   *
   * @param exampleDelay Amount of minutes after the {@code departure time}.
   * @throws IllegalArgumentException if the delay is not between 0 and 59.
   */
  public void setDelay(int exampleDelay) throws IllegalArgumentException {
    verifyInputDelay(exampleDelay);
    this.delay = exampleDelay;
  }

  /**
   * This method is used to get the {@code line}.
   * The {@code line} is given as a String in the format "L1", "L2", "L3" and so on.
   *
   * @return A String representation of the {@code line}.
   */
  public String getLine() {
    return line;
  }

  /**
   * This method is used to get the {@code train number}.
   * The {@code train number} is given as an Integer, and is unique within a 24-hour period.
   *
   * @return The unique {@code train number} for the specific departure.
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * This method is used to get the {@code destination}.
   * The {@code destination} is given as a String. It is decided by the final
   * {@code destination} of the departure.
   *
   * @return The final {@code destination} of the departure.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * This method is used to get the {@code track}.
   * Not all trains have a {@code track}, in these cases the {@code track} is set to -1.
   * The {@code track} is given as an Integer.
   *
   * @return The number that represents the {@code track} the train will depart from.
   */
  public int getTrack() {
    if (track == 0) {
      return -1;
    } else {
      return track;
    }
  }

  /**
   * This method is used to set the {@code track} of the departure.
   * The {@code track} is given as an Integer. For no {@code track}, it is set to -1.
   *
   * @param exampleTrack The {@code track} the train will depart from.
   * @throws IllegalArgumentException if the {@code track} is less than -1.
   */
  public void setTrack(int exampleTrack) throws IllegalArgumentException {
    verifyInputIntegers(exampleTrack, "track");
    if (exampleTrack == 0) {
      this.track = -1;
    } else {
      this.track = exampleTrack;
    }
  }

  /**
   * This is a toString method used to display a specific {@link TrainDeparture} object.
   * The returned String includes the {@code departure time}, the {@code line}, the
   * {@code train number}, the {@code destination}, the {@code track} and the {@code delay}.
   * <p>The method returns a String in the format:
   * Train departure: HH:MM, LN, train number: N, destination, track: N, N min delay</p>
   *
   * @return A String representation of the {@link TrainDeparture} object.
   */
  @Override
  public String toString() {
    return "Train departure: "
        + getDepartureTime()
        + ", " + line
        + ", train number: " + trainNumber
        + ", " + destination
        + ", track: " + track + ", "
        + delay + " min delay";
  }
}