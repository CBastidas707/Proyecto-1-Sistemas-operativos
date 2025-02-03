package Logica.java;

public class Process {
    private String process_name;
    private int process_length; // Cantidad de instrucciones
    public String consumption_category;  // Si es CPU Bound o I/O Bound
    
    // Parámetros de procesos I/O Bound
    public int exceptionRequirement = 0;     // ¿Cuántos ciclos se requieren para generar una excepción?
    public int exceptionCoverage = 0;        // ¿Cuántos ciclos se requieren para satisfacer una excepción?

    // Constructor proceso CPU Bound
    public Process(String process_name, int process_length) {
        this.process_name = process_name;
        this.process_length = process_length;
        this.consumption_category = "CPU Bound";
    }
    
    // Constructor proceso I/O Bound
    
        public Process(String process_name, int process_length, int exceptionRequirement, int exceptionCoverage) {
        this.process_name = process_name;
        this.process_length = process_length;
        this.consumption_category = "I/O Bound";
        this.exceptionRequirement = exceptionRequirement;
        this.exceptionCoverage = exceptionCoverage;
    }
    

    // Nombre del Proceso
    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    // Cantidad de instrucciones
    public int getProcess_length() {
        return process_length;
    }

    public void setProcess_length(int process_length) {
        this.process_length = process_length;
    }

    // Tipo de consumo. DEBE AGREGARSE LA RESTRICCIÓN: I/O Bound / CPU Bound
    public String getConsumption_category() {
        return consumption_category;
    }

    public void setConsumption_category(String consumption_category) {
        this.consumption_category = consumption_category;
    }

    // Parámetros para el caso I/O Bound
    public int getExceptionRequirement() {
        return exceptionRequirement;
    }

    public void setExceptionRequirement(int exceptionRequirement) {
        this.exceptionRequirement = exceptionRequirement;
    }

    public int getExceptionCoverage() {
        return exceptionCoverage;
    }

    public void setExceptionCoverage(int exceptionCoverage) {
        this.exceptionCoverage = exceptionCoverage;
    }
    
    
}
    
