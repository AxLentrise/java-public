package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
	private static final int PORT = 8080;
	public static final CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();
	
	public static void main(String[] args) {
		try(var server = new ServerSocket(PORT)) {
			System.out.println("Server online.");
			
			while(true) {
				var client = server.accept();
				System.out.println(client + " connected.");
				
				var clientHandler = new ClientHandler(client);
				clients.add(clientHandler);
				
				new Thread(clientHandler).start();
			}
		} catch(IOException e) {
			
		}
	}
}
