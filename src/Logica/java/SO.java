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
public class SO extends Thread {
    private Cola readyQueue;
    private List buffer;
    private String queueOp;
    private Semaphore soS;
    private Process proceso;


    public SO(Cola readyQueue, String queueOp, Semaphore soS, Process proceso) {
        this.readyQueue = readyQueue;
        this.queueOp = queueOp;
        this.soS = soS;
        this.proceso = proceso;
    }
    
    
    
    @Override
    public void run(){
        
        System.out.println("Se esta ejecutando el SO");
        
        while(queueOp != "Exit"){
            
            if(queueOp == "Next"){
                try {
                    sleep(3000);
                    
                    soS.acquire();
                    
                    Nodo cpu = proceso.getCpu();
                    
                    System.out.println("Procesos en la cola de listos: " + readyQueue.getSize());

                    Process nextProceso = readyQueue.desencolarProceso();  // Saca de la cola de listos al siguiente proceso para despacharlo   
        
                    if (nextProceso != null) {  // Despacha al proceso en el CPU en el que estaba el proceso que se bloqueó
                        cpu.setData(nextProceso);
                        nextProceso.setCpu(cpu);
                        nextProceso.getPcb().setStatus("Running");
            
                    } else{ // Si no hay procesos en la cola de listos
                          System.out.println("No hay mas procesos en la cola de listos");
                          while(queueOp != "Exit"){
                              sleep(10);
                              if(readyQueue.getSize()> 0){
                              nextProceso = readyQueue.desencolarProceso();
                              }
                              if(nextProceso != null){
                                  cpu.setData(nextProceso);
                                  nextProceso.setCpu(cpu);
                                  nextProceso.getPcb().setStatus("Running");
                                  queueOp = "Exit";
                              }
                              
                          }
                            }
                    
                    queueOp = "Exit";
                    soS.release();
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(SO.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        }
        
        
    }

    public String getQueueOp() {
        return queueOp;
    }

    public void setQueueOp(String queueOp) {
        this.queueOp = queueOp;
    }
    
    

}
