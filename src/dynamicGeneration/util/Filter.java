package dynamicGeneration.util;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import dynamicGeneration.structures.AnimatedElement;
import dynamicGeneration.structures.Page;

public class Filter {
	
	public static Set<AnimatedElement> byBorderColor(Page p){
		Set<AnimatedElement> result = new TreeSet<AnimatedElement>(new Comparator<AnimatedElement>(){
			@Override
			public int compare(AnimatedElement a, AnimatedElement b) {
				return a.borderColor.compareTo(b.borderColor);
			}
		});
		result.addAll(p.elements);
		return result;
	}

	public static Set<AnimatedElement> byBorderStyle(Page p) {
		Set<AnimatedElement> result = new TreeSet<AnimatedElement>(new Comparator<AnimatedElement>(){
			@Override
			public int compare(AnimatedElement a, AnimatedElement b) {
				return a.borderStyle.compareTo(b.borderStyle);
			}
		});
		result.addAll(p.elements);
		return result;
	}

	public static Set<AnimatedElement> byBorderWidth(Page p) {
		Set<AnimatedElement> result = new TreeSet<AnimatedElement>(new Comparator<AnimatedElement>(){
			@Override
			public int compare(AnimatedElement a, AnimatedElement b) {
				return Integer.compare(a.borderWidth, b.borderWidth);
			}
		});
		result.addAll(p.elements);
		return result;
	}

	public static Set<AnimatedElement> byTimingFunction(Page p) {
		Set<AnimatedElement> result = new TreeSet<AnimatedElement>(new Comparator<AnimatedElement>(){
			@Override
			public int compare(AnimatedElement a, AnimatedElement b) {
				return a.animationTiming.compareTo(b.animationTiming);
			}
		});
		result.addAll(p.elements);
		return result;
	}

	public static Set<AnimatedElement> byDelay(Page p) {
		Set<AnimatedElement> result = new TreeSet<AnimatedElement>(new Comparator<AnimatedElement>(){
			@Override
			public int compare(AnimatedElement a, AnimatedElement b) {
				return Double.compare(a.delay, b.delay);
			}
		});
		result.addAll(p.elements);
		return result;
	}

	public static Set<AnimatedElement> byDuration(Page p) {
		Set<AnimatedElement> result = new TreeSet<AnimatedElement>(new Comparator<AnimatedElement>(){
			@Override
			public int compare(AnimatedElement a, AnimatedElement b) {
				return Double.compare(a.delay, b.delay);
			}
		});
		result.addAll(p.elements);
		return result;
	}

}