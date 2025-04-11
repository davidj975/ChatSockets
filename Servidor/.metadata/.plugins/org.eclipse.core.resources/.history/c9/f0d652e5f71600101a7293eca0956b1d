package Servidor;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int port = 1234;
        String palabraClave = "STOP";
        boolean continuar = true;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Inicio Servidor OK");
            System.out.println("Esperando conexión en puerto " + port + "...");

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

                System.out.println("Cliente OK");

                String mensaje;
                while (continuar && (mensaje = in.readLine()) != null) {
                    System.out.println("Cliente: " + mensaje);
                    continuar = !mensaje.equalsIgnoreCase(palabraClave);

                    if (continuar) {
                        System.out.print("Servidor: ");
                        String respuesta = userInput.readLine();
                        out.println(respuesta);
                        continuar = !respuesta.equalsIgnoreCase(palabraClave);
                    }

                    if (!continuar) {
                        System.out.println("Adios :c");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error servidor: " + e.getMessage());
        }
    }
}
