package net.tfobz.TTTServerClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ClientInfoStatus;
import java.util.Scanner;

import net.tfobz.tictactoe.Spielfeld;


public class TicTacToeServer extends Spielfeld
{
	
	private Socket client;
	private static int PORT = 1024;
	private ServerSocket server;
	private static int FELDGROESSE = 3;
	
	
	public TicTacToeServer(int feldgroesse,  int port) throws IOException
	{
		super(feldgroesse);
		try
		{
			server = new ServerSocket(port);
		}catch(IOException e){ e.printStackTrace(); }
		
	}
	
	public static void main(String[] args)
	{
		try
		{
			TicTacToeServer ttts = new TicTacToeServer(FELDGROESSE,PORT);
			TicTacToeJFrame tttj = new TicTacToeJFrame("Tic-Tac-Toe Server",ttts);
			
			
			while(ttts.getGewonnen() == 0 && ttts.getEinerKannGewinnen())
			{
				tttj.repaint();
				tttj.setStatusleistentext("Warten auf Zug des Gegners...");
				ttts.getGegnerZug();
				tttj.repaint();
				
				
				if(ttts.getGewonnen() != 0) break;
				
				tttj.setStatusleistentext("Ihr Zug ...");
				
				int zugS2 = -1; //readInt("Spieler2 ZUG : ");
				zugS2 = tttj.getGewaehlteFeldnummer();
				
				while(ttts.setMeinZug(zugS2) != 0)
				{
					tttj.setStatusleistentext("Zug schon gesetzt oder ausserhalb des Feldes");
					zugS2 = tttj.getGewaehlteFeldnummer();
				}
				
				ttts.setMeinZug(zugS2);
				tttj.repaint();
				
				
			}
				
			if(ttts.getGewonnen() == -1) tttj.setStatusleistentext("Spieler 1 hat gewonnen");
			if(ttts.getGewonnen() == -2) tttj.setStatusleistentext("Spieler 2 hat gewonnen");
			if(ttts.getGewonnen() == 0) tttj.setStatusleistentext("ausgleich");
			
		}catch(IOException e) { e.printStackTrace(); }
	}
	
	public void close() throws IOException
	{
		if(client != null) {
			
			client.close();
			client = null;
		}
		
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
	
	public int getGegnerZug() throws IOException
	{
		if(client == null)
		{
			try
			{
				System.out.println("Spieler2: Warten auf den Zug des Gegners...");
				int zug1;
				client = server.accept();
				InputStream in = client.getInputStream();
				zug1 = in.read();
				int retzug1 = setZugSpieler1(zug1);
				if(retzug1 != 0) return retzug1;
			}catch(IOException e){ e.printStackTrace(); }			
		}
		else return -3;
		
		return 0;
	}
	public int setMeinZug(int zug) throws IOException
	{
		int retzug2 = setZugSpieler2(zug);
		if(client == null) return -3;
		if(retzug2 == -2) return -2;
		if(retzug2 == -1) return -1;
		if(retzug2 == 0)
		{
			try
			{
				System.out.println("Geat eini");
				OutputStream out = client.getOutputStream();
				out.write(zug);
				System.out.println("ot gschriebn");
			}catch(IOException e) { e.printStackTrace(); }
			finally { close(); }
		}

		
		return 0;
	}
}
