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
	System.out.println("1 - ");
	System.out.println("2 - ");

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

    public static void metodo() {

	Scanner sc = new Scanner(System.in);
	Statement st;
	ResultSet rs;

	String sql = cfg.getProperty("consulta347");
	LocalDate fechaGeneracion = leerFechaValida("Fecha de generacion del fichero", "yyyy-MM-dd");
	int anyoGeneracion = fechaGeneracion.getYear();

	try {

	    File file = new File(cfg.getProperty("dir_347"));

	    st = conexion.createStatement();

	    RandomAccessFile raf = new RandomAccessFile(file, "rw");

	    rs = st.executeQuery(sql);

	    while (rs.next()) {
		
		System.out.println("------------");
		System.out.println("INTERLOCUTOR");
		System.out.println(rs.getInt("Ejercicio"));
		System.out.println(rs.getString("NIF"));
		System.out.println(rs.getString("Nombre"));
		System.out.println(rs.getString("Provincia"));
		System.out.println(rs.getString("Pais"));
		System.out.println(rs.getString("TipoIva"));
		System.out.println(rs.getDouble("Total"));
		System.out.println(rs.getDouble("TotalBase"));
		System.out.println(rs.getDouble("TotalCuota"));

	    }

	} catch (Exception e) {
	    System.err.println(e.getMessage());
	}

    }

}
