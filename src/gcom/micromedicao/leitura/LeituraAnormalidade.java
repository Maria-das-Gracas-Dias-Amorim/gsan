package gcom.micromedicao.leitura;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
@ControleAlteracao
public class LeituraAnormalidade extends ObjetoTransacao {

	public static final int ALTERAR_DADOS_DO_FATURAMENTO = 436;

	private static final long serialVersionUID = 1L;

	/**
	 * identifier field
	 */
	private Integer id;

	/**
	 * persistent field
	 */
	private String descricao;

	/**
	 * persistent field
	 */
	private String descricaoAbreviada;

	/**
	 * persistent field
	 */
	private short indicadorRelativoHidrometro;

	/**
	 * nullable persistent field
	 */
	private Short indicadorImovelSemHidrometro;

	/**
	 * nullable persistent field
	 */
	private Short indicadorSistema;

	/**
	 * persistent field
	 */
	private short indicadorEmissaoOrdemServico;

	/**
	 * nullable persistent field
	 */
	private Short indicadorUso;

	/**
	 * nullable persistent field
	 */
	// private Short servCdservico;
	/**
	 * nullable persistent field
	 */
	private Short indicadorPerdaTarifaSocial;

	/**
	 * nullable persistent field
	 */
	private Date ultimaAlteracao;

	private BigDecimal numeroFatorSemLeitura;

	private BigDecimal numeroFatorComLeitura;

	private Short indicadorLeitura;

	/**
	 * TODO : COSANPA
	 * Pamela Gatinho - 13/03/2012
	 * Campo que identifica se a anormalidade ser� usada ou
	 * n�o no sistema de leitura e impress�o simultanea.
	 */
	private Short indicadorImpressaoSimultanea;
	
	/**
	 * persistent field
	 */
	private LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComleitura;

	/**
	 * persistent field
	 */
	private LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemleitura;

	/**
	 * persistent field
	 */
	private LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComleitura;

	/**
	 * persistent field
	 */
	private LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemleitura;

	private ServicoTipo servicoTipo;

	private Integer numeroMesesLeituraSuspensa;

	private Integer numeroVezesSuspendeLeitura;

	// --CONSTANTES
	/**
	 * Description of the Field
	 */
	public final static Integer HIDROMETRO_PARADO = new Integer(30);

	public final static Integer HIDROMETRO_SUBSTITUIDO = new Integer(32);

	/**
	 * Description of the Field
	 */
	public final static Integer HIDROMETRO_PARADO_SEM_CONSUMO = new Integer(38);

	
	public final static Integer HIDROMETRO_RETIRADO  = new Integer(2);
	
	/**
	 * Description of the Field
	 */
	public final static Integer INDICADOR_PERDA_TARIFA_SOCIAL = new Integer(1);

	/**
	 * Description of the Field
	 */
	public final static Integer INDICADOR_NAO_PERDA_TARIFA_SOCIAL = new Integer(2);

	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_IMOVEL_SEM_HIDROMETRO = new Short("2");

	/**
	 * Description of the Field
	 */
	public final static Integer INDICADOR_LIGADO_CLANDESTINO_AGUA = new Integer("81");

	/**
	 * Description of the Field
	 */
	public final static Integer INDICADOR_LIGADO_CLANDESTINO_ESGOTO = new Integer("82");

	/**
	 * Description of the Field
	 */
	public final static Integer INDICADOR_LIGADO_CLANDESTINO_AGUA_ESGOTO = new Integer("83");

