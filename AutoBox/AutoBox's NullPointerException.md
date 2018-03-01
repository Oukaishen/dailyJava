# AutoBox's NullPointerException

kaishen, 1 Mar, 2018

AutoBox is a very useful feature in Jave since JDK1.5. The standard example can be found in [here](https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html).

But using this convenient has to take care of one thing -> **easy lead to NPE**.

Take a look at the following code Snippet

## Code Snippet

```java
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
```

The result of this code snippet is 

```
123
123
Exception in thread "main" java.lang.NullPointerException
	at testAndTry.TestAutoBox.main(TestAutoBox.java:10)
```

As shown in the example, it is quite easy to get an NPE if the Wrapper class actually is an **null**. Then it will cause an NPE when performing autoboxing. 

