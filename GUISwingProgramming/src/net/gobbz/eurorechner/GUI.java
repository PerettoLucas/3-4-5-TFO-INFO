package net.gobbz.eurorechner;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.WriteAbortedException;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	private JPanel contentPane;
	
	
	private JLabel[] jLabels = new JLabel[13];
	private JTextField[] jTextFields = new JTextField[13];
	
	private EuroUmrechner eu = new EuroUmrechner();
	private MeinTastaturAbhoerer meinTastaturAbhoerer= null;
	

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
		setTitle("Euroumrechner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		
		int offset = 30;
		
		  for (int i =  0; i < 13; i++) 
		  {
			  //Dynamicly add JLabels and JTextfields and assigning it each a WAEHRUNG + Listener
			  jLabels[i] = new JLabel(EuroUmrechner.WAEHRUNGEN[i] +":");
			  jTextFields[i] = new JTextField();
			  jTextFields[i].setName(EuroUmrechner.WAEHRUNGEN[i]+":");
			  meinTastaturAbhoerer = new MeinTastaturAbhoerer(this.jTextFields,this.eu);
		       
			  jTextFields[i].addKeyListener(meinTastaturAbhoerer);
		       
			  jTextFields[i].setBounds(250,offset,200,35); 
			  jLabels[i].setBounds(30,offset,200,50);
			  getContentPane().add(jTextFields[i]);
			  getContentPane().add(jLabels[i]);
		       
			  jLabels[i].setName(EuroUmrechner.WAEHRUNGEN[i]);
		       
			  offset += 35;
		  }
	}
}
