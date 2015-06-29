package leancarbuilder;

import org.jcsp.lang.*;
import org.jcsp.plugNplay.Plex;

import leancarbuilder.workstation.*;

/**
 * Zero buffering produces: 
 * ~77 cars per minute on battery (CPU step-down)
 * ~ cars per minute on mains
 */
public class ZeroBufferedCarFactory {

	public static void main(String[] args) {
		StandardChannelFactory channelFactory = StandardChannelFactory.getDefaultInstance();
		One2OneChannel engineSupply = channelFactory.createOne2One();
		One2OneChannel coachworksSupply = channelFactory.createOne2One();
		One2OneChannel wheelSupply = channelFactory.createOne2One();
		One2OneChannel engines = channelFactory.createOne2One();
		One2OneChannel coachwork = channelFactory.createOne2One();
		One2OneChannel wheels = channelFactory.createOne2One();
		One2OneChannel assemblies = channelFactory.createOne2One();
		One2OneChannel[] toPaint = channelFactory.createOne2One(3);
		One2OneChannel[] painted = channelFactory.createOne2One(3);
		One2OneChannel finished = channelFactory.createOne2One();
		
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
				new Painter(Painter.RED, toPaint[0].in(), painted[0].out()),	
				new Painter(Painter.GREEN, toPaint[1].in(), painted[1].out()),	
				new Painter(Painter.BLUE, toPaint[2].in(), painted[2].out()),
				new Plex(Channel.getInputArray(painted),finished.out()),
				new Consumer(finished.in())
			}
		).run();
	}

}

