public class GenerateElementKeyframes {

	public static void main(String[] args){
		System.out.println(generate("cw", "trbl", 4, "dashed", "blue", "white", "black"));
		System.out.println(generate("cw", "trb", 4, "dashed", "blue", "white", "black"));
	}
	
	public static String generateFractionalPercentage(int num, int denom, boolean downOne){
		double d = num * 98.0 / denom;
		if (downOne){
			d = d - .01;
		}
		return String.format("%.2f%%", d);
	}
	
	public static String getDirection(char c){
		switch(c){
			case 'r':
				return "right";
			case 't':
				return "top";
			case 'l':
				return "left";
			case 'b':
				return "bottom";
			default:
				return null;
		}
	}
	
	public static String generateBorderColor(char direction, String color){
		return String.format("\t\tborder-%s-color: %s;\n", getDirection(direction), color);
	}
	
	public static String generateHeader(int index, int denominator){
		String result = "\t" + generateFractionalPercentage(index, denominator, false);
		if (index != denominator){
			result += ", " + generateFractionalPercentage(index+1, denominator, true);
		}
		result += " {\n";
		return result;
	}
	
	
	public static String generate(String rotationDirection, String edges, int borderWidth, String borderStyle, String borderColor, String backgroundColor, String textColor){
		int denom = edges.length();
		
		String result = "@keyframes "+MasterGenerator.generateKeyframesName(rotationDirection, edges, borderWidth, borderStyle, borderColor)+"{\n";
		result += "\t0%, 70%{\n\t\tcolor: "+backgroundColor+";\n\t}\n\t100%{\n\t\tcolor: "+textColor+";\n\t}\n";
		for (int index = 0; index <= edges.length(); index++){
			String statement = generateHeader(index, denom);
			if (index > 0){
				statement += generateBorderColor(edges.charAt(index-1), borderColor);
			}
			if (index < denom){
				statement += generateBorderColor(edges.charAt(index), "transparent");
			}
			if (index == denom){
				for (int j = 0;  j < index-1; j++){
					statement += generateBorderColor(edges.charAt(j), borderColor);
				}
			}
			if (index == 0){
				for (int j = 1;  j < edges.length(); j++){
					statement += generateBorderColor(edges.charAt(j), "transparent");
				}
			}
			statement += "\t}\n";
			
			result += statement;
		}
		
		return result + "}\n";
	}
	
}
