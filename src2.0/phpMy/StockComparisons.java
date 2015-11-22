package phpMy;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;

public class StockComparisons extends JFrame {

	private JPanel contentPane;
	public static JComboBox stockBoxA = new JComboBox();
	public static JComboBox stockBoxB = new JComboBox();
	private static Connection conn = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	private static ResultSet resultSetName = null;
	public static String symbolA = null;
	public static String nameA = null;
	public static String dailyChangeA = null;
	public static String volumeA = null;
	public static String dailyHighA = null;
	public static String dailyLowA = null;
	public static String lastTradeTimeA = null;
	public static String lastTradeDateA = null;
	public static String lastTradeValueA = null;
	public static String openingA = null;
	public static String symbolB = null;
	public static String nameB = null;
	public static String dailyChangeB = null;
	public static String volumeB = null;
	public static String dailyHighB = null;
	public static String dailyLowB = null;
	public static String lastTradeTimeB = null;
	public static String lastTradeDateB = null;
	public static String lastTradeValueB = null;
	public static String openingB = null;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockComparisons frame = new StockComparisons();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StockComparisons() {
		conn = Connect.Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStockToStock = new JLabel("Stock to Stock Comparison");
		lblStockToStock.setBounds(5, 5, 637, 29);
		lblStockToStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockToStock.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(lblStockToStock);
		
		JLabel lblNewLabel = new JLabel("Stock A");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(106, 36, 80, 29);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 35, 652, 10);
		contentPane.add(separator);
		
		JLabel lblStockB = new JLabel("Stock B");
		lblStockB.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStockB.setBounds(427, 36, 80, 29);
		contentPane.add(lblStockB);
		
		JLabel lblCompanyNameA = new JLabel("Company Name:");
		lblCompanyNameA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCompanyNameA.setBounds(8, 79, 105, 14);
		contentPane.add(lblCompanyNameA);
		
		JLabel lblCurrentValueA = new JLabel("Current Value:");
		lblCurrentValueA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentValueA.setBounds(129, 104, 97, 14);
		contentPane.add(lblCurrentValueA);
		
		JLabel lblLastTradeA = new JLabel("Last Trade:");
		lblLastTradeA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastTradeA.setBounds(129, 129, 74, 14);
		contentPane.add(lblLastTradeA);
		
		JLabel lblDailyChangeA = new JLabel("Daily Change:");
		lblDailyChangeA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDailyChangeA.setBounds(8, 129, 85, 14);
		contentPane.add(lblDailyChangeA);
		
		JLabel lblOpeningA = new JLabel("Opening:");
		lblOpeningA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOpeningA.setBounds(8, 104, 60, 14);
		contentPane.add(lblOpeningA);
		
		JLabel lblDailyHighA = new JLabel("Daily High:");
		lblDailyHighA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDailyHighA.setBounds(8, 154, 65, 14);
		contentPane.add(lblDailyHighA);
		
		JLabel lblDailyLowA = new JLabel("Daily Low:");
		lblDailyLowA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDailyLowA.setBounds(141, 154, 65, 14);
		contentPane.add(lblDailyLowA);
		
		JLabel lblCompanyNameB = new JLabel("Company Name:");
		lblCompanyNameB.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCompanyNameB.setBounds(332, 79, 105, 14);
		contentPane.add(lblCompanyNameB);
		
		JLabel lblCurrentValueB = new JLabel("Current Value:");
		lblCurrentValueB.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentValueB.setBounds(446, 104, 97, 14);
		contentPane.add(lblCurrentValueB);
		
		JLabel lblLastTradeB = new JLabel("Last Trade:");
		lblLastTradeB.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastTradeB.setBounds(452, 129, 74, 14);
		contentPane.add(lblLastTradeB);
		
		JLabel lblDailyChangeB = new JLabel("Daily Change:");
		lblDailyChangeB.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDailyChangeB.setBounds(332, 129, 85, 14);
		contentPane.add(lblDailyChangeB);
		
		JLabel lblOpeningB = new JLabel("Opening:");
		lblOpeningB.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOpeningB.setBounds(332, 104, 60, 14);
		contentPane.add(lblOpeningB);
		
		JLabel lblDailyHighB = new JLabel("Daily High:");
		lblDailyHighB.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDailyHighB.setBounds(332, 154, 65, 14);
		contentPane.add(lblDailyHighB);
		
		JLabel lblDailyLowB = new JLabel("Daily Low:");
		lblDailyLowB.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDailyLowB.setBounds(462, 154, 65, 14);
		contentPane.add(lblDailyLowB);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(90, 64, 97, 10);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(410, 64, 95, 10);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(186, 35, 21, 30);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(410, 35, 21, 30);
		contentPane.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(505, 36, 21, 29);
		contentPane.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(90, 35, 21, 30);
		contentPane.add(separator_6);
		
		stockBoxA = new JComboBox();
		stockBoxA.setMaximumRowCount(10);
		stockBoxA.setBounds(200, 41, 105, 22);
		contentPane.add(stockBoxA);
		
