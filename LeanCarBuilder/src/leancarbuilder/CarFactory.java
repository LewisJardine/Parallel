package leancarbuilder;

import java.awt.Color;

import org.jcsp.lang.*;
import org.jcsp.plugNplay.Plex;
import org.jcsp.util.Buffer;

import leancarbuilder.workstation.*;

public class CarFactory {

	public static void main(String[] args) {
		StandardChannelFactory channelFactory = StandardChannelFactory.getDefaultInstance();
		One2OneChannel engineSupply = channelFactory.createOne2One(new Buffer(10));
		One2OneChannel coachworksSupply = channelFactory.createOne2One(new Buffer(10));
		One2OneChannel wheelSupply = channelFactory.createOne2One(new Buffer(40));
		One2OneChannel engines = channelFactory.createOne2One(new Buffer(3));
		One2OneChannel coachwork = channelFactory.createOne2One(new Buffer(3));
		One2OneChannel wheels = channelFactory.createOne2One(new Buffer(3));
		One2OneChannel assemblies = channelFactory.createOne2One(new Buffer(10));
		One2OneChannel[] toPaint = channelFactory.createOne2One(new Buffer(3), 3);
		One2OneChannel[] painted = channelFactory.createOne2One(new Buffer(3), 3);
		One2OneChannel finished = channelFactory.createOne2One(new Buffer(10));
		
		new Parallel
		(
			new CSProcess[] {	
				new EngineProvider(engineSupply.out(), 20000000l),
				new EngineFilter(engineSupply.in(), engines.out()),
				new CoachWorkProvider(coachworksSupply.out(), 40000000l),
				new CoachWorkFilter(coachworksSupply.in(), coachwork.out()),
				new WheelProvider(wheelSupply.out(), 60000000l),
				new WheelFilter(wheelSupply.in(), wheels.out()),
				new CarAssembly(engines.in(), coachwork.in(), wheels.in(), assemblies.out(), 80000000l),
				new Splitter(assemblies.in(), toPaint),
				new Painter(Color.RED, toPaint[0].in(), painted[0].out()),	
				new Painter(Color.GREEN, toPaint[1].in(), painted[1].out()),	
				new Painter(Color.BLUE, toPaint[2].in(), painted[2].out()),
				new Plex(Channel.getInputArray(painted),finished.out()),
				new Consumer(finished.in())
			}
		).run();
	}

}
