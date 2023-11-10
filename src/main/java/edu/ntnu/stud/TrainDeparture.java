package edu.ntnu.stud;
/**
 * This class represents one specific train departure.
 * A train departure has a departure time, a line, a train number, a destination, a track and possibly a delay.
 *
 * The constructor takes the following parameters, and initializes an object of the type TrainDeparture.
 * @param departureTime The time of departure. The time is given as an Integer in the format HHMM.
 *                      For example 08:30 is given as 0830. In the user-interface the time is displayed as hh:mm.
 *                      If a departure is set to 08:30, it will disappear from the user-interface after 08:30, and also after the delay has passed.
 *                      This depends on what current time is given from the user.
 * @param line The line the train will "drive". Several trains can go on the same line, but obviously not at the same time.
 *             The line is given as a String in the format "L1", "L2", "L3" and so on. And it will be displayed accordingly in the user-interface.
 * @param trainNumber The train number is a unique number for each train. The train number is given as an Integer.
 *                    The number is unique within a 24-hour period.
 * @param destination The final destination for the train. The destination is given as a String, and is displayed accordingly.
 *                    For example a train departure from Trondheim to Oslo, has Oslo as its destination.
 * @param track The track the train will depart from. The track is given as an Integer. Not all trains have a track,
 *             in these cases the track is set to -1.
 * @param delay The possible delay for the departure. The delay is given as an Integer in the format HHMM.
 *              If there is no delay, it will not be displayed, and it is set to 0000. If there is a delay, it will be displayed as hh:mm.
 * @version 4.11.2023-
 * @author Ingrid Midtmoen Døvre
 * hva mener han med rolle/ansvar?
 *
 */

public class TrainDeparture {
    private final int departureTime;
    private final String line;
    private final int trainNumber;
    private final String destination;
    private int track;
    private int delay;

    public TrainDeparture(int departureTime, String line, int trainNumber, String destination, int track, int delay) {
        this.departureTime = departureTime;
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.track = track;
        this.delay = delay;
    }

    /**
     * This method is used to get the departure time.
     * The departure time is given as an Integer in the format hhmm.
     * This method returns the time in the format hhmm, not as displayed in the user-interface, hh:mm.
     * @return departureTime
     */
    public int getDepartureTime() {
        return departureTime;
    }

    /**
     * This method is used to get the line.
     * The line is given as a String in the format "L1", "L2", "L3" and so on.
     * @return line
     */
    public String getLine() {
        return line;
    }

    /**
     * This method is used to get the train number.
     * The train number is given as an Integer, and is unique within a 24-hour period.
     * @return trainNumber
     */
    public int getTrainNumber() {
        return trainNumber;
    }

    /**
     * This method is used to get the destination.
     * The destination is given as a String.
     * @return destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * This method is used to get the track.
     * The track is given as an Integer.
     * @return track
     */
    public int getTrack() {
        return track;
    }

    /**
     * This method is used to get the delay.
     * The delay is given as an Integer in the format hhmm.
     * If there is no delay, the delay is set to 0000, and is not displayed in the user-interface.
     * @return delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * This method is used to set the track. Not all trains have a track, in these cases the track is set to -1.
     * The track can be set later, with a mutator method.
     * The track is given as an Integer.
     * @param track
     */
    public void setTrack(int track) {
        this.track = track;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }
    public String toString() {
        return "Train departure{"
                + "departureTime = " + departureTime
                + ", line = '" + line + '\''
                + ", trainNumber = " + trainNumber
                + ", destination = '" + destination + '\''
                + ", track = " + track
                + ", delay = " + delay
                + '}';
    }
}
