<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarZonaPressaoActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(formulario){
		if(validateFiltrarZonaPressaoActionForm(formulario)){
			if(validateInteger){
	    		submeterFormPadrao(formulario);
			}
		}
	}

	function IntegerValidations () {
		this.aa = new Array("id", "O campo C�digo deve conter apenas n�meros.", new Function ("varName", " return this[varName];"));
	}

    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
		function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];
		if (tipoConsulta == 'distrito') {
		     form.distritoOperacionalID.value = codigoRegistro;
		 	 form.distritoOperacionalDescricao.value = descricaoRegistro;
	  	     form.distritoOperacionalDescricao.style.color = "#000000";
		}
	}
	
	function limpar(tipo){
		var form = document.forms[0];
		switch (tipo){
			case 1:
			   form.distritoOperacionalID.value = "";
		  	   form.distritoOperacionalDescricao.value = "";
           	   form.distritoOperacionalDescricao.style.color = "#000000";

  		       //Coloca o foco no objeto selecionado
		       form.distritoOperacionalID.focus();
		       break;
		   case 2:
		   form.distritoOperacionalDescricao.value = "";
           form.distritoOperacionalDescricao.style.color = "#000000";

		   //Coloca o foco no objeto selecionado
		   form.distritoOperacionalID.focus();
		   break;
		}
	}	
	
</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/filtrarZonaPressaoAction"
	name="FiltrarZonaPressaoActionForm"
	type="gcom.gui.operacional.FiltrarZonaPressaoActionForm"
	method="post"
	onsubmit="return validateFiltrarZonaPressaoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			</div>
			</td>
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


			<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Zona de Press&atilde;o</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar uma zona de press&atilde;o, informe os dados
					abaixo:</td>
					<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:text property="id" size="2" maxlength="2" /><font
						size="1">&nbsp;(somente n�meros)</font> <br>
					<font color="red"><html:errors property="id" /></font></td>
					<td>&nbsp;</td>
				</tr>
		       <tr>
					<td><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="30" maxlength="30" /> </span></td>
			   		<td width="15%">&nbsp;</td>
			   </tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoAbreviada" size="6" maxlength="6" /> </span></td>
					<td>&nbsp;</td>
				</tr>
      			<tr>
       				 <td><strong>Distrito Operacional: </strong></td>
        			 <td colspan="3">
						 <html:text property="distritoOperacionalID" size="5" maxlength="3" tabindex="1" 
			 				onkeypress="limpar(2);validaEnterComMensagem(event, 'exibirFiltrarZonaPressaoAction.do?objetoConsulta=1', 'distritoOperacionalID','Distrito Operacional');"/>
							<a href="javascript:abrirPopup('exibirPesquisarDistritoOperacionalAction.do?indicadorUsoTodos=1', 250, 495);">
							<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Distrito Operacional" /></a>

					<logic:present name="corDistritoOperacional">

							<logic:equal name="corDistritoOperacional" value="exception">
								<html:text property="distritoOperacionalDescricao" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:equal>

							<logic:notEqual name="corDistritoOperacional" value="exception">
								<html:text property="distritoOperacionalDescricao" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEqual>
	
					</logic:present>

					<logic:notPresent name="corDistritoOperacional">

					<logic:empty name="FiltrarZonaPressaoActionForm" property="distritoOperacionalID">
						<html:text property="distritoOperacionalDescricao" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:empty>
					<logic:notEmpty name="FiltrarZonaPressaoActionForm" property="distritoOperacionalID">
						<html:text property="distritoOperacionalDescricao" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
			
				</logic:notPresent>

					<a	href="javascript:limpar(1);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a>
				</td>
	    	</tr>
			<tr>
				<td><strong>Indicador de uso:</strong></td>
				<td><html:radio property="indicadorUso" tabindex="2" value="1" /><strong>Ativo</strong>
					<html:radio property="indicadorUso" tabindex="3" value="2" /><strong>Inativo</strong>
					<html:radio property="indicadorUso" tabindex="4" value="" /><strong>Todos</strong>
				</td>
				<td>&nbsp;</td>
			</tr>
				
			<tr>
				<td><input name="Button" type="button" class="bottonRightCol"
					value="Limpar" align="left"
					onclick="window.location.href='/gsan/exibirFiltrarZonaPressaoAction.do?menu=sim'"
					tabindex="8"></td>
				<td align="right" colspan="2">&nbsp;</td>
				<td width="65" align="right"><input name="Button2" type="button"
					class="bottonRightCol" value="Filtrar" align="right"
					onClick="javascript:validarForm(document.forms[0]);" tabindex="9"></td>
			</tr>

		</table>

		</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
