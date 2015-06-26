package leancarbuilder;

import java.util.Random;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;

import leancarbuilder.part.CoachWork;

public class CoachWorkProvider implements CSProcess{

    private ChannelOutput out;
    private final static float DEFECTIVE = 0.08f;
    private Random rng;
    private long serialNumber;

    public CoachWorkProvider(ChannelOutput out, long baseSerial)
    {
      this.out = out;
      rng = new Random(System.currentTimeMillis()+baseSerial);
      serialNumber = baseSerial;
    }

	@Override
	public void run() {
		while (true) {
			out.write(new CoachWork(rng.nextFloat() <= DEFECTIVE, serialNumber++));
		}
		
	}

}
