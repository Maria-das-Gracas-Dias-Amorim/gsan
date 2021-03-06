package gcom.relatorio.cadastro.imovel;

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
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de imoveis por situa��o da liga��o de agua
 * 
 * @author Rafael Pinto
 * @created 03/12/2007
 */
public class RelatorioImoveisSituacaoLigacaoAgua extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisSituacaoLigacaoAgua(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_SITUACAO_LIGACAO_AGUA);
	}

	@Deprecated
	public RelatorioImoveisSituacaoLigacaoAgua() {
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

		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro = 
			(FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper) getParametro("filtrarRelatorioImoveisSituacaoLigacaoAguaHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioImoveisSituacaoLigacaoAguaBean relatorioImoveisSituacaoLigacaoAguaBean = null;

		Collection<RelatorioImoveisSituacaoLigacaoAguaHelper> colecao =  
			fachada.pesquisarRelatorioImoveisSituacaoLigacaoAgua(filtro);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioImoveisSituacaoLigacaoAguaHelper helper = 
					(RelatorioImoveisSituacaoLigacaoAguaHelper) colecaoIterator.next();
				
				relatorioImoveisSituacaoLigacaoAguaBean = 
					new RelatorioImoveisSituacaoLigacaoAguaBean(helper);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioImoveisSituacaoLigacaoAguaBean);				
				
				
			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_SITUACAO_LIGACAO_AGUA,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.IMOVEIS_SITUACAO_LIGACAO_AGUA,
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

		retorno = 
			Fachada.getInstancia().pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(
				(FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper) 
					getParametro("filtrarRelatorioImoveisSituacaoLigacaoAguaHelper"));

		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisSituacaoLigacaoAgua", this);

	}

}
