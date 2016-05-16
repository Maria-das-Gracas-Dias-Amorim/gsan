package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Remover Cliente do Imovel em Manter Imovel
 * 
 * @author Rafael Santos
 * @created 09/02/2006
 */
public class RemoverAtualizarImovelClienteAction extends GcomAction {

    /**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

        Collection imovelClientesNovos = (Collection) sessao.getAttribute("imovelClientesNovos");

        String[] clientesImoveis = httpServletRequest.getParameterValues("idRemocaoClienteImovel");
        if (clientesImoveis != null) {
        	sessao.setAttribute("arrayClientesImoveis", clientesImoveis);
        } else {
        	clientesImoveis = (String[]) sessao.getAttribute("arrayClientesImoveis");
        }

        Imovel imovel = null;

        ActionForward retorno = actionMapping.findForward("atualizarImovelCliente");
            imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");
 
        Collection colecaoClientesImoveisFimRelacao = new ArrayList();
            
        // instancia cliente
        ClienteImovel clienteImovel = null;
        Collection colecaoClientesImoveisRemovidos = null;
        if(sessao.getAttribute("colecaoClientesImoveisRemovidos") == null ){
        	colecaoClientesImoveisRemovidos = new ArrayList();	
        }else{
        	colecaoClientesImoveisRemovidos = (Collection) sessao.getAttribute("colecaoClientesImoveisRemovidos");
        }
        
        
        if (imovelClientesNovos != null && !imovelClientesNovos.equals("")) {

            Iterator clienteImovelIterator = imovelClientesNovos.iterator();

            while (clienteImovelIterator.hasNext()) {
                clienteImovel = (ClienteImovel) clienteImovelIterator.next();
                //Verifica se pode remover o cliente.
                          
                for (int i = 0; i < clientesImoveis.length; i++) {
                    if (obterTimestampIdObjeto(clienteImovel) == new Long(clientesImoveis[i]).longValue()) {
                    	//if(!(colecaoClientesImoveisRemovidos.contains(clienteImovel))){
                    	                 
                    	
                    	
                    			if ((imovel.getImovelPerfil().getId().equals(
	                                    ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) 
                            	&&   (clienteImovel
	                                    .getClienteRelacaoTipo()
	                                    .getId().intValue() ==  
	                                     ConstantesSistema.CLIENTE_IMOVEL_TIPO_USUARIO.intValue()
	                                                            )){
                            		throw new ActionServletException(
                                    "atencao.remover.cliente.atualizar.imovel");
                            	}
                            	if ((imovel.getImovelPerfil().getId().equals(
	                                    ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) 
                            	&&
                            	(clienteImovel
	                                    .getClienteRelacaoTipo()
	                                    .getId().intValue() == ConstantesSistema.CLIENTE_IMOVEL_TIPO_PROPIETARIO.intValue()
	                                                            )){
                            		 throw new ActionServletException(
                                             "atencao.remover.cliente.atualizar.imovel");	                            		
                            	}
                            	
                            	   if (clienteImovel.getId() != null
                                         && !clienteImovel.getId().equals("")) {
        		
                            		
                            	
		                            // caso seja um cliente im�vel da base
	                                // ent�o vai para o
	                                // exibirManterImovelFimRelacaoClienteImovel para
									// colocar
	                                // o motivo do fim da rela��o
	                                //retorno = actionMapping
	                                        //.findForward("exibirManterImovelFimRelacaoClienteImovelAction");
                            		httpServletRequest.setAttribute("aberturaPopup", "1");
                            		
                            		// Verifica permiss�o especial para manter cliente 
                        			// responsavel do imovel.
                        			Categoria categoria =
                        					fachada.obterPrincipalCategoriaImovel(clienteImovel.getImovel().getId());
                        	
                        				if(categoria.getId().compareTo(Categoria.PUBLICO)==0
                        						&& clienteImovel.getClienteRelacaoTipo().getId()
                        							.compareTo(ClienteRelacaoTipo.RESPONSAVEL.intValue())==0){
                        		
                        				boolean possuiPermissaoManterClienteResponsavelImoveisPublicos = 
                        						fachada.verificarPermissaoEspecialAtiva(
                        								PermissaoEspecial.ALTERAR_CLIENTE_RESPONSAVEL_PARA_IMOVEIS_PUBLICOS,usuarioLogado);
                        		
                        					if(!possuiPermissaoManterClienteResponsavelImoveisPublicos){
                        						
                                        		httpServletRequest.removeAttribute("aberturaPopup");
                        						throw new ActionServletException(
                        							"atencao.nao_usuario_nao_possui_permissao_alterar_cliente_reponsavel");
                        					}
                        	 	
                        			}
                        			fachada.verificaRestricaoDaTabelaClienteImovel(clienteImovel);
	                                colecaoClientesImoveisFimRelacao.add(clienteImovel);
	                    			// [FS0019] - Verificar exist�ncia de negativa��o para o cliente-im�vel
	                    			// Exibir mensagem de advert�ncia caso o cliente esteja em processo de negativa��o
	                    			// Adicionado por Victor Cisneiros (12/01/2009)
	                    			// ANALISTA: F�tima Sampaio
	                                
	                				Cliente cliente = clienteImovel.getCliente();
	                				Imovel im = clienteImovel.getImovel();
	                				
	                				if (cliente != null) {
	                					if (Fachada.getInstancia().verificarNegativacaoDoClienteImovel(cliente.getId(), im.getId())) {
	                						String confirmado = httpServletRequest.getParameter("confirmado");
	                						
	                						if (confirmado == null || !confirmado.equals("ok")) {
	                							httpServletRequest.setAttribute("nomeBotao1", "Prosseguir");
//	                							httpServletRequest.setAttribute("caminhoVoltar", "atualizarImovelWizardAction.do?destino=3&action=atualizarImovelEnderecoAction");
	                							
	                							return montarPaginaConfirmacao("atencao.advertencia.imovel.negativado", 
	                									httpServletRequest, actionMapping, new String[] { cliente.getDescricao(), im.getId().toString() });
	                						}
	                					}
	                				}
	                				colecaoClientesImoveisRemovidos.addAll(colecaoClientesImoveisFimRelacao);
	                                sessao.setAttribute("colecaoClientesImoveisFimRelacao", colecaoClientesImoveisFimRelacao);
                            	 }else{
 	                            	// verifica se o tipo do cliente � usu�rio
 		                            if (clienteImovel
 		                                    .getClienteRelacaoTipo()
 		                                    .getId().shortValue() == 
 		                                    ConstantesSistema.CLIENTE_IMOVEL_TIPO_USUARIO.shortValue()) {
 		                            		if(sessao.getAttribute(
 		                            			"idClienteImovelUsuario") != null){
 		                            				sessao.removeAttribute("idClienteImovelUsuario");	 
 		                            		}
 		                            }
 		                            
 		                            // verifica se o tipo do cliente � responsavel
 		                            if ((clienteImovel.getClienteRelacaoTipo().getId().shortValue()
 		                                     == ConstantesSistema.CLIENTE_IMOVEL_TIPO_RESPONSAVEL.shortValue())) {
 		                               if(sessao.getAttribute(
 		                           			"idClienteImovelResponsavel") != null){
 		                            	   sessao.removeAttribute("idClienteImovelResponsavel");	 
 		                               }
 		                            }
 		                         
 		                            
 	                            	if(!(colecaoClientesImoveisRemovidos.contains(clienteImovel))){
 	                            		fachada.verificaRestricaoDaTabelaClienteImovel(clienteImovel);  
 	                            		colecaoClientesImoveisRemovidos.add(clienteImovel);	
 	                            	}
 		                            clienteImovelIterator.remove();
                            		 
                            	}           	                           	
	                        //}                           	
                        }
                    }
                }
            }       
        	
        	sessao.setAttribute("colecaoClientesImoveisRemovidos", colecaoClientesImoveisRemovidos);	
        	imovelClientesNovos.removeAll(colecaoClientesImoveisFimRelacao);
        	sessao.setAttribute("imovelClientesNovos", imovelClientesNovos);
        	            
        return retorno;
    }
}
