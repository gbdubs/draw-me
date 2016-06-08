package dynamicGeneration;

import java.util.ArrayList;
import java.util.List;

import dynamicGeneration.structures.CssProp;

public class GeneralProperties {

	public static List<CssProp> all(){
		List<CssProp> rules = new ArrayList<CssProp>();
		
		rules.add(new CssProp(".dm", "position", "relative"));
		rules.add(new CssProp(".dm:before", "content", "''"));
		rules.add(new CssProp(".dm:before", "display", "block"));
		rules.add(new CssProp(".dm:before", "position", "absolute"));
		rules.add(new CssProp(".dm:before", "z-index", "1"));
		rules.add(new CssProp(".dm:after", "display", "block"));
		rules.add(new CssProp(".dm:after", "content", "''"));
		rules.add(new CssProp(".dm:after", "position", "absolute"));
		
		return rules;
	}
	
}
