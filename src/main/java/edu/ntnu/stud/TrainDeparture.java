package edu.ntnu.stud;
/**
 * Klassen representerer in togavgang. (Train Departure).
 * @version 4.11.2023-1-01 (første versjon i git?)
 * @author Ingrid Midtmoen Døvre
 * @throws ......dette finner vi ut av etterhvert
 * hva mener han med rolle/ansvar?
 *
 * Konstruktøren oppretter et objekt av typen TrainDeparture. ENGELSK!!!
 * @param departureTime
 * @param line
 * @param trainNumber
 * @param destination
 * @param track
 * @param delay
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
    public int getDepartureTime() {
        return departureTime;
    }
    public String getLine() {
        return line;
    }
    public int getTrainNumber() {
        return trainNumber;
    }
    public String getDestination() {
        return destination;
    }
    public int getTrack() {
        return track;
    }
    public int getDelay() {
        return delay;
    }
    public void setTrack(int track) {
        this.track = track;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }
    public String toString() {
        return "edu.ntnu.stud.TrainDeparture{" +
                "departureTime=" + departureTime +
                ", line='" + line + '\'' +
                ", trainNumber=" + trainNumber +
                ", destination='" + destination + '\'' +
                ", track=" + track +
                ", delay=" + delay +
                '}';
    }
}
