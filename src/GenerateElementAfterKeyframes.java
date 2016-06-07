
public class GenerateElementAfterKeyframes {
	
	public static char opposite(char c){
		switch(c){
			case 'r':
				return 'l';
			case 't':
				return 'b';
			case 'l':
				return 'r';
			case 'b':
				return 't';
			default:
				return ' ';
		}
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
	
	public static String generatePositional(char direction, String borderWidth){
		return String.format("\t\t%s: %s;\n", getDirection(direction), borderWidth);
	}
	
	public static String generateHeightWidth(int borderWidth, String borderColor){
		return String.format("\t\theight: %spx;\n\t\twidth: %spx;\n\t\tdisplay: block;\n\t\tbackground: %s\n", borderWidth, borderWidth, borderColor);
	}
	
	public static char getPrevious(String rotationDirection, char first){
		String truth = "";
		if (rotationDirection.equals("cw")){
			truth = "trbl";
		} else if (rotationDirection.equals("ccw")){
			truth = "tlbr";
		}
		int index = (truth.indexOf(first) + 3) % 4;
		return truth.charAt(index);
	}
	
	public static String generateInPlaceFrame(String rotationDirection, String edges, int borderWidth, String borderColor){
		char c1 = edges.charAt(0);
		char c0 = getPrevious(rotationDirection, c1);
		
		String p0 = generatePositional(c0, "-"+borderWidth+"px");
		String p1 = generatePositional(c1, "-"+borderWidth+"px");
		String p2 = generatePositional(opposite(c0), "auto");
		String p3 = generatePositional(opposite(c1), "auto");
		
		String hw = generateHeightWidth(borderWidth, borderColor);
		
		return p0 + p1 + p2 + p3 + hw;
	}
		
	public static String generateNotThereFrame(String borderColor){
		return "\t\theight: 0;\n\t\twidth: 0;\n\t\tbackground: "+borderColor+";\n\t\tdisplay: none;\n";
	}
	
	public static String surroundWithFramePercent(int percent, String s){
		return String.format("\t%d%% {\n%s\t}\n", percent, s);
	}
	
	public static String generate(String rotationDirection, String edges, int borderWidth, String borderStyle, String borderColor){
		
		String result = String.format("@keyframes %s-after {\n", MasterGenerator.generateKeyframesName(rotationDirection, edges, borderWidth, borderStyle, borderColor));
		result += surroundWithFramePercent(0, generateNotThereFrame(borderColor));
		result += surroundWithFramePercent(1, generateInPlaceFrame(rotationDirection, edges, borderWidth, borderColor));
		result += surroundWithFramePercent(100, generateInPlaceFrame(rotationDirection, edges, borderWidth, borderColor));
		result += "}\n";
		return result;
		
	}
	
	public static void main(String[] args){
		System.out.println(generate("cw", "ltr",3,"solid","black"));
	}
	
}
