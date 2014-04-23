package gcom.faturamento.consumotarifa;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.faturamento.TarifaTipoCalculo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ConsumoTarifa implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Set consumoTarifaVigencias;
    
    /** nullable persistent field */
    private LigacaoAguaPerfil ligacaoAguaPerfil;
    
    /** nullable persistent field */
    private TarifaTipoCalculo tarifaTipoCalculo;
    
    public static final Integer CONSUMO_NORMAL  = new Integer("1");
    
    public static final Integer CONSUMO_SOCIAL  = new Integer("2");

    public static final Integer AGUA_BRUTA_ADUTORA = new Integer("21");
    
    /** full constructor */
    public ConsumoTarifa(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public ConsumoTarifa() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Set getConsumoTarifaVigencias() {
		return consumoTarifaVigencias;
	}

	public void setConsumoTarifaVigencias(
			Set consumoTarifaVigencias) {
		this.consumoTarifaVigencias = consumoTarifaVigencias;
	}

	/**
	 * @return Retorna o campo ligacaoAguaPerfil.
	 */
	public LigacaoAguaPerfil getLigacaoAguaPerfil() {
		return ligacaoAguaPerfil;
	}

	/**
	 * @param ligacaoAguaPerfil O ligacaoAguaPerfil a ser setado.
	 */
	public void setLigacaoAguaPerfil(LigacaoAguaPerfil ligacaoAguaPerfil) {
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
	}

	public TarifaTipoCalculo getTarifaTipoCalculo() {
		return tarifaTipoCalculo;
	}

	public void setTarifaTipoCalculo(TarifaTipoCalculo tarifaTipoCalculo) {
		this.tarifaTipoCalculo = tarifaTipoCalculo;
	}
}
