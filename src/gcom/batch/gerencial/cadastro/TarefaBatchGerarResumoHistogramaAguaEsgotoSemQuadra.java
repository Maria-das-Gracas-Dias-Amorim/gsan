package gcom.batch.gerencial.cadastro;

import gcom.cadastro.localidade.SetorComercial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Gerar Resumo Histograma Agua Esgoto Sem Quadra
 * @author Ivan Sergio
 * @created 01/09/2010
 */
public class TarefaBatchGerarResumoHistogramaAguaEsgotoSemQuadra extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoHistogramaAguaEsgotoSemQuadra(
			Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoHistogramaAguaEsgotoSemQuadra() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoIdsSetorComercialParaExecucao = 
			(Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		Integer anoMesFaturamentoSistemaParametro = 
			(Integer) getParametro("anoMesFaturamentoSistemaParametro");
		Iterator iterator = colecaoIdsSetorComercialParaExecucao.iterator();

		while (iterator.hasNext()) {
			Integer idSetor = ((SetorComercial) iterator.next()).getId();
			
			System.out.println("*********************************************************"
							 + "GERAR RESUMO HISTOGRAMA AGUA ESGOTO: SETOR COMERCIAL "
							 + (idSetor)
							 + "*********************************************************");
			
			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_RESUMO_HISTOGRAMA_AGUA_ESGOTO_SEM_QUADRA,
					new Object[]{
							anoMesFaturamentoSistemaParametro,
							idSetor,
							this.getIdFuncionalidadeIniciada()});
		}
		
		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarResumoHistogramaAguaEsgoto", this);
	}
}
