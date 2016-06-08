package dynamicGeneration.structures;

import java.util.ArrayList;
import java.util.List;

import dynamicGeneration.GeneralProperties;
import dynamicGeneration.InheritableProperties;
import dynamicGeneration.keyframes.AfterKeyframes;
import dynamicGeneration.keyframes.BeforeKeyframes;
import dynamicGeneration.keyframes.ElementKeyframes;
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
	
	public String generateCss(){
		List<CssProp> props = GeneralProperties.all();
		props.addAll(GeneralProperties.all());
		props.addAll(InheritableProperties.all(this));
		String propCss = CssProp.allPropsToCss(props);
		
		List<KeyFrames> keyframes = new ArrayList<KeyFrames>();
		for (AnimatedElement ae : elements){
			keyframes.add(AfterKeyframes.afterKeyframes(ae));
			keyframes.add(BeforeKeyframes.beforeKeyframes(ae));
			keyframes.add(ElementKeyframes.elementKeyframes(ae));
		}
		String keyframeCss = KeyFrames.keyframesToCSS(keyframes);
		
		return propCss + keyframeCss;
	}
	
	public String generateMinimalCss(){
		String css = generateCss();
		return Minimization.minimize(css);
	}
	
}
