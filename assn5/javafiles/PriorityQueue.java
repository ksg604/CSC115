/*
 * Student Name: Kevin San Gabriel
 * Student ID  : V00853258
*/

import java.util.NoSuchElementException;


public class PriorityQueue {
	
	private Heap heap;

	/**
	 * Create an empty priority queue.
	 */
	public PriorityQueue() {
		heap = new Heap();
	}
	
	/**
	 * Inserts an item into the queue.
	 * @param item The item to insert.
	 */
 	public void enqueue(Comparable item) {
		heap.insert(item);
	}
	
 	/**
 	 * Removes the highest priority item from the queue.
 	 * @return The item.
 	 * @throws NoSuchElementException if the queue is empty.
 	 */
	public Comparable dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException("No element to dequeue, the PriorityQueue is empty.");
		}	
		
		return heap.removeRootItem();
	}
	
	/**
	 * Retrieves, but does not remove the next item out of the queue.
	 * @return The item with the highest priority in the queue.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	public Comparable peek() {
		if(isEmpty()) {
			throw new NoSuchElementException("No element to peek at, the PriorityQueue is empty.");
		}
		return heap.getRootItem();
	}
	
	/**
	 * @return True if the queue is empty, false if it is not.
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	/**
	 * Private method to print out the items in the queue.
	 */
	private void printQueue() {
		heap.printHeapArray();
	}
	
	public static void main(String[] args) {
		PriorityQueue pq = new PriorityQueue();
		String[] complaints = {"Walk-in", "Life-threatening","Chronic","Major fracture", "Chronic"};
		System.out.println();
		
		//here we test the enqueue method
		for (int i = 0; i < complaints.length; i++) {
			pq.enqueue(new ER_Patient(complaints[i]));
		}
		System.out.println("We have "+pq.heap.size()+" ER patients in need of immediate care.");
		
		System.out.println("Here is a list of the patients for the ER Physician: ");
		System.out.println();
		//here we test the private printQueue method
		pq.printQueue();
		System.out.println();
		
		System.out.println("We must give the highest priority to this patient: ");
		System.out.println();
		//here we test the peek method
		System.out.println(pq.peek());
		System.out.println();
		
		System.out.println("We are now tending to the patient...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted");
			return;
		}
		System.out.println("The highest priority patient has been treated and will live.  He has left the ER room.");
		System.out.println("Here is a list of the remaining patients we must tend to: ");
		System.out.println();
		//here we test the dequeue method
		pq.dequeue();
		pq.printQueue();
		
		System.out.println("This is the next patient we must give priority to: ");
		System.out.println();
		System.out.println(pq.peek());
		System.out.println();
		System.out.println("We are now tending to the patient...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted");
			return;
		}
		
		pq.dequeue();
		System.out.println("Operation success.  The patient is now well and has left the ER room.");
		System.out.println();
		System.out.println("Remaining ER patients: ");
		pq.printQueue();
		System.out.println();
		System.out.println("We are now working on the next patient: "+pq.peek());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted");
			return;
		}
		
		pq.dequeue();
		System.out.println("Operation success.");
		System.out.println();
		System.out.println("Remaining ER patients: ");
		pq.printQueue();
		System.out.println();
		System.out.println("Now the next patient: "+pq.peek());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted");
			return;
		}
		
		pq.dequeue();
		System.out.println("Operation success.");
		System.out.println();
		System.out.println("Remaining ER patients: ");
		pq.printQueue();
		System.out.println();
		System.out.println("Now the final and least prioritized patient: "+pq.peek());
		System.out.println();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted");
			return;
		}
		
		pq.dequeue();
		//here we test the isEmpty method
		if(pq.isEmpty()) {
			System.out.println("We have successfully treated all of our patients.  Our ER rooms are now empty:");
		}else{
			System.out.println("Error.  The system has malfunctioned.");
		}
		System.out.println();
		pq.printQueue();
				
	}
			
}
	
