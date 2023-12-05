package edu.ntnu.stud;
import java.time.DateTimeException;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class is testing the {@link TrainDeparture} class. The test class has both positive and negative
 * tests for the methods. The purpose of the class is to test the constructor and the set methods in the
 * {@code TrainDeparture} class. The tests mainly focuses on the input validation of the methods and in the constructor.
 * <p>The class has been created with help from ChatGPT for inspiration about which scenarios to test.</p>
 *
 * @version 1.0
 */
public class TrainDepartureTest {
  @Nested 
  @DisplayName("Positive tests for the TrainDeparture class constructor and set methods.")
  public class positiveTrainDepartureTests {
    @Test
    @DisplayName("Departure time in constructor should return 13:00 + 15 min delay = 13:15.")
    void departureTimeShouldReturn1315() {
      try {
        TrainDeparture train2 = new TrainDeparture("13:00", "L2", 2, "Trondheim",
                3, 15);
        assertEquals(LocalTime.of(13, 15), train2.getDepartureTime());
      } catch (DateTimeException | IllegalArgumentException e) {
        fail("Should not throw exception" + e.getMessage());
      }
    }
    @Test
    @DisplayName("Positive test to set new departure time with set method.")
    void shouldSetNewDepartureTimeTo1400() {
      try {
        Time time = new Time();
        time.setCurrentTime("09:00");
        TrainDeparture train6 = new TrainDeparture("10:00", "L6", 6, "Bodø",
                6, 10);
        train6.setNewDepartureTime("14:00");
      } catch (DateTimeException e) {
        fail("Should not throw exception" + e.getMessage());
      }
    }
    @Test
    @DisplayName("Positive test to initialise delay in constructor.")
    void shouldReturnPositiveDelay() {
      try {
        TrainDeparture train2 = new TrainDeparture("13:00", "L2", 2, "Trondheim",
                3, 59);
      } catch (IllegalArgumentException e) {
        fail("Should not throw exception" + e.getMessage());
      }
    }
    @Test
    @DisplayName("Positive test to set delay to 15 minutes.")
    void shouldSetDepartureTimeTo1315() {
      try {
        TrainDeparture train2 = new TrainDeparture("13:00", "L2", 2, "Trondheim",
                2, 0);
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
        TrainDeparture train2 = new TrainDeparture("13:00", "L2", 2, "Trondheim",
                2, 0);
        assertEquals(2, train2.getTrainNumber());
      } catch (IllegalArgumentException e) {
        fail("Should not throw exception" + e.getMessage());
      }
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
    @DisplayName("Positive test for getting -1 track value when set to 0.")
    void shouldReturnNegativeOneTrack() {
      try {
        TrainDeparture train5 = new TrainDeparture("12:00", "L2", 2, "Trondheim",
                3, 0);
        train5.setTrack(0);
        assertEquals(-1, train5.getTrack());
      } catch (IllegalArgumentException e) {
        fail("Should not throw exception" + e.getMessage());
      }
    }
    @Test
    @DisplayName("Positive test to set new track value with set method.")
    void shouldSetNewTrackValueTo5() {
      try {
        TrainDeparture train6 = new TrainDeparture("12:00", "L6", 6, "Bodø",
                6, 0);
        train6.setTrack(5);
      } catch (IllegalArgumentException e) {
        fail("Should not throw exception" + e.getMessage());
      }
    }
  }

  @Nested
  @DisplayName("Negative tests for the TrainDeparture class constructor and set methods.")
  public class negativeDepartureTests {
    @Test
    @DisplayName("Negative test for departure time before 00:00 in constructor.")
    void throwsDateTimeExceptionBefore0000() {
      String departureTime = "-1:00";
      DateTimeException thrown = assertThrows(
              DateTimeException.class, () -> new TrainDeparture(departureTime, "L1", 1,
                      "Valdres", 1, 0),
              "Expected to throw DateTimeException, but didn't."
      );
    }

    @Test
    @DisplayName("Negative test for departure time after 23:59 in constructor.")
    void throwsDateTimeExceptionAfter2359() {
      String departureTime = "24:00";
      DateTimeException thrown = assertThrows(
              DateTimeException.class, () -> new TrainDeparture(departureTime, "L2", 2,
                      "Lillehammer", 2, 0), "Expected to throw DateTimeException, but didn't."
      );
    }

    @Test
    @DisplayName("Negative test for set new departure time before current time.")
    void throwsDateTimeExceptionBeforeCurrentTime() {
      Time time = new Time();
      time.setCurrentTime("09:00");
      TrainDeparture train1 = new TrainDeparture("10:00", "L1", 1, "Trondheim", 1, 0);
      DateTimeException thrown = assertThrows(
          DateTimeException.class, () -> train1.setNewDepartureTime("08:00"), "Should throw DateTimeException, but didn't."
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
    @DisplayName("Negative test for delay more than 59 in the constructor.")
    void shouldThrowIllArgExcForDelayMoreThan59() {
      IllegalArgumentException thrown = assertThrows(
              IllegalArgumentException.class, () -> new TrainDeparture("14:00", "L3", 3,
                      "Oslo", 3, 60), "Expected to throw Ill.Arg.Exc. but didn't."
      );
    }

    @Test
    @DisplayName("Negative test for null Line in the constructor.")
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
    void shouldThrowIllArgExcForNegativeTrackValues() {
      IllegalArgumentException thrown = assertThrows(
              IllegalArgumentException.class, () -> new TrainDeparture("10:00", "L4", 4, "Tromsø", -4, 0),
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
    @DisplayName("Negative test for set method with negative delay.")
    void shouldThrowIllArgExceptionForSetNegativeDelay() {
      TrainDeparture train3 = new TrainDeparture("12:00", "L2", 2, "Trondheim",
              3, 0);
      IllegalArgumentException thrown = assertThrows(
              IllegalArgumentException.class, () -> train3.setDelay(-1),
              "Expected to throw Ill.Arg.Exc. but didn't."
      );
    }
    @Test
    @DisplayName("Negative test for set method with delay more than 59.")
    void shouldThrowExcForSetDelayTo60() {
      TrainDeparture train3 = new TrainDeparture("12:00", "L2", 2, "Trondheim",
              3, 0);
      IllegalArgumentException thrown = assertThrows(
              IllegalArgumentException.class, () -> train3.setDelay(60),
              "Expected to throw Ill.Arg.Exc. but didn't."
      );
    }
    @Test
    void testToString1() {
      TrainDeparture train1 = new TrainDeparture("12:00", "L1", 1, "Trondheim",
              1, 0);
      assertEquals("Train departure: 12:00, L1, train number: 1, Trondheim, track: 1, 0 min delay", train1.toString());
    }
  }
}
