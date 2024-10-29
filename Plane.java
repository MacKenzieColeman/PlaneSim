import java.text.NumberFormat;

/*
 * Methods: Initialization, takeoff, landing.
 *
 * Variables: Flight number, plane status of either arriving or departing
 *
 */

public class Plane {

	public int airWaitTime, groundWaitTime;
    public int flightNumber;
    public Runway currentRunway;
    public boolean arriving; //arriving is true if arriving (going to land), and false if departing (leaving or on land).


	public Plane(Runway runway, int number)
	{
        currentRunway = runway;
		flightNumber = number;
        arriving = true; //when a plane is created, it must be arriving
	}

    public void Update(){
        if(arriving == true) airWaitTime++;
        else groundWaitTime++;
    }

    public int getNumber() {
      return flightNumber;
    }

    public void Land(){
        arriving = false; //now on land
        currentRunway.newLanding(Plane.this);
    }

    public void TakeOff(){
        currentRunway.newTakeoff();
    }
}
