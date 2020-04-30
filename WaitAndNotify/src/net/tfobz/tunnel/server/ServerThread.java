package net.tfobz.tunnel.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Der Thread liest vom Socket die Anzahl, und dabei werden die drei F�lle 
 * � gr��er 0, kleiner 0 oder gleich 0 � unterschieden und entsprechen am 
 * VisitorsMonitor die Anfragen gestellt. Das  Ergebnis wird an den Client 
 * zur�ck geschickt. der ServerThread erh�lt den Socket des Clients und eine 
 * Referenz auf VisitorsMonitor
 */
public class ServerThread extends Thread
{
	/**
	 * Der Clientsocket von welchem die Besucheranzahl gelesen werden kann
	 */
	protected Socket client = null;
	/**
	 * VisitorsMonitor an dem die Anfrage nach Besuchern bzw. die R�ckgabe
	 * der Besucher nach Beendigung einer Besichtigung gestellt werden kann
	 */
	protected VisitorsMonitor visitorsMonitor = null;
	
	/**
	 * Konstruktor erh�lt den Clientsocket und den VisitorsMonitor als
	 * Referenz. Als Threadname wird die IP-Adresse des Clients gesetzt.
	 * Die IP-Adresse kann �ber den Clientsocket durch die Methode
	 * getInetAdress() erfragt werden
	 * @param client
	 * @param visitorsMonitor
	 */
	public ServerThread(Socket client, VisitorsMonitor visitorsMonitor) 
	{
		this.visitorsMonitor = visitorsMonitor;
		this.client = client;
		this.setName(""+client.getInetAddress());
	}
	
	/**
	 * Diese Methode liest zuerst vom Clientsocket die Anzahl. Je nach dem
	 * welche Werte in anzahl stehen, werden folgende Aufgaben erledigt:<br><br>
	 * <b>anzahl == 0</b><br>
	 * Es wird die Anzahl der am VisitorsMonitor momentan verf�gbaren Benutzer
	 * abgefragt und an den Client zur�ck geschickt<br><br>
	 * <b>anzahl > 0</b><br>
	 * Es werden am VisitorsMonitor die Benutzer angefordert<br><br>
	 * <b>anzahl < 0</b><br>
	 * Es werden dem VisitorsMonitor die Anzahl an Benutzer zur�ck gegeben
	 */
	public void run() 
	{
		try {
			BufferedReader in = new BufferedReader( new InputStreamReader(client.getInputStream()));
			PrintStream out  = new PrintStream(client.getOutputStream());
			
			int count = 0;
			
			{
				try {
					count = Integer.parseInt(""+in.read());
				} catch (java.net.SocketException e) {}
			}
			System.out.println(count);
			
			if(count == 0) {
				out.print(visitorsMonitor.getAvailableVisitors());
			}else if(count > 0) 
			{
				//Blockierend bis request mit den Visitors antwortet
//				visitorsMonitor.request(count);
				
//				out.print(count);
			}else if(count < 0) 
			{
				
			}
			
			
		} catch (Exception e) {e.printStackTrace();}
		
		
	}
}
