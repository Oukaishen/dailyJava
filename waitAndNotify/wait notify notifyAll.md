## wait notify notifyAll

kaishen, 4 Arpil

In this post, we do not compare `wait()` and `sleep()`. Instead, we focus on how the `wait()` method is utilized in the multiple threads situations. 

 **Why we need wait method? What can it give us?**

Consider this situation, we have two threads, and we want them to cooperate. For example, at this time stamp, Thread-A need to wait until Thread-B is ready.

![A wait the B finish](./Picture1.png)

> Of course we can use a for/while loop to keep asking Thread-B: Are you ready? This is just called busy waiting. And definitely this is bad because A will keep its resources. Here one example of resource is lock. If we use the busy waiting, other thread cannot get the lock that A hold.

So, `wait()` can alleviate this kind of problem.

As mentioned above, the `wait()` will release the lock, so you can think of that:

> You must pust wait() inside Synchronized block. Because you can release only after you acquire.

If you don put the `wait()` inside the `synchronized` block, you will get `IllegalMonitorStateException` at runtime. 

> You should always wrap the wait() method inside the while loop. 

This is recommended in the standard Java [doc](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html) . There are many reasons, one possible reason is in (TIJ, page 1202) is that:

> By the time this task awaken from its wait(), its possible that some other task will have changed the things such that this task is unable to perform or is uninterested in performing it operation at this time. Again, it should be resuspended by calling wait() again.

So normally, it is recommend to write this way:

```java
synchronized (obj) {
         while (<condition does not hold>)
             obj.wait(timeout);
         ... // Perform action appropriate to condition
     }
```



### Code Snippet

Here I use `wait()` and `notify()` to write a program that prints "121212121212"

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Print1Thread implements Runnable{

	private Object lock;
	
	public Print1Thread(Object obj){
		lock = obj;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!Thread.interrupted()){
			synchronized(lock){
				while( !AB12byWait.flag ) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("1");
				AB12byWait.flag = false;
				lock.notify();
			}
		}
	}
	
}

class Print2Thread implements Runnable{

	private Object lock;
	
	public Print2Thread(Object obj){
		lock = obj;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!Thread.interrupted()){
			synchronized(lock){
				while( AB12byWait.flag ) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("2");
				AB12byWait.flag = true;
				lock.notify();
			}
		}
	}
	
}


public class AB12byWait {
	public static boolean flag;
	public static void main(String [] args) throws InterruptedException{
		ExecutorService exec = Executors.newCachedThreadPool();
		Object lock = new Object();
		exec.execute(new Print1Thread(lock));
		exec.execute(new Print2Thread(lock));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

```

The output of this program is 

```
2
1
2
1
2
1
2
1
2
1
2
1
2
1
2
1
2
1
2
```

