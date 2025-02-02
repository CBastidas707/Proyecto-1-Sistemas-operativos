package Logica.java.Estructuras;


    public class List {
    private Nodo pFirst;
    private String name;
    private int iN;

    public List(String s) {
        this.name = s;
        this.pFirst = null;
        this.iN = 0;
    }

    public int size() {
        return this.iN;
    }

    public boolean isEmpty() {
        return this.pFirst == null;
    }

    public Nodo last() {
        if (isEmpty()) {
            return null;
        }
        Nodo pAux = this.pFirst;
        while (pAux.getpNext() != null) {
            pAux = pAux.getpNext();
        }
        return pAux;
    }

    public Nodo first() {
        return this.pFirst;
    }

    public Object read(Nodo pValor) {
        
        if (pValor.getData() instanceof Proceso){
           Proceso proceso = (Proceso) pValor.getData();
           return proceso.getNombre();
                   }
        return pValor.getData();
    }

    public Nodo next(Nodo pValor) {
        return pValor.getpNext();
    }

    public void insert(Object x, Nodo pValor) {
        Nodo pNew = new Nodo(x);
        if (this.isEmpty()) {
            this.pFirst = pNew;
        } else {
            pNew.setpNext(pValor.getpNext());
            pValor.setpNext(pNew);
        }
        iN++;
    }

    public void insertFirst(Object x) {
        Nodo pNew = new Nodo(x);
        pNew.setpNext(this.pFirst);
        this.pFirst = pNew;
        iN++;
    }

    public void delete(Nodo pValor) {
        if (this.isEmpty()) {
            return;
        }
        if (pValor == this.pFirst) {
            this.pFirst = pValor.getpNext();
        } else {
            Nodo pAux = this.pFirst;
            while (pAux != null && pAux.getpNext() != pValor) {
                pAux = pAux.getpNext();
            }
            if (pAux != null) {
                pAux.setpNext(pValor.getpNext());
            }
        }
        iN--;
    }

    public String travel() {
        Nodo pAux = this.pFirst;
        String result = "";
        while (pAux != null) {
            result += this.read(pAux) + ", ";
            pAux = this.next(pAux);
        }
        return result;
    }

    public Nodo beforeLast() {
        if (this.isEmpty() || this.pFirst.getpNext() == null) {
            return null;
        }
        Nodo pAux = this.pFirst;
        while (pAux.getpNext().getpNext() != null) {
            pAux = pAux.getpNext();
        }
        return pAux;
    }
    

    public Nodo find(String targetNombre) {
        Nodo current = pFirst;
        while (current != null) {
            Object data = current.getData();
            if (data instanceof Proceso) { // Verifica si el objeto es de la clase Proceso
                Proceso proceso = (Proceso) data; // Castea el objeto a Proceso
                if (proceso.getNombre() != null && proceso.getNombre().equals(targetNombre)) {
                    return current; // Se encontró el nodo con el Proceso buscado
                }
            }
            current = current.getpNext();
        }
        return null; // No se encontró ningún nodo con el Proceso buscado
    }

}