package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import org.junit.jupiter.api.Test;
// teste ferdig alle metoder i TrainDeparture.java
public class TrainDepartureTest {
  @Test
    public void testTrainDeparture() {
      TrainDeparture train1 = new TrainDeparture(1200, "", 1, "Oslo", 2, 0);
      TrainDeparture train2 = new TrainDeparture(2500, "L2", 2, "Trondheim", 3, 15);
      TrainDeparture train3 = new TrainDeparture(1400, "L3", -3, "Bergen", 4, 0);
      TrainDeparture train4 = new TrainDeparture(1500, "L4", 4, null, 5, 5);
      TrainDeparture train5 = new TrainDeparture(1500, "L5", 5, "Stavanger", -6, 5);
      TrainDeparture train6 = new TrainDeparture(1500, "L6", 6, "Tromsø", 6, -5);
      TrainDeparture train7 = new TrainDeparture(1500, "L7", 7, "Tromsø", 7, 5);
      TrainDeparture train8 = new TrainDeparture(1500, "L8", 8, "Tromsø", 8, 5);

      assertThrows(IllegalArgumentException.class, () -> {
        train7.setTrack(-2);
      });
      assertThrows(IllegalArgumentException.class, () -> {
        train8.setDelay(-4);
      });
      assertThrows(DateTimeException.class, train2::getDepartureTime);
      assertThrows(NullPointerException.class, train4::getDestination);
      assertThrows(IllegalArgumentException.class, train3::getTrainNumber);
      assertThrows(NullPointerException.class, train1::getLine);
      assertThrows(IllegalArgumentException.class, train5::getTrack);
      assertThrows(IllegalArgumentException.class, train6::getDelay);
  }
}
