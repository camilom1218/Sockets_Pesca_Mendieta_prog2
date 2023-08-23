package Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente  extends Thread{

	private Socket socket;
	private BufferedReader lec;
	private BufferedWriter esc;
	private int port;
	private String address;
	private Scanner scanner;
	
	public Cliente(String address, int port) {
		this.socket = null;
		this.lec = null;
		this.esc = null;
		this.port = port;
		this.address = address;
	}
	
	public void run() {
		try {
			socket = new Socket(this.address, this.port);
			System.out.println("Conectado al servidor");
			
			this.lec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.esc = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String saludo = lec.readLine();
			System.out.println(saludo);
			System.out.println("Elige el numero de pregunta que quieres que respnda o escribe 0 para salir");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		

	}

}
