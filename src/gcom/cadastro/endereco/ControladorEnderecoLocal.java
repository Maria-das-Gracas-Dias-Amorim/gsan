package gcom.cadastro.endereco;

import java.util.Collection;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.endereco.bean.AtualizarLogradouroBairroHelper;
import gcom.cadastro.endereco.bean.AtualizarLogradouroCepHelper;
import gcom.cadastro.endereco.to.LogradouroTO;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

/**
 * Description of the Interface
 * 
 * @author S�vio Luiz
 * @created 20 de Julho de 2005
 */
public interface ControladorEnderecoLocal extends javax.ejb.EJBLocalObject {

	/**
	 * inseri um logradouro na base e se tiver um bairro inseri na tabela de
	 * liga��o logradouroBairro
	 * 
	 * @param logradouro
	 *            Description of the Parameter
	 * @param bairro
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public Integer inserirLogradouro(Logradouro logradouro, Collection<Bairro> colecaoBairros, 
			Collection<Cep> colecaoCeps)
			throws ControladorException;

	/**
	 * atualiza um logradouro na base e se tiver um bairro inseri na tabela de
	 * liga��o logradouroBairro
	 * 
	 * @param logradouro
	 *            Description of the Parameter
	 * @param bairro
	 *            Description of the Parameter
	 */
	public void atualizarLogradouro(Logradouro logradouro, Collection colecaoBairros, 
			Collection colecaoCeps, Collection<AtualizarLogradouroBairroHelper> colecaoBairrosAtualizacao,
			Collection<AtualizarLogradouroCepHelper> colecaoCepsAtualizacao)
			throws ControladorException;

	/**
	 * remove um logradouro e o bairro ta tabela liga��o logradouroBairro do
	 * logradouro removido.
	 * 
	 * @param ids
	 *            Description of the Parameter
	 * @param pacoteLogradouro
	 *            Description of the Parameter
	 */
	public void removerLogradouro(String[] ids, String pacoteLogradouro, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper )
			throws ControladorException;

	/**
	 * inseri os ceps importados
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 */
	public void inserirCepImportados(Collection cepsImportados)
			throws ControladorException;

	public String pesquisarEndereco(Integer idImovel)
			throws ControladorException;
	
