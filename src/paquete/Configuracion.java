package paquete;

import java.util.Properties;
import java.io.*;

public class Configuracion {

	public static void main(String[] args) {

		String usuario = System.getProperty("user.name");

		Properties cfg = new Properties();

		cfg.setProperty("db", "SBODemoES");
		cfg.setProperty("server", "localhost");
		cfg.setProperty("user", "sa");
		cfg.setProperty("password", "12345Ab##");
		cfg.setProperty("port", "1433");
		cfg.setProperty("url", "jdbc:sqlserver://" + cfg.getProperty("server") + ":" + cfg.getProperty("port")
				+ ";databaseName=" + cfg.getProperty("db") + ";encrypt=false");
		
		cfg.setProperty("driver","com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {

			cfg.store(
					new FileOutputStream(
							"C:\\Users\\" + usuario + "\\eclipse-workspace\\SAP_B1\\MATERIALES\\Configuracion.cfg"),
					"Fichero de configuracion");
			
			System.out.println("Fichero creado");

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}