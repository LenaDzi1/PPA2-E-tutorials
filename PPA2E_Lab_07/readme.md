# Assignment 7
# Motivation
ADT Queue is usually employed when requests are treated in the order in which they are created. An example may be connecting calls on a hotline to operators, from the point of view of the callers, but from the point of view of operators as well. If there are multiple operators available, a call will be connected to the operator that has been "free" for the longest time.  In the assignment, you will implement a simple automatic system that connects calls to operators using two queues, implemented using linked structures.

# Task 1 (debugging) (up to 1 point)
Following code introduces a queue of callers, however, it is not semantically correct. Fix it.

```
class IncomingCall {
	int callingNumber;
	int time; // time from the start of the shift, when the call came
}

class Link {
	IncomingCall data;
	Link next;
}

class CallerQueue {
	private Link first, last;
	
	public void add(IncomingCall call) {
		Link nl = new Link();
		nl.data = call;
		if (first == null) {
			first = nl;
			last = nl;
		}
		else
			last = nl;
	}
	
	public IncomingCall get() {
		if (first != null)
			return null;
		else
			return first.data;
	}
	
	public void removeFirst() {
		if (first!=null)
			first = first.next;
		else System.out.println("Remove call on empty queue. Probably error, continuing...");
	}	
}
```

# Task 2 Dispatcher (up to 4 points)
Add the following class Dispatcher to the project:
```
class Dispatcher {
	private CallerQueue callerQueue;
	private OperatorQueue operatorQueue;
	
	public Dispatcher() {
		this.callerQueue = new CallerQueue();
		this.operatorQueue = new OperatorQueue();
	}
	
	public void call(int number, int time) {
		IncomingCall call = new IncomingCall();
		call.callingNumber = number;
		call.time = time;
		callerQueue.add(call);
	}
	
	public void freeOperator(String name, int time) {
		operatorQueue.add(new FreeOperator(name, time)); // operator name se time sekund od zacatku smeny prihlasil jako dostupny
	}
	
	public void dispatchCall() {
	}
	
	private void assignCall(IncomingCall call, FreeOperator operator) {
		System.out.println(operator.name + " is answering call from +420 "+call.callingNumber);
		System.out.println("The caller has waited for " + Math.max(0, operator.time-call.time) + " seconds.");
	}
}
```
* Add classes needed for creating a queue of free operators in order to make the code work. (2 points)
* Add the body of the dispatchCall() method, which checks, if either of the queues is empty. In case neither is empty, the method should assign the longest waiting call to the longest waiting operator by removing them from their respective queues and calling the method assignCall. (2 points)
* Check the correctness of the implementation. Find out on your own, what should the following program output, and check whether or not you obtain the expected result from your implementation.

```
public class CallDispatching {
	public static void main(String[] args) {
		Dispatcher d = new Dispatcher();
		d.freeOperator("Tonda", 0);
		d.dispatchCall();
		d.freeOperator("Jarmila", 10);
		d.dispatchCall();
		d.call(608123456, 15);
		d.dispatchCall();
		d.call(723987654, 35);
		d.dispatchCall();
		d.call(602112233, 45);
		d.dispatchCall();
		d.freeOperator("Pepa", 62);
		d.dispatchCall();
		d.call(608987654, 124);
		d.dispatchCall();
		d.freeOperator("Tonda", 240);
		d.dispatchCall();
	}
}
```
