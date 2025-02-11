package Logica.java;
import GUI.Pantalla;
import Logica.java.Estructuras.Cola;
import Logica.java.Estructuras.List;
import Logica.java.Estructuras.Nodo;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;



public class Main {

    
    
    public static void main(String[] args) {


       // Esto es una prueba para crear listas y verificar que sirve, primero se crea una lista para almacenar las imágenes de los procesos
       List ProcessImagesList = new List("Imágenes de los procesos");
       
        Process_Image procesonuevo1 = new Process_Image("Proceso1", 5);
        Process_Image procesonuevo2 = new Process_Image("Proceso2", 4);
        //Process_Image procesonuevo3 = new Process_Image("Proceso3", 3);
        //Process_Image procesonuevo4 = new Process_Image("Proceso4", 2);
        Process_Image procesonuevo5 = new Process_Image("Proceso5", 4, 2, 3);
        //Process_Image procesonuevo45 = new Process_Image("Proceso4,5", 0, 6, 7);
        
        
        ProcessImagesList.insertFirst(procesonuevo1);
        ProcessImagesList.insertFirst(procesonuevo2);
        //ProcessImagesList.insertFirst(procesonuevo3);
        //ProcessImagesList.insertFirst(procesonuevo4);
        ProcessImagesList.insertFirst(procesonuevo5);
        
        //ProcessImagesList.insert(procesonuevo45,ProcessImagesList.find("Proceso5"));
        
        AtomicInteger tiempoInstruccion = new AtomicInteger(1000); // Esto es el tiempo que tardará cada ciclo de reloj
        
        //Estas son las colas de listos y bloqueados
        
        Cola colaR = new Cola("Ready");
        Cola colaB = new Cola("Blocked");
        
        
        Semaphore schedulerS = new Semaphore(1);  // Esto es un semáforo para acceder al scheduler
        
        Scheduler scheduler = new Scheduler(colaB, colaR);  // Esto crea al scheduler
        
        
        //Esta es la creación de los procesos a partir de sus imágenes
        
        Process proceso1 = new Process(ProcessImagesList.findPCB("Proceso1"), tiempoInstruccion,scheduler, schedulerS);
        Process proceso5 = new Process(ProcessImagesList.findPCB("Proceso5"), tiempoInstruccion, scheduler, schedulerS);
        Process proceso2 = new Process(ProcessImagesList.findPCB("Proceso2"), tiempoInstruccion, scheduler, schedulerS);

        
        //Esta es una lista de los procesos
        
        List listaProcesos = new List("Lista de procesos");
        listaProcesos.insertFirst(proceso1);
        listaProcesos.insertFirst(proceso5);
        listaProcesos.insertFirst(proceso2);
        
        
        // Esta es la creación de la lista de CPU y de cada CPU
        
        List listaCPU = new List("Lista CPU");
        Nodo Cpu1 = new Nodo(null);
        Nodo Cpu5 = new Nodo(null);
        listaCPU.insertFirst(Cpu5);
        listaCPU.insertFirst(Cpu1);
        
        
        // Esto es un for para iniciar cada proceso, se encolaran automaticamente porque se inicializan en "ready"
        
        for (int j = 0; j < listaProcesos.size(); j++) {
                listaProcesos.findProcessByIndex(j).start();
        }
        
        // Esto es para que no empiece a asignar
        
        while(listaCPU.size() > colaR.getSize()){
            ;
        }
        
        
        for (int i = 0; i < listaCPU.size(); i++) {
            Nodo cpu = listaCPU.findByIndex(i);
            Process proceso = colaR.desencolarProceso();
            cpu.setData(proceso);
            proceso.getPcb().setStatus("Running");
            
        }
        
        
        System.out.println("Procesos encolados en Ready: " + colaR.getSize());
        
        // Acá asignas los procesos a un Cpu
       
        
       
        

        
        Simulation simulation = new Simulation();
        Pantalla pantalla = new Pantalla(simulation);
        pantalla.setVisible(true);

    }
    
}
