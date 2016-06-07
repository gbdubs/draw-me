import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CodeAnalyzer {
	
	public static double timeToAnimateEachEdge;
	
	public static void main(String[] args) throws IOException{
		String filename = "../../Commentary/rotation/index.html";
		String contents = readFile(filename, Charset.defaultCharset());
		System.out.println(Tools.minimize(analyzeAndSelectRelevantCss(contents, .5)));
	}
	
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public static String analyzeAndSelectRelevantCss(String original, double timeToAnimatePerEdge){
		StringBuilder result = new StringBuilder();
		System.out.println("HERE");
		result.append(base());
		Set<String> colors = findAllUsedColors(original);
		System.out.println("HERE");
		Set<String> blocks = findAllUsedBlocks(original);
		System.out.println("HERE");
		result.append(createColoredBlocks(blocks, colors, original));
		System.out.println("HERE");
		result.append(createDelays(original));
		System.out.println("HERE");
		result.append(createBorderStyleStyles(findAllUsedStyles(original)));
		System.out.println("HERE");
		result.append(createWidthStyles(findAllUsedWidths(original)));
		System.out.println("HERE");
		return result.toString();
	}
	
	public static Set<String> findAllUsedStyles(String original){
		String lookingFor = "dm-border-style-";
		int foundAt = original.indexOf(lookingFor);
		Set<String> usedStyles = new HashSet<String>();
		usedStyles.add("solid");
		while (foundAt >= 0){
			foundAt += lookingFor.length();
			int end = foundAt + 1;
			while ("\"' ".indexOf(original.charAt(end)) == -1){
				end++;
			}
			String s = original.substring(foundAt, end);			
			usedStyles.add(s);
			foundAt = original.indexOf(lookingFor, foundAt+1);
		}
		return usedStyles;
	}
	
	public static StringBuilder createBorderStyleStyles(Set<String> styles){
		StringBuilder result = new StringBuilder();
		for(String s : styles){
			result.append(String.format(
					".dm-border-style-%s .dm {\n" + 
					"  border-style: %s;\n" + 
					"}\n" + 
					"\n" + 
					".dm-border-style-%s .dm:before {\n" + 
					"  border-style: %s;\n" + 
					"}\n", s, s, s, s
			));
		}
		return result;
	}
	
	public static Set<Integer> findAllUsedWidths(String original){
		String lookingFor = "dm-border-width-";
		int foundAt = original.indexOf(lookingFor);
		Set<Integer> usedWidths = new HashSet<Integer>();
		usedWidths.add(1);
		while (foundAt >= 0){
			foundAt += lookingFor.length();
			int end = foundAt + 1;
			while ("\"' ".indexOf(original.charAt(end)) == -1){
				end++;
			}
			String s = original.substring(foundAt, end);			
			usedWidths.add(Integer.parseInt(s));
			foundAt = original.indexOf(lookingFor, foundAt+1);
		}
		return usedWidths;
	}
	
	public static StringBuilder createWidthStyles(Set<Integer> widths){
		StringBuilder result = new StringBuilder();
		for(Integer i : widths){
			result.append(String.format(
					".dm-border-width-%d.dm," +
					".dm-border-width-%d .dm{\n" + 
					"  border-width: %dpx;\n" + 
					"}\n", i, i, i
			));
		}
		return result;
	}
	
	public static StringBuilder createColorStyles(Set<String> colors){
		StringBuilder result = new StringBuilder();
		for (String color : colors){
			String tempColor = color.replace("#", "");
			String modColor = isInHex(tempColor) ? "#" + tempColor : tempColor;	
			result.append(String.format(
					".dm-border-color-%s:before{ border-color: %s !important; }\n" + 
					//"\n" + 
					//".dm-border-color-%s{ border-color: %s; }\n" + 
					"\n" + 
					".dm-border-color-%s:after{ background: %s; }\n",
					tempColor, modColor, tempColor, modColor, tempColor, modColor
			));
		}
		System.out.println(result);
		return result;	
	}

	public static Set<String> findAllUsedColors(String original){
		String lookingFor = "dm-border-color-";
		int foundAt = original.indexOf(lookingFor);
		Set<String> usedColors = new HashSet<String>();
		usedColors.add("black");
		while (foundAt >= 0){
			foundAt += lookingFor.length();
			int end = foundAt + 1;
			while ("\"' ".indexOf(original.charAt(end)) == -1){
				end++;
			}
			String s = original.substring(foundAt, end);			
			usedColors.add(s);
			foundAt = original.indexOf(lookingFor, foundAt+1);
		}
		return usedColors;
	}
	
	public static Set<String> findAllUsedBlocks(String original){
		int foundAt = original.indexOf("dm-c");
		Set<String> usedBlocks = new HashSet<String>();
		while (foundAt >= 0){
			int end = foundAt + 4;
			while ("cw-tblr".indexOf(original.charAt(end)) > -1){
				end++;
			}
			String s = original.substring(foundAt+3, end);			
			usedBlocks.add(s);
			foundAt = original.indexOf("dm-c", foundAt+1);
		}
		return usedBlocks;
	}
	
	private static boolean colorBlockStyleNescessary(String block, String color, String original){
		if (color.equals("black")){
			return true;
		}
		String option1 = "dm-"+block+" dm-border-color-"+color;
		String option2 = "dm-border-color-"+color+" dm-"+block;
		
		if (original.contains(option2) || original.contains(option1)){
			return true;
		}
		return false;
	}
	
	private static boolean isInHex(String s){
		if (s.length() != 6){
			return false;
		}
		for (char c : s.toCharArray()){
			if (!"0123456789ABCDEF".contains(""+c)){
				return false;
			}
		}
		return true;
	}
	
	public static StringBuilder createColoredBlocks(Set<String> blocks, Set<String> colors, String original){
		StringBuilder result = new StringBuilder();
		for(String block : blocks){
			String direction = block.substring(0, block.indexOf("-"));
			String edges = block.substring(block.indexOf("-")+1);
			for(String color : colors){
				boolean isInHex = isInHex(color);
				if (colorBlockStyleNescessary(block, color, original)){
					String modColor = isInHex ? "#" + color : color;
					result.append(MasterGenerator.generateRules(direction, edges, timeToAnimateEachEdge, 1, "solid", modColor, "linear")+"\n");
				}
			}
		}
		return result;
	}
	
	public static StringBuilder createDelays(String original){
		int index = 0;
		int foundAt = original.indexOf("dm-delay-", index);
		List<Double> delays = new ArrayList<Double>();
		while (foundAt >= 0){
			int a = original.indexOf(":", foundAt);
			int b = original.indexOf("{", foundAt);
			a = a == -1 ? 1000000000 : a;
			b = b == -1 ? 1000000000 : b;
			int end = Math.min(a,b);
			String s = original.substring(foundAt, end);
			s = s.trim().replace("-", "");
			int i = Integer.parseInt(s);
			delays.add(i * 100.0);
			index = foundAt+1;
		}
		
		StringBuilder sb = new StringBuilder();
		for (Double d : delays){
			sb.append(DelayRuleGenerator.makeRule(d));
		}
		return sb;
	}
	
	public static String base(){
		
		return ".dm{\n" + 
				"  position: relative;\n" + 
				"  border-top-color: transparent;\n" + 
				"  border-bottom-color: transparent;\n" + 
				"  border-right-color: transparent;\n" + 
				"  border-left-color: transparent;\n" + 
				"  border-style: solid;\n" + 
				"  border-width: 1px;\n" + 
				"  font-weight: bold;\n" + 
				"  animation-delay: 0s;\n" + 
				"  animation-iteration-count: 1 !important;\n" + 
				"  animation-fill-mode: both !important;\n" + 
				"}\n" + 
				"\n" + 
				".dm:before{\n" + 
				"  content: '';\n" + 
				"  display: block;\n" + 
				"  position: absolute;\n" + 
				"  z-index: 1;\n" + 
				"  border-style: solid;\n" + 
				"  border-color: black;\n" + 
				"  border-width: 1px 1px 0 0;\n" + 
				"  animation-delay: 0s;\n" + 
				"  animation-iteration-count: 1 !important;\n" + 
				"  animation-fill-mode: both !important;\n" + 
				"}";
	}
	
}
