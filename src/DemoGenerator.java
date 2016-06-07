
public class DemoGenerator {

	public static String getHTML(String rotationType, String order){
		return String.format("<div class=\"dm dm-%s-%s\">%s - %s</div>\n", rotationType, order, rotationType, order);
	}
	
	public static String getAllHTML(){
		StringBuilder sb = new StringBuilder();
		String ccw = "tlbrtlbrtlbr";
		for (int i = 0; i <= 3; i++){
			for (int j = 1; j <= 4; j++){
				sb.append(getHTML("ccw", ccw.substring(i, i+j)));
			}
			sb.append("<br>\n");
		}
		String cw = "trbltrbltrbl";
		for (int i = 0; i <= 3; i++){
			for (int j = 1; j <= 4; j++){
				sb.append(getHTML("cw", cw.substring(i, i+j)));
			}
			sb.append("<br>\n");
		}
		return sb.toString();
	}
	
}
