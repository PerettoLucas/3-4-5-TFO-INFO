package net.tfobz.kontoverwaltung;

/** 
 * Abstrakte Klasse Konto welche zur Konstruktion von Gehalts- und Sparkonto
 * herangezogen wird
 * <ul>
 *	<li>
 *		Jedes Konto erhält eine eindeutige Kontonummer
 *	</li>
 *	<li>
 *		Für alle Konten wird ein Startzinssatz vergeben, der beim Anlegen eines Kontos 
 *		für das Konto eingestellt wird
 *	</li>
 *	<li>
 *		Jedes Konto verfügt über einen Kontostand
 *	</li>
 *	<li>
 *		Jedes Konto kann Zinsen abwerfen und kann Spesen verursachen. Wie diese berechnet 
 *		werden, wird in Gehalts- und Sparkonto definiert
 *	</li>
 *	<li>
 *		Auf das Konto bzw. vom Konto kann gebucht werden. Nach welchen Regeln dies erfolgen 
 *		muss wird erst in Gehalts- und Sparkonto festgelegt
 *	</li>
 *	<li>
 *		Vom Konto kann ein Betrag auf ein anderes Konto überwiesen werden
 *	</li>
 * </ul>
 * @author Michael Wild
 */
public abstract class Konto
{
	/*
	 * Private Membervariablen
	 */
	/** 
	 * Gibt den Zinssatz vor, der jedem Konto beim Anlegen automatisch zugewiesen wird.
	 */
	protected static double startzinssatz = 0.0;
	/** 
	 * Dadurch wird die Kontonummer jedes neu anzulegenden Kontos ermittelt
	 */
	protected static int naechsteKontonummer = 0;
	
	/** 
	 * Speichert für jedes Konto die Kontonummer ab 
	 */
	protected final int kontonummer;
	/**
	 * Speichert für jedes Konto den momentanen Kontostand ab 
	 */
	protected double kontostand = 0.0;
	/**
	 * Speichert für jedes Konto den bei der Zinsberechnung verwendeten Zinssatz ab 
	 */
	protected double zinssatz = 0.0;
	
	/*
	 * Konstruktor
	 */
	/** 
	 * Beim Anlegen erhält das Konto eine eindeutige Kontonummern und der Zinssatz wird 
	 * übernommen. Es wird jedem neuen Konto eine fortlaufende Kontonummer gegeben. Dabei 
	 * wird keine Kontonummer doppelt vergeben. Eine Kontonummer eines gelöschten Kontos 
	 * darf nicht mehr verwendet werden. Weiters wird für das Konto der Zinssatz gesetzt, 
	 * welcher durch setStartzinssatz für die gesamte Klasse gesetzt wurde. Der Kontostand 
	 * wird auf 0 initialisiert
	 */
	public Konto() {
		this.kontonummer = Konto.naechsteKontonummer;
		Konto.naechsteKontonummer = Konto.naechsteKontonummer + 1;
		this.zinssatz = Konto.startzinssatz;
	}
	
	/*
	 * Abstrakte Methoden
	 */
	/** 
	 * Ermittelt für das Konto den Zinsbetrag
	 * @return die Zinsen die für das Konto anfallen 
	 */
	public abstract double getZinsen();
	/**
	 * Ermittelt für das Konto die Spesen
	 * @return die Spesen des Kontos
	 */
	public abstract double getSpesen();
	
	/*
	 * Getter- und Settermethoden
	 */
	/** 
	 * Merkt sich für die Klasse den Startzinssatz. Wird ein Konto angelegt, so wird der 
	 * durch diese Methode eingestellte Startzinssatz dem Konto zugewiesen. Der Zinssatz 
	 * eines neu angelegtem Kontos wird durch den Startzinssatz initialisiert
	 * @param startzinssatz pstartZinssatz der zu setzende Startzinssatz
	 * @throws KontoException wird ausgelöst, wenn der Startzinssatz negativ ist
	 */
	public static void setStartzinssatz(double startzinssatz) throws KontoException {
		if (startzinssatz < 0)
			throw new KontoException("Zinssatz muss grösser oder gleich Null sein");
		else
			Konto.startzinssatz = startzinssatz;
	}
	
	/** 
	 * Gibt den gesetzten Startzinssatz zurück
	 */
	public static double getStartzinssatz() {
		return Konto.startzinssatz;
	}
	
	/** 
	 * Der Kontostand des Kontos wird gelesen. Auf den Kontostand kann nur lesend 
	 * zugegriffen werden. Buchungen im Konto werden durch die Methoden buchen und 
	 * ueberweisen durchgeführt
	 * @return double den Kontostand
	 */
	public double getKontostand() {
		return this.kontostand;
	}
	
	/** 
	 * Die Kontonummer des Kontos wird gelesen. Auf die Kontonummer kann nur lesend 
	 * zugegriffen werden
	 * @return int die Kontonummer 
	 */
	public int getKontoNummer() {
		return this.kontonummer;
	}
	
	/** 
	 * Der Zinssatz des Kontos wird gelesen
	 * @return double der Zinssatz
	 */
	public double getZinssatz() {
		return this.zinssatz;
	}
	
	/** 
	 * Der Zinssatz des Kontos wird neu gesetzt
	 * @param zinssatz ist der zu setzende Zinssatz
	 * @throws KontoException wird ausgelöst, wenn der Zinssatz kleiner Null ist
	 */
	public void setZinssatz(double zinssatz) throws KontoException {
	  if (zinssatz < 0)
	  	throw new KontoException("Zinssatz darf nicht kleiner als Null sein");
	  else
	  	this.zinssatz = zinssatz;
	}
	
	/*
	 * Methoden
	 */
	/** 
	 * Auf das Konto wird gebucht und damit der Kontostand geändert. Wird ein negativer 
	 * Betrag angegeben, so bedeutet dies, dass vom Konto abgebucht wird. Positive Beträge 
	 * bedeuten eine Erhöhung des Kontostandes
	 * @param betrag der zu verbuchende positive oder negative Betrag
	 * @throws KontoException wird aus Kompatiblitätsgründen zu überlagernden Methoden eingeführt
	 */
	public abstract void buchen(double betrag) throws KontoException;
	
	/** 
	 * Überweist vom Konto auf ein anderes Konto. Überweist vom Konto auf das in der 
	 * Parameterliste angegebene Konto den angegebenen Betrag. Dabei wird der Betrag vom
	 * Konto abgebucht und zum übergebenen Konto dazugebucht
	 * @param k jenes Konto auf welches überwiesen wird
	 * @param betrag der überwiesen wird
	 * @throws KontoException wird ausgelöst, wenn der zu überweisende Betrag negativ ist
	 * oder wenn kein Konto übergeben wurde
	 */
	public void ueberweisen(Konto k, double betrag) throws KontoException {
		if (k == null)
			throw new KontoException("Kein Konto wurde übergeben");
		if (betrag <= 0)
			throw new KontoException("Überweisungsbetrag muss positiv sein");
		else {
			this.buchen(-betrag);
			k.buchen(betrag);
		}
	}
	
	/** 
	 * Ausgabe der Kontodaten. Es wird die Kontonummer, der Kontostand, der Zinssatz, die Zinsen 
	 * und die Spesen des Kontos ausgegeben 
	 * @return die Stringentsprechung des Kontos
	 */
	public String toString() {
		return "KontoNummer: " + this.kontonummer + " Kontostand: " + this.kontostand +
			" Zinssatz: " + this.zinssatz + " Zinsen: "  + this.getZinsen() + " Spesen: " + this.getSpesen();
	}
}