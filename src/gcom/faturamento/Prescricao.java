package gcom.faturamento;import gcom.cadastro.cliente.EsferaPoder;import gcom.interceptor.ObjetoTransacao;import gcom.util.filtro.Filtro;import gcom.util.filtro.ParametroSimples;import java.util.Date;
/** @author Hibernate CodeGenerator *//** * Descri��o da classe *  * @author Hugo Leonardo * @date 18/10/2010 */
public class Prescricao extends ObjetoTransacao{	private static final long serialVersionUID = 1L;	/**	 * persistent field	 */
	private Integer id;	/**	 * persistent field	 */	private Integer anoMesReferencia;		/**	 * persistent field	 */	private EsferaPoder esferaPoder1;		/**	 * persistent field	 */	private EsferaPoder esferaPoder2;			private Date ultimaAlteracao;	public String[] retornaCamposChavePrimaria(){		String[] retorno = new String[1];		retorno[0] = "id";		return retorno;	}
	public Prescricao() {
	}	public Integer getAnoMesReferencia() {		return anoMesReferencia;	}	public void setAnoMesReferencia(Integer anoMesReferencia) {		this.anoMesReferencia = anoMesReferencia;	}	public EsferaPoder getEsferaPoder1() {		return esferaPoder1;	}	public void setEsferaPoder1(EsferaPoder esferaPoder1) {		this.esferaPoder1 = esferaPoder1;	}	public EsferaPoder getEsferaPoder2() {		return esferaPoder2;	}	public void setEsferaPoder2(EsferaPoder esferaPoder2) {		this.esferaPoder2 = esferaPoder2;	}	public Integer getId() {		return id;	}	public void setId(Integer id) {		this.id = id;	}	public Date getUltimaAlteracao() {		return ultimaAlteracao;	}	public void setUltimaAlteracao(Date ultimaAlteracao) {		this.ultimaAlteracao = ultimaAlteracao;	}	@Override	public String getDescricaoParaRegistroTransacao() {		return getId().toString();	}		@Override	public Filtro retornaFiltroRegistroOperacao() {		Filtro filtro = retornaFiltro();				return filtro;	}		public Filtro retornaFiltro() {		FiltroPrescricao filtroPrescricao = new FiltroPrescricao();		filtroPrescricao.adicionarParametro(new ParametroSimples(FiltroPrescricao.ID, this.getId()));				return filtroPrescricao;	}	}
