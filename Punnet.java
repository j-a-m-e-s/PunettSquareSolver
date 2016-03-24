/*
 * 
 */
public class Punnet {
	private Parent[] myParents = new Parent[2];
	private OffSpring myOffSpring[][];
/*
 * Default class constructor.	
 */
	public Punnet (){
		
	}
/*
 * Class constructor by taking in both parents.
 * 
 * @param parent1	info about parent1.
 * @param parent2	info about parent2.	
 */
	public Punnet (Parent parent1, Parent parent2){
		
	}

/*
 * returns either parent1 or parent2;
 * @param	i	specific which parent (0 or 1);
 * @return		return parent specified, null otherwise.
 */
	public Parent getParent (int i){
		return myParents[i];
	}
	
/*
 * returns the OffSpring information at Punnet Square i, j
 * @param	i	specify punnet Square row i;
 * @param	j	specify Punnet Square column j;
 * @return	OffSpring information	
 */
	public OffSpring getOffSpring (int i, int j) {
		return myOffSpring[i][j];
	}

/*
 * Take the parent informations and construct the Punnet Square.
 */
	public void calcPunnet () {
		
	}

}
