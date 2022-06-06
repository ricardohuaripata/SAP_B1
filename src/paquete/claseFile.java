package paquete;

import java.io.File;

public class claseFile {

    public static void main(String[] args) {
	// TODO Auto-generated method stub

	File dir = new File("C:\\CLINICA");

	File ar[] = dir.listFiles();

	if (dir.isDirectory()) {

	    for (int i = 0; i < ar.length; i++) {

		System.out.println("NOMBRE: " + ar[i].getName() + ":");
		System.out.println(ar[i]);
		System.out.println("ES FICHERO: " + ar[i].isFile());
		System.out.println("ES CARPETA: " + ar[i].isDirectory());
		System.out.println();

	    }

	}

    }

}
