/*
 * Student Name: Kevin San Gabriel
 * Student ID  : V00853258
*/

import java.util.NoSuchElementException;



@SuppressWarnings({"unchecked"})


public class Heap {

	private Comparable[] heapArray;
	private int count;
	private int INITIAL_SIZE = 3;
	
	/**
	 * Create an empty heap
	 */
	public Heap() {
		heapArray = new Comparable[INITIAL_SIZE];
		count = 0;
	}
	
	/**
	 * True if the heap is empty, false if it is not.
	 * @return
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * @return The number of items in the heap.
	 */
	public int size() {
		return count;
	}
	
	/**
	 * Inserts an item into the heap.
	 * @param item - The newly added item
	 */
	public void insert(Comparable item) {	
		if(heapArray.length == count) {
			doubleArray();
		}
		heapArray[this.size()] = item;
		count++;
		
		int jplace = this.size()-1;
		int parent = (place - 1) / 2;
		
		for (int i = 0; i < heapArray.length; i++){
			while ((parent >= 0) && compareItems(heapArray[place], heapArray[parent]) < 0) {
				Comparable temp = heapArray[parent];
				heapArray[parent] = heapArray[place];
				heapArray[place] = temp;
			}
			
			place = parent;
			parent = (place - 1) / 2;
		}	
	}
	
	/**
	 * Removes the item at the root node of the heap.
	 * @return The item at the root of the heap.
	 * @throws NoSuchElementException if the heap is empty.
	 */
	public Comparable removeRootItem() {
		
		if(isEmpty()) {
			throw new NoSuchElementException("The array is empty, there is no element to remove.");
		}
		
		Comparable rootItem = null;
		Comparable[] temp = heapArray;
		int loc;
		if(!isEmpty()) {
		rootItem = heapArray[0];
		loc = this.size()-1;
		heapArray[0] = heapArray[loc];
			
		heapArray[loc] = null;
		for(int k = loc; k < heapArray.length-1; k++) {
			temp[k] = heapArray[k+1];
		}
		heapArray = temp;
		count--;
		
		heapRebuild(0);
		}
		return rootItem;
	}

	/**
	 * Retrieves, without removing the item in the root.
	 * @return The top item in the tree.
	 * @throws NoSuchElementException if the heap is empty.
	 */
	public Comparable getRootItem() {
		if(isEmpty()) {
			throw new NoSuchElementException("The array is empty, there is no element to be found.");
		}
		
		return heapArray[0];
	}
	
	/**
	 * Private method that helps in converting the array back into a heap after an item is removed.
	 * @param root the root 
	 */
	private void heapRebuild(int root) {
		int child = 2 * root + 1;
		
		for (int i = 0; i < heapArray.length; i++) {
			if(child < this.size()){
				int rightChild = child + 1;
				if ((rightChild < this.size()) && (compareItems(heapArray[rightChild], heapArray[child]) < 0)) {
					child = rightChild;
				}
				if (compareItems(heapArray[root], heapArray[child] ) > 0) {
					Comparable temp = heapArray[root];
					heapArray[root] = heapArray[child];
					heapArray[child] = temp;
					heapRebuild(child);
				}
			}
		}
	}
	
	/**
	 * Protected method that helps in printing out the items in the heapArray.
	 */
	protected void printHeapArray() {
		for (int i = 0; i < heapArray.length; i++) {
			if(heapArray[i] == null){
				System.out.println("-");
			}else{
				System.out.println(heapArray[i]);
			}
		}
	}
	
	/**
	 * Private method that helps to compare two items.
	 * @param item1
	 * @param item2
	 * @return  <ul>
	 *	<li>a number less than 0 if this patient has a higher priority,</li>
	 * 	<li>0 if the priorities are equal (which should only happen if it is the same patient), or</li>
	 * 	<li>a number greater than 0 if the other patient has a higher priority.</li>
	 * </ul>
	 */
	private int compareItems(Comparable item1, Comparable item2) {
		return (item1.compareTo(item2));
	}
	
	/**
	 * Private method to double the array if it is full.
	 */
	private void doubleArray() {
		Comparable[] newArray = new Comparable[heapArray.length * 2];
		for(int i = 0; i < heapArray.length; i++) {
			newArray[i] = heapArray[i];
		}
		heapArray = newArray;
	}
	
	/**
	 * Used for internal testing purposes.
	 * @param args
	 */
	public static void main(String[] args) {
		Heap heap = new Heap();
		
		ER_Patient patient = new ER_Patient("Walk-in");
		ER_Patient patient2 = new ER_Patient("Chronic");
		ER_Patient patient3 = new ER_Patient("Life-threatening");
		ER_Patient patient4 = new ER_Patient("Major fracture");
		System.out.println();
		
		//here we test the insert method
		heap.insert(patient);
		heap.insert(patient2);
		heap.insert(patient3);
		heap.insert(patient4);
		
		//here we test the size method
		System.out.println("We have "+heap.size()+" ER patients in need of immediate care.");
		
		System.out.println("Here is a list of the patients for the ER Physician: ");
		System.out.println();
		//here we test the private print method
		heap.printHeapArray();
		System.out.println("");
		
		System.out.println("We must give the highest priority to this patient: ");
		System.out.println();
		//here we test the getRootItem method
		System.out.println(heap.getRootItem());
		System.out.println("");
		

		System.out.println("We are now working tending to the patient...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted");
			return;
		}
		System.out.println("The highest priority patient has been treated and will live.  He has left the ER room.");
		System.out.println("Here is a list of the remaining patients we must tend to: ");
		System.out.println();
		//here we test the removeRootItem method
		heap.removeRootItem();
		heap.printHeapArray();
		

		System.out.println("This is the next patient we must give priority to: ");
		System.out.println();
		System.out.println(heap.getRootItem());
		System.out.println();
		System.out.println("We are now tending to the patient...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted");
			return;
		}
		
		heap.removeRootItem();
		System.out.println("Operation success.  The patient is now well and has left the ER room.");
		System.out.println();
		System.out.println("We are now working on the remaining two patients...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted");
			return;
		}

			
		heap.removeRootItem();
		heap.removeRootItem();
		
		//tests the isEmpty method
		if(heap.isEmpty()) {
			System.out.println("We have successfully treated all of our patients.  Our ER rooms are now empty:");
		}else{
			System.out.println("Error.  The system has malfunctioned.");
		}
		System.out.println();
		heap.printHeapArray();

		
		
	}


}
