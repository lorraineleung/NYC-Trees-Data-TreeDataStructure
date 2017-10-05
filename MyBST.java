package edu.nyu.cs.ll3094;

/**
 * The class represents a BST (binary search tree)   
 * @author Lorraine Leung
 * @version April 23, 2017
 */

import java.util.NoSuchElementException;

public class MyBST <E extends Comparable<E>> {
	
	protected int size;
	protected BSTNode<E> root;
	
	/**
	 * Default constructor which sets the root to null
	 */
	public MyBST(){
		this.root = null;
	}
	
	/**
	 * Adds a leaf to BST   
	 * @param e, data to be added with BSTNode
	 * @return boolean, true if data has been added, else false
	 * @throws ClassCastException, NullPointerException
	 */
	public boolean add(E e) throws ClassCastException{
		if (e == null){
			throw new NullPointerException("Null cannot be added");
		}
		else if(e!=null){
			root = addHelper(root, e);
			size++;
			return true;
		}
		return false;
	}
	
	/**
	 * Helper method for adding a leaf to BST 
	 * @param newNode, to be added  
	 * @param current, root/currentNode to compare to newNode 
	 * @return BSTNode<E>
	 */
	private BSTNode<E> addHelper(BSTNode<E> current, E e){
		if (current == null){
			BSTNode<E> newNode = new BSTNode<E>(e);
			return newNode;
		}
		if((e.compareTo(current.getData()))<0){
			BSTNode<E> currentLeft = current.getLeft();
			currentLeft = addHelper(currentLeft, e);
		}
		else{
			BSTNode<E> currentRight = current.getRight();
			currentRight = addHelper(currentRight, e);
		}
		return current;
	}
	
	/**
	 * Remove a node with specified object in the node
	 * @param o, object in node to be removed
	 * @return boolean, true if node was removed, else false
	 * @throws ClassCastException, NullPointerException
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(Object o) throws ClassCastException{
		if(o==null){
			throw new NullPointerException("Object to be removed cannot be null");
		}
		else if(o!=null){
			this.root = removeHelper(root, (E)o);
			return true;
		}
		return false;
	}
	
	/**
	 * Return BSTNode<E> with the given data to be searched
	 * @param current node 
	 * @param e, data 
	 * @return BSTNode<E>
	 */
	private BSTNode<E> removeHelper(BSTNode<E> current, E e){
		if (current == null){
			return current;
		}
		else if(e.compareTo(current.getData())<0){
			removeHelper(current.getLeft(), e);
		}
		else if (e.compareTo(current.getData())>0){
			removeHelper(current.getRight(), e);
		}
		else{
			current = remove(current);
		}
		return current;
	}
	
	/**
	 * Remove the node and return new node 
	 * @param node
	 * @return BSTNode<E>
	 */
	private BSTNode<E> remove(BSTNode<E> node){
		if(node.getLeft()==null){
			return node.getRight();
		}
		else if(node.getRight()==null){
			return node.getLeft();
		}
		E data = getPredecessor(node);
		node.setData(data);
		BSTNode<E> nodeLeft = node.getLeft();
		nodeLeft = removeHelper(node.getLeft(), data);
		return node;
	}
	
	/**
	 * Get predecessor of BSTNode<E> if node to be removed has 2 children
	 * @param n, node to be removed
	 * @return E, data 
	 */
	private E getPredecessor(BSTNode<E> n){
		BSTNode<E> current = new BSTNode<E>();
		if(n.getLeft()!=null){
			System.err.println("Error: leftNode is null"); 
		}
		else{
			current = n.getLeft();
			while(current.getRight()!=null){
				current=current.getRight();
			}
		}
		return current.getData();
	}

	/**
	 * Check if BST contains the specified element 
	 * @param Object o to be searched for in BST 
	 * @return true if BST contains Object o, else false
	 * @throws ClassCastException
	 * @throws NullPointerException
	 */
	public boolean contains(Object o) throws ClassCastException{
		if(o==null){
			throw new NullPointerException("Object cannot be null");
		}
		else{
			return containsHelper(o, root);
		}
	}
	
	/**
	 * Helper method for contains method 
	 * @param newNode, with data to be searched for
	 * @param currentNode, for comparison 
	 * @return boolean, true if BST contains data, else false
	 */
	private boolean containsHelper(Object o, BSTNode<E> currentNode){
		@SuppressWarnings("unchecked")
		BSTNode<E> newNode = new BSTNode<E>((E)o);
		if (currentNode==null){
			return false;
		}
		else if (newNode.compareTo(currentNode)<0){
			return containsHelper(o, currentNode.getLeft());
		}
		else if (newNode.compareTo(currentNode)>0){
			return containsHelper(o, currentNode.getRight());
		}
		else{
			return true;
		}
	}
	
	/**
	 * Returns first (lowest) element in BST 
	 * @return type E element, the first (lowest) element currently in this set 
	 * @throws NoSuchElementException, if set is empty
	 */
	public E first(){
		if (this.size==0){
			throw new NoSuchElementException("This set is empty");
		}
		else{
			return root.getData();
		}
	}
	
	/**
	 * Returns last (highest) element in BST
	 * @return type E element, the last (highest) element currently in this set 
	 * @throws NoSuchElementException, if set is empty
	 */
	public E last(){
		if (this.size==0){
			throw new NoSuchElementException("This set is empty");
		}
		else{
			BSTNode<E> current = root;
			while(root.getRight()!=null){
				current = root.getRight();
			}
			return current.getData();
		}
	}
	
	/**
	 * Returns a string representation of this set (BST)  
	 * @return string representation of this set (BST) 
	 */
	public String toString(){
		String treeString = "This tree has "+ size+ " objects";
		return treeString;
	}


}
