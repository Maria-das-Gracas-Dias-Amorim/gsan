package gcom.gui.faturamento;

import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.cadastro.imovel.Contrato;
import gcom.cadastro.imovel.ContratoTipo;
import gcom.cadastro.imovel.FiltroContrato;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarContratoDemandaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarContratoDemandaActionForm atualizarContratoDemandaActionForm = (AtualizarContratoDemandaActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao
				.getAttribute(Usuario.USUARIO_LOGADO);

		Contrato contratoDemanda = (Contrato) sessao.getAttribute("contratoDemandaAtualizar");
		
		String dataInicioContrato = atualizarContratoDemandaActionForm.getDataInicioContrato();
		String dataFimContrato = atualizarContratoDemandaActionForm.getDataFimContrato();
		String dataEncerramento = atualizarContratoDemandaActionForm.getDataEncerramento();
		String idMotivoCancelamento = atualizarContratoDemandaActionForm.getIdMotivoCancelamento();
		
		Imovel imovel = null;

		String idImovel = atualizarContratoDemandaActionForm.getIdImovel();
		
		if (idImovel != null && !idImovel.trim().equals("")) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
			
			Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
				imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Im�vel");
			}
			
		}
		
		contratoDemanda.setNumeroContrato(atualizarContratoDemandaActionForm.getNumeroContrato());
		contratoDemanda.setImovel(imovel);
		
		if (dataInicioContrato != null && !dataInicioContrato.trim().equals("")) {
			contratoDemanda.setDataContratoInicio(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataInicioContrato()));
		} else {
			contratoDemanda.setDataContratoInicio(null);
		}
		
		if (dataFimContrato != null && !dataFimContrato.trim().equals("")) {
			contratoDemanda.setDataContratoFim(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataFimContrato()));
		} else {
			contratoDemanda.setDataContratoFim(null);
		}
		
		if (dataEncerramento != null && !dataEncerramento.trim().equals("")) {
			contratoDemanda.setDataContratoEncerrado(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataEncerramento()));
		} else {
			contratoDemanda.setDataContratoEncerrado(null);
		}
		
		if (idMotivoCancelamento != null && !idMotivoCancelamento.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ContratoMotivoCancelamento contratoMotivoCancelamento = new ContratoMotivoCancelamento();
			contratoMotivoCancelamento.setId(new Integer(idMotivoCancelamento));
			contratoDemanda.setContratoMotivoCancelamento(contratoMotivoCancelamento);
		} else {
			contratoDemanda.setContratoMotivoCancelamento(null);
		}
		
		if (contratoDemanda.getDataContratoEncerrado() == null && contratoDemanda.getContratoMotivoCancelamento() == null) {
			FiltroContrato filtroContratoDemanda = new FiltroContrato();
			filtroContratoDemanda.adicionarParametro(new ParametroSimples(FiltroContrato.IMOVEL, idImovel));
			filtroContratoDemanda.adicionarParametro(new ParametroSimples(FiltroContrato.CONTRATO_TIPO, ContratoTipo.DEMANDA));
			filtroContratoDemanda.adicionarParametro(new ParametroNulo(FiltroContrato.DATACONTRATOENCERRAMENTO));
			filtroContratoDemanda.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroContrato.ID, contratoDemanda.getId()));
			
			Collection colecaoContratoDemanda = fachada.pesquisar(filtroContratoDemanda, Contrato.class.getName());
			
			if (colecaoContratoDemanda != null & !colecaoContratoDemanda.isEmpty()) {
				throw new ActionServletException("atencao.contrato.demanda.encerrado", null, idImovel);
			}
		}
		
		// Inserir na base de dados ContratoDemanda
		fachada.atualizarContratoDemanda(contratoDemanda,usuarioLogado);
		
		// Montar a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Contrato de Demanda de c�digo "
				+ contratoDemanda.getId().toString()
				+ " atualizado com sucesso.",
				"Realizar outra Manuten��o de Contrato de Demanda",
				"exibirFiltrarContratoDemandaAction.do?menu=sim");

		return retorno;

	}
}
