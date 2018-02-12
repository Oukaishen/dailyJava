## Future 

created by kaishen, 12 Feb, 2018

#### Easy Understanding

Future is a class of JDK, inside the `java.util.concurrent` package. Based on the original JDK description, it is used for:

> Future represents the result of an asynchronous computation. ... 

This is to say, in the "future", we can come back and get the result, which is extremely useful in some scenarios. Take this as an exapmle,

```java
// ... do something before
Future<Integer> futureResult = es.submit(new CallableDemo());
// ... continue to do your things, no need to wait
// after some time you can come back and get the actual result
int actualResult = futureResult.get(); // just an example, need to wrap it in try-catch
```

At this point, please ignore the right hand side of the code `es.submit()` , it is just an method that give us an Future. This is useful because, we wont be blocked at the line of code. We don't need to wait until the `CallableDemo` finish. Future class also provides some methods that can tell us if the actual result is ready or not, like `isDone`. 

***In short, Future class give us a way to compute "asynchronously", it provides a "fake"/"pretend" result instantly. Dont need to wait, just go ahead in the code. Come back and retrieve the actual result when it is ready.***



#### Deep Understanding

Here presents a simple example of Future.

Because we want to utilize the built-in method in the `ExecutorService.Class` , we first take a look of the method signature.

```java
<T> Future<T> submit(Callable<T> task);
```

Since the `submit` method requires a `Callable` as input, then we define one like this.

```java
import java.util.concurrent.Callable;

public class CallableDemo implements Callable<Integer>{
	private int sum = 0;

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inside the Callable method");
		Thread.sleep(5000);
		for(int i = 0; i< 50; i++){
			sum += i;
		}
		System.out.println("Callable complete");
		return sum;
	}
}
```

After that, we create the test class as follows:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureTest {
	public static void main(String[] args){
		ExecutorService es = Executors.newSingleThreadExecutor();
//		CallableDemo demo = new CallableDemo();
		Future<Integer> futureResult = es.submit(new CallableDemo());
		es.shutdown();
		try{
//			Thread.sleep(2000);
			System.out.println("Main Thread is working on something else");
//			System.out.println(futureResult.isDone()); /// this line is ok
			while(!futureResult.isDone()){
				//Print too much will cause the eclipse crash
//				System.out.println("The Future task is not done yet..."); 
//				Thread.sleep(1000);
			}
            // get the actual result
			System.out.println("The future Task result is "+ futureResult.get());
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("The main Thread ends. ");
	}
}
```

The result of this test class is

```
Inside the Callable method
Main Thread is working on something else
Callable complete
The future Task result is 1225
The main Thread ends. 
```



#### Reference Links

(http://blog.csdn.net/javazejian/article/details/50896505)



