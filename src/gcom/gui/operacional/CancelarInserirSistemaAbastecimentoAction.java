package gcom.gui.operacional;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author R�mulo Aur�lio
 *
 */
public class CancelarInserirSistemaAbastecimentoAction  extends GcomAction{
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

        ActionForward retorno = actionMapping.findForward("telaPrincipal");

        //obt�m a inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        //limpa a sess�o
        
        sessao.removeAttribute("InserirSistemaAbastecimentoActionForm");
        sessao.removeAttribute("tipoPesquisaRetorno");
        sessao.removeAttribute("statusWizard");

        return retorno;
    }

}
