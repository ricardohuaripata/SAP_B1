package paquete;

import java.util.Properties;
import java.io.*;

public class Configuracion {

	public static void main(String[] args) {

		String usuario = System.getProperty("user.name");

		Properties cfg = new Properties();

		cfg.setProperty("propiedad1", "empty");
		cfg.setProperty("propiedad2", "empty");
		cfg.setProperty("propiedad3", "empty");

		try {

			cfg.store(
					new FileOutputStream(
							"C:\\Users\\" + usuario + "\\eclipse-workspace\\SAP_B1\\MATERIALES\\Configuracion.cfg"),
					"Fichero de configuracion");

			System.out.println("PROPIEDAD 1: " + cfg.getProperty("propiedad1"));
			System.out.println("PROPIEDAD 2: " + cfg.getProperty("propiedad2"));
			System.out.println("PROPIEDAD 3: " + cfg.getProperty("propiedad3"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}