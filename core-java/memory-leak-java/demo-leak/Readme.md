# Memory Leak

uses [visualvm](https://visualvm.github.io/)

A Memory Leak is a situation when there are objects present in the heap that are no 
longer used, but the garbage collector is **unable to remove** them from memory and, thus
 they are unnecessarily maintained.
 
 There are two different types of objects that reside in Heap memory — **referenced**
and **unreferenced**. Referenced objects are those who have still active references
within the application whereas unreferenced objects don't have any active references.

### Types of Memory Leaks in Java

* Memory Leak Through static Field
(static fields have a life that usually matches the entire lifetime of the running application)

StaticTest Vs not-static test