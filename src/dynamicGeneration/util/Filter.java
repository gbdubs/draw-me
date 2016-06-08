package dynamicGeneration.util;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import dynamicGeneration.structures.AnimatedElement;
import dynamicGeneration.structures.Page;

public class Filter {
	public static class Delay implements Comparator<AnimatedElement>{
		@Override
		public int compare(AnimatedElement a, AnimatedElement b) {
			return Double.compare(a.delay, b.delay);
		}
	}
	
	public static class Duration implements Comparator<AnimatedElement>{
		@Override
		public int compare(AnimatedElement a, AnimatedElement b) {
			return Double.compare(a.delay, b.delay);
		}
	}
	
	public static class BorderColor implements Comparator<AnimatedElement>{
		@Override
		public int compare(AnimatedElement a, AnimatedElement b) {
			return a.borderColor.compareTo(b.borderColor);
		}
	}
	
	public static class BorderWidth implements Comparator<AnimatedElement>{
		@Override
		public int compare(AnimatedElement a, AnimatedElement b) {
			return Integer.compare(a.borderWidth, b.borderWidth);
		}
	}
	
	public static class BorderStyle implements Comparator<AnimatedElement>{
		@Override
		public int compare(AnimatedElement a, AnimatedElement b) {
			return a.borderStyle.compareTo(b.borderStyle);
		}
	}
	
	public static Set<AnimatedElement> by(Comparator<AnimatedElement> comparator, Page p){
		Set<AnimatedElement> result = new TreeSet<AnimatedElement>(new Duration());
		for (AnimatedElement ae : p.elements){
			result.add(ae);
		}
		return result;
	}
}