	public String pesquisarEnderecoFormatado(Integer idImovel)
	throws ControladorException;
	
	
	/**
	 * Obt�m o CEP PADR�O para um determinado munic�pio
	 * 
	 * @author Raphael Rossiter
	 * @date 04/05/2006
	 * 
	 * @param municipio
	 * @return Cep
	 */
	public Cep obterCepInicialMunicipio(Municipio municipio) throws ControladorException ;
	
	
	/**
	 * Verifica se o CEP � �nico de munic�pio
	 * 
	 * @author Raphael Rossiter
	 * @date 10/05/2006
	 * 
	 * @param cep
	 * @return boolean
	 */
	public boolean verificarCepUnicoMunicipio(Cep cep) throws ControladorException;
	
	
	/**
	 * Verifica se o Bairro � do tipo "BAIRRO NAO INFORMADO"
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param bairro
	 * @return boolean
	 */
	public boolean verificarBairroTipoBairroNaoInformado(Bairro bairro)
			throws ControladorException ;
	
	
	/**
	 * Verificar se o CEP est� associado a outro logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 04/05/2006
	 * 
	 * @param cep
	 * @return Logradouro
	 */
	public Logradouro verificarCepAssociadoOutroLogradouro(Cep cep) throws ControladorException;
	
	
	/**
	 * Seleciona os bairros em que o logradouro est� contido
	 * 
	 * @author Raphael Rossiter
	 * @date 10/05/2006
	 * 
	 * @param Logradouro
	 * @return Collection<Bairro>
	 */
	public Collection<Bairro> obterBairrosPorLogradouro(Logradouro logradouro) throws ControladorException;
	
	
	/**
	 * 
	 * 
	 * @author Raphael Rossiter
	 * @date 10/05/2006
	 * 
	 * @param Logradouro
	 * @return Integer
	 */
	public Integer inserirAssociacaoLogradouroCep(LogradouroCep logradouroCep) throws ControladorException ;
	
	
	/**
	 * [UC0003] Informar Endere�o
	 * 
	 * Pesquisar associa��o de LogradouroCep apenas por logradouro
	 * 
	 * @author Raphael Rossiter
	 * @data 12/05/2006
	 * 
	 * @param idLogradouro
	 * @return LogradouroCep
	 */
	public Collection<LogradouroCep> pesquisarAssociacaoLogradouroCepPorLogradouro(Logradouro logradouro)
			throws ControladorException ;
	
	
	/**
	 * Verifica se o logradouro j� est� associado a CEPs do tipo logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 17/05/2006
	 * 
	 * @param logradouro
	 * @return boolean
	 */
	public boolean verificarLogradouroAssociadoCepTipoLogradouro(Logradouro logradouro) 
		throws ControladorException ;
	
	
	/**
	 * Obt�m o CEP �nico para um determinado munic�pio
	 * 
	 * @author Raphael Rossiter
	 * @date 23/05/2006
	 * 
	 * @param municipio
	 * @return Cep
	 */
	public Cep obterCepUnicoMunicipio(Municipio municipio) throws ControladorException ;
	
	
	/**
	 * [UC0003] Informar Endere�o
	 * 
	 * Pesquisar associa��o de LogradouroBairro j� existente
	 * 
	 * @author Raphael Rossiter
	 * @data 24/05/2006
	 * 
	 * @param idBairro, idLogradouro
	 * @return LogradouroBairro
	 */
	public LogradouroBairro pesquisarAssociacaoLogradouroBairro(Integer idBairro, Integer idLogradouro)
			throws ControladorException ;
	
	
	/**
	 * [UC0003] Informar Endere�o
	 * 
	 * Pesquisar associa��o de LogradouroCep j� existente
	 * 
	 * @author Raphael Rossiter
	 * @data 24/05/2006
	 * 
	 * @param idCep, idLogradouro
	 * @return LogradouroBairro
	 */
	public LogradouroCep pesquisarAssociacaoLogradouroCep(Integer idCep, Integer idLogradouro)
			throws ControladorException ;
	
	
	
	public Collection<Logradouro> pesquisarLogradouro(
			FiltroLogradouro filtroLogradouro, Integer numeroPaginas)
			throws ControladorException;
	
	public Integer pesquisarLogradouroCount(
			FiltroLogradouro filtroLogradouro)
			throws ControladorException;
	
	/**
	 * [UC0085] - Obter Endere�o Autor: S�vio Luiz Data: 14/06/2006
	 */

	public String pesquisarEnderecoClienteAbreviado(Integer idCliente)
			throws ControladorException;
	
//	metodo que serve para fazer a pesquisa do logradouro
	//apartir dos parametros informados
	public Collection pesquisarLogradouroCompleto(
			String codigoMunicipio, String codigoBairro, String nome,
			String nomePopular, String logradouroTipo, String logradouroTitulo, String cep, 
			String codigoLogradouro, String indicadorUso, String tipoPesquisa, String tipoPesquisaPopular,
			Integer numeroPaginas)
			throws ControladorException;
	
	public Collection pesquisarLogradouroCompletoRelatorio(
			String codigoMunicipio, String codigoBairro, String nome,
			String nomePopular, String logradouroTipo, String logradouroTitulo, String cep, 
			String codigoLogradouro, String indicadorUso, String tipoPesquisa, String tipoPesquisaPopular)
			throws ControladorException;
	
	public Integer pesquisarLogradouroCompletoCount(
			String codigoMunicipio, String codigoBairro, String nome,
			String nomePopular, String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa, String tipoPesquisaPopular
			)throws ControladorException;
	
	
	
