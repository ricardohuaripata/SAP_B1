package documento347;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class ejercicio347 {

    static Properties cfg = new Properties();
    static Connection conexionSBO;
    static Connection conexionDeclaraciones;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

	cargarCfg();
	conexion();

	if (conexion() == true) {

	    try {
		File fichero = new File(cfg.getProperty("dir_347.txt"));

		System.out.println("Escribrir anyo de declaracion");
		int anyo = sc.nextInt();

		long num_declaracion = leerNdeclaracion();

		escribirDeclaracion(fichero, anyo, num_declaracion);
		conexionSBO.close();
		conexionDeclaraciones.close();

	    } catch (Exception e) {
		System.err.println(e.getMessage());
	    }

	}

    }

    public static void cargarCfg() {
	try {
	    cfg.load(new BufferedReader(new FileReader("C:\\Users\\" + System.getProperty("user.name")
		    + "\\eclipse-workspace\\SAP_B1\\MATERIALES\\Configuracion.cfg")));
	} catch (Exception e) {
	    System.err.println(e.getMessage());
	}
    }

    public static boolean conexion() {
	try {
	    Class.forName(cfg.getProperty("driver"));
	    conexionSBO = DriverManager.getConnection(cfg.getProperty("urlSBO"), cfg.getProperty("user"),
		    cfg.getProperty("password"));
	    conexionDeclaraciones = DriverManager.getConnection(cfg.getProperty("urlDeclaraciones"),
		    cfg.getProperty("user"), cfg.getProperty("password"));
	    return true;
	} catch (Exception e) {
	    System.err.println(e.getMessage());
	    return false;
	}
    }

    public static long leerNdeclaracion() {
	boolean repetir = true;
	long num = 0;
	try {
	    Statement st = conexionDeclaraciones.createStatement();
	    ResultSet rs = null;
	    do {
		System.out.println("Escribir numero de declaracion");
		num = sc.nextLong();
		rs = st.executeQuery("select 1 from historia where numerodeclaracion=" + num);
		if (rs.next()) {
		    System.err.println("El numero de declaracion ya existe");
		} else
		    repetir = false;
	    } while (repetir == true);
	    st.close();
	    rs.close();
	} catch (SQLException e) {
	    System.err.println(e.getMessage());
	}
	return num;
    }

    public static void escribirDeclaracion(File salida, int anyo, long num_declaracion) {
	try {

	    BufferedWriter bw = new BufferedWriter(new FileWriter(salida, Charset.forName("ISO-8859-1")));

	    Statement st = conexionSBO.createStatement();
	    Statement st2 = conexionSBO.createStatement();

	    ResultSet rs = st.executeQuery("exec empresadeclarante "+anyo);
	    while (rs.next()) {

		Declarante declarante = new Declarante(anyo, rs.getString("nifdeclarante"),
			rs.getString("nombredeclarante"), "T", rs.getString("telefonocontacto"),
			rs.getString("personacontacto"), num_declaracion, " ", rs.getLong("numerodeclaracionanterior"),
			rs.getLong("cantidadtipo2"), " ", rs.getDouble("sumaoperaciones"), rs.getLong("cantidadtipo2"),
			" ", 0, " ", " ", " ", " ");

		bw.write(declarante.getCadenaRegistro());
		System.out.println(declarante.getCadenaRegistro());

		ResultSet rs2 = st2.executeQuery("exec declarados347 " + anyo);

		while (rs2.next()) {
		    Declarado declarado = new Declarado(anyo, declarante.getNif(), rs2.getString("NIF"), " ",
			    rs2.getString("Nombre"), "D", rs2.getInt("provincia"), rs2.getString("pais"), " ", "A",
			    rs2.getString("signo_anual"), rs2.getDouble("Total"), " ", " ", 0, " ", 0, 0,
			    // 1 **
			    rs2.getString("signo_anual"), rs2.getDouble("Total"), "0", 0,
			    // 2 **
			    rs2.getString("signo_anual"), rs2.getDouble("Total"), "0", 0,
			    // 3 **
			    rs2.getString("signo_anual"), rs2.getDouble("Total"), "0", 0,
			    // 4 **
			    rs2.getString("signo_anual"), rs2.getDouble("Total"), "0", 0,

			    rs2.getString("pais"), rs2.getString("NIF"), " ", "0", 0, " ");

		    bw.write("\n" + declarado.getCadenaRegistro());
		    System.out.println(declarado.getCadenaRegistro());

		}
		st2.close();
		rs2.close();
	    }

	    guardarDeclaracion(anyo, num_declaracion);

	    bw.close();
	    st.close();
	    rs.close();

	} catch (Exception e) {
	    System.err.println(e.getMessage());
	}

    }

    public static void guardarDeclaracion(int periodo, long numerodeclaracion) {

	try {
	    Statement st = conexionDeclaraciones.createStatement();
	    st.executeUpdate("insert into historia(declaracion,periodo,numerodeclaracion,tipo,fechageneracion)"
		    + "values ('347'," + periodo + "," + numerodeclaracion + ",'Inicial', getdate())");

	    st.close();
	} catch (SQLException e) {
	    System.err.println(e.getMessage());

	}
    }

}
