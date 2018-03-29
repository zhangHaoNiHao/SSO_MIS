package Utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class jdbcUtil {
	
	
	public static Connection getConnection() throws Exception
	{
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		String driverClass=bundle.getString("driverClass");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");
		System.out.println("”√ªß√˚ "+user+"  √‹¬Î  "+password+"  url "+url+" driver  "+driverClass);
		Class.forName(driverClass);
		Connection conn = null;
		conn = DriverManager.getConnection(url,user,password);
		return conn;
	}
	public static void closeResource(Connection conn,Statement st,ResultSet rs)
	{
		closeResultSet(rs);
		closeStatement(st);
		closeConnection(conn);
	}
	public static void closeResultSet(ResultSet rs)
	{
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		rs = null;
	}
	public static void closeStatement(Statement pstmt)
	{
		if(pstmt!=null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		pstmt = null;
	}
	public static void closeConnection(Connection conn)
	{
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		conn = null;
	}
}
