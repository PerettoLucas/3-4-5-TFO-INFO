package net.tfobz.TTTServerClient;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;

/**
 * Diese von JButton abgeleitete Klasse dient zum Anzeigen und Ausw�hlen der Tipps im
 * Spiel TicTacToe. Sie erweitert die Funktionalit�t indem entweder X oder 0 am Knopf
 * angezeigt wird. Der Text des Knopfes wird autmatisch auf die Gr��e des Knopfes 
 * skaliert.<br>
 * Beim Anlegen des Knopfes wird ihm die Feldnummer �bergeben f�r welche der Knopf im
 * Spielfeld steht
 * @author Michael Wild
 */
@SuppressWarnings("serial")
public class TicTacToeJButton extends JButton
{
	/**
	 * Das am Knopf darzustellende Zeichen (entweder X oder 0)
	 */
	private String zeichen = null;
	/**
	 * Die Feldnummer des Feldes f�r das der Knopf im Spielfeld steht  
	 */
	private int feldnummer = -1;
	
	/**
	 * Konstruktor der die Feldnummer des Feldes f�r das der Knopf im TicTacToe-Spielfeld
	 * steht
	 * @param feldnummer
	 */
	public TicTacToeJButton(int feldnummer) {
		this.feldnummer = feldnummer;
	}
	
	/**
	 * Diese Methode sorgt daf�r, dass das f�r den Knopf gesetzte Zeichen am Knopf in der
	 * Mitte und in der Gr��e des Knopfes gezeichnet wird 
	 * @param g der Grafikkontext
	 */
	public void paint(Graphics g) {
		super.paint(g);
		if (this.zeichen != null) {
			// Zeichensatz "Courier New" wird in der Normalschriftart angelegt. Dabei wird die
			// Schriftgr��e automatisch auf die Gr��e des Knopfes angepasst
			g.setFont(new Font("Courier New",Font.PLAIN,this.getHeight()));
			// Breite des Zeichens wird ermittelt
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D r = fm.getStringBounds(this.zeichen,g);
			// Das Zeichen wird in der Mitte des Knopfes ausgegeben, getAscent() liefert die
			// tats�chliche H�he des Zeichens
			g.drawString(this.zeichen,(int)(this.getWidth() - r.getWidth()) / 2,
				fm.getAscent());
		}
	}
	
	/*
	 * Getter- und Settermethoden
	 */
	/**
	 * Setzt ein "X" als Text im Knopf aber nur dann, wenn noch kein Zeichen gesetzt wurde
	 */
	public void setX() {
		if (this.zeichen == null)
			this.zeichen = "X";
	}
	/**
	 * Setzt ein "O" als Text im Knopf aber nur dann, wenn noch kein Zeichen gesetzt wurde
	 */
	public void set0() {
		if (this.zeichen == null)
			this.zeichen = "O";
	}
	/**
	 * Liefert die Position - also die Feldnummer - des Knopfes im Spielfeld zur�ck. dabei
	 * werden die Felder in einem 3x3-Spielfeld folgenderma�en durchnummeriert:<br>
	 * 0, 1, 2<br>
	 * 3, 4, 5<br>
	 * 6, 7, 8
	 * @return die Feldnummer des Knopfes
	 */
	public int getFeldnummer() {
		return this.feldnummer;
	}
}
