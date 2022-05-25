package paquete;

import java.io.File;
import java.io.FilenameFilter;

public class Filtro implements FilenameFilter{
	
	String extension;
	
	Filtro(String text) {
		this.extension=text;
	}

	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		return name.endsWith(".java");
	}

}
