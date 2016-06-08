package dynamicGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CssRule implements Comparable<CssRule>{

	String selector;
	Map<String, String> properties;
	
	public CssRule (List<CssProp> rules){
		selector = rules.get(0).selector;
		properties = new HashMap<String, String>();
		for (int i = 1; i < rules.size(); i++){
			if (!selector.equals(rules.get(i).selector)){
				throw new RuntimeException("A CSSRules was created with different selectors!");
			}
			properties.put(rules.get(i).property, rules.get(i).value);
		}
	}
	
	public String toString(){
		StringBuilder body = new StringBuilder();
		List<String> props = new ArrayList<String>(properties.keySet());
		Collections.sort(props);
		for(String prop : props){
			body.append(String.format("\t%s: %s;\n", prop, properties.get(prop)));
		}
		return String.format("%s {\n%s}\n", selector, body.toString());
	}

	@Override
	public int compareTo(CssRule that) {
		return this.toString().compareTo(that.toString());
	}
	
	public static CssRule merge(CssRule a, CssRule b){
		a.selector = a.selector + ",\n" + b.selector;
		return a;
	}
	
	public static List<CssRule> condense(List<CssRule> rules){
		List<CssRule> result = new ArrayList<CssRule>();
		Collections.sort(rules);
		CssRule last = rules.get(0);
		for (int i = 1; i < rules.size(); i++){
			if (last.compareTo(rules.get(i)) == 0){
				last = merge(last, rules.get(i));
			} else {
				result.add(last);
				last = rules.get(i);
			}
		}
		result.add(last);
		return result;
	}
}
