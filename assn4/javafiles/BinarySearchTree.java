 /*
  * Student Name: Kevin San Gabriel
  * Student ID  : V00853258
 */


import java.util.ArrayList;



public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	// the root is inherited from BinaryTree.

	/**
	 * Create an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		super();
	}

	
	/**
	 * Inserts a new item into the tree, maintaining its order. If the item already exists in the tree, nothing happens.
	 * @param newItem The newest item.
	 */
	public void insert(E newItem) {		
		root = insertItem(root, newItem);
	}
	
	/**
	 * Helper method for insert. Traverses through the tree and adds an item based on the imposed order in the tree.
	 * @param tNode The node from which to traverse from.
	 * @param newItem The new item in the new node.
	 * @return The parent node.
	 */
	protected TreeNode<E> insertItem(TreeNode<E> tNode, E newItem) {
		TreeNode<E> newSubtree;
		
		if (tNode == null) {
			tNode = new TreeNode<E>(newItem, null, null);
			return tNode;
		}
		
		E nodeItem = tNode.item;
	
		if (newItem.equals(nodeItem)) {
			return null;										
		}
		if (newItem.compareTo(nodeItem) < 0) {
			newSubtree = insertItem(tNode.left, newItem);
			tNode.left = newSubtree;
			return tNode;	
		}else {
			newSubtree = insertItem(tNode.right, newItem);
			tNode.right = newSubtree;
			return tNode;		
		}		
	}

	
	/**
	 * Looks for the item in the tree that is equivalent to the key.  
	 * @param key The item that is equivalent to the item we are looking for. Equality is determined by the equals method of the item.
	 * @return The item if it's in the tree, null if it is not.
	 */
	public E retrieve(E key) {		
		return retrieveItem(root, key);
	}

	
	/**
	 * Helper method for retrieve.  Traverses through the tree in order and retrieves and returns the item in the tree equivalent to the key.
	 * @param tNode
	 * @param key 
	 * @return The item if it's in the tree, null if it is not.
	 */

	protected E retrieveItem(TreeNode<E> tNode, E key) {
		
		E treeItem;
		
		if (tNode == null) {
			treeItem = null;
		}
		else {
			E nodeItem = tNode.item;
			if (key.equals(nodeItem)) {
				treeItem = tNode.item;
			}else if (key.compareTo(nodeItem) < 0) {
				treeItem = retrieveItem(tNode.left, key);
			}else {
				treeItem = retrieveItem(tNode.right,key);
				}				
		}
		return treeItem;	 
	}

	
	/**
	 * Finds the item that is equivalent to the key and removes it, if it's in the tree.
	 * @param key The item that is equivalent to the item we are looking for. Equality is determined by the equals method of the item.
	 */
	public void delete(E key) {
		root = deleteItem(root, key);
	}

	
	/**
	 * Helper method for delete.  Traverses through the tree in order and retrieves and removes the item in the tree equivalent to the key.
	 * @param tNode The node from which to traverse from.
	 * @param The item that is equivalent to the item we are looking for. Equality is determined by the equals method of the item.
	 * @return The node that will be deleted
	 */
	protected TreeNode<E> deleteItem(TreeNode<E> tNode, E key) {
		TreeNode<E> newSubtree;
		if (tNode == null) {
			System.out.println("Item not found.");
			return null;
		}else{
			E nodeItem = tNode.item;
			if (key.equals(nodeItem)) {
				tNode = deleteNode(tNode);
			}else if (key.compareTo(nodeItem) < 0) {
				newSubtree = deleteItem(tNode.left, key);
				tNode.left = newSubtree;
			}else {
				newSubtree = deleteItem(tNode.right, key);
				tNode.right = newSubtree;
			}
		}
		return tNode;		
	}

	
	/**
	 * Helper method for deleteItem.  Considers four cases:
	 * 1. tNode is a leaf
	 * 2. tNode has no left child
	 * 3. tNode has no right child
	 * 4. tNode has two children
	 * Calls: findLeftmost and deleteLeftmost
	 * @param tNode The node from which to traverse from.
	 * @return Retrieves and deletes the in order successor.  
	 */
	protected TreeNode<E> deleteNode (TreeNode<E> tNode) {
		E replacementItem;
		
		if (tNode.left == null && tNode.right == null) {
			return null;
		}else if (tNode.left == null) {
			return tNode.right;
		}else if (tNode.right == null) {
			return tNode.left;
		}else {
			replacementItem = findLeftmost(tNode.right);
			tNode.item = replacementItem;
			tNode.right = deleteLeftmost(tNode.right);
			return tNode;
		}
		
	}

	
	/**
	 * Locates the leftmost node.
	 * @param tNode The node from which to traverse from.
	 * @return The leftmost node.
	 */
	protected E findLeftmost (TreeNode<E> tNode) {
		if (tNode.left == null) {
			return tNode.item;
		}else {
			return findLeftmost(tNode.left);
		}
	}

	
	/**
	 * Deletes the leftmost node of a parent.
	 * @param tNode The node from which to traverse from.
	 * @return The parent node.
	 */
	protected TreeNode<E> deleteLeftmost(TreeNode<E> tNode) {
		if (tNode.left == null) {
			return tNode.right;
		}else {
			tNode.right = deleteLeftmost(tNode.left);
			return tNode;
		}				
	}

	
	/**
	 * Places all the items in the tree into a sorted list.
	 * @return the sorted list.
	 */
	public ArrayList<E> inOrder() {
		ArrayList<E> list = new ArrayList<E>();
		collectInOrder(list,root);
		return list;
	}

