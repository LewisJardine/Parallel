package leancarbuilder.workstation;

import org.jcsp.lang.*;

import leancarbuilder.part.CanBeDefective;

public class EngineFilter extends Worker implements CSProcess {

    private ChannelInput input;
    private ChannelOutput output;

	public EngineFilter(ChannelInput in, ChannelOutput out)
	{
		super(2);
		input = in;
		output = out;
	}
	
	
	@Override
	public void run() {
		while(true) {
			CanBeDefective engine = (CanBeDefective) input.read();
			doWork();
			if (engine.isDefective()) {
				System.out.println("Defective Engine"); }
			else {
				output.write(engine);
				//System.out.println("Good Engine"); 
			}
		}

	}

}