	/**
	 * full constructor
	 * 
	 * @param descricao
	 *            Descri��o do par�metro
	 * @param descricaoAbreviada
	 *            Descri��o do par�metro
	 * @param indicadorRelativoHidrometro
	 *            Descri��o do par�metro
	 * @param indicadorImovelSemHidrometro
	 *            Descri��o do par�metro
	 * @param indicadorSistema
	 *            Descri��o do par�metro
	 * @param indicadorEmissaoOrdemServico
	 *            Descri��o do par�metro
	 * @param indicadorUso
	 *            Descri��o do par�metro
	 * @param servCdservico
	 *            Descri��o do par�metro
	 * @param indicadorPerdaTarifaSocial
	 *            Descri��o do par�metro
	 * @param ultimaAlteracao
	 *            Descri��o do par�metro
	 * @param leituraAnormalidadeLeituraComleitura
	 *            Descri��o do par�metro
	 * @param leituraAnormalidadeLeituraSemleitura
	 *            Descri��o do par�metro
	 * @param leituraAnormalidadeConsumoComleitura
	 *            Descri��o do par�metro
	 * @param leituraAnormalidadeConsumoSemleitura
	 *            Descri��o do par�metro
	 */
	public LeituraAnormalidade(
			String descricao, String descricaoAbreviada, short indicadorRelativoHidrometro,
			Short indicadorImovelSemHidrometro, Short indicadorSistema, short indicadorEmissaoOrdemServico,
			Short indicadorUso,
			// Short servCdservico,
			Short indicadorPerdaTarifaSocial, Date ultimaAlteracao,
			gcom.micromedicao.leitura.LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComleitura,
			gcom.micromedicao.leitura.LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemleitura,
			gcom.micromedicao.leitura.LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComleitura,
			gcom.micromedicao.leitura.LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemleitura,
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorRelativoHidrometro = indicadorRelativoHidrometro;
		this.indicadorImovelSemHidrometro = indicadorImovelSemHidrometro;
		this.indicadorSistema = indicadorSistema;
		this.indicadorEmissaoOrdemServico = indicadorEmissaoOrdemServico;
		this.indicadorUso = indicadorUso;
		// this.servCdservico = servCdservico;
		this.indicadorPerdaTarifaSocial = indicadorPerdaTarifaSocial;
		this.ultimaAlteracao = ultimaAlteracao;
		this.leituraAnormalidadeLeituraComleitura = leituraAnormalidadeLeituraComleitura;
		this.leituraAnormalidadeLeituraSemleitura = leituraAnormalidadeLeituraSemleitura;
		this.leituraAnormalidadeConsumoComleitura = leituraAnormalidadeConsumoComleitura;
		this.leituraAnormalidadeConsumoSemleitura = leituraAnormalidadeConsumoSemleitura;
		this.servicoTipo = servicoTipo;
	}

	/**
	 * @return Retorna o campo servicoTipo.
	 */
	public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	/**
	 * @param servicoTipo
	 *            O servicoTipo a ser setado.
	 */
	public void setServicoTipo(gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	/**
	 * default constructor
	 */
	public LeituraAnormalidade() {
	}

	/**
	 * minimal constructor
	 * 
	 * @param descricao
	 *            Descri��o do par�metro
	 * @param descricaoAbreviada
	 *            Descri��o do par�metro
	 * @param indicadorRelativoHidrometro
	 *            Descri��o do par�metro
	 * @param indicadorEmissaoOrdemServico
	 *            Descri��o do par�metro
	 * @param leituraAnormalidadeLeituraComleitura
	 *            Descri��o do par�metro
	 * @param leituraAnormalidadeLeituraSemleitura
	 *            Descri��o do par�metro
	 * @param leituraAnormalidadeConsumoComleitura
	 *            Descri��o do par�metro
	 * @param leituraAnormalidadeConsumoSemleitura
	 *            Descri��o do par�metro
	 */
	public LeituraAnormalidade(
			String descricao, String descricaoAbreviada, short indicadorRelativoHidrometro,
			short indicadorEmissaoOrdemServico,
			gcom.micromedicao.leitura.LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComleitura,
			gcom.micromedicao.leitura.LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemleitura,
			gcom.micromedicao.leitura.LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComleitura,
			gcom.micromedicao.leitura.LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemleitura) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorRelativoHidrometro = indicadorRelativoHidrometro;
		this.indicadorEmissaoOrdemServico = indicadorEmissaoOrdemServico;
		this.leituraAnormalidadeLeituraComleitura = leituraAnormalidadeLeituraComleitura;
		this.leituraAnormalidadeLeituraSemleitura = leituraAnormalidadeLeituraSemleitura;
		this.leituraAnormalidadeConsumoComleitura = leituraAnormalidadeConsumoComleitura;
		this.leituraAnormalidadeConsumoSemleitura = leituraAnormalidadeConsumoSemleitura;
	}

	/**
	 * Retorna o valor de id
	 * 
	 * @return O valor de id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Seta o valor de id
	 * 
	 * @param id
	 *            O novo valor de id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Retorna o valor de descricao
	 * 
	 * @return O valor de descricao
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Seta o valor de descricao
	 * 
	 * @param descricao
	 *            O novo valor de descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Retorna o valor de descricaoAbreviada
	 * 
	 * @return O valor de descricaoAbreviada
	 */
	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	/**
	 * Seta o valor de descricaoAbreviada
	 * 
	 * @param descricaoAbreviada
	 *            O novo valor de descricaoAbreviada
	 */
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	/**
	 * Retorna o valor de indicadorRelativoHidrometro
	 * 
	 * @return O valor de indicadorRelativoHidrometro
	 */
	public short getIndicadorRelativoHidrometro() {
		return this.indicadorRelativoHidrometro;
	}

