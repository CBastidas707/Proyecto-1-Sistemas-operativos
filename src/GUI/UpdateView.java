/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Logica.java.Estructuras.List;
import Logica.java.Estructuras.Nodo;
import javax.swing.DefaultListModel;
import Logica.java.Process;

/**
 *
 * @author carlo_7ogoiii
 */
public class UpdateView{

private DefaultListModel cpu1;
private DefaultListModel cpu2;
private DefaultListModel cpu3;
private List listaCPU;

    public UpdateView(DefaultListModel cpu1, DefaultListModel cpu2, DefaultListModel cpu3, List listaCPU) {
        this.cpu1 = cpu1;
        this.cpu2 = cpu2;
        this.cpu3 = cpu3;
        this.listaCPU = listaCPU;
    }

    
    public void Actualizar(Nodo nodoCPU){
        
        DefaultListModel cpu = null;

        // Identificar el DefaultListModel según el Nodo
        
        if (nodoCPU == listaCPU.findByIndex(0)) {
            cpu = cpu1;
        } else if (nodoCPU == listaCPU.findByIndex(1)) {
            cpu = cpu2;
        } else if (nodoCPU == listaCPU.findByIndex(2)) {
            cpu = cpu3;
        }

        if (cpu != null) { // Si se encontró el DefaultListModel
            
            cpu.removeAllElements();
        
            Object data = nodoCPU.getData();
        
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
                 
        }}else{
            System.out.println("No se encontro el CPU");
        }   
    }
    
    
    
    
    
    
    
}
