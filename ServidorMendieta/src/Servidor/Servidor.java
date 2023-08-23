package Servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {

	private Socket socket;
	private ServerSocket server;
	private int port;

	public Servidor(int port) {
		this.server = server;
		this.port = port;
	}

	public void run() {
		try {
			this.server = new ServerSocket(this.port);
			
			System.out.println("Servidor iniciado. esperando conexion..");
			this.socket = server.accept();
			
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){ 
					
					writer.write("Hola cliente");
					writer.newLine();
					writer.flush();
					
					
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

	
	
	