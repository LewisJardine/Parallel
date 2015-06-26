package leancarbuilder.workstation;

import org.jcsp.lang.*;

import leancarbuilder.part.Car;

public class Consumer implements CSProcess {

    private ChannelInput input;
    private int total = 0;

	public Consumer(ChannelInput in)
	{
		input = in;
	}
	
	
	@Override
	public void run() {
		while(true) {
			Car car = (Car) input.read();
			total++;
			System.out.println(total+" cars produced, colour = "+car.getColour()); 
		}

	}

}
