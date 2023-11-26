package edu.ntnu.stud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.DateTimeException;
import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {
  @Nested
  @DisplayName("Positive tests for Time class methods")
  class positiveTestsForTimeClassMethods {
    @Test
    @DisplayName("Positive test for setting time between 00:00 and 23:59")
    void setCurrentTime() {
      try {
        Time time = new Time();
        time.setCurrentTime("00:00");
      } catch (DateTimeException e) {
        fail("Test failed, should not thrown exception.");
      }
    }
    @Test
    @DisplayName("Positive test for getting current time")
    void shouldReturnValidTime() {
      try {
        Time time = new Time();
        time.setCurrentTime("12:00");
        assertEquals("12:00", time.getCurrentTime().toString());
      } catch (DateTimeException e) {
        fail("Test failed, should not have thrown exception.");
      }
    }

    @Test
    @DisplayName("Positive test for verifying input of time")
    void shouldNotThrowExceptionWhenTimeIs0000() {
      try {
        Time time = new Time();
        time.verifyInputOfTime("00:00", "test");
      } catch (DateTimeException e) {
        fail("Test failed, should not thrown exception.");
      }
    }
    @Test
    @DisplayName("Positive test for verifying input of time")
    void shouldNotThrowExceptionWhenTimeIs2359() {
      try {
        Time time = new Time();
        time.verifyInputOfTime("23:59", "test");
      } catch (DateTimeException e) {
        fail("Test failed, should not thrown exception.");
      }
    }
  }

  @Nested
  @DisplayName("Negative tests for setting time")
  class negativeTestsForSettingTime {
    @Test
    @DisplayName("Negative test for setting time before 00:00")
    void shouldThrowExceptionWhenTimeBefore0000() {
      DateTimeException thrown = assertThrows(
        DateTimeException.class, () -> {
          Time time = new Time();
          time.setCurrentTime("-1:00");
        }
      );
    }
    @Test
    @DisplayName("Negative test for setting time after 23:59")
    void shouldThrowExceptionWhenTimeIsAfter2359() {
      DateTimeException thrown = assertThrows(
        DateTimeException.class, () -> {
          Time time = new Time();
          time.setCurrentTime("24:00");
        }
      );
    }
    @Test
    @DisplayName("Negative test for verifying input of time after 23:59.")
    void shouldThrowDateTimeExcWhenTimeIsNotReal() {
      DateTimeException thrown = assertThrows(
        DateTimeException.class, () -> {
          Time time = new Time();
          time.verifyInputOfTime("24:00", "test");
        }
      );
    }
    @Test
    @DisplayName("Negative test for verifying input of time before 00:00.")
      void shouldThrowDateTimeExcWhenTimeIsNotReal2() {
      DateTimeException thrown = assertThrows(
        DateTimeException.class, () -> {
          Time time = new Time();
          time.verifyInputOfTime("01:60", "test");
        }
      );
    }
  }
}
