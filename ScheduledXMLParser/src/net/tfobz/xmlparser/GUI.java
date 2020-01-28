package net.tfobz.xmlparser;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GUI extends JFrame
{

	private JPanel contentPane;
	private List<RssReaderRunnable> rssReaderList = new ArrayList<>();
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
	private JEditorPane editorPane;
	private StringBuilder stringBuilder = new StringBuilder();
	private boolean active_flag = false;
	private TrayIcon trayIcon ;
	private Tray tray = null;
	
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
		
		tray = new Tray();
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(Color.LIGHT_GRAY);
		btnUpdate.setBounds(633, 438, 114, 25);
		contentPane.add(btnUpdate);
		
		JButton btnDisactivateScheduler = new JButton("Activate Scheduler");
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
		
		/*
		 * ActionListener für die Knöpfe
		 */
		
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
				stringBuilder.append("<b> Scheduler is now Active (schedule Period = 10 Sec.) </b><br>");
				editorPane.setText(stringBuilder.toString());
				
				
				scheduler = Executors.newScheduledThreadPool(10);
				
				//Sending updating message to Pane and Tray Icon
				scheduler.scheduleAtFixedRate(new MessageRunnable(editorPane, stringBuilder, trayIcon), 10, 10, TimeUnit.SECONDS);
				
				for (RssReaderRunnable rssReaderRunnable : rssReaderList) 
				{
					scheduler.scheduleAtFixedRate(rssReaderRunnable, 10, 10, TimeUnit.SECONDS);
				}
				
				active_flag = true;
			}
		});
		
		
	}
	
	
	public class Tray
	{
		public Tray() 
		{
			// Schedule a job for the event-dispatching thread:
			// adding TrayIcon.
			SwingUtilities.invokeLater(()-> createAndShowGUI());
		}

		private void createAndShowGUI() 
		{
			// Check the SystemTray support
			if (!SystemTray.isSupported()) 
			{
				System.out.println("SystemTray is not supported");
				return;
			}
			
			final PopupMenu popup = new PopupMenu();
			trayIcon = new TrayIcon(createImage("bulb.gif", "tray icon"));
			final SystemTray tray = SystemTray.getSystemTray();

			trayIcon.setImageAutoSize(true);
			// Create a popup menu components
			MenuItem exitItem = new MenuItem("Exit");

			// Add components to popup menu
			popup.addSeparator();
			popup.add(exitItem);

			trayIcon.setPopupMenu(popup);

			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				System.out.println("TrayIcon could not be added.");
				return;
			}

			trayIcon.addActionListener(e->JOptionPane.showMessageDialog(null, "This dialog box is run from System Tray"));
			exitItem.addActionListener(e->
			{
				tray.remove(trayIcon);
				System.exit(0);
			});
		}

		// Obtain the image URL
		protected Image createImage(String path, String description) 
		{
			URL imageURL = Tray.class.getResource(path);
			if (imageURL == null) {
				System.err.println("Resource not found: " + path);
				return null;
			} else {
				return (new ImageIcon(imageURL, description)).getImage();
			}
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