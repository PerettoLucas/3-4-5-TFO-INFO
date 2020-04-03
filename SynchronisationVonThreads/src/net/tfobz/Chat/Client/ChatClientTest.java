package net.tfobz.Chat.Client;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.xml.stream.events.StartDocument;

import net.tfobz.Chat.Server.ChatServer;

public class ChatClientTest 
{
	public static final int PORT = 65532;
	private static ArrayList<ChatClientThread> clientThreads = new ArrayList<ChatClientThread>();
	private static ArrayList<ChatClient> clients = new ArrayList<ChatClient>();
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) 
	{
		new Thread(()->{ChatServer.main(null);}).start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {e.printStackTrace();}
		
		Socket client = null;
		BufferedReader in = null;
		PrintStream out = null;
		
		for (int i = 0; i < 5; i++) {

			final int icopy = i;
			new Thread(()->{
				ChatClient c =new ChatClient();
				c.main(new String[] {"ClientNR."+icopy,"localhost"});
				clients.add(c);
				new ChatClientThread(c.in).start();
			}).start();
			
			
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {e.printStackTrace();}
			
		}
		
		
	}
}
