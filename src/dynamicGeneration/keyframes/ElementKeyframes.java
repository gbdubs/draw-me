package dynamicGeneration.keyframes;

import dynamicGeneration.structures.AnimatedElement;
import dynamicGeneration.structures.Edge;
import dynamicGeneration.structures.KeyFrames;
import dynamicGeneration.util.ColorUtility;

public class ElementKeyframes {
	
	public static double percentage(int numerator, int denom, boolean goDownOne){
		double d = Math.round(numerator * 9800.0 / denom) / 100.0;
		d = goDownOne ? d - .01 : d;
		return d;
	}
	
	private static final double FADE_IN_AT = 70.0;
	public static void addFadeIn(KeyFrames frames, AnimatedElement ae){
		frames.add(0.0, "color", ColorUtility.toHexCode(ae.fadeTextFromColor));
		frames.add(FADE_IN_AT, "color", ColorUtility.toHexCode(ae.fadeTextFromColor));
		frames.add(100.0, "color", ColorUtility.toHexCode(ae.fadeTextToColor));
		
		frames.add(0.0, "background-color", ColorUtility.toHexCode(ae.fadeBackgroundFromColor));
		frames.add(FADE_IN_AT, "background-color", ColorUtility.toHexCode(ae.fadeBackgroundFromColor));
		frames.add(100.0, "background-color", ColorUtility.toHexCode(ae.fadeBackgroundToColor));
	}
	
	public static void addProperty(KeyFrames frames, int index, int denom, String property, String value){
		frames.add(percentage(index, denom, false), property, value);
		if (index < denom){
			frames.add(percentage(index + 1, denom, true), property, value);
		}
	}
	
	public static KeyFrames elementKeyframes(AnimatedElement ae){
		String edges = ae.edges;
		int denom = edges.length();
		String borderColor = ColorUtility.toHexCode(ae.borderColor);
		
		String keyframesName = KeyframesGenerator.elementKeyframesName(ae);
		KeyFrames frames = new KeyFrames(keyframesName);
		
		if (ae.shouldFade){
			addFadeIn(frames, ae);
		}
		
		for (int index = 0; index <= edges.length(); index++){
			if (index > 0){
				String property = "border-" + Edge.get(edges.charAt(index-1)).fullName() + "-color";
				addProperty(frames, index, denom, property, borderColor);
			}
			if (index < denom){
				String property = "border-" + Edge.get(edges.charAt(index)).fullName() + "-color";
				addProperty(frames, index, denom, property, "transparent");
			}
			if (index == denom){
				for (int j = 0;  j < index-1; j++){
					String property = "border-" + Edge.get(edges.charAt(j)).fullName() + "-color";
					addProperty(frames, index, denom, property, borderColor);
				}
			}
			if (index == 0){
				for (int j = 1;  j < edges.length(); j++){
					String property = "border-" + Edge.get(edges.charAt(j)).fullName() + "-color";
					addProperty(frames, index, denom, property, "transparent");
				}
			}
		}
		
		for (Edge e : Edge.all){
			if (!edges.contains(""+e.letterName())){
				frames.add(0.0, "border-"+e.fullName()+"-color", "transparent");
				frames.add(100.0, "border-"+e.fullName()+"-color", "transparent");
			}
		}
		
		return frames;
	}
	
}
