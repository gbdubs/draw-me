package dynamicGeneration.structures;

import static jodd.jerry.Jerry.jerry;

import java.util.ArrayList;
import java.util.List;

import jodd.jerry.Jerry;
import dynamicGeneration.AnimationSelectorProperties;
import dynamicGeneration.GeneralProperties;
import dynamicGeneration.InheritableProperties;
import dynamicGeneration.keyframes.AfterKeyframes;
import dynamicGeneration.keyframes.BeforeKeyframes;
import dynamicGeneration.keyframes.ElementKeyframes;
import dynamicGeneration.util.Minimization;

public class Page {

	public List<AnimatedElement> elements;
	public Jerry doc;
	
	public Page (String s, String html){
		doc = jerry(html);
		
		String[] elementDescriptions = s.split("\n");
		elements = new ArrayList<AnimatedElement>();
		for (String description : elementDescriptions){
			elements.add(new AnimatedElement(this, description));
		}
	}
	
	public boolean selectorIsHelpful(String selector){
		selector = selector.replace(":before", "");
		selector = selector.replace(":after", "");
		if (doc.$(selector).size() > 0){
			return true;
		}
		return false;
	}

	public String generateCss(){
		List<CssProp> props = GeneralProperties.all();
		props.addAll(GeneralProperties.all());
		props.addAll(InheritableProperties.all(this));
		
		List<KeyFrames> keyframes = new ArrayList<KeyFrames>();
		for (AnimatedElement ae : elements){
			
			for (String s : AnimationSelectorProperties.generateAnimationSelectors(ae)){
				props.add(new CssProp(s, "animation-name", KeyFrames.elementKeyframesName(ae)));
				props.add(new CssProp(s+":before", "animation-name", KeyFrames.beforeKeyframesName(ae)));
				props.add(new CssProp(s+":after", "animation-name", KeyFrames.afterKeyframesName(ae)));
			}
			
			keyframes.add(AfterKeyframes.afterKeyframes(ae));
			keyframes.add(BeforeKeyframes.beforeKeyframes(ae));
			keyframes.add(ElementKeyframes.elementKeyframes(ae));
		}
		
		List<CssProp> usefulProps = new ArrayList<CssProp>();
		for (CssProp cp : props){
			if (selectorIsHelpful(cp.selector)){
				usefulProps.add(cp);
			}
		}
		
		String propCss = CssProp.allPropsToCss(usefulProps);
		String keyframeCss = KeyFrames.keyframesToCSS(keyframes);
		
		return propCss + keyframeCss;
	}
	
	public String generateMinimalCss(){
		String css = generateCss();
		return Minimization.minimize(css);
	}
	
}
