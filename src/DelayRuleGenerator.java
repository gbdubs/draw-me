
public class DelayRuleGenerator {

	public static String makeRule(double seconds){
		String selector = ".dm.dm-delay-"+String.format("%.2f", seconds).replace('.', '-');
		String delay = String.format("{ animation-delay: %.2fs !important}",seconds);
		String noDelay = "{ animation-delay: -10000s !important }";
		String a = selector;
		String b = selector + ":before";
		String c = selector + ":after";
		return String.format("%s, %s, %s \n%s\n .dm-no-animate %s, .dm-no-animate %s, .dm-no-animate %s\n %s\n",a,b,c,delay,a,b,c,noDelay);
	}
	
	public static String makeKitchenSinkDelay(){
		StringBuilder sb = new StringBuilder();
		for (double d = 0; d < 60; d += .25){
			sb.append(makeRule(d));
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(Tools.minimize(makeKitchenSinkDelay()));
	}
	
}
