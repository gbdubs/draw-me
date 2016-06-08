package dynamicGeneration;

import java.util.ArrayList;
import java.util.List;

public class Page {

	List<AnimatedElement> elements;
	
	public Page (String s){
		String[] elementDescriptions = s.split("\n");
		elements = new ArrayList<AnimatedElement>();
		for (String description : elementDescriptions){
			elements.add(new AnimatedElement(description));
		}
	}
	
	public String generateCss(){
		List<CssProp> props = GeneralProperties.all();
		props.addAll(InheritableProperties.all(this));
		
		
		String css = CssProp.allPropsToCss(props);
		return css;
	}
	
	public String generateMinimalCss(){
		String css = generateCss();
		return Minimization.minimize(css);
	}
	
}
