import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSlider;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class GUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField P1Gene1In;
	private JTextField P1Gene2In;
	private JTextField P1Gene3In;
	private JTextField P2Gene1In;
	private JTextField P2Gene2In;
	private JTextField P2Gene3In;
	private JLabel outp1g1;
	private JLabel outp1g2;
	private JLabel outp1g3;
	private JLabel outp2g1;
	private JLabel outp2g2;
	private JLabel outp2g3;
	

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
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSlider slider = new JSlider();
		slider.setMinimum(1);
		slider.setBounds(10, 428, 170, 23);
		slider.setSnapToTicks(true);
		slider.setMaximum(3);
		slider.setValue(2);
		contentPane.add(slider);
		
		
		
		JLabel Title = new JLabel("Punnet Square Solver");
		Title.setBounds(195, 11, 111, 17);
		Title.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Title);
		
		
		JLabel P1 = new JLabel("Parent 1");
		P1.setBounds(10, 45, 86, 14);
		contentPane.add(P1);
		
		JLabel P1Gene1 = new JLabel("Gene 1");
		P1Gene1.setBounds(20, 68, 46, 14);
		contentPane.add(P1Gene1);
		
		JLabel P1Gene2 = new JLabel("Gene 2");
		P1Gene2.setBounds(20, 116, 46, 14);
		contentPane.add(P1Gene2);
		
		JLabel P1Gene3 = new JLabel("Gene 3");
		P1Gene3.setBounds(20, 162, 46, 14);
		contentPane.add(P1Gene3);
		
		P1Gene1In = new JTextField();
		P1Gene1In.setBounds(10, 82, 86, 20);
		contentPane.add(P1Gene1In);
		P1Gene1In.setColumns(10);
		P1Gene1In.addActionListener(this);
		
		P1Gene2In = new JTextField();
		P1Gene2In.setBounds(10, 131, 86, 20);
		contentPane.add(P1Gene2In);
		P1Gene2In.setColumns(10);
		P1Gene2In.addActionListener(this);
		
		P1Gene3In = new JTextField();
		P1Gene3In.setBounds(10, 177, 86, 20);
		contentPane.add(P1Gene3In);
		P1Gene3In.setColumns(10);
		P1Gene3In.addActionListener(this);
		
		JLabel P2 = new JLabel("Parent 2");
		P2.setBounds(10, 223, 86, 14);
		contentPane.add(P2);
		
		JLabel P2Gene1 = new JLabel("Gene 1");
		P2Gene1.setBounds(20, 248, 46, 14);
		contentPane.add(P2Gene1);
		
		JLabel P2Gene2 = new JLabel("Gene 2");
		P2Gene2.setBounds(20, 299, 46, 14);
		contentPane.add(P2Gene2);
		
		JLabel P2Gene3 = new JLabel("Gene 3");
		P2Gene3.setBounds(20, 350, 46, 14);
		contentPane.add(P2Gene3);
		
		JLabel Counter = new JLabel("  1                        2                       3");
		Counter.setBounds(10, 411, 200, 14);
		contentPane.add(Counter);
		
		P2Gene1In = new JTextField();
		P2Gene1In.setBounds(10, 262, 86, 20);
		contentPane.add(P2Gene1In);
		P2Gene1In.setColumns(10);
		P2Gene1In.addActionListener(this);
		
		P2Gene2In = new JTextField();
		P2Gene2In.setBounds(10, 313, 86, 20);
		contentPane.add(P2Gene2In);
		P2Gene2In.setColumns(10);
		P2Gene2In.addActionListener(this);
		
		P2Gene3In = new JTextField();
		P2Gene3In.setBounds(10, 365, 86, 20);
		contentPane.add(P2Gene3In);
		P2Gene3In.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("results in this box");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel.setBounds(313, 313, 161, 138);
		contentPane.add(lblNewLabel);
		
		
		JButton Calculate = new JButton("Calculate");
		Calculate.setBounds(195, 428, 91, 23);
		getContentPane().add(Calculate);
		contentPane.add(Calculate);
		
		 outp1g1 = new JLabel("outp1g1");
		outp1g1.setBounds(185, 68, 46, 14);
		contentPane.add(outp1g1);
		
		 outp1g2 = new JLabel("outp1g2");
		outp1g2.setBounds(185, 93, 46, 14);
		contentPane.add(outp1g2);
		
		 outp1g3 = new JLabel("outp1g3");
		outp1g3.setBounds(185, 116, 46, 14);
		contentPane.add(outp1g3);
		
		 outp2g1 = new JLabel("outp2g1");
		outp2g1.setBounds(185, 137, 46, 14);
		contentPane.add(outp2g1);
		
		 outp2g2 = new JLabel("outp2g2");
		outp2g2.setBounds(185, 162, 46, 14);
		contentPane.add(outp2g2);
		
		 outp2g3 = new JLabel("outp2g3");
		outp2g3.setBounds(185, 193, 46, 14);
		contentPane.add(outp2g3
			);
		
		Calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				grab();
				
			}
		});
	}
	
	public void grab(){
		outp1g1.setText(P1Gene1In.getText());
		outp1g2.setText(P1Gene2In.getText());
		outp1g3.setText(P1Gene3In.getText());
		outp2g1.setText(P2Gene1In.getText());
		outp2g2.setText(P2Gene2In.getText());
		outp2g3.setText(P2Gene3In.getText());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
