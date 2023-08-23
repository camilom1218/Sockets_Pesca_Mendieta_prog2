package Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
		
	}
	public static void main(String[] args) {
		

	}

}
