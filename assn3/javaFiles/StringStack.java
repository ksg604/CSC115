 /** 
  *  Name       : Kevin San Gabriel
  *  Student ID : V00853258
  */

public class StringStack {

	private Node head;
	
	/**
	 * Checks to see if the stack is empty
	 */
	public boolean isEmpty() {
	return(head == null);
	}

	/**
	 * Pops the item at the top of the stack and returns it
	 */
	public String pop() {
	if (isEmpty()) {
		throw new StackEmptyException("The stack is empty. ");
	}else{
		Node temp = head;
		head = head.next;
		return temp.item;
		}	
	}

	/**
	 * Looks at the item at the top of the stack without discarding it
	 */
	public String peek() {
	if (isEmpty()) {
		throw new StackEmptyException("The stack is empty. ");
	}else{
		return head.item;
		}	
	}
	
	/**
	 * Pushes an item into the stack
	 * @param item The String to push into the stack
	 */
	public void push(String item) {	
	Node newHead = new Node(item,head);
	head = newHead;
	}
	
	/**
	 * Removes everything from the stack
	 */
	public void popAll() {
	head = null;
	}
	
	/**
	 * Private method that prints every item in the stack
	 */
	private void printList() {
		if (isEmpty()) {
			throw new StackEmptyException("The stack is empty. ");
		}
		
		Node curr = head;
		int k = 0;
		System.out.println("Stack contents:");
		while(curr != null) {
			System.out.println(k+" : "+curr.item);
			curr = curr.next;
			k++;
		}
	}
	
	//the main method will test the instance methods 
	public static void main(String[] args) {
		StringStack stack = new StringStack();
		stack.push("First item"); //tests the push method
		stack.printList(); //tests the printList private method
		stack.push("Second item");
		System.out.println(stack.peek());	//tests the peek method
		stack.printList();
		System.out.println("This should return the string of the head of the list: "+stack.peek());
		System.out.println("Now we will pop the item from the top of the stack");
		stack.pop();	//tests the pop method 
		System.out.println("The list should now only have one item");
		stack.printList();
		stack.push("Second item");
		stack.push("Third item");
		stack.printList();
		System.out.println("Will now pop everything off the list");
		stack.popAll();	//tests the popAll method
		stack.printList(); //should 
		
	}
}
