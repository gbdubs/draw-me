package dynamicGeneration;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

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
	
	public static Set<AnimatedElement> by(Comparator<AnimatedElement> comparator, Page p){
		Set<AnimatedElement> result = new TreeSet<AnimatedElement>(new Duration());
		for (AnimatedElement ae : p.elements){
			result.add(ae);
		}
		return result;
	}

	
}

