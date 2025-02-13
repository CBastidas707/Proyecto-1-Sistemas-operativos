package Logica.java.Estructuras;
import Logica.java.PCB;
import Logica.java.Process_Image;
import Logica.java.Process;


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
        
        if (pValor.getData() instanceof Process_Image){
           Process_Image proceso = (Process_Image) pValor.getData();
           return proceso.getProcess_name();
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
            if (data instanceof Process_Image) { // Verifica si el objeto es de la clase Proceso
                Process_Image proceso = (Process_Image) data; // Castea el objeto a Proceso
                if (proceso.getProcess_name() != null && proceso.getProcess_name().equals(targetNombre)) {
                    return current; // Se encontró el nodo con el Proceso buscado
                }
            }
            current = current.getpNext();
        }
        return null; // No se encontró ningún nodo con el Proceso buscado
    }
    
        public PCB findPCB(String targetNombre) {
          Nodo current = pFirst;
          while (current != null) {
              Object data = current.getData();
              if (data instanceof Process_Image) { // Verifica si el objeto es de la clase Proceso
                  Process_Image proceso = (Process_Image) data; // Castea el objeto a Proceso
                  if (proceso.getProcess_name() != null && proceso.getProcess_name().equals(targetNombre)) {
                      return proceso.getPcb(); // Se encontró el nodo con el Proceso buscado
                  }
              }
              current = current.getpNext();
          }
          return null; // No se encontró ningún nodo con el Proceso buscado
    }
        
        public Nodo findByIndex(int index) {
            
          if (index < 0 || index >= this.size()) {
              throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
          }

          Nodo current = this.pFirst;
          for (int i = 0; i < index; i++) {
              current = current.getpNext();
          }

          return current;

    }
        
        public Process_Image findProcessImageByIndex(int index) {
            
          if (index < 0 || index >= this.size()) {
              throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
          }

          Nodo current = this.pFirst;
          for (int i = 0; i < index; i++) {
              current = current.getpNext();
          }

          Object data = current.getData();
              if (data instanceof Process_Image) {// Verifica si el objeto es de la clase Process Image
                  Process_Image proceso = (Process_Image) data; // Castea el objeto a Process Image
                  return proceso;

              }

              return null;

    }
               
        public Process findProcessByIndex(int index) {
            
            if (index < 0 || index >= this.size()) {
                throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
            }
            Nodo current = this.pFirst;
            for (int i = 0; i < index; i++) {
                current = current.getpNext();
            }

            Object data = current.getData();
                if (data instanceof Process) {// Verifica si el objeto es de la clase Proceso
                    Process proceso = (Process) data; // Castea el objeto a Proceso
                    return proceso;

                }

                return null;

        }

    }
