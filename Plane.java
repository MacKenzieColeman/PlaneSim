import java.text.NumberFormat;

public class Plane {

	public int WaitTime;
    private boolean Waiting;

	
	public Plane()
	{
		
	}

    public Update(){
        if(Waiting == true) WaitTime++;
    }

    public TakeOff(){

    }

    public Land(){
        Waiting = true;
    }
