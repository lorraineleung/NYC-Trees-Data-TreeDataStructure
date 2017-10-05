package edu.nyu.cs.ll3094;

/**
 * The class is the main program and calculates tree search results   
 * @author Lorraine Leung
 * @version April 23, 2017
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NYCStreetTrees{

	public static void main(String[] args) throws FileNotFoundException {
		
		String fileName = null;
		
		//Ask user for file name 
		if (args.length==0){
			System.err.println("No input given");
			System.exit(0);
		}
		else{
			fileName = args[0];
		}
		
		//String to store all the data read 
		ArrayList<ArrayList<String>> dataTable = new ArrayList<ArrayList<String>>();
		
		//Use try catch block to open and make sure file exists 
		try{
     
			Scanner fileScn = new Scanner(new File(fileName));
			
			while (fileScn.hasNextLine()){
				ArrayList<String> dataLine = splitCSVLine(fileScn.nextLine());
				dataTable.add(dataLine);
			}
			fileScn.close();
			
		}
		catch (FileNotFoundException e){
			System.err.println("Error: The file " + fileName + " canot be opened.");
			System.exit(0);
		}
		
		
		//remove first line of 2D ArrayList because they are headers for the columns
		dataTable.remove(0);
		
		//create TreeCollection with each tree as an object stored inside 
		TreeCollection treeCollection = new TreeCollection();
		
		for (int i=0; i<dataTable.size();i++){
			try{
				int id = Integer.parseInt(dataTable.get(i).get(0));
				int dbh = Integer.parseInt(dataTable.get(i).get(3));
				String status = dataTable.get(i).get(6);
				String health = dataTable.get(i).get(7);
				String species = dataTable.get(i).get(9);
				int zip = Integer.parseInt(dataTable.get(i).get(25));
				String boro = dataTable.get(i).get(29);
				double x = Double.parseDouble(dataTable.get(i).get(39));
				double y = Double.parseDouble(dataTable.get(i).get(40));
				Tree toAddTree = new Tree(id, dbh, status, health, species, zip, boro, x, y);
				treeCollection.add(toAddTree);
				} 
			catch (Exception e){
				continue;
			}
		}
		
		//Scanner for user input 
		Scanner input = new Scanner(System.in);
		
		while (true){
			
			//ask user for tree species 
			System.out.println("Enter the tree species to learn more about it (\"quit\" to stop): ");
			String userSpecies = input.nextLine().toLowerCase();
			
			//break out of loop if user inputs quit 
			if (userSpecies.equals("quit")){
				break;
			}
			
			//print out all matching species
			System.out.println("All matching species: \n");
			ArrayList<String> matchList = (ArrayList<String>) treeCollection.getMatchingSpecies(userSpecies);
			for(int i = 0; i<matchList.size(); i++){
				System.out.println(matchList.get(i));
			}
			
			//print out species popularity in the city 
			//nyc tree counts 
			int nycTotalSpecies = treeCollection.getCountByTreeSpecies(userSpecies);
			int nycTotalTrees = treeCollection.getTotalNumberOfTrees();
			double nycPercent;
			if(nycTotalTrees==0){
				nycPercent = 0;
			}
			else{
				nycPercent = ((double)nycTotalSpecies/(double)nycTotalTrees)*100;
			}
			
			//Manhattan tree counts 
			int manTotalSpecies = treeCollection.getCountByTreeSpeciesBorough(userSpecies, "manhattan");
			int manTotalTrees = treeCollection.getCountByBorough("Manhattan");
			double manPercent;
			if(manTotalTrees==0){
				manPercent = 0;
			}
			else{
				manPercent = ((double)manTotalSpecies/(double)manTotalTrees)*100;
			}
			
			
			//Bronx tree counts
			int bronxTotalSpecies = treeCollection.getCountByTreeSpeciesBorough(userSpecies, "bronx");
			int bronxTotalTrees = treeCollection.getCountByBorough("bronx");
			double bronxPercent;
			if (bronxTotalTrees==0){
				bronxPercent = 0;
			}
			else{
				bronxPercent = ((double)bronxTotalSpecies/(double)bronxTotalTrees)*100;
			}
			
			//Brooklyn tree counts
			int brookTotalSpecies = treeCollection.getCountByTreeSpeciesBorough(userSpecies, "Brooklyn");
			int brookTotalTrees = treeCollection.getCountByBorough("Brooklyn");
			double brookPercent;
			if (brookTotalTrees==0){
				brookPercent=0;
			}
			else{
				brookPercent = ((double)brookTotalSpecies/(double)brookTotalTrees)*100;
			}
			
			//Queens tree counts
			int queensTotalSpecies = treeCollection.getCountByTreeSpeciesBorough(userSpecies, "Queens");
			int queensTotalTrees = treeCollection.getCountByBorough("Queens");
			double queensPercent;
			if (queensTotalTrees==0){
				queensPercent=0;
			}
			else{
				queensPercent = ((double)queensTotalSpecies/(double)queensTotalTrees)*100;
			}
			
			//Staten island tree counts 
			int statenTotalSpecies = treeCollection.getCountByTreeSpeciesBorough(userSpecies, "Staten Island");
			int statenTotalTrees = treeCollection.getCountByBorough("Staten Island");
			double statenPercent;
			if (statenTotalTrees==0){
				statenPercent=0;
			}
			else{
				statenPercent = ((double)statenTotalSpecies/(double)statenTotalTrees)*100;
			}
			
			
			//Print output to user 
			System.out.println("\nPopularity in the city: ");
			System.out.printf("%-15s %,-5d  %1s %,d %s %.2f %s", "NYC: ", nycTotalSpecies, "(", nycTotalTrees, ")", nycPercent, "%");
			System.out.println("");
			System.out.printf("%-15s %,-5d  %s %,d %s %.2f %s", "Manhattan: ", manTotalSpecies, "(", manTotalTrees, ")", manPercent, "%");
			System.out.println("");
			System.out.printf("%-15s %,-5d  %s %,d %s %.2f %s", "Bronx: ", bronxTotalSpecies, "(", bronxTotalTrees, ")", bronxPercent, "%");
			System.out.println("");
			System.out.printf("%-15s %,-5d  %s %,d %s %.2f %s", "Brooklyn: ", brookTotalSpecies, "(", brookTotalTrees, ")", brookPercent, "%");
			System.out.println("");
			System.out.printf("%-15s %,-5d  %s %,d %s %.2f %s", "Queens: ", queensTotalSpecies, "(", queensTotalTrees, ")", queensPercent, "%");
			System.out.println("");
			System.out.printf("%-15s %,-5d  %s %,d %s %.2f %s", "Staten Island: ", statenTotalSpecies, "(", statenTotalTrees, ")", statenPercent, "%");
			System.out.println("\n");
		}
		
		
		//close all scanner objects
		input.close();
	}
	
	
	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries that may contain commas). 
	 * 
	 * @param textLine  line of text to be parsed
	 * @return an ArrayList object containing all individual entries/tokens
	 *         found on the line.
	 */
	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry= false;
		
		//iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			//handle smart quotes as well as regular quotes 
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') { 
				//change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false; 
				}
				else {
					insideQuotes = true; 
					insideEntry = true; 
				}
			}
			else if (Character.isWhitespace(nextChar)) {
				if  ( insideQuotes || insideEntry ) {
					// add it to the current entry
					nextWord.append( nextChar );
				}
				else  { // skip all spaces between entries 
					continue;
				}
			}
			else if ( nextChar == ',') {
				if (insideQuotes) //comma inside an entry 
					nextWord.append(nextChar);
				else { //end of entry found 
					insideEntry = false; 
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else {
				//add all other characters to the nextWord 
				nextWord.append(nextChar);
				insideEntry = true; 
			}

		}
		// add the last word (assuming not empty)
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}

}

