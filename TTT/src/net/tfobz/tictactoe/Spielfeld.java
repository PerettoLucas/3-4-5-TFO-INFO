package net.tfobz.tictactoe;

import java.io.IOError;
import java.io.IOException;

/*
 * Simuliert das Spielfeld des Spiels TicTacToe. Dabei besteht die Möglichkeit, 
 * die Züge der zwei Spieler ins Spielfeld einzutragen. Weiters kann erfragt werden, 
 * wer gewonnen hat und ob es überhaupt noch möglich ist zu gewinnen.
 * Das Spielfeld wird in einem zweidimensionalen Feld aufgebaut. 
 * Die Elemente des Feldes sind vom Typ int. 
 * Zu Beginn wird das Spielfeld folgendermaßen initialisiert:
 * 0 1 2
 * 3 4 5
 * 6 7 8
 * Jedem Spieler wird eine Nummer zugeordnet. 
 * Setzt der Spieler mit der Nummer -1 beispielsweise an Position 4, 
 * so wird dort seine Nummer eingetragen:
 * 0 1 2
 * 3 -1 5
 * 6 7 8
 */
public class Spielfeld 
{
	//Nummer des ersten Spielers
	public  final int SPIELER1 = -1;
	//Nummer des zweiten Spielers
	public  final int SPIELER2 = -2;
	//Das Spielfeld
	private int[][] spielfeld;
	
	//Konstruktor initialisiert das Spielfeld mit Zahlen beginnend bei 0.
	public Spielfeld(int feldgroesse){
		if(feldgroesse < 3) feldgroesse = 3;

		spielfeld = new int[feldgroesse][feldgroesse];
		for(int i = 0; i < feldgroesse; i++)
		{
			spielfeld[i] = new int[feldgroesse];
		}
		
		int help = 0; 
		for(int i = 0; i < feldgroesse; i++)
		{
			for(int j = 0; j < feldgroesse; j++)
			{
				spielfeld[i][j] = help;
				help++;
			}
		}
	}		
	/*
	 * Zeilenweise Ausgabe des Spielfeldes. 
	 * Dabei werden an den gesetzten Postionen nicht die Spielernummern ausgegeben 
	 * sondern fuer den ersten Spieler ein X und fuer den Zweiten ein O (O wie Otto).
	 * @see java.lang.Object#toString()
	 * return: das Spielfeld aufgeteilt auf mehrere Zeilen
	 */
	public String toString(){
		//TODO X/O einbinden 
		
		String ret = "";
		
		for(int i = 0; i < spielfeld.length; i++)
		{
			ret += "   ";
			for(int j = 0; j < spielfeld[i].length; j++)
			{
				switch(spielfeld[i][j])
				{
				case SPIELER1:
					ret += "X";
					ret += " ";
					
					break;
				case SPIELER2:
					ret += "O";
					ret += " ";
					break;
				default: 
					ret += spielfeld[i][j];
					ret += " ";
					break;
				}				
			}
			ret = ret + "\n";
		}
		return ret; 
	}
	/*
	 * Liefert die Feldgröße des Spielfeldes zurück
   *
	 * Returns: Die Feldgröße des Spielfeldes 
	 */
	public int getFeldgroesse() {
		return spielfeld.length;
	}
	/*
	 * Ermittelt wie das Spielfeld an der Stelle zeile/spalte gesetzt ist

	 *	Parameters:
   *  	zeile - des Spielfeldes an der nachgeschaut werden soll
   *  	spalte - des Spielfeldes an der nachgeschaut werden soll
   *  Returns:
   *  	0 falls an der übergebenen Position noch kein Spieler gesetzt hat
   *  	SPIELER1 falls an der übergebenen Position der erste Spieler gesetzt hat
   *  	SPIELER2 falls an der übergebenen Position der zweite Spieler gesetzt hat
   *  	-3 falls zeile und/oder spalte außerhalb des Spielfeldes zugreifen wollen 
	 */
	public int getSpielfeld(int zeile, int spalte) {
		if(zeile > getFeldgroesse() || spalte > getFeldgroesse() || spalte < 0 || zeile < 0) return -3;
		if(spielfeld[zeile][spalte] == SPIELER1) return SPIELER1;
		if(spielfeld[zeile][spalte] == SPIELER2) return SPIELER2;
		if(spielfeld[zeile][spalte] != SPIELER1 && spielfeld[zeile][spalte] != SPIELER2) return 0;
		
		else return -3;
	}
	
