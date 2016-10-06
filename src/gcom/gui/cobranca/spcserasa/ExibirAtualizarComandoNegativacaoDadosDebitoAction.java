package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os par�metros para realiza��o
 * da inser��o de um Comando de Negativa��o (Aba n� 02 - Dados do D�bito) 
 *
 * @author Ana Maria	
 * @date 06/11/2007
 */
public class ExibirAtualizarComandoNegativacaoDadosDebitoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("atualizarComandoNegativacaoDadosDebito");
        
        Fachada fachada = Fachada.getInstancia();
        
        AtualizarComandoNegativacaoPorCriterioActionForm form = (AtualizarComandoNegativacaoPorCriterioActionForm) actionForm;
        
  	    //Pesquisar Sistema Parametro 
  	    FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
  			
  	   Collection<SistemaParametro> collectionSistemaParametro = fachada
  					.pesquisar(filtroSistemaParametro,
  							SistemaParametro.class.getName());
  	    SistemaParametro sistemaParametro = (SistemaParametro) collectionSistemaParametro
  					.iterator().next();
  	  
  	    //Refer�ncia do D�bito Final
  	    if(form.getReferenciaFinal() == null ||
  	    		form.getReferenciaFinal().equals("")){
	  	    String anoMesArrecadacao = Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao());
	  	  form.setReferenciaFinal(anoMesArrecadacao);
  	    }
  	    //Data do Vencimento Final
  	    if(form.getDataVencimentoFinal() == null || form.getDataVencimentoFinal().equals("")){
			//Per�odo de vencimento do d�bito	
			Integer numeroDiasVencimentoCobranca = new Integer(sistemaParametro.getNumeroDiasVencimentoCobranca());			
			Date dataVencimentoFinal = Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca);
			Date dataVencimentoInicial = Util.subtrairNumeroAnosDeUmaData(dataVencimentoFinal, 5);
			form.setDataVencimentoInicial(Util.formatarData(dataVencimentoInicial));
			form.setDataVencimentoFinal(Util.formatarData(dataVencimentoFinal));
  	    }
  	    
        if(form.getContasRevisao() == null){
        	//Considerar Conta em Revis�o - exibir com op��o "N�o" selecionada    		
        	form.setContasRevisao(ConstantesSistema.NAO_CONFIRMADA);   
        }
	
        if(form.getGuiasPagamento() == null){
        	//Considerar Guias de Pagamento - exibir com op��o "N�o" selecionada    		
        	form.setGuiasPagamento(ConstantesSistema.NAO_CONFIRMADA);   
        }

        if(form.getParcelaAtraso() == null){
        	//Parcela em Atraso - exibir com op��o "N�o" selecionada    		
        	form.setParcelaAtraso(ConstantesSistema.NAO_CONFIRMADA);   
        }
        
        if(form.getCartaParcelamentoAtraso() == null){
        	//Recebeu Carta de Parcelamento em Atraso - exibir com op��o "N�o" selecionada    		
        	form.setCartaParcelamentoAtraso(ConstantesSistema.NAO_CONFIRMADA);   
        }
        
        if(form.getIndicadorContaNomeCliente() == null){
        	form.setIndicadorContaNomeCliente(ConstantesSistema.NAO_CONFIRMADA);
        }

		// Data Corrente
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));
    		
    	return retorno;
    }

}
