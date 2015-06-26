package carbuilder.workstation;

import org.jcsp.lang.*;

import carbuilder.part.*;

public class CarAssembly implements CSProcess {

    private ChannelInput engines, coachwork, wheels;
    private ChannelOutput output;
    private long serialNumber;

	public CarAssembly(ChannelInput e, ChannelInput c, ChannelInput w, ChannelOutput out, long baseSerial)
	{
		engines = e;
		coachwork = c;
		wheels = w;
		output = out;
		serialNumber = baseSerial;
	}
	
	
	@Override
	public void run() {
		while(true) {
			Car car = new Car((Engine) engines.read(), (CoachWork) coachwork.read(), (FourWheels) wheels.read(), serialNumber++);
			output.write(car);
			System.out.println("Car Assembled"); 
		}

	}

}
