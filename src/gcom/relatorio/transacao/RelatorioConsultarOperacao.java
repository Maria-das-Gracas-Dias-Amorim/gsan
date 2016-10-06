package gcom.relatorio.transacao;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAlteracao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAlteracao;
import gcom.seguranca.transacao.FiltroTabelaLinhaColunaAlteracao;
import gcom.seguranca.transacao.TabelaLinhaColunaAlteracao;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * classe respons�vel por criar o relat�rio de opera��o consultar
 * 
 * @author Rafael Corr�a
 * @created 06/04/2006
 */
public class RelatorioConsultarOperacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioConsultarOperacao(Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_OPERACAO_CONSULTAR);
	}
	
	@Deprecated
	public RelatorioConsultarOperacao() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	
	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {
	
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		String[]    idOperacoes              = (String[])    getParametro("idOperacoes");
		Usuario     usuario                  = (Usuario)     getParametro("usuario");
		UsuarioAcao usuarioAcao              = (UsuarioAcao) getParametro("usuarioAcao");
		Date        periodoRealizacaoInicial = (Date)        getParametro("periodoRealizacaoInicial");
		Date        periodoRealizacaoFinal   = (Date)        getParametro("periodoRealizacaoFinal");
		Date        horarioRealizacaoInicial = (Date)        getParametro("horarioRealizacaoInicial");
		Date        horarioRealizacaoFinal   = (Date)        getParametro("horarioRealizacaoFinal");
		Integer     id1                      = (Integer)     getParametro("id1");
		int         tipoFormatoRelatorio     = (Integer)     getParametro("tipoFormatoRelatorio");
		String      tipoRelatorio            = (String)      getParametro("tipoRelatorio");
		String      unidadeNegocio           = (String)      getParametro("unidadeNegocio");
		Integer     idFuncionalidade         = (Integer)     getParametro("idFuncionalidade");
		Hashtable<String, String> argumentos = (Hashtable<String, String>) getParametro("argumentos");

		
		// valor de retorno
		byte[] retorno = null;

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoOperacoesEfetuadas =   null;

		colecaoOperacoesEfetuadas = fachada.pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(
		usuarioAcao.getId() , idOperacoes , usuario.getLogin(), periodoRealizacaoInicial , 
		periodoRealizacaoFinal , horarioRealizacaoInicial , horarioRealizacaoFinal , 
		argumentos , id1 , unidadeNegocio );
		
		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioConsultarOperacaoBean relatorioBean = null;
		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoOperacoesEfetuadas != null
				&& !colecaoOperacoesEfetuadas.isEmpty()) {
			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoOperacoesEfetuadasIterator = colecaoOperacoesEfetuadas
					.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoOperacoesEfetuadasIterator.hasNext()) {

	OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) colecaoOperacoesEfetuadasIterator.next();

	FiltroUsuarioAlteracao filtroUsuarioAlteracao = new FiltroUsuarioAlteracao();
	filtroUsuarioAlteracao.adicionarParametro(new ParametroSimples( FiltroUsuarioAlteracao.OPERACAO_EFETUADA_ID ,
						operacaoEfetuada.getId()));
	
	filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade("usuario");

	Collection colecaoUsuarioAlteracao = fachada.pesquisar( filtroUsuarioAlteracao ,
			UsuarioAlteracao.class.getName());
	
				String nomeUsuario = "";
				String ipUsuario   = "";

				if ( colecaoUsuarioAlteracao != null
						&& !colecaoUsuarioAlteracao.isEmpty()) {
		UsuarioAlteracao usuarioAlteracao = (UsuarioAlteracao) colecaoUsuarioAlteracao.iterator().next();
	
		nomeUsuario = usuarioAlteracao.getUsuario().getNomeUsuario();

		ipUsuario = usuarioAlteracao.getIpAlteracao();
				}
				
			FiltroTabelaLinhaColunaAlteracao filtroTabelaLinhaColunaAlteracao = new FiltroTabelaLinhaColunaAlteracao();
			filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_COLUNA);
			filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA_ID, operacaoEfetuada.getId()));
				
