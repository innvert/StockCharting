package phpMy;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class GUI extends JFrame {
	
	private static Connection conn = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	private static Object updateSet = null;
	private static JComboBox stockBox = new JComboBox();
	private static JLabel stockName = new JLabel();
	public static String symbol = null;
	private JPanel contentPane;

	
	public static void main(String[] args) {
		String URL = "http://download.finance.yahoo.com/d/quotes.csv?s=AAPL,IBM,GS,AXP,USB,DE,WFC,WMT,KO,PG&f=snl1d1t1c1ohgv&e=.csv";
		try {
		    org.apache.commons.io.FileUtils.copyURLToFile(new URL(URL), new File("C:\\Users\\Kevin\\Desktop\\download\\stocks.csv"));
		} catch (Exception x) { x.printStackTrace(); }
		System.out.println("Successfully downloaded Yahoo Finance Stock Data!");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		conn = Connect.Connect();
		Initialize();
	}

	public void Initialize(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		stockBox.setMaximumRowCount(20);
		stockBox.setBounds(93, 37, 100, 25);
		contentPane.add(stockBox);
		
		stockName.setBounds(108, 72, 204, 14);
		contentPane.add(stockName);
		
		final JLabel lblSelectStock = new JLabel("Select Stock:");
		lblSelectStock.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSelectStock.setBounds(10, 42, 76, 20);
		contentPane.add(lblSelectStock);
		
		final JLabel lblStockCompany = new JLabel("Stock Company:");
		lblStockCompany.setBounds(10, 72, 108, 14);
		contentPane.add(lblStockCompany);
		
		final JLabel lblShareValue = new JLabel("Share Value:");
		lblShareValue.setBounds(10, 98, 76, 14);
		contentPane.add(lblShareValue);
		
		final JLabel stockLastTradeValue = new JLabel("");
		stockLastTradeValue.setBounds(86, 98, 46, 14);
		contentPane.add(stockLastTradeValue);
		
		final JLabel lblStockInspector = new JLabel("Stock Inspector");
		lblStockInspector.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblStockInspector.setBounds(5, 2, 257, 31);
		contentPane.add(lblStockInspector);
		
		final JLabel lblNewLabel = new JLabel("Daily High:");
		lblNewLabel.setBounds(10, 192, 60, 14);
		contentPane.add(lblNewLabel);
		
		final JLabel lblNewLabel_1 = new JLabel("Daily Low:");
		lblNewLabel_1.setBounds(10, 215, 60, 14);
		contentPane.add(lblNewLabel_1);
		
		final JLabel stockDailyHigh = new JLabel("");
		stockDailyHigh.setBounds(75, 192, 60, 14);
		contentPane.add(stockDailyHigh);
		
		final JLabel stockDailyLow = new JLabel("");
		stockDailyLow.setBounds(75, 215, 60, 14);
		contentPane.add(stockDailyLow);
		
		final JLabel lblLastTradeDaytime = new JLabel("Last Trade:");
		lblLastTradeDaytime.setBounds(10, 122, 65, 14);
		contentPane.add(lblLastTradeDaytime);
		
		final JLabel stockLastTradeDate = new JLabel("");
		stockLastTradeDate.setBounds(82, 122, 70, 14);
		contentPane.add(stockLastTradeDate);
		
		final JLabel lblDailyChange = new JLabel("Daily Change:");
		lblDailyChange.setBounds(10, 146, 76, 14);
		contentPane.add(lblDailyChange);
		
		final JLabel stockDailyChange = new JLabel("");
		stockDailyChange.setBounds(92, 146, 46, 14);
		contentPane.add(stockDailyChange);
		
		final JLabel stockLastTradeTime = new JLabel("");
		stockLastTradeTime.setBounds(150, 122, 76, 14);
		contentPane.add(stockLastTradeTime);
		
		final JLabel lblOpening = new JLabel("Opening:");
		lblOpening.setBounds(10, 170, 54, 14);
		contentPane.add(lblOpening);
		
		final JLabel stockOpening = new JLabel("");
		stockOpening.setBounds(65, 170, 70, 14);
		contentPane.add(stockOpening);
		
		final JLabel lblNewLabel_2 = new JLabel("Volume:");
		lblNewLabel_2.setBounds(10, 236, 54, 14);
		contentPane.add(lblNewLabel_2);
		
		final JLabel stockVolume = new JLabel("");
		stockVolume.setBounds(62, 236, 83, 14);
		contentPane.add(stockVolume);
		
		JLabel lblNewLabel_3 = new JLabel("Shares");
		lblNewLabel_3.setBounds(120, 236, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(207, 261, 76, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(293, 261, 76, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(379, 261, 76, 23);
		contentPane.add(btnNewButton_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(223, 37, 233, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(223, 248, 233, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(222, 37, 2, 212);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(455, 37, 2, 212);
		contentPane.add(separator_3);
		
		JLabel lblChartingArea = new JLabel("Charting Area");
		lblChartingArea.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChartingArea.setBounds(281, 8, 136, 25);
		contentPane.add(lblChartingArea);
		
		stockBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedStock = stockBox.getSelectedItem().toString();
				try {
					statement = conn.createStatement();
				    resultSet = statement
					          .executeQuery("SELECT * FROM topstocks WHERE Symbol = '" + selectedStock + "'");
					      writeNameLabel(resultSet);	 
				}catch (SQLException e) {
						e.printStackTrace();
					}
				
			}

			private void writeNameLabel(ResultSet resultSet) throws SQLException {
				 while (resultSet.next()) {
					 	String name = resultSet.getObject("Name").toString();
					 	String lastTradeValue =  resultSet.getObject("Last_Trade_Value").toString();
					 	String lastTradeDate = resultSet.getObject("Last_Trade_Date").toString();
					 	String lastTradeTime = resultSet.getObject("Last_Trade_Time").toString();
					 	String dailyChange = resultSet.getObject("Daily_Change").toString();
					 	String opening = resultSet.getObject("Opening").toString();
					 	String dailyHigh = resultSet.getObject("Daily_High").toString();
					 	String dailyLow = resultSet.getObject("Daily_Low").toString();
					 	String volume = resultSet.getObject("Volume").toString();
				    	stockName.setText(name);
				    	stockLastTradeValue.setText(lastTradeValue);
				    	stockLastTradeDate.setText(lastTradeDate);
				    	stockLastTradeTime.setText(lastTradeTime);
				    	stockDailyChange.setText(dailyChange);
				    	stockOpening.setText(opening);
				    	stockDailyHigh.setText(dailyHigh);
				    	stockDailyLow.setText(dailyLow);
				    	stockVolume.setText(volume);
				    	//stockVolume.setText()
				    	
				    	
				    }
				
			}
		});
		
	
		try {
			statement = conn.createStatement();
			statement.executeUpdate("DROP TABLE IF EXISTS `topstocks`");
			statement.executeUpdate("CREATE TABLE `topstocks` ("
			          		+ "`Symbol` varchar(4), `Name` varchar(31), `Last_Trade_Value` decimal(5,2), "
			          		+ "`Last_Trade_Date` varchar(12), `Last_Trade_Time` varchar(8), `Daily_Change` decimal(4,2), "
			          		+ "`Opening` decimal(5,2), `Daily_High` decimal(5,2), `Daily_Low` decimal(5,2), `Volume` int(8)"
			          		+ ")");
		}catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		try {
			statement = conn.createStatement();
		    resultSet = statement
			          .executeQuery("LOAD DATA LOCAL INFILE 'C:/Users/Kevin/Desktop/download/stocks.csv' INTO TABLE `topstocks` FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'");
		}catch (SQLException e) {
				e.printStackTrace();
			}
		
		try {
			statement = conn.createStatement();
		    resultSet = statement
			          .executeQuery("SELECT * FROM topstocks");
			      populateComboBox(resultSet);
		}catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	 private static void populateComboBox(ResultSet resultSet) throws SQLException {
		    // ResultSet is initially before the first data set
		    while (resultSet.next()) {
		    	stockBox.addItem(resultSet.getObject("Symbol").toString());
		    	
		    }
		    
		  }
}
