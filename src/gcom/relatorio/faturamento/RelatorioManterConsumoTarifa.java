package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de manter consumo tarifa
 * 
 * @author Rafael Corr�a
 * @created 11/05/2007
 */
public class RelatorioManterConsumoTarifa extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterConsumoTarifa(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONSUMO_TARIFA_MANTER);
	}

	@Deprecated
	public RelatorioManterConsumoTarifa() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		String descricao = (String) getParametro("descricao");
		Date dataVigenciaInicial = (Date) getParametro("dataVigenciaInicial");
		Date dataVigenciaFinal = (Date) getParametro("dataVigenciaFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterConsumoTarifaBean relatorioBean = null;

		Collection colecaoConsumoTarifaRelatorioHelper = fachada
				.pesquisarConsumoTarifaRelatorio(descricao,
						dataVigenciaInicial, dataVigenciaFinal);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoConsumoTarifaRelatorioHelper != null && !colecaoConsumoTarifaRelatorioHelper.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoConsumoTarifaRelatorioHelperIterator = colecaoConsumoTarifaRelatorioHelper.iterator();

			// la�o para criar a cole��o de par�metros da analise
			
			String categoriaAnterior = "";
			String idConsumoTarifaAnterior = "";
			Date dataVigenciaInicialAnterior = null;
			
			ConsumoTarifaRelatorioHelper consumoTarifaRelatorioHelper = null;
			
			while (colecaoConsumoTarifaRelatorioHelperIterator.hasNext()) {

				if (consumoTarifaRelatorioHelper != null) {
					idConsumoTarifaAnterior = consumoTarifaRelatorioHelper.getIdConsumoTarifa().toString(); 
				}
				
				consumoTarifaRelatorioHelper = (ConsumoTarifaRelatorioHelper) colecaoConsumoTarifaRelatorioHelperIterator
						.next();
				
				// Validade
				String validade = "";
				
				if (consumoTarifaRelatorioHelper.getDataValidadeInicial() != null
						&& ((!consumoTarifaRelatorioHelper
								.getDataValidadeInicial().equals(
										dataVigenciaInicialAnterior)) || (!idConsumoTarifaAnterior
								.equals(consumoTarifaRelatorioHelper
										.getIdConsumoTarifa().toString())))) {
					validade = validade + Util.formatarData(consumoTarifaRelatorioHelper.getDataValidadeInicial());
					dataVigenciaInicialAnterior = consumoTarifaRelatorioHelper.getDataValidadeInicial();
					
					Date dataValidadeFinal = fachada.pesquisarDataFinalValidadeConsumoTarifa(consumoTarifaRelatorioHelper.getIdConsumoTarifa(), consumoTarifaRelatorioHelper.getDataValidadeInicial());
					
					if (dataValidadeFinal != null) {
						validade = validade + " A " + Util.formatarData(dataValidadeFinal);
					}

				}
				
				// Categoria
				String categoria = "";
				
				if (consumoTarifaRelatorioHelper.getCategoria() != null) {
					categoria = consumoTarifaRelatorioHelper.getCategoria();
					categoriaAnterior = consumoTarifaRelatorioHelper.getCategoria();
				}
				
				// Faixa de Consumo
				String faixa = "";
				
				if (consumoTarifaRelatorioHelper.getFaixaInicial() != null) {
					faixa = faixa + consumoTarifaRelatorioHelper.getFaixaInicial();
				}
				
				if (consumoTarifaRelatorioHelper.getFaixaFinal() != null) {
					faixa = faixa + " A " + consumoTarifaRelatorioHelper.getFaixaFinal();
				}
					
				// Custo	
				String custo = "";
				
				if (consumoTarifaRelatorioHelper.getCusto() != null) {
					custo = Util.formatarMoedaReal(consumoTarifaRelatorioHelper.getCusto());
				}
					
				// Tarifa M�nima	
				String tarifaMinima = "";
				
				if (consumoTarifaRelatorioHelper.getTarifaMinima() != null && !categoria.equals("")) {
					tarifaMinima = Util.formatarMoedaReal(consumoTarifaRelatorioHelper.getTarifaMinima());
				}
				
				// Consumo M�nimo
				// Inclui mais um bean com o valor do consumo minimo, a data de validade e a categoria
				if (consumoTarifaRelatorioHelper.getConsumoMinimo() != null && !categoria.equals("")) {
					String faixaMinima = "ATE " + consumoTarifaRelatorioHelper.getConsumoMinimo(); 
					
					relatorioBean = new RelatorioManterConsumoTarifaBean(

							// Tarifa de Consumo
							consumoTarifaRelatorioHelper.getDescricaoConsumoTarifa(),
							
							// Validade
							validade,
							
							// Categoria
							categoria,
							
							// Faixa de Consumo
							faixaMinima,
							
							// Custo do M�
							"",
							
							// Tarifa M�nima
							tarifaMinima);
					
					// adiciona o bean a cole��o
					relatorioBeans.add(relatorioBean);
					
					// Seta os valores para vazio para n�o serem impressos novamente
					validade = "";
					categoria = "";
					tarifaMinima = "";
				}

				relatorioBean = new RelatorioManterConsumoTarifaBean(

						// Tarifa de Consumo
						consumoTarifaRelatorioHelper.getDescricaoConsumoTarifa(),
						
						// Validade
						validade,
						
						// Categoria
						categoria,
						
						// Faixa de Consumo
						faixa,
						
						// Custo do M�
						custo,
						
						// Tarifa M�nima
						tarifaMinima);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// Descri��o 
		if (descricao != null) {
			parametros.put("descricao", descricao);
		} else {
			parametros.put("descricao", "");
		}
		
		// Data Vig�ncia
		if (dataVigenciaInicial != null) {
			parametros.put("dataVigencia", Util.formatarData(dataVigenciaInicial) + " a " + Util.formatarData(dataVigenciaFinal));
		} else {
			parametros.put("dataVigencia", "");
		}

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSUMO_TARIFA_MANTER,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_BAIRRO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroBairro) getParametro("filtroBairro"),
//				Bairro.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterBairro", this);

	}

}
