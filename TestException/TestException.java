package testAndTry;

class subException extends Exception{
	
}

public class TestException {

	public static void main(String[] args) throws subException {
		// TODO Auto-generated method stub
//		throwException();
		try{
			throwSub();
		}catch(subException e){
			System.err.println("I am here in the first catch");
			// this shows that the same level catch can not catch excepetion from catch -.-
			throw new RuntimeException("from first catch");
//			throw new subException();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			System.err.println("In finally");
		}
	}

	public static void throwException(){
		try {
			throw new RuntimeException();
		}
		finally{		
		}
	}
	
	public static void throwSub() throws Exception{
		throw new subException();
	}
}
