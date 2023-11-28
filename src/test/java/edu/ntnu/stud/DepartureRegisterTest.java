package edu.ntnu.stud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.DateTimeException;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
public class DepartureRegisterTest {
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

        assertNotEquals(depReg.getAllDepartures().get(0).getDepartureTime(),
            depReg.sortListByTime(depReg.getAllDepartures()).get(0).getDepartureTime());
      }
      @Test
      @DisplayName("Positive test to remove departures that departed before the current time.")
      void shouldRemoveDeparturesBeforeCurrentTime() {
        Time time = new Time();
        time.setCurrentTime("12:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("10:00", "L1", 1, "Oslo", 1, 0);
        depReg.newDeparture("11:00", "L2", 2, "Oslo", 2, 0);
        depReg.newDeparture("12:05", "L3", 3, "Oslo", 3, 0);
        assertEquals(1, depReg.sortListByTime(depReg.getAllDepartures()).size());
      }

      @Test
      @DisplayName("Negative test to not change list when it is already sorted correctly.")
      void shouldNotChangeTheOrderOfTheList() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
        depReg.newDeparture("13:00", "L2", 2, "Oslo", 2, 0);
        depReg.newDeparture("14:00", "L3", 3, "Oslo", 3, 0);
        depReg.sortListByTime(depReg.getAllDepartures());
        assertEquals(LocalTime.parse("12:00"),
            depReg.sortListByTime(depReg.getAllDepartures()).get(0).getDepartureTime());
        assertEquals(LocalTime.parse("13:00"),
            depReg.sortListByTime(depReg.getAllDepartures()).get(1).getDepartureTime());
      }
      @Test
      @DisplayName("Positive test to not sort an empty list.")
      void shouldNotSortAnEmptyList() {
        DepartureRegister depReg = new DepartureRegister();
        assertEquals(0, depReg.sortListByTime(depReg.getAllDepartures()).size());
      }
    }

    @Nested
    @DisplayName("Tests for show list of departures method.")
    class showListOfDeparturesTests {
      @Test
      @DisplayName("Positive test for show list of departures.")
      void shouldShowListOfAllDepartures() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
        depReg.newDeparture("13:00", "L2", 2, "Oslo", 2, 5);
        depReg.newDeparture("14:00", "L3", 3, "Oslo", 3, 10);
        assertEquals("""
                ----------------------------------------------------------------------------------------
                | Train number  | Departure time | Line  |          Destination | Track |    Time left |
                ----------------------------------------------------------------------------------------
                |             1 |          12:00 |    L1 |                 Oslo |     1 |     2h 0 min |
                |             2 |          13:05 |    L2 |                 Oslo |     2 |     3h 5 min |
                |             3 |          14:10 |    L3 |                 Oslo |     3 |    4h 10 min |
                ----------------------------------------------------------------------------------------
                """,
            depReg.showListOfDepartures(depReg.getAllDepartures()));
      }
      @Test
      @DisplayName("Negative test for showing an empty list of departures.")
      void shouldShowEmptyListOfDepartures() {
        DepartureRegister depReg = new DepartureRegister();
        assertEquals("""
                ----------------------------------------------------------------------------------------
                | Train number  | Departure time | Line  |          Destination | Track |    Time left |
                ----------------------------------------------------------------------------------------
                ----------------------------------------------------------------------------------------
                """,
            depReg.showListOfDepartures(depReg.getAllDepartures()));
      }
      @Test
      @DisplayName("Negative test to show list of departures when list is null.")
      void shouldReturnEmptyList() {
        NullPointerException thrown = assertThrows(
            NullPointerException.class, () -> {
              DepartureRegister depReg = new DepartureRegister();
              depReg.showListOfDepartures(null);
            }
        );
      }
    }

    @Nested
    @DisplayName("Tests for get all departures method.")
    class getAllDeparturesListTests {
      @Test
      @DisplayName("Positive test for get all departures method.")
      void shouldGetAllDeparturesWhenListHasElements() {
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
        depReg.newDeparture("13:00", "L2", 2, "Oslo", 2, 5);
        depReg.newDeparture("14:00", "L3", 3, "Oslo", 3, 10);
        assertEquals(3, depReg.getAllDepartures().size());
      }
      @Test
      @DisplayName("Positive test for get all departures method when list is empty.")
      void shouldGetAllDeparturesWhenListIsEmpty() {
        DepartureRegister depReg = new DepartureRegister();
        assertEquals(0, depReg.getAllDepartures().size());
      }
    }

    @Nested
    @DisplayName("Tests for search by train number method.")
    class searchByTrainNumberTests {
    @Test
    @DisplayName("Positive test to search by train number when one train departure exists.")
    void shouldReturnDepartureWhenTrainNumberExists() {
      Time time = new Time();
      time.setCurrentTime("10:00");
      DepartureRegister depReg = new DepartureRegister();
      depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
      assertEquals("""
              ----------------------------------------------------------------------------------------
              | Train number  | Departure time | Line  |          Destination | Track |    Time left |
              ----------------------------------------------------------------------------------------
              |             1 |          12:00 |    L1 |                 Oslo |     1 |     2h 0 min |
              ----------------------------------------------------------------------------------------
              """,
          depReg.searchByTrainNumber(1));
      }
      @Test
      @DisplayName("Positive test to search by train number when multiple train departures exists.")
      void shouldReturnValuesOfAllDepartures() {
      Time time = new Time();
      time.setCurrentTime("10:00");
      DepartureRegister depReg = new DepartureRegister();
      depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
      depReg.newDeparture("13:00", "L2", 2, "Oslo", 2, 5);
      depReg.newDeparture("14:00", "L3", 3, "Oslo", 3, 10);
      depReg.newDeparture("15:00", "L4", 4, "Oslo", 4, 15);
      depReg.newDeparture("16:00", "L5", 5, "Oslo", 5, 20);
        assertEquals("""
                ----------------------------------------------------------------------------------------
                | Train number  | Departure time | Line  |          Destination | Track |    Time left |
                ----------------------------------------------------------------------------------------
                |             1 |          12:00 |    L1 |                 Oslo |     1 |     2h 0 min |
                ----------------------------------------------------------------------------------------
                """,
            depReg.searchByTrainNumber(1));
      }
      @Test
      @DisplayName("Negative test to search by train number when no departures exists.")
      void shouldReturnEmptyStringListWhenDepartureNotExist() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        assertEquals("""
                ----------------------------------------------------------------------------------------
                | Train number  | Departure time | Line  |          Destination | Track |    Time left |
                ----------------------------------------------------------------------------------------
                ----------------------------------------------------------------------------------------
                """,
            depReg.searchByTrainNumber(1));
      }
      @Test
      @DisplayName("Negative test to search by train number when train number is invalid.")
      void shouldReturnEmptyListForInvalidTrainNumber() {
      Time time = new Time();
      time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        assertEquals("""
                ----------------------------------------------------------------------------------------
                | Train number  | Departure time | Line  |          Destination | Track |    Time left |
                ----------------------------------------------------------------------------------------
                ----------------------------------------------------------------------------------------
                """,
            depReg.searchByTrainNumber(-1));
      }
    }

    @Nested
    @DisplayName("Tests for search by destination method.")
    class searchByDestinationTests {
      @Test
      @DisplayName("Positive test to search by destination when one train departure exists.")
      void shouldReturnOneDepartureWithCorrectDestination() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
        assertEquals(
            """
                ----------------------------------------------------------------------------------------
                | Train number  | Departure time | Line  |          Destination | Track |    Time left |
                ----------------------------------------------------------------------------------------
                |             1 |          12:00 |    L1 |                 Oslo |     1 |     2h 0 min |
                ----------------------------------------------------------------------------------------
                """,
            depReg.searchByDestination("Oslo"));
      }
      @Test
      @DisplayName("Positive test to search by destination with multiple departures.")
      void shouldReturnListOfMultipleDepartures() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
        depReg.newDeparture("13:00", "L2", 2, "Fagernes", 2, 5);
        depReg.newDeparture("14:00", "L3", 3, "Oslo", 3, 10);
        depReg.newDeparture("15:00", "L4", 4, "Oslo", 4, 15);
        assertEquals(
            """
                ----------------------------------------------------------------------------------------
                | Train number  | Departure time | Line  |          Destination | Track |    Time left |
                ----------------------------------------------------------------------------------------
                |             1 |          12:00 |    L1 |                 Oslo |     1 |     2h 0 min |
                |             3 |          14:10 |    L3 |                 Oslo |     3 |    4h 10 min |
                |             4 |          15:15 |    L4 |                 Oslo |     4 |    5h 15 min |
                ----------------------------------------------------------------------------------------
                """,
            depReg.searchByDestination("Oslo"));
      }
      @Test
      @DisplayName("Negative test to search by destination when no departures has correct destination.")
      void shouldReturnEmptyList() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
        depReg.newDeparture("13:00", "L2", 2, "Fagernes", 2, 5);
        depReg.newDeparture("14:00", "L3", 3, "Trondheim", 3, 10);
        assertEquals("""
                ----------------------------------------------------------------------------------------
                | Train number  | Departure time | Line  |          Destination | Track |    Time left |
                ----------------------------------------------------------------------------------------
                ----------------------------------------------------------------------------------------
                """,
            depReg.searchByDestination("Bergen"));
      }
      @Test
      @DisplayName("Negative test to search by destination when no departures exists.")
      void shouldReturnEmptyListWhenNoDeparturesExists() {
        DepartureRegister depReg = new DepartureRegister();
        assertEquals("""
                ----------------------------------------------------------------------------------------
                | Train number  | Departure time | Line  |          Destination | Track |    Time left |
                ----------------------------------------------------------------------------------------
                ----------------------------------------------------------------------------------------
                """,
            depReg.searchByDestination("Oslo"));
      }
      @Test
      @DisplayName("Negative test to search by destination when destination is empty.")
      void shouldReturnEmptyListWhenDestinationIsEmpty() {
        DepartureRegister depReg = new DepartureRegister();
        assertEquals("""
                ----------------------------------------------------------------------------------------
                | Train number  | Departure time | Line  |          Destination | Track |    Time left |
                ----------------------------------------------------------------------------------------
                ----------------------------------------------------------------------------------------
                """,
            depReg.searchByDestination(""));
      }
      @Test
      @DisplayName("Negative test to search by destination when destination is null.")
      void shouldReturnEmptyListWhenDestinationIsNull() {
        DepartureRegister depReg = new DepartureRegister();
        assertEquals("""
                ----------------------------------------------------------------------------------------
                | Train number  | Departure time | Line  |          Destination | Track |    Time left |
                ----------------------------------------------------------------------------------------
                ----------------------------------------------------------------------------------------
                """,
            depReg.searchByDestination(null));
      }
    }

    @Nested
    @DisplayName("Tests for check if destination exists method.")
    class checkIfDestinationExistsTests {
      @Test
      @DisplayName("Positive test to return true when destination exists with different cased letters.")
      void shouldReturnTrueWhenDestinationExists() {
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
        assertTrue(depReg.checkIfDestinationExists("oslo"));
      }
      @Test
      @DisplayName("Positive test to return false when destination does not exist")
      void shouldReturnFalseWhenDestinationDoesNotExist() {
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
        assertFalse(depReg.checkIfDestinationExists("Bergen"));
      }
      @Test
      @DisplayName("Negative test to return false when destination is null.")
      void shouldReturnFalseWhenDestinationIsNull() {
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
        assertFalse(depReg.checkIfDestinationExists(null));
      }
      @Test
      @DisplayName("Negative test to return false when destination is empty.")
      void shouldReturnFalseWhenDestinationIsEmpty() {
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 0);
        assertFalse(depReg.checkIfDestinationExists(""));
      }
    }

    @Nested
    @DisplayName("Tests for minutes until departure method.")
    class minutesUntilDepartureTests {
      @Test
      @DisplayName("Positive test to return minutes until departure when departure exists.")
      void shouldReturnMinutesUntilDepartureWhenDepartureExists() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("10:30", "L1", 1, "Oslo", 1, 0);
        LocalTime departureTime = LocalTime.parse("10:30");
        assertEquals("30 min", depReg.minutesUntilDeparture(departureTime, 1));
      }
      @Test
      @DisplayName("Positive test to return 0 minutes until departure.")
      void shouldReturn0HoursAnd0Min() {
        Time time = new Time();
        time.setCurrentTime("10:05");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("10:00", "L1", 1, "Oslo", 1, 5);
        LocalTime departureTime = LocalTime.parse("10:05");
        assertEquals("0 min", depReg.minutesUntilDeparture(departureTime, 1));
      }
      @Test
      @DisplayName("Negative test to return empty String when departure does not exist.")
      void shouldReturnNullWhenDepartureDoesNotExist() {
        Time time = new Time();
        time.setCurrentTime("10:00");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("10:30", "L1", 1, "Oslo", 1, 0);
        LocalTime departureTime = LocalTime.parse("10:30");
        assertEquals("", depReg.minutesUntilDeparture(departureTime, 2));
      }

      @Test
      @DisplayName("Negative test to return empty string when current time is after departure time.")
      void shouldReturnEmptyStringWhenCurrentTimeIsPastDepartureTime() {
        Time time = new Time();
        time.setCurrentTime("12:10");
        DepartureRegister depReg = new DepartureRegister();
        depReg.newDeparture("12:00", "L1", 1, "Oslo", 1, 5);
        LocalTime departureTime = LocalTime.parse("12:05");
        assertEquals("", depReg.minutesUntilDeparture(departureTime, 1));
      }
    }
}
