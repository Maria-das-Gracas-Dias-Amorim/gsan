package gcom.cadastro.localidade;

import gcom.cadastro.dadocensitario.IbgeSetorCensitario;
import gcom.cadastro.geografico.Bairro;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Rota;
import gcom.micromedicao.RoteiroEmpresa;
import gcom.operacional.Bacia;
import gcom.operacional.DistritoOperacional;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Quadra extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public final static Short COM_REDE = new Short("2");
	public final static String SIM = "SIM";

	public final static Short SEM_REDE = new Short("1");
	public final static String NAO = "NAO";

	public final static Short REDE_PARCIAL = new Short("3");
	public final static String PARCIAL = "PARCIAL";
	
	public final static Short BLOQUEIO_INSERIR_IMOVEL_SIM = new Short("1");
	public final static Short BLOQUEIO_INSERIR_IMOVEL_NAO = new Short("2");

	/** identifier field */
	private Integer id;

	/** persistent field */
	private int numeroQuadra;

	/** nullable persistent field */
	private Short indicadorRedeAgua;

	/** nullable persistent field */
	private Short indicadorRedeEsgoto;

	/** nullable persistent field */
	private Short numeroRotaSequencia;

	/** nullable persistent field */
	private Date dataImplantacao;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Rota rota;

	/** persistent field */
	private IbgeSetorCensitario ibgeSetorCensitario;

	/** persistent field */
	private gcom.cadastro.localidade.SetorComercial setorComercial;

	/** persistent field */
	private DistritoOperacional distritoOperacional;

	/** persistent field */
	private Bacia bacia;

	/** persistent field */
	private gcom.cadastro.localidade.Zeis zeis;

	/** persistent field */
	private gcom.cadastro.localidade.QuadraPerfil quadraPerfil;

	private AreaTipo areaTipo;
	
	private RoteiroEmpresa roteiroEmpresa;
	
	private Short indicadorIncrementoLote;
	
	private Short indicadorBloqueio = BLOQUEIO_INSERIR_IMOVEL_NAO;
	
	private Bairro bairro;

	/** full constructor */
	public Quadra(int descricao, Short indicadorRedeAgua,
			Short indicadorRedeEsgoto, Short numeroRotaSequencia,
			Date dataImplantacao, Short indicadorUso, Date ultimaAlteracao,
			Rota rota, IbgeSetorCensitario ibgeSetorCensitario,
			gcom.cadastro.localidade.SetorComercial setorComercial,
			DistritoOperacional distritoOperacional, Bacia bacia,
			gcom.cadastro.localidade.Zeis zeis,
			gcom.cadastro.localidade.QuadraPerfil quadraPerfil, int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
		this.indicadorRedeAgua = indicadorRedeAgua;
		this.indicadorRedeEsgoto = indicadorRedeEsgoto;
		this.numeroRotaSequencia = numeroRotaSequencia;
		this.dataImplantacao = dataImplantacao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.rota = rota;
		this.ibgeSetorCensitario = ibgeSetorCensitario;
		this.setorComercial = setorComercial;
		this.distritoOperacional = distritoOperacional;
		this.bacia = bacia;
		this.zeis = zeis;
		this.quadraPerfil = quadraPerfil;
	}

	public Quadra() {
	}

	public Quadra(Integer id, int numeroQuadra, Rota rota) {
		this.id = id;
		this.numeroQuadra = numeroQuadra;
		this.rota = rota;
	}

	public Quadra(Integer id) {
		this.id = id;
	}

	public Quadra(int descricao, Rota rota,
			IbgeSetorCensitario ibgeSetorCensitario,
			gcom.cadastro.localidade.SetorComercial setorComercial,
			DistritoOperacional distritoOperacional, Bacia bacia,
			gcom.cadastro.localidade.Zeis zeis,
			gcom.cadastro.localidade.QuadraPerfil quadraPerfil, int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
		this.rota = rota;
		this.ibgeSetorCensitario = ibgeSetorCensitario;
		this.setorComercial = setorComercial;
		this.distritoOperacional = distritoOperacional;
		this.bacia = bacia;
		this.zeis = zeis;
		this.quadraPerfil = quadraPerfil;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumeroQuadra() {
		return this.numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getIndicadorRedeAgua() {
		return this.indicadorRedeAgua;
	}

	public void setIndicadorRedeAgua(Short indicadorRedeAgua) {
		this.indicadorRedeAgua = indicadorRedeAgua;
	}

	public Short getIndicadorRedeEsgoto() {
		return this.indicadorRedeEsgoto;
	}

	public void setIndicadorRedeEsgoto(Short indicadorRedeEsgoto) {
		this.indicadorRedeEsgoto = indicadorRedeEsgoto;
	}

	public Short getNumeroRotaSequencia() {
		return this.numeroRotaSequencia;
	}

	public void setNumeroRotaSequencia(Short numeroRotaSequencia) {
		this.numeroRotaSequencia = numeroRotaSequencia;
	}

	public Date getDataImplantacao() {
		return this.dataImplantacao;
	}

	public void setDataImplantacao(Date dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
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

	public Rota getRota() {
		return this.rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public IbgeSetorCensitario getIbgeSetorCensitario() {
		return this.ibgeSetorCensitario;
	}

	public void setIbgeSetorCensitario(IbgeSetorCensitario ibgeSetorCensitario) {
		this.ibgeSetorCensitario = ibgeSetorCensitario;
	}

	public gcom.cadastro.localidade.SetorComercial getSetorComercial() {
		return this.setorComercial;
	}

	public void setSetorComercial(
			gcom.cadastro.localidade.SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public DistritoOperacional getDistritoOperacional() {
		return this.distritoOperacional;
	}

	public void setDistritoOperacional(DistritoOperacional distritoOperacional) {
		this.distritoOperacional = distritoOperacional;
	}

	public Bacia getBacia() {
		return this.bacia;
	}

	public void setBacia(Bacia bacia) {
		this.bacia = bacia;
	}

	public gcom.cadastro.localidade.Zeis getZeis() {
		return this.zeis;
	}

	public void setZeis(gcom.cadastro.localidade.Zeis zeis) {
		this.zeis = zeis;
	}

	public gcom.cadastro.localidade.QuadraPerfil getQuadraPerfil() {
		return this.quadraPerfil;
	}

	public void setQuadraPerfil(
			gcom.cadastro.localidade.QuadraPerfil quadraPerfil) {
		this.quadraPerfil = quadraPerfil;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("ibgeSetorCensitario");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bacia");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("zeis");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("quadraPerfil");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("areaTipo");

		filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, this.getId()));
		return filtroQuadra;
	}

	public AreaTipo getAreaTipo() {
		return areaTipo;
	}

	public void setAreaTipo(AreaTipo areaTipo) {
		this.areaTipo = areaTipo;
	}

	public RoteiroEmpresa getRoteiroEmpresa() {
		return roteiroEmpresa;
	}

	public void setRoteiroEmpresa(RoteiroEmpresa roteiroEmpresa) {
		this.roteiroEmpresa = roteiroEmpresa;
	}

	@Override
	public String[] retornarAtributosSelecionadosRegistro() {
		String[] atributos = {"roteiroEmpresa"};
		return atributos;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] atributos = {"numeroQuadra", "setorComercial.codigo", "setorComercial.localidade.descricao"};
		return atributos;
	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = {"N�mero da quadra", "C�digo do Setor Comercial", "Localidade"};
		return labels;
	}
	
	@Override
	public void initializeLazy() {
		if (getSetorComercial() != null){
			getSetorComercial().initializeLazy();
		}
	}
	
	public String getDescricao(){
		return numeroQuadra +""; 
	}
	
	/**
	 * @return Retorna o campo indicadorIncrementoLote.
	 */
	public Short getIndicadorIncrementoLote() {
		return indicadorIncrementoLote;
	}

	/**
	 * @param indicadorIncrementoLote O indicadorIncrementoLote a ser setado.
	 */
	public void setIndicadorIncrementoLote(Short indicadorIncrementoLote) {
		this.indicadorIncrementoLote = indicadorIncrementoLote;
	}
	
	public Short getIndicadorBloqueio() {
		return indicadorBloqueio;
	}

	public void setIndicadorBloqueio(Short indicadorBloqueio) {
		this.indicadorBloqueio = indicadorBloqueio;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
}
