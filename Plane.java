
/*
 * Methods: Initialization, takeoff, landing.
 *
 * Variables: Flight number, plane status of either arriving or departing
 *
 */

public class Plane {

	public int airWaitTime, groundWaitTime; //Wait time in air or on ground, in Time Units
    public int flightNumber; //Flight number of current object
    public Runway currentRunway; //Current runway
    public boolean arriving; //arriving is true if arriving (going to land), and false if departing (leaving or on land).


	public Plane(Runway runway, int number)
	{
        currentRunway = runway; //Sets plane's runway
		flightNumber = number; //Sets plane's number
        arriving = true; //when a plane is created, it is arriving at the airport
	}

    public void Update(){ //Updates every time interval
        if(arriving == true) airWaitTime++; //If the plane is in the air, increase airwaittime
        else groundWaitTime++; //If plane is on the ground, increase ground wait time
    }

    public int getNumber() { //Getter for the flight number
      return flightNumber;
    }

    /*
     * The project calls for a Takeoff() and Land() method, however, we decided it was unnecessary for this specific class.
     * When juggling the pros and cons of it, we realized it would be far less complicated if we handled all the taking off
     * and landing in the Runway class. This is because the queues that handle both taking off and landing are both already
     * in that class, so it wouldnt make sense to unnecessarily reach out to another class when it can all be handled together.
     */
}
