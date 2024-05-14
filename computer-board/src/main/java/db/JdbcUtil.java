package db;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
 
public class JdbcUtil {
 
	public static Connection getConnection(){
		
		Connection con=null;
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/comdb";
		
		try {
			
			Class.forName(driver);
			con=DriverManager.getConnection(url,"root","qwer1234");
			con.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void close(Connection con){
		
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(Statement stmt){
		
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(ResultSet rs){
		
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void commit(Connection con){
		
		try {
			con.commit();
			System.out.println("commit success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void rollback(Connection con){
		
		try {
			con.rollback();
			System.out.println("rollback success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
