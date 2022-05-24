declare @viapago			NVARCHAR(30)		--sirve para asignar el banco por el que se presenta
DECLARE @efectos_sel		varchar(max)
declare @fechavtod			date
declare @fechavtoh			date
set @viapago		= 'Transf_pago'	
set @efectos_sel	='11,35' --EFECTOS CON DATOS VALIDADOS
set @fechavtod		= CONVERT(datetime,'20150101',112)
set @fechavtoh		= CONVERT(datetime,'20221231',112)

SELECT 
	'PRE'+format(getdate(),'yyyyMMddHHmmss')					as "GrpHdr/MsgId"
	,format(getdate(),'yyyy-MM-dd-THH:mm:ss')					as "GrpHdr/CreDtTm"
--	,count(*)													as "GrpHdr/NbOfTxs"
--	,format(sum([BoeSum]),'#.00','en-US')						as "GrpHdr/CtrlSum"
	,Em.CompnyName												as "GrpHdr/InitgPty/Nm"
	,Em.CompnyAddr												as "GrpHdr/InitgPty/PstlAdr/StrtNm"
	,Em1.ZipCode												as "GrpHdr/InitgPty/PstlAdr/PstCd"
	,Em1.County													as "GrpHdr/InitgPty/PstlAdr/TwnNm"
	,Em.Country													as "GrpHdr/InitgPty/PstlAdr/Ctry"
	,Em.SEPACredID as "GrpHdr/InitgPty/Id/OrgId/Othr/Id"

	,'NOTPROVIDED'												as "PmtInf/PmtInfId"
    ,'TR'														as "PmtInf/PmtMtd"
	,'true'														as "PmtInf/BtchBookg"
--	,count(*)													as "PmtInf/NbOfTxs"
--	,format(sum(EF.[BoeSum]),'#.00','en-US')					as "PmtInf/CtrlSum"
	,'SEPA'														as "PmtInf/PmtTpInf/SvcLvl/Cd"
	,format(EF.DueDate,'yyyy-MM-dd')							as "PmtInf/ReqdExctnDt"
	,Em.CompnyName												as "PmtInf/Dbtr/Nm"
	,Em1.ZipCode												as "PmtInf/Dbtr/PstlAdr/PstCd"
	,Em.Country													as "PmtInf/Dbtr/PstlAdr/Ctry"
	,Em.CompnyAddr												as "PmtInf/Dbtr/PstlAdr/AdrLine"
	,Em1.County													as "PmtInf/Dbtr/PstlAdr/AdrLine"
	,Em.SEPACredID												as "PmtInf/Dbtr/Id/OrgId/Othr/Id"
	,VP.DflIBAN													as "PmtInf/DbtrAcct/Id/IBAN"
	,'EUR'														as "PmtInf/DbtrAcct/Ccy"
	,VP.DflSwift												as "PmtInf/DbtrAgt/FinInstnId/BIC"
	,'ES'														as "PmtInf/DbtrAgt/FinInstnId/PstlAdr/Ctry"
	,'SLEV'														as "PmtInf/ChrgBr"


		,EF.PmntNum									as "PmtId/EndToEndId"
		,'EUR'										AS "AmtId/InstdAmt/@Ccy"
		,format(EF.[BoeSum],'#.00','en-US')			as "AmtId/InstdAmt"
		,ICban.SwiftNum								as "CdtrAgt/FinInstnId/BIC"
		,'ES'										as "CdtrAgt/FinInstnId/PstlAdr/Ctry"
		,IC.CardName								as "Cdtr/Nm"
		,IC.Country									as "Cdtr/PstlAdr/Ctry"
		,IC.Address									as "Cdtr/PstlAdr/AdrLine"
		,IC.ZipCode+' '+IC.City+' ('+PR.Name+')'	as "Cdtr/PstlAdr/AdrLine"
		,IC.LicTradNum								as  "Cdtr/Id/OrgId/Othr/Id"
		,IcBan.IBAN									as "CdtrAcct/Id/IBAN" --podria no existir ya por cambio de cuenta y no podria generarse el xml con este registro
		,"RmtInf/Ustrd"=
RTRIM(
REPLACE((SELECT 
iif(InvType=18,convert(varchar,FA.docnum)+'/EUR'+FORMAT(FA.DocTotal,' #.00','en-US')+format(FA.Docdate,' yyyy-MM-dd'), 
iif(InvType=19,convert(varchar,AB.docnum)+'/EUR'+FORMAT(AB.DocTotal,' #.00','en-US')+format(AB.Docdate,' yyyy-MM-dd'),''))+'     ' 
from OBOE ALIAS
left join OVPM CO ON EF.BoeKey=CO.BoeAbs 
left join VPM2 COD ON COD.DocNum=CO.DocEntry
left join OPCH FA on FA.docentry = COD.DocEntry AND FA.ObjType=cod.InvType
left join ORPC AB on AB.docentry = COD.DocEntry AND AB.ObjType=cod.InvType
left join OJDT AP on AP.TransId  = COD.DocEntry AND AP.TransType=cod.InvType
WHERE ALIAS.BOEKEY=EF.BOEKEY FOR XML PATH('')) 
,'&#X20;',''))



   FROM [OBOE] EF
   left join OCRD IC ON IC.CardCode=EF.CardCode
   left join OCST PR ON PR.Country=IC.Country AND PR.Code=IC.State1 
   LEFT JOIN OCRB ICBan ON ICban.CardCode=IC.CardCode AND ICBan.BankCode=IC.[BankCode] and ICBan.Branch=IC.[DflBranch] and ICban.Account=IC.DflAccount
   LEFT JOIN OPYM VP ON Ef.[PayMethCod]=VP.PayMethCod
  ,OADM Em,ADM1 Em1
  where  boetype='O'  and BoeStatus='C'
     and Ef.PayMethCod=@viapago							--filtro por via de pago (cuenta de banco propio asociada a la via de cobro //no se deben mezclar
     and DueDate>=@fechavtod and DueDate<=@fechavtoh	--filtro por fecha de vencimiento (desde-hasta) 
     --AND (@efectos_sel IS NULL OR BoeKey IN (SELECT value FROM STRING_SPLIT(@efectos_sel, ','))) --filtro por numero de efecto marcado o desmarcado (STRING_SPLIT con compatibilidad 130)
--     AND (@efectos_sel IS NULL OR BoeKey IN (select id from @ListaDeEfectos))
  ORDER BY ef.DueDate, ef.BOEKEY

--   group by Em.CompnyName,Em.CompnyAddr,Em1.ZipCode,Em1.County,Em.Country,Em.SEPACredID,VP.DflIBAN,VP.DflSwift
-- </CstmrCdtTrfInitn>
--</Document>
 --hay que controlar que esten bien rellenos los datos de la cuenta del proveedor, generando avisos correspondientes
 -- 1: que siga existiendo, si no bic seria nulo
 