# PerformantContinuousTesting

## Running test cases parallely in TestNG

TestNG is a testing framework for Java that helps to organize tests in a structured way and enhances maintainability and readability to the scripts. TestNG has made it easier for automation testers owing to its large feature set. One of which is parallel testing or parallel execution. TestNG provides an auto-defined XML file, where one can set the parallel attribute to method/tests/classes and by using the concept of multi-threading of Java, one can set the number of threads, one wants to create for parallel execution. Below is the structure for defining this attribute in the TestNG XML:

```
<suite name="Parallel_Testing" parallel="methods" thread-count="2">
```
The parallel attribute can be extended for multiple values, as below:

```
Methods: Helps run methods in separate threads
Tests: Help to run all methods belonging to the same tag in the same thread
Classes: Helps to run all methods belonging to a class in a single thread
Instances: Helps run all methods in the same instance in the same thread
```
Along with the parallel attribute, the thread-count attribute helps in defining the number of threads one wishes to create while running the tests in parallel. For example, in case one has three methods, with thread count as two, then during execution, two threads shall start in parallel with the corresponding methods. As the first method execution is completed, and the thread gets free, it takes up the next method in the queue.
