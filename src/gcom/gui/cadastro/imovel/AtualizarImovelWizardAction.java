/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cadastro.imovel;

import gcom.gui.StatusWizard;
import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Rossiter
 */
public class AtualizarImovelWizardAction extends WizardAction {

    // M�TODOS DE EXIBI��O
    // =======================================================

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
    public ActionForward adicionarAtualizarImovelClienteAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new AdicionarAtualizarImovelClienteAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

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
    public ActionForward exibirAtualizarImovelLocalidadeAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
    	
    	//**********************************************************************
    	// CRC2103
    	// Autor: Ivan Sergio
    	// Data: 28/07/2009
    	// Esta verificacao guarda na sessao o wizard e deve ser feita por conta
    	// do popup de inserir cliente que tambem possui um wizard.
    	//**********************************************************************
    	HttpSession sessao = httpServletRequest.getSession(false);
    	if (sessao.getAttribute("statusWizardAnterior") == null) {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizard");
        	sessao.setAttribute("statusWizardAnterior", statusWizardAnterior);
    	} else {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizardAnterior");
    		sessao.setAttribute("statusWizard", statusWizardAnterior);
    	}
    	//**********************************************************************
    	
        return new ExibirAtualizarImovelLocalidadeAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

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
    public ActionForward exibirAtualizarImovelEnderecoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	//**********************************************************************
    	// CRC2103
    	// Autor: Ivan Sergio
    	// Data: 28/07/2009
    	// Esta verificacao guarda na sessao o wizard e deve ser feita por conta
    	// do popup de inserir cliente que tambem possui um wizard.
    	//**********************************************************************
    	HttpSession sessao = httpServletRequest.getSession(false);
    	if (sessao.getAttribute("statusWizardAnterior") == null) {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizard");
        	sessao.setAttribute("statusWizardAnterior", statusWizardAnterior);
    	} else {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizardAnterior");
    		sessao.setAttribute("statusWizard", statusWizardAnterior);
    	}
    	//**********************************************************************

        return new ExibirAtualizarImovelEnderecoAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

    /**
     * Description of the Method
     */
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
    public ActionForward exibirAtualizarImovelPrincipalAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirAtualizarImovelPrincipalAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

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
    public ActionForward exibirAtualizarImovelClienteAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	//**********************************************************************
    	// CRC2103
    	// Autor: Ivan Sergio
    	// Data: 28/07/2009
    	// Esta verificacao guarda na sessao o wizard e deve ser feita por conta
    	// do popup de inserir cliente que tambem possui um wizard.
    	//**********************************************************************
    	HttpSession sessao = httpServletRequest.getSession(false);
    	if (sessao.getAttribute("statusWizardAnterior") == null) {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizard");
        	sessao.setAttribute("statusWizardAnterior", statusWizardAnterior);
    	} else {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizardAnterior");
    		sessao.setAttribute("statusWizard", statusWizardAnterior);
    	}
    	//**********************************************************************

        return new ExibirAtualizarImovelClienteAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
    }

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
    public ActionForward exibirAtualizarImovelSubCategoriaAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new ExibirAtualizarImovelSubCategoriaAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

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
    public ActionForward exibirAtualizarImovelCaracteristicasAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirAtualizarImovelCaracteristicasAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

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
    public ActionForward exibirAtualizarImovelConclusaoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirAtualizarImovelConclusaoAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    // FIM DOS M�TODOS DE EXIBI��O =============================================
    //==========================================================================

    // M�TODOS DE INSER��O =====================================================

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
    public ActionForward atualizarImovelLocalidadeAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new AtualizarImovelLocalidadeAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

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
    public ActionForward atualizarImovelEnderecoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new AtualizarImovelEnderecoAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

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
    public ActionForward atualizarImovelPrincipalAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new AtualizarImovelPrincipalAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

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
    public ActionForward atualizarImovelClienteAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new AtualizarImovelClienteAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        
    	//**********************************************************************
    	// CRC2103
    	// Autor: Ivan Sergio
    	// Data: 28/07/2009
    	// Esta verificacao guarda na sessao o wizard e deve ser feita por conta
    	// do popup de inserir cliente que tambem possui um wizard.
    	//**********************************************************************
    	HttpSession sessao = httpServletRequest.getSession(false);
    	if (sessao.getAttribute("statusWizardAnterior") == null) {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizard");
        	sessao.setAttribute("statusWizardAnterior", statusWizardAnterior);
    	} else {
    		StatusWizard statusWizardAnterior = (StatusWizard) sessao.getAttribute("statusWizardAnterior");
    		sessao.setAttribute("statusWizard", statusWizardAnterior);
    	}
    	//**********************************************************************
        
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

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
    public ActionForward atualizarImovelSubCategoriaAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new AtualizarImovelSubCategoriaAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

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
    public ActionForward atualizarImovelCaracteristicasAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new AtualizarImovelCaracteristicasAction().execute(actionMapping,
                actionForm, httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

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
    public ActionForward atualizarImovelConclusaoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        new AtualizarImovelConclusaoAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
        return this.redirecionadorWizard(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

    // FIM DOS M�TODOS DE EXIBI��O =============================================
    //==========================================================================

    // M�TODO PARA CONCLUIR O PROCESSO =========================================

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
    public ActionForward atualizarImovelAction(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        //recebe o parametros e consulta o objeto da sessao para chamar outro
        // metodo desta classe
        return new AtualizarImovelAction().execute(actionMapping, actionForm,
                httpServletRequest, httpServletResponse);
    }

}
