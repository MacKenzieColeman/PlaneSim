///airport with only one runway
//at any one unit of time, one plane can take off, one plane can land, but never both at the same time
//these times are random
//especially concerned with the amount of time waiting in queues
//A key step in our simulation is to decide, at each time unit, how many new planes become ready to land and take off
//Runway maintains 2 queues, landing and takeoff

//The user must supply the number of time intervals the simulation is to run, the expected number
//of planes arriving, the expected number of planes departing per time interval (time intervals are
//discrete sequential steps in time), and the maximum allowed size for runway queues

import java.util.Scanner;
import java.util.Random;

public class Project3 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in); //create scanner
    Random randy = new Random(); //create random

    //Initialize all the int values
    int maxTime = 0;
    int numPlanes = 0;
    int numDeparting = 0;
    int runwaySize = 0;

    //create runway
    Runway runway = new Runway(runwaySize);

    //= = = = = = = Introduction + System settings
    System.out.println("\u001B[33mWelcome to the Airport Simulation!\n\u001B[32mWould you like to randomize the settings? (y/n)");
    String input = scan.nextLine().toUpperCase();
    if(input.equals("Y")){ //If YES to randomization:
      maxTime = randy.nextInt(10)+1; //Random amount of time between 1 and 10
      numPlanes = randy.nextInt(8)+1; //Random amount of planes between 1 and 8
      numDeparting = randy.nextInt(3)+1; //Random amount of departing planes between 1 and 3
      runwaySize = randy.nextInt(5)+1; //Random size of runway between 1 and 5
      runway.changeSize(runwaySize); //set the proper runway size
    }
    else{ //If NO to randomization
      System.out.println("\u001B[35mInsert Number of Time Intervals: "); //Ask input
      while(true) { //Failsafe for improper input
        if(scan.hasNextInt()) {
          maxTime = scan.nextInt();
          break;
        } else {
          System.out.println("Please insert a number."); //Error
          scan.next();
        }
      }

      System.out.println("\nInsert Number of Planes Arriving: "); //Ask num of planes
      while(true) {//Failsafe for improper input
        if(scan.hasNextInt()) {
          numPlanes = scan.nextInt();
          break;
        } else {
          System.out.println("Please insert a number."); //Error
          scan.next();
        }
      }

      System.out.println("\nInsert Number of Planes Arriving or Departing per Time Interval: "); //Ask for number of planes departing/arriving per unit
      while(true) { //Failsafe for improper input
        if(scan.hasNextInt()) {
          numDeparting = scan.nextInt();
          break;
        } else {
          System.out.println("Please insert a number."); //Error
          scan.next();
        }
      }

      System.out.println("\nInsert Max Size of Runway: "); //Ask for runway size
      while(true) { //Failsafe for improper input
        if(scan.hasNextInt()) {
          runwaySize = scan.nextInt();
          runway.changeSize(runwaySize); //sets runway size
          break;
        } else {
          System.out.println("Please insert a number."); //Error
          scan.next();
        }
      }
    }

    //Create the proper amount of planes
    for(int i = 0; i < numPlanes; i++){
      Plane planes = new Plane(runway, i); //create the runway, setting the proper runway, and Flight number
      runway.newLanding(planes); //Add each plane to the runway, starting in the Runway queue
    }
    int currentTime = 1; //Create current time int, starting at 1 (doesnt make sense to start at time 0)

      //= = = = = = = = = = = Starting Report! Shows user either what they inputed, or what the program randomized
      System.out.println("\u001B[34m= = Starting Report = =");
      System.out.println("Max time: "+maxTime);
      System.out.println("Planes Arriving / Departing per Time Unit: "+numDeparting);
      System.out.println("Runway Size: "+runwaySize);
      System.out.println("Planes in air: "+ runway.Landing.size());
      System.out.println("Planes at airport: "+ runway.Takeoff.size());
      System.out.println("Planes Departed: " + runway.getDeparted()); //Add number of planes departed
      System.out.println("= = = = =\n");

      //= = = = = = = = = = = Each unit of time! While there is still time left, continue loop
    while(currentTime <= maxTime){
      for(int i = 0; i < numDeparting; i++)
      {
        if(runway.Landing.peek() != null && runway.Takeoff.size() < runwaySize){ //If theres a plane in landing, and runway isn't full, land a new plane to the runway
          runway.newTakeoff();
        }
        else if(runway.Takeoff.size() > 0){ //Otherwise, as long as theres a plane in the runway, depart a plane
          runway.newLeave(runway.Takeoff.peek());
        }
      }

      //Runs the Update() method for every plane in the program.
      //This is what counts their individual statistics.
      for(Plane plane: runway.Landing){ //For each plane in Landing, update
        plane.Update();
      }
      for(Plane plane: runway.Takeoff){ //For each plane in Takeoff, update
        plane.Update();
      }

      //= = = = = = = = = = = Quick Summary! Shows current information for the airport at each time interval
      System.out.println("\u001B[36mCurrent Time: "+currentTime);
      System.out.println("Planes in air: "+ runway.Landing.size());
      System.out.println("Planes at airport: "+ runway.Takeoff.size());
      System.out.println("Planes Departed: " + runway.getDeparted()); //Add number of planes departed
      System.out.println("= = = = =\n");

      currentTime++; //Increases to the next time, before continuing while loop
    }

    //Print out report summary. 
    System.out.println("\u001B[33m= = = = Final Summary = = = =");
    System.out.println("\u001B[32mTime Simulated: "+maxTime); //Amount of time units simulated
    System.out.println("Number of total planes: "+ numPlanes); //Amount of planes from start
    System.out.println("\t Planes Arrived: " + runway.getProcessed()); //Planes that arrived at the airport
    System.out.println("\t Planes Departed: " + runway.getDeparted()); //Planes that left the runway, and departed
    System.out.println("\t Planes Turned Away: " + runway.getRefused()); //Number of planes turned away because of a full airport
    System.out.println("Average wait time on GROUND: " + runway.getWaitTime()); //Average wait time on airport
  }
}
