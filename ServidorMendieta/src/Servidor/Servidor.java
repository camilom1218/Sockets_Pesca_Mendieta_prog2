package Servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
	private Socket socket;
	private ServerSocket server;
	private int port;

	public Servidor(int port) {
		this.server = null;
		this.port = port;

	}

	@Override
	public void run() {
		try {
			this.server = new ServerSocket(this.port);

			System.out.println("Servidor iniciado. Esperando conexiones...");
			this.socket = server.accept();

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
// writer.write("Cliente aceptado");
				writer.write("Hola, cliente!");
				writer.newLine();
				writer.flush();

				String pregunta;
				try (BufferedReader LPreguntas = new BufferedReader(new FileReader("Preguntas.txt"))) {
					while ((pregunta = LPreguntas.readLine()) != null) {
						writer.write(pregunta);
						writer.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				writer.write("");
				writer.newLine();
				writer.flush();

				while (true) {
					String mensajeCliente = reader.readLine();
					int numeroPregunta = Integer.parseInt(mensajeCliente);
					if (numeroPregunta > 20) {
						writer.write("Número no válido. Por favor ingresa un número entre 1 y 20.");
					} else if (numeroPregunta == 0) {
						break;
					} else {

						String respuesta = obtenerRespuesta(numeroPregunta);
						if (respuesta != null) {
							writer.write(respuesta);
						} else {
							writer.write("Pregunta no válida.");
						}
						writer.newLine();
						writer.flush();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String obtenerRespuesta(int numeroPregunta) {
		try (BufferedReader br = new BufferedReader(new FileReader("Respuestas.txt"))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(":");
				if (partes.length >= 2) {
					int numeroRespuesta = Integer.parseInt(partes[0].trim());
					if (numeroRespuesta == numeroPregunta) {
						return partes[1].trim();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Servidor servidorr = new Servidor(5000);
		servidorr.start();
	}
}
