package paquete;

import java.io.*;

public class MetodosFile2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File dir = new File("C:\\CLINICA");

		String ar[] = dir.list();

		for (int i = 0; i < ar.length; i++) {
			System.out.println(ar[i]);
		}

	}

}
