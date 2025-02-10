package Logica.java;
import GUI.Pantalla;
import Logica.java.Estructuras.Cola;
import Logica.java.Estructuras.List;
import Logica.java.Estructuras.Nodo;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;



public class Main {

    
    
    public static void main(String[] args) {


       /* Esto es una prueba para crear listas y verificar que sirve   */
       List ProcessImagesList = new List("Imágenes de los procesos");
       
        Process_Image procesonuevo1 = new Process_Image("Proceso1", 5);
        Process_Image procesonuevo2 = new Process_Image("Proceso2", 4);
        Process_Image procesonuevo3 = new Process_Image("Proceso3", 3);
        Process_Image procesonuevo4 = new Process_Image("Proceso4", 2);
        Process_Image procesonuevo5 = new Process_Image("Proceso5", 4, 2, 3);
        Process_Image procesonuevo45 = new Process_Image("Proceso4,5", 0, 6, 7);
        
        
        ProcessImagesList.insertFirst(procesonuevo1);
        ProcessImagesList.insertFirst(procesonuevo2);
        ProcessImagesList.insertFirst(procesonuevo3);
        ProcessImagesList.insertFirst(procesonuevo4);
        ProcessImagesList.insertFirst(procesonuevo5);
        
        ProcessImagesList.insert(procesonuevo45,ProcessImagesList.find("Proceso5"));
        
        AtomicInteger tiempoInstruccion = new AtomicInteger(1000);
        
        Cola colaR = new Cola("Ready");
        Cola colaB = new Cola("Blocked");
        
        Semaphore queueInterrupt = new Semaphore(1);
        
        Scheduler scheduler = new Scheduler(queueInterrupt, colaB, colaR);
        
        
        Process proceso1 = new Process(ProcessImagesList.findPCB("Proceso1"), tiempoInstruccion,scheduler );
        Process proceso5 = new Process(ProcessImagesList.findPCB("Proceso5"), tiempoInstruccion, scheduler);
        
        Nodo Cpu1 = new Nodo(proceso1);
        Nodo Cpu5 = new Nodo(proceso5);
        
        // Acá asignas los procesos a un Cpu
        
        if(Cpu1.getData() instanceof Process){
            Process proceso = (Process) Cpu1.getData();
            proceso.start();
            proceso.getPcb().setStatus("Running");
        };
        
        if(Cpu5.getData() instanceof Process){
            Process proceso = (Process) Cpu5.getData();
            proceso.start();
            proceso.getPcb().setStatus("Running");
        };
        
        
        
       
        

        
        Simulation simulation = new Simulation();
        Pantalla pantalla = new Pantalla(simulation);
        pantalla.setVisible(true);

    }
    
}
