package paquete;

import java.io.*;

public class MetodosFile1 {

	public static void main(String args[]) {

		File fichero = new File("C:\\CLINICA\\apellidos.txt");

		if (fichero.exists()) {

			System.out.println("NOMBRE: " + fichero.getName());
			System.out.println("RUTA: " + fichero.getPath());
			System.out.println("RUTA COMPLETA: " + fichero.getAbsolutePath());
			System.out.println("LONGITUD: " + fichero.length());
			System.out.println("SE PUEDE LEER: " + fichero.canRead());
			System.out.println("SE PUEDE ESCRIBIR: " + fichero.canWrite());

		} else {
			System.out.println("EL FICHERO NO EXISTE");
		}

	}

}
