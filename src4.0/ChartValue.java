import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.chart.CategoryAxis;

import org.jfree.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartValue extends ApplicationFrame {
	private static Connection conn = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	public static ResultSet resultSet;
	public static String selectedStock;
	public static String[] stockValue;
	public static String[] stockDate;
	public static String[] stockVolume;
	public static long rowCount;


	public static void main(String[] args){
		switch(StockComparisons.stockCase) {
		case 1: selectedStock = StockComparisons.stockBoxA.getSelectedItem().toString();
		break;
		case 2: selectedStock = StockComparisons.stockBoxB.getSelectedItem().toString();
		break;
		}
		Connect();
		Gather();
		ChartValue chartValue = new ChartValue("Stock Charting" , "Value of " + selectedStock + " Stock");
		chartValue.pack( );
		RefineryUtilities.centerFrameOnScreen( chartValue );
		chartValue.setVisible( true );
		
	}

	public void windowClosing(final WindowEvent evt){
		if(evt.getWindow() == this){
		dispose();

		}
		}
	
	

	public ChartValue(String applicationTitle , String chartTitle)
	{
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createLineChart(
				chartTitle,
				"Date","Value of Stock",
				createDatasetValue(),
				PlotOrientation.VERTICAL,
				true,true,false);

		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 600 , 500 ) );
		setContentPane( chartPanel );
		chartPanel.setMouseWheelEnabled(true);
		CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setDomainGridlinesVisible(true);
        org.jfree.chart.axis.CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        .75));
	}

	private DefaultCategoryDataset createDatasetValue( )
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		Double[] stockValueNumber = new Double[(int) rowCount];
		for(int i = 0; i < rowCount; i++){
			
			stockValueNumber[i] = Double.parseDouble(stockValue[i]);
			dataset.addValue(stockValueNumber[i], "Value" , stockDate[i]);
		}

		return dataset;
	}
	
	

	
	
	

	private static void Gather() {

		try {
			statement = conn.createStatement();
			resultSet = statement
					.executeQuery("SELECT COUNT(*) FROM " + selectedStock);
			while (resultSet.next()) {
				rowCount = (long) resultSet.getObject("COUNT(*)");
			}	    
		}catch (SQLException e) {
			e.printStackTrace();
		}

		stockValue = new String[(int) rowCount];
		stockDate = new String[(int) rowCount];
		stockVolume = new String[(int) rowCount];

		try {
			statement = conn.createStatement();
			resultSet = statement
					.executeQuery("SELECT * FROM " + selectedStock);
			int i = 0;
			while (resultSet.next()) {
				String datePart1 = null;
				String datePart2 = null;
				String datePart3 = null;
				String datePart4 = null;
				String datePart5 = null;
				stockValue[i] = resultSet.getObject("Last_Trade_Value").toString();
				datePart1 = resultSet.getObject("Last_Trade_DateTime").toString().split("/")[1];
				datePart2 = resultSet.getObject("Last_Trade_DateTime").toString().split("/")[2]; //don't use
				datePart3 = datePart2.split(",")[0];
				datePart4 = resultSet.getObject("Last_Trade_DateTime").toString().split(",")[1]; //don't use
				datePart5 = datePart4.split("(?=\\p{Lower})")[0];
				stockDate[i] = datePart1 + "/" + datePart3 + datePart5;
				Double.parseDouble(stockValue[i]);
				i++;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}





	public static Connection Connect(){
		conn = null;
		try{
			String url = "jdbc:mysql://localhost:3306/stocks";
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("Successfully connected to database!");
		} catch(Exception e){
			System.out.println("Exception found: " + e);
		}
		return conn;
	}

	public Connection getConnection(){
		return conn;
	}

	public void closeConnection(){
		try{
			conn.close ();
		}catch (Exception e) {
			System.out.println ("Connection close error");
		}
	}
}