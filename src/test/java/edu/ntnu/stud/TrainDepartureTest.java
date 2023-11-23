package edu.ntnu.stud;
import java.time.DateTimeException;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

public class TrainDepartureTest {
  @Nested
  @DisplayName("Positive tests for the TrainDeparture class.")
  public class positiveTrainDepartureTests {
    @Test
    @DisplayName("Departure time in constructor should return 13:00 + 15 min delay = 13:15.")
    void departureTimeShouldReturn1315() {
      try {
        TrainDeparture train2 = new TrainDeparture("13:00", "L2", 2, "Trondheim", 3, 15);
        assertEquals(LocalTime.of(13, 15), train2.getDepartureTime());
      } catch (DateTimeException | NullPointerException | IllegalArgumentException e) {
        fail("Should not throw exception" + e.getMessage());
      }
    }
  }
  //TrainDeparture train3 = new TrainDeparture("14:00", "L3", -2, "Bergen", 4, 0);
  //TrainDeparture train4 = new TrainDeparture("15:00", "L4", 4, null, 5, 5);
  //TrainDeparture train5 = new TrainDeparture("15:00", "L4", 4, "Stavanger", 0, 5);
  //TrainDeparture train6 = new TrainDeparture("15:00", "L4", 4, "Stavanger", 5, -5);

  @Nested
  @DisplayName("Negative tests for the TrainDeparture class.")
  public class negativeDepartureTests {
    @Test
    @DisplayName("Negative test for departure time in the constructor. ")
    void throwsDateTimeExceptionBefore0000() {
      try {
        TrainDeparture train1 = new TrainDeparture("-1:00", "L1", 1, "Oslo", 2, 0);
        assertThrows(DateTimeException.class, train1::getDepartureTime);
        fail("Should throw exception, but did not. Test failed.");
      } catch (DateTimeException e) {
        assertEquals("Invalid departure time: -1:00. Departure time must be between 00:00 and 23:59, and in the format: HH:MM", e.getMessage());
      }
    }
  }

  @Test
  void testConstructor2() {
      NullPointerException thrown = assertThrows(
              NullPointerException.class, () -> new TrainDeparture("13:00", "", 2,
                      "Trondheim", 3, 15),
              "Expected NullPointerException to throw, but it didn't");
  }

@Test
void testConstructor() {
    try {
      new TrainDeparture("13:00", "", 2, "Trondheim", 3, 15);
        fail("Should throw exception, but did not. Test failed.");
    } catch (NullPointerException e) {
      assertEquals("Invalid string:  for line", e.getMessage());
    }
  }
  @Test
  void testSetDelay() {
  }

  @Test
  void testSetTrack() {
  }

  @Test
  void testToString1() {
  }
}
