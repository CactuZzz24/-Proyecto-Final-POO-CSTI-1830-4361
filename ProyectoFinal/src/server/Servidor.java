package server;

import java.io.*;
import java.net.*;

import Logic.Clinica;

public class Servidor extends Thread {
    private static boolean isRunning = true; 
    private static ServerSocket sfd;


    
    public static void main(String args[]) {
         sfd = null;
        try {
            sfd = new ServerSocket(7000);
        } catch (IOException ioe) {
            System.out.println("Comunicación rechazada." + ioe);
            System.exit(1);
        }

        while (isRunning) {
            try {
                Socket nsfd = sfd.accept();
                System.out.println("Conexion aceptada de: " + nsfd.getInetAddress());

                DataInputStream input = new DataInputStream(nsfd.getInputStream());
                String message = input.readUTF(); 
                if (message.equals("EXIT")) {
                    isRunning = false;
                    sfd.close();
                } else {
                  
                }

                input.close();
                nsfd.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
    

    private static void generarRespaldo(Clinica clinica) {
        try {
            FileOutputStream clinicaRespaldoFile = new FileOutputStream("clinica_respaldo.dat");
            ObjectOutputStream clinicaRespaldoStream = new ObjectOutputStream(clinicaRespaldoFile);

            clinicaRespaldoStream.writeObject(clinica);
            clinicaRespaldoStream.close();
            clinicaRespaldoFile.close();
        } catch (IOException ioe) {
            System.out.println("Error al generar el respaldo: " + ioe);
        }
    }
    public static void stopServer() {
        isRunning = false;
        try {
            if (sfd != null) {
                sfd.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error al cerrar el socket del servidor: " + ioe);
        }
    }
}


