package gcom.atendimentopublico.ligacaoesgoto;


import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class FiltroLigacaoEsgotoDestinoAguasPluviais extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroLigacaoEsgotoDestinoAguasPluviais(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroLigacaoEsgotoDestinoAguasPluviais() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";
}
