package edu.nyu.cs.ll3094;

/**
 * The class represents a tree collection that uses a binary search tree to store all tree objects   
 * @author Lorraine Leung
 * @version April 23, 2017
 */

import java.util.ArrayList;
import java.util.Collection;

public class TreeCollection extends MyBST<Tree>{
	
	//private data fields
	private ArrayList<String> borough;
	private ArrayList<Integer> boroughCount;
	private int size;
	
	/**
	 * TreeCollection default constructor
	 * Creates an empty collection
	 */
	public TreeCollection(){
	}
	
	/**
	 * Returns total number of trees 
	 * @return int, total number of trees
	 */
	public int getTotalNumberOfTrees(){
		int num = this.size;
		return num;
	}
	
	/**
	 * Return count of tree species (all matching species)
	 * @param speciesName 
	 * @return int, total number of trees with that species name 
	 */
	public int getCountByTreeSpecies(String speciesName){
		int count = 0;
		ArrayList<String> matchingSpecies = (ArrayList<String>) getMatchingSpecies(speciesName);
		for (int i =0; i<matchingSpecies.size(); i++){
			count += getCountByTreeSpeciesHelper(matchingSpecies.get(i), this.root);
		}
		return count;
	}
	
	/**
	 * Helper method for getCountByTreeSpecies
	 * @param speciesName to look for
	 * @param current, current BSTNode<Tree> with data
	 * @return int, total count for that species
	 */
	private int getCountByTreeSpeciesHelper(String speciesName, BSTNode<Tree> current){
		int speciesCount = 0;
		if (current == null){
			return speciesCount;
		}
		else if (current.getLeft()==null&&current.getRight()==null){
			return speciesCount;
		}
		else if (current.getLeft().getData().getSpecies().equalsIgnoreCase(speciesName)){
			speciesCount++;
			getCountByTreeSpeciesHelper(speciesName, current.getLeft());
		}
		else if (current.getRight().getData().getSpecies().equalsIgnoreCase(speciesName)){
			speciesCount++;
			getCountByTreeSpeciesHelper(speciesName, current.getRight());
		}
		return speciesCount;
	}

	/**
	 * Return total number of trees in a specific borough
	 * @param boroName
	 * @return number of trees in specified borough
	 */
	public int getCountByBorough(String boroName){
		if(borough.contains(boroName.toLowerCase())){
			int index = borough.indexOf(boroName.toLowerCase());
			return boroughCount.get(index);
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Return count of trees according to species name and borough
	 * @param String, speciesName 
	 * @param String, boroName
	 * @return total number of specified tree in specified borough 
	 */
	public int getCountByTreeSpeciesBorough(String speciesName, String boroName){
		int totalCount = 0;
		ArrayList<String> allMatchingSpecies = (ArrayList<String>) getMatchingSpecies(speciesName);
		for (int i=0; i< allMatchingSpecies.size(); i++){
			totalCount+=treeSpecisBoroughHelper(this.root, allMatchingSpecies.get(i), boroName);
		}
		return totalCount;
	}
	
	/**
	 * getCountByTreeSpeciesBorough helper method 
	 * @param current BSTNode<Tree> with data 
	 * @param speciesName to be compared to species name in current BSTNode<Tree>
	 * @param boroName to be compared to borough name in current BSTNode<Tree>
	 * @return int, total number of tree of that specified species 
	 */
	private int treeSpecisBoroughHelper(BSTNode<Tree> current, String speciesName, String boroName){
		int speciesCount = 0;
		if (current == null){
			return speciesCount;
		}
		else if (current.getLeft()==null&&current.getRight()==null){
			return speciesCount;
		}
		else if(current.getLeft().getData().getSpecies().equalsIgnoreCase(speciesName)&&current.getLeft().getData().getBoro().equalsIgnoreCase(boroName)){
			speciesCount++;
			treeSpecisBoroughHelper(current.getLeft(), speciesName, boroName);
		}
		else if(current.getRight().getData().getSpecies().equalsIgnoreCase(speciesName)&&current.getRight().getData().getBoro().equalsIgnoreCase(boroName)){
			speciesCount++;
			treeSpecisBoroughHelper(current.getRight(), speciesName, boroName);
		}
		return speciesCount;
		}
	
	/**
	 * Returns a collection of matching species name given a certain species name
	 * @param speciesName 
	 * @return Collection<String> of all matching speciesName
	 */
	public Collection<String> getMatchingSpecies(String speciesName){
		return getMatchingSpeciesHelper(speciesName, this.root);
	}
	
	/**
	 * getMatchingSpecies helper method 
	 * @param speciesName to be searched 
	 * @param currentNode, current BSTNode<Tree>
	 * @return ArrayList with all the matching species name
	 */
	private ArrayList<String> getMatchingSpeciesHelper(String speciesName, BSTNode<Tree> currentNode){
		ArrayList<String> matchList = new ArrayList<String>();
		if (currentNode == null){
			return matchList;
		}
		if (currentNode.getLeft()==null&&currentNode.getRight()==null){
			matchList.add(speciesName);
			return matchList;
		}
		else if(currentNode.getLeft()!=null){
			if(currentNode.getLeft().getData().getSpecies().toLowerCase().contains(speciesName.toLowerCase())
					&&(!matchList.contains(speciesName.toLowerCase()))){
				matchList.add(currentNode.getLeft().getData().getSpecies().toLowerCase());
			}
			getMatchingSpeciesHelper(speciesName, currentNode.getLeft());
		}
		else if(currentNode.getRight()!=null){
			if(currentNode.getRight().getData().getSpecies().toLowerCase().contains(speciesName.toLowerCase())
					&&(!matchList.contains(speciesName.toLowerCase()))){
				matchList.add(currentNode.getRight().getData().getSpecies().toLowerCase());
			}
			getMatchingSpeciesHelper(speciesName, currentNode.getRight());
		}
		return matchList;
	}
	
	/**
	 * Overriden add method from BST 
	 * @param t, Tree object
	 * @return boolean, true if tree was added, else false
	 * @exception ClassCastException, NullPointerException
	 */
	@Override
	public boolean add(Tree t) throws ClassCastException{
		if (t==null){
			throw new NullPointerException("Null cannot be added");
		}
		else{
			//call addHelper
			root = addHelper(root, t);
			
			//initialize the 2 arraylists
			borough = new ArrayList<String>();
			boroughCount = new ArrayList<Integer>();
			
			//add boroughname and count to arraylists
			if(borough.contains(t.getBoro().toLowerCase())){
				int index = borough.indexOf(t.getBoro().toLowerCase());
				int tempCount = boroughCount.get(index);
				int newCount = tempCount+1;
				boroughCount.add(index, newCount);
			}
			else if(!borough.contains(t.getBoro().toLowerCase())){
				borough.add(t.getBoro().toLowerCase());
				boroughCount.add(1);
			}
			this.size++;
			return true;
		}
	}
	
	/**
	 * Private add helper method 
	 * @param current, current BSTNode<Tree>
	 * @param t, tree object to be added
	 * @return BSTNode<Tree> to be added
	 */
	private BSTNode<Tree> addHelper(BSTNode<Tree> current, Tree t){
		if (current == null){
			BSTNode<Tree> newNode = new BSTNode<Tree>(t);
			return newNode;
		}
		if((t.compareTo(current.getData()))<0){
			BSTNode<Tree> currentLeft = current.getLeft();
			currentLeft = addHelper(currentLeft, t);
		}
		else{
			BSTNode<Tree> currentRight = current.getRight();
			currentRight = addHelper(currentRight, t);
		}
		return current;
	}
}
