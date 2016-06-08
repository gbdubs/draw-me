package dynamicGeneration.structures;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dynamicGeneration.util.ColorUtility;

public class AnimatedElement {
	
	public String rotationDirection;
	public String edges;
	public int borderWidth;
	public String borderStyle;
	public String borderColor;
	public double delay;
	public double duration;
	public String animationTiming;
	public String fadeBackgroundFromColor;
	public String fadeBackgroundToColor;
	public String fadeTextFromColor;
	public String fadeTextToColor;
	public boolean shouldFade;
	
	public AnimatedElement(String s){
		String[] blocks = s.split(" ");
		if (blocks.length != 13){
			throw new RuntimeException(String.format("Expected Animation Elements of the Standard, Twelve Part Form: [%s]", s));
		}
		String acceptable = "";
		if (blocks[0].equalsIgnoreCase("ccw")){
			acceptable = "tlbrtlbr";
			rotationDirection = "ccw";
		} else if (blocks[0].equalsIgnoreCase("cw")){
			acceptable = "trbltrbl";
			rotationDirection = "cw";
		} else {
			throw new RuntimeException(String.format("Rotation Direction Rejected, must be CW or CCW: [%s]", blocks[0]));
		}
		
		edges = blocks[1].toLowerCase();
		if (!acceptable.contains(edges)){
			throw new RuntimeException(String.format("The edge sequence provided is not compatible with the rotation direction: [%s] [%s]", edges, rotationDirection));
		}
		
		try {
			borderWidth = Integer.parseInt(blocks[2]);
			if (borderWidth <= 0){
				throw new RuntimeException(String.format("Border Width Must Be Postive: [%d]", borderWidth));
			}
		} catch (java.lang.NumberFormatException nfe){
			throw new RuntimeException(String.format("The width provided was not a parseable integer: [%s]", blocks[2]));
		}
		
		borderStyle = blocks[3];
		if (!validBorderStyles.contains(borderStyle)){
			throw new RuntimeException(String.format("The style provided is not a CSS supported type for border-style: [%s]", blocks[3]));
		}
		
		borderColor = ColorUtility.toHex(blocks[4]);
		if (borderColor == null){
			throw new RuntimeException(String.format("The border color provided is neither a default HTML color, nor a HEX code: [%s]", blocks[4]));
		}
		
		try {
			delay = Double.parseDouble(blocks[5]);
			delay = Math.round(delay * 100.0) / 100.0;
		} catch (java.lang.NumberFormatException nfe){
			throw new RuntimeException(String.format("The delay provided was not a parse-able double: [%s]", blocks[5]));
		}
		
		try {
			duration = Double.parseDouble(blocks[6]);
			duration = Math.round(duration * 100.0) / 100.0;
			if (duration < 0){
				throw new RuntimeException(String.format("The duration provided was negative. [%s]", blocks[6]));
			}
		} catch (java.lang.NumberFormatException nfe){
			throw new RuntimeException(String.format("The duration provided was not a parse-able double: [%s]", blocks[6]));
		}
		
		animationTiming = blocks[7];
		if (!validAnimationTimings.contains(animationTiming)){
			throw new RuntimeException(String.format("The animation timing function provided was not supported or not recognized: [%s]", blocks[7]));
		}
		
		fadeBackgroundFromColor = ColorUtility.toHex(blocks[8]);
		if (fadeBackgroundFromColor == null){
			throw new RuntimeException(String.format("The behind color provided is neither a default HTML color, nor a HEX code: [%s]", blocks[8]));
		}
		
		fadeBackgroundToColor = ColorUtility.toHex(blocks[9]);
		if (fadeBackgroundToColor == null){
			throw new RuntimeException(String.format("The background color provided is neither a default HTML color, nor a HEX code: [%s]", blocks[9]));
		}
		
		fadeTextFromColor = ColorUtility.toHex(blocks[10]);
		if (fadeTextFromColor == null){
			throw new RuntimeException(String.format("The text color provided is neither a default HTML color, nor a HEX code: [%s]", blocks[10]));
		}
		
		fadeTextToColor = ColorUtility.toHex(blocks[11]);
		if (fadeTextToColor == null){
			throw new RuntimeException(String.format("The text color provided is neither a default HTML color, nor a HEX code: [%s]", blocks[11]));
		}
		
		if (blocks[12].equalsIgnoreCase("true") || blocks[12].equals("1")){
			shouldFade = true;
		} else if (blocks[12].equalsIgnoreCase("false") || blocks[12].equals("0")){
			shouldFade = false;
		} else {
			throw new RuntimeException(String.format("The fadeIn boolean provided is not a parseable boolean: [%s]", blocks[12]));
		}
		
	}
		
	private static Set<String> validAnimationTimings;
	private static Set<String> validBorderStyles;
	
	static {
		String[] validStylesArray = {"solid", "dotted", "dashed", "double", "groove", "ridge", "inset", "outset"};
		validBorderStyles = new HashSet<String>(Arrays.asList(validStylesArray));
		String[] validAnimationTimingsArray = {"linear", "ease", "ease-in", "ease-out", "ease-in-out"};
		validAnimationTimings = new HashSet<String>(Arrays.asList(validAnimationTimingsArray));
	}
}
