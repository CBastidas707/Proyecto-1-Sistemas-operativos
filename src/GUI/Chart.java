/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Logica.java.Estructuras.Cola;
import Logica.java.Estructuras.List;
import Logica.java.Estructuras.Nodo;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author carlo_7ogoiii
 */
public class Chart {

    private XYSeries seriesCpuBound;
    private XYSeries seriesIoBound;
    private XYSeriesCollection dataset;
    private JFreeChart xyLineChart;
    private Timer updateTimer;
    private Cola finished;
    private Cola ready;
    private List blocked;

    private int id;

    public Chart(int id, Cola finished) {
        initializeSeries();
        initializeChart();
        startDataUpdateTimer(id);
        this.finished = finished;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private void initializeSeries() {
        seriesCpuBound = new XYSeries("CPU-Bound");
        seriesIoBound = new XYSeries("I/O-Bound");
        dataset = new XYSeriesCollection();
        dataset.addSeries(seriesCpuBound);
        dataset.addSeries(seriesIoBound);
    }

    private void initializeChart() {
        xyLineChart = ChartFactory.createXYLineChart(
                "Procesos terminados", // Título del gráfico
                "Tiempo", // Etiqueta eje X
                "Cantidad de Procesos", // Etiqueta eje Y
                dataset, // Dataset
                PlotOrientation.VERTICAL,
                true, // Mostrar leyenda
                true, // Generar tooltips
                false // URLs
        );

        customizeChart();
    }

    /**
     * Personaliza la apariencia del gráfico.
     */
    private void customizeChart() {
        XYPlot plot = xyLineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // Configurar colores y estilos
        renderer.setSeriesPaint(0, Color.GREEN); // CPU-Bound en verde
        renderer.setSeriesPaint(1, Color.yellow); // I/O-Bound en amarillo
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
    }

    /**
     * Inicia un temporizador que actualiza las series de datos periódicamente.
     */
    private void startDataUpdateTimer(int id) {
        int delay = 1000; // Actualización cada segundo
        updateTimer = new Timer(delay, e -> updateChartData(id));
        updateTimer.start();
    }

    private void updateChartData(int id) {
        try {
            switch (id) {
                case 0:
                    updateChartDataSystem();
                    break;
                case 1:
                    //updateChartDataCPU1();
                    break;
                case 2:
                    //updateChartDataCPU2();
                    break;
                case 3:
                    //updateChartDataCPU3();
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (Exception e) {
        }

    }

    public void updateChartDataSystem() {

        int tiempoActual = seriesCpuBound.getItemCount() + 1;

        int contadorCPUBOUND = 0;
        int contadorIOBOUND = 0;

        for (int i = 0; i < finished.getSize(); i++) {
            if (finished.getProcessAtIndex(i).getPcb().getExceptionG() > 0) {
                contadorIOBOUND++;
            } else {
                contadorCPUBOUND++;
            }
        }

        seriesCpuBound.addOrUpdate(tiempoActual, contadorCPUBOUND);
        seriesIoBound.addOrUpdate(tiempoActual, contadorIOBOUND);
    }



    public void clearChartData() {
        seriesCpuBound.clear();
        seriesIoBound.clear();
    }

    
    
    /**
     * Detiene la actualización automática del gráfico.
     */
    public void stopUpdateTimer() {
        if (updateTimer != null) {
            updateTimer.stop();
        }
    }
    
    public JFreeChart getChart() {
    return xyLineChart;
}
}

