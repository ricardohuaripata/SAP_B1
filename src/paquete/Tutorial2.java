package paquete;

import java.util.Scanner;
import java.io.*;

public class Tutorial2 {

    static RandomAccessFile archivo;

    public static void main(String[] args) {
	
	long largo = 1000000000;
	
	Scanner scanner = new Scanner(System.in);

	try {
	    archivo = new RandomAccessFile("Personas.dat", "rw");
	} catch (IOException e) {

	}

	int opcion;

	do {

	    System.out.print("1. Escribir en el archivo binario\n" + "2. Buscar persona en el archivo\n"
		    + "3. Mostrar todos los datos guardados\n" + "4. Salir\n" + "Escriba la opcion aqui: ");
	    opcion = scanner.nextInt();
	    System.out.println();

	    switch (opcion) {

	    case 1: {

		String nombre;
		int edad;
		char sexo;
		System.out.print("Escriba su nombre: ");
		nombre = scanner.next();
		System.out.print("Escriba su edad: ");
		edad = scanner.nextInt();
		System.out.print("Escriba su sexo: ");
		sexo = scanner.next().charAt(0);
		try {
		    escribir(nombre, edad, sexo);
		} catch (IOException e) {

		}
		System.out.println("Se han ingresado los datos correctamente!");
		break;

	    }

	    case 2: {
		String nombre;
		System.out.print("Escriba el nombre que desea buscar: ");
		nombre = scanner.next();
		try {
		    if (buscarPersona(nombre))
			System.out.println("Se ha encontrado el nombre en el archivo");
		    else
			System.out.println("No se ha encontrado el nombre en el archivo");
		} catch (IOException e) {

		}

		break;
	    }

	    case 3: {
		try {
		    imprimir();
		} catch (IOException e) {

		}
		break;
	    }

	    default: {

		if (opcion != 4)
		    System.out.println("Opcion no valida");
	    }

	    }

	} while (opcion != 4);

    }

    static void escribir(String nombre, int edad, char sexo) throws IOException {

	archivo.seek(archivo.length()); // el puntero se posiciona al final
	archivo.writeUTF(nombre);
	archivo.writeInt(edad);
	archivo.writeChar(sexo);

    }

    static boolean buscarPersona(String nombre) throws IOException {

	archivo.seek(0);
	while (archivo.getFilePointer() < archivo.length()) {
	    if (archivo.readUTF().equals(nombre))
		return true;
	    archivo.skipBytes(6);
	}
	return false;

    }

    static void imprimir() throws IOException {

	archivo.seek(0);
	while (archivo.getFilePointer() < archivo.length()) {
	    System.out.println(archivo.readUTF());
	    System.out.println(archivo.readInt());
	    System.out.println(archivo.readChar());
	}

    }
    
    public static void escribirFichero2() {

	try {
	    
	    File file = new File("");
		
	    RandomAccessFile raf = new RandomAccessFile(file,"rw");
	    
	    raf.writeLong(922337203);
	    raf.writeDouble(3.95);
	    raf.writeBoolean(false);
	    
	    // boolean = 1, int = 4, double = 8, char = 2, string = 2*length
	    // 4 + 8 + 1 = 13
	    // primera posicion es int por tanto empieza en 0 y termina en 3, 
	    // segunda posicion es double por lo que empieza en 4 y termina en 11
	    raf.seek(0);
	    System.out.println(raf.readLong());
	    raf.seek(8);
	    System.out.println(raf.readDouble());
	    raf.seek(12);
	    System.out.println(raf.readBoolean());
	    
	    raf.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
	
    }

}