package dynamicGeneration.structures;

import static jodd.jerry.Jerry.jerry;

import java.util.ArrayList;
import java.util.List;

import jodd.jerry.Jerry;
import dynamicGeneration.AnimationSelectorProperties;
import dynamicGeneration.GeneralProperties;
import dynamicGeneration.InheritableProperties;
import dynamicGeneration.keyframes.KeyframesGenerator;
import dynamicGeneration.util.Minimization;

public class Page {

	public List<AnimatedElement> elements;
	public Jerry doc;
	
	public Page (String html){
		doc = jerry(html);
		String s = DOMParsing.findAnimatedElementsDefinition(doc);
		String[] elementDescriptions = s.split("\n");
		elements = new ArrayList<AnimatedElement>();
		for (String description : elementDescriptions){
			elements.add(new AnimatedElement(description));
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

		props.addAll(AnimationSelectorProperties.all(this));
		
		List<KeyFrames> keyframes = KeyframesGenerator.all(this);
		
		List<CssProp> usefulProps = new ArrayList<CssProp>();
		for (CssProp cp : props){
			if (selectorIsHelpful(cp.selector)){
				usefulProps.add(cp);
			}
		}

		System.out.printf("PROPS SIZE %d\n", usefulProps.size());
		String propCss = CssProp.allPropsToCss(usefulProps);
		String keyframeCss = KeyFrames.keyframesToCSS(keyframes);
		
		return propCss + keyframeCss;
	}
	
	public String generateMinimalCss(){
		String css = generateCss();
		return Minimization.minimize(css);
	}
	
}
