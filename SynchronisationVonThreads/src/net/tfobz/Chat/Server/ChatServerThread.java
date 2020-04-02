package net.tfobz.Chat.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

public class ChatServerThread extends Thread
{
	private Socket client = null;
	private BufferedReader in = null;
	private PrintStream out = null;
	
	public ChatServerThread(Socket client) throws IOException {
		this.client = client;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintStream(client.getOutputStream());
	}
	
	@Override
	public void run() {
		try {
			synchronized (ChatServer.outputStreams) {
				ChatServer.outputStreams.add(out);
			}
			String name = in.readLine();
			System.out.println(name + " signed in. " + ChatServer.outputStreams.size() + " users");
			
			synchronized (ChatServer.outputStreams) {
				for (PrintStream outs: ChatServer.outputStreams)
					outs.println(name + " signed in");
			}
			
			while (true) {
				
				String line = null;
				synchronized (in) 
				{
					if (in != null) 
					{
						try {
							line = in.readLine();
						} catch(SocketException e) {e.printStackTrace();}
					}
				}
				
				if (line == null)
					break;
				synchronized (ChatServer.outputStreams) 
				{
					for (PrintStream outs: ChatServer.outputStreams)
						outs.println(name + ": " + line);
				}
			}
			
			synchronized (ChatServer.outputStreams) 
			{
				ChatServer.outputStreams.remove(out);
				System.out.println(name + " signed out. " + ChatServer.outputStreams.size() + " users");
				for (PrintStream outs: ChatServer.outputStreams)
					outs.println(name + " signed out");
			}
		} catch (IOException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			synchronized (ChatServer.outputStreams) 
			{
				if (out != null) ChatServer.outputStreams.remove(out);
			}
		} finally {
			try { client.close(); } catch (Exception e1) { ; }
		}
	}
}
