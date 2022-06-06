package paquete;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio2RAF {

    public static void main(String[] args) {

	Scanner sc = new Scanner(System.in);

	File fichero = new File("C:\\ficheros\\MiDocumento.txt");

	if (fichero.exists()) {
	    
	    System.out.print("Escribir palabra para modificar a mayusculas: ");
	    String palabra = sc.nextLine();
	    modificarFichero(palabra, fichero);

	} else
	    System.out.println("El fichero no existe");

    }

    public static void modificarFichero(String palabra, File fichero) {

	try {

	    RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
	    String cadena;
	    cadena = raf.readLine();
	    long pos = 0;

	    while (cadena != null) {

		if (cadena.indexOf(palabra) >= 0) {
		    cadena = cadena.replace(palabra, palabra.toUpperCase());
		    System.out.println(palabra.toUpperCase());
		    raf.seek(pos);
		    raf.writeBytes(cadena);

		}
		pos = raf.getFilePointer();
		cadena = raf.readLine();

	    }

	    raf.close();

	} catch (Exception e) {
	    e.printStackTrace();

	}

    }

}
