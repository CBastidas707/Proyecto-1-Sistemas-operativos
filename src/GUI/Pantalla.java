/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Logica.java.Estructuras.Cola;
import Logica.java.Estructuras.List;
import Logica.java.Estructuras.Nodo;
import Logica.java.Process_Image;
import Logica.java.Scheduler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

/**
 *
 * @author carlo_7ogoiii
 */
public class Pantalla extends javax.swing.JFrame {
    /**
     * Creates new form Pantalla
     */
    
    private List listaCPU;
    
    DefaultListModel cpu1 = new DefaultListModel();
    DefaultListModel cpu2 = new DefaultListModel();
    DefaultListModel cpu3 = new DefaultListModel();
    
    
    public Pantalla() {
        
        initComponents();
        listCPU1.setModel(cpu1);
        listCPU2.setModel(cpu2);
        listCPU3.setModel(cpu3);
        
        // Esto es una prueba para crear listas y verificar que sirve, primero se crea una lista para almacenar las imágenes de los procesos
       List ProcessImagesList = new List("Imágenes de los procesos");
       
        Process_Image procesonuevo1 = new Process_Image("Proceso1", 6);
        Process_Image procesonuevo2 = new Process_Image("Proceso2", 8,2,3);
        //Process_Image procesonuevo3 = new Process_Image("Proceso3", 3);
        //Process_Image procesonuevo4 = new Process_Image("Proceso4", 2);
        Process_Image procesonuevo5 = new Process_Image("Proceso5", 4, 2, 3);
        //Process_Image procesonuevo45 = new Process_Image("Proceso4,5", 0, 6, 7);
        

        
        ProcessImagesList.insertFirst(procesonuevo1);
        ProcessImagesList.insertFirst(procesonuevo2);
        //ProcessImagesList.insertFirst(procesonuevo3);
        //ProcessImagesList.insertFirst(procesonuevo4);
        ProcessImagesList.insertFirst(procesonuevo5);
        
        //ProcessImagesList.insert(procesonuevo45,ProcessImagesList.find("Proceso5"));

        
        AtomicInteger tiempoInstruccion = new AtomicInteger(1000); // Esto es el tiempo que tardará cada ciclo de reloj
        AtomicInteger planificacion = new AtomicInteger(2);    // Esto es la política de planificación
        
        //Estas son las colas de listos y bloqueados
        
        Cola colaR = new Cola("Ready");
        List colaB = new List("Blocked");
        
        
        Semaphore soS = new Semaphore(1);  // Esto es un semáforo para acceder a la sección crítica del SO
        
        Scheduler scheduler = new Scheduler(colaB, colaR, soS, planificacion);  // Esto crea al scheduler
        
        
        //Esta es la creación de los procesos a partir de sus imágenes
        
        Logica.java.Process proceso1 = new Logica.java.Process(ProcessImagesList.findPCB("Proceso1"), tiempoInstruccion,scheduler, null, planificacion);
        Logica.java.Process proceso5 = new Logica.java.Process(ProcessImagesList.findPCB("Proceso5"), tiempoInstruccion, scheduler, null, planificacion);
        Logica.java.Process proceso2 = new Logica.java.Process(ProcessImagesList.findPCB("Proceso2"), tiempoInstruccion, scheduler, null, planificacion);

        
        //Esta es una lista de los procesos
        
        List listaProcesos = new List("Lista de procesos");
        listaProcesos.insertFirst(proceso1);
        listaProcesos.insertFirst(proceso5);
        listaProcesos.insertFirst(proceso2);
        
        
        // Esta es la creación de la lista de CPU y de cada CPU
        
        List listaCPU = new List("Lista CPU");
        Nodo Cpu1 = new Nodo(null);
        Nodo Cpu2 = new Nodo(null);
        Nodo Cpu3 = new Nodo(null);
        listaCPU.insertFirst(Cpu3);
        listaCPU.insertFirst(Cpu2);
        listaCPU.insertFirst(Cpu1);
        
        UpdateView actualizarPantalla = new UpdateView(cpu1, cpu2, cpu3, listaCPU, tiempoInstruccion);
        
        actualizarPantalla.start();
        
        
        // Esto es un for para iniciar cada proceso, se encolaran automaticamente porque se inicializan en "ready"
        
        for (int j = 0; j < listaProcesos.size(); j++) {
                listaProcesos.findProcessByIndex(j).start();
        }
        
        // Esto es para que cuando hayan suficientes procesos listos, se asignen
        
        while(colaR.getSize() < listaProcesos.size()){
            ;
        }
        
        // Acá asignas los procesos a un Cpu
        
        for (int i = 0; i < listaCPU.size(); i++) {
            Nodo cpu = listaCPU.findByIndex(i);
            Logica.java.Process proceso = colaR.desencolarProceso();
            cpu.setData(proceso);
            proceso.setCpu(cpu);
            proceso.getPcb().setStatus("Running");
            
        }
    

        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        CrearProceso = new javax.swing.JPanel();
        txtDuracion = new javax.swing.JLabel();
        txtDuracion.setVisible(false);
        fieldCiclos = new javax.swing.JTextField();
        txtCiclo2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        txtCiclo1 = new javax.swing.JLabel();
        txtCiclo1.setVisible(false);
        fieldNombre = new javax.swing.JTextField();
        fieldNombre.setVisible(false);
        fieldDuracion = new javax.swing.JTextField();
        fieldDuracion.setVisible(false);
        txtCrearProceso = new javax.swing.JLabel();
        txtCiclo3 = new javax.swing.JLabel();
        txtCiclo4 = new javax.swing.JLabel();
        txtCiclo4.setVisible(false);
        fieldCiclos2 = new javax.swing.JTextField();
        opcion1 = new javax.swing.JButton();
        opcion2 = new javax.swing.JButton();
        botonCrear = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        button_loadFile = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        fileChooserStateMessage = new javax.swing.JTextPane();
        fieldinstructionCycle = new javax.swing.JTextField();
        fieldactiveCPU = new javax.swing.JTextField();
        txtinstructionCycle = new javax.swing.JLabel();
        txtActiveCPU = new javax.swing.JLabel();
        botonConfirmarValores = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCPU1 = new javax.swing.JList<>();
        txtCPU1 = new javax.swing.JLabel();
        txtCPU2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listCPU2 = new javax.swing.JList<>();
        txtCPU3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listCPU3 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CrearProceso.setLayout(null);

        txtDuracion.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtDuracion.setText("Duración:");
        CrearProceso.add(txtDuracion);
        txtDuracion.setBounds(260, 150, 101, 32);

        fieldCiclos.setVisible(false);
        fieldCiclos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCiclosActionPerformed(evt);
            }
        });
        CrearProceso.add(fieldCiclos);
        fieldCiclos.setBounds(380, 220, 251, 36);

        txtCiclo2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtCiclo2.setText("una interrupción:");
        txtCiclo2.setVisible(false);
        CrearProceso.add(txtCiclo2);
        txtCiclo2.setBounds(180, 220, 182, 50);

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtNombre.setText("Nombre:");
        txtNombre.setVisible(false);
        CrearProceso.add(txtNombre);
        txtNombre.setBounds(270, 90, 100, 32);

        txtCiclo1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtCiclo1.setText("Ciclos para generar");
        CrearProceso.add(txtCiclo1);
        txtCiclo1.setBounds(160, 200, 204, 32);

        fieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNombreActionPerformed(evt);
            }
        });
        CrearProceso.add(fieldNombre);
        fieldNombre.setBounds(380, 90, 251, 35);

        fieldDuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDuracionActionPerformed(evt);
            }
        });
        CrearProceso.add(fieldDuracion);
        fieldDuracion.setBounds(380, 150, 251, 35);

        txtCrearProceso.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        txtCrearProceso.setText("Crear un proceso");
        CrearProceso.add(txtCrearProceso);
        txtCrearProceso.setBounds(260, 0, 390, 64);

        txtCiclo3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtCiclo3.setText("Ciclos para satisfacer");
        txtCiclo3.setVisible(false);
        CrearProceso.add(txtCiclo3);
        txtCiclo3.setBounds(150, 270, 220, 30);

        txtCiclo4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtCiclo4.setText("una interrupción:");
        CrearProceso.add(txtCiclo4);
        txtCiclo4.setBounds(180, 300, 182, 32);

        fieldCiclos2.setVisible(false);
        fieldCiclos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCiclos2ActionPerformed(evt);
            }
        });
        CrearProceso.add(fieldCiclos2);
        fieldCiclos2.setBounds(380, 290, 251, 38);

        opcion1.setText("CPU Bound");
        opcion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion1ActionPerformed(evt);
            }
        });
        CrearProceso.add(opcion1);
        opcion1.setBounds(720, 90, 160, 50);

        opcion2.setText("I/O Bound");
        opcion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion2ActionPerformed(evt);
            }
        });
        CrearProceso.add(opcion2);
        opcion2.setBounds(720, 170, 160, 50);

        botonCrear.setText("Crear");
        botonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearActionPerformed(evt);
            }
        });
        CrearProceso.add(botonCrear);
        botonCrear.setBounds(550, 350, 76, 27);

        jTabbedPane1.addTab("Crear proceso", CrearProceso);

        button_loadFile.setText("Cargar  Archivo");
        button_loadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_loadFileActionPerformed(evt);
            }
        });

        fileChooserStateMessage.setEditable(false);
        fileChooserStateMessage.setText("Cargue los parámetros iniciales de la simulación.");
        jScrollPane2.setViewportView(fileChooserStateMessage);

        fieldinstructionCycle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldinstructionCycleActionPerformed(evt);
            }
        });

        fieldactiveCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldactiveCPUActionPerformed(evt);
            }
        });

        txtinstructionCycle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtinstructionCycle.setText("Duración del Ciclo de la Instrucción:");

        txtActiveCPU.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtActiveCPU.setText("Cantidad de Procesadores Activos:");

        botonConfirmarValores.setText("Confirmar Valores");
        botonConfirmarValores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfirmarValoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtActiveCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtinstructionCycle, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldinstructionCycle)
                    .addComponent(fieldactiveCPU))
                .addGap(232, 232, 232)
                .addComponent(button_loadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(botonConfirmarValores)
                        .addGap(81, 81, 81))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtinstructionCycle)
                    .addComponent(fieldinstructionCycle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtActiveCPU)
                    .addComponent(fieldactiveCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_loadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addComponent(botonConfirmarValores)
                .addGap(33, 33, 33))
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(listCPU1);

        txtCPU1.setText("CPU 1");

        txtCPU2.setText("CPU 2");

        jScrollPane3.setViewportView(listCPU2);

        txtCPU3.setText("CPU 3");

        jScrollPane4.setViewportView(listCPU3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCPU3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCPU1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCPU2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(314, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCPU2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(txtCPU1)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(txtCPU3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldCiclosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCiclosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldCiclosActionPerformed

    private void fieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNombreActionPerformed

    private void fieldDuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDuracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDuracionActionPerformed

    private void fieldCiclos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCiclos2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldCiclos2ActionPerformed

    private void opcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion1ActionPerformed
        txtNombre.setVisible(true);
        txtDuracion.setVisible(true);
        fieldDuracion.setVisible(true);
        fieldNombre.setVisible(true);
        txtCiclo1.setVisible(false);
        txtCiclo2.setVisible(false);
        txtCiclo3.setVisible(false);
        txtCiclo4.setVisible(false);
        fieldCiclos.setVisible(false);
        fieldCiclos2.setVisible(false);
        
        fieldCiclos.setText("");
        fieldCiclos2.setText("");
        
    }//GEN-LAST:event_opcion1ActionPerformed

    private void opcion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion2ActionPerformed
        txtNombre.setVisible(true);
        txtDuracion.setVisible(true);
        fieldDuracion.setVisible(true);
        fieldNombre.setVisible(true);
        txtCiclo1.setVisible(true);
        txtCiclo2.setVisible(true);
        txtCiclo3.setVisible(true);
        txtCiclo4.setVisible(true);
        fieldCiclos.setVisible(true);
        fieldCiclos2.setVisible(true);
    }//GEN-LAST:event_opcion2ActionPerformed

    private void botonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearActionPerformed

        
    }//GEN-LAST:event_botonCrearActionPerformed

    private void button_loadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_loadFileActionPerformed

        
        
    }//GEN-LAST:event_button_loadFileActionPerformed

    
    
    private void fieldinstructionCycleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldinstructionCycleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldinstructionCycleActionPerformed

    private void fieldactiveCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldactiveCPUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldactiveCPUActionPerformed

    private void botonConfirmarValoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarValoresActionPerformed

        
        
    }//GEN-LAST:event_botonConfirmarValoresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CrearProceso;
    private javax.swing.JButton botonConfirmarValores;
    private javax.swing.JButton botonCrear;
    private javax.swing.JButton button_loadFile;
    private javax.swing.JTextField fieldCiclos;
    private javax.swing.JTextField fieldCiclos2;
    private javax.swing.JTextField fieldDuracion;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JTextField fieldactiveCPU;
    private javax.swing.JTextField fieldinstructionCycle;
    private javax.swing.JTextPane fileChooserStateMessage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> listCPU1;
    private javax.swing.JList<String> listCPU2;
    private javax.swing.JList<String> listCPU3;
    private javax.swing.JButton opcion1;
    private javax.swing.JButton opcion2;
    private javax.swing.JLabel txtActiveCPU;
    private javax.swing.JLabel txtCPU1;
    private javax.swing.JLabel txtCPU2;
    private javax.swing.JLabel txtCPU3;
    private javax.swing.JLabel txtCiclo1;
    private javax.swing.JLabel txtCiclo2;
    private javax.swing.JLabel txtCiclo3;
    private javax.swing.JLabel txtCiclo4;
    private javax.swing.JLabel txtCrearProceso;
    private javax.swing.JLabel txtDuracion;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JLabel txtinstructionCycle;
    // End of variables declaration//GEN-END:variables
}
