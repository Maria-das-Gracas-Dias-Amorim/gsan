<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="RetificarContasPagasSemDebitoCreditoActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript">

function validarForm(form){	
	if(validateRetificarContasPagasSemDebitoCreditoActionForm(form)){
	    if(form.quatidadeConta.value != ''){    		
		 form.submit();
		}else{
		  alert('Selecione primeiro as Contas. ');
		}
	}
}

function selecionarContas(form){
	if(validateRetificarContasPagasSemDebitoCreditoActionForm(form)){    		
		form.action = 'exibirRetificarContasPagasSemDebitoCreditoAction.do?quantidadeConta=ok'
  		form.submit();
	}
}


</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/retificarContasPagasSemDebitoCreditoAction"
  name="RetificarContasPagasSemDebitoCreditoActionForm"
  type="gcom.gui.faturamento.conta.RetificarContasPagasSemDebitoCreditoActionForm"
  method="post"
  onsubmit="return validateRetificarContasPagasSemDebitoCreditoActionForm(this);"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>
<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="130" valign="top" class="leftcoltext">
      <div align="center">

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>

         <%@ include file="/jsp/util/informacoes_usuario.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>

         <%@ include file="/jsp/util/mensagens.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>

          <td class="parabg">Filtrar Contas Pagas sem D&eacute;bitos e/ou Cr&eacute;ditos </td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
        	<td width="100%" colspan=2>
	        	<table width="100%" border="0">
		          	<tr>
		          		<td>Preencha os campos abaixo para filtrar os im&oacute;veis com releitura :</td>
					</tr>
		          </table>
        	</td>
        </tr>
        <tr>
          <td width="25%"><strong>M&ecirc;s / Ano de refer&ecirc;cia:<font color="#FF0000">*</font></strong></td>
          <td width="75%">
          	<html:text property="referenciaConta" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);return isCampoNumerico(event);"/>
          	&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
			<td><strong>Grupo de Faturamento:</strong></td>
		    <td>
		    	<span class="style2">
		    		<strong> 
						<html:select property="idGrupo" style="width: 230px;">
							<html:option value="">&nbsp;</html:option>
							<logic:present name="collectionFaturamentoGrupo">
								<html:options collection="collectionFaturamentoGrupo" labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
					</strong>                
				</span>
			</td>
		</tr>	
		<tr>
			<td height="10" width="160">
				<strong>Quantidade de Contas:</strong>
			</td>
			<td width="403">
				<html:text  property="quatidadeConta" 
							size="7" 
							readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #000000"/>
							<font color="#ff0000">
								<input name="Submit222"
									class="bottonRightCol" value="Selecionar" type="button"
									onclick="javascript:selecionarContas(document.forms[0]);">
							</font>
		    </td>													
			
		
		<tr>
			<td><strong> <font color="#000000"></font></strong></td>
			<td align="right">
				<div align="left"><strong><font color="#FF0000">*</font></strong>Campos obrigatórios</div>
			</td>
		</tr>
		
		<tr> 
			<td>
				<strong> 
					<font color="#ff0000">
						<input name="Submit222"
							class="bottonRightCol" value="Voltar" type="button"
							onclick="javascript:history.back();">
					</font>
				</strong>
			</td>
			
			<td width="65" align="right">
				<input name="Button2" type="button"
					class="bottonRightCol" value="Retificar Contas" align="right"
					onClick="javascript:validarForm(document.forms[0]);" tabindex="9"/>
			</td>            	
		</tr>        
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>