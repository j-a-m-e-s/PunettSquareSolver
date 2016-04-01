import java.util.ArrayList;

/*
 * OffSpring class mirrored Parent.  Space holder for
 * additional methods.
 */
public class OffSpring {
	/*
	 * Contains square array of strings for all possible offsprings
	 */
	String[][] myGene;

	/*
	 * Constructor Method
	 * Generates the offspring table from parent genes
	 * 
	 * @param parents 		Array of both parents
	 * @param numberOfGenes	Number of genes for each parent
	 */
	public OffSpring(Parent[] parents, int numberOfGenes) {
		ArrayList<String> geneArray0 = parents[0].getGeneArray();
		ArrayList<String> geneArray1 = parents[1].getGeneArray();
		int MaxEntry = (int) Math.pow(2, numberOfGenes);
		myGene = new String[MaxEntry][MaxEntry];

		for (int i = 0; i < MaxEntry; i++)
			for (int j = 0; j < MaxEntry; j++)
				myGene[i][j] = combine(geneArray0.get(i), geneArray1.get(j));
	}

	/*
	 * Takes characters alternately from each string, and combines them into a
	 * single string.
	 */
	private String combine(String str1, String str2) {
		String str = new String();
		char[] charList1 = str1.toCharArray();
		char[] charList2 = str2.toCharArray();
		for (int i = 0; i < str1.length(); i++)
			str += (String.valueOf(charList1[i]) + String.valueOf(charList2[i]));
		return str;
	}

	/*
	 * Gets genetic string from offspring table
	 * 
	 * @param i index into offspring table for columns
	 * @param j index into offspring table for rows
	 */
	public String getGeneStr(int i, int j) {
		return myGene[i][j];
	}
}
