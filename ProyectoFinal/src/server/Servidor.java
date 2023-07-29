package server;

import java.io.*;
import java.net.*;

import Logic.Clinica;

public class Servidor extends Thread {
    
    public static void main(String args[]) {
        ServerSocket sfd = null;
        try {
            sfd = new ServerSocket(7000);
        } catch (IOException ioe) {
            System.out.println("Comunicación rechazada." + ioe);
            System.exit(1);
        }

        while (true) {
            try {
                Socket nsfd = sfd.accept();
                System.out.println("Conexion aceptada de: " + nsfd.getInetAddress());

                ObjectInputStream ois = new ObjectInputStream(nsfd.getInputStream());

                // Leer la instancia de la clínica enviada por el cliente
                Clinica clinica = (Clinica) ois.readObject();
                ois.close();
                nsfd.close();

                // Generar el respaldo con la instancia de la clínica recibida
                generarRespaldo(clinica);

                System.out.println("Respaldo generado correctamente.");
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    private static void generarRespaldo(Clinica clinica) {
        try {
            FileOutputStream clinicaRespaldoFile = new FileOutputStream("clinica_respaldo.dat");
            ObjectOutputStream clinicaRespaldoStream = new ObjectOutputStream(clinicaRespaldoFile);

            // Guardar la instancia de la clínica en el archivo clinica_respaldo.dat
            clinicaRespaldoStream.writeObject(clinica);
            clinicaRespaldoStream.close();
            clinicaRespaldoFile.close();
        } catch (IOException ioe) {
            System.out.println("Error al generar el respaldo: " + ioe);
        }
    }
}
