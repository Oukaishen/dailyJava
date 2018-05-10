package testAndTry;

import java.util.HashMap;
import java.util.Map;

public class TestExceptionInInitializerError {
	
	private static TestExceptionInInitializerError teII = new TestExceptionInInitializerError();
	
	private static Map<String, String> map = new HashMap<>();
	
	private TestExceptionInInitializerError(){
		map.put("Make","Error");
	}
	
	public static TestExceptionInInitializerError getInstance(){
		return teII;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestExceptionInInitializerError.getInstance();
	}

}
