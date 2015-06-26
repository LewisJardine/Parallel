package leancarbuilder;

import java.util.Random;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;

import leancarbuilder.part.Engine;

public class EngineProvider implements CSProcess{

    private ChannelOutput out;
    private final static float DEFECTIVE = 0.05f;
    private Random rng;
    private long serialNumber;

    public EngineProvider(ChannelOutput out, long baseSerial)
    {
      this.out = out;
      rng = new Random(System.currentTimeMillis()+1234l);
      serialNumber = baseSerial;
    }

	@Override
	public void run() {
		while (true) {
			out.write(new Engine(rng.nextFloat() <= DEFECTIVE, serialNumber++));
		}
		
	}

}
