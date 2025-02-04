package Logica.java;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo_7ogoiii
 */
public class Process extends Thread{
    
    private PCB pcb;

    public Process(PCB pcb) {
        this.pcb = pcb;
    }
    
    @Override
    public void run(){
        while(true){
         
            try {
                System.out.println(
                        "--------------------------------\n"
                        + "Proceso: " + pcb.getProcess_name()
                        + "\n" + "MAR: " + pcb.getMAR_Status()
                        + "\n" + "PC: " + pcb.getPC_Status()
                        + "\n" + "Longitud: " + pcb.getLength()
                        + "\n--------------------------------\n");
                pcb.setMAR_Status(pcb.getMAR_Status() + 1);
                pcb.setPC_Status(pcb.getPC_Status() + 1);
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
