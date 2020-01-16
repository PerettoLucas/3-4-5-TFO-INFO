package net.tfobz.testprog;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class testProg extends JFrame
{

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					testProg frame=new testProg();
					frame.setVisible(true);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testProg()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,331,259);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTextPane textPane = new JTextPane();
		contentPane.add(textPane);
		
		JTextPane txtpnTestprogramm = new JTextPane();
		txtpnTestprogramm.setText("TestProgramm");
		contentPane.add(txtpnTestprogramm);
		
		table = new JTable();
		contentPane.add(table, BorderLayout.WEST);
		
		JButton btnNewButton = new JButton("hoi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		contentPane.add(btnNewButton, BorderLayout.EAST);
		
		
		
		
		
		
		setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
	}

}
