package net.tfobz.Chat.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.Thread.State;
import java.net.Socket;
import java.util.ArrayList;

import net.tfobz.Chat.Server.ChatServer;

public class ChatClientTest 
{
	public static final int PORT = 65535;
	private static ArrayList<Thread> threads = new ArrayList<Thread>();
	private int counter = 0;
	
	public static void decrement() {
		
	}
	
	public static void main(String[] args) 
	{
		new Thread(()->{ChatServer.main(null);}).start();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		for(int i=0;i<3;i++)
		{
			final int icopy = i;
			Thread t = new Thread(()->{
				Socket client = null;
				PrintStream out = null;
				BufferedReader in = null;
				try
				{
					client = new Socket("localhost", PORT);
					in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					out = new PrintStream(client.getOutputStream());
				}catch(IOException e1){}
				
				out.println("ClientNR."+icopy);
				
				new ChatClientThread(in).start();
				
				for (int j = 0; j < 50; j++) 
				{
					out.println(" sagt zum :"+j+"ten mal hallo" );
					try
					{
						Thread.sleep((long) (Math.random() * 18 + 2));
					}catch(InterruptedException e){}
				}
				
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				
				try{client.close();}catch(IOException e){}
				
			});
			threads.add(t);
			t.start();
		}
	}
}
