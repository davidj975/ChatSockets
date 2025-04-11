package Servidor;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int port = 1234;                        // Puerto de conexión
        String palabraClave = "STOP";           // Comando para cerrar conexión
        boolean continuar = true;               // Control del bucle

        try (ServerSocket serverSocket = new ServerSocket(port)) {  // Socket servidor
            System.out.println("Servidor iniciado en puerto " + port);

            // Aceptar conexión de cliente
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // Recibir mensajes
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);  // Enviar mensajes
                 BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {  // Entrada por consola

                System.out.println("Cliente conectado");

                // Bucle principal de chat
                String mensaje;
                while (continuar && (mensaje = in.readLine()) != null) {
                    System.out.println("Cliente: " + mensaje);  // Mostrar mensaje cliente
                    continuar = !mensaje.equalsIgnoreCase(palabraClave);  // Verificar comando STOP

                    if (continuar) {
                        System.out.print("Servidor: ");
                        String respuesta = userInput.readLine();  // Leer respuesta
                        out.println(respuesta);  // Enviar respuesta
                        continuar = !respuesta.equalsIgnoreCase(palabraClave);  // Verificar STOP
                    }

                    if (!continuar) {
                        System.out.println("Cerrando conexión...");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}