	/**
	 * Seta o valor de indicadorRelativoHidrometro
	 * 
	 * @param indicadorRelativoHidrometro
	 *            O novo valor de indicadorRelativoHidrometro
	 */
	public void setIndicadorRelativoHidrometro(short indicadorRelativoHidrometro) {
		this.indicadorRelativoHidrometro = indicadorRelativoHidrometro;
	}

	/**
	 * Retorna o valor de indicadorImovelSemHidrometro
	 * 
	 * @return O valor de indicadorImovelSemHidrometro
	 */
	public Short getIndicadorImovelSemHidrometro() {
		return this.indicadorImovelSemHidrometro;
	}

	/**
	 * Seta o valor de indicadorImovelSemHidrometro
	 * 
	 * @param indicadorImovelSemHidrometro
	 *            O novo valor de indicadorImovelSemHidrometro
	 */
	public void setIndicadorImovelSemHidrometro(Short indicadorImovelSemHidrometro) {
		this.indicadorImovelSemHidrometro = indicadorImovelSemHidrometro;
	}

	/**
	 * Retorna o valor de indicadorSistema
	 * 
	 * @return O valor de indicadorSistema
	 */
	public Short getIndicadorSistema() {
		return this.indicadorSistema;
	}

	/**
	 * Seta o valor de indicadorSistema
	 * 
	 * @param indicadorSistema
	 *            O novo valor de indicadorSistema
	 */
	public void setIndicadorSistema(Short indicadorSistema) {
		this.indicadorSistema = indicadorSistema;
	}

	/**
	 * Retorna o valor de indicadorEmissaoOrdemServico
	 * 
	 * @return O valor de indicadorEmissaoOrdemServico
	 */
	public short getIndicadorEmissaoOrdemServico() {
		return this.indicadorEmissaoOrdemServico;
	}

	/**
	 * Seta o valor de indicadorEmissaoOrdemServico
	 * 
	 * @param indicadorEmissaoOrdemServico
	 *            O novo valor de indicadorEmissaoOrdemServico
	 */
	public void setIndicadorEmissaoOrdemServico(short indicadorEmissaoOrdemServico) {
		this.indicadorEmissaoOrdemServico = indicadorEmissaoOrdemServico;
	}

	/**
	 * Retorna o valor de indicadorUso
	 * 
	 * @return O valor de indicadorUso
	 */
	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	/**
	 * Seta o valor de indicadorUso
	 * 
	 * @param indicadorUso
	 *            O novo valor de indicadorUso
	 */
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	// /**
	// * Retorna o valor de servCdservico
	// *
	// * @return O valor de servCdservico
	// */
	// public Short getServCdservico() {
	// return this.servCdservico;
	// }
	//
	// /**
	// * Seta o valor de servCdservico
	// *
	// * @param servCdservico
	// * O novo valor de servCdservico
	// */
	// public void setServCdservico(Short servCdservico) {
	// this.servCdservico = servCdservico;
	// }

	/**
	 * Retorna o valor de indicadorPerdaTarifaSocial
	 * 
	 * @return O valor de indicadorPerdaTarifaSocial
	 */
	public Short getIndicadorPerdaTarifaSocial() {
		return this.indicadorPerdaTarifaSocial;
	}

	/**
	 * Seta o valor de indicadorPerdaTarifaSocial
	 * 
	 * @param indicadorPerdaTarifaSocial
	 *            O novo valor de indicadorPerdaTarifaSocial
	 */
	public void setIndicadorPerdaTarifaSocial(Short indicadorPerdaTarifaSocial) {
		this.indicadorPerdaTarifaSocial = indicadorPerdaTarifaSocial;
	}

	/**
	 * Retorna o valor de ultimaAlteracao
	 * 
	 * @return O valor de ultimaAlteracao
	 */
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	/**
	 * Seta o valor de ultimaAlteracao
	 * 
	 * @param ultimaAlteracao
	 *            O novo valor de ultimaAlteracao
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * Retorna o valor de leituraAnormalidadeLeituraComleitura
	 * 
	 * @return O valor de leituraAnormalidadeLeituraComleitura
	 */
	public gcom.micromedicao.leitura.LeituraAnormalidadeLeitura getLeituraAnormalidadeLeituraComleitura() {
		return this.leituraAnormalidadeLeituraComleitura;
	}

	/**
	 * Seta o valor de leituraAnormalidadeLeituraComleitura
	 * 
	 * @param leituraAnormalidadeLeituraComleitura
	 *            O novo valor de leituraAnormalidadeLeituraComleitura
	 */
	public void setLeituraAnormalidadeLeituraComleitura(
			gcom.micromedicao.leitura.LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComleitura) {
		this.leituraAnormalidadeLeituraComleitura = leituraAnormalidadeLeituraComleitura;
	}

