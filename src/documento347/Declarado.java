package documento347;

public class Declarado {

    private int longitud[] = { 1, 3, 4, 9, 9, 9, 40, 1, 2, 2, 1, 1, 1, 15, 1, 1, 15, 1, 15, 4, 1, 15, 1, 15, 1, 15, 1,
	    15, 1, 15, 1, 15, 1, 15, 1, 15, 2, 15, 1, 1, 1, 1, 15, 201 };

    private StringBuilder atributo[] = new StringBuilder[longitud.length];

    public Declarado(int ejercicio, String nif_declarante, String nif, String nif_representante, String razon_social,
	    String tipo_hoja, int cod_provincia, String cod_pais, String empty_1, String clave_op,
	    String signo_total_op, long total_anual, String op_seguro, String arrendamientos, long recibido_efectivo,
	    String signo_total_inm, long total_comp_vent_inm, int ejercicio_op_efectivo, String t1_signo_total,
	    long t1_total, String t1_signo_total_inm, long t1_total_inm, String t2_signo_total, long t2_total,
	    String t2_signo_total_inm, long t2_total_inm, String t3_signo_total, long t3_total,
	    String t3_signo_total_inm, long t3_total_inm, String t4_signo_total, long t4_total,
	    String t4_signo_total_inm, long t4_total_inm, String criterio_caja, String sujeto_pasivo,
	    String bienes_vinculados, String signo_total_anual_criterio_caja, long total_anual_criterio_caja,
	    String empty_2) {

	this.atributo[0] = new StringBuilder("2");
	this.atributo[1] = new StringBuilder("347");
	this.atributo[2] = new StringBuilder(String.format("%04d", ejercicio));
	this.atributo[3] = new StringBuilder(nif_declarante);
	this.atributo[4] = new StringBuilder(nif);
	this.atributo[5] = new StringBuilder(nif_representante);
	this.atributo[6] = new StringBuilder(razon_social);
	this.atributo[7] = new StringBuilder(tipo_hoja);
	this.atributo[8] = new StringBuilder(String.format("%02d", cod_provincia));
	this.atributo[9] = new StringBuilder(cod_pais);
	this.atributo[10] = new StringBuilder(empty_1);
	this.atributo[11] = new StringBuilder(clave_op);
	this.atributo[12] = new StringBuilder(signo_total_op);
	this.atributo[13] = new StringBuilder(String.format("%015d", total_anual));
	this.atributo[14] = new StringBuilder(op_seguro);
	this.atributo[15] = new StringBuilder(arrendamientos);
	this.atributo[16] = new StringBuilder(String.format("%015d", recibido_efectivo));
	this.atributo[17] = new StringBuilder(signo_total_inm);
	this.atributo[18] = new StringBuilder(String.format("%015d", total_comp_vent_inm));
	this.atributo[19] = new StringBuilder(String.format("%04d", ejercicio_op_efectivo));

	this.atributo[20] = new StringBuilder(t1_signo_total);
	this.atributo[21] = new StringBuilder(String.format("%015d", t1_total));
	this.atributo[22] = new StringBuilder(t1_signo_total_inm);
	this.atributo[23] = new StringBuilder(String.format("%015d", t1_total_inm));

	this.atributo[24] = new StringBuilder(t2_signo_total);
	this.atributo[25] = new StringBuilder(String.format("%015d", t2_total));
	this.atributo[26] = new StringBuilder(t2_signo_total_inm);
	this.atributo[27] = new StringBuilder(String.format("%015d", t2_total_inm));

	this.atributo[28] = new StringBuilder(t3_signo_total);
	this.atributo[29] = new StringBuilder(String.format("%015d", t3_total));
	this.atributo[30] = new StringBuilder(t3_signo_total_inm);
	this.atributo[31] = new StringBuilder(String.format("%015d", t3_total_inm));

	this.atributo[32] = new StringBuilder(t4_signo_total);
	this.atributo[33] = new StringBuilder(String.format("%015d", t4_total));
	this.atributo[34] = new StringBuilder(t4_signo_total_inm);
	this.atributo[35] = new StringBuilder(String.format("%015d", t4_total_inm));

	this.atributo[36] = new StringBuilder(cod_pais);
	this.atributo[37] = new StringBuilder(nif);
	this.atributo[38] = new StringBuilder(criterio_caja);
	this.atributo[39] = new StringBuilder(sujeto_pasivo);
	this.atributo[40] = new StringBuilder(bienes_vinculados);
	this.atributo[41] = new StringBuilder(signo_total_anual_criterio_caja);
	this.atributo[42] = new StringBuilder(String.format("%015d", total_anual_criterio_caja));
	this.atributo[43] = new StringBuilder(empty_2);

	for (int i = 0; i < atributo.length; i++) {
	    atributo[i].setLength(longitud[i]);
	}

    }

    @Override
    public String toString() {
	return atributo[0].toString() + atributo[1].toString() + atributo[2].toString() + atributo[3].toString()
		+ atributo[4].toString() + atributo[5].toString() + atributo[6].toString() + atributo[7].toString()
		+ atributo[8].toString() + atributo[9].toString() + atributo[10].toString() + atributo[11].toString()
		+ atributo[12].toString() + atributo[13].toString() + atributo[14].toString() + atributo[15].toString()
		+ atributo[16].toString() + atributo[17].toString() + atributo[18].toString() + atributo[19].toString()
		+ atributo[20].toString() + atributo[21].toString() + atributo[22].toString() + atributo[23].toString()
		+ atributo[24].toString() + atributo[25].toString() + atributo[26].toString() + atributo[27].toString()
		+ atributo[28].toString() + atributo[29].toString() + atributo[30].toString() + atributo[31].toString()
		+ atributo[32].toString() + atributo[33].toString() + atributo[34].toString() + atributo[35].toString()
		+ atributo[36].toString() + atributo[37].toString() + atributo[38].toString() + atributo[39].toString()
		+ atributo[40].toString() + atributo[41].toString() + atributo[42].toString() + atributo[43].toString();
    }

}
