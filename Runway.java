import java.util.Queue;
import java.util.LinkedList;

/*
 * Should prioriize landing a plane then a new one taking off (Better to wait on ground then in air)
 * SO: A plane can only take off if landing queue is empty
 *
 * Keep statistics on: Number of plane sprocessed,
 * average time spent waiting, number of planes refused service(queue is full and cant take anymore planes)
 *
 */

public class Runway {
    public int maxQueueSize; //Max runway size
    public int numProcessed; //Number of planes processed
    public int numRefused; //Number of planes refused because of full airport
    public int totalWait; //Total wait time
    public int numDeparted; //Number of planes departed

    Queue<Plane> Landing = new LinkedList<>(); //planes waiting to land
    Queue<Plane> Takeoff = new LinkedList<>(); //planes waiting to takeoff

    public Runway(int size){
        maxQueueSize = size; //Sets max runway size
    }

    public void changeSize(int newSize){ //Change the max runway size since it's been initialized
        maxQueueSize = newSize;
    }

    public void newLanding(Plane plane){ //New plane in the air
        Landing.add(plane); //adds new plane to those waiting to land!
    }

    public void newTakeoff(){ //New plane to the airport
        if(Takeoff.size() < maxQueueSize && Landing.size() > 0) { //If the runway isnt full & there is a plane waiting to land...
          Takeoff.add(Landing.peek()); //Adds the plane from the front of Landing to Takeoff
          Plane landingPlane = Landing.peek(); //sets LandingPlane to whatever plane is landing
          System.out.println("\u001B[36mPlane "+Landing.peek().flightNumber+" has landed!"); //Outputs in the console what plane is landing
          Landing.remove(); //Removes the plane from the air queue
          landingPlane.arriving = false; //Sets the arriving bool to false of the plane
          numProcessed++; //Increases number of planes processed
        }
        else { //Otherwise...
          System.out.println("\u001B[31mFull Airport!"); //Output the airport is full
          numRefused++; //Increase amount refused
        }
    }

    public void newLeave(Plane plane){ //Inputted plane departs from the airport
        if(Takeoff.size() > 0){ //As long as the airport isn't empty
            System.out.println("\u001B[36mPlane "+plane.getNumber()+" has departed!"); //Get plane number, and outputs that to console
            Takeoff.remove(); //removes plane from the airport
            totalWait += plane.groundWaitTime; //adds to the wait time
            numDeparted++; //inreases number of planes departed
        }
    }

    public int getProcessed(){ //getter for amount processed
        return numProcessed;
    }

    public int getDeparted(){ //getter for amount departed
        return numDeparted;
    }

    public int getRefused(){ //getter for amount refused
        return numRefused;
    }

    public double getWaitTime(){ //getter for the average wait time
        if(numDeparted == 0) return 0;
        else return (double) totalWait / numDeparted;
    }
}
