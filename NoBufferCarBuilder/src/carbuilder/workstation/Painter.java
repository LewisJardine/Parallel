package carbuilder.workstation;

import java.awt.Color;

import org.jcsp.lang.*;

import carbuilder.part.*;

public class Painter implements CSProcess {

    private ChannelInput input;
    private ChannelOutput output;
    private Color colour;

	public Painter(Color c, ChannelInput in, ChannelOutput out)
	{
		colour = c;
		input = in;
		output = out;
	}
	
	@Override
	public void run() {
		while(true) {
			Car car = (Car) input.read();
			car.setColor(colour);
			System.out.println("Car Painted "+colour.toString()); 
			output.write(car);
		}

	}

}
