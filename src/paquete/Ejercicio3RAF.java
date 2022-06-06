package paquete;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio3RAF {

    static Scanner sc = new Scanner(System.in);
    static Scanner sc2 = new Scanner(System.in);
    static final int ocupacion = 72;

    public static void main(String[] args) {

	File file = new File("C:\\ficheros\\productos.dat");

	System.out.println("Cuanto productos vas a insertar?");
	int cantidad = sc.nextInt();

	insertarProducto(cantidad, file);

	System.out.println("Desea modificar algun campo? (Y/N)");
	String respuesta = sc2.nextLine();

	if (respuesta.equalsIgnoreCase("Y")) {

	    System.out.println("Introduce el orden del producto que deseas modificar, actualmente hay "
		    + file.length() / ocupacion + " productos");

	    int pos = sc.nextInt();

	    modificarProducto((pos * ocupacion) - ocupacion, file);

	}
    }

    public static void insertarProducto(int cantidad, File file) {

	try {

	    RandomAccessFile raf = new RandomAccessFile(file, "rw");

	    long pos = file.length();

	    for (int i = 0; i < cantidad; i++) {

		raf.seek(pos);
		System.out.println("Codigo del producto");
		int codproducto = sc.nextInt();

		System.out.println("Nombre del producto");
		StringBuffer nombre = new StringBuffer(sc2.nextLine());
		nombre.setLength(30);

		System.out.println("Precio del producto");
		double precio = sc.nextDouble();

		raf.writeInt(codproducto);
		raf.writeChars(nombre.toString());
		raf.writeDouble(precio);

		pos += ocupacion;

	    }

	    raf.close();

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

    }

    public static void modificarProducto(long pos, File file) {

	try {

	    int campoModificacion[] = { 0, 4, 60 };
	    RandomAccessFile raf = new RandomAccessFile(file, "rw");
	    int posCampo;

	    do {
		System.out.println("Introduce orden del campo que vas a modificar, elige entre 1 y 3");
		posCampo = sc.nextInt();
	    } while (posCampo < 1 || posCampo > 3);

	    pos += campoModificacion[posCampo - 1];

	    raf.seek(pos);

	    switch (posCampo) {

	    case 1:
		System.out.println("Codigo del producto");
		int codproducto = sc.nextInt();
		raf.writeInt(codproducto);
		break;

	    case 2:
		System.out.println("Nombre del producto");
		StringBuffer nombre = new StringBuffer(sc2.nextLine());
		nombre.setLength(30);
		raf.writeChars(nombre.toString());
		break;

	    case 3:
		System.out.println("Precio del producto");
		double precio = sc.nextDouble();
		raf.writeDouble(precio);
		break;

	    }

	    raf.close();

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

    }

    // metodo para preguntar al usuario si quiere modificar el campo de un registro

}
