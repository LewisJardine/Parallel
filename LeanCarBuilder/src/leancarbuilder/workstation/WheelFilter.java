package leancarbuilder.workstation;

import org.jcsp.lang.*;

import leancarbuilder.part.*;

public class WheelFilter extends Worker implements CSProcess {

    private ChannelInput input;
    private ChannelOutput output;

	public WheelFilter(ChannelInput in, ChannelOutput out)
	{
		super(1);
		input = in;
		output = out;
	}
	
	
	@Override
	public void run() {
		FourWheels wheels = new FourWheels();
		while(true) {
			Wheel wheel = (Wheel) input.read();
			doWork();
			if (wheel.isDefective()) {
				System.out.println("Defective Wheel"); }
			else {
				wheels.addWheel(wheel);
				if (wheels.isComplete()) {
					wheels = new FourWheels();
					output.write(wheels);
					// System.out.println("Good Set of Wheels"); 
				}
			}
		}

	}

}
