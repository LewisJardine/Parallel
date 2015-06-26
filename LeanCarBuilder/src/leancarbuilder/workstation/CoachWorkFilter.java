package leancarbuilder.workstation;

import org.jcsp.lang.*;

import leancarbuilder.part.CoachWork;

public class CoachWorkFilter extends Worker implements CSProcess {

    private ChannelInput input;
    private ChannelOutput output;

	public CoachWorkFilter(ChannelInput in, ChannelOutput out)
	{
		super(5);
		input = in;
		output = out;
	}
	
	
	@Override
	public void run() {
		while(true) {
			CoachWork coach = (CoachWork) input.read();
			doWork();
			if (coach.isDefective()) {
				System.out.println("Defective Coachworks"); }
			else {
				//System.out.println("Good Coachworks"); 
				output.write(coach);
			}
		}

	}

}
