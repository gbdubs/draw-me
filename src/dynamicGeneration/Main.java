package dynamicGeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import dynamicGeneration.structures.Page;
import dynamicGeneration.util.Minimization;

public class Main {

	static String test1 = "ccw rtl 10 solid black 0 1000 linear white white white black 1";
	
	static String personal = "ccw lb 5 solid black 100 100 linear transparent transparent transparent black 1\n" + 
			"ccw rt 5 solid DE1718 200 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid DE1718 400 100 linear transparent transparent transparent black 1\n" + 
			"ccw l 5 solid DE1718 300 100 linear transparent transparent transparent black 1\n" + 
			"cw rb 5 solid black 200 100 linear transparent transparent transparent black 1\n" + 
			"ccw tlb 5 solid black 300 100 linear transparent transparent transparent black 1\n" + 
			"ccw lbr 5 solid black 375 100 linear transparent transparent transparent black 1\n" + 
			"ccw br 5 solid black 000 100 linear transparent transparent transparent black 1\n" + 
			"ccw lb 5 solid black 425 100 linear transparent transparent transparent black 1\n" + 
			"ccw lb 5 solid black 475 100 linear transparent transparent transparent black 1\n" + 
			"cw rbl 5 solid black 575 100 linear transparent transparent transparent black 1\n" + 
			"ccw br 5 solid black 000 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 100 100 linear transparent transparent transparent black 1\n" + 
			"ccw tl 5 solid black 200 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 100 100 linear transparent transparent transparent black 1\n" + 
			"ccw tl 5 solid black 200 100 linear transparent transparent transparent black 1\n" + 
			"ccw lb 5 solid EFBD26 300 100 linear transparent transparent transparent black 1\n" + 
			"cw rb 5 solid EFBD26 400 100 linear transparent transparent transparent black 1\n" + 
			"cw rb 5 solid 1C55BF 300 100 linear transparent transparent transparent black 1\n" + 
			"ccw lb 5 solid 1C55BF 400 100 linear transparent transparent transparent black 1\n" + 
			"cw lt 5 solid 1C55BF 500 100 linear transparent transparent transparent black 1\n" + 
			"ccw rt 5 solid EFBD26 300 100 linear transparent transparent transparent black 1\n" + 
			"ccw tlb 5 solid EFBD26 400 100 linear transparent transparent transparent black 1\n" + 
			"ccw lbr 5 solid black 400 100 linear transparent transparent transparent black 1\n" + 
			"ccw tl 5 solid black 300 100 linear transparent transparent transparent black 1\n" + 
			"cw blt 5 solid black 475 100 linear transparent transparent transparent black 1\n" + 
			"ccw l 5 solid black 400 100 linear transparent transparent transparent black 1\n" + 
			"ccw tl 5 solid black 300 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 400 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 475 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 525 100 linear transparent transparent transparent black 1\n" + 
			"ccw br 5 solid black 400 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 425 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 500 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 550 100 linear transparent transparent transparent black 1\n" + 
			"ccw br 5 solid black 425 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 450 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 525 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 575 100 linear transparent transparent transparent black 1\n" + 
			"ccw br 5 solid black 450 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 475 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 550 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 600 100 linear transparent transparent transparent black 1\n" + 
			"ccw br 5 solid black 475 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 500 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 575 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 625 100 linear transparent transparent transparent black 1\n" + 
			"ccw br 5 solid black 500 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 525 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 600 100 linear transparent transparent transparent black 1\n" + 
			"ccw br 5 solid black 525 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 550 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 625 100 linear transparent transparent transparent black 1\n" + 
			"ccw br 5 solid black 550 100 linear transparent transparent transparent black 1\n" + 
			"cw tr 5 solid black 575 100 linear transparent transparent transparent black 1\n" + 
			"ccw br 5 solid black 575 100 linear transparent transparent transparent black 1";
	
	
	public static void main(String[] args){
		Page p = new Page(personal);
		String css = p.generateCss();
		saveToFiles(css, "site/result");
	}
	
	
	public static void saveToFiles(String css, String fileName){
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
