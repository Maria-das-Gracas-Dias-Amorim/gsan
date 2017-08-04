package gcom.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.CobrancaSituacao;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ImovelCobrancaSituacao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date dataImplantacaoCobranca;

	private Date dataRetiradaCobranca;

	private Date ultimaAlteracao;

	private gcom.cadastro.imovel.Imovel imovel;

	private CobrancaSituacao cobrancaSituacao;

	private Integer anoMesReferenciaInicio;

	private Integer anoMesReferenciaFinal;

	private Cliente cliente;

	private ContaMotivoRevisao contaMotivoRevisao;

	private Cliente escritorio;

	private Cliente advogado;

	public ImovelCobrancaSituacao(
			Date dataImplantacaoCobranca,
			Date dataRetiradaCobranca, 
			Integer cpfCobranca, 
			Integer cnpjCobranca, 
			Date ultimaAlteracao, 
			Imovel imovel,
			CobrancaSituacao cobrancaSituacao, 
			Integer anoMesReferenciaInicio, 
			Integer anoMesReferenciaFinal, 
			Cliente cliente, 
			ContaMotivoRevisao contaMotivoRevisao) {
		
		this.dataImplantacaoCobranca = dataImplantacaoCobranca;
		this.dataRetiradaCobranca = dataRetiradaCobranca;
		this.ultimaAlteracao = ultimaAlteracao;
		this.imovel = imovel;
		this.cobrancaSituacao = cobrancaSituacao;
		this.anoMesReferenciaInicio = anoMesReferenciaInicio;
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;

	}

	public ImovelCobrancaSituacao() {
	}

	public ImovelCobrancaSituacao(
			Date dataImplantacaoCobranca,
			Imovel imovel, 
			CobrancaSituacao cobrancaSituacao) {
		
		this.dataImplantacaoCobranca = dataImplantacaoCobranca;
		this.imovel = imovel;
		this.cobrancaSituacao = cobrancaSituacao;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataImplantacaoCobranca() {
		return this.dataImplantacaoCobranca;
	}

	public void setDataImplantacaoCobranca(Date dataImplantacaoCobranca) {
		this.dataImplantacaoCobranca = dataImplantacaoCobranca;
	}

	public Date getDataRetiradaCobranca() {
		return this.dataRetiradaCobranca;
	}

	public void setDataRetiradaCobranca(Date dataRetiradaCobranca) {
		this.dataRetiradaCobranca = dataRetiradaCobranca;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.cadastro.imovel.Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
		this.imovel = imovel;
	}

	public CobrancaSituacao getCobrancaSituacao() {
		return this.cobrancaSituacao;
	}

	public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Integer getAnoMesReferenciaFinal() {
		return anoMesReferenciaFinal;
	}

	public void setAnoMesReferenciaFinal(Integer anoMesReferenciaFinal) {
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
	}

	public Integer getAnoMesReferenciaInicio() {
		return anoMesReferenciaInicio;
	}

	public void setAnoMesReferenciaInicio(Integer anoMesReferenciaInicio) {
		this.anoMesReferenciaInicio = anoMesReferenciaInicio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ContaMotivoRevisao getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}

	public void setContaMotivoRevisao(ContaMotivoRevisao contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}

	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroImovelCobrancaSituacao();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelCobrancaSituacao.ID, this.getId()));
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtro.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");

		return filtro;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public Cliente getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Cliente advogado) {
		this.advogado = advogado;
	}

	public Cliente getEscritorio() {
		return escritorio;
	}

	public void setEscritorio(Cliente escritorio) {
		this.escritorio = escritorio;
	}
}
