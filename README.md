# StockCharting
Stock Charting Application (Java/MySQL)

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JFrame;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;

public class Example {

    public static void main(String args[]) {

        // 1. Download MSFT quotes from Yahoo Finance and store them as OHLCDataItem
        List<OHLCDataItem> dataItems = new ArrayList<OHLCDataItem>();
        try {
            String strUrl = "http://ichart.yahoo.com/table.csv?s=MSFT&a=3&b=1&c=2013&d=3&e=15&f=2050&g=d";
            URL url = new URL(strUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            DateFormat df = new SimpleDateFormat("y-M-d");

            String inputLine;
            in.readLine();
            while ((inputLine = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(inputLine, ",");

                Date date = df.parse(st.nextToken());
                double open = Double.parseDouble(st.nextToken());
                double high = Double.parseDouble(st.nextToken());
                double low = Double.parseDouble(st.nextToken());
                double close = Double.parseDouble(st.nextToken());
                double volume = Double.parseDouble(st.nextToken());
                double adjClose = Double.parseDouble(st.nextToken());

                // adjust data:
                open = open * adjClose / close;
                high = high * adjClose / close;
                low = low * adjClose / close;

                OHLCDataItem item = new OHLCDataItem(date, open, high, low, adjClose, volume);
                dataItems.add(item);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.reverse(dataItems); // Data from Yahoo is from newest to oldest. Reverse so it is oldest to newest.
        //Convert the list into an array
        OHLCDataItem[] data = dataItems.toArray(new OHLCDataItem[dataItems.size()]);
        OHLCDataset dataset = new DefaultOHLCDataset("MSFT", data);

        // 2. Create chart
        JFreeChart chart = ChartFactory.createCandlestickChart("Stock Chart", "Time", "Price", dataset, false);
        ChartPanel myChart = new ChartPanel(chart);
        myChart.setMouseWheelEnabled(true);

        // 3. Set chart background
        chart.setBackgroundPaint(Color.white);

        // 4. Set a few custom plot features
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE); // light yellow = new Color(0xffffe0)
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);
        ((NumberAxis) plot.getRangeAxis()).setAutoRangeIncludesZero(false);

        // 5. Skip week-ends on the date axis
        ((DateAxis) plot.getDomainAxis()).setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());

        // 6. No volume drawn
        ((CandlestickRenderer) plot.getRenderer()).setDrawVolume(false);

        
        JFrame myFrame = new JFrame();
        myFrame.setResizable(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JPanel jpanel = new JPanel();
        //jpanel.setLayout(new java.awt.BorderLayout());
        //jpanel.add(myChart,BorderLayout.CENTER);
        //jpanel.setVisible(true);
        //jpanel.validate();

        myFrame.add(new ChartPanel(chart), BorderLayout.CENTER);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Insets insets = kit.getScreenInsets(myFrame.getGraphicsConfiguration());
        Dimension screen = kit.getScreenSize();
        myFrame.setSize(new java.awt.Dimension( 800 , 800 ));
        myFrame.setLocation((int) (insets.left), (int) (insets.top));
        myFrame.setVisible(true);
        
        
       
    }
}