Collection<TabelaLinhaColunaAlteracao> colecaoTabelaLinhaColunaAlteracao = (Collection<TabelaLinhaColunaAlteracao>) fachada.pesquisar(filtroTabelaLinhaColunaAlteracao, TabelaLinhaColunaAlteracao.class.getName());
					
	for ( TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao : colecaoTabelaLinhaColunaAlteracao ) {

					relatorioBean = new RelatorioConsultarOperacaoBean(
								
							// Data Realiza��o
							operacaoEfetuada.getUltimaAlteracao(),
						
							// Nome da operacao
							operacaoEfetuada.getOperacao().getDescricao(),
						
							// dados
							operacaoEfetuada.getArgumentoValor() + "",
					
							// outros dados
							consultarInformacoesExtrasOperacaoEfetuada(operacaoEfetuada),
						
							// Usu�rio
							nomeUsuario,
								
							// Id Opera��o Efetuada
							operacaoEfetuada.getId(),
								
							// Campo
							tabelaLinhaColunaAlteracao.getTabelaColuna().getDescricaoColuna(),
								
							// Conte�do Anterior
							tabelaLinhaColunaAlteracao.getConteudoColunaAnterior(),
								
							// Conte�do Atual
							tabelaLinhaColunaAlteracao.getConteudoColunaAtual(),
								
							// Data da Atualiza��o
							tabelaLinhaColunaAlteracao.getUltimaAlteracao(),
							
							ipUsuario
						);

					// adiciona o bean a cole��o
					relatorioBeans.add(relatorioBean);
				}
			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		if (tipoRelatorio != null) {
			parametros.put("tipoRelatorio", tipoRelatorio);
		} else {
			parametros.put("tipoRelatorio", "");
		}

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		Operacao operacao = null;
		if (idOperacoes != null && idOperacoes.length == 1){
			FiltroOperacao filtro = new FiltroOperacao();
			filtro.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, idOperacoes[0]));
			Collection operacoes = Fachada.getInstancia().pesquisar(filtro, Operacao.class.getSimpleName());			
			operacao = (Operacao) Util.retonarObjetoDeColecao(operacoes);
			
		}
		if (operacao != null){
			parametros.put("idOperacao", operacao.getId() == null? "": "" + operacao.getId());
			parametros.put("nomeOperacao", operacao.getDescricao());				
		} else {
			parametros.put("idOperacao", "");
			parametros.put("nomeOperacao", "");				
		}
		

		parametros.put("idUsuario", usuario.getId() == null ? "": "" + usuario.getId());

		parametros.put("nomeUsuario", usuario.getNomeUsuario());

		parametros.put("acaoUsuario", usuarioAcao.getDescricao());

		if (periodoRealizacaoInicial != null && periodoRealizacaoFinal != null) {
			parametros.put("periodoRealizacao", "" + Util.formatarData(periodoRealizacaoInicial) + " a " + Util.formatarData(periodoRealizacaoFinal));
		} else {
			parametros.put("periodoRealizacao", "");
		}

		if (horarioRealizacaoInicial != null && horarioRealizacaoFinal != null) {
			parametros.put("horarioRealizacao", "" +Util.formatarHoraSemData(horarioRealizacaoInicial) + " a " + Util.formatarHoraSemData(horarioRealizacaoFinal));
		} else {
			parametros.put("horarioRealizacao", "");
		}
		
		if( idFuncionalidade != null ){
			parametros.put( "idFuncionalidade" , "" +idFuncionalidade );
		}
		else{
			parametros.put( "idFuncionalidade" , "" );
		}
		if( unidadeNegocio != null ){
			parametros.put( "unidadeNegocio" , unidadeNegocio );
		}
		else{
			parametros.put( "unidadeNegocio" , "" );
		}
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

retorno = gerarRelatorio( ConstantesRelatorios.RELATORIO_OPERACAO_EFETUADA , parametros, ds, tipoFormatoRelatorio );
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
persistirRelatorioConcluido( retorno , Relatorio.CONSULTAR_OPERACAO , idFuncionalidadeIniciada );
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarOperacao", this);
	}
	
	private String consultarInformacoesExtrasOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada){
		
		String dadosFormatados = "";
		    
		String dadosAdicionais = operacaoEfetuada.getDadosAdicionais();		
		if (dadosAdicionais != null){
			
			StringTokenizer stk = new StringTokenizer(dadosAdicionais,"$");
			
			while (stk.hasMoreElements()) {
				String element = (String) stk.nextElement();
				int ind = element.indexOf(":");
				if (ind != -1){
					String label = element.substring(0,ind);
					String valor = element.substring(ind+1, element.length());
					dadosFormatados += label + "="+valor + ";";
				}
				
			}
			
		}
		return dadosFormatados;
	}	
}
