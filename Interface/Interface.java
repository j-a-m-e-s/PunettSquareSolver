import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Interface extends JFrame {
	   private static final int FRAME_WIDTH = 450;
	   private static final int FRAME_HEIGHT = 450;
	   
	   private JButton b;
	   private JLabel l1, l2, l3 , l4, l5, l6, l7;
	   private JTextField rec1,rec2,rec3,rec4,rec5,rec6;
	   private JTextField pro1,pro2,pro3,pro4,pro5,pro6;
	   private JButton button;
	   private JLabel resultLabel;
	   GridBagConstraints gbc = new GridBagConstraints();
	   	
	   
	   public Interface()
	   {  
		   setLayout(new GridBagLayout());
		   
		   gbc.insets = new Insets(5, 5, 5, 5);
		   createText(rec1,2,1);
		   createText(rec2,2,2);
		 //  createText(rec3,1,3);
		 //  createText(rec4,2,1);
		//   createText(rec5,2,2);
		  // createText(rec6,2,3);
		   LABEL(l1, "Arithmatic Equation In", 1, 1);
		   LABEL(l2, "Output", 1, 2);
		   createButton(b, 3, 3);
		   

	      

	      setSize(FRAME_WIDTH, FRAME_HEIGHT);
	   }
	    
	   private void createText(JTextField text,int x,int y)
	   {
	      final int FIELD_WIDTH = 10;
	     text = new JTextField(FIELD_WIDTH);
	     gbc.gridx = x;
	     gbc.gridy = y;
	     add(text,gbc);
	   }
	   private void LABEL(JLabel label, String name, int x, int y){
		   label = new JLabel(name);
		   gbc.gridx = x;
		   gbc.gridy = y;
		   add(label,gbc);
	   }
	   private void createButton(JButton button , int x, int y)
	   {
		  gbc.gridx = x;
		  gbc.gridy = y;
	      button = new JButton("Calculate");
	    
	   }

}

