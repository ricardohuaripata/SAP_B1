package documento347;

import java.util.Properties;
import java.io.*;

public class Configuracion {

    public static void main(String[] args) {

	String usuario = System.getProperty("user.name");

	Properties cfg = new Properties();

	cfg.setProperty("db", "SBODemoES");
	cfg.setProperty("server", "localhost");
	cfg.setProperty("user", "sa");
	cfg.setProperty("password", "12345Ab##");
	cfg.setProperty("port", "1433");
	cfg.setProperty("url", "jdbc:sqlserver://" + cfg.getProperty("server") + ":" + cfg.getProperty("port")
		+ ";databaseName=" + cfg.getProperty("db") + ";encrypt=false");
	cfg.setProperty("dir_347", "C:\\Users\\" + usuario + "\\eclipse-workspace\\SAP_B1\\MATERIALES\\347.dat");
	
	cfg.setProperty("consulta347", 
		"select\r\n"
		+ "	YEAR(T0.RefDate) as Ejercicio,\r\n"
		+ "	IC.LicTradNum as NIF,\r\n"
		+ "	IC.CardName	AS Nombre,\r\n"
		+ "	IC.State1 as Provincia1,\r\n"
		+ "	IC.ZipCode as CP,\r\n"
		+ "	IIF(IC.Country='ES',\r\n"
		+ "	    iif(ISNUMERIC(IC.ZipCode)=1,FORMAT(CONVERT(INT, IC.ZipCode)/1000,'00'),''),\r\n"
		+ "		'99') as Provincia,\r\n"
		+ "	IIF(IC.Country='ES','',IC.Country) as Pais,\r\n"
		+ "\r\n"
		+ "	iif(TI.Category='I','A','B') as TipoIva,\r\n"
		+ "	sum(iif(Debit-Credit=0,\r\n"
		+ "							SIGN(T0.LocTotal),\r\n"
		+ "							iif(TI.Category='I',-1,1)*SIGN(T1.Credit-T1.Debit)\r\n"
		+ "			)*\r\n"
		+ "		(abs(T1.BaseSum)+abs(Debit-Credit))) as Total,\r\n"
		+ "	sum(T1.BaseSum)							AS TotalBase,\r\n"
		+ "	sum(SIGN(T1.BaseSum)*abs(Debit-Credit)) as TotalCuota\r\n"
		+ "FROM OJDT T0  (NOLOCK)\r\n"
		+ "	LEFT JOIN JDT1 T1 (NOLOCK) ON T0.TransId=T1.TransId\r\n"
		+ "	LEFT JOIN OCRD IC (NOLOCK) ON T1.ContraAct=IC.CardCode\r\n"
		+ "	left join OVTG TI (NOLOCK) ON T1.VatGroup=TI.Code\r\n"
		+ "WHERE \r\n"
		+ "YEAR(T0.RefDate)=2016 AND VatLine='Y' and EquVatRate=0\r\n"
		+ "	and TI.R349Code=0\r\n"
		+ "GROUP BY YEAR(T0.RefDate), IC.LicTradNum, IC.CardName,IC.State1, TI.Category, IC.Country, IC.State1, IC.ZipCode\r\n"
		+ "HAVING \r\n"
		+ "ABS(sum(iif(Debit-Credit=0,\r\n"
		+ "							SIGN(T0.LocTotal),\r\n"
		+ "							iif(TI.Category='I',-1,1)*SIGN(T1.Credit-T1.Debit)\r\n"
		+ "			)*\r\n"
		+ "		(abs(T1.BaseSum)+abs(Debit-Credit))))>3005.06");

	cfg.setProperty("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");

	try {

	    cfg.store(
		    new FileOutputStream(
			    "C:\\Users\\" + usuario + "\\eclipse-workspace\\SAP_B1\\MATERIALES\\Configuracion.cfg"),
		    "Fichero de configuracion");

	    System.out.println("Fichero creado");

	} catch (FileNotFoundException e) {
	    e.printStackTrace();

	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}