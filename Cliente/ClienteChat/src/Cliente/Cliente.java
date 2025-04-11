package Cliente;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        // Configuración básica
        String host = "localhost";       // Dirección del servidor
        int port = 1234;                // Mismo puerto que el servidor
        String palabraClave = "STOP";    // Palabra para terminar conexión
        boolean continuar = true;        // Control del bucle principal

        try (
            // Establece conexión con el servidor
            Socket socket = new Socket(host, port);
            
            // Flujo para recibir mensajes del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Flujo para enviar mensajes al servidor (auto-flush activado)
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            // Flujo para leer entrada del usuario por consola
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Conectado al servidor en " + host + ":" + port);

            // Bucle principal de comunicación
            while (continuar) {
                // Primero el cliente envía un mensaje
                System.out.print("Cliente: ");
                String mensaje = userInput.readLine();
                out.println(mensaje);
                continuar = !mensaje.equalsIgnoreCase(palabraClave);

                // Luego espera respuesta del servidor
                if (continuar) {
                    String respuesta = in.readLine();
                    if (respuesta == null || respuesta.equalsIgnoreCase(palabraClave)) {
                        continuar = false;
                    } else {
                        System.out.println("Servidor: " + respuesta);
                    }
                }

                if (!continuar) {
                    System.out.println("Desconectando...");
                }
            }
        } catch (IOException e) {
            System.out.println("Error en cliente: " + e.getMessage());
        }
    }
}