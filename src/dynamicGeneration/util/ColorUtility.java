package dynamicGeneration.util;

import java.util.HashMap;
import java.util.Map;

public class ColorUtility {

	private final static String[] htmlDefaultColorNames = {"white","gray","silver","black","maroon","red ","purple","fuchsia","green","lime","olive","yellow","navy","blue","teal","aqua","orange","indianred","lightcoral","salmon","darksalmon","lightsalmon","crimson","red","firebrick","darkred","pink","lightpink","hotpink","deeppink","mediumvioletred","palevioletred","lightsalmon","coral","tomato","orangered","darkorange","orange","gold","yellow","lightyellow","lemonchiffon","lightgoldenrodyellow","papayawhip","moccasin","peachpuff","palegoldenrod","khaki","darkkhaki","lavender","thistle","plum","violet","orchid","fuchsia","magenta","mediumorchid","mediumpurple","blueviolet","darkviolet","darkorchid","darkmagenta","purple","indigo","slateblue","darkslateblue","mediumslateblue","greenyellow","chartreuse","lawngreen","lime","limegreen","palegreen","lightgreen","mediumspringgreen","springgreen","mediumseagreen","seagreen","forestgreen","green","darkgreen","yellowgreen","olivedrab","olive","darkolivegreen","mediumaquamarine","darkseagreen","lightseagreen","darkcyan","teal","aqua","cyan","lightcyan","paleturquoise","aquamarine","turquoise","mediumturquoise","darkturquoise","cadetblue","steelblue","lightsteelblue","powderblue","lightblue","skyblue","lightskyblue","deepskyblue","dodgerblue","cornflowerblue","mediumslateblue","royalblue","blue","mediumblue","darkblue","navy","midnightblue","cornsilk","blanchedalmond","bisque","navajowhite","wheat","burlywood","tan","rosybrown","sandybrown","goldenrod","darkgoldenrod","peru","chocolate","saddlebrown","sienna","brown","maroon","white","snow","honeydew","mintcream","azure","aliceblue","ghostwhite","whitesmoke","seashell","beige","oldlace","floralwhite","ivory","antiquewhite","linen","lavenderblush","mistyrose","gainsboro","lightgrey","silver","darkgray","gray","dimgray","lightslategray","slategray","darkslategray","black"};
	private final static String[] htmlDefaultColorHexCodes = {"FFFFFF","808080","C0C0C0","000000","800000","FF0000","800080","FF00FF","008000","00FF00","808000","FFFF00","000080","0000FF","008080","00FFFF","FFA500","CD5C5C","F08080","FA8072","E9967A","FFA07A","DC143C","FF0000","B22222","8B0000","FFC0CB","FFB6C1","FF69B4","FF1493","C71585","DB7093","FFA07A","FF7F50","FF6347","FF4500","FF8C00","FFA500","FFD700","FFFF00","FFFFE0","FFFACD","FAFAD2","FFEFD5","FFE4B5","FFDAB9","EEE8AA","F0E68C","BDB76B","E6E6FA","D8BFD8","DDA0DD","EE82EE","DA70D6","FF00FF","FF00FF","BA55D3","9370DB","8A2BE2","9400D3","9932CC","8B008B","800080","4B0082","6A5ACD","483D8B","7B68EE","ADFF2F","7FFF00","7CFC00","00FF00","32CD32","98FB98","90EE90","00FA9A","00FF7F","3CB371","2E8B57","228B22","008000","006400","9ACD32","6B8E23","808000","556B2F","66CDAA","8FBC8F","20B2AA","008B8B","008080","00FFFF","00FFFF","E0FFFF","AFEEEE","7FFFD4","40E0D0","48D1CC","00CED1","5F9EA0","4682B4","B0C4DE","B0E0E6","ADD8E6","87CEEB","87CEFA","00BFFF","1E90FF","6495ED","7B68EE","4169E1","0000FF","0000CD","00008B","000080","191970","FFF8DC","FFEBCD","FFE4C4","FFDEAD","F5DEB3","DEB887","D2B48C","BC8F8F","F4A460","DAA520","B8860B","CD853F","D2691E","8B4513","A0522D","A52A2A","800000","FFFFFF","FFFAFA","F0FFF0","F5FFFA","F0FFFF","F0F8FF","F8F8FF","F5F5F5","FFF5EE","F5F5DC","FDF5E6","FFFAF0","FFFFF0","FAEBD7","FAF0E6","FFF0F5","FFE4E1","DCDCDC","D3D3D3","C0C0C0","A9A9A9","808080","696969","778899","708090","2F4F4F","000000"};
	private static Map<String, String> htmlDefaultColors = new HashMap<String, String>();
	private static Map<String, String> reverseHtmlDefaultColors = new HashMap<String, String>();
	
	static {
		for (int i = 0; i < htmlDefaultColorNames.length; i++){
			htmlDefaultColors.put(htmlDefaultColorNames[i], htmlDefaultColorHexCodes[i]);
			reverseHtmlDefaultColors.put(htmlDefaultColorHexCodes[i], htmlDefaultColorNames[i]);
		}
	}
	
	public static boolean isHexCode(String s){		
		if (s.indexOf("#") == 0){
			s = s.substring(1);
		}
		if (s.length() != 6){
			return false;
		}
		String acceptable = "0123456789ABCDEF";
		for (char c : s.toUpperCase().toCharArray()){
			if (!acceptable.contains(""+c)){
				return false;
			}
		}
		return true;
	}
	
	public static String toHex(String s){
		if (s.equals("transparent")){
			return s;
		}
		if (s.indexOf("#") == 0){
			s = s.substring(1);
		}
		if (isHexCode(s)){
			return s.toUpperCase();
		}
		if (htmlDefaultColors.keySet().contains(s.toLowerCase())){
			return htmlDefaultColors.get(s.toLowerCase());
		}
		return null;
	}
	
	public static String toHexCode(String s){
		if (s.equals("transparent")){
			return s;
		}
		String hex = toHex(s);
		if (hex == null){
			return s;
		}
		return "#" + hex;
	}
	
	public static String toName(String s){
		if (reverseHtmlDefaultColors.containsKey(s)){
			return reverseHtmlDefaultColors.get(s);
		}
		return toHex(s);
	}
	
	public static boolean isDefault(String s){
		if (s.equalsIgnoreCase("black")){
			return true;
		}
		if (s.equals("#000000")){
			return true;
		}
		if (s.equals("000000")){
			return true;
		}
		return false;
	}
}
