
public class GenerateElementBeforeKeyframes {

	public static String makeFirstBeforeKeyframe(){
		return "\t0% {\n\t\tborder-width: 0 0 0 0;\n\t}\n";
	}
	
	public static String makeLastBeforeKeyframe(int borderWidth, String finalCorner, String borderColor){
		String properties = buildPositionProperties(finalCorner, borderWidth);
		return String.format("\t100%% {\n\t\tborder-width: 0 0 0 0;\n%s\t\twidth: %dpx;\n\t\theight: %dpx;\n\t\tbackground:%s\t}\n", properties, borderWidth, borderWidth, borderColor);
	}
	
	public static String makeOrderExplicitPairs(String rotationType, String order){
		String known = "";
		if (rotationType.equals("ccw")){
			known = "trbl";
		} else if (rotationType.equals("cw")){
			known = "tlbr";
		}
		
		char last = known.charAt((known.indexOf(order.charAt(order.length() - 1))+3)%4);
		char first = known.charAt((known.indexOf(order.charAt(0))+5)%4);
		order = first + order + last;
		return order;
	}

	public static String buildPositionProperties(String corner, int borderWidth){
		String[] properties = {"top", "bottom", "right", "left"};
		String running = "";
		for (String prop : properties){
			running += "\t\t" + prop + ": ";
			if (corner.contains(""+prop.charAt(0))){
				running += "-"+borderWidth+"px";
			} else {
				running += "auto";
			}
			running += ";\n";
		}
		return running;
	}
	
	public static String buildSecondSuffix(String rotationType, String firstCorner, int borderWidth){
		String bw = "\t\tborder-width: "+borderWidth+"px "+borderWidth+"px 0 0;";
		if (firstCorner.charAt(0) == 't' || firstCorner.charAt(0) == 'b'){
			return "\t\twidth: 0px;\n\t\theight: 100%;\n"+bw;
		}
		return "\t\twidth: 100%;\n\t\theight: 0px;\n"+bw;
	}
	
	public static String buildPercentage(int num, int denom, boolean roundDown){
		double d = 98 * num / denom + 1;
		if (roundDown){
			d -= .01;
		}
		return String.format("\t%.2f%% {\n", d);
	}
	
	public static String buildCornerKeyframe(String rotationType, String corner, int index, int denom, int borderWidth, String borderStyle, String borderColor){
		String positionProps = buildPositionProperties(corner, borderWidth);
		String firstSuffix = String.format("\t\theight: 0px;\n\t\twidth: 0px;\n\t\tborder-width: %dpx %dpx 0 0;\n\t}\n", borderWidth, borderWidth);
		String firstKeyframe = buildPercentage(index, denom, false) + positionProps + firstSuffix;
		String secondKeyframe = buildPercentage(index+1, denom, true) + positionProps + buildSecondSuffix(rotationType, corner, borderWidth)+"\n\t}\n";
		String result = firstKeyframe  + secondKeyframe ;
		return result;
	}
	
	public static String generate(String rotationDirection, String edges, int borderWidth, String borderStyle, String borderColor){
		String result = "@keyframes "+MasterGenerator.generateKeyframesName(rotationDirection, edges, borderWidth, borderStyle, borderColor)+"-before {\n";
		result += makeFirstBeforeKeyframe();
		edges = makeOrderExplicitPairs(rotationDirection, edges);
		for (int i = 0; i < edges.length() - 2; i++){
			String corner = edges.substring(i, i+2);
			result += buildCornerKeyframe(rotationDirection, corner, i, edges.length() - 2, borderWidth, borderStyle, borderColor);
		}
		result += makeLastBeforeKeyframe(borderWidth, edges.substring(edges.length() - 2, edges.length() - 0), borderColor);
		return result +"}\n";
	}
	
	public static void main(String[] args){
		System.out.println(generate("cw", "trbl", 10, "dashed","red"));
		
	}
}
