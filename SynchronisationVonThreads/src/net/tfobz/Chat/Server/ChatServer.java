package net.tfobz.Chat.Server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer
{
	public static final int PORT = 65535;
	
	protected static ArrayList<PrintStream> outputStreams =
		new ArrayList<PrintStream>();
	
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(PORT);
			System.out.println("Chat server started");
			while (true) {
				synchronized (server) {
					Socket client = server.accept();
					try {
						new ChatServerThread(client).start();
					} catch (IOException e) {
						System.out.println(e.getClass().getName() + ": " + e.getMessage());
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try { server.close(); } catch (Exception e1) { ; }
		}
	}
}
