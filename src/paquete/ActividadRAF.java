package paquete;

import java.util.Scanner;
import java.io.*;

public class ActividadRAF {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String dir = "C:\\Users\\RicardoAlonzoHuaripa\\eclipse-workspace\\SAP_B1\\MATERIALES\\randomaccessfile.dat";
		RandomAccessFile fichero = null;
		int pos;
		int numero;
		long size;

		try {

			File file = new File(dir);
			fichero = new RandomAccessFile(file, "rw");

			if (fichero.length() != 0) {

				fichero.writeInt(1);
				fichero.writeInt(2);
				fichero.writeInt(3);

				size = fichero.length() / 4;

				System.out.println("El fichero tiene " + size + " enteros");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			try {
				if (fichero != null) {
					fichero.close();
				}

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}

	}

}
