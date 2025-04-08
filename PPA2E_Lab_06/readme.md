# Assignment 6
# Motivation
It is well known, that one of the main causes of procrastination (persistent nothingdoing) is the inability to start a task that seems too large (the so-called decision paralysis). An efficient technique against this effect is a systematic subdivision of the task into smaller parts, which no longer cause the mental block. An appropriate data structure is therefore a Stack, that allows tracking tasks and subdivising them. At the beginning, the user inserts the main task, such as "write a thesis". Next, tasks are presented to her and she is given the following options:

* report finishing the task (it is simple enough to be started and finished)
* subdivide the top task into smaller parts, which are then added to the stack instead of the original top task (for example "decide the chapter names", "Write chpater 1", "Write chapter 2" etc.)

# Task 1 (debugging) (up to 1 point)
Following code represents a stack of strings implemented using an array, together with a method for adding an element (add). Due to syntactic errors it is not possible to compile it. Fix the errors and test that the program works correctly if the initial capacity of 5 items is not exceeded.

```
/**
 * Stack for storing strings
 * @author Libor Vasa
 */
interface IStringStack {
  /**
   * Adds a string to the stack
   * @param s a string that is to be added to the stack
   */
  void add(String s);
  
  /**
   * Returns a string at the top of the stack
   * @return the string at the top of the stack
   */
  String get();
  
  /**
   * Removes the last (top) element of the stack
   */
  void removeLast();
}

/**
 * Procrastination reducing assistant
 * @author Libor Vasa
 */
class ProcrastinationAssistant {
  public static void main(String[] args) {
    IstringStack stack = new StackArray();
    stack.add("Learn to play ukulele");
    stack.add(randomString);
  }

  /**
   * Generates and returns a random string 
   * @return returns a random string of random characters (5 to 24 characters)
   */
  public static String randomString() {
    StringBuilder sb = new StringBuilder();
    Random r = new Random();
    for (int i = 0; i < (5 + r.nextInt(20); i++) {
      sb.append(char) (r.nextInt(24) + 65));
    }
    return(sb.toString());
  }

/**
 * Implementation of a stack using an array
 * @author Libor Vasa
 */
class StackArray implements IStringStack {
  /** Data in stack */
  private String[] data;
  /** Index where a new element can be put */
  private int freeIndex;

  /**
   * Creates an empty stack
   */
  public StackArray() {
    data = new int[5];
    freeIndex = 0;
  }
  
  public void add(String s) {
    data[freeIndex] = s;
    freeIndex++;
  }
  
  String get() {
  }

  void removeLast() {
  }
}
```

# Task 2 (implementing get, removeLast, testing)(up to 4 points)
Implement the method for getting the top element (String get()) and removing the top element (void removeLast()). Implement the method void expandArray() for doubling the stack capacity in the case that and element is being added that does not fit into the current stack, and add appropriate means of calling it into the add(...) method. (2 points)

Write method testStack(IStringStack stack) that tests an (empty) stack by inserting and removing 100 000 randomly generated elements. Put the random elements into an array first, then insert them one by one into the stack, and finally get/remove them from the stack again using the get and removeLast methods, while checking whether or not they correspond to the original elements in the original array. (1 point)

Measure the time needed for the test using the standard implementation and the time needed if the capacity is increased by 10 elements at the time. An appropriate approach is to use the IStringStack interface and using the existing mathod testStack. Use an appropriate concept of object oriented programming. (1 bod)
