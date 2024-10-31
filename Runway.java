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
    public int avgWait;
    public int numDeparted;

    Queue<Plane> Landing = new LinkedList<>(); //planes waiting to land
    Queue<Plane> Takeoff = new LinkedList<>(); //planes waiting to takeoff

    public Runway(int size){
        maxQueueSize = size;
    }

    public void newLanding(Plane plane){
        Landing.add(plane); //adds new plane to those waiting to land!
    }

    public void newTakeoff(){
        if(Takeoff.size() < maxQueueSize && Landing.size() > 0) {
          Takeoff.add(Landing.peek()); //should remove the plane from landing, and immediately add it to takeoff
          Landing.remove();
          numProcessed++;
        }
        else {
          System.out.println("\u001B[31mFull Airport!");
          numRefused++;
        }
    }

    public void newLeave(Plane plane){
        if(Takeoff.size() > 0){
            System.out.println("\u001B[33mPlane: "+plane.getNumber()+" has departed!");
            Takeoff.remove();
            avgWait = avgWait + plane.groundWaitTime;
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

    public int getWaitTime(){
        return avgWait;
    }
}
