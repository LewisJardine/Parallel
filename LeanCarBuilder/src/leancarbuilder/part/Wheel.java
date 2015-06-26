package leancarbuilder.part;

public class Wheel extends CanBeDefective{

	public Wheel(boolean defect, long serialNumber) {
		defective = defect;
		setSerialNumber(serialNumber);
	}
	
}
