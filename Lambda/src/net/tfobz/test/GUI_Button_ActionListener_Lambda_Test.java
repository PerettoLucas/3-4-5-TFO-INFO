package net.tfobz.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class GUI_Button_ActionListener_Lambda_Test extends JFrame
{

	private JPanel contentPane;

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
					GUI_Button_ActionListener_Lambda_Test frame=new GUI_Button_ActionListener_Lambda_Test();
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
	public GUI_Button_ActionListener_Lambda_Test()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,450,300);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxCheckbox = new JCheckBox("Checkbox ");
		chckbxCheckbox.setBounds(303, 131, 126, 23);
		contentPane.add(chckbxCheckbox);
		
		JButton btnActivate = new JButton("Activate");
		btnActivate.addActionListener(e->
		{
			chckbxCheckbox.setSelected(true);
		});
		btnActivate.setBounds(162, 110, 114, 25);
		contentPane.add(btnActivate);
		
		JButton btnDeactivate = new JButton("Deactivate");
		btnDeactivate.addActionListener(e->
		{
			chckbxCheckbox.setSelected(false);
		});
		btnDeactivate.setBounds(162, 147, 114, 25);
		contentPane.add(btnDeactivate);
	}
}
