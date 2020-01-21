package net.tfobz.xmlparser;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame
{

	private JPanel contentPane;
	private ArrayList<RssReader> rssReaderList = new ArrayList<>();
	private RssReader reader ;
	private String url;
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,759,500);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(Color.LIGHT_GRAY);
		btnUpdate.setBounds(633, 438, 114, 25);
		contentPane.add(btnUpdate);
		
		JButton btnDisactivateScheduler = new JButton("Disactivate Scheduler");
		btnDisactivateScheduler.setBackground(Color.LIGHT_GRAY);
		btnDisactivateScheduler.setBounds(430, 438, 191, 25);
		contentPane.add(btnDisactivateScheduler);
		
		JButton btnAddUrl = new JButton("Add URL ...");
		btnAddUrl.setBackground(Color.LIGHT_GRAY);
		btnAddUrl.setBounds(304, 438, 114, 25);
		contentPane.add(btnAddUrl);
		
		JScrollPane scrollPane = new JScrollPane(editorPane);
		scrollPane.setBounds(0, 0, 753, 437);
		contentPane.add(scrollPane);
		
		
		btnAddUrl.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				url = JOptionPane.showInputDialog("Geben sie die URL ein : ");
				if(url == null || url.isEmpty()) JOptionPane.showMessageDialog(getParent(), "Cannot be Empty");
				try 
				{
					rssReaderList.add(new RssReader(url));
				} catch (Exception e2) 
				{JOptionPane.showMessageDialog(getParent(), "Cannot parse given URL");}
				
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String text_to_show = "";
				
				for (RssReader rssReader : rssReaderList) 
				{
					text_to_show += rssReader.getNewest();
				}
				
				editorPane.setText(text_to_show);				
				
			}
		});
	}
	class RunnableUpdate implements Runnable
	{

		@Override
		public void run() 
		{
			
			
		}
		
	}
}

/*
 * 	https://www.suedtirolnews.it/feed
 * 	http://www.provinz.bz.it/wetter/rss.asp
 * 	https://www.spiegel.de/schlagzeilen/tops/index.rss
 * 
 * 
 * 
 */