	/**
	 * Verifica se o CEP � inicial de munic�pio
	 * 
	 * @author Raphael Rossiter
	 * @date 10/05/2006
	 * 
	 * @param cep
	 * @return boolean
	 */
	public boolean verificarCepInicialMunicipio(Cep cep) throws ControladorException ;
	
	
	/**
	 * [UC0085] - Obter Endere�o Autor: Ana Maria
	 */

	public String pesquisarEnderecoRegistroAtendimentoFormatado(
			Integer idRegistroAtendimento) throws ControladorException ;
	
	
	/**
	 * [UC0085] - Obter Endere�o Autor: Ana Maria
	 */

	public String pesquisarEnderecoRegistroAtendimentoSolicitanteFormatado(
			Integer idRegistroAtendimentoSolicitante)
			throws ControladorException;
	
	/**
	 * Obter o objeto de registro atendimento para recuperar Endere�o Autor:
	 * S�vio Luiz
	 */

	public RegistroAtendimento pesquisarRegistroAtendimentoEndereco(
			Integer idRegistroAtendimento) throws ControladorException ;
			
			
	/**
	 * Obter os parametros de logradouroCep para o endere�o Autor: S�vio Luiz
	 */

	public LogradouroCep pesquisarLogradouroCepEndereco(Integer idLogradouroCep)
			throws ControladorException;
	
	/**
	 * Obter os parametros de logradouroBairro para o endere�o Autor: S�vio Luiz
	 */

	public LogradouroBairro pesquisarLogradouroBairroEndereco(
			Integer idLogradouroBairro) throws ControladorException; 
	/**
	 * Obter os campos necess�rio para o endere�o do im�vel Autor:S�vio Luiz
	 */

	public Imovel pesquisarImovelParaEndereco(Integer idImovel)
			throws ControladorException;

	/**
	 * 
	 * Pesquisar os Endere�os do Cliente
	 *
	 * [UC0474] Consultar Im�vel
	 *
	 * @author Rafael Santos
	 * @date 19/09/2006
	 *
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClientesEnderecosAbreviado(Integer idCliente)
		throws ControladorException;
	
	/**
	 * 
	 * Pesquisar o endere�o abreviado a partir do id do im�vel
	 * 
	 * [UC0483] - Obter Endere�o Abreviado
	 * 
	 * @author Rafael Corr�a
	 * @date 18/10/2006
	 * 
	 * @param idImovel
	 * @return String
	 * @throws ControladorException
	 */

	public String obterEnderecoAbreviadoImovel(Integer idImovel)
			throws ControladorException;
	
	public Collection pesquisarEnderecoTotalmenteDividido(Integer idImovel)
	throws ControladorException;
	
	/**
	 * 
	 * Pesquisar o endere�o abreviado a partir do id do ra
	 * 
	 * [UC0483] - Obter Endere�o Abreviado
	 * 
	 * @author Rafael Corr�a
	 * @date 18/10/2006
	 * 
	 * @param idRA
	 * @return String
	 * @throws ControladorException
	 */

	public String obterEnderecoAbreviadoRA(Integer idRA)
			throws ControladorException;
	
	/**
	 * [UC0085] - Obter Endere�o Autor: S�vio Luiz Data: 09/04/2007 Recupera o
	 * endere�o em 3 partes:o endere�o abreviado formatado sem o municipio e a
	 * unidade federa��o,a descri��o do municipio e a terceira parte a sigla da
	 * unidade federa��o
	 */

	public String[] pesquisarEnderecoClienteAbreviadoDividido(Integer idCliente)
			throws ControladorException;
	
	/**
	 * [UC0210] - Obter Endere�o Autor: S�vio Luiz
	 */

