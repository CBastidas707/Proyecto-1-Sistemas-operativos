package Logica.java;
import GUI.Pantalla;
import Logica.java.Estructuras.List;
import Logica.java.Process_Image;
import Logica.java.Process;

public class Main {

    
    
    public static void main(String[] args) {


       /* Esto es una prueba para crear listas y verificar que sirve   */
       List lista = new List("Prueba");
       
        Process_Image procesonuevo1 = new Process_Image("Proceso1", 5);
        Process_Image procesonuevo2 = new Process_Image("Proceso2", 4);
        Process_Image procesonuevo3 = new Process_Image("Proceso3", 3);
        Process_Image procesonuevo4 = new Process_Image("Proceso4", 2);
        Process_Image procesonuevo5 = new Process_Image("Proceso5", 1, 6, 5);
        Process_Image procesonuevo45 = new Process_Image("Proceso4,5", 0, 6, 7);
        
        
        lista.insertFirst(procesonuevo1);
        lista.insertFirst(procesonuevo2);
        lista.insertFirst(procesonuevo3);
        lista.insertFirst(procesonuevo4);
        lista.insertFirst(procesonuevo5);
        
        lista.insert(procesonuevo45,lista.find("Proceso5"));
        
        Process proceso1 = new Process(lista.findPCB("Proceso1"));
        Process proceso2 = new Process(lista.findPCB("Proceso5"));
        
        proceso1.start();
        proceso2.start();
        
        
        
         Pantalla pantalla = new Pantalla();
        pantalla.setVisible(true); 

    }
    
}
