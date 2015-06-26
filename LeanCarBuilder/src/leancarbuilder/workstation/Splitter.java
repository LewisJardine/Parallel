package leancarbuilder.workstation;

import org.jcsp.lang.*;

public class Splitter implements CSProcess {

    private ChannelInput input;
    private ChannelOutput[] output;

	public Splitter(ChannelInput in, One2OneChannel[] out)
	{
		input = in;
		output = new ChannelOutput[out.length];
		for (int i = 0 ; i<output.length ; i++) {
			output[i] = out[i].out();
		}
	}
	
	
	@Override
	public void run() {
		while(true) {
			int chan = (int) Math.floor(output.length*Math.random());
			output[chan].write(input.read());
		}
	}

}
