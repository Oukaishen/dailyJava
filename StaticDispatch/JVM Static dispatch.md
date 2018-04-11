## JVM Static dispatch

kaishen, 11 Apr

We all knwo Java has two important attributes: **overload** and **override**. 

Here I want to make an extra notice is that their chinese should be **过载** and **重写**. 

How is JVM achieve **overload 过载** ？

Based on the \<深入理解Java虚拟机>, chapter 8, [book](https://book.douban.com/subject/24722612/).

> 在方法接受者，已经确定的前提下，使用哪一个过载版本，就完全取决于参数的数量和数据类型。
>
> 虚拟机（更准确来说是编译器）在“过载”时是通过参数的静态类型而不是实际类型作为判断依据的。



## Code Snippet

```java
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
```

The output of this code is 

```
Hi, This is Human.
Hi, This is Human.
```

