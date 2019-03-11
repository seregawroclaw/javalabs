package dataObjects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class clientThread extends Thread {
	Socket clientSocket = null;
    private static BufferedReader reader; 
    private static BufferedReader in; 
    private static BufferedWriter out; 
    private String actualBalance;
	@Override
	public void run() {
		while(true) {
		try {
            try {
            	sleep(2000);
                clientSocket = new Socket("localhost", 3001); 
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                out.write(this.getName()+"\n"); 
                out.flush();
                String serverWord = in.readLine(); 
                System.out.println(serverWord); 
                serverWord = serverWord.replaceAll(this.getName(),"");
                serverWord = serverWord.replaceAll("[^-&&\\W]", "");
                setActualBalance(serverWord);
            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (Exception e) {
            
           break;
        }
		}
	}
	public String getActualBalance() {
		return actualBalance;
	}
	public void setActualBalance(String actualBalance) {
		this.actualBalance = actualBalance;
	}

}