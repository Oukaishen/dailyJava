## ThreadPoolExecutor

created by Kaishen, 12 Feb, 2018

#### Easy Understanding

`ThreadPoolExecutor` let us customize our own thread-pool, which handles the thread schedule, create new thread, etc. This class is the underlying things behind many useful class such as `Executors.newCachedThreadPool`, `Executors.newFixedThreadPool`. 

***In short, we can use ThreadpoolExecutor to create our own thread pool that feeds special tasks.***

#### Deep Understanding

If we need thorough understanding of this class, we have to dive into the JDK source code. So this post is probably a note for reading the source code.

Maybe it's appropriate to start from the description inside the JDK class.

> The main pool control state, **ctl**, is an atomic integer packing two conceptual fields
>
> **workerCount**, indicating the effective number of threads 
>
> **runState**, indicating whether running, shutting down etc

This description is on top of the ThreadPoolExecutor source code, which means that the ctl is of great importance. Ctl has two information as stating above, and they are packed together for convenience.

Look at these important runStates.

```java
private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
private static final int COUNT_BITS = Integer.SIZE - 3; // 32 - 3 = 29
private static final int CAPACITY   = (1 << COUNT_BITS) - 1; // last 29 bits are all ones

// runState is stored in the high-order bits
private static final int RUNNING    = -1 << COUNT_BITS;      //111  highest 32-30 bit
private static final int SHUTDOWN   =  0 << COUNT_BITS;      //000
private static final int STOP       =  1 << COUNT_BITS;      //001
private static final int TIDYING    =  2 << COUNT_BITS;      //010
private static final int TERMINATED =  3 << COUNT_BITS;      //011
```

Based on the JDK doc, we list the meaning of different status:

> **RUNNING:**  Accept new tasks and process queued tasks
>
> **SHUTDOWN:** Don't accept new tasks, but process queued tasks
>
> **STOP:** Don't accept new tasks, don't process queued tasks, and interrupt in-progress tasks
>
> **TIDYING:**  All tasks have terminated, workerCount is zero, the thread transitioning to state TIDYING will run the terminated() hook method
>
> **TERMINATED:** terminated() has completed



#### Reference Links

https://www.jianshu.com/p/d2729853c4da?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation

https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ThreadPoolExecutor.html