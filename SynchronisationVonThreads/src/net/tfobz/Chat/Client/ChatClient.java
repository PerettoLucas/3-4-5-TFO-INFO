package net.tfobz.Chat.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author Michael
 * @see http://www.vorlesungen.uos.de/informatik/b06/
 */
public class ChatClient
{
	public static final int PORT = 65535;
	public static BufferedReader in = null;
	public static PrintStream out = null;
	
	public static void main(String[] args) {
		Socket client = null;
		try {
			client = new Socket(args[1], PORT);
			in = new BufferedReader( new InputStreamReader(client.getInputStream()));
			out = new PrintStream(client.getOutputStream());
			BufferedReader consoleIn =
				new BufferedReader(new InputStreamReader(System.in));
			// sending the name of the client to the server
			synchronized (out) {
				out.println(args[0]);
			}
			
			new ChatClientThread(in).start();
			
			while (true) {
				String line = consoleIn.readLine();
				if (line == null)
					// pressed [Ctrl]+Z to sign out
					break;
				out.println(line);
			}
		} catch (IOException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try { client.close(); } catch (Exception e1) { ; }
		}
	}
}
