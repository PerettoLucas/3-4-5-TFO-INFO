package net.gobbz.eurorechner;

/**
 * Dieser Euroumrechner stellt Konstanten f�r die Kurse und die Namen der W�hrungen
 * bereit. Weiters kann dem Rechner ein Betrag und die Ausgangsw�hrung �bergeben werden
 * in der dieser Betrag vorliegt: z. B. betrag = 1000, waehrung = 7 f�r Italienische Lire.
 * Der Umrechner kann dann diesen Betrag in eine andere W�hrung durch die Methode
 * getBetrag umrechnen. Dieser Methode wird die W�hrung �bergeben in die umgerechnet
 * werden soll: z. B. getBetrag(1) w�rde im obigen Fall 7,106 ergeben weil 1 f�r 
 * �sterreichische Schillinge steht. 
 * @author Michael Wild
 */
public class EuroUmrechner
{
	/*
	 * Private Membervariablen
	 */
	/**
	 * Speichert die W�hrung ab in welcher der Betrag eingegeben wurde
	 */
	private int waehrung = -1;
	/**
	 * Speichert den umzurechnenden Betrag ab, wobei in waehrung die W�hrung des 
	 * umzurechnenden Betrages eingestellt wurde
	 */
	private double betrag = 0;
	
	/*
	 * �ffentlichen Konstanten 
	 */
	/**
	 * Die Umrechnungskurse der einzelnen W�hrungen in einem konstanten Feld
	 */
	public final double[] KURSE =	
	  {1, 13.7603, 40.3399, 1.95583, 166.386, 6.55957, 0.787564,	
	  1936.27, 40.399, 2.20371, 200.482, 5.94573, 340.75};
	/**
	 * Die Namen der einzelnen W�hrungen in einem konstanten Feld
	 */
	public final static String[] WAEHRUNGEN =	
	  {"Euro", "�sterreichische Schilling", "Belgische Franc", "Deutsche Mark",	
	  "Spanische Peseten", "Franz�sische Franc", "Irische Pfund",	
	  "Italienische Lire", "Luxenburgische Franc", "Niederl�ndische Gulden",	
	  "Portugiesische Escudos", "Finnmark", "Griechische Drachmen"};
	public final int EURO = 0;
	public final int OESTERREICHISCHE_SCHILLING = 1;
	public final int BELGISCHE_FRANC = 2;
	public final int DEUTSCHE_MARK = 3;
	public final int SPANISCHE_PESETEN = 4;
	public final int FRANZOESISCHE_FRANC = 5;
	public final int IRISCHE_PFUND = 6;
	public final int ITALIENISCHE_LIRE = 7;
	public final int LUXENBURGISCHE_FRANC = 8;
	public final int NIEDERLAENDISCHE_GULDEN = 9;
	public final int PORTUGIESISCHE_ESCUDO = 10;
	public final int FINMARK = 11;
	public final int GRIECHISCHE_DRACHMEN = 12;
	
	/**
	 * Setzt die W�hrung, welche den W�hrungsbetrag entspricht. Es muss eine g�ltige
	 * W�hrungsnummer eingegeben werden, ansonsten macht die Methode nichts
	 * @param waehrung die zu setzen ist
	 */
	public void setWaehrung(int waehrung) {
		if (waehrung >= 0 && waehrung < KURSE.length)
			this.waehrung = waehrung;
	}
	/**
	 * Liefert die Nummer der W�hrung, welcher der eingegebene W�hrungsbetrag entspricht
	 * @return die Nummer der W�hrung
	 */
	public int getWaehrung() {
		int ret = this.waehrung;
		return ret;
	}
	
	/**
	 * Setzt den umzurechnenden Betrag. Der Betrag muss gr��er oder gleich 0 sein
	 * @param betrag der gesetzt werden soll
	 */
	public void setBetrag(double betrag) {
		if (betrag >= 0)
			this.betrag = betrag;
	}
	/**
	 * Liefert den in die �bergebene W�hrung umgerechneten W�hrungsbetrag zur�ck. Dabei
	 * muss w�hrung die Nummer einer g�ltigen W�hrung sein
	 * @param waehrung in die umgerechnet werden soll
	 * @return den in die �bergebene W�hrung umgerechnete W�hrungsbetrag
	 */
	public double getBetrag(int waehrung) {
		double ret = 0;
		if (waehrung >= 0 && waehrung < KURSE.length && this.waehrung >= 0)
			ret = this.betrag / KURSE[this.waehrung] * KURSE[waehrung];
		return ret;
	}
}
