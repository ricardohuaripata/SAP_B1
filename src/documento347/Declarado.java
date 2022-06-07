package documento347;

public class Declarado {

    private int longitud[] = { 1, 3, 4, 9, 9, 9, 40, 1, 2, 2, 1, 1, 1, 15, 1, 1, 15, 1, 15, 4, 1, 15, 1, 15, 1, 15, 1,
	    15, 1, 15, 1, 15, 1, 15, 1, 15, 2, 15, 1, 1, 1, 1, 15, 201 };

    private StringBuffer atributo[] = new StringBuffer[longitud.length];
    private StringBuffer cadenaRegistro = new StringBuffer();

    public Declarado(int ejercicio, String nif_declarante, String nif, String nif_representante, String razon_social,
	    String tipo_hoja, int cod_provincia, String cod_pais, String empty_1, String clave_op,
	    String signo_total_op, double total_anual, String op_seguro, String arrendamientos, double recibido_efectivo,
	    String signo_total_inm, double total_comp_vent_inm, int ejercicio_op_efectivo, String t1_signo_total,
	    double t1_total, String t1_signo_total_inm, double t1_total_inm, String t2_signo_total, double t2_total,
	    String t2_signo_total_inm, double t2_total_inm, String t3_signo_total, double t3_total,
	    String t3_signo_total_inm, double t3_total_inm, String t4_signo_total, double t4_total,
	    String t4_signo_total_inm, double t4_total_inm, String criterio_caja, String sujeto_pasivo,
	    String bienes_vinculados, String signo_total_anual_criterio_caja, double total_anual_criterio_caja,
	    String empty_2) {

	this.atributo[0] = new StringBuffer("2");
	this.atributo[1] = new StringBuffer("347");
	this.atributo[2] = new StringBuffer(String.valueOf(ejercicio));
	this.atributo[3] = new StringBuffer(fmtAlf(nif_declarante));
	this.atributo[4] = new StringBuffer(fmtAlf(nif));
	this.atributo[5] = new StringBuffer(fmtAlf(nif_representante));
	this.atributo[6] = new StringBuffer(fmtAlf(razon_social));
	this.atributo[7] = new StringBuffer(fmtAlf(tipo_hoja));
	this.atributo[8] = new StringBuffer(fmtNum(cod_provincia, 2));
	this.atributo[9] = new StringBuffer(fmtAlf(cod_pais));
	this.atributo[10] = new StringBuffer(empty_1);
	this.atributo[11] = new StringBuffer(fmtAlf(clave_op));
	this.atributo[12] = new StringBuffer(fmtAlf(signo_total_op));
	this.atributo[13] = new StringBuffer(fmtDec(total_anual, 15));
	this.atributo[14] = new StringBuffer(fmtAlf(op_seguro));
	this.atributo[15] = new StringBuffer(fmtAlf(arrendamientos));
	this.atributo[16] = new StringBuffer(fmtDec(recibido_efectivo, 15));
	this.atributo[17] = new StringBuffer(fmtAlf(signo_total_inm));
	this.atributo[18] = new StringBuffer(fmtDec(total_comp_vent_inm, 15));
	this.atributo[19] = new StringBuffer(fmtNum(ejercicio_op_efectivo, 4));

	this.atributo[20] = new StringBuffer(fmtAlf(t1_signo_total));
	this.atributo[21] = new StringBuffer(fmtDec(t1_total, 15));
	this.atributo[22] = new StringBuffer(fmtAlf(t1_signo_total_inm));
	this.atributo[23] = new StringBuffer(fmtDec(t1_total_inm, 15));

	this.atributo[24] = new StringBuffer(fmtAlf(t2_signo_total));
	this.atributo[25] = new StringBuffer(fmtDec(t2_total, 15));
	this.atributo[26] = new StringBuffer(fmtAlf(t2_signo_total_inm));
	this.atributo[27] = new StringBuffer(fmtDec(t2_total_inm, 15));

	this.atributo[28] = new StringBuffer(fmtAlf(t3_signo_total));
	this.atributo[29] = new StringBuffer(fmtDec(t3_total, 15));
	this.atributo[30] = new StringBuffer(fmtAlf(t3_signo_total_inm));
	this.atributo[31] = new StringBuffer(fmtDec(t3_total_inm, 15));

	this.atributo[32] = new StringBuffer(fmtAlf(t4_signo_total));
	this.atributo[33] = new StringBuffer(fmtDec(t4_total, 15));
	this.atributo[34] = new StringBuffer(fmtAlf(t4_signo_total_inm));
	this.atributo[35] = new StringBuffer(fmtDec(t4_total_inm, 15));

	this.atributo[36] = new StringBuffer(fmtAlf(cod_pais));
	this.atributo[37] = new StringBuffer(fmtAlf(nif));
	this.atributo[38] = new StringBuffer(fmtAlf(criterio_caja));
	this.atributo[39] = new StringBuffer(fmtAlf(sujeto_pasivo));
	this.atributo[40] = new StringBuffer(fmtAlf(bienes_vinculados));
	this.atributo[41] = new StringBuffer(fmtAlf(signo_total_anual_criterio_caja));
	this.atributo[42] = new StringBuffer(fmtDec(total_anual_criterio_caja, 15));
	this.atributo[43] = new StringBuffer(empty_2);

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
