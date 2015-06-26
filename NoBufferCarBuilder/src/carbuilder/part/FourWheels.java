package carbuilder.part;

import java.util.ArrayList;

public class FourWheels {

	private ArrayList<Wheel> wheels;
	
	public FourWheels() {
		wheels = new ArrayList<Wheel>();
	}
	
	public boolean isComplete() {
		return wheels.size() >= 4;
	}
	
	public boolean addWheel(Wheel w) {
		if (isComplete()) {
			return false;
		} else {
			wheels.add(w);
			return true;
		}
	}
	
	public ArrayList<Wheel> getWheels() {
		return wheels;
	}
}
