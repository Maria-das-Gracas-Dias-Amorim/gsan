package gcom.gui.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado para inserir uma resolu��o de diretoria no banco
 * 
 * [UC0217] Inserir Resolu��o de Diretoria Permite inserir uma
 * ResolucaoDiretoria
 * 
 * @author Rafael Corr�a
 * @since 30/03/2006
 */
public class InformarUnidadeNegocioTestemunhaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");


		// Recupera as unidades de negocio testemunha adicionadas e removidas

		// Informa as Unidade de Neg�cio Testemunha, fazendo
		// algumas valida��es no ControladorCobranca.
//		fachada.informarUnidadeNegocioTestemunha(
//				colecaoUnidadeNegocioTestemunhaAdicionadas,
//				colecaoUnidadeNegocioTestemunhaRemovidas, this
//						.getUsuarioLogado(httpServletRequest));

		// Monta a p�gina de sucesso de acordo com o padr�o do sistema.
		montarPaginaSucesso(httpServletRequest, "Unidade(s) de Neg�cio Testemunha(s) informada(s) com sucesso.",
				"Informar outra Unidade de Neg�cio Testemunha",
				"exibirInformarUnidadeNegocioTestemunhaAction.do?menu=sim");

		return retorno;

	}

}
