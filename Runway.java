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
    Queue<Plane> Landing = new LinkedList<>(); //planes waiting to land
    Queue<Plane> Takeoff = new LinkedList<>(); //planes waiting to takeoff

    public Runway(int size){
        maxQueueSize = size;
    }

    public void newLanding(Plane plane){
        Landing.add(plane); //adds new plane to those waiting to land!
    }

    public void newTakeoff(){
        if(Takeoff.size() < maxQueueSize && Landing.size() > 0) Takeoff.add(Landing.remove()); //should remove the plane from landing, and immediately add it to takeoff
        else System.out.println("Full Airport!");
    }

    public void newLeave(Plane plane){
        if(Takeoff.size() > 0){
            System.out.println("Plane: "+plane.getNumber()+" has departed!");
            Takeoff.remove();
        }
    }
}
