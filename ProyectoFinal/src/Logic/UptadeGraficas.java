package Logic;

import Visual.PrincipalAdmin;

public class UptadeGraficas extends Thread {

    private boolean running = true;
    private final int updateInterval = 20000; // 20 segs

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(updateInterval);
                PrincipalAdmin.actualizarGraficas();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopUpdating() {
        running = false;
    }
}