		stockBoxB = new JComboBox();
		stockBoxB.setMaximumRowCount(10);
		stockBoxB.setBounds(519, 41, 105, 22);
		contentPane.add(stockBoxB);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(325, 36, 13, 437);
		contentPane.add(separator_7);
		
		JLabel lblVolumeA = new JLabel("Volume:");
		lblVolumeA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVolumeA.setBounds(8, 179, 60, 14);
		contentPane.add(lblVolumeA);
		
		JLabel stockDailyChangeA = new JLabel("Daily Change");
		stockDailyChangeA.setBounds(96, 129, 74, 14);
		contentPane.add(stockDailyChangeA);
		
		JLabel stockCompanyNameA = new JLabel("Company Name");
		stockCompanyNameA.setBounds(115, 80, 188, 14);
		contentPane.add(stockCompanyNameA);
		
		JLabel stockCurrentValueA = new JLabel("Current Value");
		stockCurrentValueA.setBounds(221, 105, 74, 14);
		contentPane.add(stockCurrentValueA);
		
		JLabel stockLastTradeA = new JLabel("Last Trade");
		stockLastTradeA.setBounds(200, 130, 150, 14);
		contentPane.add(stockLastTradeA);
		
		JLabel stockOpeningA = new JLabel("Opening");
		stockOpeningA.setBounds(67, 104, 46, 14);
		contentPane.add(stockOpeningA);
		
		JLabel stockDailyHighA = new JLabel("Daily High");
		stockDailyHighA.setBounds(83, 155, 67, 14);
		contentPane.add(stockDailyHighA);
		
		JLabel stockDailyLowA = new JLabel("Daily Low");
		stockDailyLowA.setBounds(210, 155, 67, 14);
		contentPane.add(stockDailyLowA);
		
		JLabel stockVolumeA = new JLabel("Volume");
		stockVolumeA.setBounds(67, 179, 139, 14);
		contentPane.add(stockVolumeA);
		
		JLabel lblVolumeB = new JLabel("Volume:");
		lblVolumeB.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVolumeB.setBounds(332, 179, 60, 14);
		contentPane.add(lblVolumeB);
		
		JLabel stockCompanyNameB = new JLabel("Company Name");
		stockCompanyNameB.setBounds(442, 80, 188, 14);
		contentPane.add(stockCompanyNameB);
		
		JLabel stockCurrentValueB = new JLabel("Current Value");
		stockCurrentValueB.setBounds(541, 105, 74, 14);
		contentPane.add(stockCurrentValueB);
		
		JLabel stockLastTradeB = new JLabel("Last Trade");
		stockLastTradeB.setBounds(522, 130, 150, 14);
		contentPane.add(stockLastTradeB);
		
		JLabel stockDailyChangeB = new JLabel("Daily Change");
		stockDailyChangeB.setBounds(421, 130, 74, 14);
		contentPane.add(stockDailyChangeB);
		
		JLabel stockOpeningB = new JLabel("Opening");
		stockOpeningB.setBounds(392, 105, 46, 14);
		contentPane.add(stockOpeningB);
		
		JLabel stockDailyHighB = new JLabel("Daily High");
		stockDailyHighB.setBounds(408, 155, 67, 14);
		contentPane.add(stockDailyHighB);
		
		JLabel stockDailyLowB = new JLabel("Daily Low");
		stockDailyLowB.setBounds(532, 155, 67, 14);
		contentPane.add(stockDailyLowB);
		
		JLabel stockVolumeB = new JLabel("Volume");
		stockVolumeB.setBounds(392, 180, 150, 14);
		contentPane.add(stockVolumeB);
		
		JLabel lblArrowA = new JLabel("");
		lblArrowA.setBounds(5, 36, 46, 29);
		contentPane.add(lblArrowA);
		
		JLabel lblArrowB = new JLabel("");
		lblArrowB.setBounds(330, 36, 46, 29);
		contentPane.add(lblArrowB);
		
		Image downArrow = new ImageIcon(this.getClass().getResource("downArrow.png")).getImage();
		Image upArrow = new ImageIcon(this.getClass().getResource("upArrow.png")).getImage();
		lblArrowA.setIcon(new ImageIcon(upArrow));
		lblArrowB.setIcon(new ImageIcon(downArrow));
		
		JLabel arrowPercentA = new JLabel("Daily Change");
		arrowPercentA.setBounds(46, 35, 46, 29);
		contentPane.add(arrowPercentA);
		
		JLabel arrowPercentB = new JLabel("Daily Change");
		arrowPercentB.setBounds(368, 35, 46, 29);
		contentPane.add(arrowPercentB);
		
		JLabel chartA = new JLabel("");
		chartA.setBounds(22, 224, 283, 238);
		contentPane.add(chartA);
		
		JLabel chartB = new JLabel("");
		chartB.setBounds(348, 224, 283, 238);
		contentPane.add(chartB);
		
		stockBoxA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = stockBoxA.getSelectedItem().toString();
				try {
					statement = conn.createStatement();
				    resultSet = statement
					          .executeQuery("SELECT * FROM " + name + "");
					      writeLabelsA(resultSet);	 
				}catch (SQLException e1) {
						e1.printStackTrace();
					}
				
