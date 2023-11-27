package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * This class represents one specific train departure. This is a Parent class, and it is used by
 * the DepartureRegister class. A train departure has a departure time, a line, a train number,
 * a destination, a track and possibly a delay. The class has a constructor, and several accessor
 * and mutator methods and a toString method.
 *
 * <p>Goal: To create a class that represents a train departure.

 * @author Ingrid Midtmoen Døvre
 * @version 1.4
 * @since 0.1
 */

public class TrainDeparture {
  private LocalTime departureTime;
  private final String line;
  private final int trainNumber;
  private final String destination;
  private int track;
  private int delay;
  /**
   * This is the constructor for the TrainDeparture class. It initializes an object of the type
   * TrainDeparture. The constructor takes the following parameters:

   * @param departureTime The time of departure. The time is a String in the format HH:MM,
   *                     it is not set as final because it can be changed if there is delay.
   *                     <p>If a departure is set to 08:30 it will disappear from the user interface
   *                     after 08:30, also after the delay has passed. This depends on what current
   *                     time is given from the user. The datatype is set to String to use the
   *                     LocalTime class from the java.time. package.</p>
   * @param line The line the train will go. Several trains can go on the same line, but obviously
   *            not at the same time. The line is given as a final String in the format "L1", "L2",
   *            "L3" and so on. It will be displayed accordingly in the user-interface.
   *            <p>This attribute is set as a String because it contains both letters and numbers,
   *            and will not be needed for further mathematical calculations.</p>
   * @param trainNumber The train number is a unique number for each train. The train number is
   *                    given as an Integer. The number is unique within a 24-hour period.<p>
   *                    The train number is set as a final Integer because it will not be changed,
   *                    and the number will only contain Natural(N) numbers.</p>
   * @param destination The final destination for the train. The destination is given as a String,
   *                    and is displayed accordingly.<p>Setting the destinations attribute as a
   *                    final String is most appropriate because it is only a word. For example, a
   *                    train departure from Trondheim to Oslo, has Oslo as its final destination,
   *                    and this will not change.</p>
   * @param track The track the train will depart from. The track is given as an Integer because it
   *             is a one-digit number. <p>It is not set as a final attribute because not all trains
   *             have a track, in these cases the track is set to -1, and it can be changed, or set,
   *             later with a mutator method.</p>
   * @param delay The delay for the departure. The delay is given as an Integer, since the delay
   *             time only contains Natural(N) numbers. The delay is usually not longer than a few
   *             minutes, therefore it is given in the format M (how many minutes), not HH:MM,
   *             like the departure time. If there is no delay, it will not be displayed. If there
   *             is a delay, it will be displayed as HH:MM in the user-interface.
   *             <p>Not all trains have a delay, or the delay will come later, therefore it is
   *             not a final Integer, and it can be changed, or set, later with a
   *             mutator method.</p>
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
   * LocalTime objects. These Strings cannot be null or empty, and they have to be in the format
   * HH:MM. The method initializes the Time class and calls the verifyInputOfTime method to verify
   * the input.

   * @param exampleTime a String variable that is supposed to be verified before being parsed to an
   *                    LocalTime object. The String is supposed to be in the format HH:MM.
   * @throws DateTimeParseException if the String cannot be parsed to a LocalTime object or is null.
   * @throws DateTimeException if the String is before 00:00 or after 23:59.
   *
   */
  private void verifyInputLocalTime(String exampleTime) throws DateTimeException {
    Time time = new Time();
    time.verifyInputOfTime(exampleTime, "departure time");
  }
  /**
   * This method is used to verify the input from the user of values not supposed to be null or
   * empty. This method is used to verify the line and the destination.

   * @param exampleString a String variable that is supposed to be verified.
   * @param parameterName the name of the parameter that is supposed to be verified.
   * @throws NullPointerException if the String is empty or null.
   */
  private void verifyInputString(String exampleString, String parameterName) throws
      NullPointerException {
    if (exampleString == null || exampleString.isBlank()) {
      throw new NullPointerException("Invalid string: " + exampleString + " for " + parameterName);
    }
  }

  /**
   * This method is used to verify the delay value. The delay can obviously not be negative, and it
   * should not be more than 59 minutes. If the delay is more than 59 minutes, it is more useful to
   * set a new departure time instead.
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
   * This method is used to verify the train number, and the

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
   * This method is used to get the departure time. The departure time is given as a String
   * in the format HH:MM. The method returns an object of the type LocalTime. If the departure
   * has a delay, the delay is added to the departure time. Therefore, the departure time is not
   * final.<p>It uses the LocalTime class from the java.time. package to summarize the departure
   * time and the delay correctly. For example, if the departure time is 11:55, and the delay is 10
   * minutes, the departure time will be 12:05, and not 11:65.</p>

   * @return summarized departure time and delay in HH:MM format.
   */
  public LocalTime getDepartureTime() {
    return departureTime.plusMinutes(delay);
  }

  /**
   * This method is used to set a new departure time for a departure. This method is useful if
   * a departure has more than 59 min of delay. The method takes in a String parameter of the new
   * departure time in the format HH:MM. It then calls the verifyInputLocalTime method to verify the
   * input, and then parses the String to a LocalTime object.
   */
  public void setNewDepartureTime(String newDepartureTime) throws DateTimeException {
    verifyInputLocalTime(newDepartureTime);
    this.departureTime = LocalTime.parse(newDepartureTime);
  }

  /**
   * This method is used to set the delay.
   * If there is no delay, the delay is set to 0.
   * The delay is given as an Integer as a count of minutes after the departure.

   * @param exampleDelay Amount of minutes after the departure time.
   * @throws IllegalArgumentException if the delay is not between 0 and 59.
   */
  public void setDelay(int exampleDelay) throws IllegalArgumentException {
    verifyInputDelay(exampleDelay);
    this.delay = exampleDelay;
  }

  /**
   * This method is used to get the line.
   * The line is given as a String in the format "L1", "L2", "L3" and so on.

   * @return A String representation of the line.
   * @throws NullPointerException if the line is empty.
   */
  public String getLine() {
    return line;
  }

  /**
   * This method is used to get the train number.
   * The train number is given as an Integer, and is unique within a 24-hour period.

   * @return The unique train number for the departure.
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * This method is used to get the destination.
   * The destination is given as a String. It is decided by the final destination of the departure.

   * @return The final destination of the departure.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * This method is used to get the track.
   * Not all trains have a track, in these cases the track is set to -1.
   * The track is given as an Integer.

   * @return The number that represents the track the train will depart from.
   */
  public int getTrack() {
    if (track == 0) {
      return -1;
    } else {
      return track;
    }
  }

  /**
   * This method is used to set the track of the departure.
   * The track is given as an Integer. For no track, the track is set to -1.

   * @param exampleTrack The track the train will depart from.
   * @throws IllegalArgumentException if the track is less than -1.
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
   * This is a toString method used to display a specific train departure.
   * The returned String includes the departure time, the line, the train number,
   * the destination, the track and the delay.
   * The method returns a String in the format:
   * Train departure: Departure time: hh:mm, Line L1, trainNumber:
   * 1, destination: Oslo, track 1, 0-min delay.

   * @return A String representation of the TrainDeparture object.
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