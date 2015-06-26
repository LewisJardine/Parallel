package leancarbuilder.workstation;

import org.jcsp.lang.*;

import leancarbuilder.part.*;

public class CarAssembly extends Worker implements CSProcess {

    private ChannelInput engines, coachwork, wheels;
    private ChannelOutput output;
    private long serialNumber;
    //private Worker worker;

	public CarAssembly(ChannelInput e, ChannelInput c, ChannelInput w, ChannelOutput out, long baseSerial)
	{
		super(100);
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
			doWork();
			output.write(car);
			System.out.println("Car Assembled"); 
		}

	}

}
