# CopyOnWriteArraySet

kaishen, 20 Mar, 2018

Recently I encounter the use of this class in Web-App scenario. I refer to this [post](http://www.cnblogs.com/skywang12345/p/3498497.html) and just mark down some own thought.

+ inherit from `AbstratSet` 
+ best suited for applications in whcih set size stay **small**, **read-only**. 
+ Thread-safe