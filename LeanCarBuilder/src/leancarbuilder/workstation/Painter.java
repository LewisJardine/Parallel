package leancarbuilder.workstation;

import org.jcsp.lang.*;

import leancarbuilder.part.*;

public class Painter extends Worker implements CSProcess {

	public final static String RED = "Red";
	public final static String GREEN = "Green";
	public final static String BLUE = "Blue";

    private ChannelInput input;
    private ChannelOutput output;
    private String colour;

	public Painter(String c, ChannelInput in, ChannelOutput out)
	{
		super(15);
		colour = c;
		input = in;
		output = out;
	}
	
	@Override
	public void run() {
		while(true) {
			Car car = (Car) input.read();
			doWork();
			car.setColour(colour);
			//System.out.println("Car Painted "+colour); 
			output.write(car);
		}

	}

}
