package leancarbuilder.workstation;

import java.awt.Color;

import org.jcsp.lang.*;

import leancarbuilder.part.*;

public class Painter extends Worker implements CSProcess {

    private ChannelInput input;
    private ChannelOutput output;
    private Color colour;

	public Painter(Color c, ChannelInput in, ChannelOutput out)
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
			car.setColor(colour);
			//System.out.println("Car Painted "+colour.toString()); 
			output.write(car);
		}

	}

}
