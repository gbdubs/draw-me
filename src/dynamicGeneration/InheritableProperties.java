package dynamicGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dynamicGeneration.structures.AnimatedElement;
import dynamicGeneration.structures.CssProp;
import dynamicGeneration.structures.Page;
import dynamicGeneration.util.ColorUtility;
import dynamicGeneration.util.Filter;

public class InheritableProperties {

	private static String doubleToString(double d){
		return String.format("%.2f", d).replace('.','-');
	}
	
	public static List<CssProp> generalAnimationRules(){
		List<CssProp> rules = new ArrayList<CssProp>();
		
		rules.add(new CssProp(".dm", "animation-fill-mode", "both"));
		rules.add(new CssProp(".dm:before", "animation-fill-mode", "both"));
		rules.add(new CssProp(".dm:after", "animation-fill-mode", "both"));
		
		rules.add(new CssProp(".dm", "animation-iteration-count", "1"));
		rules.add(new CssProp(".dm:before", "animation-iteration-count", "1"));
		rules.add(new CssProp(".dm:after", "animation-iteration-count", "1"));
		
		rules.add(new CssProp(".dm", "animation-direction", "normal"));
		rules.add(new CssProp(".dm:before", "animation-direction", "normal"));
		rules.add(new CssProp(".dm:after", "animation-direction", "normal"));
		
		return rules;
	}
	
	
	
	public static List<CssProp> animationDuration(Page p){
		List<CssProp> rules = new ArrayList<CssProp>();
		
		rules.add(new CssProp(".dm", "animation-duration", "1s"));
		rules.add(new CssProp(".dm:before", "animation-duration", "inherit"));
		rules.add(new CssProp(".dm:after", "animation-duration", "inherit"));
		
		Set<AnimatedElement> uniqueDurations = Filter.byDuration(p);
		
		for (AnimatedElement ae : uniqueDurations){
			
			String selector = String.format(".dm-duration-%s", doubleToString(ae.duration));
			String duration = String.format("%.2fs", ae.duration);
			rules.add(new CssProp(selector+" .dm", "animation-duration ", duration));
			rules.add(new CssProp("div"+selector+".dm", "animation-duration", duration));
		}
		
		return rules;
	}
	
	public static List<CssProp> animationDelay(Page p){
		List<CssProp> rules = new ArrayList<CssProp>();
		
		rules.add(new CssProp(".dm", "animation-delay", "0s"));
		rules.add(new CssProp(".dm:before", "animation-delay", "inherit"));
		rules.add(new CssProp(".dm:after", "animation-delay", "inherit"));
		
		Set<AnimatedElement> uniqueDelays = Filter.byDelay(p);
		
		for (AnimatedElement ae : uniqueDelays){
			String selector = String.format(".dm-delay-%s", doubleToString(ae.delay));
			String delay = String.format("%.2fs", ae.delay);
			rules.add(new CssProp(selector+" .dm", "animation-delay ", delay));
			rules.add(new CssProp(selector+".dm", "animation-delay", delay));
		}
		return rules;
	}
	
	public static List<CssProp> animationTimingFunction(Page p){
		List<CssProp> rules = new ArrayList<CssProp>();
		
		rules.add(new CssProp(".dm", "animation-timing-function", "linear"));
		
		rules.add(new CssProp(".dm:before", "animation-timing-function", "inherit"));
		rules.add(new CssProp(".dm:after", "animation-timing-function", "inherit"));
		
		Set<AnimatedElement> uniqueDelays = Filter.byTimingFunction(p);
		
		for (AnimatedElement ae : uniqueDelays){
			String selector = String.format(".dm-animation-timing-%s", ae.animationTiming);
			rules.add(new CssProp(selector+ " .dm", "animation-timing-function", ae.animationTiming));
			rules.add(new CssProp(selector+ ".dm", "animation-timing-function", ae.animationTiming));
		}
		
		return rules;
	}
	
	public static List<CssProp> borderColor(Page p){
		List<CssProp> rules = new ArrayList<CssProp>();
		
		rules.add(new CssProp(".dm", "border-color", "black"));
		rules.add(new CssProp(".dm:before", "border-color", "inherit"));
		
		Set<AnimatedElement> uniqueColors = Filter.byBorderColor(p);
		
		for (AnimatedElement ae : uniqueColors){
			String selector = String.format(".dm-border-color-%s", ColorUtility.toHex(ae.borderColor));
			rules.add(new CssProp(selector, "border-color", ColorUtility.toHexCode(ae.borderColor)));
			rules.add(new CssProp(selector+ " .dm", "border-color", ae.borderColor));
			
			rules.add(new CssProp(selector+".dm:before", "background", ColorUtility.toHexCode(ae.borderColor)));
			rules.add(new CssProp(selector+" .dm:before", "background", ColorUtility.toHexCode(ae.borderColor)));
			rules.add(new CssProp(selector+".dm:after", "background", ColorUtility.toHexCode(ae.borderColor)));
			rules.add(new CssProp(selector+" .dm:after", "background", ColorUtility.toHexCode(ae.borderColor)));
		}
		
		return rules;
	}
	
	public static List<CssProp> borderWidth(Page p){
		List<CssProp> rules = new ArrayList<CssProp>();
		
		rules.add(new CssProp(".dm", "border-width", "1px"));
		rules.add(new CssProp(".dm:before", "border-width", "inherit"));
		
		Set<AnimatedElement> uniqueWidths = Filter.byBorderWidth(p);
		
		for (AnimatedElement ae : uniqueWidths){
			String selector = String.format(".dm-border-width-%d", ae.borderWidth);
			rules.add(new CssProp(selector, "border-width", ae.borderWidth + "px"));
			rules.add(new CssProp(selector+ " .dm", "border-width", ae.borderWidth + "px"));
		}
		
		return rules;
	}
	
	public static List<CssProp> borderStyle(Page p){
		List<CssProp> rules = new ArrayList<CssProp>();
		
		rules.add(new CssProp(".dm", "border-style", "solid"));
		rules.add(new CssProp(".dm:before", "border-style", "inherit"));
		
		Set<AnimatedElement> uniqueWidths = Filter.byBorderStyle(p);
		
		for (AnimatedElement ae : uniqueWidths){
			String selector = String.format(".dm-border-style-%s", ae.borderStyle);
			rules.add(new CssProp(selector, "border-style", ae.borderStyle));
			rules.add(new CssProp(selector+" .dm", "border-style", ae.borderStyle));
		}
		
		return rules;
	}
	
	public static List<CssProp> all(Page p){
		List<CssProp> props = new ArrayList<CssProp>();
		props.addAll(animationDelay(p));
		props.addAll(animationDuration(p));
		props.addAll(animationTimingFunction(p));
		props.addAll(borderColor(p));
		props.addAll(borderStyle(p));
		props.addAll(borderWidth(p));
		props.addAll(generalAnimationRules());
		return props;
	}
}
