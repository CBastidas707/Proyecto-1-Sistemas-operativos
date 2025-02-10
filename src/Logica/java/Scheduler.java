
package Logica.java;

import java.util.concurrent.Semaphore;

/**
 *
 * @author carlo_7ogoiii
 */
public class Scheduler {
    private Semaphore interruptS; // Semáforo para recibir interrupciones
    private Semaphore soS;        // Semáforo para llamar al SO

    public Scheduler(Semaphore interruptS, Semaphore soS) {
        this.interruptS = interruptS;
        this.soS = soS;
    }
    
    
    
}