				try {
					statement = conn.createStatement();
				    resultSetName = statement
					          .executeQuery("SELECT NAME FROM `watchedstocks` WHERE SYMBOL = '"  + name + "'");
					      writeNameA(resultSetName);	 
				}catch (SQLException e1) {
						e1.printStackTrace();
					}

				
				
				
			}
			private void writeLabelsA(ResultSet resultSet) throws SQLException {
				 while (resultSet.next()) {
					 	lastTradeDateA = resultSet.getObject("Last_Trade_DateTime").toString().split(", ")[0];
					 	lastTradeTimeA = resultSet.getObject("Last_Trade_DateTime").toString().split(", ")[1];
					 	lastTradeValueA =  resultSet.getObject("Last_Trade_Value").toString();
					 	dailyChangeA = resultSet.getObject("Daily_Change").toString();
					 	double percentChangeA = 0.0;
					 	percentChangeA = Double.valueOf(dailyChangeA);
					 	if(percentChangeA > 0.0) {
					 		lblArrowA.setIcon(new ImageIcon(upArrow));
					 		arrowPercentA.setForeground(Color.GREEN);
					 	}
					 	if(percentChangeA < 0.0) {
					 		lblArrowA.setIcon(new ImageIcon(downArrow));
					 		arrowPercentA.setForeground(Color.RED);
					 	}
					 	arrowPercentA.setText(dailyChangeA + " %");
					 	openingA = resultSet.getObject("Opening").toString();
					 	dailyHighA = resultSet.getObject("Daily_High").toString();
					 	dailyLowA = resultSet.getObject("Daily_Low").toString();
					 	volumeA = resultSet.getObject("Volume").toString();
				    	stockCurrentValueA.setText(lastTradeValueA);
				    	stockLastTradeA.setText(lastTradeDateA + "  " + lastTradeTimeA);
				    	stockDailyChangeA.setText(dailyChangeA +"");
				    	stockOpeningA.setText(openingA);
				    	stockDailyHighA.setText(dailyHighA);
				    	stockDailyLowA.setText(dailyLowA);
				    	stockVolumeA.setText(volumeA + " shares");
				    }
			}
			
		
			private void writeNameA(ResultSet resultSet) throws SQLException {
				 while (resultSet.next()) {
					 nameA = resultSetName.getObject("Name").toString();
					 stockCompanyNameA.setText(nameA);
				 }
			}
				
			
		});
		
		
		stockBoxB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = stockBoxB.getSelectedItem().toString();
				try {
					statement = conn.createStatement();
				    resultSet = statement
					          .executeQuery("SELECT * FROM " + name + "");
					      writeLabelsB(resultSet);	 
				}catch (SQLException e1) {
						e1.printStackTrace();
					}
				
				try {
					statement = conn.createStatement();
				    resultSetName = statement
					          .executeQuery("SELECT NAME FROM `watchedstocks` WHERE SYMBOL = '"  + name + "'");
					      writeNameB(resultSetName);	 
				}catch (SQLException e1) {
						e1.printStackTrace();
					}

				
				
				
			}
			private void writeLabelsB(ResultSet resultSet) throws SQLException {
				 while (resultSet.next()) {
					 	lastTradeDateB = resultSet.getObject("Last_Trade_DateTime").toString().split(", ")[0];
					 	lastTradeTimeB = resultSet.getObject("Last_Trade_DateTime").toString().split(", ")[1];
					 	lastTradeValueB =  resultSet.getObject("Last_Trade_Value").toString();
					 	dailyChangeB = resultSet.getObject("Daily_Change").toString();
					 	double percentChangeB = Double.valueOf(dailyChangeB);
					 	if(percentChangeB > 0.0) {
					 		lblArrowB.setIcon(new ImageIcon(upArrow));
					 		arrowPercentB.setForeground(Color.GREEN);
					 	}
					 	if(percentChangeB < 0.0) {
					 		lblArrowB.setIcon(new ImageIcon(downArrow));
					 		arrowPercentB.setForeground(Color.RED);
					 	}
					 	arrowPercentB.setText(dailyChangeB + " %");
					 	openingB = resultSet.getObject("Opening").toString();
					 	dailyHighB = resultSet.getObject("Daily_High").toString();
					 	dailyLowB = resultSet.getObject("Daily_Low").toString();
					 	volumeB = resultSet.getObject("Volume").toString();
				    	stockCurrentValueB.setText(lastTradeValueB);
				    	stockLastTradeB.setText(lastTradeDateB + "  " + lastTradeTimeB);
				    	stockDailyChangeB.setText(dailyChangeB);
				    	stockOpeningB.setText(openingB);
				    	stockDailyHighB.setText(dailyHighB);
				    	stockDailyLowB.setText(dailyLowB);
				    	stockVolumeB.setText(volumeB + " shares");
				    }
			}
			
		
			private void writeNameB(ResultSet resultSet) throws SQLException {
				 while (resultSet.next()) {
					 nameB = resultSetName.getObject("Name").toString();
					 stockCompanyNameB.setText(nameB);
				 }
			}
				
			
		});
		
		
	}
}
