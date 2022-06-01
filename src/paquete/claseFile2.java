package paquete;

import java.io.File;
import java.io.FileFilter;

public class claseFile2 {

    static FileFilter filtro = new FileFilter() {

	public boolean accept(File file) {
	    if (file.getName().endsWith(".bak") || file.isDirectory())
		return true;

	    return false;

	}
    };

    public static void main(String[] args) {

	File directorio = new File("C:\\");

	mostrarArchivos(directorio);

    }

    public static void mostrarArchivos(File carpeta) {

	try {

	    File ar[] = carpeta.listFiles(filtro);

	    for (int i = 0; i < ar.length; i++) {

		System.out.println(ar[i]);

		if (ar[i].isDirectory()==false)
		    mostrarArchivos(ar[i]);

	    }

	}

	catch (Exception e) {
	    System.err.println(e.getMessage());
	}

    }

}
