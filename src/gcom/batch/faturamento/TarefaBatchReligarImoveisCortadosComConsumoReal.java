package gcom.batch.faturamento;

import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;


public class TarefaBatchReligarImoveisCortadosComConsumoReal extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchReligarImoveisCortadosComConsumoReal(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchReligarImoveisCortadosComConsumoReal() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        Collection<Localidade> colecaoIdsLocalidades = (Collection<Localidade>) 
        	getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 

        Integer anoMesFaturamento = (Integer) getParametro("anoMesFaturamento");
        
        Iterator iterator = colecaoIdsLocalidades.iterator();

        while (iterator.hasNext()) {

            Localidade localidade = (Localidade) iterator.next();

            enviarMensagemControladorBatch(
            		ConstantesJNDI.BATCH_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL_MDB,
                    new Object[] {  
            			anoMesFaturamento,
            			localidade.getId(),
                    	this.getIdFuncionalidadeIniciada() }
            );
                            
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
		AgendadorTarefas.agendarTarefa("ReligarImoveisCortadosComConsumoRealBatch",
				this);
	}

}
