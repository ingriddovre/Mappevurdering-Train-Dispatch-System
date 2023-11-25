package edu.ntnu.stud;
import java.time.DateTimeException;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

public class TrainDepartureTest {
  @Nested
  @DisplayName("Positive tests for the TrainDeparture class constructor and set methods.")
  public class positiveTrainDepartureTests {
    @Test
    @DisplayName("Departure time in constructor should return 13:00 + 15 min delay = 13:15.")
    void departureTimeShouldReturn1315() {
      try {
        TrainDeparture train2 = new TrainDeparture("13:00", "L2", 2, "Trondheim", 3, 15);
        assertEquals(LocalTime.of(13, 15), train2.getDepartureTime());
      } catch (DateTimeException | IllegalArgumentException e) {
        fail("Should not throw exception" + e.getMessage());
      }
    }

    @Test
    @DisplayName("Positive test to set delay to 15 minutes.")
    void shouldSetDepartureTimeTo1315() {
      try {
        TrainDeparture train2 = new TrainDeparture("13:00", "L2", 2, "Trondheim", 2, 0);
        train2.setDelay(15);
        assertEquals(LocalTime.of(13, 15), train2.getDepartureTime());
      } catch (IllegalArgumentException e) {
        fail("Should not throw exception" + e.getMessage());
      }
    }

    @Test
    @DisplayName("Positive test for train number in constructor.")
    void shouldReturnPositiveTrainNumber() {
      try {
        TrainDeparture train2 = new TrainDeparture("13:00", "L2", 2, "Trondheim", 2, 0);
        assertEquals(2, train2.getTrainNumber());
      } catch (IllegalArgumentException e) {
        fail("Should not throw exception" + e.getMessage());
      }
    }

    @Test
    @DisplayName("Positive test for getting -1 track value when set to 0.")
    void shouldReturnNegativeOneTrack() {
      try {
        TrainDeparture train5 = new TrainDeparture("12:00", "L2", 2, "Trondheim", 3, 0);
        train5.setTrack(0);
        assertEquals(-1, train5.getTrack());
      } catch (IllegalArgumentException e) {
        fail("Should not throw exception" + e.getMessage());
      }
    }
  }

  @Nested
  @DisplayName("Negative tests for the TrainDeparture class constructor and set methods.")
  public class negativeDepartureTests {
    @Test
    @DisplayName("Negative test for departure time before 00:00.")
    void throwsDateTimeExceptionBefore0000() {
      String departureTime = "-1:00";
      DateTimeException thrown = assertThrows(
              DateTimeException.class, () -> new TrainDeparture(departureTime, "L1", 1,
                      "Valdres", 1, 0),
              "Expected to throw DateTimeException, but didn't."
      );
    }

    @Test
    @DisplayName("Negative test for departure time after 23:59.")
    void throwsDateTimeExceptionAfter2359() {
      String departureTime = "24:00";
      DateTimeException thrown = assertThrows(
              DateTimeException.class, () -> new TrainDeparture(departureTime, "L2", 2,
                      "Lillehammer", 2, 0), "Expected to throw DateTimeException, but didn't."
      );
    }

    @Test
    @DisplayName("Negative test for delay less than 0 in the constructor.")
    void throwsIllArgExceptionDelayLessThan0() {
      IllegalArgumentException thrown = assertThrows(
              IllegalArgumentException.class, () -> new TrainDeparture("14:00", "L3", 3,
                      "Oslo", 3, -1), "Expected to throw Ill.Arg.Exc. but didn't."
      );
    }

    @Test
    @DisplayName("Negative test to set a negative delay value.")
    void shouldThrowIllArgExceptionForSetNegativeDelay() {
      TrainDeparture train3 = new TrainDeparture("12:00", "L2", 2, "Trondheim", 3, 0);
      IllegalArgumentException thrown = assertThrows(
              IllegalArgumentException.class, () -> train3.setDelay(-1),
              "Expected to throw Ill.Arg.Exc. but didn't."
      );
    }

    @Test
    @DisplayName("Negative test for Line in the constructor.")
    void testConstructorLine() {
      NullPointerException thrown = assertThrows(
              NullPointerException.class, () -> new TrainDeparture("13:00", null, 2,
                      "Trondheim", 3, 15),
              "Expected NullPointerException to throw, but it didn't");
    }

    @Test
    @DisplayName(("Negative test for null destination in constructor."))
    void shouldThrowNullPointExcForNoDestination() {
      NullPointerException thrown = assertThrows(
              NullPointerException.class, () -> new TrainDeparture("13:00", "L2", 2,
                      null, 2, 0),
              "Expected NullPointerException to throw, but it didn't");
    }

    @Test
    @DisplayName("Negative test for initializing negative track value in constructor.")
    void shouldThrowIllArgExcForNegativeValues() {
      IllegalArgumentException thrown = assertThrows(
              IllegalArgumentException.class, () -> new TrainDeparture("10:00", "L4", 4, "Tromsø", 4, -1),
              "Expected to throw Ill.Arg.Exception but didn't.");
    }

    @Test
    @DisplayName("Negative test for setting negative track value.")
    void shouldThrowExceptionForSetNegativeTrack() {
      TrainDeparture train4 = new TrainDeparture("12:00", "L2", 2, "Trondheim", 3, 0);
      IllegalArgumentException thrown = assertThrows(
              IllegalArgumentException.class, () -> train4.setTrack(-1),
              "Expected to throw Ill.Arg.Exc. but didn't."
      );
    }

    @Test
    @DisplayName("Positive test for getting -1 track when initialised as 0 in constructor.")
    void shouldReturnMinusOneTrack() {
      try {
        TrainDeparture train5 = new TrainDeparture("12:00", "L5", 5, "Trondheim", 0, 0);
        assertEquals(-1, train5.getTrack());
      } catch (IllegalArgumentException e) {
        fail("should not throw exception" + e.getMessage());
      }
    }

    @Test
    void testToString1() {
    }
  }

  @Nested
  @DisplayName("Positive tests for verify methods.")
  public class positiveVerifyMethodTests {
    @Test
    @DisplayName("Positive test for verify Departure time method.")
    void shouldNotThrowExceptionFor0000() {
// skal jeg lage tester for denne metoden at all siden jeg egt heller burde teste den i Time klassen
    }

    @Test
    @DisplayName("Positive test for verify Departure time method.")
    void shouldNotThrowExceptionFor2359() {

    }

    @Test
    @DisplayName("Positive test for verify String method.")
    void shouldNotThrowExceptionForStringNotNull() {

    }

    @Test
    @DisplayName("Positive test for verify Integer method.")
    void shouldNotThrowExceptionForPositiveInteger() {

    }

    @Test
    @DisplayName("Positive test for verify delay method between 0 and 59")
    void shouldNotThrowExcForPosDelayLessThan60() {
      // skal jeg teste dise, blir det ikke d samme som å teste de samme metodene igjen
    }
  }

  @Nested
  @DisplayName("Negative tests for verify methods.")
  public class negativeVerifyMethodTests {
    @Test
    @DisplayName("Negative test for verify Departure time less than 00:00.")
    void shouldThrowExceptionForLessThan0000() {

    }

    @Test
    @DisplayName("Negative test for verify Departure time more than 23:59.")
    void shouldThrowDateTimeExcForMoreThan2359() {

    }
  }
}
