///airport with only one runway
//at any one unit of time, one plane can take off, one plane can land, but never both at the same time
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
import java.util.Random;

public class Project3 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in); //create scanner
    Random randy = new Random();

    int maxTime = 0;
    int numPlanes = 0;
    int numDeparting = 0;
    int runwaySize = 0; //Initialize all the int values

    Runway runway = new Runway(runwaySize); //create runway

    //= = = = = = = Introduction + System settings
    System.out.println("\u001B[33mWelcome to the Airport Simulation!\n\u001B[32mWould you like to randomize the settings? (y/n)");
    String input = scan.nextLine().toUpperCase();
    if(input.equals("Y")){
      maxTime = randy.nextInt(10)+1; //Random amount of time between 1 and 10
      numPlanes = randy.nextInt(8)+1; //Random amount of planes between 1 and 8
      numDeparting = randy.nextInt(3)+1; //Random amount of departing planes between 1 and 3
      runwaySize = randy.nextInt(5)+1; //Random size of runway between 1 and 5
    }
    else{
      System.out.println("\u001B[35mInsert Number of Time Intervals: ");
      while(true) {
        if(scan.hasNextInt()) {
          maxTime = scan.nextInt();
          break;
        } else {
          System.out.println("Please insert a number.");
          scan.next();
        }
      }

      System.out.println("\nInsert Number of Planes Arriving: ");
      while(true) {
        if(scan.hasNextInt()) {
          numPlanes = scan.nextInt();
          break;
        } else {
          System.out.println("Please insert a number.");
          scan.next();
        }
      }

      System.out.println("\nInsert Number of Planes Departing per Time Interval: ");
      while(true) {
        if(scan.hasNextInt()) {
          numDeparting = scan.nextInt();
          break;
        } else {
          System.out.println("Please insert a number.");
          scan.next();
        }
      }

      System.out.println("\nInsert Max Size of Runway Queues: ");
      while(true) {
        if(scan.hasNextInt()) {
          runwaySize = scan.nextInt();
          break;
        } else {
          System.out.println("Please insert a number.");
          scan.next();
        }
      }
    }

    for(int i = 0; i < numPlanes; i++){ //create planes
      Plane planes = new Plane(runway, i);
      runway.newLanding(planes);
    }
    int currentTime = 1;

      //Starting Report!
      System.out.println("\u001B[34mStarting Report | Max time: "+maxTime);
      System.out.println("Planes in air: "+ runway.Landing.size());
      System.out.println("Planes at airport: "+ runway.Takeoff.size());
      System.out.println("Planes Departed: " + runway.getDeparted()); //Add number of planes departed
      System.out.println("= = = = =\n");

    while(currentTime <= maxTime){
      //If planes in air, and queue isnt full, land one
      if(runway.Landing.peek() != null && runway.Takeoff.size() < runwaySize){
        runway.newTakeoff();
      }
      else if(runway.Takeoff.size() > 0){ //Otherwise, take one off
        runway.newLeave(runway.Takeoff.peek());
      }

      //Runs the Update() method for every plane in the program.
      //This is what counts their individual statistics.
      for(Plane plane: runway.Landing){
        plane.Update();
      }
      for(Plane plane: runway.Takeoff){
        plane.Update();
      }

      //Quick Summary
      System.out.println("\u001B[36mCurrent Time: "+currentTime);
      System.out.println("Planes in air: "+ runway.Landing.size());
      System.out.println("Planes at airport: "+ runway.Takeoff.size());
      System.out.println("Planes Departed: " + runway.getDeparted()); //Add number of planes departed
      System.out.println("= = = = =\n");

      currentTime++;
    }

    //Print out report summary
    System.out.println("\u001B[33m= = = = Final Summary = = = =");
    System.out.println("\u001B[32mTime Simulated: "+maxTime);
    System.out.println("Number of total planes: "+ numPlanes);
    System.out.println("\t Planes Arrived: " + runway.getProcessed()); //COUNT NUMBER OF PLANES THAT ARRIVED
    System.out.println("\t Planes Departed: " + runway.getDeparted()); //COUNT NUMBER OF PLANES THAT LEFT RUNWAY
    System.out.println("\t Planes Turned Away: " + runway.getRefused()); //COUNT NUMBER OF PLANES THAT TURNED AWAY
    System.out.println("Average wait time on GROUND: " + runway.getWaitTime()); //Average wait time 2
  }
}
