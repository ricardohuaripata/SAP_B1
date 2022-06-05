
--consultas, tablas que intervienen

select * from OJDT --asientos
select * from JDT1 --apuntes
select * from OCRD --interlocutores
select * from OVTG --codigos de iva
select * from OADM --empresa





create database declaraciones

drop table historia

create table historia (
	id int IDENTITY(1,1) PRIMARY KEY,
	declaracion int not NULL, -- 347
	periodo int not NULL, --solo anyo de 4 digitos
	numerodeclaracion int not null, --numero 
	tipo VARCHAR(250) not NULL, --inicial, sustitutivo o complementario
	fechageneracion datetime not NULL --fecha y hora en la que se genero
)

insert into historia 
select 347, 2010 ,152441234,'Inicial',GETDATE()

SELECT * FROM historia

GO

--devolver cantidad de registros de tipo 2
create or alter FUNCTION dbo.CantidadRegistroTipo2()
returns int
AS
BEGIN

	declare @contador as int = (select DISTINCT COUNT(*) OVER ()
	FROM OJDT T0  (NOLOCK)
		LEFT JOIN JDT1 T1 (NOLOCK) ON T0.TransId=T1.TransId
		LEFT JOIN OCRD IC (NOLOCK) ON T1.ContraAct=IC.CardCode
		left join OVTG TI (NOLOCK) ON T1.VatGroup=TI.Code
	WHERE YEAR(T0.RefDate)=2016 AND VatLine='Y' and EquVatRate=0
		and TI.R349Code=0
	GROUP BY YEAR(T0.RefDate), IC.LicTradNum, IC.CardName,IC.State1, TI.Category, IC.Country, IC.State1, IC.ZipCode
	HAVING ABS(sum(iif(Debit-Credit=0,
	SIGN(T0.LocTotal),
	iif(TI.Category='I',-1,1)*SIGN(T1.Credit-T1.Debit))*(abs(T1.BaseSum)+abs(Debit-Credit))))>3005.06)
	RETURN @contador

END

select dbo.CantidadRegistroTipo2()

--devolver suma de totas las operaciones con los declarados
create or alter FUNCTION dbo.SumaTotalOperaciones()
returns DECIMAL(18,0)
AS
BEGIN

	declare @total as DECIMAL(18,2) = 0
	declare @i as DECIMAL(18,2)

	declare x cursor for
select
		sum(iif(Debit-Credit=0,
SIGN(T0.LocTotal),
iif(TI.Category='I',-1,1)*SIGN(T1.Credit-T1.Debit))*
(abs(T1.BaseSum)+abs(Debit-Credit))) as Total
	FROM OJDT T0  (NOLOCK)
		LEFT JOIN JDT1 T1 (NOLOCK) ON T0.TransId=T1.TransId
		LEFT JOIN OCRD IC (NOLOCK) ON T1.ContraAct=IC.CardCode
		left join OVTG TI (NOLOCK) ON T1.VatGroup=TI.Code
	WHERE 
YEAR(T0.RefDate)=2016 AND VatLine='Y' and EquVatRate=0
		and TI.R349Code=0
	GROUP BY YEAR(T0.RefDate), IC.LicTradNum, IC.CardName,IC.State1, TI.Category, IC.Country, IC.State1, IC.ZipCode
	HAVING 
ABS(sum(iif(Debit-Credit=0,
SIGN(T0.LocTotal),
iif(TI.Category='I',-1,1)*SIGN(T1.Credit-T1.Debit)
)*(abs(T1.BaseSum)+abs(Debit-Credit))))>3005.06

	open x
	FETCH NEXT FROM x into @i

	while @@FETCH_STATUS =0
	begin
		SET @total += @i
		FETCH NEXT FROM x into @i
	end

	close x
	deallocate x

	return @total

END


SELECT dbo.SumaTotalOperaciones()

create or alter procedure empresadeclarante
as

BEGIN

	select
		TaxIdNum as nifdeclarante,
		CompnyName as nombredeclarante,
		Phone1 as telefonocontacto,
		Manager as personacontacto,
		dbo.numeroDeclaracionAnterior() as numerodeclaracionanterior,
		dbo.CantidadRegistroTipo2() as cantidadtipo2,
		dbo.SumaTotalOperaciones() as sumaoperaciones

	FROM OADM

END

exec empresadeclarante

create or alter FUNCTION dbo.numeroDeclaracionAnterior() 
RETURNS VARCHAR(13)
AS
BEGIN
	RETURN (
	SELECT TOP(1) numerodeclaracion
	FROM declaraciones.dbo.historia
	ORDER BY fechageneracion DESC
		)
END


create or alter procedure declarados347
	@ejercicio as int
as

BEGIN
	select
		YEAR(T0.RefDate) as Ejercicio,
		isnull((IC.LicTradNum),' ') as NIF,
		isnull(IC.CardName,' ')	AS Nombre,
		IC.State1 as Provincia1,
		isnull(IC.ZipCode,' ') as CP,
		IIF(IC.Country='ES',
	    isnull(iif(ISNUMERIC(IC.ZipCode)=1,FORMAT(CONVERT(INT, IC.ZipCode)/1000,'00'),''),
		'99'),0) as Provincia,
		isnull(IIF(IC.Country='ES','',IC.Country),' ') as Pais,

		iif(TI.Category='I','A','B') as TipoIva,
		CONVERT(int, sum(iif(Debit-Credit=0,
							SIGN(T0.LocTotal),
							iif(TI.Category='I',-1,1)*SIGN(T1.Credit-T1.Debit)
			)*
		(abs(T1.BaseSum)+abs(Debit-Credit))) ) as Total,
		sum(T1.BaseSum)							AS TotalBase,
		sum(SIGN(T1.BaseSum)*abs(Debit-Credit)) as TotalCuota, 
		dbo.signo(sum(iif(Debit-Credit=0,
							SIGN(T0.LocTotal),
							iif(TI.Category='I',-1,1)*SIGN(T1.Credit-T1.Debit)
			)*(abs(T1.BaseSum)+abs(Debit-Credit)))) as signo_anual

	FROM OJDT T0  (NOLOCK)
		LEFT JOIN JDT1 T1 (NOLOCK) ON T0.TransId=T1.TransId
		LEFT JOIN OCRD IC (NOLOCK) ON T1.ContraAct=IC.CardCode
		left join OVTG TI (NOLOCK) ON T1.VatGroup=TI.Code
	WHERE 
YEAR(T0.RefDate)=@ejercicio AND VatLine='Y' and EquVatRate=0
		and TI.R349Code=0
	GROUP BY YEAR(T0.RefDate), IC.LicTradNum, IC.CardName,IC.State1, TI.Category, IC.Country, IC.State1, IC.ZipCode
	HAVING 
ABS(sum(iif(Debit-Credit=0,
							SIGN(T0.LocTotal),
							iif(TI.Category='I',-1,1)*SIGN(T1.Credit-T1.Debit)
			)*
		(abs(T1.BaseSum)+abs(Debit-Credit))))>3005.06

END

CREATE OR ALTER FUNCTION dbo.signo(@importe as int)
RETURNS VARCHAR(1)

as
BEGIN
	declare @signo as VARCHAR(1) = 	IIF(@importe > 0, ' ', 'N')
	RETURN @signo
END


EXEC declarados347 2020

SELECT getdate()