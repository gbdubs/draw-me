package dynamicGeneration.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static List<CssRule> condense(List<CssProp> properties){
		List<CssRule> result = new ArrayList<CssRule>();
		if (properties.size() == 0){
			return result;
		}
		Collections.sort(properties);
		List<CssProp> running = new ArrayList<CssProp>();
		running.add(properties.get(0));
		int i = 1;
		while (i < properties.size()){
			while (i < properties.size() && properties.get(i).compareTo(running.get(0)) == 0){
				running.add(properties.get(i));
				i++;
			}
			if (i < properties.size()){
				result.add(new CssRule(running));
				running.clear();
				running.add(properties.get(i));
				i++;
			}
		}
		result.add(new CssRule(running));
		return result;
	}

	@Override
	public int compareTo(CssProp that) {
		return this.selector.compareTo(that.selector);
	}
	
	private static class CssRule implements Comparable<CssRule>{

		String selector;
		Map<String, String> properties;
		String body;
		
		public CssRule (List<CssProp> rules){
			selector = rules.get(0).selector;
			properties = new HashMap<String, String>();
			for (CssProp cp : rules){
				if (!selector.equals(cp.selector)){
					throw new RuntimeException("A CSSRules was created with different selectors!");
				}
				if (cp.property.contains("animation")){
					properties.put("-moz-"+cp.property, cp.value);
					properties.put("-webkit-"+cp.property, cp.value);
				}
				properties.put(cp.property, cp.value);
			}
			this.body = getBody();
		}
		
		public String getBody(){
			StringBuilder body = new StringBuilder();
			List<String> props = new ArrayList<String>(properties.keySet());
			Collections.sort(props);
			for(String prop : props){
				body.append(String.format("\t%s: %s;\n", prop, properties.get(prop)));
			}
			return body.toString();
		}
		
		public String toString(){ 
			return String.format("%s {\n%s}\n", selector, body);
		}

		@Override
		public int compareTo(CssRule that) {
			return this.body.compareTo(that.body);
		}
		
		public CssRule merge(CssRule that){
			this.selector = this.selector + ",\n" + that.selector;
			if (!this.body.equals(that.body)){
				throw new RuntimeException("Two CSS Rules tried to merge that should not have.");
			}
			
			return this;
		}
		
		public static List<CssRule> condense(List<CssRule> rules){

			List<CssRule> result = new ArrayList<CssRule>();
			Collections.sort(rules);
			CssRule last = rules.get(0);
			for (int i = 1; i < rules.size(); i++){
				if (last.compareTo(rules.get(i)) == 0){
					last = last.merge(rules.get(i));
				} else {
					result.add(last);
					last = rules.get(i);
				}
			}
			result.add(last);
			
			return result;
		}
	}
}
