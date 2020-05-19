/*
  * Student Name: Kevin San Gabriel
  * Student ID  : V00853258
 */



public class BinaryTree<E> {

	/* The root is inherited by any subclass
	 * so it must be protected instead of private.
	 */
	protected TreeNode<E> root;

	/**
	 * Create an empty BinaryTree.
	 */
	public BinaryTree() {
	}

	/**
	 * Create a BinaryTree with a single item.
	 * @param item The only item in the tree.
	 */
	public BinaryTree(E item) {
		root = new TreeNode<E>(item);
	}

	/**
	 * Used only by subclasses and classes in the same package (directory).
	 * @return The root node of the tree.
	 */
	protected TreeNode<E> getRoot() {
		return root;
	}
	
	/**
	 * Calculates the height of the tree.
	 * @param root
	 * @return The height of the tree. The textbook's definition of the height is the maximum number of nodes from the root to a leaf node. The height of an empty tree is therefore equal to 0.
	 */
	public int height() {
		return findHeight(root);
	}
	
	/**
	 * Helper method to traverse through left subtrees and right subtrees.
	 * @param tNode The node from which to traverse from.
	 * @return If the tree is empty, root is a leaf node so height is 0. Returns the height of the tree.
	 */
	protected int findHeight(TreeNode<E> tNode) {
		if (isEmpty()) {
			return 0;
		}
		return (1+Math.max(findHeight(tNode.left),findHeight(tNode.right)));	
	}
	
	/**
	 * Checks if the tree is empty.
	 * @return True if the tree is empty.
	 */
	public boolean isEmpty(){
		return (root == null);
	}
	
	/**
	 * Makes the tree empty.
	 */
	public void makeEmpty() {
		root = null;
	}

 	/******* COMPLETE *******/

}

	
