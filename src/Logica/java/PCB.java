package Logica.java;

public class PCB {
    private int Process_ID;
    private String status;
    private String name;
    private String PC_Status;
    private String MAR_Status;

    public PCB(int Process_ID, String status, String name, String PC_Status, String MAR_Status) {
        this.Process_ID = Process_ID;
        this.status = status;
        this.name = name;
        this.PC_Status = PC_Status;
        this.MAR_Status = MAR_Status;
    }

    // ID del proceso
    public int getProcess_ID() {
        return Process_ID;
    }

    public void setProcess_ID(int Process_ID) {
        this.Process_ID = Process_ID;
    }

    // Estado del proceso
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Nombre del proceso
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Estado del PC
    public String getPC_Status() {
        return PC_Status;
    }

    public void setPC_Status(String PC_Status) {
        this.PC_Status = PC_Status;
    }

    // Estado del MAR
    public String getMAR_Status() {
        return MAR_Status;
    }

    public void setMAR_Status(String MAR_Status) {
        this.MAR_Status = MAR_Status;
    }
    
    
}
