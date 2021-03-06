package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o - Exibir
 * 
 * @author Vivianne Sousa
 *
 * @date 12/04/2011
 */
public class ExibirGerarRelatorioOSExecutadasPrestadoraServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioOSExecutadasPrestadoraServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		GerarRelatorioOSExecutadasPrestadoraServicoActionForm form = 
			(GerarRelatorioOSExecutadasPrestadoraServicoActionForm) actionForm;
		
		String menu = httpServletRequest.getParameter("menu");
		if (menu != null && !menu.equalsIgnoreCase("")){
			pesquisarRegiao(sessao);
			pesquisarMicroregiao(sessao);
			pesquisarMunicipio(sessao);
			
			
			form.setPeriodoEncerramentoInicial("");
			form.setPeriodoEncerramentoFinal("");
			form.setEmpresa("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			form.setIdGerencia("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			form.setIdUnidadeNegocio("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			form.setCodigoLocalidade("");
			form.setDescricaoLocalidade("");
			form.setColecaoRegiao(null);
			form.setColecaoMicrorregiao(null);
			form.setColecaoMunicipio(null);
			form.setOpcaoRelatorio("2");
			
			
		}
		
		pesquisarEmpresa(form);
		pesquisarGerenciaRegional(httpServletRequest);
		pesquisarUnidadeNegocio(httpServletRequest,form);
		
		String codigoLocalidade = httpServletRequest.getParameter("codigoLocalidade");

		if (codigoLocalidade != null && !codigoLocalidade.trim().equals("")) {
			pesquisarLocalidade(codigoLocalidade, httpServletRequest);

		}
		return retorno;
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarEmpresa(GerarRelatorioOSExecutadasPrestadoraServicoActionForm form) {
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, new Integer(13)));
//		filtroEmpresa.adicionarParametro(new ParametroSimples(
//				FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		Collection<Empresa> colecaoEmpresa = this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());
		Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(colecaoEmpresa);
		// [FS0001 - Verificar Existencia de dados]
		if ( (colecaoEmpresa == null) || (colecaoEmpresa.size() == 0) ) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null, Empresa.class.getName());
		}else {
			form.setEmpresa(empresa.getDescricao());
		}
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroQuadra.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());


		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Ger�ncia Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}
	
	
	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioOSExecutadasPrestadoraServicoActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		if(form.getIdGerencia() != null && 
			!form.getIdGerencia().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, form.getIdGerencia()));		
		}

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());


		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Unidade de Neg�cio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		}
	}

	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarRegiao(HttpSession sessao){
		
		FiltroRegiao filtroRegiao = new FiltroRegiao();

		filtroRegiao.setCampoOrderBy(FiltroRegiao.DESCRICAO);

		Collection<Regiao> colecaoRegiao = this.getFachada().pesquisar(filtroRegiao,Regiao.class.getName());

		if (colecaoRegiao == null || colecaoRegiao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Regi�o");
		} else {
			sessao.setAttribute("colecaoRegiao",colecaoRegiao);
		}
		
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarMicroregiao(HttpSession sessao){
		
		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();

		filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);

		Collection<Microrregiao> colecaoMicrorregiao = this.getFachada().pesquisar(filtroMicrorregiao,Microrregiao.class.getName());

		if (colecaoMicrorregiao == null || colecaoMicrorregiao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Microregi�o");
		} else {
			sessao.setAttribute("colecaoMicrorregiao",colecaoMicrorregiao);
		}

	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarMunicipio(HttpSession sessao){
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.setCampoOrderBy(FiltroMunicipio.NOME);
		
		Collection<Municipio> colecaoMunicipio = this.getFachada().pesquisar(filtroMunicipio,Municipio.class.getName());

		if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Munic�pio");
		} else {
			sessao.setAttribute("colecaoMunicipio",colecaoMunicipio);
		}

	}

	/**
	 * Pesquisa uma localidade informada e prepara os dados para exibi��o na tela
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	private void pesquisarLocalidade(String idLocalidade,
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidade));

		Collection<Localidade> localidadePesquisada = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// Se nenhuma localidade for encontrada a mensagem � enviada para a p�gina
		if (localidadePesquisada == null || localidadePesquisada.isEmpty()) {
			// [FS0001 - Verificar exist�ncia de dados]
			httpServletRequest.setAttribute("codigoLocalidade", "");
			httpServletRequest.setAttribute("descricaoLocalidade","Localidade Inexistente".toUpperCase());
		}

		// obtem o imovel pesquisado
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = localidadePesquisada.iterator().next();
			// Manda a Localidade pelo request
			httpServletRequest.setAttribute("codigoLocalidade", idLocalidade);
			httpServletRequest.setAttribute("descricaoLocalidade", localidade.getDescricao());
		}

	}
	
}
