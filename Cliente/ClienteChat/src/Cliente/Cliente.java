package Cliente;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;
        String palabraClave = "STOP";
        boolean continuar = true;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conexion en " + host + ":" + port);

            System.out.print("Cliente: ");
            String mensaje = userInput.readLine();
            out.println(mensaje);
            continuar = !mensaje.equalsIgnoreCase(palabraClave);

            while (continuar) {
                String respuesta = in.readLine();
                continuar = respuesta != null && !respuesta.equalsIgnoreCase(palabraClave);

                if (respuesta != null) {
                    System.out.println("Servidor: " + respuesta);
                }

                if (continuar) {
                    System.out.print("Cliente: ");
                    mensaje = userInput.readLine();
                    out.println(mensaje);
                    continuar = !mensaje.equalsIgnoreCase(palabraClave);
                }

                if (!continuar) {
                    System.out.println("Adios :c");
                }
            }
        } catch (IOException e) {
            System.out.println("Error cliente: " + e.getMessage());
        }
    }
}
