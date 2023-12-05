package edu.ntnu.stud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.DateTimeException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class is testing the {@link Time} class. The test class has both positive and negative
 * tests for the methods. The purpose of the class is to test the constructor and the set methods in the
 * {@link Time} class. The tests mainly focuses on the input validation, and to throw correct exceptions.
 *
 * @version 1.0
 */

public class TimeTest {
  @Nested
  @DisplayName("Positive tests for Time class methods")
  class positiveTestsForTimeClassMethods {
    @Nested
    @DisplayName("Positive test for assuring extreme values are set correctly (00:00, 12:00, 23:59)")
    class positiveTestsForSetCurrentTimeMethod {
      @Test
      void setCurrentTimeTo0000() {
        try {
          Time time = new Time();
          time.setCurrentTime("00:00");
        } catch (DateTimeException e) {
          fail("Test failed, should not thrown exception.");
        }
      }
      @Test
      void setCurrentTimeTo1200() {
        try {
          Time time = new Time();
          time.setCurrentTime("12:00");
        } catch (DateTimeException e) {
          fail("Test failed, should not thrown exception.");
        }
      }
      @Test
      void setCurrentTimeTo2359() {
        try {
          Time time = new Time();
          time.setCurrentTime("23:59");
        } catch (DateTimeException e) {
          fail("Test failed, should not thrown exception.");
        }
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
    @DisplayName("Positive test for verifying input of earliest time 00:00")
    void shouldNotThrowExceptionWhenTimeIs0000() {
      try {
        Time time = new Time();
        time.verifyInputOfTime("00:00", "test");
      } catch (DateTimeException e) {
        fail("Test failed, should not thrown exception.");
      }
    }
    @Test
    @DisplayName("Positive test for verifying input of latest time 23:59.")
    void shouldNotThrowExceptionWhenTimeIs2359() {
      try {
        Time time = new Time();
        time.verifyInputOfTime("23:59", "test");
      } catch (DateTimeException e) {
        fail("Test failed, should not thrown exception.");
      }
    }
    @Test
    @DisplayName("Positive test for checking if input time is after current time.")
    void shouldNotThrowExceptionWhenInputIsAfterCurrentTime() {
      try {
        Time time = new Time();
        time.setCurrentTime("11:00");
        time.inputIsAfterCurrentTime("11:01");
      } catch (DateTimeException e) {
        fail("Test failed, should not thrown exception.");
      }
    }
  }

  @Nested
  @DisplayName("Negative tests for setting and verifying time inputs")
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
    void shouldThrowDateTimeExcWhenNotVerified() {
      DateTimeException thrown = assertThrows(
        DateTimeException.class, () -> {
          Time time = new Time();
          time.verifyInputOfTime("23:60", "test");
        }
      );
    }
    @Test
    @DisplayName("Negative test for verifying input of time before 00:00.")
      void shouldThrowDateTimeExcWhenTimeIsNotReal2() {
      DateTimeException thrown = assertThrows(
        DateTimeException.class, () -> {
          Time time = new Time();
          time.verifyInputOfTime("-1:00", "test");
        }
      );
    }
    @Test
    @DisplayName("Negative test for verifying input of time when time is empty.")
    void shouldThrowDateTimeExcWhenTimeIsEmpty() {
      DateTimeException thrown = assertThrows(
        DateTimeException.class, () -> {
          Time time = new Time();
          time.verifyInputOfTime("", "test");
        }
      );
    }
    @Test
    @DisplayName("Negative test for when input is non-numeric characters.")
    void shouldThrowExcWhenInputIsNonNumeric() {
      DateTimeException thrown = assertThrows(
          DateTimeException.class, () -> {
            Time time = new Time();
            time.verifyInputOfTime("aa:bb", "test");
          }
      );
    }
    @Test
    @DisplayName("Negative test for when input is non-numeric and numeric characters.")
    void shouldThrowExcWhenInputIsNonNumericAndNumeric() {
      DateTimeException thrown = assertThrows(
          DateTimeException.class, () -> {
            Time time = new Time();
            time.verifyInputOfTime("a1:b0", "test");
          }
      );
    }
    @Test
    @DisplayName("Negative test for when input is in H:mm.")
    void shouldThrowExcWhenInputIsHmm() {
      DateTimeException thrown = assertThrows(
          DateTimeException.class, () -> {
            Time time = new Time();
            time.verifyInputOfTime("1:00", "test");
          }
      );
    }
    @Test
    @DisplayName("Negative test for when input is in HH:m.")
    void shouldThrowExcWhenInputIsHHm() {
      DateTimeException thrown = assertThrows(
          DateTimeException.class, () -> {
            Time time = new Time();
            time.verifyInputOfTime("11:0", "test");
          }
      );
    }
    @Test
    @DisplayName("Negative test when input is HH-MM.")
    void shouldThrowExcWhenInputIsWithLine() {
      DateTimeException thrown = assertThrows(
          DateTimeException.class, () -> {
            Time time = new Time();
            time.verifyInputOfTime("11-00", "test");
          }
      );
    }

    @Nested
    @DisplayName("Negative test for when input is in HH:mm (am/pm).")
    class negativeVerifyMethodsForAmPmFormatInput {
      @Test
      @DisplayName("Negative test for when input is in Hpm.")
      void shouldThrowExcWhenInputIsHam() {
        DateTimeException thrown = assertThrows(
          DateTimeException.class, () -> {
            Time time = new Time();
            time.verifyInputOfTime("1am", "test");
          }
        );
      }
      @Test
      @DisplayName("Negative test for when input is in H:MMpm.")
      void shouldThrowExcWhenInputIsHMMpm() {
        DateTimeException thrown = assertThrows(
          DateTimeException.class, () -> {
            Time time = new Time();
            time.verifyInputOfTime("1:30pm", "test");
          }
        );
      }
    }
    @Test
    @DisplayName("Negative test for verifying when input time is before current time.")
    void shouldThrowDateTimeExcWhenInputIsBeforeCurrentTime() {
      DateTimeException thrown = assertThrows(
        DateTimeException.class, () -> {
          Time time = new Time();
          time.setCurrentTime("13:00");
          time.inputIsAfterCurrentTime("11:00");
        }
      );
    }
  }
}
