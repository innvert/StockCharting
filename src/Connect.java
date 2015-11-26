import java.sql.*;

public final class Connect {
	private static Connection conn = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	public static Connection Connect(){
		conn = null;
		try{
			String url = "jdbc:mysql://localhost:3306/stocks";
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("Successfully connected to database!");
		} catch(Exception e){
			System.out.println("Exception found");
		}
		return conn;
	}

	public Connection getConnection(){
		return conn;
	}
	public static void main(String[] args){
		Connect();
	}

	public void closeConnection(){
		try{
			conn.close ();
		}catch (Exception e) {
			System.out.println ("Connection close error");
		}
	}
}

