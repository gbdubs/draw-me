package dynamicGeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import dynamicGeneration.structures.Page;
import dynamicGeneration.util.Minimization;

public class Main {

	public static String test1 = "ccw rtl 10 solid black 0 1000 linear white white white black 1";
	
	public static String personal = "ccw lb 5 solid black 100 100 linear transparent transparent transparent black 1\n" + 
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
	
	public static String htmlPersonal = "<html>\n" + 
			"   <head>\n" + 
			"      <link href='https://fonts.googleapis.com/css?family=Lusitana:400,700' rel='stylesheet' type='text/css'>\n" + 
			"      <link href='result.css' rel='stylesheet' type='text/css'>\n" + 
			"      <link href='style.css' rel='stylesheet' type='text/css'>\n" + 
			"      \n" + 
			"   </head>\n" + 
			"   <body>\n" + 
			"      <div class=\"dm-wrapper dm-border-width-5\">\n" + 
			"      <div class=\"title-wrapper\">\n" + 
			"         <div class=\"title dm-ccw-lb dm dm-delay-1-00\">\n" + 
			"            Grady Ward        \n" + 
			"            <div class=\"decoration-a dm dm-ccw-rt dm-delay-2-00 dm-border-color-DE1718\"></div>\n" + 
			"            <div class=\"decoration-b dm dm-cw-tr dm-delay-4-00 dm-border-color-DE1718\">\n" + 
			"               <div class=\"decoration-c dm dm-ccw-l dm-delay-3-00 dm-border-color-DE1718\"></div>\n" + 
			"            </div>\n" + 
			"         </div>\n" + 
			"         <div class=\"subtitle dm-cw-rb dm dm-delay-2-00\">\n" + 
			"            Engineer        \n" + 
			"            <div class=\"menu\">\n" + 
			"              <a href=\"mailto:grady.b.ward@gmail.com\" class=\"item dm dm-ccw-tlb dm-delay-3-00\">Contact</a>\n" + 
			"              <a class=\"item dm dm-ccw-lbr dm-delay-3-75 no-top\">About<div class=\"menu-selector-d dm dm-ccw-br dm-delay-0-00\"></div></a>\n" + 
			"              <a href=\"https://www.linkedin.com/in/gradyward\" target=\"_blank\" class=\"item dm dm-ccw-lb dm-delay-4-25 no-top\">LinkedIn</a>\n" + 
			"              <a href=\"https://www.github.com/gbdubs\" target=\"_blank\"  class=\"item dm dm-ccw-lb dm-delay-4-75 no-top\">Github</a>\n" + 
			"              <a class=\"item dm dm-cw-rbl dm-delay-5-75 no-top\">Portfolio<div class=\"menu-selector-c dm dm-ccw-br dm-delay-0-00\"></div></a>\n" + 
			"            </div>\n" + 
			"            <div id=\"portfolio-lines\" class=\"menu-selector-a dm dm-cw-tr dm-delay-1-00\"><div class=\"menu-selector-b dm dm-ccw-tl dm-delay-2-00\"></div></div>\n" + 
			"            <div id=\"about-lines\" class=\"menu-selector-a dm dm-cw-tr dm-delay-1-00\"><div class=\"menu-selector-b dm dm-ccw-tl dm-delay-2-00\"></div></div>\n" + 
			"            <div class=\"decoration-i dm-delay-3-00 dm dm-ccw-lb dm-border-color-EFBD26\"></div>\n" + 
			"            <div class=\"decoration-j dm-delay-4-00 dm dm-cw-rb dm-border-color-EFBD26\"></div>\n" + 
			"         </div>\n" + 
			"         <div class=\"decoration-d dm dm-cw-rb dm-delay-3-00 dm-border-color-1C55BF\">\n" + 
			"            <div class=\"decoration-e dm dm-ccw-lb dm-delay-4-00 dm-border-color-1C55BF\">\n" + 
			"               <div class=\"dm-delay-5-00 dm-border-color-1C55BF decoration-f dm dm-cw-lt\"></div>\n" + 
			"            </div>\n" + 
			"         </div>\n" + 
			"         <div class=\"decoration-g dm-delay-3-00 dm dm-ccw-rt dm-border-color-EFBD26\"></div>\n" + 
			"         <div class=\"decoration-h dm-delay-4-00 dm dm-ccw-tlb dm-border-color-EFBD26\"></div>\n" + 
			"      </div><br><div id=\"about-wrapper\" class=\"about-wrapper dm-border-width-5 dm-wrapper\"> \n" + 
			"        <div class=\"about dm dm-delay-4-00 dm-ccw-lbr\">\n" + 
			"          <div class=\"about-title dm dm-delay-3-00 dm-ccw-tl\">About</div>\n" + 
			"          <div class=\"about-description\">\n" + 
			"            <div class=\"about-image dm dm-delay-4-75 dm-cw-blt\"><img src=\"grady.jpg\"></div>\n" + 
			"            <p>\n" + 
			"              Grady Ward is a recent college graduate from Brandeis University. In August, he is moving to the Bay Area as a software engineer for Google. In the past he has worked for edX, Jumpstart and Brandeis' Student Union.\n" + 
			"            </p><p>\n" + 
			"              Before moving to San Francisco, he is spending the summer reading, writing, working on some fun projects, and spending time with family. On the summer reading list: \"Mules and Men\", \"Gorilla, My Love\", \"The Kite Runner\", \"I am Malala\", and \"I Know Why the Caged Bird Sings\".\n" + 
			"            </p><p>\n" + 
			"              He has worked extensively in web development as a full stack developer, on Node, Python and Java stacks, over the Amazon and Google clouds. His research interests have centered on computational complexity and algorithm design.\n" + 
			"            </p><p>\n" + 
			"              In his spare time, he enjoys running, origami, hiking and reading.\n" + 
			"            </p>\n" + 
			"          </div>\n" + 
			"        </div>\n" + 
			"      </div><div id=\"portfolio-wrapper\" class=\"portfolio-wrapper dm-border-width-5 dm-wrapper\"> \n" + 
			"        <div class=\"portfolio dm dm-delay-4-00 dm-ccw-l\">\n" + 
			"          <div class=\"portfolio-title dm dm-delay-3-00 dm-ccw-tl\">Portfolio</div>\n" + 
			"          <div class=\"portfolio-item\">\n" + 
			"            <span class=\"name dm dm-delay-4-00 dm-cw-tr\">\n" + 
			"              Senior Thesis\n" + 
			"            </span><a target=\"_blank\" href=\"https://github.com/gbdubs/thesis\" class=\"code dm dm-delay-4-75 dm-cw-tr\">\n" + 
			"              Code\n" + 
			"            </a><a target=\"_blank\" href=\"https://github.com/gbdubs/thesis/raw/master/Documentation%20%2B%20Reports/THESIS/thesis.pdf\" class=\"result dm dm-delay-5-25 dm-cw-tr\">\n" + 
			"              Result\n" + 
			"            </a>\n" + 
			"            <div class=\"description dm dm-delay-4-00 dm-ccw-br\">\n" + 
			"              A senior honors thesis on the graph isomorphism problem, and the ways that that problem impacts related questions of random graph generation, theoretical analysis of associated problems, and an invariant for analysis called the \"cycles\" invariant, which counts the number of closed walks through each vertex in a graph. Work primarily uses C (for computationally expensive operations) and Matlab (for visualization and small set analysis). In May 2016, this work received Highest Honors from Brandeis University, and won the \"Computer Science Prize for Outstanding Achievement\".\n" + 
			"            </div>\n" + 
			"          </div>\n" + 
			"          <div class=\"portfolio-item\">\n" + 
			"            <span class=\"name dm dm-delay-4-25 dm-cw-tr\">\n" + 
			"              CalcU\n" + 
			"            </span><a target=\"_blank\"  href=\"https://github.com/gbdubs/calcu\" class=\"code dm dm-delay-5-00 dm-cw-tr\">\n" + 
			"              Code\n" + 
			"            </a><a target=\"_blank\"  href=\"http://calcu.education\" class=\"result dm dm-delay-5-50 dm-cw-tr\">\n" + 
			"              Result\n" + 
			"            </a>\n" + 
			"            <div class=\"description dm dm-delay-4-25 dm-ccw-br\">\n" + 
			"              CalcU is a scalable, responsive and accessible web application that serves as a course aid for Calculus students. It scrapes the web for calculus resources, and uses user feedback alongside out of the box machine learning to direct students to problems, questions and content that best fits their learning needs and current understandings. Received a competitive SPARK grant to hire three interns for a summer of work, completed in August of 2015.\n" + 
			"            </div>\n" + 
			"          </div>\n" + 
			"          <div class=\"portfolio-item\">\n" + 
			"            <span class=\"name dm dm-delay-4-50 dm-cw-tr\">\n" + 
			"              Draw Me CSS!\n" + 
			"            </span><a target=\"_blank\" href=\"https://github.com/gbdubs/draw-me\" class=\"code dm dm-delay-5-25 dm-cw-tr\">\n" + 
			"              Code\n" + 
			"            </a><a target=\"_blank\" class=\"result dm dm-delay-5-75 dm-cw-tr\">\n" + 
			"              Result\n" + 
			"            </a>\n" + 
			"            <div class=\"description dm dm-delay-4-50 dm-ccw-br\">\n" + 
			"              A tool for the dynamic generation of CSS styles which draw the borders of elements on the page to allow \"pipe\" animations to make it appear that the page is being drawn before the viewer (this page uses this library). Started small scale, trying to just get a single little circular border to work, then expanded up, adding support for border colors, thicknesses, dash-styles, animation speeds, animation delays, different edges, and different animation directions. To include every one of these options across every possible permutation would be a 3MB CSS file (not-optimal), so I built a web service that dynamically calculates the styles that are on each page, and only includes those styles. The result is a magical animation, which can be added to an existing bordered webpage in just minutes, with a CSS file that is typically under 1KB. Completed in July of 2016.\n" + 
			"            </div>\n" + 
			"          </div>\n" + 
			"          <div class=\"portfolio-item\">\n" + 
			"            <span class=\"name dm dm-delay-4-75 dm-cw-tr\">\n" + 
			"              GradyDocs\n" + 
			"            </span><a target=\"_blank\" href=\"https://github.com/gbdubs/gradydocs\" class=\"code dm dm-delay-5-50 dm-cw-tr\">\n" + 
			"              Code (No Server)\n" + 
			"            </a><a target=\"_blank\" href=\"http://www.gradydocs.com/\" class=\"result dm dm-delay-6-00 dm-cw-tr\">\n" + 
			"              Result\n" + 
			"            </a>\n" + 
			"            <div class=\"description dm dm-delay-4-75 dm-ccw-br\">\n" + 
			"              Grady Docs: GoogleDocs, but shittier! I worked on this project to teach myself NodeJS on a relatively easy problem. The project attempted to emulate the functionality of \"GoogleDocs\", which is a platform that allows collaborative editing across multiple computers. The final result uses a data-driven client-side editing methodology alongside websockets on the backend to deliver solid real-time simultaneous editing. Built in NodeJS over AWS. The problem is that a couple of people actually were trying to use it for shared text document storage (and my solution was highly inefficient in terms of storage), so the server time became materially expensive and I shut down the server, but left up the client facing elements. Started and completed over the winter holiday, December of 2015.\n" + 
			"            </div>\n" + 
			"          </div>\n" + 
			"          <div class=\"portfolio-item\">\n" + 
			"            <span class=\"name dm dm-delay-5-00 dm-cw-tr\">\n" + 
			"              Student Union\n" + 
			"            </span><a target=\"_blank\" href=\"https://github.com/gbdubs/StudentUnion\" class=\"code dm dm-delay-5-75 dm-cw-tr\">\n" + 
			"              Code\n" + 
			"            </a><a target=\"_blank\" href=\"http:union.brandeis.io\" class=\"result dm dm-delay-6-25 dm-cw-tr\">\n" + 
			"              Result\n" + 
			"            </a>\n" + 
			"            <div class=\"description dm dm-delay-5-00 dm-ccw-br\">\n" + 
			"              In the summer of 2015, I was tasked with creating a new Student Union website, which I did using a simple static page generator, to allow someone else to do the updates. Though this solution worked for the 2015-2016 academic year, unfortunately, it became apparent at the end of that year that the solution was too rigid to fit the ever changing needs of a student organization with a wide range of functions and sub-groups. To combat this, I created a web application which allows dynamic WYSIWYG editing of static pages, using GithubPages as the true static page server, and a Google App Engine backend to force authentication and allow metadata storage and page updates. The result has been widely used by all members of the Student Union, and provides a secure, version controlled way for every member to update, create and delete pages with no technical experience. Completed in April of 2016.\n" + 
			"            </div>\n" + 
			"          </div>\n" + 
			"          <div class=\"portfolio-item\">\n" + 
			"            <span class=\"name dm dm-delay-5-25 dm-cw-tr\">\n" + 
			"              WSMD?\n" + 
			"            </span><a target=\"_blank\" href=\"http://gbdubs.github.io/whatshouldmaddiedo/\" class=\"result dm dm-delay-6-00 dm-cw-tr\">\n" + 
			"              Result\n" + 
			"            </a>\n" + 
			"            <div class=\"description dm dm-delay-5-25 dm-ccw-br\">\n" + 
			"              A tool for the indecisive, built in 2 hours as a challenge. November of 2015.\n" + 
			"            </div>\n" + 
			"          </div>\n" + 
			"          <div class=\"portfolio-item\">\n" + 
			"            <span class=\"name dm dm-delay-5-50 dm-cw-tr\">\n" + 
			"              AI Science Test\n" + 
			"            </span><a target=\"_blank\" href=\"https://github.com/gbdubs/AIScienceTest\" class=\"code dm dm-delay-6-25 dm-cw-tr\">\n" + 
			"              Code\n" + 
			"            </a>\n" + 
			"            <div class=\"description dm dm-delay-5-50 dm-ccw-br\">\n" + 
			"              A machine-learning project for an AI class which competed on Kaggle and got in the top 20 out of 500+ teams. Simply used TensorFlow alongside some clever parameter domain space testing, using Wikipedia as a corpus text. My first experiment in ML. September of 2015.\n" + 
			"            </div>\n" + 
			"          </div>\n" + 
			"          <div class=\"portfolio-item\">\n" + 
			"            <span class=\"name dm dm-delay-5-75 dm-cw-tr\">... Many More</span>\n" + 
			"            <div class=\"description dm dm-delay-5-75 dm-ccw-br\">\n" + 
			"              These are just the most recent highlights of my coding life.  To check out the full range of what I have worked on in the last few years, check out <a target=\"_blank\"href=\"http://www.github.com/gbdubs\">my github profile</a>. \n" + 
			"            </div>\n" + 
			"          </div>\n" + 
			"        </div>\n" + 
			"      </div>\n" + 
			"      </div>\n" + 
			"      <script src=\"https://code.jquery.com/jquery-2.2.4.min.js\" integrity=\"sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=\" crossorigin=\"anonymous\"></script>\n" + 
			"      <script src=\"script.js\"></script>\n" + 
			"   </body>\n" + 
			"</html>";
	
	public static void main(String[] args){
		long then = System.currentTimeMillis();
		Page p = new Page(htmlPersonal);
		String css = p.generateCss();
		System.out.println("CALCULATION TOOK "+(System.currentTimeMillis() - then)+" milliseconds");
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
