package dynamicGeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import dynamicGeneration.structures.Page;
import dynamicGeneration.util.Minimization;

public class Main {

	public static void main(String[] args){
		execute("DrawMeCss/index.html", "DrawMeCss/resulting.css");
	}
	
	private static void execute(String inputHtmlFileName, String outputCssFileName){
		long then = System.currentTimeMillis();
		Page p = new Page(readFile(inputHtmlFileName));
		String css = p.generateCss();
		String header = String.format("\n/* DRAWMECSS - By Grady Ward (grady.b.ward@gmail.com)  */\n/* Input file = [%s], Today = [%s] */\n", inputHtmlFileName, (new Date()).toString());
		header += String.format("/* Version = [1.0] , Calculation Time = [%.3fs] */\n\n", (System.currentTimeMillis() - then) / 1000.0);
		saveToFiles(header+css, outputCssFileName);
	}
	
	private static String readFile(String filename){
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(filename));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return new String(encoded, Charset.defaultCharset());
	}
	
	private static void saveToFiles(String css, String fileName){
		if (fileName.contains(".css")){
			fileName = fileName.substring(0, fileName.indexOf(".css"));
		}
		File f = new File(fileName+".css");
		try {
			PrintWriter pw = new PrintWriter(f);
			pw.print(css);
			pw.close();
			f = new File(fileName+".min.css");
			css = Minimization.minimize(css);
			pw = new PrintWriter(f);
			pw.print(css);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
