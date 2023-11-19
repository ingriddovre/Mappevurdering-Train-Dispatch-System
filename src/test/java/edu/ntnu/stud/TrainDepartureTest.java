package edu.ntnu.stud;
import java.time.DateTimeException;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainDepartureTest {


  //TrainDeparture train3 = new TrainDeparture("14:00", "L3", -2, "Bergen", 4, 0);
  //TrainDeparture train4 = new TrainDeparture("15:00", "L4", 4, null, 5, 5);
  //TrainDeparture train5 = new TrainDeparture("15:00", "L4", 4, "Stavanger", 0, 5);
  //TrainDeparture train6 = new TrainDeparture("15:00", "L4", 4, "Stavanger", 5, -5);

  @Test
  void departureTimeShouldReturn1pm() {
    TrainDeparture train2 = new TrainDeparture("13:00", "L2", 2, "Trondheim", 3, 15);
    train2.getDepartureTime();
  }
@Test // todo: hvorfor thrower den nullpointer og ikke bare datetime exception men ok
  void assertThrowsDateTimeExceptionAfter2359() {
    TrainDeparture train1 = new TrainDeparture("25:00", "L1", 1, "Oslo", 2, 0);
    assertThrows(DateTimeException.class, train1::getDepartureTime);
  }


  @Test
  void testSetDelay() {
  }

  @Test
  void testGetDelay() {
  }

  @Test
  void testGetLine() {
  }

  @Test
  void testGetTrainNumber() {
  }

  @Test
  void testGetDestination() {
  }

  @Test
  void testGetTrack() {
  }

  @Test
  void testSetTrack() {
  }

  @Test
  void testToString1() {
  }
}
