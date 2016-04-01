import java.util.ArrayList;

/* 
 * Parent class holds the gene string
 */
public class Parent {
	String myGene;
	
	/*
	 * default constructor method
	 */
	public Parent (){
		myGene = "";
	}
	
	/*
	 * construct a Parent object with gene string.
	 * @param	gene	gene string
	 */
	public Parent (String gene){
		myGene = gene;
	}
	
	/*
	 * get the genetic code at specific location.
	 * @param	i,	j	location of the genetic code
	 * 					i indicates which pair
	 * 					j indicates which of the pair
	 * @return 	genetic code at specified location
	 */
	public char getGene (int i, int j){
		return myGene.charAt(i * 2 + j);
	}
	
	/*
	 * set the genetic code at specific location.
	 * @param	i,	j	location of the genetic code
	 * @param	c	 	genetic code to be set
	 */
	public void setGene (int i, int j, char c){
		int index = i * 2 + j;
		if (myGene == null) {
			throw new NullPointerException();
		} else if (index < 0 || index >= myGene.length()) {
			throw new RuntimeException();
		} else {
		    char[] chars = myGene.toCharArray();
		    chars[index] = c;
		    myGene = String.valueOf(chars);       
		}
	}
	
	/*
	 * set the whole genetic string
	 * @param	gene	genetic string
	 */
	public void setGeneStr (String gene) {
		myGene = gene;
	}
	
	/*
	 * return the entire genetic string
	 * @return 	the genetic string of parent
	 */
	public String getGeneStr (){
		return myGene;
	}
	/*
	 * Method
	 * Creates array of combinations for genetic string
	 */
	public ArrayList<String> getGeneArray(){
		return getGeneArray (myGene);
	}
	/*
	 * Method
	 * Actual method to create the genetic string using the current parents.
	 * Takes off the first two characters and calls itself.
	 * If there is only two characters left then return each character in a separate string.
	 * Takes the returned array and appends each character to the front of each element of the array.
	 * 
	 * @param	geneStr  genetic string
	 * @returns          array created with combinations of the genetic string. 
	 * 
	 */
	public ArrayList<String> getGeneArray(String geneStr){
		ArrayList<String> GeneArray = new ArrayList<String>();
		char c0 = geneStr.charAt(0);
		char c1 = geneStr.charAt(1);
		if (geneStr.length() > 2){
			ArrayList<String> subGeneArray = getGeneArray (geneStr.substring(2));
			for (String subGene : subGeneArray)
				GeneArray.add(c0 + subGene);
			for (String subGene : subGeneArray)
				GeneArray.add(c1 + subGene);
		} else {
			GeneArray.add(String.valueOf(c0));
			GeneArray.add(String.valueOf(c1));
		}
		return GeneArray;
	}
}
