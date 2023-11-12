package edu.ntnu.stud;

import org.junit.jupiter.api.Test;

public class TrainDepartureTest {
  @Test
    public void testTrainDeparture() {
      TrainDeparture train1 = new TrainDeparture(1200, "L1", 1, "Oslo", 2, 0);
      TrainDeparture train2 = new TrainDeparture(2500, "", 2, "Trondheim", 3, 15);
      TrainDeparture train3 = new TrainDeparture(1400, "L3", -3, "Bergen", 4, 0);
      TrainDeparture train4 = new TrainDeparture(1500, "L4", 4, null, 5, 5);
      TrainDeparture train5 = new TrainDeparture(1500, "L4", 5, "Stavanger", -6, 5);
      TrainDeparture train6 = new TrainDeparture(1500, "L4", 6, "Tromsø", 6, -5);


    System.out.println(train1.toString());
    System.out.println(train2.toString());
    System.out.println(train3.toString());
    System.out.println(train4.toString());
    System.out.println(train5.toString());
    System.out.println(train6.toString());
    train1.setDelay(-4);
    train2.setTrack(-1);

  }
}
