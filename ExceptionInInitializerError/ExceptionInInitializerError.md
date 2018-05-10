## ExceptionInInitializerError

kaishen, May 10, 2018

Currently I am playing with the `java.lang.NoClassDefFoundError` , and sometimes this error is related to the error happened in Initialization. At those time, `java.lang.ExceptionInInitializerError ` will happen. And here shows one of the example that will cause this error.

```java
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
```

This code will lead thos such an error

```
Exception in thread "main" java.lang.ExceptionInInitializerError
Caused by: java.lang.NullPointerException
	at testAndTry.TestExceptionInInitializerError.<init>(TestExceptionInInitializerError.java:13)
	at testAndTry.TestExceptionInInitializerError.<clinit>(TestExceptionInInitializerError.java:8)
```

The core is this line

```java
	private static TestExceptionInInitializerError teII = new TestExceptionInInitializerError();
```

And then lead to this line

```java
private TestExceptionInInitializerError(){
		map.put("Make","Error");
	}
```

Hence there will occur an NullPointerException. This story tells us that we have to take care of our code's static initialization procedure.