package com.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	private static final String SERVER_ADDRESS = "localhost";
	private static final int SERVER_PORT = 8080;
	
	public static void main(String [] args) {
		try(var socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
			System.out.println("Connected to the Server!");
			
			var out = new PrintWriter(socket.getOutputStream(), true);
			var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			new Thread(() -> {
				try {
					String serverResponse;
					while((serverResponse = in.readLine()) != null) {
						System.out.println(serverResponse);
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}).start();
			
			var scanner = new Scanner(System.in);
			String userInput = null;
			var counter = 0;
			while(counter < 10) {
				userInput = scanner.nextLine();
				out.println(userInput);
				counter++;
			}
			
			socket.close();
			scanner.close();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
