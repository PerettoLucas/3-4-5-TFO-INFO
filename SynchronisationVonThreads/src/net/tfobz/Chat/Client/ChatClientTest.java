package net.tfobz.Chat.Client;

import java.awt.List;
import java.util.ArrayList;

import net.tfobz.Chat.Server.ChatServer;

public class ChatClientTest 
{
	public static final int PORT = 65532;
	private static ArrayList<ChatClient> clients = new ArrayList<ChatClient>();
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) 
	{
		ChatServer.main(null);
		
		for (int i = 0; i < 30; i++) {
			ChatClient c = new ChatClient();
			c.main(new String[]{"ClientNR."+i,"localhost"});

			clients.add(c);
			
//			for (ChatClient c : clients) {
//				for (int j = 0; j < 50; j++) {
//					System.out.println("hallo" + j);
//					out.println("hallo" + j);
//					sleep((long) (Math.random() * 18 + 2));
//				}
//			}
		}
		
		
	}
}
