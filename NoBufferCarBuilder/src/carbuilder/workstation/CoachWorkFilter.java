package carbuilder.workstation;

import org.jcsp.lang.*;

import carbuilder.part.CoachWork;

public class CoachWorkFilter implements CSProcess {

    private ChannelInput input;
    private ChannelOutput output;

	public CoachWorkFilter(ChannelInput in, ChannelOutput out)
	{
		input = in;
		output = out;
	}
	
	
	@Override
	public void run() {
		while(true) {
			CoachWork coach = (CoachWork) input.read();
			if (coach.isDefective()) {
				System.out.println("Defective Coachworks"); }
			else {
				System.out.println("Good Coachworks"); 
				output.write(coach);
			}
		}

	}

}
