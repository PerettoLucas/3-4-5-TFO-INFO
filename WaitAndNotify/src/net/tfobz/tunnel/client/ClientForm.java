package net.tfobz.tunnel.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		setResizable(false);
		setTitle("Entrance");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,544,588);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEntrance = new JLabel("Entrance");
		lblEntrance.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblEntrance.setBounds(12, 12, 127, 38);
		contentPane.add(lblEntrance);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(12, 54, 217, 118);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAvailableGuides = new JLabel("Available Guides: ");
		lblAvailableGuides.setBounds(12, 12, 193, 15);
		panel.add(lblAvailableGuides);
		
		Label label = new Label("Visitors:");
		label.setBounds(10, 33, 68, 21);
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(155, 35, 50, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnRequestVisit = new JButton("Request visit");
		btnRequestVisit.setBounds(12, 66, 193, 38);
		panel.add(btnRequestVisit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(12, 178, 217, 269);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblActiveVisits = new JLabel("Active visits:");
		lblActiveVisits.setBounds(12, 12, 193, 15);
		panel_1.add(lblActiveVisits);
		
		textField_1 = new JTextField();
		textField_1.setBounds(12, 39, 193, 172);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnFinishVisit = new JButton("Finish visit");
		btnFinishVisit.setBounds(12, 223, 193, 36);
		panel_1.add(btnFinishVisit);
		
		JLabel lblAvailableVisitors = new JLabel("Available Visitors: ");
		lblAvailableVisitors.setBounds(22, 459, 207, 15);
		contentPane.add(lblAvailableVisitors);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(241, 35, 66, 15);
		contentPane.add(lblStatus);
		
		textField_2 = new JTextField();
		textField_2.setBounds(241, 54, 289, 495);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
	}
}