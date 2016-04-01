
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.BevelBorder;

/*
 * Main program.
 * Builds the graphic user interface and is the main program
 */
public class GUI extends JFrame implements ActionListener, ChangeListener {

	private static final int MaxParent = 2;
	private static final int MaxGene = 3;
	private static final int MaxPunnet = 8;
	private static int numberOfGenes = MaxGene;
	private JPanel contentPane;
	private JLabel[] plabel = new JLabel[MaxParent];
	private JButton[][][] PGButton = new JButton[MaxGene][MaxParent][2];
	private JSpinner[] gSpinner = new JSpinner[MaxGene];
	private char[] gCharUsed = new char[MaxGene];
	private JSlider slider;
	private PunnetPane punnetPane;
	/*
	 * List of characters used by the spinner
	 */
	private final String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel Title = new JLabel("Punnet Square Solver");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 6;
		c.weightx = 0.0;
		Title.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Title, c);

		/*
		 * Creates label for gene input column
		 */
		JLabel glabel = new JLabel("Gene");
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		contentPane.add(glabel, c);
		c.gridx += c.gridwidth;
		/*
		 * Label for parents 1 and 2
		 */
		for (int i = 0; i < MaxParent; i++) {
			plabel[i] = new JLabel("Parent " + Integer.toString(i + 1));
			contentPane.add(plabel[i], c);
			c.gridx += c.gridwidth;
		}
		;
		
		for (int j = 0; j < MaxGene; j++) {
			c.gridy++;
			c.gridx = 2;
			c.gridwidth = 1;
			for (int i = 0; i < MaxParent; i++) {
				/*
				 * For each parent and each gene there are a pair of buttons.
				 * Keeps button's previous state when switching letters.
				 */
				for (int k = 0; k < 2; k++) {
					PGButton[j][i][k] = new JButton(alphabet[j]);
					contentPane.add(PGButton[j][i][k], c);
					c.gridx += c.gridwidth;
					PGButton[j][i][k].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JButton source = (JButton) e.getSource();
							String str = source.getText();
							if (Character.isUpperCase(str.charAt(0)))
								source.setText(str.toLowerCase());
							else
								source.setText(str.toUpperCase());
						}
					});
				}
				;
			}
			;
			c.gridwidth = 2;
			c.gridx = 0;
			/*
			 * Creates spinner for choosing gene character
			 */
			CyclingSpinnerListModel gSpinnerModel = new CyclingSpinnerListModel(alphabet);
			gSpinnerModel.setCharUsedList(gCharUsed);
			gSpinnerModel.setLinkedButtons(PGButton[j]);
			gSpinnerModel.setCurrentSpinnerList(j);
			gSpinner[j] = new JSpinner((SpinnerListModel) gSpinnerModel);
			gSpinner[j].setValue(alphabet[j]);
			gCharUsed[j] = alphabet[j].charAt(0);
			setSpinnerWidth(gSpinner[j], 3);
			contentPane.add(gSpinner[j], c);
			c.gridx += c.gridwidth;
		}
		;
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		/*
		 * Creates slider bar for setting the number of genes
		 */
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		slider = new JSlider(JSlider.HORIZONTAL, 1, MaxGene, MaxGene);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setFont(new Font("Serif", Font.ITALIC, 15));
		/*
		 * Event listener for slider state changes
		 * Calls SliderStateChanged
		 */
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				SliderStateChanged((JSlider) e.getSource());
			}
		});
		contentPane.add(slider, c);
		c.gridx += c.gridwidth;

		/*
		 * Create button for calculation
		 */
		JButton Calculate = new JButton("Calculate");
		c.gridwidth = 4;
		contentPane.add(Calculate, c);
		/*
		 * Action listener for calculation button.
		 * Calls grab
		 */
		Calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grab();
			}
		});

		c.gridwidth = 15;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridx = 7;
		c.gridy = 0;
		/*
		 * Creates
		 */
		punnetPane = new PunnetPane(MaxGene);
		grab();
		contentPane.add(punnetPane, c);
		c.gridwidth = 1;
		c.gridheight = 0;

		pack();
	}

	/*
	 * set Spinner text field to width.
	 * 
	 * @param spinner a JSpinner object specifying which spinner to change
	 * 
	 * @param width width of text field in characters.
	 */
	private void setSpinnerWidth(JSpinner spinner, int width) {
		JFormattedTextField gTextField;
		JComponent editor = spinner.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			gTextField = ((JSpinner.DefaultEditor) editor).getTextField();
			if (gTextField != null) {
				gTextField.setColumns(width);
				gTextField.setHorizontalAlignment(JTextField.CENTER);
			}
		} else {
			System.err.println("Unexpected editor type: " + spinner.getEditor().getClass()
					+ " isn't a descendant of DefaultEditor");
		}
	}

	/*
	 * Method called when Slider changed state. It makes buttons visible and
	 * invisible according to the number of genes specified in the slider.
	 * 
	 * @param source source of change. In this case the slider
	 */

	private void SliderStateChanged(JSlider source) {
		if (!source.getValueIsAdjusting()) {
			numberOfGenes = (int) source.getValue();
			for (int j = 0; j < MaxGene; j++) {
				gSpinner[j].setVisible(j < numberOfGenes);
				for (int i = 0; i < MaxParent; i++)
					for (int k = 0; k < 2; k++)
						PGButton[j][i][k].setVisible(j < numberOfGenes);
			}
			punnetPane.setSize(numberOfGenes);
			grab();
			pack();
		}
	}

	/*
	 * Method called when the calculate button is clicked. It generate the gene
	 * sequence from the buttons.
	 */
	public void grab() {
		Parent[] parents = new Parent[MaxParent];
		String[][] punnetSqrLblStr = new String[MaxPunnet][MaxParent];
		for (int i = 0; i < MaxParent; i++) {
			String gSequence = new String("");
			for (int j = 0; j < MaxPunnet; j++) {
				punnetSqrLblStr[j][i] = new String("");
			}
			for (int j = 0; j < numberOfGenes; j++)
				for (int k = 0; k < 2; k++)
					gSequence += PGButton[j][i][k].getText();
			parents[i] = new Parent(gSequence);
			System.out.println(parents[i].getGeneStr());
		}
		punnetPane.setParents(parents);
		punnetPane.setOffsprings(new OffSpring(parents, numberOfGenes));
	}

	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}