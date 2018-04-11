package testAndTry;

/*
 * This class test the static dispatch of method overload
 * The example is taken from JVM book
 * 
 * */
public class TestStaticDispatch {
	
	public static abstract class Human {}
	
	public static class Man extends Human{}
	
	public static class Woman extends Human{}
	
	public void sayHi(Human h){
		System.out.println("Hi, This is Human.");
	}
	
	public void sayHi(Man m){
		System.out.println("Hi, This is Man");
	}
	
	public void sayHi(Woman w){
		System.out.println("Hi, This is Woman");
	}
	
	public static void main(String [] args){
		TestStaticDispatch test = new TestStaticDispatch();
		// both of these have the same "Static Type/ Apparent Type"= Human
		Human man = new Man();
		Human woman = new Woman();
		
		// method overload use the static type to choose method instead of runtime type
		test.sayHi(man);
		test.sayHi(woman);
	}
	
}
