package dynamicGeneration.structures;

import static jodd.jerry.Jerry.jerry;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jodd.jerry.Jerry;
import dynamicGeneration.Main;

public class DOMParsing {

	public static String findAnimatedElementsDefinition(Jerry doc) {
		Jerry dms = doc.$(".dm");
		
		Iterator<Jerry> iter = dms.iterator();
		Set<String> all = new HashSet<String>();
		while (iter.hasNext()){
			Jerry next = iter.next();
			all.add(getDefinitionStringFromElement(next));
		}
		
		StringBuilder sb = new StringBuilder();
		for (String s : all){
			sb.append(s);
			sb.append("\n");
		}

		return sb.toString().trim();
	}

	public static void main(String[] args){
		Jerry doc = jerry(Main.htmlPersonal);
		findAnimatedElementsDefinition(doc);
	}

	public static boolean reachedTheTop(Jerry element){
		if (element.hasClass(".dm-wrapper")){
			return true;
		}
		if (element.parent() == null){
			return true;
		}
		return false;
	}

	private static String getDefinitionStringFromElement(Jerry element){
		String result = "";
		result += findRotationDirection(element)+" ";
		result += findEdges(element)+" ";
		result += generalRecursivePropertyFind(element, "dm-border-width-", "1")+" ";
		result += generalRecursivePropertyFind(element, "dm-border-style-", "solid")+" ";
		result += generalRecursivePropertyFind(element, "dm-border-color-", "black")+" ";
		result += generalRecursivePropertyFind(element, "dm-delay-", "0-00").replace("-","")+" ";
		result += generalRecursivePropertyFind(element, "dm-duration-", "1-00").replace("-","")+" ";
		result += generalRecursivePropertyFind(element, "dm-animation-timing-", "linear")+" ";
		String fade = generalRecursivePropertyFind(element, "dm-fade-", "transparent-transparent-transparent-black");
		if (fade.equals("none")){
			result += "none none none none 0";
		} else {
			result += fade.replace('-', ' ') + " 1";
		}
		return result;
	}
	
	private static String generalRecursivePropertyFind(Jerry element, String lookingFor, String defaultResult){
		String classes;
		try{
			classes = element.attr("class");
		} catch (NullPointerException npe){
			return defaultResult;
		}
		if (classes != null && classes.length() > 0){
			int index = classes.indexOf(lookingFor);
			if (index == -1){
				if (reachedTheTop(element)){
					return defaultResult;
				} else {
					return generalRecursivePropertyFind(element.parent(), lookingFor, defaultResult);
				}
			} else {
				int first = index + lookingFor.length();
				int last = classes.indexOf(" ", first);
				if (last == -1){
					last = classes.length();
				}
				return classes.substring(first, last);
			}
		} else {
			if (reachedTheTop(element)){
				return defaultResult;
			} else {
				return generalRecursivePropertyFind(element.parent(), lookingFor, defaultResult);
			}
		}
	}

	private static String findRotationDirection(Jerry element){
		String classes = element.attr("class");
		if (classes.indexOf("dm-ccw-") >= 0){
			return "ccw";
		} else if (classes.indexOf("dm-cw-") >= 0){
			return "cw";
		}
		return null;
	}

	private static String findEdges (Jerry element) {
		String cname = element.attr("class");
		String root = "dm-"+findRotationDirection(element)+"-";
		int start = cname.indexOf(root) + root.length();
		int end = cname.indexOf(" ", start);
		if (end == -1){
			end = cname.length();
		}
		return cname.substring(start, end);
	}

}
