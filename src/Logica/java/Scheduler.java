
package Logica.java;

import Logica.java.Estructuras.Cola;
import java.util.concurrent.Semaphore;

/**
 *
 * @author carlo_7ogoiii
 */
public class Scheduler {
    
    private Semaphore soS;        // Semáforo para llamar al SO
    private Cola blocked;
    private Cola ready;

    public Scheduler(Cola blocked, Cola ready) {
        this.blocked = blocked;
        this.ready = ready;
    }

    
    
    public void EncolarBloqueado(Process proceso){
        
        blocked.encolarProceso(proceso);
        
    }
    
    public void EncolarListo(Process proceso){
        
        ready.encolarProceso(proceso);
    }
    
    
}
