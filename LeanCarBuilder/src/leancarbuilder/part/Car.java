package leancarbuilder.part;

public class Car extends Part{

	private Engine engine;
	private CoachWork coachwork;
	private FourWheels wheels;
	private String colour;
	
	public Car(Engine e, CoachWork c, FourWheels w, long serialNumber) {
		engine = e;
		coachwork = c;
		wheels = w;
		setSerialNumber(serialNumber);
	}
	
	public void setColour(String c) {
		colour = c;
	}
	
	public String getColour() {
		return colour;
	}

}
