package gcom.gui.seguranca.acesso.transacao;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarOperacaoEfetuadaAction   extends GcomAction {
    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("consultaOperacaoEfetuada");

        //ExibirConsultarOperacaoEfetuadaActionForm form = (ExibirConsultarOperacaoEfetuadaActionForm) actionForm;  

        //HttpSession sessao = httpServletRequest.getSession(false);
        
        //Collection coll = (Collection) sessao.getAttribute("usuarioAlteracao");

        return retorno;
    }

}
