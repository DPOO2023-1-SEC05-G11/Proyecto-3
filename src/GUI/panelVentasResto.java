package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.CombinedRangeCategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JPanel;
import Persistencia.Hotel;
import java.util.HashMap;

public class panelVentasResto extends JPanel {

    private HashMap<String, int[]> ventasPorProd;

    public panelVentasResto() {
        ventasPorProd = Hotel.getInstance().ventasPorProdResto();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        DefaultCategoryDataset redDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset blueDataset = new DefaultCategoryDataset();

        for (String name : ventasPorProd.keySet()) {
            int[] values = ventasPorProd.get(name);
            redDataset.addValue(values[0], "Cantidad Vendido", name);
            blueDataset.addValue(values[1], "Precio Total Vendido", name);
        }

        JFreeChart redChart = ChartFactory.createBarChart("Cantidad (Rojo)", "Product", "Cantidad", redDataset);
        JFreeChart blueChart = ChartFactory.createBarChart("Precio (Azul)", "Product", "Precio", blueDataset);

        CategoryPlot redPlot = redChart.getCategoryPlot();
        CategoryPlot bluePlot = blueChart.getCategoryPlot();

        CombinedDomainCategoryPlot combinedDomainPlot = new CombinedDomainCategoryPlot();
        combinedDomainPlot.add(redPlot);
        combinedDomainPlot.add(bluePlot);
        combinedDomainPlot.setGap(10.0); 

        CombinedRangeCategoryPlot combinedRangePlot = new CombinedRangeCategoryPlot();
        combinedRangePlot.add(combinedDomainPlot);

        JFreeChart combinedChart = new JFreeChart("Ventas del Restaurante", combinedRangePlot);
        ChartFrame frame = new ChartFrame("Sales by Product", combinedChart);

        frame.setPreferredSize(new Dimension(1000, 400));
        frame.setBounds(0, 0, 1000, 400);

        add(frame.getContentPane());
    }

}
