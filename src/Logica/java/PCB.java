package Logica.java;

public class PCB {
    private int PCB_ID;
    private String status;
    private String name;
    private String PC_Status;
    private String MAR_Status;

    public PCB(int PCB_ID, String status, String name, String PC_Status, String MAR_Status) {
        this.PCB_ID = PCB_ID;
        this.status = status;
        this.name = name;
        this.PC_Status = PC_Status;
        this.MAR_Status = MAR_Status;
    }

    // ID del proceso
    public int getPCB_ID() {
        return PCB_ID;
    }

    public void setPCB_ID(int PCB_ID) {
        this.PCB_ID = PCB_ID;
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
