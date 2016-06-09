package dynamicGeneration.keyframes;

import dynamicGeneration.structures.AnimatedElement;
import dynamicGeneration.structures.Edge;
import dynamicGeneration.structures.KeyFrames;

public class AfterKeyframes {

	public static KeyFrames afterKeyframes(AnimatedElement ae){
		int borderWidth = ae.borderWidth;
		String edges = ae.edges;
		String rotationDirection = ae.rotationDirection;
		
		Edge e1 = Edge.get(edges.charAt(0));
		Edge e0 = Edge.prev(rotationDirection, e1);
		
		String name = KeyFrames.afterKeyframesName(ae);
		
		KeyFrames frames = new KeyFrames(name);
		
		//frames.add(0.0, "display", "none"); //TODO NESCESSARY?
		frames.add(0.0, "height", "0");
		frames.add(0.0, "width", "0");
		
		//frames.add(1.0, "display", "block"); //TODO NESCESSARY?
		frames.add(1.0, "height", borderWidth+"px");
		frames.add(1.0, "width", borderWidth+"px");
		frames.add(1.0, e0.fullName(), "-"+borderWidth+"px");
		frames.add(1.0, e1.fullName(), "-"+borderWidth+"px");
		frames.add(1.0, e0.opposite().fullName(), "auto");
		frames.add(1.0, e1.opposite().fullName(), "auto");
		
		//frames.add(100.0, "display", "block"); //TODO NESCESSARY?
		frames.add(100.0, "height", borderWidth+"px");
		frames.add(100.0, "width", borderWidth+"px");
		frames.add(100.0, e0.fullName(), "-"+borderWidth+"px");
		frames.add(100.0, e1.fullName(), "-"+borderWidth+"px");
		frames.add(100.0, e0.opposite().fullName(), "auto");
		frames.add(100.0, e1.opposite().fullName(), "auto");
		
		return frames;
	}
}
