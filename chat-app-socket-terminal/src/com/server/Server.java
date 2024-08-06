package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.*;

public class Server {
	private static final int PORT = 8080;
	private static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();
	
	public static void main(String [] args) {
		try(var serverSocket = new ServerSocket(PORT)) {
			
			System.out.println("Server online.");
			
			while(true) {
				var clientSocket = serverSocket.accept();
				System.out.println("New client connected: " + clientSocket);
				
				var clientHandler = new ClientHandler(clientSocket);
				clients.add(clientHandler);
				
				for(var client : clients) {
					client.updateClients(clients);
				}
				
				new Thread(clientHandler).start();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
