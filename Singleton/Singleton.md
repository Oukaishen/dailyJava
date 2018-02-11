## Singleton 

Created by Kaishen, 11 Feb, 2018

***version1:*** Lazy implementation

```java
// Version 1, not thread-safe. Lazy implementation.
public class SingletonV1 {
	// make the constructor private
	private SingletonV1(){}
	
	// the Singleton object
	private static SingletonV1 instance = null;
	
	// the factory method
	public static SingletonV1 getInstance(){
		if(instance == null){
			// here is not thread-safe.
			/*there may be two thread come in this block at the same time 
			 and create the instance twice
			 */
			instance = new SingletonV1();
		}
		return instance;
	}
}
```

This is not thread-safe. The key flaw is inside this line `if(instance == null)` . Suppose there are two threads A & B, both go inside the code block, then the instance will be created twice.

***version2:*** synchronized, double check

```java
// Version 2, not thread-safe. Still have a little bit flaw
public class SingletonV2 {
	// make the constructor private
		private SingletonV2(){}
		
		// the Singleton object
		private static SingletonV2 instance = null;
		
		// the factory method
		public static SingletonV2 getInstance(){
			if(instance == null){
				// here is not thread-safe.
				/*there may be two thread come in this block at the same time 
				 and create the instance twice
				 */
				synchronized(SingletonV2.class){
					// double check
					/*if thread A & B both inside the first if
					 * then after A creates the SingletonV2,
					 * B may still initial the second time
					 * so we need the double check
					 * */
					if(instance == null){
						instance = new SingletonV2();
					}
				}
			}
			return instance;
		}
}
```

Still not thread safe. The reason is that JVM might reorder the code. 

```java
memory = allocate(); //allocate the mermory room
ctorInstance(memory); // do the initialization
instance = memory; 
```

to

```java
memory = allocate();
instance = memory;
ctorInstance(memory);
```



Then if thread A is initializing the instance, thread B can detect the `instance != null` , and it will return the unprepared (un-ready) instance. 

***version3:*** volatile

```java
// V3 add the volatile to prevent the JVM re-order.
public class SingletonV3 {
	// make the constructor private
		private SingletonV3(){}
		
		// the Singleton object
		// use the volatile keyword to prevent the JVM optimizing and re-order the code
		private volatile static SingletonV3 instance = null;
		
		// the factory method
		public static SingletonV3 getInstance(){
			if(instance == null){
				// here is not thread-safe.
				/*there may be two thread come in this block at the same time 
				 and create the instance twice
				 */
				synchronized(SingletonV3.class){
					// double check
					/*if thread A & B both inside the first if
					 * then after A creates the SingletonV2,
					 * B may still initial the second time
					 * so we need the double check
					 * */
					if(instance == null){
						instance = new SingletonV3();
					}
				}
			}
			return instance;
		}
}
```



***More About Singleton in java, to be continued…***

 



