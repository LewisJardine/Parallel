package carbuilder.workstation;

import org.jcsp.lang.*;

import carbuilder.part.*;

public class WheelFilter implements CSProcess {

    private ChannelInput input;
    private ChannelOutput output;

	public WheelFilter(ChannelInput in, ChannelOutput out)
	{
		input = in;
		output = out;
	}
	
	
	@Override
	public void run() {
		FourWheels wheels = new FourWheels();
		while(true) {
			Wheel wheel = (Wheel) input.read();
			if (wheel.isDefective()) {
				System.out.println("Defective Wheel"); }
			else {
				wheels.addWheel(wheel);
				if (wheels.isComplete()) {
					wheels = new FourWheels();
					output.write(wheels);
					System.out.println("Good Set of Wheels"); 
				}
			}
		}

	}

}
