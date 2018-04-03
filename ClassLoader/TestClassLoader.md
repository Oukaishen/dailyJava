## TestClassLoader

kaishen, Apri 3

Today there is an interesting problem, asking why this code of snippet will cause error?

```java
package testAndTry;

public class TestClassLoader {
	public static void main(String [] args){
		new String();
	}
}

class String{
	public String(){
		System.out.println("test loader");
	}
}
```

The result is 

```
Error: Main method not found in class testAndTry.TestClassLoader, please define the main method as:
   public static void main(String[] args)
or a JavaFX application class must extend javafx.application.Application
```

As you can see the JVM complains that there is no main method, while we actually do have one.

Why is this? What happens? We know from JVM books that the JVM will load `java.lang.String` using bootstrap class loader. 

The reason here is that the `testAndTry.String` shadows `java.lang.String.`

In other words, this make the `public static void main(String [] args)` becomes to `public static void main(testAndTry.String [] args)` . So the JVM complains that there is no main entry point.

The following code can work:

```java
package testAndTry;

public class TestClassLoader {
	public static void main(java.lang.String [] args){
		new String();
	}
}
//// please comment this class after usage. Otherwise will cause this package wrong.
class String{
	public String(){
		System.out.println("test loader");
	}
}
```

The output is 

```
test loader
```