	/*
	 *  Setzt den Zug des Spielers 
	 *  Parameters:
   *    zug - den zu setzenden Zug
   *  Returns:
   *  	0 falls Zug erfolgreich gesetzt werden konnte
   * 	 -1 falls der Zug außerhalb des Spielfeldes liegt
   * 	 -2 falls der Zug bereits gesetzt wurde
	 */
	public int setZugSpieler1(int zug) {
		return setZug(zug,SPIELER1);
	}
	
	/*
	 * Setzt den Zug des Spielers 2
	 */
	public int setZugSpieler2(int zug) {
		return setZug(zug,SPIELER2);
	}
	
	/*
	 * Setzt den übergebenen Zug im Spielfeld für 
	 * den Spieler dessen Nummer ebenfalls übergeben wurde
	 * Returns:
	 *   0 falls Zug erfolgreich gesetzt werden konnte
	 *   -1 falls der Zug außerhalb des Spielfeldes liegt
	 *   -2 falls der Zug bereits gesetzt wurde
	 */
	private int setZug(int zug, int spielernummer) {
			
		if(zug > Math.pow(getFeldgroesse(),2) - 1 || zug < 0) return -1;
			
		int zeile = (int) Math.floor(zug / getFeldgroesse());
		int spalte = zug % getFeldgroesse();
			
		if(getSpielfeld(zeile,spalte) != 0) return -2;
		else
		{
			spielfeld[zeile][spalte] = spielernummer;
			return 0;
		}
	}
	
	/*
	 *	Ermittelt die Nummer des Spielers der gewonnen hat
	 *  Returns:
	 *		0 derzeit hat noch niemand gewonnen
	 *		SPIELER1 oder SPIELER2 
	 */
	public int getGewonnen() {
		// erstellt die Summer der im Spielfeld gesetzten X/O
		try
		{
			//Überprüft Zeilenweise
			for(int zeile = 0; zeile < getFeldgroesse();zeile++)
			{
					for(int spalte = 1; spalte < getFeldgroesse() && getSpielfeld(zeile,spalte) == getSpielfeld(zeile,spalte - 1); spalte++)
					{
						if(spalte == getFeldgroesse() - 1) return getSpielfeld(zeile,spalte);	
					}
			}
			
			//Überprüft Spaltenweise
			for(int spalte = 0; spalte < getFeldgroesse();spalte++)
			{
					for(int zeile = 1; zeile < getFeldgroesse() && getSpielfeld(zeile,spalte) == getSpielfeld(zeile - 1,spalte) ; zeile++)
					{
						if(zeile == getFeldgroesse() - 1) return getSpielfeld(zeile,spalte);	
					}
			}
			
			//Diagonal Sinkend
			for(int zeile = 1,spalte = 1; zeile < getFeldgroesse() && getSpielfeld(zeile,spalte) == getSpielfeld(zeile - 1,spalte - 1); zeile++, spalte++)
			{
				if(zeile == getFeldgroesse()-1 || spalte == getFeldgroesse()-1) return getSpielfeld(zeile,spalte);
			}
			
			//Diagonal Steigend
			for(int zeile = getFeldgroesse()-1,spalte = 0  ; spalte < getFeldgroesse() && getSpielfeld(zeile,spalte) == getSpielfeld(zeile -1,spalte +1); zeile--, spalte++)
			{
				if(zeile == 1 || spalte == getFeldgroesse() -1) return getSpielfeld(zeile,spalte);
			}
			
		}catch(ArrayIndexOutOfBoundsException e)
		{
		}
		return 0;	
	}
	
