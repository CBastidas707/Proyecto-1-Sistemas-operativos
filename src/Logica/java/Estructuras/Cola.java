
package Logica.java.Estructuras;
import Logica.java.PCB;
import Logica.java.Process;


public class Cola {
    private String name;
    private Nodo head;
    private Nodo tail;
    private int size;

    public Cola(String name) {
        this.head = head;
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Retorna true si está vacía la cola
     */
    
    public boolean isEmpty(){
        return this.getHead() == null;
    }
    
    /**
     * Vacía toda la cola
     */
    
    public void Empty(){
        setHead(null);
        setTail(null);
        setSize(0);
    }
    
    /**
     * Permite añadir un nodo a la cola
     */
    
    public void encolar(Object dato){
        Nodo nuevo = new Nodo(dato);
        if(isEmpty()){
            setHead(nuevo);
            setTail(nuevo);
        }else{
            getTail().setpNext(nuevo);
            setTail(nuevo);
        }
        setSize(getSize() + 1);
    }
    
        public void encolarProceso(Process dato){
        Nodo nuevo = new Nodo(dato);
        if(isEmpty()){
            setHead(nuevo);
            setTail(nuevo);
        }else{
            getTail().setpNext(nuevo);
            setTail(nuevo);
        }
        setSize(getSize() + 1);
    }
    
    /**
     * Permite despachar al primero en la cola
     */

    public void desencolar(){
        if (!isEmpty()){
            if(getSize() == 1){
                Empty();
            }else{
                setHead(getHead().getpNext());
                setSize(getSize() - 1);
            }
        }
    }
    
public Process desencolarProceso() {
    if (isEmpty()) {
        return null;
    }

    if (getHead().getData() instanceof Process) {
        Process proceso = (Process) getHead().getData();

        if (getSize() == 1) {
            Empty();
        } else {
            setHead(getHead().getpNext());
            setSize(getSize() - 1);
        }
        return proceso;
    } else {
        return null; 
    }
}
    
    /**
     * Retorna un string con todos los valores de los nodos encolados por orden desde el primer nodo hasta el ultimo en cola
     */
    
    public String travel(){
        String toPrint = "";
        if(!isEmpty()){
            for (int i = 0; i < getSize(); i++) {
                Nodo actual = head;
                desencolar();
                toPrint += actual.getData() + "-->";
                encolar(actual.getData());
            }
        } 
        return toPrint;
    }
    
        public String travelPCB(){
        String toPrint = "";
        if(!isEmpty()){
            for (int i = 0; i < getSize(); i++) {
                Nodo actual = head;
                desencolar();
                Process data = (Process) actual.getData();
                toPrint += "," + data.getPcb().getProcess_name();
                encolar(actual.getData());
            }
        } 
        return toPrint;
    }
    /**
     * Retorna el primer nodo en la cola
     */
    public Nodo getHead() {
        return head;
    }

    /**
     * Permite indicar a la cola qué nodo está de primero
     */
    public void setHead(Nodo head) {
        this.head = head;
    }

    /**
     * Retorna el último nodo en la cola
     */
    public Nodo getTail() {
        return tail;
    }

    /**
     * Permite indicar a la cola qué nodo está de último en la cola
     */
    public void setTail(Nodo tail) {
        this.tail = tail;
    }

    /**
     * Retorna la cantidad de nodos en cola
     */
    public int getSize() {
        return size;
    }

    /**
     * Permite indicar a la cola cuántos nodos tiene
     */
    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Process getProcessAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        Nodo current = head;
        for (int i = 0; i < index; i++) {
            current = current.getpNext();
        }

        if (current.getData() instanceof Process) {
            return (Process) current.getData();
        } else {
            return null; // Or throw an exception if you expect only Process objects
        }
    }
    
    public PCB getPCBAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        Nodo current = head;
        for (int i = 0; i < index; i++) {
            current = current.getpNext();
        }

        if (current.getData() instanceof PCB) {
            return (PCB) current.getData();
        } else {
            return null; // Or throw an exception if you expect only Process objects
        }
    }
    
    
    
    
    
}
