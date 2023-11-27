package edu.ntnu.stud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.DateTimeException;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
public class DepartureRegisterTest {
  @Nested
  @DisplayName("Registering a new departure method tests.")
  class RegisterNewDeparture {
    @Nested
    @DisplayName("Positive tests for registering a new departure.")
    class PositiveTestsRegisterNewDeparture {
      @Test
      @DisplayName("Register a new departure and added to the allDepartures list")
      void shouldRegisterNewDepartureWithAllValidInputs() {
        try {
          DepartureRegister depReg = new DepartureRegister();
          depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 5);
          assertEquals(1, depReg.getAllDepartures().size());
        } catch (IllegalArgumentException e) {
          fail("Should not throw Illegal Argument Exception, test failed.");
        }
      }
    }
    @Nested
    @DisplayName("Negative tests for registering a new departure.")
    class NegativeTestsRegisterNewDeparture {
      @Test
      @DisplayName("Register new departure with invalid time.")
      void shouldNotRegisterNewDepartureWithInvalidTime() {
        DateTimeException thrown = assertThrows(
          DateTimeException.class, () -> {
            DepartureRegister depReg = new DepartureRegister();
            depReg.newDeparture("23:60", "L1", 1, "Oslo", 1, 5);
          }
        );
      }
      @Test
      @DisplayName("Register new departure method with empty line.")
      void shouldNotRegisterNewDepartureWithInvalidLine() {
        NullPointerException thrown = assertThrows(
          NullPointerException.class, () -> {
            DepartureRegister depReg = new DepartureRegister();
            depReg.newDeparture("12:00", "", 1, "Oslo", 1, 5);
          }
        );
      }
      @Test
      @DisplayName("Register new departure with invalid train number.")
      void shouldNotRegisterWhenTrainNumberIsNegative() {
        IllegalArgumentException thrown = assertThrows(
          IllegalArgumentException.class, () -> {
            DepartureRegister depReg = new DepartureRegister();
            depReg.newDeparture("12:00", "L1", -1, "Oslo", 1, 5);
          }
        );
      }
      @Test
      @DisplayName("Register new departure with no destination.")
      void shouldNotRegisterWithNoDestination() {
        NullPointerException thrown = assertThrows(
          NullPointerException.class, () -> {
            DepartureRegister depReg = new DepartureRegister();
            depReg.newDeparture("12:00", "L1", 1, null, 1, 5);
          }
        );
      }
      @Test
      @DisplayName("Register new departure with invalid track number.")
      void shouldNotRegisterWithNegativeTrackNumber() {
        IllegalArgumentException thrown = assertThrows(
          IllegalArgumentException.class, () -> {
            DepartureRegister depReg = new DepartureRegister();
            depReg.newDeparture("12:00", "L1", 1, "Oslo", -1, 5);
          }
        );
      }
      @Test
      @DisplayName("Register new departure with invalid amount of delay.")
      void shouldNotRegisterWithNegativeDelay() {
        IllegalArgumentException thrown = assertThrows(
          IllegalArgumentException.class, () -> {
            DepartureRegister depReg = new DepartureRegister();
            depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, -5);
          }
        );
      }
    }
  }
    @Nested
    @DisplayName("Tests for check if departure exists method.")
    class checkIfDepartureExistsTests {
      @Test
      @DisplayName("Positive test for check if departure exists return false.")
      void shouldReturnFalseWhenDepartureDoesntExists() {
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 2, "Oslo", 1, 5);
        assertFalse(depReg.checkIfDepartureExists(1));
      }
      @Test
      @DisplayName("Positive test for check if departure exists return true.")
      void shouldReturnTrueWhenDepartureExists() {
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 5);
        assertTrue(depReg.checkIfDepartureExists(1));
      }

      @Test
      @DisplayName("Negative test for check if exists when train number is invalid.") // har allerede blitt kastet før man kommer til denne metoden...
      void shouldThrowIllArgExcWhenTrainNumberNegative() {
      }
    }
    @Nested
    @DisplayName("Tests for removing departures before chosen time method.")
    class removeDeparturesBeforeChosenTimeTests {
      @Test
      @DisplayName("Positive test for removing departures before chosen time.")
      void shouldRemoveDeparturesBeforeChosenTime() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 5);
        depReg.newDeparture("13:00", "L2", 2, "Oslo", 2, 0);
        depReg.newDeparture("14:00", "L3", 3, "Oslo", 3, 0);
        LocalTime chosenTime = LocalTime.parse("13:00");
        assertDoesNotThrow(() -> depReg.removeDeparturesBeforeChosenTime(chosenTime));
      }
      @Test
      @DisplayName("Positive test for removing departures before with no departures before this time.")
      void shouldNotRemoveDeparturesBeforeChosenTime() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 5);
        depReg.newDeparture("13:00", "L1", 2, "Oslo", 1, 5);
        depReg.newDeparture("14:00", "L1", 3, "Oslo", 1, 5);
        LocalTime chosenTime = LocalTime.parse("12:00");
        assertDoesNotThrow(() -> depReg.removeDeparturesBeforeChosenTime(chosenTime));
      }
    }
    @Nested
    @DisplayName("Tests for set track to departure method.")
    class setTrackToDepartureTests {
      @Test
      @DisplayName("Positive test to set track to an existing departure.")
      void shouldSetTrackToDeparture() {
        try {
          DepartureRegister depReg = new DepartureRegister();
          depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
          depReg.setTrackToDeparture(1, 4);
        } catch (IllegalArgumentException e) {
          fail("Should not throw Illegal Argument Exception, test failed.");
        }
      }
      @Test
      @DisplayName("Positive test to set same track to an existing departure.")
      void shouldSetSameTrackToDeparture() {
        try {
          DepartureRegister depReg = new DepartureRegister();
          depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
          depReg.setTrackToDeparture(1, 1);
        } catch (IllegalArgumentException e) {
          fail("Should not throw Illegal Argument Exception, test failed.");
        }
      }
      @Test // to makes sure that it doesn't add a non-existing departure
      @DisplayName("Negative test to set track to a non-existing departure.")
      void shouldNotSetTrackToAnyDepartures() {
        DepartureRegister depReg = new DepartureRegister();
        depReg.setTrackToDeparture(1, 4);
        assertEquals(0, depReg.getAllDepartures().size());
      }
    }

    @Nested
    @DisplayName("Tests for set delay to departure method.")
    class setDelayToDepartureTests {
      @Test
      @DisplayName("Positive test to set delay to an existing departure.")
      void shouldSetDelayToDeparture() {
        try {
          DepartureRegister depReg = new DepartureRegister();
          depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
          depReg.setDelayToDeparture(1, 5);
        } catch (IllegalArgumentException e) {
          fail("Should not throw Illegal Argument Exception, test failed.");
        }
      }
      @Test
      @DisplayName("Positive test to set same delay to an existing departure.")
      void shouldSetSameDelayToDeparture() {
        try {
          DepartureRegister depReg = new DepartureRegister();
          depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
          depReg.setDelayToDeparture(1, 0);
        } catch (IllegalArgumentException e) {
          fail("Should not throw Illegal Argument Exception, test failed.");
        }
      }
      @Test // to makes sure that the delay is not set to a non-existing departure
      @DisplayName("Negative test to set delay to a non-existing departure.")
      void shouldNotSetDelayToAnyDepartures() {
        DepartureRegister depReg = new DepartureRegister();
        depReg.setDelayToDeparture(1, 5);
        assertEquals(0, depReg.getAllDepartures().size());
      }
    }

    @Nested
    @DisplayName("Tests for set new departure time method.")
    class setNewDepartureTimeTests {
      @Test
      @DisplayName("Positive test to set new departure time to an existing departure.")
      void shouldSetNewDepartureTime() {
        try {
          DepartureRegister depReg = new DepartureRegister();
          depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
          depReg.setNewDepartureTime(1, "13:00");
        } catch (IllegalArgumentException | DateTimeException e) {
          fail("Should not throw any Exception, test failed.");
        }
      }
      @Test
      @DisplayName("Positive test to set the same departure time to an existing departure.")
      void shouldSetTheSameTimeToExistingDeparture() {
        try {
          DepartureRegister depReg = new DepartureRegister();
          depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
          depReg.setNewDepartureTime(1, "12:00");
        } catch (IllegalArgumentException | DateTimeException e) {
          fail("Should not throw any Exception, test failed.");
        }
      }
      @Test
      @DisplayName("Negative test to set new departure time to a non-existing departure.")
      void shouldNotSetNewDepartureTimeToAnyDepartures() {
        DepartureRegister depReg = new DepartureRegister();
        depReg.setNewDepartureTime(1, "13:00");
        assertEquals(0, depReg.getAllDepartures().size());
      }
      @Test
      @DisplayName("Negative test to set new departure time to an invalid time.")
      void shouldThrowDateTimeExceptionWhenSetToInvalidTime() {
        DateTimeException thrown = assertThrows(
          DateTimeException.class, () -> {
            DepartureRegister depReg = new DepartureRegister();
            depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
            depReg.setNewDepartureTime(1, "23:60");
          }
        );
      }
    }

    @Nested
    @DisplayName("Tests for sort list by time method.")
    class sortListByTimeTests {
      @Test
      @DisplayName("Positive test to sort list by time from earliest to latest.")
      void shouldSortListByTimeFromEarlyToLatest() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("14:00", "L1", 1, "Oslo", 1, 0);
        depReg.newDeparture("12:00", "L2", 2, "Oslo", 2, 0);
        depReg.newDeparture("13:00", "L3", 3, "Oslo", 3, 0);
        depReg.sortListByTime(depReg.getAllDepartures());
        assertEquals(LocalTime.parse("12:00"), depReg.getAllDepartures().get(0).getDepartureTime());
      }
      @Test
      @DisplayName("Positive test to not change list when it is already sorted correctly.")
      void shouldNotChangeTheOrderOfTheList() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
        depReg.newDeparture("13:00", "L2", 2, "Oslo", 2, 0);
        depReg.newDeparture("14:00", "L3", 3, "Oslo", 3, 0);
        depReg.sortListByTime(depReg.getAllDepartures());
        assertEquals(LocalTime.parse("12:00"), depReg.getAllDepartures().get(0).getDepartureTime());
        assertEquals(LocalTime.parse("13:00"), depReg.getAllDepartures().get(1).getDepartureTime());
      }
      @Test
      @DisplayName("Negative test to sort an empty list.")
      void shouldNotSortAnEmptyList() {}
    }

    @Nested
    @DisplayName("Tests for show list of departures method.")
    class showListOfDeparturesTests {

    }

    @Nested
    @DisplayName("Tests for search by train number method.")
    class searchByTrainNumberTests {

    }

    @Nested
    @DisplayName("Tests for search by destination method.")
    class searchByDestinationTests {

    }

    @Nested
    @DisplayName("Tests for check if destination exists method.")
    class checkIfDestinationExistsTests {

    }

    @Nested
    @DisplayName("Tests for minutes until departure method.")
    class minutesUntilDepartureTests {

    }
}

