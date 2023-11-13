package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
/**
 * This class represents one specific train departure. This is a Parent class, and it is used by
 * the DepartureRegister class. A train departure has a departure time, a line, a train number,
 * a destination, a track and possibly a delay. The class has a constructor, and several accessor
 * and mutator methods and a toString method.
 *
 * @version 4.11.2023-12.11.2023
 * @author Ingrid Midtmoen Døvre
 */

public class TrainDeparture {
  private int departureTime;
  private final String line;
  private final int trainNumber;
  private final String destination;
  private int track;
  private int delay;
  /**
   * This is the constructor for the TrainDeparture class. It initializes an object of the type
   * TrainDeparture. The constructor takes the following parameters:


   * @param departureTime The time of departure. The time is given as an Integer in the format HHMM,
   *                     it is not set as final because it can be changed if there is delay.
   *                     The time can only contain Natural(N) numbers, therefore it is given as an
   *                     Integer. For example 08:30 is given as 0830.
   *                     In the user-interface the time is displayed as hh:mm. If a departure is set
   *                     to 08:30, it will disappear from the user-interface after 08:30,
   *                     also after the delay has passed. This depends on what current time is given
   *                     from the user.

   * @param line The line the train will go. Several trains can go on the same line, but obviously
   *            not at the same time. The line is given as a final String in the format "L1", "L2",
   *            "L3" and so on. And it will be displayed accordingly in the user-interface.
   *            This attribute is set as a String because it contains both letters and numbers,
   *            and will not be needed for further mathematical calculations.

   * @param trainNumber The train number is a unique number for each train. The train number is
   *                    given as an Integer. The number is unique within a 24-hour period.
   *                    The train number is set as a final Integer because it will not be changed,
   *                    and the number will only contain Natural(N) numbers.

   * @param destination The final destination for the train. The destination is given as a String,
   *                    and is displayed accordingly. Setting the destinations attribute as a final
   *                    String is most appropriate because it is only a word. For example a train
   *                    departure from Trondheim to Oslo, has Oslo as its destination,
   *                    and this will not change.

   * @param track The track the train will depart from. The track is given as an Integer because it
   *             is a one-digit number. It is not set as a final attribute because not all trains
   *             have a track, in these cases the track is set to -1, and it can be changed, or set,
   *             later with a mutator method.

   * @param delay The delay for the departure. The delay is given as an Integer, since the delay
   *             time only contains Natural(N) numbers. The delay is usually not longer than a few
   *             minutes, therefore it is given in the format M (how many minutes), not HHMM,
   *             like the departure time. If there is no delay, it will not be displayed, and it is
   *             set to 0000. If there is a delay, it will be displayed as hh:mm in the
   *             user-interface. Not all trains have a delay, or the delay will come later,
   *             therefore it is not a final Integer, and it can be changed, or set, later with a
   *             mutator method.

   * @throws DateTimeException Is thrown if the departure time is not between 00:00 and 23:59.
   * @throws IllegalArgumentException if the line is empty, the train number is less than 0,
   *        the track is less than -1 or if the delay is not between 0 and 59.
   * @throws NullPointerException if the destination is empty.
   */
  public TrainDeparture(int departureTime, String line, int trainNumber, String destination,
                        int track, int delay) {
    if (departureTime < 0 || departureTime > 2359) {
      throw new DateTimeException("Invalid departure time: " + departureTime
          + ". Departure time must be between 0000 and 2359.");
    }
    this.departureTime = departureTime;

    if (line == null || line.isBlank()) {
      throw new NullPointerException("The line cannot be empty.");
    }
    this.line = line;

    if (trainNumber < 0) {
      throw new IllegalArgumentException("The train number cannot be negative.");
    }
    this.trainNumber = trainNumber;

    if (destination == null || destination.isBlank()) {
      throw new NullPointerException("Please enter a destination.");
    }
    this.destination = destination;

    if (track < -1 || track == 0) {
      throw new IllegalArgumentException("Wrong input of track: " + track
          + ". To register no track, type -1.");
    }
    this.track = track;

    if (delay < 0 || delay > 59) {
      throw new IllegalArgumentException("The delay must be between 0 and 59.");
    }
    this.delay = delay;
  }

