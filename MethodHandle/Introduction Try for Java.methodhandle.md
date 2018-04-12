## Introduction Try for Java.methodhandle

kaishen, 12 Apr

Recently I was reading the **深入理解Java虚拟机**, which is quite a good book.

However, there maybe something that is no longer valid in the latest Java version.(I mean 1.8+, the book use 1.7). 

What I refer to is about the **java.lang.invoke.MethodHandle**, in chapter 8, page 268.



## Code Snippet

```java
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
```

The supposed output should be 

```
GrandFather is thinking
Father is thinking
GrandFather is thinking
```

While the **actual True** output is 

```
GrandFather is thinking
Father is thinking
Father is thinking
```

About the reason, I see someone on the Internet said that the JSR292 has changed something. At the current stage, I do not dive deep for why this happen. Mabye go there in the future when I have more thorough understanding towards JVM.



## Useful Links

[Zhihu for this question](https://www.zhihu.com/question/40427344)

[Some discussion on the Internet](https://my.oschina.net/floor/blog/1535062)

