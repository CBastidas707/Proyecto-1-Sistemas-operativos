package Logica.java.Estructuras;

public class Nodo {
    private Object data;
    private Nodo pNext;

    public Nodo(Object data) {
        this.data = data;
        this.pNext = null;
    }

    /**
     * Retorna data del nodo
     */
    public Object getData() {
        return data;
    }

    /**
     * Permite modificar la data del nodo
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Retorna el nodo que le sigue
     */
    public Nodo getpNext() {
        return pNext;
    }

    /**
     * Permite definir cuál es el nodo que le sigue
     */
    public void setpNext(Nodo pNext) {
        this.pNext = pNext;
    }
    
    
    
}
