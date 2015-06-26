package leancarbuilder.part;

import java.awt.Color;

public class Car extends Part{

	private Engine engine;
	private CoachWork coachwork;
	private FourWheels wheels;
	private Color colour;
	
	public Car(Engine e, CoachWork c, FourWheels w, long serialNumber) {
		engine = e;
		coachwork = c;
		wheels = w;
		setSerialNumber(serialNumber);
	}
	
	public void setColor(Color c) {
		colour = c;
	}
	
	public Color getColour() {
		return colour;
	}

}