/*
 * NOTE TO STUDENT.
 * This recursive method is the one that does the work
 * of traversing the tree in order and placing items
 * into the list.
 */
	
	/**
	 * Traverses through the tree in order and places items into a sorted list.
	 * @param list The list to place items into.
	 * @param node The node from which to start traversing from.
	 */
	private void collectInOrder(ArrayList<E> list, TreeNode<E> node) {
 		
		list.add(node.item);
		if (node != null) {
			if (node.left != null) {
				collectInOrder(list,node.left);
			}
			if (node.right != null) {
				collectInOrder(list,node.right);
			}
		}	
	}
	

	/**
	 * Internal test harness.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
/*
 * NOTE TO STUDENT:
 * Something to get you started...
 * Uncomment as you go, then continue testing.
 */

	
		BinarySearchTree<PatientLocation> tree = new BinarySearchTree<PatientLocation>();
		PatientLocation p1 = new PatientLocation("Duck", "Donald", 338);
		PatientLocation p2 = new PatientLocation("Mouse", "Minnie",116);
		PatientLocation p3 = new PatientLocation("Dog", "Goofie",422);
		PatientLocation p4 = new PatientLocation("Newman","Alfred",607);
		PatientLocation p5 = new PatientLocation("Blue","Bob",608);
		PatientLocation p6 = new PatientLocation("Aname", "Adam", 101);
		PatientLocation p7 = new PatientLocation("Christy", "Cool", 777);
		PatientLocation p8 = new PatientLocation("Zname", "Zaname", 593);
		PatientLocation p9 = new PatientLocation("Mouse", "Micky", 1001);
		PatientLocation p10 = new PatientLocation("Aname", "Aswell", 1);
		PatientLocation p11 = new PatientLocation("Duck", "Donald", 338);
		//testing the insert method
		tree.insert(p1);
		tree.insert(p4);
		tree.insert(p3);
		tree.insert(p2);
		tree.insert(p5);
		tree.insert(p6);
		tree.insert(p7);
		tree.insert(p8);
		tree.insert(p9);
		tree.insert(p10);
		tree.insert(p11);
		//testing the retrieve method
		System.out.println(tree.retrieve(p6));
		//testing the inOrder and collectInOrder methods
		ArrayList<PatientLocation> list  = tree.inOrder();	
		System.out.println(list);
		//testing the delete method
		tree.delete(p4);
		list = tree.inOrder();
		System.out.println(list);
		// draw the tree in its current state:
		DrawableBTree<PatientLocation> dbt = new DrawableBTree<PatientLocation>(tree);
		dbt.showFrame();
		
	
	}

}
