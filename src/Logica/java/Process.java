package Logica.java;

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

    public Process(PCB pcb, AtomicInteger sleepTime) {
        this.pcb = pcb;
        this.sleepTime = sleepTime;
    }
    
    @Override
    public void run(){
        while(pcb.getMAR_Status() != pcb.getLength() && pcb.getStatus() != "Exit"){
         
            try {
                if(pcb.getStatus() == "Ready"){
                    
                    System.out.println(pcb.getProcess_name() + "fue transladado a la cola de listos");
                    
                while(pcb.getStatus() == "Ready"){
                    wait();
                }
                    System.out.println(pcb.getProcess_name() + "fue despachado");
                }
                
                
                if(pcb.getStatus() == "Blocked"){
                    
                while(pcb.getStatus() == "Blocked"){
                    wait();
                }
                }
                
                try{
                    if(pcb.getExceptionG() > 0){
                       System.out.println(
                        "--------------------------------\n"
                        + "Proceso: " + pcb.getProcess_name()
                        + "\n" + "MAR: " + pcb.getMAR_Status()
                        + "\n" + "PC: " + pcb.getPC_Status()
                        + "\n" + "Longitud: " + pcb.getLength()
                        + "\n" + "Tipo: I/O bound"
                        + "\n--------------------------------\n"); 
                    }
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
                    
                }catch(Exception e){
                System.out.println(
                        "--------------------------------\n"
                        + "Proceso: " + pcb.getProcess_name()
                        + "\n" + "MAR: " + pcb.getMAR_Status()
                        + "\n" + "PC: " + pcb.getPC_Status()
                        + "\n" + "Longitud: " + pcb.getLength()
                        + "\n" + "Tipo: CPU bound"
                        + "\n--------------------------------\n"); }
                
                
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
