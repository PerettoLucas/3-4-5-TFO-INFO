package net.tfobz.tictactoe;

import java.io.ObjectInputStream.GetField;

public class TicTacToeSingle
{

	public static void main(String[] args)
	{
		
		int nochmal;
		do 
		{
			Spielfeld s = new Spielfeld(3);
			System.out.println(s.toString());
			do
			{
				//SpielerX
				int zug1 = TestScannerErweitert.readInt("Eingabe SpielerX: ");
				s.setZugSpieler1(zug1);
				
				System.out.println(s.toString());
				if(s.getEinerKannGewinnen() == false) break;
				if(s.getGewonnen() == s.SPIELER1) break;
				
				//SpielerO
				int zug2 = TestScannerErweitert.readInt("Eingabe SpielerO: ");
				s.setZugSpieler2(zug2);
				
				System.out.println(s.toString());
				if(s.getEinerKannGewinnen() == false) break;
				if(s.getGewonnen() == s.SPIELER2) break;
				
			}while(s.getGewonnen() == 0);
			if(s.getGewonnen() == s.SPIELER1) System.out.println("SpielerX hat gewonnen!");
			if(s.getGewonnen() == s.SPIELER2) System.out.println("SpielerO hat gewonnen!");
			if(s.getEinerKannGewinnen() == false) System.out.println("Keiner kann mehr gewinnen!");
			nochmal = TestScannerErweitert.readInt("Nochmal spielen?(ja=1/nein=0)");
			
		}while(nochmal != 0);	
	}
}
