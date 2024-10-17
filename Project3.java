//airport with only one runway
//at any given time, one plane can take off, one plane can land, but never both at the same time
//these times are random
//especially concerned with the amount of time waiting in queues
//A key step in our simulation is to decide, at each time unit, how many new planes become ready to land and take off
//Runway maintains 2 queues, landing and takeoff
//The user must supply the number of time intervals the simulation is to run, the expected number
//of planes arriving, the expected number of planes departing per time interval (time intervals are
//discrete sequential steps in time), and the maximum allowed size for runway queues

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class Project3 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Insert Number of Time Intervals: ");
    int timeInterval = scan.nextInt();
    System.out.println("\nInsert Number of Planes Arriving: ");
    int numPlanes = scan.nextInt();
    System.out.println("\nInsert Number of Planes Departing per Time Interval: ");
    int numDeparting = scan.nextInt();
    System.out.println("\nInsert Max Size of Runway Queues: ");
    int runwaySize = scan.nextInt();
  }
}
