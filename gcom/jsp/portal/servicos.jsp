<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
<head>
	<title>Compesa | Servi�os</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
	<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">
	<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('.info-serv').hide();
			$('#lista-servicos li, #lista-informacoes li').hover(function(){
				$('.ativo').removeClass('ativo');
				$(this).find('.info-serv').fadeIn(50);
				$(this).find('a').addClass('ativo').css('color', '#FFF');
			}, function(){
				$('.ativo').removeClass('ativo').css('color', '#008FD6');;
				$(this).find('.info-serv').fadeOut(50);
			});
		
			$('.confirm').click(function(){
				$.unblockUI();
			});
		});
	</script>
	<logic:present name="imovelSemDebito" scope="request">
		<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#imovelSemDebitos'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	
	<logic:present name="debitoParceladoMesCorrente" scope="request">
		<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#debitoParceladoMesCorrente'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	
	<logic:present name="imovelSemQuitacaoAnual" scope="request">
			<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#imovelSemQuitacaoAnual'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	
	<!-- Valida��es Inicias de efetuar parcelamento de d�bitos -->
	<logic:present name="imovelParcelamentoAtivo" scope="request">
		<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#imovelParcelamentoAtivo'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	
	<logic:present name="imovelSemDebitos" scope="request">
		<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#imovelSemDebitos'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	
	<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
		<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#imovelNaoPossuiPerfilParcelamento'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	<!-- Fim das valida��es Inicias de efetuar parcelamento de d�bitos -->
</head>
	
