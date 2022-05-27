package paquete;

import java.io.File;
import java.io.FileFilter;

public class claseFile2 {

	public static void main(String[] args) {

		File directorio = new File("C:\\Users\\" + System.getProperty("user.name") + "\\eclipse-workspace");
		
		FileFilter fl = new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isFile();
			}
		};

	}

	public static void mostrarArchivos(File carpeta) {

		try {

			File ar[] = carpeta.listFiles();

			for (int i = 0; i < ar.length; i++) {

				System.out.println(ar[i]);

				if (ar[i].isDirectory())
					mostrarArchivos(ar[i]);

			}

		}

		catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
