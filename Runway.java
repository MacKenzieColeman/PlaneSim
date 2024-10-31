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
    public int maxQueueSize;
    public int numProcessed;
    public int numRefused;
    public int totalWait;
    public int numDeparted;

    Queue<Plane> Landing = new LinkedList<>(); //planes waiting to land
    Queue<Plane> Takeoff = new LinkedList<>(); //planes waiting to takeoff

    public Runway(int size){
        maxQueueSize = size;
    }

    public void changeSize(int newSize){
        maxQueueSize = newSize;
    }

    public void newLanding(Plane plane){ //New plane in the air
        Landing.add(plane); //adds new plane to those waiting to land!
    }

    public void newTakeoff(){ //New plane to the airport
        if(Takeoff.size() < maxQueueSize && Landing.size() > 0) {
          Takeoff.add(Landing.peek()); //should remove the plane from landing, and immediately add it to takeoff
          Plane landingPlane = Landing.peek();
          System.out.println("Plane "+Landing.peek().flightNumber+" has landed!");
          Landing.remove();
          landingPlane.arriving = false;
          numProcessed++;
        }
        else {
          System.out.println("\u001B[31mFull Airport!");
          numRefused++;
        }
    }

    public void newLeave(Plane plane){
        if(Takeoff.size() > 0){
            System.out.println("Plane "+plane.getNumber()+" has departed!");
            Takeoff.remove();
            totalWait += plane.groundWaitTime;
            numDeparted++;
        }
    }

    public int getProcessed(){
        return numProcessed;
    }

    public int getDeparted(){
        return numDeparted;
    }

    public int getRefused(){
        return numRefused;
    }

    public double getWaitTime(){
        if(numDeparted == 0) return 0;
        else return (double) totalWait / numDeparted;
    }
}
