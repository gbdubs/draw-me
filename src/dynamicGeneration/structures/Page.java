package dynamicGeneration.structures;

import java.util.ArrayList;
import java.util.List;

import dynamicGeneration.GeneralProperties;
import dynamicGeneration.InheritableProperties;
import dynamicGeneration.keyframes.AfterKeyframes;
import dynamicGeneration.keyframes.BeforeKeyframes;
import dynamicGeneration.keyframes.ElementKeyframes;
import dynamicGeneration.util.ColorUtility;
import dynamicGeneration.util.Minimization;

public class Page {

	public List<AnimatedElement> elements;
	
	public Page (String s){
		String[] elementDescriptions = s.split("\n");
		elements = new ArrayList<AnimatedElement>();
		for (String description : elementDescriptions){
			elements.add(new AnimatedElement(description));
		}
	}
	
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
	
	public static List<String> generateAnimationSelectors(AnimatedElement ae){
		List<String> result = new ArrayList<String>();
		String base = String.format(".dm.dm-%s-%s", ae.rotationDirection, ae.edges);
		List<String> temp = new ArrayList<String>();
		temp.add(base);
		result.addAll(considerBorderWidth(considerShouldFade(temp, ae), ae));
		result.addAll(considerShouldFade(considerBorderWidth(temp, ae), ae));
		return result;
	}
	
	public String generateCss(){
		List<CssProp> props = GeneralProperties.all();
		props.addAll(GeneralProperties.all());
		props.addAll(InheritableProperties.all(this));
		
		List<KeyFrames> keyframes = new ArrayList<KeyFrames>();
		for (AnimatedElement ae : elements){
			String keyframesName = KeyFrames.keyframesName(ae);
			
			for (String s : generateAnimationSelectors(ae)){
				props.add(new CssProp(s, "animation-name", keyframesName));
				props.add(new CssProp(s+":before", "animation-name", keyframesName+"-before"));
				props.add(new CssProp(s+":after", "animation-name", keyframesName+"-after"));
			}
			
			keyframes.add(AfterKeyframes.afterKeyframes(ae));
			keyframes.add(BeforeKeyframes.beforeKeyframes(ae));
			keyframes.add(ElementKeyframes.elementKeyframes(ae));
		}
		String propCss = CssProp.allPropsToCss(props);
		String keyframeCss = KeyFrames.keyframesToCSS(keyframes);
		
		return propCss + keyframeCss;
	}
	
	public String generateMinimalCss(){
		String css = generateCss();
		return Minimization.minimize(css);
	}
	
}
