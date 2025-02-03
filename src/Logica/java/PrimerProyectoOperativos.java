package Logica.java;
import Logica.java.Estructuras.List;
import Logica.java.Estructuras.Nodo;
import Logica.java.Process;

public class PrimerProyectoOperativos {

    
    
    public static void main(String[] args) {


       /* Esto es una prueba para crear listas y verificar que sirve   */
       List lista = new List("Prueba");
       
        Process proceso1 = new Process("Proceso1", 5);
        Process proceso2 = new Process("Proceso2", 4);
        Process proceso3 = new Process("Proceso3", 3);
        Process proceso4 = new Process("Proceso4", 2);
        Process proceso5 = new Process("Proceso5", 1, 6, 5);
        Process proceso45 = new Process("Proceso4,5", 0, 6, 7);
        
        
        lista.insertFirst(proceso1);
        lista.insertFirst(proceso2);
        lista.insertFirst(proceso3);
        lista.insertFirst(proceso4);
        lista.insertFirst(proceso5);
        
        lista.insert(proceso45,lista.find("Proceso5"));
        
        System.out.println(lista.travel());
        
    }
    
}
