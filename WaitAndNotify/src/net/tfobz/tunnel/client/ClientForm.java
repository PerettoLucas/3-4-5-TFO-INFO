package net.tfobz.tunnel.client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;
import javax.swing.JTextArea;

/**
 * Diese Klasse erstellt die Benutzerschnittstelle und den GuidesMonitor zur 
 * Verwaltung der Gruppenf�hrer pro Eingang. Sie enth�lt auch die 
 * Ereignisbehandlungsmethoden f�r die beiden Kn�pfe. In diesen Methoden werden 
 * die Objekte vom Typ ClientThread zur Behandlung der Clientanfragen angelegt 
 * und die Threads gestartet.<br><br>
 * <b>Ereignisbehandlungsmethode Besichtigung anfordern</b><br>
 * Diese Methode kontrolliert zuerst, ob eine Besucherzahl ins Textfeld
 * eingegeben wurde und konvertiert den Inhalt in eine Zahl. Diese Zahl
 * darf nicht gr��er sein als das maximale Fassungsverm�gen des Tunnels.
 * Dann wird der ClientThread gestartet, dem diese Besucheranzahl und
 * die Referenzen auf das ClientForm sowie auf den GuidesMonitor
 * �bergeben werden.<br><br>
 * <b>Ereignisbehandlungsmethode Besichtigung beenden</b><br>
 * Zuerst wird kontrolliert ob es �berhaupt Aktive Besichtigungen gibt,
 * welche von diesem Eingang aus den Tunnel betreten haben. Sind solche
 * vorhanden, dann wird kontrolliert, ob eine aktive Besichtigung 
 * ausgew�hlt wurde. Ist dies der Fall so wird aus dem Text des ausgew�hlten
 * JList-Eintrages die Anzahl der Besucher ermittelt und in eine Zahl
 * konvertiert. Dann wird der ClientThred gestartet, dem diese negative (!)
 * Anzahl und Referenzen auf ClientForm und GuidesMonitor 
 * �bergeben werden
 */
