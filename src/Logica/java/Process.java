package Logica.java;

import Logica.java.Estructuras.Nodo;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.UpdateView;

/**
 *
 * @author carlo_7ogoiii
 */
public class Process extends Thread {

    private PCB pcb;
    private AtomicInteger sleepTime; // Variable atómica para el tiempo de sleep
    private Scheduler scheduler;
    private Nodo cpu;
    private AtomicInteger planificacion;

    public Process(PCB pcb, AtomicInteger sleepTime, Scheduler scheduler, Nodo cpu, AtomicInteger planificacion) {
        this.pcb = pcb;
        this.sleepTime = sleepTime;
        this.scheduler = scheduler;
        this.cpu = cpu;
        this.planificacion = planificacion;
    }

    @Override
    public void run() {

        int ciclosG = 1;
        int quantum = 5;

        if (pcb.getExceptionG() > 0) {
            ciclosG = pcb.getExceptionG(); // Servirá como contador en un proceso I/O bound para saber cuándo realizará una operación entrada salida
        }

        while (pcb.getMAR_Status() != pcb.getLength() && pcb.getStatus() != "Exit") {

            try {

                // Para que el proceso se encole en bloqueados se tiene que cambiar el estatus de su pcb a Blocked, y se ejecutará esto:
                if ("Blocked".equals(pcb.getStatus())) {

                    scheduler.EncolarBloqueado(this);
                    System.out.println(pcb.getProcess_name() + " fue transladado a la cola de bloqueados");
                    int ciclosT = pcb.getExceptionD(); // Es un contador para medir cuántos ciclos faltan para que se complete la operación entrada salida

                    while ("Blocked".equals(pcb.getStatus())) {

                        System.out.println("Ciclos para completar operacion entrada salida de " + pcb.getProcess_name() + ": " + ciclosT);
                        sleep(sleepTime.get());
                        ciclosT--;

                        if (ciclosT == 0) {
                            scheduler.Interrupt(this);

                        }

                    }
                }

                // Para que el proceso se encole en listos se tiene que cambiar el estatus de su pcb a Ready, y se ejecutará esto:
                if ("Ready".equals(pcb.getStatus())) {

                    scheduler.EncolarListo(this);
                    System.out.println(pcb.getProcess_name() + " fue transladado a la cola de listos");

                    while ("Ready".equals(pcb.getStatus())) {

                        sleep(10);
                    }
                    System.out.println(pcb.getProcess_name() + " fue despachado");
                }

                // Esto es lo que hará el proceso
                // Si el proceso es I/O bound
                if (pcb.getExceptionG() > 0) {

                    if (ciclosG == 1) {// Si falta un solo ciclo para generar una interrupción:

                        pcb.setStatus("Blocked");
                        ciclosG = pcb.getExceptionG() + 1;
                    }

                    System.out.println(
                            "--------------------------------\n"
                            + "Proceso: " + pcb.getProcess_name()
                            + "\n" + "MAR: " + pcb.getMAR_Status()
                            + "\n" + "PC: " + pcb.getPC_Status()
                            + "\n" + "Longitud: " + pcb.getLength()
                            + "\n" + "Tipo: I/O bound"
                            + "\n--------------------------------\n");

                    ciclosG--;
                } // Si el proceso es CPU bound
                else {
                    System.out.println(
                            "--------------------------------\n"
                            + "Proceso: " + pcb.getProcess_name()
                            + "\n" + "MAR: " + pcb.getMAR_Status()
                            + "\n" + "PC: " + pcb.getPC_Status()
                            + "\n" + "Longitud: " + pcb.getLength()
                            + "\n" + "Tipo: CPU bound"
                            + "\n--------------------------------\n");
                }

                if (planificacion.get() == 2) {
                    quantum--;
                    System.out.println("Quantum restante del " + pcb.getProcess_name() + ": " + quantum);
                }

                if (quantum == 0) {
                    pcb.setStatus("Ready");
                    scheduler.Interrupt(this);
                    quantum = 5;
                }

                pcb.setMAR_Status(pcb.getMAR_Status() + 1);
                pcb.setPC_Status(pcb.getPC_Status() + 1);
                sleep(sleepTime.get());

            } catch (InterruptedException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        pcb.setStatus("Exit");
        scheduler.Finish(this);

        while (pcb.getStatus() == "Exit") {
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Finalizo " + pcb.getProcess_name());
    }

    public PCB getPcb() {
        return pcb;
    }

    public void setPcb(PCB pcb) {
        this.pcb = pcb;
    }

    public Nodo getCpu() {
        return cpu;
    }

    public void setCpu(Nodo cpu) {
        this.cpu = cpu;
    }

}
