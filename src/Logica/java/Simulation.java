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
    
    // LECTURA / ESCRITURA DE ARCHIVOS:
    
    public void createFile(){
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
