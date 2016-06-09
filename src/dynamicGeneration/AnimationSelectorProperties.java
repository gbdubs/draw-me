package dynamicGeneration;

import java.util.ArrayList;
import java.util.List;

import dynamicGeneration.keyframes.KeyframesGenerator;
import dynamicGeneration.structures.AnimatedElement;
import dynamicGeneration.structures.CssProp;
import dynamicGeneration.structures.Page;
import dynamicGeneration.util.ColorUtility;

public class AnimationSelectorProperties {
	private static List<String> considerShouldFade(List<String> selectors, AnimatedElement ae){
		List<String> result = new ArrayList<String>();
		if (!ae.shouldFade){
			for (String s : selectors){
				result.add(".dm-fade-none "+s);
				result.add(s+".dm-fade-none");
			}
		} else {
			String fadeString = String.format(
					".dm-fade-%s-%s-%s-%s", 
					ColorUtility.toName(ae.fadeBackgroundFromColor),
					ColorUtility.toName(ae.fadeBackgroundToColor),
					ColorUtility.toName(ae.fadeTextFromColor),
					ColorUtility.toName(ae.fadeTextToColor)
			);
			if (ae.fadeBackgroundFromColor.equals("transparent") 
				&& ae.fadeBackgroundToColor.equals("transparent")
				&& ae.fadeTextFromColor.equals("transparent")
				&& ae.fadeTextToColor.equals("000000")
			){
				for (String s : selectors){
					result.add(s);
				}
			}
			for (String s : selectors){
				result.add(fadeString + " " + s);
				result.add(s+fadeString);
			}
		}
		return result;
	}
	
	private static List<String> considerBorderWidth(List<String> selectors, AnimatedElement ae){
		List<String> result = new ArrayList<String>();
		if (ae.borderWidth == 1){
			result.addAll(selectors);
		}
		for (String s : selectors){
			result.add(s + ".dm-border-width-" + ae.borderWidth);
			result.add(".dm-border-width-"+ae.borderWidth+" " + s);
		}
		return result;
	}
	
	public static List<String> considerBorderColor(List<String> selectors, AnimatedElement ae){
		List<String> result = new ArrayList<String>();
		if (ae.borderColor.equals("black") || ae.borderColor.equals("000000")){
			result.addAll(selectors);
		}
		for (String s : selectors){
			result.add(s + ".dm-border-color-" + ae.borderColor);
			result.add(".dm-border-color-"+ae.borderColor+" " + s);
		}
		return result;
	}
	
	public static List<String> generateAnimationSelectors(AnimatedElement ae){
		List<String> result = new ArrayList<String>();
		String base = String.format(".dm.dm-%s-%s", ae.rotationDirection, ae.edges);
		List<String> temp = new ArrayList<String>();
		temp.add(base);
		result.addAll(considerShouldFade(considerBorderWidth(considerBorderColor(temp, ae), ae), ae));
		result.addAll(considerShouldFade(considerBorderColor(considerBorderWidth(temp, ae), ae), ae));
		result.addAll(considerBorderWidth(considerShouldFade(considerBorderColor(temp, ae), ae), ae));
		result.addAll(considerBorderColor(considerShouldFade(considerBorderWidth(temp, ae), ae), ae));
		result.addAll(considerBorderWidth(considerBorderColor(considerShouldFade(temp, ae), ae), ae));
		result.addAll(considerBorderColor(considerBorderWidth(considerShouldFade(temp, ae), ae), ae));
		return result;
	}
	
	public static List<CssProp> all(Page p){
		List<CssProp> props = new ArrayList<CssProp>();
		for (AnimatedElement ae : p.elements){
			for (String s : generateAnimationSelectors(ae)){
				props.add(new CssProp(s, "animation-name", KeyframesGenerator.elementKeyframesName(ae)));
				props.add(new CssProp(s+":before", "animation-name", KeyframesGenerator.beforeKeyframesName(ae)));
				props.add(new CssProp(s+":after", "animation-name", KeyframesGenerator.afterKeyframesName(ae)));
			}
		}
		return props;
	}
	
}
