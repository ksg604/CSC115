/** 
 *  Name       : Kevin San Gabriel
 *  Student ID : V00853258
 */

public class Node {
	
	protected String item;
	protected Node next;
	
	
	/**creates a Node that contains a String object.
	@param item The String contained in the node.
	@param next The node that comes after this one in the list.
	*/
	protected Node(String item, Node next) {
		this.item = item;
		this.next = next;
	}
	
	/**creates a Node that contains an String that is not attached to the list.
	@param item The String that is contained within the Node.
	*/
	protected Node(String item) {
		this(item,null);
	}
	
	


}