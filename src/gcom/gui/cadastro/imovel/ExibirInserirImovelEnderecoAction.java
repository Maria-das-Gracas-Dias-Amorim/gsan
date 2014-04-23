package gcom.gui.cadastro.imovel;

import java.util.Collection;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirImovelEnderecoAction extends GcomAction {

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
		ActionForward retorno = actionMapping
				.findForward("inserirImovelEndereco");

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		String removeEndereco = httpServletRequest
				.getParameter("removeEndereco");

		if (removeEndereco != null && !removeEndereco.equals("")) {
			sessao.removeAttribute("colecaoEnderecos");
		}else {
			//**********************************************************************
			// Autor: Ivan Sergio
			// Data: 23/07/2009
			// CRC2103
			// Guarda o endereco do Imovel para o caso em que o Inserir/Manter
			// cliente � invocado pelo Inserir/Manter Imovel como PopUp
			//**********************************************************************
			Collection colecaoEndereco = (Collection) sessao.getAttribute("colecaoEnderecos");
			if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
				Object obj = (Object) colecaoEndereco.iterator().next();
				if (!(obj instanceof Imovel)) {
					sessao.removeAttribute("colecaoEnderecos");
				}
				if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
					sessao.setAttribute("colecaoEnderecos", sessao.getAttribute("colecaoEnderecosImovel"));
				}
			}else if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
				sessao.setAttribute("colecaoEnderecos", sessao.getAttribute("colecaoEnderecosImovel"));
			}
			//**********************************************************************
		}

		if (httpServletRequest.getAttribute("confirmou") != null) {
			sessao.setAttribute("confirmou", "sim");
		}
		
		sessao.removeAttribute("gis");

		DynaValidatorForm inserirImovelLocalidadeActionForm = (DynaValidatorForm) sessao
				.getAttribute("InserirImovelActionForm");

		String codigoSetorComercial = (String) inserirImovelLocalidadeActionForm
				.get("idSetorComercial");
		String idLocalidade = (String) inserirImovelLocalidadeActionForm
				.get("idLocalidade");

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial
				.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.MUNICIPIO);

		// coloca parametro no filtro
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.ID_LOCALIDADE, new Integer(idLocalidade)));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
						codigoSetorComercial)));

		// Obt�m a inst�ncia da Fachada
		// Fachada fachada = Fachada.getInstancia();

		// pesquisa
//		Collection setorComerciais = fachada.pesquisar(filtroSetorComercial,
//				SetorComercial.class.getName());
//		Collection colecaoEndereco = (Collection) sessao
//		.getAttribute("colecaoEnderecos");
//
//		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
//
//			Imovel imovel = (Imovel) colecaoEndereco.iterator().next();
//
//			System.out
//					.println("imovel.getLogradouroBairro().getLogradouro().getMunicipio().getId().toString()="
//							+ imovel.getLogradouroBairro().getLogradouro()
//									.getMunicipio().getId().intValue());
//			System.out
//					.println("( ((SetorComercial) ((List) setorComerciais).get(0)).getMunicipio().getId()="
//							+ (((SetorComercial) ((List) setorComerciais)
//									.get(0)).getMunicipio().getId().intValue()));
//			System.out
//					.println("boolean="
//							+ (!(imovel.getLogradouroBairro().getLogradouro()
//									.getMunicipio().getId().intValue() == (((SetorComercial) ((List) setorComerciais)
//									.get(0)).getMunicipio().getId().intValue()))));
//
//			if (imovel.getLogradouroBairro() != null
//					&& imovel.getLogradouroBairro().getLogradouro() != null
//					&& imovel.getLogradouroBairro().getLogradouro()
//							.getMunicipio() != null
//					&& (!(imovel.getLogradouroBairro().getLogradouro()
//							.getMunicipio().getId().intValue() == (((SetorComercial) ((List) setorComerciais)
//							.get(0)).getMunicipio().getId().intValue())))) {
//
//				Usuario usuario = (Usuario) sessao
//						.getAttribute("usuarioLogado");
//
//				if (!fachada
//						.verificarPermissaoInserirImovelMunicipioLogradouroDiferenteSetor(usuario)) {
//
//					throw new ActionServletException(
//							"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial");
//
//				} else {
//					if (sessao.getAttribute("confirmou") == null) {
//						httpServletRequest.setAttribute("destino",
//								actionMapping
//										.findForward("inserirImovelEndereco"));
//
//						// httpServletRequest.setAttribute("confirmou", "sim");
//
//						// retorno = actionMapping
//						// .findForward("gerenciado");
//						String destino ="inserirImovelEndereco" ;
//						actionMapping.setParameter(destino);
//
//						retorno = montarPaginaConfirmacaoWizard(
//								"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial",
//								httpServletRequest, actionMapping, "");
//					}
//				}

				// httpServletRequest.setAttribute("atencao","O munic�pio do
				// logradouro n�o � o mesmo do setor comercial");

				// URL da pr�xima ABA
				// httpServletRequest
				// .setAttribute(
				// "proximaAba",
				// "/gsan/inserirImovelWizardAction.do?destino=3&action=inserirImovelEnderecoAction");
				//
				// // URL da ABA anterior
				// httpServletRequest
				// .setAttribute(
				// "voltarAba",
				// "/gsan/inserirImovelWizardAction.do?removeEndereco=true&destino=2&action=inserirImovelLocalidadeAction");
				//
				// retorno = actionMapping.findForward("telaOpcaoConsultar");

				// sessao.removeAttribute("colecaoEnderecos");
				// throw new
				// ActionServletException("atencao.municipio.diferente.setor_comercial.logradouro");

			
		
		return retorno;
	}

}
