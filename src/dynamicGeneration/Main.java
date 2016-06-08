package dynamicGeneration;

import dynamicGeneration.structures.Page;

public class Main {

	static String test1 = "ccw rtl 1 solid black 0 1000 linear white white white black 1";
	
	public static void main(String[] args){
		Page p = new Page(test1);
		System.out.println(p.generateCss());
	}
	
}
