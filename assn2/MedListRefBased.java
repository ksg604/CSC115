public class MedListRefBased implements List<Medication> {
	
	//instance variables
	private int count;
	private MedicationNode head;
	
	//constructor
	public MedListRefBased() {	
		
		head = null;
		count = 0;
		
	}
	
	//Adds a medication to the list.  
	public void add(Medication item, int index) {
		if (index < 0 || index > count) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		if (isEmpty()) {
			head = new MedicationNode(item,null,null);
		}
		MedicationNode curr = head;
		MedicationNode temp = head;
		int k = 1;
		while(k < index) {
			curr = curr.next;
			k++;
		}
		if(curr.next == null) {
			MedicationNode newNode = new MedicationNode(null, curr, null);
			curr.next = newNode;
		}else{
			MedicationNode newNode = new MedicationNode(item, curr, curr.next);
			curr.next.prev = newNode;
			curr.next = newNode;
		}
		count++;
		
	}
	
	//Removes a medication from the linked list as specified by its index position in the list
	public void remove(int index) {
		if (index < 0 || index > count-1) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		if (isEmpty()) {
			System.out.println("List is empty");
			return;
		}
		
		MedicationNode curr = head;
		int k = 0;
		while(k < index) {
			curr = curr.next;
			k++;
		}
		if (curr.prev == null) {
			curr.next.prev = null;
			head = curr.next;
		}else if(curr.next == null) {
			curr = curr.prev;
			curr.next = null;
		}else{
			curr.next.prev = curr.prev;
			curr.prev.next = curr.next;
		}			
		count--;
	}	

	//Gets and returns the index position of a specified medication in the linked list
	public Medication get(int index) {
		if (index < 0 || index > count-1) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		if (isEmpty()) {
			System.out.println("List is empty");
		}
		
		MedicationNode curr = head;
		int k = 0;
		while(k < index) {
			curr = curr.next;
			k++;
		}
		if(curr.prev == null) {
			return curr.item;
		}else if(curr.next == null) {
			return curr.item;
		}else{
			return curr.item;
		}
	}	
	
	//Checks to see if the list is empty, returns true if the list is empty and false otherwise
	public boolean isEmpty() {
		return(head == null);
	}
	
	//Returns the number of items in the list
	public int size() {
		return count;
	}
	
	//Finds and returns the index position of an item on the list
	public int find(Medication item) {
		MedicationNode curr = head;
		int k = 0;
		while(curr != null){
			if (curr.item != null && curr.item.equals(item)){ 
            return k;
        }
        curr = curr.next;
		k++;
    }
    return -1;
}	

	//Removes everything from the list and creates a fresh linked list
	public void removeAll() {
		head = null;
		count = 0;
	}
	
	//Removes a medication as specified. Every identical medication will be removed from the list.
	public void remove(Medication value) {
		if (isEmpty()) {
			System.out.println("List is empty");
			return;
		}
		MedicationNode curr = head;
		int decrementCount = 0;
		
		for (int k = 0; k < count; k++) {
			if(curr.item.equals(value)) {
				decrementCount++;
				if (curr.prev == null) {
					curr.next.prev = null;
					head = curr.next;
				}else if(curr.next == null) {
					curr = curr.prev;
					curr.next = null;
				}else{
					curr.next.prev = curr.prev;
					curr.prev.next = curr.next;
				}
			}
			curr = curr.next;
		}
		count = count - decrementCount;
	}	
	
	public String toString() {
		MedicationNode curr = head;
		if (count == 0) {
			return "{}";
		}
		StringBuilder s = new StringBuilder(count*10);
		s.append("List: {\n");
		for(int k = 0; k < count; k++) {
			s.append("\t"+curr.item+",\n");
			curr = curr.next;
		}
		if (count > 0) {
			s.append("\t"+curr.item+"\t");
		}
		s.append("}");
		return s.toString();
	}
	
	
	//prints out the contents of the list
	private void printList() {
		MedicationNode current = head;
		int k = 0;
		System.out.println("Actual List contents:");
		while(current != null) {
			System.out.println(k+" : "+current.item);
			current = current.next;
			k++;
		}
		System.out.println("Count = "+count);
	}
	
	//inserts an item in front of the list
	private void insertFirst(Medication item) {
		if (head == null) {
			head = new MedicationNode(item,null,null);
		}else{
			MedicationNode newNode = new MedicationNode(item,head,null);
			head.next = newNode;
			count++;
		}
	}
	
	
	//main method will test the instance methods
	public static void main(String[] args) {
		MedListRefBased list = new MedListRefBased();
		list.add(new Medication("meperidine",100),0);  //testing the add method
		list.add(new Medication("acetylsalicylic acid",325),1);
		list.add(new Medication("acetominophen",100),2);
		list.add(new Medication("cimetidine",150),3);
		list.add(new Medication("meperidine",100),4);
		System.out.println("The contents of the list should be {meperidine,acetylsalicylic acid,acetominophen,cimetidine,meperidine,null}");
		System.out.println(list); //using toString method to print list
		list.printList(); //printing list using private method
		System.out.println("We will now get and return the item in index 2: "+list.get(2));  //testing the get method
		System.out.println("Now we will find and return in index position of cimetidine: " + list.find(new Medication("cimetidine",150)));  //testing the find method
		System.out.println("After removing item in index 3...");
		list.remove(3); //testing the remove method
		list.printList();
		System.out.println("Checking to see if the item is still there using the find method...");
		System.out.println(list.find(new Medication("cimetidine",150))); //testing the find method
		System.out.println(list);
		list.printList();
		System.out.println("Now we will remove two identical medications");
		list.remove(new Medication("meperidine",100));  //testing the second remove method
		System.out.println(list);
		list.printList();
		System.out.println("Now we will remove everything");
		list.removeAll();  //testing the removeAll method
		System.out.println("Will be true if the list is empty: "+list.isEmpty()); //testing the isEmpty method
		System.out.println("Checking to see if count is 0...");
		System.out.println("Count = "+list.size()); //testing the size method		
	}		
}