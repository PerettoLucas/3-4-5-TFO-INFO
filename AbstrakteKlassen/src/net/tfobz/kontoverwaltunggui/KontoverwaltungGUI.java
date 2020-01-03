package net.tfobz.kontoverwaltunggui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.tfobz.kontoverwaltung.Gehaltskonto;
import net.tfobz.kontoverwaltung.Konto;
import net.tfobz.kontoverwaltung.KontoException;
import net.tfobz.kontoverwaltung.Sparkonto;
import java.awt.Font;

@SuppressWarnings("serial")
public class KontoverwaltungGUI extends JFrame
{

	private JPanel contentPane;
	private JTextField betrag1;
	private JTextField betrag2;
	private JTextField kontonummer1;
	private JTextField kontonummer2;

	private static final double STARTZINSSATZ = 0.25;
	private static final double STARTUEBERZIEHUNG = -1000;
	private ArrayList<Konto> kontenliste = null;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try {
					KontoverwaltungGUI frame = new KontoverwaltungGUI();
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
	public KontoverwaltungGUI()
	{

		kontenliste = new ArrayList<Konto>();
		try 
		{
			Gehaltskonto.setStartueberziehung(STARTUEBERZIEHUNG);
			Gehaltskonto.setStartzinssatz(STARTZINSSATZ);
			Sparkonto.setStartzinssatz(STARTZINSSATZ);
		} catch (KontoException e) {e.printStackTrace();}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 468);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		betrag1 = new JTextField();
		betrag1.setBorder(new EmptyBorder(0, 0, 0, 0));
		betrag1.setBounds(10, 30, 142, 20);
		contentPane.add(betrag1);
		betrag1.setColumns(10);

		betrag2 = new JTextField();
		betrag2.setBorder(new EmptyBorder(0, 0, 0, 0));
		betrag2.setColumns(10);
		betrag2.setBounds(162, 30, 142, 20);
		contentPane.add(betrag2);

		JLabel lblEingabe1 = new JLabel("ersteZahlung / Buchen / Ueberweisung");
		lblEingabe1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEingabe1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEingabe1.setBounds(10, 0, 182, 33);
		contentPane.add(lblEingabe1);

		JLabel lblEingabe2 = new JLabel("Sparrate");
		lblEingabe2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEingabe2.setBounds(176, 9, 142, 14);
		contentPane.add(lblEingabe2);

		kontonummer1 = new JTextField();
		kontonummer1.setBorder(new EmptyBorder(0, 0, 0, 0));
		kontonummer1.setColumns(10);
		kontonummer1.setBounds(10, 88, 142, 20);
		contentPane.add(kontonummer1);

		kontonummer2 = new JTextField();
		kontonummer2.setBorder(new EmptyBorder(0, 0, 0, 0));
		kontonummer2.setColumns(10);
		kontonummer2.setBounds(162, 88, 142, 20);
		contentPane.add(kontonummer2);

		JLabel lblKontonummer1 = new JLabel("Kontonummer 1");
		lblKontonummer1.setHorizontalAlignment(SwingConstants.CENTER);
		lblKontonummer1.setBounds(10, 69, 142, 14);
		contentPane.add(lblKontonummer1);

		JLabel lblKontonummer2 = new JLabel("Kontonummer 2");
		lblKontonummer2.setHorizontalAlignment(SwingConstants.CENTER);
		lblKontonummer2.setBounds(162, 69, 142, 14);
		contentPane.add(lblKontonummer2);

		JButton btnGehaltkonto = new JButton("Neues Gehaltskonto");
		btnGehaltkonto.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnGehaltkonto.setBackground(Color.LIGHT_GRAY);
		btnGehaltkonto.setBounds(372, 15, 151, 23);
		contentPane.add(btnGehaltkonto);

		JButton btnSparkonto = new JButton("Neues Sparkonto");
		btnSparkonto.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSparkonto.setBackground(Color.LIGHT_GRAY);
		btnSparkonto.setBounds(533, 15, 151, 23);
		contentPane.add(btnSparkonto);

		JButton btnAnzeigen = new JButton("Anzeigen");
		btnAnzeigen.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnAnzeigen.setBackground(Color.LIGHT_GRAY);
		btnAnzeigen.setBounds(372, 49, 312, 23);
		contentPane.add(btnAnzeigen);

		JButton btnBuchen = new JButton("Buchen");
		btnBuchen.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnBuchen.setBackground(Color.LIGHT_GRAY);
		btnBuchen.setBounds(372, 87, 151, 23);
		contentPane.add(btnBuchen);

		JButton btnUberweisen = new JButton("Ueberweisen");
		btnUberweisen.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnUberweisen.setBackground(Color.LIGHT_GRAY);
		btnUberweisen.setBounds(533, 87, 151, 23);
		contentPane.add(btnUberweisen);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBounds(10, 140, 674, 288);
		contentPane.add(textArea);
		
		JButton btnTestss = new JButton("Testss");
		btnTestss.setBounds(176, 117, 89, 23);
		contentPane.add(btnTestss);
		
		btnTestss.addActionListener(new ActionListener() {
			
			boolean flag = false ;
			@Override
			public void actionPerformed(ActionEvent e)
			{	
				if(!flag)
				{
					flag = true;
					//Color and Text
					btnTestss.setBackground(Color.GREEN);
					btnTestss.setText("fertig:");
					
					//Disenabling other Fields
					btnAnzeigen.setEnabled(false);
				}
				else if(flag) 
				{
					// Doing action and ReEnebling Fields
					setTextArea("guat morgen");
					btnAnzeigen.setEnabled(true);
					
					//Restore Color and Text of Button
					btnTestss.setText("Testss");
					btnTestss.setBackground(Color.LIGHT_GRAY);
					flag = false;
				}
			}
		});

		btnGehaltkonto.addActionListener(new ActionListener() {
			boolean flag = false ;
			public void actionPerformed(ActionEvent arg0)
			{
				
				if(!flag)
				{
					flag = true;
					//Color and Text
					btnGehaltkonto.setBackground(Color.GREEN);
					btnGehaltkonto.setText("click to Confirm");
					
					//Disenabling other Fields
					betrag1.setEnabled(false);
					betrag2.setEnabled(false);
					kontonummer1.setEnabled(false);
					kontonummer2.setEnabled(false);
					
					btnSparkonto.setEnabled(false);
					btnAnzeigen.setEnabled(false);
					btnBuchen.setEnabled(false);
					btnUberweisen.setEnabled(false);
				}
				else if(flag) 
				{
					// Doing action 
					Gehaltskonto gehaltskonto = new Gehaltskonto();
					setTextArea("Gehaltskonto erstellt :" + gehaltskonto.toString());
					kontenliste.add(gehaltskonto);
					
					//ReEnebling Fields
					betrag1.setEnabled(true);
					betrag2.setEnabled(true);
					kontonummer1.setEnabled(true);
					kontonummer2.setEnabled(true);
					
					btnSparkonto.setEnabled(true);
					btnAnzeigen.setEnabled(true);
					btnBuchen.setEnabled(true);
					btnUberweisen.setEnabled(true);
					
					//Restore Color and Text of Button
					btnGehaltkonto.setText("Neues Gehaltskonto");
					btnGehaltkonto.setBackground(Color.LIGHT_GRAY);
					flag = false;
				}
				
				
					
			}
		});
		btnSparkonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				int option = JOptionPane.showConfirmDialog(null, "Neues Sparkonto erstellen ?",
						"Erstellen eines Sparkontos", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) 
				{
					Sparkonto sparkonto;
					try 
					{
						sparkonto = new Sparkonto(Double.parseDouble(betrag1.getText()),
								Double.parseDouble(betrag2.getText()));
						setTextArea("Sparkonto erstellt :" + sparkonto.toString());
						kontenliste.add(sparkonto);
					} catch (NumberFormatException nu) {
						JOptionPane.showMessageDialog(null,
								"Fehlerhafte Eingabe [" + betrag1.getText() + "] oder [" + betrag2.getText() + "]");
						setTextArea("Fehlerhafte Eingabe [" + betrag1.getText() + "] oder [" + betrag2.getText() + "]");
					} catch (KontoException e) {
						System.out.println(e.toString());
						System.out.println(e.getMessage());
						JOptionPane.showMessageDialog(null, e.getMessage());
						setTextArea("Ungültige Eingabe [" + betrag1.getText() + "] oder [" + betrag2.getText() + "]");
					}
				}
				betrag1.setText("");
				betrag2.setText("");
			}
		});
		btnAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				int kontonummer = -1;
				try {
					kontonummer = Integer.parseInt(kontonummer1.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fehlerhafte Eingabe [" + kontonummer1.getText() + "]");
					setTextArea("Fehlerhafte Eingabe [" + kontonummer1.getText() + "]");
				}
				for (Konto konto : kontenliste) {
					if (konto.getKontoNummer() == kontonummer) {
						setTextArea(konto.toString());
						break;
					}
				}
				kontonummer1.setText("");
			}
		});
		btnBuchen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{

				int kontonummer = -1;
				double betrag = 0;
				try {
					kontonummer = Integer.parseInt(kontonummer1.getText());
					betrag = Double.parseDouble(betrag1.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"Fehlerhafte Eingabe [" + kontonummer1.getText() + "] oder [" + betrag1.getText() + "]");
					setTextArea(
							"Fehlerhafte Eingabe [" + kontonummer1.getText() + "] oder [" + betrag1.getText() + "]");
				}
				for (Konto konto : kontenliste) {
					if (konto.getKontoNummer() == kontonummer) {
						try {
							konto.buchen(betrag);
							setTextArea("Buchen erfolgreich " + konto.toString());
						} catch (KontoException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
							System.out.println(e.toString());
							System.out.println(e.getMessage());
						}
						break;
					}
				}
				betrag1.setText("");
				kontonummer1.setText("");
			}
		});
		btnUberweisen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{

				int konto1 = -1;
				int konto2 = -1;
				double betrag = 0;
				try {
					konto1 = Integer.parseInt(kontonummer1.getText());
					konto2 = Integer.parseInt(kontonummer2.getText());
					betrag = Double.parseDouble(betrag1.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Fehlerhafte Eingabe [" + kontonummer1.getText() + "] oder ["
							+ betrag1.getText() + "] oder [" + kontonummer2.getText() + "]");
					setTextArea("Fehlerhafte Eingabe [" + kontonummer1.getText() + "] oder [" + betrag1.getText()
							+ "] oder [" + kontonummer2.getText() + "]");
				}
				if (betrag > 0) {
					for (Konto konto : kontenliste) {
						if (konto.getKontoNummer() == konto1) {
							try {
								konto.buchen(-betrag);
								setTextArea("Buchen erfolgreich " + konto.toString());
							} catch (KontoException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage());
								System.out.println(e1.toString());
								System.out.println(e1.getMessage());
								return;
							}
						}
					}
					for (Konto konto : kontenliste) {
						if (konto.getKontoNummer() == konto2) {
							try {
								konto.buchen(betrag);
								setTextArea("Buchen erfolgreich " + konto.toString());
							} catch (KontoException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage());
								System.out.println(e1.toString());
								System.out.println(e1.getMessage());
								return;
							}
						}
					}
					JOptionPane.showMessageDialog(null,
							"Überweisung von Konto " + konto1 + " nach " + konto2 + " erfolgreich, Betrag: " + betrag);
				}
			}
		});

	}

	public void setTextArea(String text)
	{
		textArea.setText(textArea.getText() + "\n" + text);
	}
}
