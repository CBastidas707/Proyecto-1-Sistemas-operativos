
package Logica.java;

import Logica.java.Estructuras.Cola;
import Logica.java.Estructuras.List;
import Logica.java.Estructuras.Nodo;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
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
    private AtomicInteger planificacion;
    private Cola finished;

    public Scheduler(List blocked, Cola ready, Semaphore soS, AtomicInteger planificacion, Cola finished) {
        this.blocked = blocked;
        this.ready = ready;
        this.soS = soS;
        this.planificacion = planificacion;
        this.finished = finished;
    }

    
    
    public void EncolarBloqueado(Process proceso){
        
        Nodo cpu = proceso.getCpu();
        SO so = new SO(ready, "Next", soS, proceso);
        cpu.setData(so);
        so.start();

        this.blocked.insertFirst(proceso); // Inserta en la cola de bloqueados (que en realidad es una lista)
       }
    
    public void EncolarListo(Process proceso){
        
        try {
            soS.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        ready.encolarProceso(proceso);
        soS.release();
        
    }
    
    public void Interrupt(Process proceso){
        
        
        switch (planificacion.get()) {
            case 1:     // FCFS
                
                if(proceso.getPcb().getStatus() != "Ready"){
                    
            try {
                soS.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
                    blocked.deleteByProcess(proceso);
                    soS.release();
                    Object procesoActualData = proceso.getCpu().getData();

                    if(procesoActualData instanceof Process){

                    Process procesoActual = (Process) procesoActualData;
                        try {
                            soS.acquire();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    procesoActual.getPcb().setStatus("Ready");
                    soS.release();}
                
                    SO so = new SO(ready, "Next", soS, proceso);
                    proceso.getCpu().setData(so);
                    so.start();
                    
                    proceso.getPcb().setStatus("Ready");
                }
                
                break;
                
            case 2:     // Round Robin
                
                if(proceso.getPcb().getStatus() == "Ready"){
                    SO so = new SO(ready, "Next", soS, proceso);
                    proceso.getCpu().setData(so);
                    so.start();
                }
                
                else{
                    
            try {
                soS.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
                    blocked.deleteByProcess(proceso);
                    soS.release();
                    Object procesoActualData = proceso.getCpu().getData();
                    
                    if(procesoActualData instanceof Process){
                    Process procesoActual = (Process) procesoActualData;
                        try {
                            soS.acquire();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    procesoActual.getPcb().setStatus("Ready");
                    soS.release();
                    }

                
                    SO so = new SO(ready, "Next", soS, proceso);
                    proceso.getCpu().setData(so);
                    so.start();
                
                    proceso.getPcb().setStatus("Ready");
                }
                
                
                break;
                
            case 3:     // SPN
                
                if(proceso.getPcb().getStatus() != "Ready"){
                        
            try {
                soS.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
                    blocked.deleteByProcess(proceso);
                    soS.release();
                    Object procesoActualData = proceso.getCpu().getData();

                    if(procesoActualData instanceof Process){

                    Process procesoActual = (Process) procesoActualData;
                        try {
                            soS.acquire();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    procesoActual.getPcb().setStatus("Ready");
                    soS.release();}
                
                    SO so = new SO(ready, "SPN", soS, proceso);
                    proceso.getCpu().setData(so);
                    so.start();
                    
                    proceso.getPcb().setStatus("Ready");
                }
                
                
            case 4:     // SRT
                
                
                
            case 5:     // HRRN
                
                
                
                
            default:    // No tiene una política
                    
                System.out.println("Error, no hay seleccionada una política de planificación válida");
}
        
    }
    
    public void Finish(Process proceso){
        finished.encolar(proceso);
        
        SO so = new SO(ready, "Next", soS, proceso);
        proceso.getCpu().setData(so);
        
        so.start();
        
    }
    
    
}
