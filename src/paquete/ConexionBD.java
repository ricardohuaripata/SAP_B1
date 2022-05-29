package paquete;

import java.sql.*;

public class ConexionBD {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
	
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String db = "master";
	String server = "DESKTOP-NALQRE9";
	//String ports = "1433:1433";
	String user = "DESKTOP-NALQRE9\\ricar";
	String password = "";

	String url = "jdbc:sqlserver://"+server+"/"+db;
	System.out.println(url);

	Class.forName(driver);
	System.out.println("Driver establecido");

	Connection conexion = DriverManager.getConnection(url, user, password);

	System.out.println("Conexion establecida");

	conexion.close();

    }

}