<body>
	<div id="container"> 
	   <html:form action="/portal.do?method=servicos" method="post" 
			name="ExibirPortalActionForm" type="gcom.gui.portal.ExibirPortalActionForm" >
    	<%@ include file="/jsp/portal/cabecalho.jsp"%>
        
        <!-- Content - Start -->
         <div id="content">
         <%@ include file="/jsp/portal/cabecalhoImovel.jsp"%>
            
            <ul id="lista-informacoes">
                <li>
            		<a href="segunda-via-conta.do">
            			<span>2� Via da conta</span>
           			</a>
                	<div class="info-serv" style="text-align:justify;">
                        <p>Este acesso permite solicitar a segunda via da sua conta, que poder� ser paga nos agentes recebedores da Compesa. Para acessar este servi�o, clique no assunto &quot;2� Via de conta&quot; e digite o n�mero da sua matr�cula, e aguarde o t�rmino da impress�o  para pagamento, ou se preferir dirija-se a uma loja de atendimento, ou entre em contato com o call center pelo n�mero 0800 081 0195 ou pelo link fale conosco.</p>
                        <p>A Compesa disponibiliza mais este servi�o sem custo adicional.</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    </div>
                </li>
                <li id="serv-2">
                	<a href="portal.do?method=declaracaoAnual">
                		<span style="font-size:14px;">Declara��o anual de quita��o de d�bito</span>
               		</a>
                	<div class="info-serv" style="text-align:justify;">
                        <p>Conforme determina o artigo 3� da lei federal 12.007 de 2009 a Compesa disponibiliza para voc� a declara��o de quita��o anual de d�bitos. Lembramos que para este acesso o cliente dever� estar em dia com suas contas referentes ao ano de 2010.</p>
                        <p>Para acessar este servi�o, clique no assunto declara��o anual de quita��o de d�bito ou dirija-se a uma loja de atendimento, ou entre em contato com o call center pelo n�mero 0800 081 0195 ou pelo link fale conosco ou pelo e-mail dac0800@compesa.com.br</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    </div>
                </li>
                <li id="serv-3">
                	<a href="exibirInserirCadastroEmailClientePortalAction.do?ok=sim">
                		<span>Recebimento de fatura por e-mail</span>
               		</a>
                	<div class="info-serv" style="text-align:justify;">
                        <p>A Compesa disponibiliza para voc� a facilidade de receber suas faturas em seu e-mail, para acessar este servi�o, clique no assunto recebimento de fatura por email fa�a seu cadastro e receba suas contas sem sair de casa, ou dirija-se a uma loja de atendimento, ou entre em contato com o call center pelo n�mero 0800 081 0195 ou pelo link fale conosco ou pelo e-mail dac0800@compesa.com.br</p>
                        <p>A Compesa disponibiliza mais este servi�o sem custo adicional.</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    </div>
                </li>
                <li id="serv-4">
                	<a href="exibirInserirCadastroContaBrailePortalAction.do">
                		<span>Solicitar conta em braile</span>
               		</a>
                	<div class="info-serv" style="text-align:justify;">
                        <p>Em atendimento a Lei Estadual n� 14.262 de 05 de janeiro de 2011, que  assegura aos portadores de defici�ncia visual o direito de receber os boletos de pagamento de suas contas de �gua, energia el�trica e telefonia, confeccionados em braille, estamos disponibilizando esta funcionalidade para emiss�o deste servi�o, o qual ser� solicitado sem custo adicional.</p>
                        <p>Para acessar clique em solicitar conta em braille em solicitar conta em braille ou dirija-se a uma loja de atendimento ou entre em contato com o call center, atrav�s  do  n�mero 0800 081 0195.</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    </div>
                </li>
                <li id="serv-5">
                	<a href="exibirInserirSolicitacaoServicosPortalAction.do?init=1">
                		<span>Outros servi�os</span>
               		</a>
                	<div class="info-serv">
                        <p>Por este acesso, ser� poss�vel solicitar alguns servi�os. Fa�a sua op��o.</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                   	</div>
                </li>
                <li id="serv-6">
                	<a href="exibirEfetuarParcelamentoDebitosPortalAction.do?paginaServicos=SIM">
                		<span>Negocia��o de d�bitos</span>
               		</a>
                	<div class="info-serv" style="text-align:justify;">
                        <p>Este acesso permite simular as condi��es de regulariza��o de seu d�bito � vista ou a prazo. Ao final da negocia��o, ser� gerado documento pag�vel nos agentes arrecadadores da Compesa.
						</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    </div>
                </li>
            </ul>
        </div>
        <!-- Content - End -->
        
       <%@ include file="/jsp/portal/rodape.jsp"%>
       </html:form>
    </div>
    
	<!-- Avisos -->
	
	<logic:present name="imovelSemDebito" scope="request">
		<div id="imovelSemDebitos" style="display:none;cursor:default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Im�vel sem d�bitos.</h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="imovelSemQuitacaoAnual" scope="request">
		<div id="imovelSemQuitacaoAnual" style="display:none;cursor:default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">Im�vel sem declara��o anual de quita��o de d�bitos</h3>
	        <p>
	        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195
	        </p>
	        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
	<!-- Valida��es Inicias de efetuar parcelamento de d�bitos -->
	<logic:present name="imovelParcelamentoAtivo" scope="request">
		<div id="imovelParcelamentoAtivo" style="display:none; cursor: default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="padding-top:10px; padding-bottom: 10px;">Im�vel j� possui um parcelamento n�o quitado/cobrado. </h3>
	        <p>
	        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
	        </p>
	        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="imovelSemDebitos" scope="request">
		<div id="imovelSemDebitos" style="display:none; cursor: default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="padding-top:10px; padding-bottom: 10px;">O Im�vel informado n�o possui d�bitos. </h3>
	        <p>
	        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
	        </p>
	        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="debitoParceladoMesCorrente" scope="request">
		<div id="debitoParceladoMesCorrente" style="display:none; cursor: default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="padding-top:10px; padding-bottom: 10px;">O d�bito deste im�vel j� foi parcelado no m�s de faturamento corrente. </h3>
	        <p>
	        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
	        </p>
	        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
		<div id="imovelNaoPossuiPerfilParcelamento" style="display:none; cursor: default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="padding-top:10px; padding-bottom: 10px;">N�o existe perfil de parcelamento correspondente � situa��o do im�vel. </h3>
	        <p>
	        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195.
	        </p>
	        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	<!-- Fim das valida��es Inicias de efetuar parcelamento de d�bitos -->
</body>
</html:html>