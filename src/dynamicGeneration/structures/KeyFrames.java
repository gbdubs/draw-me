package dynamicGeneration.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyFrames implements Comparable<KeyFrames>{

	public String name;
	public Map<Double, Frame> frames;
	
	public KeyFrames(String name){
		this.name = name;
		this.frames = new HashMap<Double, Frame>();
	}
	
	public void add(Double percentage, String property, String value){
		Frame f;
		if (frames.containsKey(percentage)){
			f = frames.get(percentage);
		} else {
			f = new Frame(percentage);
			frames.put(percentage, f);
		}
		f.addProperty(property, value);
	}
	
	public String toString(){
		List<Frame> frameList = Frame.minimalFrames(new ArrayList<Frame>(frames.values()));
		
		List<String> frameStrings = new ArrayList<String>();
		for (Frame f : frameList){
			frameStrings.add(f.toString());
		}
		
		Comparator<String> dpc = new Comparator<String>() {
			public String findDouble(String s){
				return s.substring(0, s.indexOf('%')).trim();
			}
			
			@Override
			public int compare(String a, String b) {
				return Double.compare(Double.parseDouble(findDouble(a)), Double.parseDouble(findDouble(b)));
			}
		};
		
		Collections.sort(frameStrings, dpc);
		
		StringBuilder body = new StringBuilder();
		
		for (String s : frameStrings){
			body.append(s);
		}
		
		String bodyString = body.toString();
		
		StringBuilder result = new StringBuilder();
		
		result.append(String.format("@keyframes %s {\n%s}\n\n", name, bodyString));
		result.append(String.format("@-webkit-keyframes %s {\n%s}\n\n", name, bodyString));
		result.append(String.format("@-moz-keyframes %s {\n%s}\n", name, bodyString));
		
		return result.toString();
	}
	
	private static class Frame implements Comparable<Frame>{
		List<Double> percentages;
		Map<String, String> properties;
		String body;
		
		public Frame(Double pct) {
			this.percentages = new ArrayList<Double>();
			percentages.add(pct);
			this.properties = new HashMap<String, String>();
		}

		public void addProperty(String property, String value) {
			properties.put(property, value);
			body = getBody();
		}
		
		public String getBody(){
			StringBuilder sb = new StringBuilder();
			List<String> props = new ArrayList<String>(properties.keySet());
			Collections.sort(props);
			for (String prop : props){
				sb.append(String.format("\t\t%s: %s;\n", prop, properties.get(prop)));
			}
			return sb.toString();
		}
		
		public String toString(){
			String percentageString = "";
			for (Double pct : percentages){
				percentageString += String.format(", %.2f%%",pct);
			}
			percentageString = percentageString.substring(2);
			
			return String.format("\t%s {\n%s\t}\n", percentageString, body);
		}

		@Override
		public int compareTo(Frame that) {
			return this.body.compareTo(that.body);
		}
		
		public void addPercentages(Frame that){
			this.percentages.addAll(that.percentages);
			Collections.sort(this.percentages);
		}
		
		public static List<Frame> minimalFrames(List<Frame> frames){
			List<Frame> result = new ArrayList<Frame>();
			Collections.sort(frames);
			Frame current = frames.get(0);
			for(int i = 1; i < frames.size(); i++){
				Frame f = frames.get(i);
				if (current.compareTo(f) == 0){
					current.addPercentages(f);
				} else {
					result.add(current);
					current = f;
				}
			}
			result.add(current);
			return result;
		}
	}
	
	public static List<KeyFrames> removeDuplicates(List<KeyFrames> list){
		Collections.sort(list);
		List<KeyFrames> unique = new ArrayList<KeyFrames>();
		unique.add(list.get(0));
		for (int i = 1; i < list.size(); i++){
			if (unique.get(unique.size()-1).compareTo(list.get(i)) != 0){
				unique.add(list.get(i));
			}
		}
		return unique;
	}
	
	public static String keyframesToCSS(List<KeyFrames> list){
		list = removeDuplicates(list);
		StringBuilder sb = new StringBuilder();
		for(KeyFrames frames : list){
			sb.append(frames.toString());
			sb.append("\n\n");
		}
		return sb.toString();
	}

	@Override
	public int compareTo(KeyFrames that) {
		return this.name.compareTo(that.name);
	}
}
