package dynamicGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CssProp implements Comparable<CssProp> {

	public String selector;
	public String property;
	public String value;
	
	public CssProp(String s, String p, String v){
		this.selector = s;
		this.property = p;
		this.value = v;
	}
	
	public String toString(){
		return String.format("%s {\n\t%s: %s;\n}\n", selector, property, value);
	}
	
	public static String allPropsToCss(List<CssProp> rules){
		List<CssRule> condensed = CssProp.condense(rules);
		condensed = CssRule.condense(condensed);
		StringBuilder sb = new StringBuilder();
		for(CssRule crs : condensed){
			sb.append(crs.toString());
		}
		return sb.toString();
	}
	
	public static List<CssRule> condense(List<CssProp> rules){
		List<CssRule> result = new ArrayList<CssRule>();
		if (rules.size() == 0){
			return result;
		}
		Collections.sort(rules);
		List<CssProp> running = new ArrayList<CssProp>();
		running.add(rules.get(0));
		int i = 1;
		while (i < rules.size()){
			while (rules.get(i).compareTo(running.get(0)) == 0){
				running.add(rules.get(i));
				i++;
			}
			result.add(new CssRule(running));
			running.clear();
			running.add(rules.get(i));
			i++;
		}
		result.add(new CssRule(running));
		return result;
	}

	@Override
	public int compareTo(CssProp that) {
		return this.selector.compareTo(that.selector);
	}
	
}
