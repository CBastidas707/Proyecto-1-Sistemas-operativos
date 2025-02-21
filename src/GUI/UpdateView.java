/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Logica.java.Estructuras.Cola;
import Logica.java.Estructuras.List;
import Logica.java.Estructuras.Nodo;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import Logica.java.Process;
import java.util.concurrent.Semaphore;
import javax.swing.JLabel;

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
private DefaultListModel blocked;
private DefaultListModel ready;
private DefaultListModel finished;
private Cola colaR;
private List colaB;
private Cola colaF;
private int contadorReloj;
private JLabel labelsito;

    public UpdateView(DefaultListModel cpu1, DefaultListModel cpu2, DefaultListModel cpu3, List listaCPU,
            AtomicInteger sleepTime, DefaultListModel blocked, DefaultListModel ready, DefaultListModel finished, List colaB, Cola colaR, Cola colaF, JLabel labelsito) {
        this.cpu1 = cpu1;
        this.cpu2 = cpu2;
        this.cpu3 = cpu3;
        this.listaCPU = listaCPU;
        this.sleepTime = sleepTime;
        this.blocked = blocked;
        this.ready = ready;
        this.finished = finished;
        this.colaB = colaB;
        this.colaR = colaR;
        this.colaF = colaF;
        this.contadorReloj = 0;
        
        this.labelsito = labelsito;
    }


    @Override
    public void run(){
        
        while(true){
            try {
                updateLabel(labelsito);
                this.Actualizar(cpu1, 0);
                this.Actualizar(cpu2, 1);
                
                if(listaCPU.size() == 3){
                this.Actualizar(cpu3, 2);
                }
                
                ActualizarColaR();

                sleep(sleepTime.get() + 5);
                
                this.contadorReloj ++;
            

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
    
    private void updateLabel(JLabel labelsito){
        
        
        labelsito.setText("Ciclo de Reloj: " + contadorReloj);
        
        
    }
    
    public void ActualizarColaR(){
        
       ready.removeAllElements();
       String[] strings = colaR.travelPCB().split(",");

        for (int i = 0; i < strings.length; i++) {
            
            String str = strings[i];
            
            ready.addElement(str);
            
        }
        
        
    }
    
    
    
    
    
    
}
