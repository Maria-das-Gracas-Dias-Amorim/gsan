package gcom.gui.faturamento.conta;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirManterMensagemContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterMensagemContaAction");

		//Fachada fachada = Fachada.getInstancia();
				
		// Mudar isso quando tiver esquema de seguran�a
		//HttpSession sessao = httpServletRequest.getSession(false);
	

		return retorno;

	}

}
