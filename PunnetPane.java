import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

/*
 * Punnett Square
 */
public class PunnetPane extends JPanel{
	/**
	 * Offspring label
	 */
	private class PunnetSqr extends JLabel {
		/**
		 *  Create each element of the offsprings table
		 */
		public PunnetSqr() {
			super();
			setBorder(LineBorder.createGrayLineBorder());
		}
	}
	/*
	 * Parent label
	 */
	private class PunnetSqrLbl extends JLabel {
		/*
		 * Creates each element of the parent column or row 
		 */
		public PunnetSqrLbl() {
			super();
			setBorder(LineBorder.createBlackLineBorder());
		}
	}
	
	int MaxPunnet, punLimit;
	PunnetSqrLbl[][] punnetSqrLbl ;
	PunnetSqr[][] punnetSqr;
	
	/*
	 * Creates punnett square with matrix of size according to slider count
	 * 
	 * @param	size	Size of the punnett square
	 */
	public PunnetPane (int size){
		super(new GridBagLayout());
		punLimit = (int) Math.pow (2, size);
		MaxPunnet = punLimit;
		punnetSqrLbl = new PunnetSqrLbl[MaxPunnet][2];
		punnetSqr = new PunnetSqr[MaxPunnet][MaxPunnet];
		Font font = new Font ("Serif", Font.PLAIN, 12);
	
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 10;
		c.ipady = 10;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		for (int i = 0; i < MaxPunnet; i++) {
			c.gridx = (1 + i) * 2;
			c.gridy = 0;
			punnetSqrLbl[i][0] = new PunnetSqrLbl ();
			setPunnetSqrLbl("", i, 0);
			punnetSqrLbl[i][0].setFont(font);
			add(punnetSqrLbl[i][0], c);
			c.gridx = 0;
			c.gridy = 1 + i;
			punnetSqrLbl[i][1] = new PunnetSqrLbl();
			punnetSqrLbl[i][1].setFont(font);
			setPunnetSqrLbl("", i, 1);
			add(punnetSqrLbl[i][1], c);
			for (int j = 0; j < MaxPunnet; j++){
				c.gridx = (i + 1) * 2;
				c.gridy = j + 1;
				punnetSqr[i][j] = new PunnetSqr();
				punnetSqr[i][j].setFont(font);
				setPunnetSqr("", i, j);
				add(punnetSqr[i][j], c);
			};
		};
	}
	
	/*
	 * Gets each parent's genetic array and puts them in the row or column headings.
	 * 
	 * @param	parents		THe two parents
	 */
	public void setParents (Parent[] parents){
		ArrayList<String> geneArray0 = parents[0].getGeneArray();
		ArrayList<String> geneArray1 = parents[1].getGeneArray();
		for (int i = 0; i < punLimit; i++) {
			setPunnetSqrLbl(geneArray0.get(i), i, 0);
			setPunnetSqrLbl(geneArray1.get(i), i, 1);
		};
	}
	/*
	 * Sets each of element of the punnett square from the offspring string
	 * 
	 * @param offsprings	offspring table
	 */
	public void setOffsprings (OffSpring offsprings){
		for (int i = 0; i < punLimit; i++)
			for (int j=0; j < punLimit; j++) {
				setPunnetSqr(offsprings.getGeneStr(i, j), i, j);
			}
		
	}
	/*
	 * sets the string of punnetSqrLbl
	 * 
	 * @param	label			string to be set
	 * @param	index, parent	decides location to place the label   
	 */
	public void setPunnetSqrLbl (String label, int index, int parent){
		punnetSqrLbl[index][parent].setText(label);
	}
	
	/*
	 * gets string of the parent label
	 * 
	 * @param	index, parents	decide which label to get from
	 * @return					returns the string in the label
	 */
	public String getPunnetSqrLbl (int index, int parent){
		return punnetSqrLbl[index][parent].getText();
	}
	/*
	 * sets string of result offspring
	 * 
	 * @param 	label	string to set
	 * @param	row		location of label in rows
	 * @param	col		location of label in columns
	 */
	public void setPunnetSqr (String label, int row, int col){
		punnetSqr[row][col].setText(label);
	}
	
	/*
	 * gets string of result offspring
	 * 
	 * @param 	row		row location of label to get from
	 * @param 	col		column location of label to get from
	 * @return			returns the string
	 */
	public String getPunnetSqr (int row, int col){
		return punnetSqr[row][col].getText();
	}
	/*
	 * Changes the size of the punnett square according to slider count
	 * 
	 * @param	n	slider count determining the size
	 */
	public void setSize (int n){
		punLimit = (int) Math.pow (2, n);
		for (int j = 0; j < MaxPunnet; j++) {
			for (int i = 0; i < MaxPunnet; i++)
				punnetSqr[i][j].setVisible((i < punLimit)&& (j < punLimit));
			for (int i = 0; i < 2; i++) {
				punnetSqrLbl[j][0].setVisible(j < punLimit);
				punnetSqrLbl[j][1].setVisible(j < punLimit);
			}
		}

	}
}

