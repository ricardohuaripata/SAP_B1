package paquete;

import java.io.File;

public class claseFile2 {

    public static void main(String[] args) {

	File directorio = new File("C:\\Users\\" + System.getProperty("user.name") + "\\eclipse-workspace");

	mostrarArchivos(directorio);

    }

    public static void mostrarArchivos(File carpeta) {

	try {

	    //Filtro filtro = new Filtro();
	    File ar[] = carpeta.listFiles();

	    for (int i = 0; i < ar.length; i++) {

		System.out.println(ar[i]);

		if (ar[i].isDirectory())
		    mostrarArchivos(ar[i]);

	    }

	}

	catch (Exception e) {
	    System.err.println("ERROR " + e.getMessage());
	}

    }

}
