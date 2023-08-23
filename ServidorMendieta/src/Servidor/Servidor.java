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
					
					String pregunta;
					try(BufferedReader LPreguntas = new BufferedReader(new FileReader("preguntas.txt"))){
						while((pregunta = LPreguntas.readLine())!= null) {
							writer.write(pregunta);
							writer.newLine();
						}
					}catch(IOException e) {
						e.printStackTrace();
					}
					writer.write("");
					writer.newLine();
					writer.flush();
					
					while(true) {
						String mensajeCliente = reader.readLine();
						int numeroPregunta = Integer.parseInt(mensajeCliente);
						if(numeroPregunta> 20) {
							writer.write("Numero no valido. Por favor ingresa un numero entre 1 y 20");
						}else if(numeroPregunta == 0) {
							break;
						}else {
							String respuesta = obtenerRespuesta(numeroPregunta);
							if (respuesta != null) {
								writer.write(respuesta);
							}else {
								writer.write("Pregunta no valida.");
							}
							writer.newLine();
							writer.flush();	
							
						}
					}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}catch(IOException e) {
		e.printStackTrace();
	}
}
	
	
	
	
	
}

	
	
	