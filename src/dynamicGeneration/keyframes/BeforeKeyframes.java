package dynamicGeneration.keyframes;

import dynamicGeneration.structures.AnimatedElement;
import dynamicGeneration.structures.Edge;
import dynamicGeneration.structures.KeyFrames;

public class BeforeKeyframes {
	public static KeyFrames beforeKeyframes(AnimatedElement ae){
		int borderWidth = ae.borderWidth;
		String edges = ae.edges;
		String rotationDirection = ae.rotationDirection;
		
		String name = KeyFrames.keyframesName(ae) + "-before";
		
		char first = Edge.get(edges.charAt(0)).prev(rotationDirection).letterName();
		char last = Edge.get(edges.charAt(edges.length()-1)).next(rotationDirection).letterName();
		
		edges = first + edges + last;
		
		KeyFrames frames = new KeyFrames(name);
		
		frames.add(0.0, "border-width", "0 0 0 0");
		
	
		for (int i = 0; i < edges.length() - 2; i++){
			Edge e0 = Edge.get(edges.charAt(i));
			Edge e1 = Edge.get(edges.charAt(i+1));
			buildBeforeCornerKeyframe(frames, rotationDirection, e0, e1, i, edges.length()-2, borderWidth);
		}
		
		buildBeforePositionProperties(frames, 100.0, borderWidth, Edge.get(edges.charAt(edges.length()-2)), Edge.get(edges.charAt(edges.length()-1)));
		frames.add(100.0, "border-width", "0 0 0 0");
		frames.add(100.0, "height", borderWidth + "px");
		frames.add(100.0, "width", borderWidth + "px");
		
		return frames;
	}

	public static void buildBeforePositionProperties(KeyFrames frames, double percentage, int borderWidth, Edge corner0, Edge corner1){
		frames.add(percentage, corner0.fullName(), "-"+borderWidth+"px");
		frames.add(percentage, corner1.fullName(), "-"+borderWidth+"px");
		frames.add(percentage, corner0.opposite().fullName(), "auto");
		frames.add(percentage, corner1.opposite().fullName(), "auto");
	}
	
	public static double buildBeforePercentage(int num, int denom, boolean roundDown){
		double d = Math.round(9800 * num / denom) / 100.0 + 1;
		d = roundDown? d -= .01 : d; 
		return d;
	}
	
	public static void buildBeforeCornerKeyframe(KeyFrames frames, String rotationType, Edge e0, Edge e1, int index, int denom, int borderWidth){
		
		double percentageOne = buildBeforePercentage(index, denom, false);
		double percentageTwo = buildBeforePercentage(index + 1, denom, true);
		
		buildBeforePositionProperties(frames, percentageOne, borderWidth, e0, e1);
		buildBeforePositionProperties(frames, percentageTwo, borderWidth, e0, e1);
		
		frames.add(percentageOne, "height", "0");
		frames.add(percentageOne, "width", "0");
		if (percentageOne < 5){
			frames.add(percentageOne, "border-width", String.format("%dpx %dpx 0 0", borderWidth, borderWidth));
		}
		if (percentageTwo > 95){
			frames.add(percentageTwo, "border-width", String.format("%dpx %dpx 0 0", borderWidth, borderWidth));
		}
		if (e0 == Edge.T || e0 == Edge.B){
			frames.add(percentageTwo, "width", "0");
			frames.add(percentageTwo, "height", "100%");
		} else {
			frames.add(percentageTwo, "width", "100%");
			frames.add(percentageTwo, "height", "0");
		}
	}
}
