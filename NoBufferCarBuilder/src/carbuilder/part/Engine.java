package carbuilder.part;

public class Engine extends CanBeDefective {

	public Engine(boolean defect, long serialNumber) {
		defective = defect;
		setSerialNumber(serialNumber);
	}
}
