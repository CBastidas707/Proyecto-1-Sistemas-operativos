package Logica.java;

import Logica.java.Estructuras.Cola;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo_7ogoiii
 */
public class Process extends Thread {
    private PCB pcb;
    private AtomicInteger sleepTime; // Variable atómica para el tiempo de sleep
    private Cola blocked;
    private Cola ready;
    private Semaphore queueS;

    public Process(PCB pcb, AtomicInteger sleepTime, Cola blocked, Cola ready, Semaphore queueS) {
        this.pcb = pcb;
        this.sleepTime = sleepTime;
        this.blocked =  blocked;
        this.ready = ready;
        this.queueS = queueS;
    }
    
    @Override
    public void run(){
        
        int ciclosG = 1;
        
        if(pcb.getExceptionG() > 0){
            ciclosG = pcb.getExceptionG(); // Servirá como contador en un proceso I/O bound para saber cuándo realizará una operación entrada salida
        }
        
        while(pcb.getMAR_Status() != pcb.getLength() && pcb.getStatus() != "Exit"){
         
            try {
                
                // Para que el proceso se encole en bloqueados se tiene que cambiar el estatus de su pcb a Blocked, y se ejecutará esto:
                
                if("Blocked".equals(pcb.getStatus())){
                    queueS.acquire();
                    System.out.println(pcb.getProcess_name() + " fue transladado a la cola de bloqueados");
                    blocked.encolarProceso(this);
                    queueS.release();
                while("Blocked".equals(pcb.getStatus())){
                    
                    //Completar el código para atender terminar una operación entrada salida
                    
                    sleep(10);
                }
                }
                
                
                // Para que el proceso se encole en listos se tiene que cambiar el estatus de su pcb a Ready, y se ejecutará esto:
                
                if("Ready".equals(pcb.getStatus())){
                    queueS.acquire();
                    System.out.println(pcb.getProcess_name() + " fue transladado a la cola de listos");
                    ready.encolarProceso(this);
                    queueS.release();
                    
                while("Ready".equals(pcb.getStatus())){
                    sleep(10);
                }
                    System.out.println(pcb.getProcess_name() + " fue despachado");
                }
                
                // Esto es lo que hará el proceso
                
                //Si el proceso es I/O bound
                    
                    if(pcb.getExceptionG() > 0){
                        
                        if(ciclosG == 0){
                            // Aquí debería llamar al scheduler
                        }
                        
                       System.out.println(
                        "--------------------------------\n"
                        + "Proceso: " + pcb.getProcess_name()
                        + "\n" + "MAR: " + pcb.getMAR_Status()
                        + "\n" + "PC: " + pcb.getPC_Status()
                        + "\n" + "Longitud: " + pcb.getLength()
                        + "\n" + "Tipo: I/O bound"
                        + "\n--------------------------------\n");
                       
                       ciclosG --;
                    }
                    
                //Si el proceso es CPU bound
                    
                    else{
                        System.out.println(
                        "--------------------------------\n"
                        + "Proceso: " + pcb.getProcess_name()
                        + "\n" + "MAR: " + pcb.getMAR_Status()
                        + "\n" + "PC: " + pcb.getPC_Status()
                        + "\n" + "Longitud: " + pcb.getLength()
                        + "\n" + "Tipo: CPU bound"
                        + "\n--------------------------------\n"); 
                    }
                
                
                pcb.setMAR_Status(pcb.getMAR_Status() + 1);
                pcb.setPC_Status(pcb.getPC_Status() + 1);
                sleep(sleepTime.get());
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Finalizo " + pcb.getProcess_name());
    }

    public PCB getPcb() {
        return pcb;
    }

    public void setPcb(PCB pcb) {
        this.pcb = pcb;
    }
    
    
    
    
}
