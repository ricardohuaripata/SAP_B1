package documento347;

public class Declarante {

    private int longitud[] = { 1, 3, 4, 9, 40, 1, 9, 40, 13, 1, 1, 13, 9, 1, 15, 9, 1, 15, 205, 9, 88, 13 };
    private StringBuilder atributo[] = new StringBuilder[longitud.length];

    public Declarante(int ejercicio, String nif, String nombre, String tipo_soporte, String tlf_contacto,
	    String pers_contacto, long num_declaracion, String tipo_declaracion, long num_declaracion_ant,
	    long cant_registro_t2, String signo_sum_ops, long sum_ops, long cant_registro_t2_alqs,
	    String signo_sum_alqs, long sum_alqs, String empty_1, String nif_representante, String empty_2,
	    String sello_electronico) {

	this.atributo[0] = new StringBuilder("1");
	this.atributo[1] = new StringBuilder("347");
	this.atributo[2] = new StringBuilder(String.format("%04d", ejercicio));
	this.atributo[3] = new StringBuilder(nif);
	this.atributo[4] = new StringBuilder(nombre);
	this.atributo[5] = new StringBuilder(tipo_soporte);
	this.atributo[6] = new StringBuilder(tlf_contacto);
	this.atributo[7] = new StringBuilder(pers_contacto);
	this.atributo[8] = new StringBuilder(String.format("%013d", num_declaracion));
	this.atributo[9] = new StringBuilder(tipo_declaracion);
	this.atributo[10] = new StringBuilder(tipo_declaracion);
	this.atributo[11] = new StringBuilder(String.format("%013d", num_declaracion_ant));
	this.atributo[12] = new StringBuilder(String.format("%09d", cant_registro_t2));
	this.atributo[13] = new StringBuilder(signo_sum_ops);
	this.atributo[14] = new StringBuilder(String.format("%015d", sum_ops));
	this.atributo[15] = new StringBuilder(String.format("%09d", cant_registro_t2_alqs));
	this.atributo[16] = new StringBuilder(signo_sum_alqs);
	this.atributo[17] = new StringBuilder(String.format("%015d", sum_alqs));
	this.atributo[18] = new StringBuilder(empty_1);
	this.atributo[19] = new StringBuilder(nif_representante);
	this.atributo[20] = new StringBuilder(empty_2);
	this.atributo[21] = new StringBuilder(sello_electronico);

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
		+ atributo[20].toString() + atributo[21].toString();
    }

    public String getNif() {
	return atributo[3].toString();
    }

}
