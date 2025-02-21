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
        
        System.out.println("Se esta ejecutando el SO. Previo a el se estaba ejecutando el proceso " + proceso.getPcb().getProcess_name());
        
        while(queueOp != "Exit"){
            
            if(queueOp == "SPN"){
                try {
                    soS.acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(SO.class.getName()).log(Level.SEVERE, null, ex);
                }
                reordenarSPN();
                soS.release();
                this.setQueueOp("Next");
            }
            
            if(queueOp == "Next"){
                try {
                    
                    sleep(3000);
                    soS.acquire();
                    
                    
                    
                    Nodo cpu = proceso.getCpu();
                    
                    System.out.println("Procesos en la cola de listos: " + readyQueue.getSize());

                    Process nextProceso = readyQueue.desencolarProceso();  // Saca de la cola de listos al siguiente proceso para despacharlo
                    
                    if(proceso.getPcb().getStatus() == "Exit"){
                        proceso.getPcb().setStatus("Finish");
                    }
        
                    if (nextProceso != null) {  // Despacha al proceso en el CPU en el que estaba el proceso que se bloqueó
                        cpu.setData(nextProceso);
                        nextProceso.setCpu(cpu);
                        nextProceso.getPcb().setStatus("Running");
                        soS.release();
            
                    } else{ // Si no hay procesos en la cola de listos
                          System.out.println("No hay mas procesos en la cola de listos");
                          soS.release();
                          boolean verificacion = false;
                          while(verificacion == false){
                              sleep(10);
                              
                              if(readyQueue.getSize()> 0){
                              soS.acquire();
                              nextProceso = readyQueue.desencolarProceso();
                              soS.release();
                              }
                              if(nextProceso != null){
                                  cpu.setData(nextProceso);
                                  nextProceso.setCpu(cpu);
                                  nextProceso.getPcb().setStatus("Running");
                                  verificacion = true;
                              }
                              
                          }
                            }
                    
                    
                    queueOp = "Exit";
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(SO.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
           
            
        }
        
        System.out.println("Se dejo de ejecutar el SO");
        
        
    }

    public String getQueueOp() {
        return queueOp;
    }

    public void setQueueOp(String queueOp) {
        this.queueOp = queueOp;
    }
    
    
    public void reordenarSPN(){
        
        
        List buffer = new List("Buffer");
        int size = readyQueue.getSize();

        // Pasar todos los procesos de la cola a la lista buffer
        
        for (int i = 0; i < size; i++) {
            Process proceso = readyQueue.desencolarProceso();
            if (proceso != null) {
                buffer.insertFirst(proceso);
            }
        }

        // Ordenar la lista buffer usando el algoritmo de selección
        
        Nodo current = buffer.first();
        while (current != null) {
            Nodo minNode = current;
            Nodo next = current.getpNext();
            while (next != null) {
                Process currentProcess = (Process) current.getData();
                Process nextProcess = (Process) next.getData();
                Process minProcess = (Process) minNode.getData();

                if (nextProcess.getPcb().getLength() < minProcess.getPcb().getLength()) {
                    minNode = next;
                }
                next = next.getpNext();
            }

            // Intercambiar datos si es necesario
            if (minNode != current) {
                Object temp = current.getData();
                current.setData(minNode.getData());
                minNode.setData(temp);
            }
            current = current.getpNext();
        }


        // 3. Devolver los procesos ordenados a la cola readyQueue
        
        current = buffer.first();
        while (current != null) {
            Process proceso = (Process) current.getData();
            readyQueue.encolarProceso(proceso);
            current = current.getpNext();
        }
        
        
    }
    
    

}
