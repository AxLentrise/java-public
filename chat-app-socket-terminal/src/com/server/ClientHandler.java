package com.server;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ClientHandler implements Runnable {
	private CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();
	
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
		
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateClients(CopyOnWriteArrayList<ClientHandler> clients) {
		this.clients = clients;
	}
	
	public void broadcast(String message, ClientHandler sender) {
		for(ClientHandler client : clients) {
			if(client != sender) client.sendMessage(message);
		}
	}
	
	@Override
	public void run() {
		try {
			String username = getUsername();
			System.out.println("User " + username + " connected.");
			out.println("Welcome to the chat, " + username + ".");
			
			String inputLine;
			while((inputLine = in.readLine()) != null) {
				System.out.println("[" + username + "]: " + inputLine);
				
				broadcast("[" + username + "]: " + inputLine, this);
			}
			
			clients.remove(this);
			
			in.close();
			out.close();
			clientSocket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getUsername() throws IOException {
		return in.readLine();
	}
	
	private void sendMessage(String message) {
		out.println("\u001B[38;5;47m"+message+"\u001B[0m");
	}
}
