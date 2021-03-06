package gcom.relatorio.arrecadacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC0339] Consultar Dados Di�rios da Arrecada��o
 * 
 * Gerar Relat�rio Dados Di�rios da Arrecada��o por Documento Agregador
 * 
 * @author Mariana Victor
 * @date 02/02/2011
 */
public class RelatorioDadosDiariosArrecadacaoDocumentoAgregador extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioDadosDiariosArrecadacaoDocumentoAgregador(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_DOCUMENTO_AGREGADOR);
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String nomeArrecadador = (String) getParametro("arrecadador");
		
		Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> mapDadosDiariosAnoMes =
			(Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>>) getParametro("mapDadosDiariosAnoMes");
		
		Map<Integer, FiltrarDadosDiariosArrecadacaoHelper> mapDadosProcessamento =
			(Map<Integer, FiltrarDadosDiariosArrecadacaoHelper>) getParametro("mapDadosProcessamento");
		Collection<BigDecimal> colecaoValorTotal = (Collection<BigDecimal>) getParametro("colecaoValorTotal");
		
		BigDecimal valorTotalPeriodo = (BigDecimal) getParametro("valorTotalPeriodo");
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();

		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		String arrecadador = "";
		if(nomeArrecadador != null){
			if(nomeArrecadador.equals("")){
				arrecadador = "TODOS";
			}
			else{
				arrecadador = nomeArrecadador;
			}
		}
		
		// Usuario que emite o relatorio
		Usuario usuario = this.getUsuario();

		String nomeUsuario = usuario.getNomeUsuario();
		
		parametros.put("nomeUsuario", nomeUsuario);
		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("enderecoEmpresa", sistemaParametro.getEnderecoFormatado());
		parametros.put("cepEmpresa", sistemaParametro.getCep().getCepFormatado());
		parametros.put("telefoneGeral", sistemaParametro.getNumeroTelefone());
		parametros.put("arrecadador", arrecadador);
		
		if (sistemaParametro.getCdDadosDiarios() != null){
			parametros.put("exibirFaturamentoCobradoConta", sistemaParametro.getCdDadosDiarios().toString());
		}
		
		Collection<RelatorioDadosDiariosArrecadacaoDocumentoAgregadorBean> colecaoBean = this
			.inicializarBeanRelatorio(mapDadosDiariosAnoMes, colecaoValorTotal,
					valorTotalPeriodo, mapDadosProcessamento);

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this
				.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_DOCUMENTO_AGREGADOR,
						parametros, ds, tipoFormatoRelatorio);

		// retorna o relat�rio gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDadosDiariosArrecadacaoGerencia", this);
	}
	
	private Collection<RelatorioDadosDiariosArrecadacaoDocumentoAgregadorBean> inicializarBeanRelatorio(
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> mapDadosDiariosAnoMes,
			Collection<BigDecimal> colecaoValorTotal, BigDecimal valorTotalPeriodo,
			Map<Integer, FiltrarDadosDiariosArrecadacaoHelper> mapDadosProcessamento){
		
		Collection<RelatorioDadosDiariosArrecadacaoDocumentoAgregadorBean> colecaoBean = new ArrayList<RelatorioDadosDiariosArrecadacaoDocumentoAgregadorBean>();

		for (Integer itemAnoMes : mapDadosDiariosAnoMes.keySet()) {

			FiltrarDadosDiariosArrecadacaoHelper helperProcessamento = mapDadosProcessamento.get(itemAnoMes);
			
			for (FiltrarDadosDiariosArrecadacaoHelper helper : mapDadosDiariosAnoMes.get(itemAnoMes)){
				
				RelatorioDadosDiariosArrecadacaoDocumentoAgregadorBean bean = new RelatorioDadosDiariosArrecadacaoDocumentoAgregadorBean();
				    
				DocumentoTipo documentoTipo = (DocumentoTipo) helper.getItemAgrupado();
				
				String tipoProcessamento = "";
				
				if (helperProcessamento.getTipoProcessamento() != null 
						&& helperProcessamento.getTipoProcessamento().equals("provisorio")) {
					tipoProcessamento = "Processamento Provis�rio:";
				} else {
					tipoProcessamento = "Processamento Definitivo:";
				}
				
				String processamentoProvisorio = "";
				if (helperProcessamento.getUltimoProcessamentoMesInformado() != null
						&& !helperProcessamento.getUltimoProcessamentoMesInformado().equals("")) {
					processamentoProvisorio = helperProcessamento.getUltimoProcessamentoMesInformado();
				} else {
					processamentoProvisorio = helperProcessamento.getUltimoProcessamentoAtualSistema();
				}
				
				bean.setTipoProcessamento(tipoProcessamento);
				bean.setProcessamentoProvisorio(processamentoProvisorio);
				bean.setMesAno(Util.formatarAnoMesParaMesAno(itemAnoMes));
				bean.setFaturamentoCobradoEmConta(helperProcessamento.getFaturamentoCobradoEmConta());
				bean.setDocumentoAgregador(documentoTipo.getDescricaoDocumentoTipo());
				bean.setQuantidadeDoc(Util.agruparNumeroEmMilhares(helper.getQuantidadeDocumentos()));
				bean.setQuantidadePag(Util.agruparNumeroEmMilhares(helper.getQuantidadePagamentos()));
				bean.setDebitos(Util.formatarMoedaReal(helper.getValorDebitos()));
				bean.setDescontos(Util.formatarMoedaReal(helper.getValorDescontos()));
				bean.setValorArrecadado(Util.formatarMoedaReal(helper.getValorArrecadacao()));
				bean.setDevolucao(Util.formatarMoedaReal(helper.getValorDevolucoes()));
				bean.setArrecadacaoLiquida(Util.formatarMoedaReal(helper.getValorArrecadacaoLiquida()));
				bean.setPercentualMes(Util.formatarMoedaReal(helper.getPercentual()));
				bean.setValorTotalPeriodo(Util.formatarMoedaReal(valorTotalPeriodo));
				
				colecaoBean.add(bean);
			}
			
		}
		
		return colecaoBean;
	}
}
