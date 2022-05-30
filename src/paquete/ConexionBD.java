package paquete;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConexionBD {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		
		Properties cfg = new Properties();
		
		String dir_configuracion = "C:\\Users\\" + System.getProperty("user.name")
		+ "\\eclipse-workspace\\SAP_B1\\MATERIALES\\Configuracion.cfg";
		
		cfg.load(new BufferedReader(new FileReader(dir_configuracion)));
		
		System.out.println(cfg.getProperty("user"));

		String db = cfg.getProperty("db");
		String server = cfg.getProperty("server");
		String user = cfg.getProperty("user");
		String password = cfg.getProperty("password");
		String port = cfg.getProperty("port");

		String url = cfg.getProperty("url");

		Class.forName(cfg.getProperty("driver"));

		System.out.println("Driver establecido");

		Connection conexion = DriverManager.getConnection(url, user, password);

		System.out.println("Conexion establecida");

		conexion.close();

	}

}
