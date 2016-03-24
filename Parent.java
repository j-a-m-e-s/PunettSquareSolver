/* 
 * Parent class holds the gene string
 */
public class Parent {
	String myGene;
	
	/*
	 * default constructor method
	 */
	public Parent (){
	}
	
	/*
	 * construct a Parent object with gene string.
	 * @param	gene	gene string
	 */
	public Parent (String gene){
		
	}
	
	/*
	 * get the genetic code at specific location.
	 * @param	i,	j	location of the genetic code
	 * @return 	genetic code at specified location
	 */
	public char getGene (int i, int j){
		char c = 'a';	
		return c;
	}
	
	/*
	 * set the genetic code at specific location.
	 * @param	i,	j	location of the genetic code
	 * @param	c	 	genetic code to be set
	 */
	public void setGene (int i, int j, char c){
		
	}
	
	/*
	 * set the whole genetic string
	 * @param	gene	genetic string
	 */
	public void setGeneStr (String gene) {
		
	}
	
	/*
	 * return the entire genetic string
	 * @return 	the genetic string of parent
	 */
	public String getGeneStr (){
		return "";
	}
	
}
