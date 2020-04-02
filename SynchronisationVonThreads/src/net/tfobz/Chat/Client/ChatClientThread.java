package net.tfobz.Chat.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;

public class ChatClientThread extends Thread
{
	private BufferedReader in = null;
	
	public ChatClientThread(BufferedReader in) {
		if(in != null) this.in = in;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				String line = in.readLine();
				System.out.println(line);
			}
		} catch (SocketException e) {
			System.out.println("Connection to ChatServer lost, ignore exception");
		} catch (IOException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
