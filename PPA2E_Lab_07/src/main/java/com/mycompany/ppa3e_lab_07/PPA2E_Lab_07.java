/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ppa3e_lab_07;

public class PPA2E_Lab_07 {

    public static void main(String[] args) {
        CallerQueue queue = new CallerQueue();
        IncomingCall data = new IncomingCall(12345678, 10);
        queue.add(data);
        data = new IncomingCall(8888888, 15);
        queue.add(data);
        System.out.println("Number: " + queue.get().callingNumber + " time: " + queue.get().time);
        queue.removeFirst();
        System.out.println("Number: " + queue.get().callingNumber + " time: " + queue.get().time);
        queue.removeFirst();
        queue.removeFirst();
        
        
        
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

class IncomingCall {
	int callingNumber;
	int time; // time from the start of the shift, when the call came
        
        public IncomingCall(){}
        
        public IncomingCall (int number, int time){
            this.callingNumber = number;
            this.time = time;
        }
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
                nl.next = null; ///// this 
		if (first == null) {
			first = nl;
			last = nl;
		}
                else{
                    last.next = nl;    /// previous next is new one
			last = nl;     
                }
	}
	
	public IncomingCall get() {
		if (first != null)
			return first.data; //switch it
		else
			return null; //it was incorrect
	}
	
	public void removeFirst() {
            if (first!=null) {
		first = first.next;
                 if (first == null) {                        
                      last = null;   /////
                  }                                                    //// those
            } else  
                     System.out.println("Remove call on empty queue. Probably error, continuing...");
        
        }
       	
}


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
               
                IncomingCall call = callerQueue.get();
                FreeOperator operator = operatorQueue.get();
                
                if(operator != null && call != null){
                callerQueue.removeFirst();
                operatorQueue.removeFirst();
                assignCall(call, operator);
            }
              
	}
	
	private void assignCall(IncomingCall call, FreeOperator operator) {
		System.out.println(operator.name + " is answering call from +420 "+call.callingNumber);
		System.out.println("The caller has waited for " + Math.max(0, operator.time-call.time) + " seconds.");
	}
}

class FreeOperator {
    String name;
    int time;

    public FreeOperator(String name, int time) {
        this.name = name;
        this.time = time;
    }
}

class OperatorLink {
	FreeOperator data;
	OperatorLink next;
}

class OperatorQueue {
	private OperatorLink first, last;
	
	public void add(FreeOperator operator) {
		OperatorLink nl = new OperatorLink();
		nl.data = operator;
                nl.next = null; ///// this 
		if (first == null) {
			first = nl;
			last = nl;
		}
                else{
                    last.next = nl;    /// previous next is new one
			last = nl;     
                }
	}
	
	public FreeOperator get() {
		if (first != null)
			return first.data; //switch it
		else
			return null; //it was incorrect
	}
	
	public void removeFirst() {
            if (first!=null) {
		first = first.next;
                 if (first == null) {                        
                      last = null;   /////
                  }                                                    //// those
            } else  
                     System.out.println("Remove call on empty queue. Probably error, continuing...");
        
        }
       	
}