	public String[] pesquisarEnderecoFormatadoDividido(Integer idImovel)
			throws ControladorException;
	
	
	/**
	 * 
	 * [UC0348] Emitir Contas por cliente responsavel CAERN
	 * 
	 * Pesquisar endereco formatado para cliente
	 * 
	 * @author Raphael Rossiter
	 * @data 22/05/2007
	 * 
	 * @param idCliente,
	 * @return Collection
	 */
	public String[] pesquisarEnderecoFormatadoClienteDividido(Integer idCliente)
	throws ControladorException ;
	
	/**
	 * [UC0210] - Obter Endere�o Autor: S�vio Luiz
	 */

	public String pesquisarEnderecoAbreviadoCAER(Integer idImovel)
			throws ControladorException;
	
	/**
	 * 
	 * Pesquisar o cep pelo codigo do cep
	 * 
	 * @author S�vio Luiz
	 * @date 05/11/2007
	 * 
	 */

	public Cep pesquisarCep(Integer codigoCep) throws ControladorException;
	
	/**
	 * Verifica a exist�ncia do endere�o de correspond�ncia do cliente pelo seu id 
	 * 
	 */
	public boolean verificarExistenciaClienteEndereco(Integer idCliente)
		throws ControladorException;
	
	/**
	 * 
	 * Pesquisar Bairro e o Logradouro do RegistroAtendimentoSolicitante
	 * 
	 * @author Yara Taciane 
	 * @date 17/06/2008
	 * 
	 */
	
	public String pesquisarBairroLogradouroRegistroAtendimentoSolicitante(
			Integer idRegistroAtendimentoSolicitante)throws ControladorException;


	/**
	 * Retorna a cole��o de Logradouro Tipos presentes na tabela CEP 
	 * 
	 * @author: Vinicius Medeiros 
	 */
	public Collection retornaTipoLogradouroPeloCep()
			throws ControladorException;
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * @author: R�mulo Aur�lio
	 * @date: 29/10/2008
	 */
	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresa(
			Integer idCliente) throws ControladorException;
	
	public LogradouroTO pesquisarLogradouro(Integer idImovel) throws ControladorException;
	
	/**
	 * Obter Logradouro(Tipo + T�tulo + Nome Logradouro)
	 */
	
	public Collection pesquisarLogradouroCliente(Integer idCliente)
		throws ControladorException;
	
	public String[] pesquisarEnderecoClienteAbreviadoDivididoCosanpa(Integer idCliente)
		throws ControladorException;
	
	public String[] pesquisarEnderecoFormatadoDivididoCosanpa(Integer idImovel)
		throws ControladorException;
	
	/**
	 * [UC0085] - Obter Endere�o Autor: Ana Maria
	 */

	public String pesquisarEnderecoRegistroAtendimentoFormatadoIniciadoPeloBairro(
			Integer idRegistroAtendimento) throws ControladorException ;
	
	/**
	 * [UC0085] Obter Endere�o
	 * 
	 * @author Vivianne Sousa
	 * @data 17/05/2011
	 */
	public String pesquisarEnderecoSolicitanteRAReiteracaoFormatado(
			Integer idraReiteracao)	throws ControladorException ;
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * @author: Mariana Victor
	 * @date: 20/05/2011
	 */
	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresaLayout02(
			Integer idCliente) throws ControladorException;
	
	 /**
	 * [UC0671] - Gerar Movimento Inclus�o de Negativa��o
	 * Ajuste do endere�o do arquivo ([SB0009]Gerar Registro Tipo Detalhe -D1.09)
	 * Autor: Ana Maria 12/07/2011
	 */
	public String[] pesquisarEnderecoImovelDividido(Integer idImovel)
			throws ControladorException;
	
	 /**
	 * [UC0671] - Gerar Movimento Inclus�o de Negativa��o
	 * Ajuste do endere�o do arquivo ([SB0009]Gerar Registro Tipo Detalhe -D1.09)
	 * Autor: Ana Maria 12/07/2011
	 */
	public String[] pesquisarEnderecoClienteDividido(Integer idCliente)
			throws ControladorException;
}
