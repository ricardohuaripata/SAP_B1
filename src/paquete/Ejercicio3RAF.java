package paquete;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio3RAF {

    static RandomAccessFile raf;

    public static void main(String[] args) throws IOException { // 79-84 examen

	Scanner sc = new Scanner(System.in);

	File f = new File("C:\\files\\productos.dat");

	raf = new RandomAccessFile(f, "rw");

	int codproducto;
	String nombre;
	double precio;
	long pos = f.length();
	
	for (int i = 0; i < 12; i++) {

	    System.out.print("\nCodigo del producto: ");
	    codproducto = sc.nextInt();
	    sc.nextLine();

	    System.out.print("Nombre del producto: ");
	    nombre = sc.nextLine();

	    StringBuffer nombrebuff = new StringBuffer(nombre);
	    nombrebuff.setLength(30);

	    System.out.print("Precio del producto: ");
	    precio = sc.nextDouble();

	    pos += (4 + (2 * 30) + 8);

	    guardarProducto(pos, codproducto, nombrebuff, precio);

	}

	raf.close();

    }

    public static void guardarProducto(long pos, int codproducto, StringBuffer nombre, double precio) {

	try {
	    raf.seek(pos);
	    raf.writeInt(codproducto);
	    raf.writeChars(nombre.toString());
	    raf.writeDouble(precio);

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    // metodo para preguntar al usuario si quiere modificar el campo de un registro

}
