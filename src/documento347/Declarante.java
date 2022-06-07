package documento347;

public class Declarante {

    private int longitud[] = { 1, 3, 4, 9, 40, 1, 9, 40, 13, 1, 1, 13, 9, 1, 15, 9, 1, 15, 205, 9, 88, 13 };
    private StringBuffer atributo[] = new StringBuffer[longitud.length];
    private StringBuffer cadenaRegistro = new StringBuffer();

    public Declarante(int ejercicio, String nif, String nombre, String tipo_soporte, String tlf_contacto,
	    String pers_contacto, long num_declaracion, String tipo_declaracion, long num_declaracion_ant,
	    long cant_registro_t2, String signo_sum_ops, double sum_ops, long cant_registro_t2_alqs,
	    String signo_sum_alqs, double sum_alqs, String empty_1, String nif_representante, String empty_2,
	    String sello_electronico) {

	this.atributo[0] = new StringBuffer("1");
	this.atributo[1] = new StringBuffer("347");
	this.atributo[2] = new StringBuffer(String.valueOf(ejercicio));
	this.atributo[3] = new StringBuffer(fmtAlf(nif));
	this.atributo[4] = new StringBuffer(fmtAlf(nombre));
	this.atributo[5] = new StringBuffer(fmtAlf(tipo_soporte));
	this.atributo[6] = new StringBuffer(fmtAlf(tlf_contacto));
	this.atributo[7] = new StringBuffer(fmtAlf(pers_contacto));
	this.atributo[8] = new StringBuffer(fmtNum(num_declaracion, 13));
	this.atributo[9] = new StringBuffer(fmtAlf(tipo_declaracion));
	this.atributo[10] = new StringBuffer(fmtAlf(tipo_declaracion));
	this.atributo[11] = new StringBuffer(fmtNum(num_declaracion_ant, 13));
	this.atributo[12] = new StringBuffer(fmtNum(cant_registro_t2, 9));
	this.atributo[13] = new StringBuffer(fmtAlf(signo_sum_ops));
	this.atributo[14] = new StringBuffer(fmtDec(sum_ops, 15));
	this.atributo[15] = new StringBuffer(fmtNum(cant_registro_t2_alqs, 9));
	this.atributo[16] = new StringBuffer(fmtAlf(signo_sum_alqs));
	this.atributo[17] = new StringBuffer(fmtDec(sum_alqs, 15));
	this.atributo[18] = new StringBuffer(empty_1);
	this.atributo[19] = new StringBuffer(fmtAlf(nif_representante));
	this.atributo[20] = new StringBuffer(empty_2);
	this.atributo[21] = new StringBuffer(sello_electronico);

	for (int i = 0; i < atributo.length; i++) {
	    if (atributo[i].length() != longitud[i]) {
		atributo[i].setLength(longitud[i]);
	    }
	    cadenaRegistro.append(atributo[i].toString());
	}

    }

    public String getCadenaRegistro() {
	return cadenaRegistro.toString();
    }

    public String getNif() {
	return atributo[3].toString();
    }

    public static String fmtDec(double decimal, int digitos) {
	String dec = String.format("%,.2f", decimal);
	return String.format("%0" + digitos + "d", Integer.parseInt(dec.replaceAll("[^a-zA-Z0-9]", "")));
    }

    public static String fmtNum(long numerico, int digitos) {
	return String.format("%0" + digitos + "d", numerico);
    }

    public static String fmtAlf(String cadena) {
	return cadena.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
    }

}
