package Logic;

import Visual.PrincipalAdmin;
import Visual.PrincipalMedico;
import Visual.PrincipalPaciente;

public class UptadeGraficas extends Thread {

    private boolean running = true;
    private final int updateInterval = 20000; // 20 segs
    private static Doctor miDoctor = null;
    private static Paciente miPaciente = null;

    public UptadeGraficas(Doctor doctor, Paciente paciente) {
    	miDoctor = doctor;
    	miPaciente = paciente;
	}

	@Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(updateInterval);
                if(miDoctor == null) {
                
                PrincipalAdmin.actualizarGraficas();
                }
                if(miDoctor != null) {
                PrincipalMedico.actualizar(miDoctor);
                }
                
                if(miPaciente != null) {
                	PrincipalPaciente.actualizarGraficas(miPaciente);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopUpdating() {
        running = false;
    }
}
