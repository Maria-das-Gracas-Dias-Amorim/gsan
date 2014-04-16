package gcom.batch.gerencial.arrecadacao;

import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;


/**
 * Descri��o da classe 
 *
 * @author Ivan S�rgio
 * @date 04/06/2007
 */
public class TarefaBatchGerarResumoArrecadacao extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoArrecadacao(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoArrecadacao() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

    	Collection colecaoLocalidade = 
    		(Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

        Integer anoMesReferenciaArrecadacao = (Integer) getParametro("anoMesReferenciaArrecadacao"); 
        
        Iterator iterator = colecaoLocalidade.iterator();
        
        while (iterator.hasNext()) {

        	Integer idLocalidade = ( (Localidade) iterator.next() ).getId();

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_RESUMO_ARRECADACAO_MDB,
                    new Object[] { idLocalidade, anoMesReferenciaArrecadacao, this.getIdFuncionalidadeIniciada() });
            	
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
		AgendadorTarefas.agendarTarefa("GerarResumoArrecadacaoBatch",
				this);
	}
}
