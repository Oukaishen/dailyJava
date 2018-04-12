package testAndTry;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class TestMethodHandle {

	public static class GrandFather{
		void think(){
			System.out.println("GrandFather is thinking");
		}
	}
	
	public static class Father extends GrandFather{
		void think(){
			System.out.println("Father is thinking");
		}
	}
	
	public static class Son extends Father{
		void think(){
			// ToDo: how can we invoke GrandFather's method here?
			
			try{
				
				MethodType mt = MethodType.methodType(void.class);
				MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "think", mt,this.getClass());
				mh.invoke(this);
				
			}catch(Throwable e){
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String [] args){
		new GrandFather().think();
		new Father().think();
		new Son().think();
	}
}
