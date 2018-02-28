# Catch can NOT catch from Catch

kaishen, 28 Feb, 2018

**What happens if I throw an Exception in the "Catch" clause?**

The quick answer is that: **The following Catch can Not catch this expcetion from the above Catch**.

---

Take a look at the following code

```java
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
```

The err output of this code is 

```
I am here in the first catch
In finally
Exception in thread "main" java.lang.RuntimeException: from first catch
	at testAndTry.TestException.main(TestException.java:17)
```

We can tell that, the same level, following Catch clause can Not catch the exceptions from the above Catch.

Also, once again, the finally clause will always run.