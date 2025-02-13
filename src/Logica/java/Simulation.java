/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.java;

import Logica.java.Estructuras.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author andre
 */
public class Simulation {
    
    int instructionCycle_duration;  // Duración del ciclo de ejecución de una instrucción. (En ms o segundos)
    int activeCPU_ammount;          // Número de procesadores activos
    
    List procImage_List = new List("Lista de Procesos"); /*EL NOMBRE ES PROVISIONAL. REVISAR. */

    // PARÁMETROS DE INICIO: Getters & Setters
    
    public int getInstructionCycle_duration() {
        return instructionCycle_duration;
    }

    public void setInstructionCycle_duration(int instructionCycle_duration) {
        this.instructionCycle_duration = instructionCycle_duration;
    }

    public int getActiveCPU_ammount() {
        return activeCPU_ammount;
    }

    public void setActiveCPU_ammount(int activeCPU_ammount) {
        this.activeCPU_ammount = activeCPU_ammount;
    }
    
    
    
    public void addProcessImage (Process_Image image){
        this.procImage_List.insertFirst(image);
    }
    
    // LECTURA / ESCRITURA DE ARCHIVOS
    
    public void createTxt(){
        String simulationData = Integer.toString(instructionCycle_duration)+"\n"+Integer.toString(activeCPU_ammount);
        String userHome = System.getProperty("user.home");
        String filePath = userHome + "/Desktop/Operativos Proyecto/Parameters.txt";
        File file = new File(filePath);        
        file.getParentFile().mkdirs();
        
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(file);
            printWriter.print(simulationData);
            System.out.println("Datos de simulación guardados en " + filePath);
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
    
    public String showProcessJSON() {      // POR AHORA SOLO GUARDA LOS PROCESOS EN UN STRING PARA IMPRIMIRLO
        String process_string = "[\n";
        for (int i = 0; i < this.procImage_List.size(); i++) {
            Process_Image proceso_i = this.procImage_List.findProcessImageByIndex(i);
            process_string += "     {\n        \"process_name\": \""+proceso_i.getProcess_name()+"\",\n        \"process_length\": "+proceso_i.getProcess_length()+",\n        \"consumption_category\": \""+proceso_i.getConsumption_category()+"\"";
            if (proceso_i.getConsumption_category().equals("I/O Bound")) {
                process_string += ",\n        \"exceptionRequirement\": "+proceso_i.getExceptionRequirement()+",\n        \"exceptionCoverage\": "+proceso_i.getExceptionCoverage();
            }
            if (!(i == this.procImage_List.size()-1)) {
                process_string += "\n     },\n";
            } else {
                process_string += "\n     }\n";
            }
        }
        process_string += "]";
        
        return process_string;
    }
    
    public void createJSON() {
        String userHome = System.getProperty("user.home");
        String filePath = userHome + "/Desktop/Operativos Proyecto/Processes.json";
        File file = new File(filePath);        
        file.getParentFile().mkdirs();
        
        PrintWriter printWriter = null;
        
        try {
            printWriter = new PrintWriter(file);
            printWriter.print(this.showProcessJSON());
            System.out.println("Datos de simulación guardados en " + filePath);
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
    
    private static String extractValue(String json, String key) {
        String pattern = "\"" + key + "\":\"?(\\d+|[^\"]+)\"?";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
    
    public void addProcessesFromJSON(String processesJSON){
        
        processesJSON = processesJSON.replaceAll("[\n\\s{\\[\\]]+","");
        String[] elements = processesJSON.split("}");
        for (String element : elements) {
            String processName = extractValue(element, "process_name");
            int processLength = Integer.parseInt(extractValue(element, "process_length"));
            String consumptionCategory = extractValue(element, "consumption_category");
            
            if(consumptionCategory.equals("I/O Bound")){
                int exceptionRequirement = Integer.parseInt(extractValue(element, "exceptionRequirement"));
                int exceptionCoverage = Integer.parseInt(extractValue(element, "exceptionCoverage"));
                Process_Image IOProcess = new Process_Image(processName,processLength,exceptionRequirement,exceptionCoverage);
                this.procImage_List.insertFirst(IOProcess);
            } else{
                Process_Image CPUProcess = new Process_Image(processName,processLength);
                this.procImage_List.insertFirst(CPUProcess);
            }
        }
    }
    
    public String readFile(File loadedFile) throws FileNotFoundException {
        String readData = "";
        String line = null;
        
        FileReader fr = new FileReader(loadedFile);
        BufferedReader br = new BufferedReader(fr);
        try {
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                }
                readData += line + "\n";
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return readData;
    }
    
}
