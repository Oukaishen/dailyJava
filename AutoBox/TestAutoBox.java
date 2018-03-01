package testAndTry;

public class TestAutoBox {

	public static void main(String [] args){
		long a = 123l;
		System.out.println(someFunction(a));
		Long Wrapa = a;
		System.out.println(someFunction(Wrapa));
		long b = BigLong().longValue();
		System.out.print(b);
	}
	
	public static Object someFunction(Object arg){
		return arg.toString();
	}
	public static Long BigLong(){
		return null;
	}
}
