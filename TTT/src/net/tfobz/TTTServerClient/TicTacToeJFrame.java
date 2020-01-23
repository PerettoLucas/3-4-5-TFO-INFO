package net.tfobz.TTTServerClient;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.tfobz.tictactoe.Spielfeld;

/**
 * Stellt das Spielfeld des Spiels TicTacToe in einem JFrame-Fenster dar. Das Spielfeld
 * wird durch Kn�pfe des Typs TicTacToeJButton dargestellt. Weiters verf�gt das Fenster
 * �ber eine �berschrift, welche denselben Text wie der Fenstertitel enth�lt und eine 
 * Statusleiste durch welche Meldungen an den Benutzer ausgegeben werden k�nnen 
 * @author Michael Wild
 */
@SuppressWarnings("serial")
public class TicTacToeJFrame extends JFrame
{
	/**
	 * Das Spielfeld der Kn�pfe 
	 */
	private TicTacToeJButton[][] b = null;
	/**
	 * Die Statusleiste
	 */
	private JLabel statusleiste = null;
	/**
	 * Ein Verweis auf das Objekt welches das TicTacToe-Spiel realisiert
	 */
	private Spielfeld tictactoe = null;
	/**
	 * Legt fest, ob das Fenster auf Eingaben des Benutzers reagiert oder nicht
	 */
	private boolean eingabenMoeglich = false;
	/**
	 * Die Feldnummer des Feldes das der Benutzer durch die maus ausgew�hlt hat
	 */
	private int gewaehlteFeldnummer = -1;
	
	/**
	 * Konstruktor der die Benutzerschnittstelle in einem Fenster erstellt und anzeigt.
	 * Dem Konstruktor wird der Titel des Fensters �bergeben. Weiters erh�lt der Konstruktor
	 * das TicTacToe-Spielobjekt aus welchem dann die Benutzerschnittstelle die Gr��e
	 * des Spielfeldes und die gesetzten Z�ge ermitteln kann. Dieses Objekt stellt das
	 * Hauptprogramm dar
	 * @param titel des Fensters
	 * @param tictactoe jenes Objekt welches die Funktionalit�t des Spiels in sich birgt,
	 * entweder ein Objekt vom Typ TicTacToeServer oder TicTacToeClient
	 */
	public TicTacToeJFrame(String titel, Spielfeld tictactoe) {
		super(titel);
		// Verbindung zum Hauptprogramm
		this.tictactoe = tictactoe;

		// Anlegen der �berschrift und der Statuszeile
		JLabel ueberschrift = new JLabel(titel);
		ueberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		ueberschrift.setFont(new Font(this.getContentPane().getFont().getName(),Font.BOLD,20));
		this.getContentPane().add(ueberschrift, BorderLayout.NORTH);
		statusleiste = new JLabel("Statusleiste");
		statusleiste.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(statusleiste, BorderLayout.SOUTH);
		
		// Anlegen der Kn�pfe
		JPanel p = new JPanel();
		p.setLayout(
			new GridLayout(this.tictactoe.getFeldgroesse(),this.tictactoe.getFeldgroesse()));
		b = new 
			TicTacToeJButton[this.tictactoe.getFeldgroesse()][this.tictactoe.getFeldgroesse()];
		int feldnummer = 0;
		for (int i = 0; i < this.tictactoe.getFeldgroesse(); i = i + 1)
			for (int j = 0; j < this.tictactoe.getFeldgroesse(); j = j + 1) {
				b[i][j] = new TicTacToeJButton(feldnummer);
				feldnummer = feldnummer + 1;
				// Registrieren des Aktionsabhoerers
				b[i][j].addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (eingabenMoeglich) {
								// Fenster reagiert auf das Klicken der Kn�pfe. Es wird der 
								// gew�hlte Knopf gemerkt und sofort das Dr�cken weiterer Kn�pfe
								// unterbunden
								eingabenMoeglich = false;
								gewaehlteFeldnummer = ((TicTacToeJButton)(e.getSource())).getFeldnummer();
							}
						}
					}
				);
				p.add(b[i][j]);
			}
		this.getContentPane().add(p,BorderLayout.CENTER);
		
		// Schlie�en des Fensters
		this.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					// Das Fenster wird geschlossen und mit ihm das gesamte Programm
					setVisible(false);
					dispose();
					System.exit(0);
				}
			}
		);
		this.setBounds(100,100,250,350);
		this.setVisible(true);
	}
	
	/**
	 * Stellt das Fenster neu dar indem es auch die gesetzten Z�ge in den Kn�pfen
	 * darstellt
	 */
	public void repaint() {
		for (int i = 0; i < this.tictactoe.getFeldgroesse(); i = i + 1)
			for (int j = 0; j < this.tictactoe.getFeldgroesse(); j = j + 1) {
				if (this.tictactoe.getSpielfeld(i,j) == this.tictactoe.SPIELER1)
			  	this.b[i][j].setX();
				if (this.tictactoe.getSpielfeld(i,j) == this.tictactoe.SPIELER2)
			  	this.b[i][j].set0();
			}
		super.repaint();
	}
	
	/*
	 * Getter- und Settermethoden
	 */
	/**
	 * Setzt den Statusleistentext des Fensters
	 * @param statusleistentext der zu setzen ist
	 */
	public void setStatusleistentext(String statusleistentext) {
		this.statusleiste.setText(statusleistentext);
	}
	/**
	 * Diese Methode liefert nur den ausgew�hlten Zug zur�ck. Sie setzt kein "X" oder "0"
	 * in den Knopf des Spielfeldes. Diese Methode wartet solange, solange der Zug nicht
	 * gesetzt ist und beendet erst dann den Aufruf, wenn der Zug �ber das Fenster 
	 * eingegeben wurde 
	 * @return der ausgew�hlte Zug
	 */
	public int getGewaehlteFeldnummer() {
		int ret = -1;
		this.eingabenMoeglich = true;
		this.gewaehlteFeldnummer = -1;
		do 
		{
			Thread.yield();
			ret = this.gewaehlteFeldnummer;
		} while (ret == -1);
		this.eingabenMoeglich = false;
		return ret;
	}
}
