import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;


public class MasterGenerator {

	public static String generateDuration(String edges, double perEdgeDuration){
		double d = perEdgeDuration; //* edges.length();
		return String.format("%.2fs", d);
	}
	
	public static String generateRules(String rotationDirection, String edges, double perEdgeDuration, int borderWidth, String borderStyle, String borderColor, String animationStyle){
		String parentSelector = "";
		if (!(borderWidth == 1)){
			parentSelector += ".dm-border-width-"+borderWidth;
		}
		if (!borderStyle.equals("solid")){
			parentSelector += ".dm-border-style-"+borderStyle;
		}
		if (!animationStyle.equals("linear")){
			parentSelector += ".dm-animation-style-"+animationStyle;
		}
		if (parentSelector.length() > 0){
			parentSelector += " ";
		}
		if (!borderColor.equals("black")){
			parentSelector += ".dm-border-color-"+borderColor.replace("#", "");
		}
		
		String selector = "dm-"+rotationDirection+"-"+edges;

		String keyframesName = generateKeyframesName(rotationDirection, edges, borderWidth, borderStyle, borderColor);
		
		String result = String.format(
				"%s.%s { animation: %s %s %s; }\n\n", 
				parentSelector, selector, keyframesName, generateDuration(edges, perEdgeDuration), animationStyle
		);
		result += String.format(
				"%s.%s:before { animation: %s-before %s %s; }\n\n", 
				parentSelector, selector, keyframesName, generateDuration(edges, perEdgeDuration), animationStyle
		);
		result += String.format(
				"%s.%s:after { animation: %s-after %s %s; }\n\n", 
				parentSelector, selector, keyframesName, generateDuration(edges, perEdgeDuration), animationStyle
		);
		result += GenerateElementBeforeKeyframes.generate(rotationDirection, edges, borderWidth, borderStyle, borderColor) + "\n";
		result += GenerateElementAfterKeyframes.generate(rotationDirection, edges, borderWidth, borderStyle, borderColor) + "\n";
		result += GenerateElementKeyframes.generate(rotationDirection, edges, borderWidth, borderStyle, borderColor, "white", "black")+ "\n";
		result += getEdgeRules(rotationDirection, edges, borderWidth, borderStyle, borderColor);
		return result;
	}
	
	public static String generateKeyframesName(String rotationDirection, String edges, int borderWidth, String borderStyle,String borderColor) {
		String result = "dm-"+rotationDirection+"-"+edges;
		if (borderWidth != 1){
			result += "-width-"+borderWidth;
		}
		if (!borderStyle.equals("solid")){
			result += "-style-"+borderStyle;
		}
		if (!borderColor.equals("black")){
			result += "-color-"+borderColor.replaceAll("#","");
		}
		return result;
	}

	public static String generateAllRules(){
		StringBuilder sb = new StringBuilder();
		sb.append(generateAllRules(1));
		sb.append(CodeAnalyzer.createColorStyles(new HashSet<String>(Arrays.asList(colors))));
		return sb.toString();
	}
	
	public static StringBuilder generateAllRules(double perEdgeDuration){
		StringBuilder sb = new StringBuilder();
		sb.append(generateAllRules(perEdgeDuration, 1));
		sb.append(generateAllRules(perEdgeDuration, 5));
		sb.append(generateAllRules(perEdgeDuration, 10));
		return sb;
	}
	
	public static StringBuilder generateAllRules(double perEdgeDuration, int borderWidth){
		StringBuilder sb = new StringBuilder();
		sb.append(generateAllRules(perEdgeDuration, borderWidth, "solid"));
		sb.append(generateAllRules(perEdgeDuration, borderWidth, "dotted"));
		return sb;
	}
	
	public static String[] colors = {"#1C55BF", "#DE1718", "#EFBD26"};
	
	public static StringBuilder generateAllRules(double perEdgeDuration, int borderWidth, String borderStyle){
		StringBuilder sb = new StringBuilder();
		sb.append(generateAllRules(perEdgeDuration, borderWidth, borderStyle, "black"));
		for (String color : colors){
			sb.append(generateAllRules(perEdgeDuration, borderWidth, borderStyle, color));
		}
		return sb;
	}
	
	public static StringBuilder generateAllRules(double perEdgeDuration, int borderWidth, String borderStyle, String borderColor){
		StringBuilder sb = new StringBuilder();
		sb.append(generateAllRules(perEdgeDuration, borderWidth, borderStyle, borderColor, "linear"));
		sb.append(generateAllRules(perEdgeDuration, borderWidth, borderStyle, borderColor, "ease-out"));
		return sb;
	}
	
	public static StringBuilder generateAllRules(double perEdgeDuration, int borderWidth, String borderStyle, String borderColor, String animationStyle){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("/*   DURATION=%.2f, WIDTH=%d, STYLE=%s, COLOR=%s, ANIMATION=%s   */",perEdgeDuration, borderWidth, borderStyle, borderColor, animationStyle));
		String ccw = "tlbrtlbrtlbr";
		for (int i = 0; i <= 3; i++){
			for (int j = 1; j <= 4; j++){
				sb.append(generateRules("ccw", ccw.substring(i, i+j), perEdgeDuration, borderWidth, borderStyle, borderColor, animationStyle));
			}
		}
		String cw = "trbltrbltrbl";
		for (int i = 0; i <= 3; i++){
			for (int j = 1; j <= 4; j++){
				sb.append(generateRules("cw", cw.substring(i, i+j), perEdgeDuration, borderWidth, borderStyle, borderColor, animationStyle));
			}
		}
		return sb;
	}
	
	
	private static String getEdgeRule(String edge, int borderWidth, String borderStyle, String borderColor){
		return String.format("\tborder-%s: %dpx %s %s;\n", edge, borderWidth, borderStyle, borderColor);
	}
	
	private static String getEdgeRules(String rotationDirection, String edges, int borderWidth, String borderStyle, String borderColor){
		String result = "";
		if (edges.contains("t")){
			result += getEdgeRule("top", borderWidth, borderStyle, borderColor);
		}
		if (edges.contains("b")){
			result += getEdgeRule("bottom", borderWidth, borderStyle, borderColor);
		}
		if (edges.contains("l")){
			result += getEdgeRule("left", borderWidth, borderStyle, borderColor);
		}
		if (edges.contains("r")){
			result += getEdgeRule("right", borderWidth, borderStyle, borderColor);
		}
		
		String selector = ".dm-"+rotationDirection+"-"+edges;
		String parentSelector = "";
		if (borderWidth != 1){
			parentSelector += ".dm-border-width-"+borderWidth;
		}
		if (!borderStyle.equals("solid")){
			parentSelector += ".dm-border-style-"+borderStyle;
		}
		if (!borderColor.equals("black")){
			selector += ".dm-border-color-"+borderColor.replaceAll("#", "");
		}
		if (parentSelector.length() > 0){
			parentSelector += " ";
		}
		selector = parentSelector + selector;
		
		return selector+"{\n"+result+ "}\n";	
	}
	
	
	public static void main(String[] args){
		File f = new File("output.css");
		try {
			PrintWriter pw = new PrintWriter(f);
			String s = MasterGenerator.generateAllRules();
			pw.print(s);
			pw.close();
			f = new File("output.min.css");
			s = Tools.minimize(s);
			pw = new PrintWriter(f);
			pw.print(s);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
