package edu.nyu.cs.ll3094;

/**
 * The class represents a BSTNode (Binary Search Tree Node)  
 * @author Lorraine Leung
 * @version April 23, 2017
 */

public class BSTNode<E extends Comparable<E>> implements Comparable<BSTNode<E>>{

	//private data fields
	private E data;
	private BSTNode <E> left;
	private BSTNode <E> right;
	
	/**
	 * Default constructor
	 */
	public BSTNode(){
		this.data = null;
	}
	
	/**
	 * One parameter constructor 
	 * @param data (E)
	 */
	public BSTNode(E data){
		this.data=data;
	}
	
	/**
	 * Constructor that takes data, left and right node of new node 
	 * @param data (E)
	 * @param left, left node of this node
	 * @param right, right node of this node
	 */
	public BSTNode(E data, BSTNode<E> left, BSTNode<E> right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Sets data for this BSTNode
	 * @param data (E)
	 */
	public void setData(E data){
		this.data = data;
	}
	
	/**
	 * Returns this BSTNode's data
	 * @return data (E)
	 */
	public E getData(){
		return this.data;
	}
	
	/**
	 * Sets the left node of this BSTNode
	 * @param BSTNode<E> leftNode
	 */
	public void setLeft(BSTNode<E> left){
		this.left = left;
	}
	
	/**
	 * Returns the left node of this BSTNode 
	 * @return BSTNode<E> leftNode
	 */
	public BSTNode<E> getLeft(){
		return this.left;
	}
	
	/**
	 * Sets the right node of this BSTNode
	 * @param BSTNode<E> rightNode
	 */
	public void setRight(BSTNode<E> right){
		this.right = right;
	}
	
	/**
	 * Returns the right node of this BSTNode
	 * @return BSTNode<E> rightNode
	 */
	public BSTNode<E> getRight(){
		return this.right;
	}
	
	/**
	 * Compares this node to another node 
	 * @param BSTNode<E> other BSTNode to be compared
	 * @return negative, 0 or positive if this node is lesser, equal or greater than the other node
	 */
	@Override
	public int compareTo(BSTNode<E> other) {
		return this.getData().compareTo(other.getData());
	}

}

