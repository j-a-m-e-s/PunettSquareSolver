
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

public class GUI extends JFrame implements ActionListener, ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6884841635816085888L;
	private static final int MaxParent = 2;
	private static final int MaxGene = 3;
	private static final int MaxPunnet = 8;
	private JPanel contentPane; 
	private JLabel[] plabel = new JLabel[MaxParent];
	private JButton[][][] PGButton = new JButton[MaxGene][MaxParent][2];
	private JSpinner[] gSpinner = new JSpinner[MaxGene];
	private char[] gCharUsed = new char[MaxGene];
	private String[] gSequence = new String[MaxGene];
	private JSlider slider;
	private JLabel[][] punnetSqr = new JLabel[MaxPunnet][MaxPunnet];
	private JLabel[][] punnetSqrLbl = new JLabel[MaxPunnet][MaxParent];
	
	private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

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

  		setBounds(100, 100, 900, 300);
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
		Title.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Title, c);
		
		JLabel glabel = new JLabel ("Gene");
		c.gridy ++;
		c.gridx = 0;
		c.gridwidth = 2;
		contentPane.add(glabel, c);
		c.gridx +=c.gridwidth;
		for (int i = 0; i < MaxParent; i++) {
			plabel[i] = new JLabel("Parent " + Integer.toString(i + 1));
			contentPane.add(plabel[i], c);
			c.gridx +=c.gridwidth;
		};
		for (int j = 0; j < MaxGene; j++){
			c.gridy ++;
			c.gridx = 2;
			c.gridwidth = 1;
			for (int i = 0; i < MaxParent; i++) {
				for (int k = 0; k < 2; k++ ){
					PGButton[j][i][k] = new JButton(alphabet[j]);
					contentPane.add(PGButton[j][i][k], c);
					c.gridx += c.gridwidth;
			        PGButton[j][i][k].addActionListener(
			                new ActionListener(){
			                    public void actionPerformed(ActionEvent e) {
			                    	JButton source = (JButton) e.getSource();
			                    	String str = source.getText();
			                    	if (Character.isUpperCase(str.charAt(0)))
			                    		source.setText(str.toLowerCase());
			                    	else
			                    		source.setText(str.toUpperCase());
			                    }
			                });
				};
			};
			c.gridwidth = 2;
			c.gridx = 0;
			CyclingSpinnerListModel gSpinnerModel = new CyclingSpinnerListModel(alphabet);
			gSpinnerModel.setLinkedButtons(PGButton[j]);
			gSpinnerModel.setCharUsedList (gCharUsed);
			gSpinnerModel.setCurrentSpinnerList(j);
			gSpinner[j] = new JSpinner((SpinnerListModel) gSpinnerModel);
			gSpinner[j].setValue(alphabet[j]);
			gCharUsed[j] = alphabet[j].charAt(0);
			setSpinnerWidth(gSpinner[j], 3);
			contentPane.add(gSpinner[j], c);
			c.gridx += c.gridwidth;
		};
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		slider = new JSlider(JSlider.HORIZONTAL, 1, MaxGene, MaxGene);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setFont(new Font("Serif", Font.ITALIC, 15));
		slider.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	SliderStateChanged ((JSlider) e.getSource());
		    }
		});
		contentPane.add(slider, c);
		c.gridx += c.gridwidth;

		JButton Calculate = new JButton("Calculate");
		c.gridwidth = 2;
		contentPane.add(Calculate, c);
		Calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grab();
			}
		});
		
		JPanel punnetPane = new JPanel(new GridBagLayout());

		c.ipadx = 10;
		c.ipady = 10;
		c.gridwidth = 2;
		for (int i = 0; i < MaxPunnet; i++) {
			c.gridx = (1 + i) * 2;
			c.gridy = 0;
			punnetSqrLbl[i][0] = new JLabel (Integer.toString(i) + ", 0");
			punnetPane.add(punnetSqrLbl[i][0], c);
			c.gridx = 0;
			c.gridy = 1 + i;
			punnetSqrLbl[i][1] = new JLabel (Integer.toString(i) + ", 1");
			punnetPane.add(punnetSqrLbl[i][1], c);
			for (int j = 0; j < MaxPunnet; j++){
				c.gridx = (i + 1) * 2;
				c.gridy = j + 1;
				punnetSqr[i][j] = new JLabel (Integer.toString(i) + ", " + Integer.toString(j));
				punnetPane.add(punnetSqr[i][j], c);
			};
		};
		c.gridwidth = 15;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridx = 7;
		c.gridy = 0;
		contentPane.add(punnetPane, c);
	}
/*
 * set Spinner text field to width.
 * @param spinner	a JSpinner object specifying which spinner to change
 * @param width		width of text field in characters.
 */
    private  void setSpinnerWidth (JSpinner spinner, int width) {
    	JFormattedTextField gTextField;
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            gTextField = ((JSpinner.DefaultEditor)editor).getTextField();
	        if (gTextField != null ) {
	            gTextField.setColumns(width); 
	            gTextField.setHorizontalAlignment(JTextField.CENTER);
	        }
        } else {
            System.err.println("Unexpected editor type: "
                               + spinner.getEditor().getClass()
                               + " isn't a descendant of DefaultEditor");
        }
    }
    
 /*
  * Method called when Slider changed state.
  * It makes buttons visible and invisible according to
  * the number of genes specified in the slider.
  * 
  * @param	source	source of change.  In this case the 
  * 		slider
  */
    
    private void SliderStateChanged (JSlider source) {		        
		if (!source.getValueIsAdjusting()) {
			int numberOfGenes = (int)  source.getValue();
			for (int j = 0; j < MaxGene; j++) {
				gSpinner[j].setVisible(j < numberOfGenes);
				for (int i = 0; i < MaxParent; i++) 
					for (int k = 0; k < 2; k++)
						PGButton[j][i][k].setVisible(j < numberOfGenes);				
			}
			int punLimit = (int) Math.pow (2, numberOfGenes);
			for (int j = 0; j < MaxPunnet; j++) {
				for (int i = 0; i < MaxPunnet; i++)
					punnetSqr[i][j].setVisible((i < punLimit)&& (j < punLimit));
				for (int i = 0; i < MaxParent; i++) {
					punnetSqrLbl[j][0].setVisible(j < punLimit);
					punnetSqrLbl[j][1].setVisible(j < punLimit);
				}
			}
		}
	}
/*
 * Method called when the calculate button is clicked.
 * It generate the gene sequence from the buttons.
 */
    public void grab() {
    	String[][] punnetSqrLblStr = new String[MaxPunnet][MaxParent];
		for (int i = 0; i < MaxParent; i++) {
			gSequence[i] = new String("");
			for (int j = 0; j < MaxPunnet; j++) {
				punnetSqrLblStr [j][i] = new String("");
			}
			for (int j = 0; j < MaxGene; j++)
				for (int k = 0; k < 2; k++)
					gSequence[i] += PGButton[j][i][k].getText();
		}
		for (int i = 0; i < MaxParent; i++) 		
			System.out.println(gSequence[i]);
	}

	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}