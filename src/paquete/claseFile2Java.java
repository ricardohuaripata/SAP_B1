package paquete;

import java.io.File;

public class claseFile2Java {

    public static void main(String[] args) {

	File directorio = new File("C:\\");

	String extension = ".bak";

	mostrarArchivos(directorio, extension);

    }

    public static void mostrarArchivos(File carpeta, String extension) {

	try {

	    File ar[] = carpeta.listFiles();

	    for (int i = 0; i < ar.length; i++) {

		if (ar[i].getName().endsWith(extension))
		    System.out.println(ar[i]);

		if (ar[i].isDirectory())
		    mostrarArchivos(ar[i], extension);

	    }

	}

	catch (Exception e) {
	    System.err.println(e.getMessage());
	}

    }

}
