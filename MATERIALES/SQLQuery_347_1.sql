select
	YEAR(T0.RefDate) as Ejercicio,
	IC.LicTradNum as NIF, 
	IC.CardName	AS Nombre,
--	IC.State1 as Provincia1,
--	IC.ZipCode as CP,
	IIF(IC.Country='ES',
	    iif(ISNUMERIC(IC.ZipCode)=1,FORMAT(CONVERT(INT, IC.ZipCode)/1000,'00'),''),
		'99') as Provincia,
	IIF(IC.Country='ES','',IC.Country) as Pais,

	iif(TI.Category='I','A','B') as TipoIva,
	sum(iif(Debit-Credit=0,
							SIGN(T0.LocTotal),
							iif(TI.Category='I',-1,1)*SIGN(T1.Credit-T1.Debit)
			)*
		(abs(T1.BaseSum)+abs(Debit-Credit))) as Total,
	sum(T1.BaseSum)							AS TotalBase,
	sum(SIGN(T1.BaseSum)*abs(Debit-Credit)) as TotalCuota
FROM OJDT T0  (NOLOCK)
LEFT JOIN JDT1 T1 (NOLOCK) ON T0.TransId=T1.TransId
LEFT JOIN OCRD IC (NOLOCK) ON T1.ContraAct=IC.CardCode
left join OVTG TI (NOLOCK) ON T1.VatGroup=TI.Code
WHERE 
YEAR(T0.RefDate)=2016 AND VatLine='Y' and EquVatRate=0
and  TI.R349Code=0
GROUP BY YEAR(T0.RefDate), IC.LicTradNum, IC.CardName,IC.State1, TI.Category, IC.Country, IC.State1, IC.ZipCode
HAVING 
ABS(sum(iif(Debit-Credit=0,
							SIGN(T0.LocTotal),
							iif(TI.Category='I',-1,1)*SIGN(T1.Credit-T1.Debit)
			)*
		(abs(T1.BaseSum)+abs(Debit-Credit))))>3005.06 
