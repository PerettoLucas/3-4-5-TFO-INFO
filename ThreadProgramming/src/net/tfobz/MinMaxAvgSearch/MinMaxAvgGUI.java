package net.tfobz.MinMaxAvgSearch;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class MinMaxAvgGUI extends JFrame
{

	private JPanel contentPane;
	private JTextField textFieldMin;
	private JTextField textFieldMax;
	private JTextField textFieldAvg;
	
	private ArrayList<Integer> fieldArrayList = new ArrayList<Integer>();
	JProgressBar progressBarMin = null;
	
	JProgressBar progressBarMax = null;
	
	JProgressBar progressBarAvg = null;
	
	
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
					MinMaxAvgGUI frame=new MinMaxAvgGUI();
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
	public MinMaxAvgGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,830,296);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		setResizable(false);
		
		//Filling Array
		for(int i = 0; i < 10000000;i++)
		{
			fieldArrayList.add(new Random().nextInt((Integer.MAX_VALUE - 2) + 1) + 2);					
		}
		
		progressBarAvg = new JProgressBar(0,fieldArrayList.size());
		progressBarMax = new JProgressBar(0,fieldArrayList.size());
		progressBarMin = new JProgressBar(0,fieldArrayList.size());
		
		
		textFieldMin = new JTextField();
		textFieldMin.setColumns(10);
		
		textFieldMax = new JTextField();
		textFieldMax.setColumns(10);
		
		textFieldAvg = new JTextField();
		textFieldAvg.setColumns(10);
		
		
		JButton btnStartSearching = new JButton("Start Searching");
		
		
		JLabel lblMin = new JLabel("Min:");
		
		JLabel lblMax = new JLabel("Max:");
		
		JLabel lblAvg = new JLabel("Avg:");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblMax, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(textFieldMax, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(progressBarMax, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblAvg, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(textFieldAvg, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(progressBarAvg, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblMin, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldMin, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnStartSearching, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
								.addComponent(progressBarMin, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnStartSearching, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblMin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textFieldMin, Alignment.LEADING)
						.addComponent(progressBarMin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(progressBarMax, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldMax, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMax, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAvg, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(progressBarAvg, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldAvg, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
		btnStartSearching.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new FindMin(fieldArrayList, progressBarMin, textFieldMin).start();
				new FindMax(fieldArrayList,progressBarMax,textFieldMax).start();
				new FindAvg(fieldArrayList,progressBarAvg,textFieldAvg).start();
			}
		});
		
		
		
	}
}
