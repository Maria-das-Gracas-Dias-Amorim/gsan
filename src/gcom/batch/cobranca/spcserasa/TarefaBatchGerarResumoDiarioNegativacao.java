package gcom.batch.cobranca.spcserasa;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * Tarefa que manda para Gerar Resumo diario Negativacao
 */
public class TarefaBatchGerarResumoDiarioNegativacao extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoDiarioNegativacao(Usuario usuario, int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoDiarioNegativacao() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection rotas = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = rotas.iterator();

		while (iterator.hasNext()) {
			Integer idRota = (Integer) iterator.next();
			enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_RESUMO_DIARIO_NEGATIVACAO_MDB, new Object[] { this.getIdFuncionalidadeIniciada(), idRota });

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
		AgendadorTarefas.agendarTarefa("BatchGerarResumoDiarioNegativacao", this);
	}

}
