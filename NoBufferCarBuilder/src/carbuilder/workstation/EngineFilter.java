package carbuilder.workstation;

import org.jcsp.lang.*;

import carbuilder.part.CanBeDefective;

public class EngineFilter implements CSProcess {

    private ChannelInput input;
    private ChannelOutput output;

	public EngineFilter(ChannelInput in, ChannelOutput out)
	{
		input = in;
		output = out;
	}
	
	
	@Override
	public void run() {
		while(true) {
			CanBeDefective engine = (CanBeDefective) input.read();
			if (engine.isDefective()) {
				System.out.println("Defective Engine"); }
			else {
				output.write(engine);
				System.out.println("Good Engine"); 
			}
		}

	}

}
