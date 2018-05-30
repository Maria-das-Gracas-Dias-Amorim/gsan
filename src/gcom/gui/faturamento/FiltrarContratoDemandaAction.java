package gcom.gui.faturamento;

import gcom.cadastro.imovel.Contrato;
import gcom.cadastro.imovel.FiltroContrato;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarContratoDemandaAction extends GcomAction {

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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterContratoDemanda");

		Fachada fachada = Fachada.getInstancia();

		FiltrarContratoDemandaActionForm filtrarContratoDemandaActionForm = (FiltrarContratoDemandaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa todo o formul�rio para evitar "sujeiras" na tela
		String numeroContrato = filtrarContratoDemandaActionForm
				.getNumeroContrato();
		String idImovel = filtrarContratoDemandaActionForm.getIdImovel();
		String dataInicioContrato = filtrarContratoDemandaActionForm
				.getDataInicioContrato();
		String dataFimContrato = filtrarContratoDemandaActionForm
				.getDataFimContrato();

		// Cria o filtro
		FiltroContrato filtroContratoDemanda = new FiltroContrato();

		boolean peloMenosUmParametroInformado = false;

		// Neste ponto o filtro � criado com os par�metros informados na p�gina
		// de filtrar contrato de demanda para ser executada a pesquisa no
		// ExibirManterContratoDemandaAction
		if (numeroContrato != null
				&& !numeroContrato.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroContratoDemanda.adicionarParametro(new ParametroSimples(
					FiltroContrato.MUMEROCONTRATO, numeroContrato));
			
			// [FS0003] - Verificar exist�ncia do contrato de demanda
			Collection colecaoContratoDemanda = fachada.pesquisar(
					filtroContratoDemanda, Contrato.class.getName());
			
			if (colecaoContratoDemanda == null || colecaoContratoDemanda.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Contrato de Demanda");
			}
		}

		// Verifica se o im�vel existe e em caso afirmativo
		// seta-a no filtro
		if (idImovel != null && !idImovel.trim().equals("")) {

			Imovel imovel = fachada.pesquisarImovel(new Integer(idImovel));

			if (imovel == null) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Im�vel");
			} else {
				peloMenosUmParametroInformado = true;
				filtroContratoDemanda.adicionarParametro(new ParametroSimples(
						FiltroContrato.IMOVEL, idImovel));
			}

		}

		if (dataInicioContrato != null
				&& !dataInicioContrato.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			Date dataInicioFormatada = Util.converteStringParaDate(dataInicioContrato);
			
			filtroContratoDemanda.adicionarParametro(new MaiorQue(
					FiltroContrato.DATACONTRATOINICIO,
					dataInicioFormatada));
		}

		if (dataFimContrato != null
				&& !dataFimContrato.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			Date dataFimFormatada = Util.converteStringParaDate(dataFimContrato);
			
			filtroContratoDemanda.adicionarParametro(new MenorQue(
					FiltroContrato.DATACONTRATOINICIO, dataFimFormatada));
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar est� marcado e em caso afirmativo
		// manda pela sess�o uma vari�vel para o
		// ExibirManterEquipeAction e nele verificar se ir� para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sess�o
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}

		// Manda o filtro pela sessao para o
		// ExibirManterContratoDemandaAction
		sessao.setAttribute("filtroContratoDemanda", filtroContratoDemanda);

		return retorno;
	}
}
