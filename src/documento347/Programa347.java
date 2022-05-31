package documento347;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class Programa347 {

    static Properties cfg = new Properties();
    static Connection conexion;

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

	int opcionMenu;

	String dir_configuracion = "C:\\Users\\" + System.getProperty("user.name")
		+ "\\eclipse-workspace\\SAP_B1\\MATERIALES\\Configuracion.cfg";

	cfg.load(new BufferedReader(new FileReader(dir_configuracion)));

	Class.forName(cfg.getProperty("driver"));

	conexion = DriverManager.getConnection(cfg.getProperty("url"), cfg.getProperty("user"),
		cfg.getProperty("password"));

	System.out.println("\nConexion a la base de datos '" + cfg.getProperty("db") + "' establecida");

	do {

	    opcionMenu = menu(0, 2);

	    switch (opcionMenu) {

	    case 1:
		metodo();
		break;

	    case 2:
		escribirFichero2();
		break;

	    }

	} while (opcionMenu != 0);

	conexion.close();

	System.out.println("FIN DEL PROGRAMA");

    }

    public static int menu(int min, int max) {
	Scanner sc = new Scanner(System.in);
	int opcion = 0;
	boolean repetir;

	System.out.println("\n=== MENU ====");
	System.out.println("1 - opcion");
	System.out.println("2 - opcion");

	do {
	    repetir = false;
	    try {
		System.out.print("Seleccionar Opcion: ");
		opcion = sc.nextInt();
		if (opcion < min || opcion > max) {
		    System.err.println("Debes elegir una opcion entre " + min + " y " + max);
		    repetir = true;
		}
	    } catch (Exception e) {
		System.err.println("Error, vuelve a intentarlo");
		repetir = true;
	    } finally {
		sc.nextLine();
	    }

	} while (repetir == true);
	return opcion;

    }

    public static LocalDate leerFechaValida(String mensaje, String formatoFecha) {

	Scanner sc = new Scanner(System.in);
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern(formatoFecha);
	boolean valido = false;
	LocalDate fechaValida = null;

	while (valido == false) {

	    try {
		System.out.print(mensaje + "(" + formatoFecha + "): ");
		String stringFecha = sc.nextLine();
		fechaValida = LocalDate.parse(stringFecha, fmt);
		valido = true;
	    } catch (DateTimeParseException e) {
		System.err.println("INTRODUZCA UNA FECHA VALIDA CON EL FORMATO(" + formatoFecha + ")");
	    }

	}
	return fechaValida;
    }

    public static void consulta() {

	Scanner sc = new Scanner(System.in);
	String sql = "select * from OADM";

	try {

	    Statement st = conexion.createStatement();

	    ResultSet rs = st.executeQuery(sql);
	    while (rs.next()) {
		System.out.println(rs.getString(1));

	    }

	    st.close();
	    rs.close();

	} catch (SQLException e) {
	    System.out.println(e);
	} catch (InputMismatchException e) {
	    System.out.println(e);
	} catch (Exception e) {
	    System.out.println(e);
	}

    }

    public static void metodo() {

	Scanner sc = new Scanner(System.in);

	LocalDate fechaGeneracion = leerFechaValida("Fecha de generacion del fichero", "yyyy-MM-dd");
	int anyoGeneracion = fechaGeneracion.getYear();
	System.out.println("Año: " + anyoGeneracion);

    }

    public static void escribirFichero() {

	try {

	    BufferedWriter writer = new BufferedWriter(new FileWriter(cfg.getProperty("dir_347")));

	    writer.write("0000000000000000000000001");
	    writer.newLine();
	    writer.write("0000000000000000000000001");
	    writer.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
    
    public static void escribirFichero2() {

	try {
	    
	    File file = new File("C:\\Users\\" + System.getProperty("user.name") + "\\eclipse-workspace\\SAP_B1\\MATERIALES\\347.dat");
		
	    RandomAccessFile raf = new RandomAccessFile(file,"rw");
	    
	    raf.writeInt(1);
	    raf.writeDouble(3.95);
	    raf.writeBoolean(false);
	    
	    // boolean = 1, int = 4, double = 8, char = 2, string = 2*length
	    // 4 + 8 + 1 = 13
	    // primera posicion es int por tanto empieza en 0 y termina en 3, 
	    // segunda posicion es double por lo que empieza en 4 y termina en 11
	    raf.seek(0);
	    System.out.println(raf.readInt());
	    raf.seek(4);
	    System.out.println(raf.readDouble());
	    raf.seek(12);
	    System.out.println(raf.readBoolean());
	    
	    raf.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
