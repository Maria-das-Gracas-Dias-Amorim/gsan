package gcom.batch.gerencial.cadastro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * Tarefa que manda para batch Gerar Resumo das A��es de Cobran�a do Cronograma
 * 
 * @author Vivianne
 * @created 23/03/2007
 */
public class TarefaBatchGerarResumoIndicadoresComercializacao extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoIndicadoresComercializacao(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoIndicadoresComercializacao() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_GERAR_RESUMO_INDICADORES_COMERCIALIZACAO_MDB,
                new Object[] { this.getIdFuncionalidadeIniciada() });
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
		AgendadorTarefas.agendarTarefa("GerarResumoIndicadoresComercializacaoBatch", this);
	}

}
