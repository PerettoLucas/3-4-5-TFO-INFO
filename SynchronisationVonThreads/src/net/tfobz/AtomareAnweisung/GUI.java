package net.tfobz.AtomareAnweisung;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import java.awt.TextField;
import java.lang.reflect.InvocationTargetException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class GUI extends JFrame
{

	private JPanel contentPane;
	private Int I = null;
	

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
					GUI frame=new GUI();
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
	public GUI()
	{
		setTitle("AtomareAnweisung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,446,236);
		setResizable(false);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		I = new Int();
		Increment increment1 = new Increment(I);
		Increment increment2 = new Increment(I);
		
		JButton btnIncrement = new JButton("Start Incrementation");
		contentPane.add(btnIncrement, BorderLayout.NORTH);
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Label label = new Label("Result :");
		panel.add(label);
		
		TextField textField = new TextField();
		textField.setColumns(30);
		panel.add(textField);
		
		JProgressBar progressBar = new JProgressBar(0,2000000);
		contentPane.add(progressBar, BorderLayout.CENTER);
		
		btnIncrement.addActionListener(e->{
			increment1.start();
			increment2.start();
		});
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(I.i <= 2000000)
				{
					try {
						SwingUtilities.invokeAndWait(()->{
								progressBar.setValue(I.i);
								textField.setText(""+I.i);
						});
					} catch (InvocationTargetException | InterruptedException e1) {e1.printStackTrace();}
				}
			}
		}).start();
	}

}
