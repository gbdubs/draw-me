package dynamicGeneration;

import java.util.Set;

public class InheritableProperties {

	private static String doubleToString(double d){
		return String.format("%.2f", d).replace('.','-');
	}
	
	public static CharSequence animationDuration(Page p){
		StringBuilder sb = new StringBuilder();
		
		sb.append(".dm-wrapper {\n\tanimation-duration: 1s;\n}\n");
		
		sb.append(".dm {\n\tanimation-duration: inherit;\n}\n");
		
		Set<AnimatedElement> uniqueDurations = Filter.by(new Filter.Duration(), p);
		
		for (AnimatedElement ae : uniqueDurations){
			sb.append(String.format(".dm-duration-%s {\n\tanimation-duration: %.2fs;\n}\n", doubleToString(ae.duration), ae.duration));
		}
		
		return sb;
	}
	
	public static CharSequence animationDelay(Page p){
		StringBuilder sb = new StringBuilder();
		
		sb.append(".dm-wrapper {\n\tanimation-delay: 0s;\n}\n");
		
		sb.append(".dm {\n\tanimation-delay: inherit;\n}\n");
		
		Set<AnimatedElement> uniqueDelays = Filter.by(new Filter.Delay(), p);
		
		for (AnimatedElement ae : uniqueDelays){
			sb.append(String.format(".dm-delay-%s {\n\tanimation-delay: %.2fs;\n}\n", doubleToString(ae.duration), ae.duration));
		}
		
		return sb;
	}
	
	
	
	
}
