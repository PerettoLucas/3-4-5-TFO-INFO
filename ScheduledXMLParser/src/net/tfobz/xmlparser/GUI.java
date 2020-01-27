package net.tfobz.xmlparser;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	private ArrayList<RssReaderRunnable> rssReaderList = new ArrayList<>();
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
	private JEditorPane editorPane;
	private StringBuilder stringBuilder = new StringBuilder();
	private boolean active_flag = true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> 
		{
			try
			{
				GUI frame=new GUI();

				frame.setVisible(true);
			}catch(Exception e)
			{e.printStackTrace();}
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
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		
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
		
		btnAddUrl.setMnemonic('A');
		btnDisactivateScheduler.setMnemonic('S');
		btnUpdate.setMnemonic('U');
		
		// Test URLS
		rssReaderList.add(new RssReaderRunnable("https://www.suedtirolnews.it/feed", editorPane, stringBuilder));
		rssReaderList.add(new RssReaderRunnable("http://www.provinz.bz.it/wetter/rss.asp", editorPane, stringBuilder));
		rssReaderList.add(new RssReaderRunnable("https://www.spiegel.de/schlagzeilen/tops/index.rss", editorPane, stringBuilder));
		
		for (RssReaderRunnable rssReader2Runnable : rssReaderList) 
		{
			scheduler.scheduleAtFixedRate(rssReader2Runnable, 0, 10, TimeUnit.SECONDS);
		}		
		
		btnAddUrl.addActionListener(e -> 
		{

			String url = JOptionPane.showInputDialog("Geben sie die URL ein : ");
			if(url == null || url.isEmpty()) JOptionPane.showMessageDialog(getParent(), "Cannot be Empty");
			try 
			{
				rssReaderList.add(new RssReaderRunnable(url, editorPane, stringBuilder));
			} catch (Exception e2) 
			{JOptionPane.showMessageDialog(getParent(), "Cannot parse given URL");}
			
		});
		
		
		
		btnUpdate.addActionListener(e -> 
		{
			scheduler = Executors.newScheduledThreadPool(10);
			
			stringBuilder.append("<b>Updating Channels Manually: </b><br>");
			for (RssReaderRunnable rssReaderRunnable : rssReaderList) 
			{
				scheduler.execute(rssReaderRunnable);
			}
			
		});
		
		
		btnDisactivateScheduler.addActionListener(e -> 
		{
			
			if(active_flag) 
			{
				btnDisactivateScheduler.setText("Activate Scheduler");
				
				scheduler.shutdown();
				stringBuilder.append("<b> Scheduler is now deactivated </b><br>");
				
				editorPane.setText(stringBuilder.toString());
				active_flag = false;
			}
			else
			{
				btnDisactivateScheduler.setText("Deactivate Scheduler");
				stringBuilder.append("<br><b> Scheduler is now Active (schedule Period = 10 Sec.) </b><br>");
				editorPane.setText(stringBuilder.toString());
				
				scheduler = Executors.newScheduledThreadPool(10);
				
				MessageRunnable messageRunnable = new MessageRunnable(editorPane, stringBuilder);
				scheduler.scheduleAtFixedRate(messageRunnable, 0, 10, TimeUnit.SECONDS);
				
				for (RssReaderRunnable rssReaderRunnable : rssReaderList) 
				{
					scheduler.scheduleAtFixedRate(rssReaderRunnable, 0, 10, TimeUnit.SECONDS);
				}
				
				active_flag = true;
			}
		});
		
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