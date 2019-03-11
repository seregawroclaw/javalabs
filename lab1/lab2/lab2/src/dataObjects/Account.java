package dataObjects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Account extends Thread {
	ServerSocket server;
	Socket clientSocket;
	String amountstring;
	static int amount = 200;
	private static BufferedReader in;
	private static BufferedWriter out;
	public Account() {
		amountstring = getRandomBalance();
	}

	public void run() {
		 try {
	            try  {
	                server = new ServerSocket(3001); 
	                System.out.println("Сервер запущен!"); 
	                while(true) {
	                clientSocket = server.accept(); 
	                try { 
	                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
	                    String word = in.readLine();
	                    System.out.println(word);
	                    out.write("Приветствуем вас, : " + word + "!\t Ваш баланс равен : " + amountstring + "$\n");
	                    out.flush(); 
					} finally {
	                    clientSocket.close();
	                    in.close();
	                    out.close();
	                }
	                }
	            } finally {
	            }
	        } catch (IOException e) {
	            
	        }
	}
	public String getRandomBalance() {
		int amountcur = ((int) (Math.random() * 1000));
		if (Math.random() > 0.5)
			amount -= amountcur;
		else
			amount += amountcur;
		Integer x = new Integer(amount);
		return x.toString();
	}
	public void stopServer() throws IOException {
		System.out.println("Сервер закрыт");
		server.close();
	}

}