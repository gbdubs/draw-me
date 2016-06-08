package dynamicGeneration.structures;

public enum Edge {
	R, L, T, B;

	public static Edge[] all = {R, L, T, B};
	
	public Edge opposite(){
		return opposite(this);
	}
	
	public static Edge opposite(Edge e){
		switch(e){
			case R:
				return L;
			case L:
				return R;
			case T:
				return B;
			case B:
				return T;
			default:
				return null;
		}
	}
	
	public String fullName(){
		return fullName(this);
	}
	
	public static String fullName(Edge e){
		switch(e){
			case R:
				return "right";
			case L:
				return "left";
			case T:
				return "top";
			case B:
				return "bottom";
			default:
				return null;
		}
	}
	
	public char letterName(){
		return letterName(this);
	}
	
	public static char letterName(Edge e){
		return e.fullName().charAt(0);
	}
	
	private static Edge ccwNext(Edge e){
		switch(e){
			case R:
				return T;
			case L:
				return B;
			case T:
				return L;
			case B:
				return R;
			default:
				return null;
		}
	}
	
	private static Edge cwNext(Edge e){
		switch(e){
			case R:
				return B;
			case L:
				return T;
			case T:
				return R;
			case B:
				return L;
			default:
				return null;
		}
	}
	
	private static Edge ccwPrev(Edge e){
		return cwNext(e);
	}
	
	private static Edge cwPrev(Edge e){
		return ccwNext(e);
	}
	
	public Edge prev(String rotDir){
		return prev(rotDir, this);
	}
	
	public static Edge prev(String rotationDirection, Edge e){
		if (rotationDirection.equals("cw")){
			return cwPrev(e);
		}
		if (rotationDirection.equals("ccw")){
			return ccwPrev(e);
		}
		return null;
	}
	
	public Edge next(String rotDir){
		return next(rotDir, this);
	}
	
	public static Edge next(String rotationDirection, Edge e){
		if (rotationDirection.equals("cw")){
			return cwNext(e);
		}
		if (rotationDirection.equals("ccw")){
			return ccwNext(e);
		}
		return null;
	}
	
	public static Edge get(String s){
		return get(s.charAt(0));
	}
	
	public static Edge get(char c){
		switch(c){
			case 'r':
				return R;
			case 'l':
				return L;
			case 't':
				return T;
			case 'b':
				return B;
			default:
				return null;
		}
	}
}
