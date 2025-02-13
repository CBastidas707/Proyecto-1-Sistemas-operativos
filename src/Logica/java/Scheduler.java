
package Logica.java;

import Logica.java.Estructuras.Cola;
import Logica.java.Estructuras.List;
import Logica.java.Estructuras.Nodo;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo_7ogoiii
 */
public class Scheduler {
    
    private Semaphore soS;        // Semáforo para llamar al SO
    private List blocked;
    private Cola ready;

    public Scheduler(List blocked, Cola ready, Semaphore soS) {
        this.blocked = blocked;
        this.ready = ready;
        this.soS = soS;
    }

    
    
    public void EncolarBloqueado(Process proceso){
        
        Nodo cpu = proceso.getCpu();
        SO so = new SO(ready, "Next");
        cpu.setData(so);
        try {
            soS.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        blocked.insertFirst(proceso);

        Process nextProceso = ready.desencolarProceso();
        if (nextProceso != null) {
            cpu.setData(nextProceso);
            nextProceso.setCpu(cpu);
            nextProceso.getPcb().setStatus("Running");
        }
        else{
            System.out.println("No hay mas procesos en la cola de listos");
        }

        soS.release();
        
    }
    
    public void EncolarListo(Process proceso){
        
        ready.encolarProceso(proceso);
    }
    
    
}
