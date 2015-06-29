package leancarbuilder.workstation;

import org.jcsp.lang.*;

import leancarbuilder.part.Car;

public class Consumer implements CSProcess {

    private ChannelInput input;
    private int total = 0;
    private int red = 0;
    private int green = 0;
    private int blue = 0;

	public Consumer(ChannelInput in)
	{
		input = in;
	}
	
	
	@Override
	public void run() {
		while(true) {
			Car car = (Car) input.read();
			total++;
			switch (car.getColour()) {
			case Painter.RED:
				red++;
				break;
			case Painter.GREEN:
				green++;
				break;
			case Painter.BLUE:
				blue++;
			}
			System.out.println(car.getColour()+" car produced, red="+red+", green="+green+", blue="+blue+", total="+total); 
		}

	}

}
