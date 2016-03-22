import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame implements ActionListener, ChangeListener {

	private final int MaxParent = 2;
	private final int MaxGene = 3;
	private JPanel contentPane;
	private JTextField[][] inPG = new JTextField[MaxParent][MaxGene];
	private JLabel[][] outPG = new JLabel[MaxParent][MaxGene];
	private JLabel[] plabel = new JLabel[MaxParent];
	private JLabel[][] PGLabel = new JLabel[MaxParent][MaxGene];

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
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Title = new JLabel("Punnet Square Solver");
		Title.setBounds(195, 11, 111, 17);
		Title.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Title);

		for (int i = 0; i < MaxParent; i++) {
			plabel[i] = new JLabel("Parent " + Integer.toString(i + 1));
			plabel[i].setBounds(10 + 150 * i, 45, 86, 14);
			contentPane.add(plabel[i]);
			for (int j = 0; j < MaxGene; j++) {
				PGLabel[i][j] = new JLabel("Gene " + Integer.toString(j + 1));
				PGLabel[i][j].setBounds(20 + 150 * i, 68 + 50 * j, 46, 14);
				contentPane.add(PGLabel[i][j]);

				inPG[i][j] = new JTextField();
				inPG[i][j].setBounds(10 + 150 * i, 82 + 50 * j, 86, 20);
				inPG[i][j].setColumns(10);
				inPG[i][j].addActionListener(this);
				contentPane.add(inPG[i][j]);

				outPG[i][j] = new JLabel();
				outPG[i][j].setBounds(100 + 150 * i, 68 + 50 * j, 46, 14);
				contentPane.add(outPG[i][j]);
			}
		}

		/*
		 * JLabel Counter = new JLabel(
		 * "  1                        2                       3");
		 * Counter.setBounds(10, 411, 200, 14); contentPane.add(Counter);
		 */
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, MaxGene, MaxGene);
		slider.setBounds(10, 400, 170, 50);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setFont(new Font("Serif", Font.ITALIC, 15));
		slider.addChangeListener(this);
		contentPane.add(slider);

		JLabel lblNewLabel = new JLabel("results in this box");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel.setBounds(313, 313, 161, 138);
		contentPane.add(lblNewLabel);

		JButton Calculate = new JButton("Calculate");
		Calculate.setBounds(195, 428, 91, 23);
		getContentPane().add(Calculate);
		contentPane.add(Calculate);

		Calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				grab();

			}
		});
	}

	public void grab() {
		for (int i = 0; i < MaxParent; i++)
			for (int j = 0; j < MaxGene; j++) {
				outPG[i][j].setText(inPG[i][j].getText());
			}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			int no_of_genes = (int) source.getValue();
			for (int i = 0; i < MaxParent; i++) {
				for (int j = 0; j < no_of_genes; j++) {
					PGLabel[i][j].setVisible(true);
					inPG[i][j].setVisible(true);
					outPG[i][j].setVisible(true);
				}
				for (int j = no_of_genes; j < MaxGene; j++) {
					PGLabel[i][j].setVisible(false);
					inPG[i][j].setVisible(false);
					outPG[i][j].setVisible(false);
				}
			}

			System.out.println(no_of_genes);
		}
	}
}
