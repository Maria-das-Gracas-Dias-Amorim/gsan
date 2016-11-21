package gcom.gui.atendimentopublico;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 17/01/2007
 */
public class EmitirSegundaViaContaInternetAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirResultadoEmitirSegundaViaContaInternetAction");

		HttpSession sessao = httpServletRequest.getSession(true);
		
		//Identifica a sess�o criada para o usu�rio da segunda via
		sessao.setAttribute("loginUsuarioSessao", "USUARIO_SEGUNDA_VIA_INTERNET");

		if (sessao.getAttribute("acessoGeral") != null) {
			retorno = actionMapping.findForward("exibirResultadoEmitirSegundaViaContaInternetAcessoGeralAction");
		}

		//Action Base ExibirEfetuarParcelamentoDebitosProcesso1Action
		SistemaParametro sistemaParametro = (SistemaParametro) sessao.getAttribute("sistemaParametro");

		EmitirSegundaViaContaInternetActionForm form = 
			(EmitirSegundaViaContaInternetActionForm) actionForm;

		Integer matricula = new Integer(form.getMatricula());

		// Verificar existencia do im�vel
		Integer matriculaExistente = 
			this.getFachada().verificarExistenciaImovel(matricula);
		
		Collection colecaoLocalidadeElo = null;

		if (matriculaExistente == 1) {

			form.setMatricula(matricula.toString());
			
			Imovel imovel = 
				this.getFachada().pesquisarImovel(new Integer(matricula));
			
			
			Integer idLocalidade = imovel.getLocalidade().getId();
			colecaoLocalidadeElo = 
				this.pesquisarLocalidade(idLocalidade.toString());
			
			Localidade localidade = null;
			
			if(colecaoLocalidadeElo != null && !colecaoLocalidadeElo.isEmpty())
				
				localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeElo);
				
				//Localidade � o Elo
				if(localidade.getId().intValue() == localidade.getLocalidade().getId().intValue()){
					form.setElo(localidade.getDescricao());
				}else{
					//Localidade nao � o Elo
					colecaoLocalidadeElo = this.pesquisarLocalidade(localidade.getLocalidade().getId().toString());
					
					localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeElo);
					form.setElo(localidade.getDescricao());
				}
			
			/**
			 * Alterado por R�mulo Aur�lio
			 * Data: 11/05/2007
			 */
				
			BigDecimal totalContas = new BigDecimal("0.00");
			BigDecimal totalContasPreterito = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidadeContas = new BigDecimal("0.00");
			
			Short nDiasVencimentoCobranca = sistemaParametro.getNumeroDiasVencimentoCobranca();
			Date dataDebito = new Date();
			
			Calendar calendar = Calendar.getInstance();

			//Data Atual - Numero de dias vencimento Cobranca
			calendar.add(Calendar.DAY_OF_MONTH,-nDiasVencimentoCobranca.shortValue());
			dataDebito = calendar.getTime();			
			form.setDataDebito(Util.formatarData(dataDebito));
			
			// Ano mes Atual.
			String ano;
			String mes;

			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);

			String anoMesInicialReferenciaDebito = "198501";
			String anoMesFinalReferenciaDebito = ano + mes;

			//Date aux1 = dataInicioVencimentoDebito.getTime();
			Date aux1 = Util.converteStringParaDate("01/01/1985");
			
			//Date aux2 = dataFimVencimentoDebito.getTime();
			Date aux2 = Util.converteStringParaDate("31/12/9999");

			String tipoRelacao = "-1";

			ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = 
				(ObterDebitoImovelOuClienteHelper) 
					this.getFachada().obterDebitoImovelOuCliente(1, 
						form.getMatricula(), 
						null,
						new Short(tipoRelacao),
						anoMesInicialReferenciaDebito,
						anoMesFinalReferenciaDebito, 
						aux1, 
						aux2, 
						1, 
						1, 
						1,
						1, 
						1, 
						1, 
						1, 
						null);
			
			Collection colecaoContasValores = obterDebitoImovelOuClienteHelper.getColecaoContasValores();
			
			Collection<ContaValoresHelper> colecaoContaValoresPreteritos = obterDebitoImovelOuClienteHelper.getColecaoContasValoresPreteritos();
			
			
			
			if ((colecaoContasValores == null || colecaoContasValores.isEmpty()) && (colecaoContaValoresPreteritos == null || colecaoContaValoresPreteritos.isEmpty())) {
				throw new ActionServletException("atencao.imovel_sem_debitos");
			} else {
			
				if (colecaoContasValores != null){
					
					Iterator colecaoContasValoresIterator = colecaoContasValores.iterator();
					
					while (colecaoContasValoresIterator.hasNext()) {
						ContaValoresHelper contaValoresHelper = 
							(ContaValoresHelper) colecaoContasValoresIterator.next();
						
						totalContas = totalContas.add(contaValoresHelper.getValorTotalConta());
						
						valorTotalAcrescimoImpontualidadeContas = 
							valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValores());
					}
					
				}
				
				if (colecaoContaValoresPreteritos != null){
					
					Iterator colecaoContaValoresPreteritoIterator = colecaoContaValoresPreteritos.iterator();
					
					while (colecaoContaValoresPreteritoIterator.hasNext()) {
						ContaValoresHelper contaValoresPreteritoHelper = 
							(ContaValoresHelper) colecaoContaValoresPreteritoIterator.next();
						
						totalContasPreterito = totalContasPreterito.add(contaValoresPreteritoHelper.getValorTotalConta());
					}
					
					
				}
				
				httpServletRequest.setAttribute("totalContas", getTotalContasFormatado(totalContas.add(totalContasPreterito)));
				//
				//httpServletRequest.setAttribute("totalContas", totalContas.add(totalContasPreterito));
				form.setValorDebito(Util.formatarMoedaReal(totalContas));
				form.setValorEncargosACobrar(Util.formatarMoedaReal(valorTotalAcrescimoImpontualidadeContas));
			}

			httpServletRequest.setAttribute("colecaoContaValoresPreteritos",colecaoContaValoresPreteritos);
			
			httpServletRequest.setAttribute("colecaoContasValores",colecaoContasValores);
			
			form.setNomeCliente(this.getFachada().obterNomeCliente(matricula));
			
			ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = 
				this.getFachada().obterDebitoImovelOuCliente(
					1, // Indicador d�bito im�vel
			        matricula.toString(), // Matr�cula do im�vel
			        null, // C�digo do cliente
			        null, // Tipo de rela��o do cliento com o
			        // im�vel
			        "000101", // Refer�ncia inicial do d�bito
			        "999912", // Refer�ncia final do d�bito
			        Util.converteStringParaDate("01/01/0001"), // Inicio
			        // Vencimento
			        Util.converteStringParaDate("31/12/9999"), // Final
			        // Vencimento
			        1, // Indicador pagamento
			        1, // Indicador conta em revis�o
			        1, // Indicador d�bito a cobrar
			        1, // Indicador cr�dito a realizar
			        1, // Indicador notas promiss�rias
			        1, // Indicador guias de pagamento
			        1, // Indicador acr�scimos por impontualidade
			        null); // Indicador Contas
			
			Collection colecaoDebitoACobrar = colecaoDebitoImovel.getColecaoDebitoACobrar();
			BigDecimal valorTotalDebitoACobrar = new BigDecimal("0.00");
			  
			if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
				Iterator debitoACobrarValores = colecaoDebitoACobrar.iterator();
				     
				while (debitoACobrarValores.hasNext()) {
				      
					DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores.next();
					valorTotalDebitoACobrar = 
						valorTotalDebitoACobrar.add(debitoACobrar.getValorTotalComBonus());
				 }
			}
			
			//valor outros servi�os so aparece para CAEMA
			//RM986 - Vivianne Sousa - 15/06/2011
			form.setValorOutrosServicosACobrar(Util.formatarMoedaReal(valorTotalDebitoACobrar));
			
			// Acrescimos por Impotualidade
			BigDecimal retornoSoma = new BigDecimal("0.00");
			
			retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeContas);
			valorTotalDebitoACobrar = valorTotalDebitoACobrar.add(retornoSoma);
			form.setDebitoACobrar(Util.formatarMoedaReal(valorTotalDebitoACobrar));
		} else {
			throw new ActionServletException("atencao.matricula.imovel.inexistente");
		}
		
		/*
		 * Colocado por Raphael Rossiter em 21/10/2008 - Analista: Rosana Carvalho
		 * 
		 * [FS0003] - Verificar se documento � v�lido
		 * [FS0004] - Cliente n�o associado ao documento
		 */
		String cpf = (String) httpServletRequest.getParameter("cpf");
		String cnpj = (String) httpServletRequest.getParameter("cnpj");
		
		this.getFachada().verificarDocumentoValidoEmissaoInternet(matricula, cpf, cnpj);
		
		return retorno;
	}
	
	public Collection pesquisarLocalidade(String idLocalidade){
		
		Collection colecaoLocalidadeElo = null;
		
		FiltroLocalidade filtrolocalidade = new FiltroLocalidade();
		filtrolocalidade.adicionarParametro(
			new ParametroSimples(
				FiltroLocalidade.ID,
				idLocalidade));
		
		colecaoLocalidadeElo = 
			this.getFachada().pesquisar(filtrolocalidade,
				Localidade.class.getName());
		
		return colecaoLocalidadeElo;
	}
	
	public String getTotalContasFormatado(BigDecimal totalContas) {
		return Util.converterDecimalParaString(totalContas);
	}
}
