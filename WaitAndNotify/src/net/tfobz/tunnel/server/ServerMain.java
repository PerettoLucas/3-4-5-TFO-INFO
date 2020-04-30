package net.tfobz.tunnel.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * In dieser Konsolenanwendung wird zuerst ein VisitorsMonitor angelegt, und dann 
 * wartet das Programm in einer Endlosschleife auf Clientanfragen. Erreicht ihm 
 * eine solche, so wird diese in einem Thread vom Typ ServerThread abgearbeitet.
 * Dadurch dass jede Anfrage in einem eigenen Thread abgearbeitet wird,
 * k�nnen mehrere Anfragen gleichzeitig bearbeitet werden
 */
public class ServerMain 
{
	/**
	 * Port an welchem der Server arbeitet
	 */
	protected static final int PORT = 65535;
	
	/**
	 * Besuchermonitor wird angelegt, und in einer Endlosschleife wird auf 
	 * Clientanfragen gewartet, welche alle �ber einzelne ServerThreads abgearbeitet
	 * werden. Dadurch dass jede Anfrage in einem eigenen Thread abgearbeitet wird,
	 * k�nnen mehrere Anfragen gleichzeitig bearbeitet werden
	 * @param args
	 * 
	 */
	public static void main(String[] args) 
	{
		System.out.println("S E R V E R");
		System.out.println("===========");
		
		VisitorsMonitor visitorsMonitor = new VisitorsMonitor();
		ServerSocket server = null;
		
		try
		{
			server = new ServerSocket(PORT);
			while (true) 
			{
				Socket client = server.accept();
				new ServerThread(client, visitorsMonitor).start();
			}
		} catch (IOException e){behandleException(e);}
		
	}
	
	/**
	 * Methode zur Exceptionbehandlung
	 * @param e
	 */
	public static void behandleException(Exception e) {
		e.printStackTrace();
	}
}
