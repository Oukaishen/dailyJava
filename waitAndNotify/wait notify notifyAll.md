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

