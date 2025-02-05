package Logica.java;

public class PCB {
    private String Process_name;
    private String status;
    private int Length;
    private int PC_Status;
    private int MAR_Status;

    public PCB(String Process_name, int length, String status) {
        this.Process_name = Process_name;
        this.Length = length;
        this.status = status;
        this.PC_Status = 1;
        this.MAR_Status = 0;
    }

    // ID del proceso
    public String getProcess_name() {
        return Process_name;
    }

    public void setProcess_name(String Process_ID) {
        this.Process_name = Process_ID;
    }

    // Estado del proceso
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Estado del PC
    public int getPC_Status() {
        return PC_Status;
    }

    public void setPC_Status(int PC_Status) {
        this.PC_Status = PC_Status;
    }

    // Estado del MAR
    public int getMAR_Status() {
        return MAR_Status;
    }

    public void setMAR_Status(int MAR_Status) {
        this.MAR_Status = MAR_Status;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int Length) {
        this.Length = Length;
    }
    
    
}
