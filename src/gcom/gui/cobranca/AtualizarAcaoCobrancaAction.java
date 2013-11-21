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
* Anderson Italo Felinto de Lima
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
package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.bean.CobrancaAcaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o crit�rio da cobran�a e as linhas do criterio da
 * cobran�a
 * 
 * @author S�vio Luiz
 * @date 18/09/2007
 */
public class AtualizarAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AcaoCobrancaAtualizarActionForm acaoCobrancaAtualizarActionForm = (AcaoCobrancaAtualizarActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		Fachada fachada = Fachada.getInstancia();
		
		CobrancaAcao cobrancaAcao = (CobrancaAcao)sessao.getAttribute("cobrancaAcao");
		
		String idAcaoPredecessora = "";
		String numeroDiasMinimoAcoes = "";
		if ( acaoCobrancaAtualizarActionForm.getIdAcaoPredecessora() != null &&
				!acaoCobrancaAtualizarActionForm.getIdAcaoPredecessora().equals("")){
			idAcaoPredecessora = acaoCobrancaAtualizarActionForm.getIdAcaoPredecessora();
			numeroDiasMinimoAcoes = acaoCobrancaAtualizarActionForm.getNumeroDiasEntreAcoes();
		}
		
		CobrancaAcaoHelper cobrancaAcaoHelper = new CobrancaAcaoHelper(acaoCobrancaAtualizarActionForm.getDescricaoAcao(),
				acaoCobrancaAtualizarActionForm.getIcAcaoObrigatoria(),
				acaoCobrancaAtualizarActionForm.getIcRepetidaCiclo(),
				acaoCobrancaAtualizarActionForm.getIcSuspensaoAbastecimento(),
				acaoCobrancaAtualizarActionForm.getNumeroDiasValidade(),
				numeroDiasMinimoAcoes,
				acaoCobrancaAtualizarActionForm.getIcUso(),
				acaoCobrancaAtualizarActionForm.getIcDebitosACobrar(),
				acaoCobrancaAtualizarActionForm.getIcCreditosARealizar(),
				acaoCobrancaAtualizarActionForm.getIcNotasPromissoria(),
				acaoCobrancaAtualizarActionForm.getIcGeraTaxa(),
				acaoCobrancaAtualizarActionForm.getOrdemCronograma(),
				acaoCobrancaAtualizarActionForm.getIcAcrescimosImpontualidade(),
				idAcaoPredecessora,
				acaoCobrancaAtualizarActionForm.getIdSituacaoLigacaoEsgoto(),
				acaoCobrancaAtualizarActionForm.getIdTipoDocumentoGerado(),
				acaoCobrancaAtualizarActionForm.getIdSituacaoLigacaoAgua(),
				acaoCobrancaAtualizarActionForm.getIdServicoTipo(),
				acaoCobrancaAtualizarActionForm.getIdCobrancaCriterio(),
				acaoCobrancaAtualizarActionForm.getIcCompoeCronograma(),
				acaoCobrancaAtualizarActionForm.getIcEmitirBoletimCadastro(),
				acaoCobrancaAtualizarActionForm.getIcImoveisSemDebitos(),
				acaoCobrancaAtualizarActionForm.getNumeroDiasVencimento(),
				acaoCobrancaAtualizarActionForm.getDescricaoCobrancaCriterio(),
				acaoCobrancaAtualizarActionForm.getDescricaoServicoTipo(),
				acaoCobrancaAtualizarActionForm.getIcMetasCronograma(),
				acaoCobrancaAtualizarActionForm.getIcOrdenamentoCronograma(),
				acaoCobrancaAtualizarActionForm.getIcOrdenamentoEventual(),
				acaoCobrancaAtualizarActionForm.getIcDebitoInterfereAcao(),
				acaoCobrancaAtualizarActionForm.getNumeroDiasRemuneracaoTerceiro(),
				acaoCobrancaAtualizarActionForm.getIcOrdenarMaiorValor(),
				acaoCobrancaAtualizarActionForm.getIcValidarItem(),
				usuarioLogado); 
		
		
		fachada.atualizarAcaoCobranca(cobrancaAcao,cobrancaAcaoHelper);

		sessao.removeAttribute("voltar");
		sessao.removeAttribute("cobrancaAcao");
		sessao.removeAttribute("colecaoLigacaoEsgotoSituacao");
		sessao.removeAttribute("colecaoLigacaoAguaSituacao");
		sessao.removeAttribute("colecaoDocumentoTipo");
		sessao.removeAttribute("colecaoAcaoPredecessora");

		montarPaginaSucesso(httpServletRequest, "A��o de Cobran�a "
				+ acaoCobrancaAtualizarActionForm.getDescricaoAcao() + " atualizada com sucesso.",
				"Realizar outra Manuten��o de A��o de Cobran�a",
		        "exibirFiltrarAcaoCobrancaAction.do?menu=sim");
		
		
		return retorno;
	}

}