	/**
	 * Retorna o valor de leituraAnormalidadeLeituraSemleitura
	 * 
	 * @return O valor de leituraAnormalidadeLeituraSemleitura
	 */
	public gcom.micromedicao.leitura.LeituraAnormalidadeLeitura getLeituraAnormalidadeLeituraSemleitura() {
		return this.leituraAnormalidadeLeituraSemleitura;
	}

	/**
	 * Seta o valor de leituraAnormalidadeLeituraSemleitura
	 * 
	 * @param leituraAnormalidadeLeituraSemleitura
	 *            O novo valor de leituraAnormalidadeLeituraSemleitura
	 */
	public void setLeituraAnormalidadeLeituraSemleitura(
			gcom.micromedicao.leitura.LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemleitura) {
		this.leituraAnormalidadeLeituraSemleitura = leituraAnormalidadeLeituraSemleitura;
	}

	/**
	 * Retorna o valor de leituraAnormalidadeConsumoComleitura
	 * 
	 * @return O valor de leituraAnormalidadeConsumoComleitura
	 */
	public gcom.micromedicao.leitura.LeituraAnormalidadeConsumo getLeituraAnormalidadeConsumoComleitura() {
		return this.leituraAnormalidadeConsumoComleitura;
	}

	/**
	 * Seta o valor de leituraAnormalidadeConsumoComleitura
	 * 
	 * @param leituraAnormalidadeConsumoComleitura
	 *            O novo valor de leituraAnormalidadeConsumoComleitura
	 */
	public void setLeituraAnormalidadeConsumoComleitura(
			gcom.micromedicao.leitura.LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComleitura) {
		this.leituraAnormalidadeConsumoComleitura = leituraAnormalidadeConsumoComleitura;
	}

	/**
	 * Retorna o valor de leituraAnormalidadeConsumoSemleitura
	 * 
	 * @return O valor de leituraAnormalidadeConsumoSemleitura
	 */
	public gcom.micromedicao.leitura.LeituraAnormalidadeConsumo getLeituraAnormalidadeConsumoSemleitura() {
		return this.leituraAnormalidadeConsumoSemleitura;
	}

	/**
	 * Seta o valor de leituraAnormalidadeConsumoSemleitura
	 * 
	 * @param leituraAnormalidadeConsumoSemleitura
	 *            O novo valor de leituraAnormalidadeConsumoSemleitura
	 */
	public void setLeituraAnormalidadeConsumoSemleitura(
			gcom.micromedicao.leitura.LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemleitura) {
		this.leituraAnormalidadeConsumoSemleitura = leituraAnormalidadeConsumoSemleitura;
	}

	public Short getIndicadorLeitura() {
		return indicadorLeitura;
	}

	public void setIndicadorLeitura(Short indicadorLeitura) {
		this.indicadorLeitura = indicadorLeitura;
	}

	public BigDecimal getNumeroFatorComLeitura() {
		return numeroFatorComLeitura;
	}

	public void setNumeroFatorComLeitura(BigDecimal numeroFatorComLeitura) {
		this.numeroFatorComLeitura = numeroFatorComLeitura;
	}

	public BigDecimal getNumeroFatorSemLeitura() {
		return numeroFatorSemLeitura;
	}

	public void setNumeroFatorSemLeitura(BigDecimal numeroFatorSemLeitura) {
		this.numeroFatorSemLeitura = numeroFatorSemLeitura;
	}

	public Integer getNumeroMesesLeituraSuspensa() {
		return numeroMesesLeituraSuspensa;
	}

	public void setNumeroMesesLeituraSuspensa(Integer numeroMesesLeituraSuspensa) {
		this.numeroMesesLeituraSuspensa = numeroMesesLeituraSuspensa;
	}

	public Integer getNumeroVezesSuspendeLeitura() {
		return numeroVezesSuspendeLeitura;
	}

	public void setNumeroVezesSuspendeLeitura(Integer numeroVezesSuspendeLeitura) {
		this.numeroVezesSuspendeLeitura = numeroVezesSuspendeLeitura;
	}

	public short getIndicadorImpressaoSimultanea() {
		return this.indicadorImpressaoSimultanea;
	}
	
	public void setIndicadorImpressaoSimultanea(short indicadorImpressaoSimultanea) {
		this.indicadorImpressaoSimultanea = indicadorImpressaoSimultanea;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();

		filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidade.ID,
																			this.getId()));
		filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
		filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoSemleitura");
		filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoComleitura");
		filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraSemleitura");
		filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraComleitura");

		return filtroLeituraAnormalidade;
	}
}