@SuppressWarnings("serial")
public class ClientForm extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Monitor durch welchen am Eingang ein F�hrer reserviert werden kann
	 */
	private GuidesMonitor guidesMonitor = null;
	/**
	 * Modell zur Verwaltung der Inhalte der JList
	 */
	protected DefaultListModel<String> mActiveVisits = null;
	
	public JTextField txtVisitors;
	public JTextField txtFieldActiveVisits;
	
	public JLabel lblEntrance;
	public JLabel lblAvailableGuides;
	public Label lblVisitors;
	public JLabel lblActiveVisits;
	public JLabel lblAvailableVisitors;
	public JLabel lblStatus;
	public JTextArea txtAreaStatus;
	
	private boolean flag = false;
	
	/**
	 * Legt das Formular an und macht es sichtbar. Beim Anlegen des Forumulas
	 * wird auch der GuidesMonitor angelegt. Nachdem das Formular angelegt wurde,
	 * werden in Abst�nden von einer Sekunde Serveranfragen geschickt zur 
	 * Ermittlung der verf�gbaren Besucher, d. h. der Server antwortet und
	 * liefert die Anzahl je Besucheranzahl zur�ck die noch in den Tunnel 
	 * eingelassen werden kann
	 * @param args
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ClientForm frame=new ClientForm();
					frame.setVisible(true);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public ClientForm()
	{
		guidesMonitor = new GuidesMonitor(this);
		
		setResizable(false);
		setTitle("Entrance");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,544,588);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		lblEntrance = new JLabel("Entrance");
		lblEntrance.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblEntrance.setBounds(12, 12, 127, 38);
		contentPane.add(lblEntrance);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(12, 54, 217, 118);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblAvailableGuides = new JLabel("Available Guides: ");
		lblAvailableGuides.setBounds(12, 12, 193, 15);
		panel.add(lblAvailableGuides);
		
		lblVisitors = new Label("Visitors:");
		lblVisitors.setBounds(10, 33, 68, 21);
		lblVisitors.setFont(new Font("Dialog", Font.BOLD, 12));
		panel.add(lblVisitors);
		
		txtVisitors = new JTextField();
		txtVisitors.setBounds(155, 35, 50, 19);
		panel.add(txtVisitors);
		txtVisitors.setColumns(10);
		
		JButton btnRequestVisit = new JButton("Request visit");
		btnRequestVisit.setBounds(12, 66, 193, 38);
		panel.add(btnRequestVisit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(12, 178, 217, 269);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblActiveVisits = new JLabel("Active visits:");
		lblActiveVisits.setBounds(12, 12, 193, 15);
		panel_1.add(lblActiveVisits);
		
		txtFieldActiveVisits = new JTextField();
		txtFieldActiveVisits.setBounds(12, 39, 193, 172);
		panel_1.add(txtFieldActiveVisits);
		txtFieldActiveVisits.setColumns(10);
		
		JButton btnFinishVisit = new JButton("Finish visit");
		btnFinishVisit.setBounds(12, 223, 193, 36);
		panel_1.add(btnFinishVisit);
		
		lblAvailableVisitors = new JLabel("Available Visitors: ");
		lblAvailableVisitors.setBounds(22, 459, 207, 15);
		contentPane.add(lblAvailableVisitors);
		
		lblStatus = new JLabel("Status:");
		lblStatus.setBounds(241, 35, 66, 15);
		contentPane.add(lblStatus);
		
		txtAreaStatus = new JTextArea();
		txtAreaStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAreaStatus.setEditable(false);
		txtAreaStatus.setBounds(241, 54, 289, 495);
		contentPane.add(txtAreaStatus);
		txtAreaStatus.setColumns(10);

		
		
		lblAvailableGuides.setText(lblAvailableGuides.getText() + " " + guidesMonitor.availableGuides);
		
		
		/*<b>Ereignisbehandlungsmethode Besichtigung anfordern</b><br>
		 * Diese Methode kontrolliert zuerst, ob eine Besucherzahl ins Textfeld
		 * eingegeben wurde und konvertiert den Inhalt in eine Zahl. Diese Zahl
		 * darf nicht gr��er sein als das maximale Fassungsverm�gen des Tunnels.
		 * Dann wird der ClientThread gestartet, dem diese Besucheranzahl und
		 * die Referenzen auf das ClientForm sowie auf den GuidesMonitor
		 * �bergeben werden.<br><br>
		 */
		btnRequestVisit.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					//TODO visitors cannot be greater than the available visitors.
					int visitors = Integer.parseInt(txtVisitors.getText());
					if(visitors > 50 || visitors > 1) JOptionPane.showMessageDialog(ClientForm.this, "Visitors can not be greater than 50 or less than 1!");
					else {
						if (guidesMonitor.availableGuides > 0)
							new Thread(() -> guidesMonitor.request()).start();
						else if(!getFlag()) 
						{
							setFlag(true);
							new Thread(() -> guidesMonitor.request()).start();
						}
					}
				} catch (java.lang.NumberFormatException e2){JOptionPane.showMessageDialog(ClientForm.this, "Visitors: Null or not a Number");}
			}
		});

		
		
		/*
		<b>Ereignisbehandlungsmethode Besichtigung beenden</b><br>
		 * Zuerst wird kontrolliert ob es �berhaupt Aktive Besichtigungen gibt,
		 * welche von diesem Eingang aus den Tunnel betreten haben. Sind solche
		 * vorhanden, dann wird kontrolliert, ob eine aktive Besichtigung 
		 * ausgew�hlt wurde. Ist dies der Fall so wird aus dem Text des ausgew�hlten
		 * JList-Eintrages die Anzahl der Besucher ermittelt und in eine Zahl
		 * konvertiert. Dann wird der ClientThred gestartet, dem diese negative (!)
		 * Anzahl und Referenzen auf ClientForm und GuidesMonitor 
		 * �bergeben werden
		 */
		btnFinishVisit.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//TODO 
				
			}
		});
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				{
					new ClientThread(0, ClientForm.this, guidesMonitor).start();
				}
			}
		}).start();
		
		
		
		//TODO jede sekunde ->Serveranfrage(anzahl der verfügbaren Visitors)
	}
	private void setFlag(boolean set) {this.flag = set; }
	private boolean getFlag() { return this.flag; }
}