  /**
   * This method is used to get the departure time. The departure time is given as an Integer in
   * the format HHMM. The method returns an object of the type LocalTime. If the departure has a
   * delay,the delay is added to the departure time. Therefore, the departure time is not final.
   * It uses the LocalTime class from the java.time. package to summarize the departure time and the
   * delay correctly. For example, if the departure time is 11:55, and the delay is 10 minutes,
   * the departure time will be 1205, and not 1165.

   * @return summarized departure time and delay in HHMM format.
   * @throws DateTimeException if the departure time is not between 0000 and 2359.
   */
  public LocalTime getDepartureTime() {
    if (departureTime < 0 || departureTime > 2359) {
      throw new DateTimeException("Invalid departure time: " + departureTime
          + ". Departure time must be between 0000 and 2359.");
    }
    LocalTime timeCalculation = LocalTime.of(departureTime / 100, departureTime % 100);
    if (delay > 0) {
      timeCalculation = timeCalculation.plusMinutes(delay);
    }
    return timeCalculation;
  }

  /**
   * This method is used to set the delay.
   * If there is no delay, the delay is set to 0.
   * The delay is given as an Integer as a count of minutes after the departure.

   * @param delay Amount of minutes after the departure time.
   * @throws IllegalArgumentException if the delay is not between 0 and 59.
   */
  public void setDelay(int delay) {
    if (delay < 0 || delay > 59) {
      throw new IllegalArgumentException("The delay must be between 0 and 59.");
    }
    this.delay = delay;
  }

  /**
   * This method is used to get the delay.
   * The delay is given as an Integer, and it is the count of minutes after the departure time.
   * If there is no delay, it is set to 0, and is not displayed in the user-interface.

   * @return The delay in amount of minutes.
   * @throws IllegalArgumentException if the delay is not between 0 and 59.
   */
  public int getDelay() {
    if (delay < 0 || delay > 59) {
      throw new IllegalArgumentException("The delay must be between 0 and 59.");
    }
    return delay;
  }

  /**
   * This method is used to get the line.
   * The line is given as a String in the format "L1", "L2", "L3" and so on.

   * @return A String representation of the line.
   * @throws NullPointerException if the line is empty.
   */
  public String getLine() {
    if (line == null || line.isBlank()) {
      throw new NullPointerException("The line cannot be empty.");
    }
    return line;
  }

  /**
   * This method is used to get the train number.
   * The train number is given as an Integer, and is unique within a 24-hour period.

   * @return The unique train number for the departure.
   * @throws IllegalArgumentException if the train number is less than 0.
   */
  public int getTrainNumber() {
    try {
      if (trainNumber < 0) {
        throw new IllegalArgumentException();
      }
      return trainNumber;
    } catch (Exception e) {
      throw new IllegalArgumentException("The train number cannot be negative.");
    }
  }

  /**
   * This method is used to get the destination.
   * The destination is given as a String. It is decided by the final destination of the departure.

   * @return The final destination of the departure.
   * @throws NullPointerException if the destination is empty.
   */
  public String getDestination() {
    if (destination == null || destination.isBlank()) {
      throw new NullPointerException("Please enter a destination.");
    }
    return destination;
  }

  /**
   * This method is used to get the track.
   * Not all trains have a track, in these cases the track is set to -1.
   * The track is given as an Integer.

   * @return The number that represents the track the train will depart from.
   * @throws IllegalArgumentException if the track is less than -1.
   */
  public int getTrack() {
    if (track < -1) {
      throw new IllegalArgumentException("Wrong input of track: " + track
          + ". To register no track, type -1.");
    }
    return track;
  }

  /**
   * This method is used to set the track of the departure.
   * The track is given as an Integer. For no track, the track is set to -1.

   * @param track The track the train will depart from.
   * @throws IllegalArgumentException if the track is less than -1.
   */
  public void setTrack(int track) {
    if (track < -1 || track == 0) {
      throw new IllegalArgumentException("Wrong input of track: " + track
          + ". To register no track, type -1.");
    }
    this.track = track;
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
            + "Departure time: " + getDepartureTime()
            + ", Line " + line
            + ", trainNumber: " + trainNumber
            + ", destination: " + destination
            + ", track " + track + ","
            + " " + delay + " min delay";
  }
}