/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Logica.java.Estructuras.List;
import Logica.java.Estructuras.Nodo;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import Logica.java.Process;
import java.util.concurrent.Semaphore;

/**
 *
 * @author carlo_7ogoiii
 */
public class UpdateView extends Thread{

private DefaultListModel cpu1;
private DefaultListModel cpu2;
private DefaultListModel cpu3;
private List listaCPU;
private AtomicInteger sleepTime;

    public UpdateView(DefaultListModel cpu1, DefaultListModel cpu2, DefaultListModel cpu3, List listaCPU, AtomicInteger sleepTime) {
        this.cpu1 = cpu1;
        this.cpu2 = cpu2;
        this.cpu3 = cpu3;
        this.listaCPU = listaCPU;
        this.sleepTime = sleepTime;
    }


    @Override
    public void run(){
        
        while(true){
            try {
                
                this.Actualizar(cpu1, 0);
                this.Actualizar(cpu2, 1);
                
                if(listaCPU.size() == 3){
                this.Actualizar(cpu3, 2);
                }

                sleep(sleepTime.get() + 5);
            

            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Actualizar(DefaultListModel cpu, int indice){
        
        cpu.removeAllElements();
        
        Object data = (listaCPU.findByIndex(indice).getData());
        
        if(data instanceof Process){
            
            Process proceso = (Process) data;
            
            if(proceso.getPcb().getExceptionG() > 0){
                // Si es I/O bound
                
                String nombre = proceso.getPcb().getProcess_name();
                String MAR = "MAR: " + String.valueOf(proceso.getPcb().getMAR_Status());
                String PC = "PC: " +  String.valueOf(proceso.getPcb().getPC_Status());
                String Length = "Longitud: " +  String.valueOf(proceso.getPcb().getLength());
                String Tipo = "Tipo: " +  "I/O bound";
                
                cpu.addElement(nombre);
                cpu.addElement(MAR);
                cpu.addElement(PC);
                cpu.addElement(Length);
                cpu.addElement(Tipo);
                
            } else{
                // Si es CPU bond
                
                String nombre = proceso.getPcb().getProcess_name();
                String MAR = "MAR: " +  String.valueOf(proceso.getPcb().getMAR_Status());
                String PC = "PC: " +  String.valueOf(proceso.getPcb().getPC_Status());
                String Length = "Longitud: " +  String.valueOf(proceso.getPcb().getLength());
                String Tipo =  "Tipo: " + "CPU bound";
                
                cpu.addElement(nombre);
                cpu.addElement(MAR);
                cpu.addElement(PC);
                cpu.addElement(Length);
                cpu.addElement(Tipo);
                             
            }
                    
                    
        } else{
            
            String so = "SO";
            cpu.addElement(so);
                 
        }
        
        
        
    }
    
    
    
    
    
    
    
}
