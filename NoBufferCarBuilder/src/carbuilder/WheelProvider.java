package carbuilder;

import java.util.Random;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;

import carbuilder.part.Wheel;

public class WheelProvider implements CSProcess{

    private ChannelOutput out;
    private final static float DEFECTIVE = 0.03f;
    private Random rng;
    private long serial;
   

    public WheelProvider(ChannelOutput out, long baseSerial)
    {
      this.out = out;
      rng = new Random(System.currentTimeMillis()+1234l);
      serial = baseSerial;
    }

	@Override
	public void run() {
		while (true) {
			out.write(new Wheel(rng.nextFloat() <= DEFECTIVE, serial++));
		}
	}
}
