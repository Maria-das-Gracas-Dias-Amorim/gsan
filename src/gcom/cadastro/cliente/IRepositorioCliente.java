/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.cadastro.cliente;

import gcom.cadastro.cliente.bean.PesquisarClienteResponsavelSuperiorHelper;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * Interface para o reposit�rio de cliente
 * 
 * @author S�vio Luiz
 * @created 22 de Abril de 2005
 */
public interface IRepositorioCliente {

	/**
	 * pesquisa uma cole��o de responsavel(is) de acordo com crit�rios
	 * existentes no filtro
	 * 
	 * @param filtroCliente
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarResponsavelSuperior(FiltroCliente filtroCliente)
			throws ErroRepositorioException;

	/**
	 * Remove todos os endere�os de um determinado cliente
	 * 
	 * @param idCliente
	 *            C�digo do cliente que ter� seus endere�os apagados
	 * @exception ErroRepositorioException
	 *                Erro no BD
	 */
	public void removerTodosEnderecosPorCliente(Integer idCliente)
			throws ErroRepositorioException;

	public Collection<Cliente> pesquisarClienteDadosClienteEndereco(
			FiltroCliente filtroCliente, Integer numeroPagina) throws ErroRepositorioException;
	
	public Collection<Cliente> pesquisarClienteDadosClienteEnderecoRelatorio(
			FiltroCliente filtroCliente) throws ErroRepositorioException;

	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * Retrona a quantidade de endere�os que existem para o Cliente
	 *
	 * pesquisarClienteDadosClienteEnderecoCount
	 *
	 * @author Roberta Costa
	 * @date 29/06/2006
	 * 
	 * @param filtroCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarClienteDadosClienteEnderecoCount(
			FiltroCliente filtroCliente) throws ErroRepositorioException;
	
	public Collection<Cliente> pesquisarClienteOutrosCriterios(
			FiltroCliente filtroCliente) throws ErroRepositorioException;

	/**
	 * [UC0242] - Registrar Movimento de Arrecadadores Author: S�vio Luiz Data:
	 * 01/02/2006
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @param anoMesReferencia
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */

	public Integer verificarExistenciaCliente(Integer idCliente)
			throws ErroRepositorioException;

	/**
	 * Pesquisa um cliente carregando os dados do relacionamento com ClienteTipo
	 *
	 * [UC0321] Emitir Fatura de Cliente Respons�vel
	 *
	 * @author Pedro Alexandre
	 * @date 02/05/2006
	 *
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarCliente(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * Pesquisa um cliente pelo id
	 * 
	 * @author Rafael Corr�a
	 * @date 01/08/2006
	 * 
	 * @return Cliente
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoClienteRelatorio(
			Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * Pesquisa as quantidades de im�veis e as quantidades de economias
	 * associadas a um cliente
	 * 
	 * @author Rafael Corr�a
	 * @date 23/08/2007
	 * 
	 * @return Object[]
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarQtdeImoveisEEconomiasCliente(Integer idCliente)
			throws ErroRepositorioException;
	
	
	/**
	 * Pesquisa todos os telefones de um cliente 
	 *  
	 * @author Raphael Rossiter
	 * @date 23/08/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteFone(Integer idCliente)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa os dados do Cliente 
	 *  
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCliente(Integer idCliente)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa todos os endere�os do cliente 
	 *  
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEnderecosCliente(Integer idCliente)
		throws ErroRepositorioException;
	
	/**
	 * [UC0458] - Imprimir Ordem de Servi�o
	 * 
	 * Pesquisa o telefone principal do Cliente para o
	 * Relat�rio de OS
	 * 
	 * @author Rafael Corr�a
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	
	public Object[] pesquisarClienteFonePrincipal(
			Integer idCliente) throws ErroRepositorioException;

	/**
	 * 
	 * Usado pelo Filtrar Cliente
	 * Filtra o Cliente usando os paramentos informados
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarCliente(
			String codigo,
			String cpf,
			String rg,
			String cnpj,
			String nome,
			String nomeMae,		
			String cep,
			String idMunicipio,
			String idBairro,
			String idLogradouro,
			String indicadorUso,
			String tipoPesquisa,
			String tipoPesquisaNomeMae,
			String clienteTipo, String idEsferaPoder,
		    Integer numeroPagina) throws ErroRepositorioException; 
	

	/**
	 * 
	 * Usado pelo Filtrar Cliente
	 * Filtra a quantidade de Clientes usando os paramentos informados
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object filtrarQuantidadeCliente(
			String codigo,
			String cpf,
			String rg,
			String cnpj,
			String nome,
			String nomeMae,		
			String cep,
			String idMunicipio,
			String idBairro,
			String idLogradouro,
			String indicadorUso,
			String tipoPesquisa,
			String tipoPesquisaNomeMae,
			String clienteTipo, String idEsferaPoder
			) throws ErroRepositorioException; 
	
	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Pesquisa os dados do cliente para a emiss�o do boletim
	 * 
	 * @author Rafael Corr�a
	 * @date 16/05/2007
	 * 
	 * @param idImovel, clienteRelacaoTipo
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarClienteEmitirBoletimCadastro(
			Integer idImovel, Short clienteRelacaoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC0864] Gerar Certid�o Negativa por Cliente
	 * 
	 * @author Rafael Corr�a
	 * @date 25/09/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarClientesAssociadosResponsavel(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 *
	 * @author Vivianne Sousa
	 * @date 27/07/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterIdENomeCliente(
			String cpf) throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 *
	 * @author Vivianne Sousa
	 * @date 30/07/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarCPFCliente(String cpf,Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosCliente(Integer idImovel, 
			Short idClienteRelacaoTipo) throws ErroRepositorioException;
	
	
	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 01/09/2008
	 * 
	 * @return Cliente Fone
	 * @throws ErroRepositorioException
	 */

