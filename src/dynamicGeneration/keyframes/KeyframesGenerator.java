package dynamicGeneration.keyframes;

import java.util.ArrayList;
import java.util.List;

import dynamicGeneration.structures.AnimatedElement;
import dynamicGeneration.structures.Edge;
import dynamicGeneration.structures.KeyFrames;
import dynamicGeneration.structures.Page;

public class KeyframesGenerator {

	public static String beforeKeyframesName(AnimatedElement ae){
		return String.format("dm-%s-%s-%d-before", ae.rotationDirection, ae.edges, ae.borderWidth);
	}

	public static String elementKeyframesName(AnimatedElement ae){
		if (ae.shouldFade){
			return String.format("dm-%s-%s-%s-%s-%s-%s-%s", ae.rotationDirection, ae.edges, ae.borderColor, ae.fadeBackgroundFromColor, ae.fadeBackgroundToColor, ae.fadeTextFromColor, ae.fadeTextToColor);
		} else {
			return String.format("dm-%s-%s-%s", ae.rotationDirection, ae.edges, ae.borderColor);
		}
	}
	
	public static String afterKeyframesName(AnimatedElement ae){
		Edge e1 = Edge.get(ae.edges.charAt(0));
		Edge e0 = e1.prev(ae.rotationDirection);
		String corner = "" + e0.letterName()+e1.letterName();
		return String.format("dm-%s-%d-after", corner, ae.borderWidth);
	}
	
	public static List<KeyFrames> all(Page p){
		List<KeyFrames> keyframes = new ArrayList<KeyFrames>();
		for (AnimatedElement ae : p.elements){
			keyframes.add(AfterKeyframes.afterKeyframes(ae));
			keyframes.add(BeforeKeyframes.beforeKeyframes(ae));
			keyframes.add(ElementKeyframes.elementKeyframes(ae));
		}
		return keyframes;
	}
	
}
