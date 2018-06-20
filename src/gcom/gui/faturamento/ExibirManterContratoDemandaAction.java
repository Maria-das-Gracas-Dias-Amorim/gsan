package gcom.gui.faturamento;

import gcom.cadastro.imovel.Contrato;
import gcom.cadastro.imovel.FiltroContrato;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Permite exibir uma lista com os contratos de demanda retornados do
 * FiltrarContratoDemandaAction ou ir para o
 * ExibirAtualizarContratoDemandaAction
 * 
 * @author Rafael Corr�a
 * @since 27/06/2007
 */
public class ExibirManterContratoDemandaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterContratoDemanda");

		// Obt�m a inst�ncia da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("contratoDemandaAtualizar");

		// Recupera o filtro passado pelo FiltrarResolucaoDiretoriaAction para
		// ser efetuada pesquisa
		FiltroContrato filtroContratoDemanda = (FiltroContrato) sessao
				.getAttribute("filtroContratoDemanda");
		filtroContratoDemanda.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtroContratoDemanda.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroContratoDemanda.adicionarCaminhoParaCarregamentoEntidade("contratoMotivoCancelamento");

		// Aciona o controle de pagina��o para que sejam pesquisados apenas
		// os registros que aparecem na p�gina
		Collection colecaoContratoDemanda = new ArrayList();
		if(filtroContratoDemanda != null && !filtroContratoDemanda.equals("")){
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroContratoDemanda, Contrato.class.getName());
			colecaoContratoDemanda = (Collection) resultado
				.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}
		// Verifica se a cole��o retornada pela pesquisa � nula, em caso
		// afirmativo comunica ao usu�rio que n�o existe nenhuma resolu��o de
		// diretoria cadastrado para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condi��es seta o retorno para o
		// ExibirAtualizarContratoDemandaAction, se n�o atender manda a
		// cole��o pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoContratoDemanda != null
				&& !colecaoContratoDemanda.isEmpty()) {

			// Verifica se a cole��o cont�m apenas um objeto, se est� retornando
			// da pagina��o (devido ao esquema de pagina��o de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usu�rio selecione o link para ir para a
			// segunda p�gina ele n�o v� para tela de atualizar.
			if (colecaoContratoDemanda.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				// Verifica se o usu�rio marcou o checkbox de atualizar no jsp
				// contrato_demanda_filtrar. Caso todas as condi��es sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarContratoDemandaAction e em caso negativo
				// manda a cole��o pelo request.
				if (sessao.getAttribute("atualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarContratoDemanda");
					Contrato contratoDemanda = (Contrato) colecaoContratoDemanda
							.iterator().next();
					sessao.setAttribute("contratoDemanda",
							contratoDemanda);
				} else {
					httpServletRequest.setAttribute(
							"colecaoContratoDemanda",
							colecaoContratoDemanda);
				}
			} else {
				httpServletRequest.setAttribute("colecaoContratoDemanda",
						colecaoContratoDemanda);
			}
		} else {
			// Caso a pesquisa n�o retorne nenhum objeto comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
