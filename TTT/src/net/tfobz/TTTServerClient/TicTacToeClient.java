package net.tfobz.TTTServerClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import net.tfobz.tictactoe.Spielfeld;

/**
 * 	Stellt den TicTacToe-Client dar. Dieser schickt seinen Zug an den Server und 
 	wartet bis dieser seinerseits seinen Zug an den Client zurück schickt.
	Die Methode main erhält als erstes Argument (args[0]) die IP-Adresse 
	des TicTacToeServers übergeben.
	Mit der Methode setMeinZug wird der Zug festgelet, ins Spielfeld eingetragen und 
	dann über einen neuen ClientSocket an den Server geschickt. 
	Nach Beendigung der Methode wird die ClientSocket nicht zerstört, 
	sondern die Methode getGegnerZug verwendet diesen Socket,
 	um den Zug des Gegners zuint in; empfangen. Nachdem der Zug des Gegners empfangen wurde,
 	wird dieser ins Spielfeld eingetragen und der ClientSocket geschlossen 
 * @author lausor
 *
 */
public class TicTacToeClient extends Spielfeld
{
	private Socket client = null;
	private static String ipAdresse = "10.216.220.153";
	private static int PORT = 1024;
	private static int FELDGROESSE = 3;
	

	public TicTacToeClient(int feldgroesse)
	{
		super(feldgroesse);
	}
	
	public static void main(String[] args)
	{
		TicTacToeClient tttc = new TicTacToeClient(FELDGROESSE);
		TicTacToeJFrame tttj = new TicTacToeJFrame("Tic-Tac-Toe Client",tttc);
		
		
		while(tttc.getGewonnen() == 0 && tttc.getEinerKannGewinnen())
		{
			tttj.repaint();
			int zugS1 = -1; //= readInt("Spieler1 ZUG : ");
			
			tttj.setStatusleistentext("Ihr Zug...");
			
		
			try
			{
				zugS1 = tttj.getGewaehlteFeldnummer();
				while(tttc.setMeinZug(zugS1) != 0)
				{
					tttj.setStatusleistentext("Zug schon gesetzt oder ausserhalb des Feldes");
					zugS1 = tttj.getGewaehlteFeldnummer();
				}
				
				tttc.setMeinZug(zugS1);
				tttj.repaint();
			}catch(UnknownHostException e1) { e1.printStackTrace(); }
			catch(IOException e1) 
			{ 
				e1.printStackTrace();
				tttj.setStatusleistentext("Es muss eine gueltige Zahl eingegeben werden!");
			}
			
		
			if(tttc.getGewonnen() != 0) break;
			
			try
			{
				tttj.setStatusleistentext("Warten auf Zug des Gegners...");
				tttc.getGegnerZug();
				tttj.repaint();
			}catch(IOException e) { e.printStackTrace(); }
		}
		
		
		
		if(tttc.getGewonnen() == -1) tttj.setStatusleistentext("Spieler 1 hat gewonnen");
		if(tttc.getGewonnen() == -2) tttj.setStatusleistentext("Spieler 2 hat gewonnen");
		if(tttc.getGewonnen() == 0) tttj.setStatusleistentext("ausgleich");
		
	}
	
	/**
	 * readInt reads an Integer and Returns it 
	 * @param txt text displayed to the user
	 * @return the input from the user
	 */
	private static int readInt(String txt) 
	{
		int ret=0;
		
		System.out.println(txt);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		ret = scanner.nextInt();
		return ret;
	}
		
	public void close() throws IOException
	{		
		if(client != null) {
			
			client.close();
			client = null;
		}
	}
	
	
	public int setMeinZug(int zug)throws IOException, UnknownHostException
	{
		int retzug1 =  setZugSpieler1(zug);
		if(retzug1 == -2) return -2;
		if(retzug1 == -1) return -1;
		if(retzug1 == 0)
		{
			if(client == null)
			{
				try
				{
					client = new Socket(ipAdresse,PORT);
					OutputStream out = client.getOutputStream();
					out.write(zug);
					
				}catch(IOException e){ e.printStackTrace(); }
				
			}
			else return -3;
		}
		
		return 0;
	
	}
	
	public int getGegnerZug() throws IOException
	{
		if(client != null)
		{
			try
			{
				System.out.println("Spieler1: Warten auf den Zug des Gegners...");
				int zug2;
				InputStream in = client.getInputStream();
				zug2 = in.read();
				int retzug2 = setZugSpieler2(zug2);
				if(retzug2 != 0) return retzug2;
			}catch(IOException e){ e.printStackTrace(); }
			finally { close(); }
		}
		
		return 0;
	}

}
