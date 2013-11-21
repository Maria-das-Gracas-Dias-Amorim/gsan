<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.math.BigDecimal,gcom.util.Util" %>
<%@ page import="gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper, gcom.arrecadacao.ArrecadacaoForma" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
-->
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarDadosDiariosArrecadacaoFormaComTarifaAction.do"
	name="FiltrarDadosDiariosArrecadacaoComTarifaActionForm"
	type="gcom.gui.arrecadacao.FiltrarDadosDiariosArrecadacaoComTarifaActionForm"
	method="post">
	<table width="770" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="770" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar Dados Di�rios da Arrecada��o - Forma de Arrecada��o</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="10">
					<table width="100%" border="0">
						<%
						BigDecimal valorTotalArrecadador = (BigDecimal)request.getAttribute("valorTotalArrecadador");
						String referencia = (String)request.getAttribute("referencia");
						String dadosMesInformado = (String)request.getAttribute("dadosMesInformado");
						String dadosAtual = (String)request.getAttribute("dadosAtual");

						%>
						<tr bgcolor="#90c7fc">
							<td colspan="4">
								<table width="100%">
									<tr>
										<td><strong>Processamento Definitivo: 
											<%= dadosMesInformado %>
											</strong>
									    </td>
										<td>
											<div align="right"><strong>M&ecirc;s/Ano:</strong><strong>
											<%= Util.formatarAnoMesParaMesAno(referencia) %></strong></div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr bgcolor="#90c7fc">
							<td colspan="4">
								<table width="100%">
									<tr>
										<td><strong>�ltimo Processamento Atual: 
											<%= dadosAtual %>
											</strong>
									    </td>

									</tr>
								</table>
							</td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td colspan="2" align="left" width="73%">
								<strong>Agente: ${requestScope.nomeAgente}</strong>
							</td>						
							<td align="right" width="10%">
								<strong>Valor:</strong>
							</td>
							<td colspan="1" align="right" width="17%">
								<strong>
									<a href="javascript:abrirPopup('exibirConsultarDadosDiariosValoresDiariosComTarifaAction.do?referencia=<%= referencia%>&idArrecadadorPopup=${requestScope.idArrecadadorPopup}', 575, 800);">
										<%= Util.formatarMoedaReal(valorTotalArrecadador) %>
									</a>
								</strong>
							</td>
						</tr>
					</table>
					<table width="100%" bgcolor="#99CCFF" dwcopytype="CopyTableRow">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
							<td width="35%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<strong>Forma de Arrecada��o</strong>
							</font></div>
							</td>
							<td width="15%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<strong>Qtd. Documentos</strong></font></div>
							</td>
							<td width="15%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<strong>Qtd. Pagamentos</strong></font></div>
							</td>
							<td width="15%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<strong>D�bitos</strong></font></div>
							</td>
							<td width="15%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<strong>Descontos</strong></font></div>
							</td>							
							<td width="15%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<strong>Valor Arrecadado</strong></font></div>
							</td>
							<td width="15%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<strong>Devolu��es</strong></font></div>
							</td>
							<td width="15%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<strong>Arrecada��o L�quida</strong></font></div>
							</td>
							<td width="10%">
							<div align="center">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<strong>Percentual</strong></font></div>
							</td>
						</tr>
						<%String cor = "#cbe5fe"; %>
						<logic:present name="colecaoDadosDiarios">
						<logic:iterate name="colecaoDadosDiarios" id="itemHelper" type="FiltrarDadosDiariosArrecadacaoHelper">
						<% 

						BigDecimal valorArrecadado = itemHelper.getValorArrecadacao();
						
						ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) itemHelper.getItemAgrupado();
						
						boolean ehTODOS = arrecadacaoForma.getId().intValue() == -1;
						String negrito = ehTODOS ? "font-weight: bold;" : "";

						if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
								<%} else {
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
								<%}%>
							<td height="17">
							      <div align="left">
							      <font color="#000000" style="font-size:9px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
							        <%=arrecadacaoForma.getDescricao() %>
							        </font>
								  </div>
							</td>
							<td height="17">
							      <div align="right">
							      <font color="#000000" style="font-size:9px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
							        <%=Util.agruparNumeroEmMilhares(itemHelper.getQuantidadeDocumentos())%>
							      </font>
								  </div>
							</td>
							<td height="17">
							      <div align="right">
							      <font color="#000000" style="font-size:9px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
							        <%=Util.agruparNumeroEmMilhares(itemHelper.getQuantidadePagamentos())%>
							      </font>
								  </div>
							</td>							
							<td>
							      <div align="right">
							      <font color="#000000" style="font-size:9px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
							        	<%=Util.formatarMoedaReal(itemHelper.getValorDebitos()) %>
							      </font>
								  </div>
							</td>
							<td>
							      <div align="right">
							      <font color="#000000" style="font-size:9px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
							        	<%=Util.formatarMoedaReal(itemHelper.getValorDescontos()) %>
							      </font>
								  </div>
							</td>
							<td>
							      <div align="right">
							      <font color="#000000" style="font-size:9px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
							        	<%=Util.formatarMoedaReal(valorArrecadado)%>
							      </font>
								  </div>
							</td>
							<td>
							      <div align="right">
							      <font color="#000000" style="font-size:9px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
							        	<%=Util.formatarMoedaReal(itemHelper.getValorDevolucoes()) %>
							      </font>
								  </div>
							</td>							
							<td>
							      <div align="right">
							      <font color="#000000" style="font-size:9px;<%=negrito%>" face="Verdana, Arial, Helvetica, sans-serif"> 
							      	<a href="javascript:abrirPopup('exibirConsultarDadosDiariosValoresDiariosComTarifaAction.do?referencia=<%=referencia %>&idArrecadadorPopup=${requestScope.idArrecadadorPopup}&idArrecadacaoForma=<%=arrecadacaoForma.getId()%>', 475, 800);">
							        	<%=Util.formatarMoedaReal(itemHelper.getValorArrecadacaoLiquida()) %></a>
							      </font>
								  </div>
							</td>					
							<td nowrap="nowrap">
							<div align="right">
							<%= Util.formatarMoedaReal(itemHelper.getPercentual()) %></font></div>
							</td>
						</tr>
					</logic:iterate>
					</logic:present>
					</table>

					<p>&nbsp;</p>
					</td>
				</tr>
			</table>
			<table border="0" width="100%">
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="left">
					<input name="ButtonCancelar" type="button"
						class="bottonRightCol" value="Voltar"
						onClick="javascript:window.history.go(-1);">
						</td>
					<td align="right">
					<input name="Button" type="button"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();"></td>
				</tr>
				<p>&nbsp;</p>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<body>
</html:html>
