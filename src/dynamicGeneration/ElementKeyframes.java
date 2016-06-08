package dynamicGeneration;

import java.util.ArrayList;
import java.util.List;

public class ElementKeyframes {

	public static List<KeyFrames> generateKeyframes(AnimatedElement ae){
		List<KeyFrames> result = new ArrayList<KeyFrames>();
		result.add(ElementKeyframes.elementKeyframes(ae));
		result.add(BeforeKeyframes.beforeKeyframes(ae));
		result.add(AfterKeyframes.afterKeyframes(ae));
		return result;
	}
	
	public static double percentage(int numerator, int denom, boolean goDownOne){
		double d = Math.round(numerator * 9800.0 / denom) / 100.0;
		d = goDownOne ? d - .01 : d;
		return d;
	}
	
	private static final double FADE_IN_AT = 70.0;
	public static void addFadeIn(KeyFrames frames, AnimatedElement ae){
		frames.add(0.0, "color", ae.fadeTextFromColor);
		frames.add(FADE_IN_AT, "color", ae.fadeTextFromColor);
		frames.add(100.0, "color", ae.fadeTextToColor);
		
		frames.add(0.0, "background-color", ae.fadeBackgroundFromColor);
		frames.add(FADE_IN_AT, "background-color", ae.fadeBackgroundFromColor);
		frames.add(100.0, "background-color", ae.fadeBackgroundToColor);
	}
	
	public static KeyFrames elementKeyframes(AnimatedElement ae){
		String edges = ae.edges;
		int denom = edges.length();
		String borderColor = ae.borderColor;
		
		String keyframesName = KeyFrames.keyframesName(ae);
		KeyFrames frames = new KeyFrames(keyframesName);
		
		if (ae.shouldFade){
			addFadeIn(frames, ae);
		}
		
		for (int index = 0; index <= edges.length(); index++){
			if (index > 0){
				String property = "border-" + Edge.get(edges.charAt(index-1)).fullName();
				frames.add(percentage(index, denom, false), property, borderColor);
				frames.add(percentage(index + 1, denom, true), property, borderColor);
			}
			if (index < denom){
				String property = "border-" + Edge.get(edges.charAt(index)).fullName();
				frames.add(percentage(index, denom, false), property, "transparent");
				frames.add(percentage(index + 1, denom, true), property, "transparent");
			}
			if (index == denom){
				for (int j = 0;  j < index-1; j++){
					String property = "border-" + Edge.get(edges.charAt(j)).fullName();
					frames.add(percentage(index, denom, false), property, borderColor);
					frames.add(percentage(index + 1, denom, true), property, borderColor);
				}
			}
			if (index == 0){
				for (int j = 1;  j < edges.length(); j++){
					String property = "border-" + Edge.get(edges.charAt(j)).fullName();
					frames.add(percentage(index, denom, false), property, borderColor);
					frames.add(percentage(index + 1, denom, true), property, "transparent");
				}
			}
		}
		
		return frames;
	}
	
}
