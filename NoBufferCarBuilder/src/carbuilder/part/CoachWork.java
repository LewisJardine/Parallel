package carbuilder.part;

public class CoachWork extends CanBeDefective {
	
	public CoachWork(boolean defect, long serialNumber) {
		defective = defect;
		setSerialNumber(serialNumber);
	}
	
}