	public Collection obterDadosClienteFone(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 26/09/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer verificaExistenciaClienteAtualizacaoCadastral(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * Pesquisa a quantidade de clientes respons�veis superiores com os condicionais informados
	 * 
	 * @author Rafael Corr�a
	 * @date 18/11/08
	 */
	public Integer pesquisarClienteResponsavelSuperiorParaPaginacaoCount(PesquisarClienteResponsavelSuperiorHelper helper) throws ErroRepositorioException;
	
	/**
	 * Pesquisa os clientes respons�veis superiores com os condicionais informados
	 * 
	 * @author Rafael Corr�a
	 * @date 18/11/08
	 */
	public Collection<Cliente> pesquisarClienteResponsavelSuperiorParaPaginacao(PesquisarClienteResponsavelSuperiorHelper helper, Integer numeroPagina) throws ErroRepositorioException;
	
	/**
	 * Pesquisar dados do Cliente Atualiza��o Cadastral
	 * 
	 * @param idCliente, idImovel
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author Ana Maria
     * @date 15/05/2009
	 * @exception ErroRepositorioException
	 */
	public ClienteAtualizacaoCadastral pesquisarClienteAtualizacaoCadastral(Integer idCliente, Integer idImovel, Integer idClienteRelacaoTipo)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar Cliente Fone Atualiza��o Cadastral
	 *
	 * @author Ana Maria
	 * @date 24/10/2008
	 *
	 * @param idCliente
	 * @throws ErroRepositorioException 
	 */
	public Collection<ClienteFoneAtualizacaoCadastral> pesquisarClienteFoneAtualizacaoCadastral(Integer idCliente, Integer idMatricula, 
			Integer idTipoFone, Integer idClienteRelacaoTipo,String numeroFone)
		throws ErroRepositorioException;
	
	
	/**
	 * 
	 * Atualiza telefone padr�o
	 *
	 * @author Daniel Alves
	 * @date 06/09/2010
	 *
	 * @param idCliente
	 * @param idClienteFonePadrao  (novo telefone padr�o do cliente).
	 * @throws ErroRepositorioException 
	 */
	public void atualizarTelefonePadrao(String idCliente, String idClienteFonePadrao)
		throws ErroRepositorioException;
	
	/**
	 * Remove todos os telefones de um determinado cliente
	 * 
	 * @param idCliente
	 *            C�digo do cliente que ter� seus telefones apagados
	 * @exception ErroRepositorioException
	 *                Erro no BD
	 */
	public void removerTodosTelefonesPorCliente(Integer idCliente)
			throws ErroRepositorioException;
	

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0002]�Verifica Crit�rio Recadastramento
	 * 
	 * @author Vivianne Sousa
	 * @date 25/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarClienteUsuarioDoImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	
	/**
	 * 
	 * Filtra os Clientes por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ErroRepositorioException 
	 */
	public Collection filtrarAutocompleteCliente(String valor)throws ErroRepositorioException;
	
	/**
	 * 
	 * Filtra os Clientes Responsavel por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ErroRepositorioException 
	 */
	public Collection filtrarAutocompleteClienteResponsavel(String valor, ClienteTipo tipo)throws ErroRepositorioException;
	
	/**
	 * Verifica Clientes Associados a um Cliente sem CNPJ ou ICPESSOAFISICAJURIDICA diferente de 2
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQtdClientesAssociadosResponsavelNaoJuridica(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * Retorna Lista de Im�veis associados ao cliente
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisAssociadosCliente(Integer idCliente, Short relacaoTipo )throws ErroRepositorioException;
	

	
	/**
	 * [UC1186] Gerar Relat�rio Ordem de Servi�o Cobran�a p/Resultado
	 * 
	 * Pesquisar os clientes a partir do im�vel e o tipo de rela��o com o cliente
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	
	
	public Collection obterClienteImovelporRelacaoTipo(Integer idImovel, Integer idRelacaoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos Atrav�s da Loja Virtual
	 * 
	 * Caso o CPF do cliente passado no par�metro seja do cliente propriet�rio
	 * ou do cliente usu�rio do im�vel o m�todo retorna o nome do cliente, caso
	 * contr�rio o m�todo retorna null.
	 * 
	 * @author Diogo Peixoto
	 * @date 28/06/2011
	 * 
	 * @param CPFCliente
	 * 
	 * @throws ErroRepositorioException
	 * @return String
	 */
	public String validarCliente(String cpfCliente, Integer matricula) throws ErroRepositorioException;

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 05/08/2011
	 */
	public Cliente pesquisarDadosCliente(Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
     * Mantis 494
     * 
     * Pesquisar Tipo de Clientes pelo Id
     * 
     * @author Wellington Rocha
     *                
     */
    public ClienteTipo pesquisarClienteTipo(Integer idClienteTipo)
            throws ErroRepositorioException;
}
