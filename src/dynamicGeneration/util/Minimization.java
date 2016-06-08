package dynamicGeneration.util;

public class Minimization {

	public static String minimize(String s){
		s = s.replaceAll("\n", "");
		s = s.replaceAll("\t", "");
		s = s.replaceAll("\\{ ", "{");
		s = s.replaceAll("\\} ", "}");
		s = s.replaceAll(" \\}", "}");
		s = s.replaceAll(" \\{", "{");
		s = s.replaceAll(";\\}", "}");
		s = s.replaceAll(": ", ":");
		return s;
	}

}
