package Cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends Thread {
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private String address;
	private int port;
	private Scanner scanner;

	public Cliente(String address, int port) {
		this.socket = null;
		this.reader = null;
		this.writer = null;
		this.address = address;
		this.port = port;
		this.scanner = new Scanner(System.in);
	}

	public void run() {
		try {
			socket = new Socket(this.address, this.port);
			System.out.println("Conectado al servidor");

			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			String saludo = reader.readLine();
			System.out.println(saludo);
			System.out.println("Elige el número de la pregunta que quieres hacer (0 para salir): ");

			String pregunta;
			while (!(pregunta = reader.readLine()).equals("")) {
				System.out.println(pregunta);
			}

			while (true) {

				int numeroPregunta = scanner.nextInt();
				scanner.nextLine();
				writer.write(Integer.toString(numeroPregunta));
				writer.newLine();
				writer.flush();

				if (numeroPregunta == 0) {
					System.out.println("El servidor fue cerrado");
					break;
				}

				String respuesta = reader.readLine();
				System.out.println("Respuesta del servidor: " + respuesta);
			}

			reader.close();
			writer.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Cliente client = new Cliente("127.0.0.1", 5000);
		client.run();
	}
}