	/*
	 *  Ermittelt ob einer der Spieler das Spiel noch gewinnen kann
	 *  Returns:
	 * 		true falls das Spiel noch gewonnen werden kann 
	 */
	public boolean getEinerKannGewinnen() {
		
		try 
		{
			//Überprüft Zeilenweise
			for(int zeile = 0; zeile < getFeldgroesse();zeile++)
			{
					int sum = 0;
					for(int spalte = 0; spalte < getFeldgroesse() ; spalte++)
					{
						if(getSpielfeld(zeile,spalte) == SPIELER1 || getSpielfeld(zeile,spalte) == 0) sum+=1;
						if(sum == getFeldgroesse() ) return true;	
					}
			}
			
			//Überprüft Spaltenweise
			for(int spalte = 0; spalte < getFeldgroesse();spalte++)
			{
					int sum = 0;
					for(int zeile = 0; zeile < getFeldgroesse(); zeile++)
					{
						if(getSpielfeld(zeile,spalte) == SPIELER1 || getSpielfeld(zeile,spalte) == 0) sum+=1;
						if(sum == getFeldgroesse() ) return true;
					}
			}
			
			//Diagonal Sinkend
			for(int zeile = 0,spalte = 0; zeile < getFeldgroesse(); zeile++, spalte++)
			{
				int sum = 0;
				if(getSpielfeld(zeile,spalte) == SPIELER1 || getSpielfeld(zeile,spalte) == 0) sum+=1;
				if(sum == getFeldgroesse()) return true;
			}
			
			//Diagonal Steigend
			for(int zeile = getFeldgroesse(),spalte = 0  ; spalte < getFeldgroesse() ; zeile--, spalte++)
			{
				int sum = 0;
				if(getSpielfeld(zeile,spalte) == SPIELER1 || getSpielfeld(zeile,spalte) == 0) sum+=1;
				if(sum == getFeldgroesse() ) return true;
			}
		}catch(ArrayIndexOutOfBoundsException e)
		{
		}
		/*
		//Spieler2 
		try 
		{
			//Überprüft Zeilenweise
			for(int zeile = 0; zeile < getFeldgroesse();zeile++)
			{
					int sum = 0;
					for(int spalte = 0; spalte < getFeldgroesse() ; spalte++)
					{
						if(getSpielfeld(zeile,spalte) == SPIELER2 || getSpielfeld(zeile,spalte) == 0) sum+=1;
						if(sum == getFeldgroesse() ) return true;	
					}
			}
			
			//Überprüft Spaltenweise
			for(int spalte = 0; spalte < getFeldgroesse();spalte++)
			{
					int sum = 0;
					for(int zeile = 0; zeile < getFeldgroesse(); zeile++)
					{
						if(getSpielfeld(zeile,spalte) == SPIELER2 || getSpielfeld(zeile,spalte) == 0) sum+=1;
						if(sum == getFeldgroesse() ) return true;
					}
			}
			
			//Diagonal Sinkend
			for(int zeile = 0,spalte = 0; zeile < getFeldgroesse(); zeile++, spalte++)
			{
				int sum = 0;
				if(getSpielfeld(zeile,spalte) == SPIELER2 || getSpielfeld(zeile,spalte) == 0) sum+=1;
				if(sum == getFeldgroesse()) return true;
			}
			
			//Diagonal Steigend
			for(int zeile = getFeldgroesse(),spalte = 0  ; spalte < getFeldgroesse() ; zeile--, spalte++)
			{
				int sum = 0;
				if(getSpielfeld(zeile,spalte) == SPIELER2 || getSpielfeld(zeile,spalte) == 0) sum+=1;
				if(sum == getFeldgroesse() ) return true;
			}
		}catch(ArrayIndexOutOfBoundsException e)
		{
		}
		*/
		return false;
	}
	 
}