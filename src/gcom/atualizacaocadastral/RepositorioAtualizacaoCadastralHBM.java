package gcom.atualizacaocadastral;

import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jboss.logging.Logger;

public class RepositorioAtualizacaoCadastralHBM implements IRepositorioAtualizacaoCadastral {

	public static IRepositorioAtualizacaoCadastral instancia;
	
	private Logger logger = Logger.getLogger(RepositorioAtualizacaoCadastralHBM.class);
	
	
	public static IRepositorioAtualizacaoCadastral getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioAtualizacaoCadastralHBM();
		}
		return instancia;
	}

	@SuppressWarnings("unchecked")
	public Collection<IImovel> obterImoveisParaAtualizar() throws ErroRepositorioException {
		
		Collection<IImovel> retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		
		try {
			
			consulta = "from ImovelRetorno imovelRetorno"
					+ " where imovelRetorno.idImovel in "
						+ " ( select imovelControle.imovel.id from ImovelControleAtualizacaoCadastral imovelControle "
						+ " where imovelControle.situacaoAtualizacaoCadastral.id = " + SituacaoAtualizacaoCadastral.APROVADO  + ") " ;
			
			retorno = (Collection<IImovel>) session.createQuery(consulta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imoveis.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<IImovelSubcategoria> obterImovelSubcategoriaParaAtualizar(Integer idImovel) throws ErroRepositorioException { 
		Collection<IImovelSubcategoria> retorno = null;
		Session session = HibernateUtil.getSession();
		
		String consulta = null;
		try {
			consulta = "from ImovelSubcategoria imovelSubcategoria"
					+ " where imovelSubcategoria.imovel.id = :idImovel " ;
			
			retorno = (Collection<IImovelSubcategoria>) session.createQuery(consulta).setInteger("idImovel", idImovel).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imovel subcategoria.");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	public ImovelControleAtualizacaoCadastral pesquisarImovelControleAtualizacao(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		ImovelControleAtualizacaoCadastral retorno = null;
		try{
			String consulta = " SELECT e FROM ImovelControleAtualizacaoCadastral e "
							+ " WHERE e.imovelRetorno.idImovel = :idImovel ";
			retorno = (ImovelControleAtualizacaoCadastral) session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.uniqueResult();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imovel controle atualizacao cadastral");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	public void apagarImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try{
			String consulta = " DELETE FROM ImovelRetorno imovelRetorno "
							+ " WHERE imovelRetorno.idImovel = :idImovel ";
			session.createQuery(consulta).setInteger("idImovel", idImovel).executeUpdate();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao excluir imovel retorno");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ImovelSubcategoriaRetorno> pesquisarImovelSubcategoriaRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<ImovelSubcategoriaRetorno> retorno = null;
		try{
			String consulta = " SELECT imovelSubCatRetorno "
							+ " FROM ImovelSubcategoriaRetorno imovelSubCatRetorno "
							+ " INNER JOIN imovelSubCatRetorno.imovel imovel"
							+ " WHERE imovel.id = :idImovel ";
			retorno = (List<ImovelSubcategoriaRetorno>) session.createQuery(consulta)
							.setInteger("idImovel", idImovel).list();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar imovel subcategoria");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public void apagarImovelRetornoRamoAtividadeRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try{
			String consulta = " DELETE ImovelRamoAtividadeRetorno ramo "
					+ " WHERE ramo.imovel.id = :idImovel ";
			session.createQuery(consulta).setInteger("idImovel", idImovel).executeUpdate();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao apagar imovel retorno ramo atividade");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ImovelSubcategoria> pesquisarImovelSubcategoriaAtualizacaoCadastral(Integer idImovel, Integer idSubcategoria,Integer idCategoria)
			throws ErroRepositorioException {
		
			Collection<ImovelSubcategoria> retorno = null;
			Session session = HibernateUtil.getSession();
			String consulta = null;
			
			try {
				consulta = " SELECT imovelSubcategoria" 
						 + " FROM ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoria" 
						 + " WHERE imovelSubcategoria.imovel.id = :idImovel";
				
				if(idSubcategoria != null){
					consulta = consulta + " AND imovelSubcategoria.subcategoria.id = "+idSubcategoria;
				}
				
				if(idCategoria != null){
					consulta = consulta + " AND imovelSubcategoria.categoria.id = "+idCategoria;
				}
			
				retorno = (Collection<ImovelSubcategoria>)session.createQuery(consulta).setInteger("idImovel",
						idImovel.intValue()).list();
			
			} catch (HibernateException e) {
				throw new ErroRepositorioException(e, "Erro ao pesquisar imovel subcategoria");
			} finally {
				HibernateUtil.closeSession(session);
			}
			
			return retorno;

	}
	
	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastral(Integer idImovel)
		throws ErroRepositorioException {
	
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT imov"
				     + " FROM ImovelAtualizacaoCadastral imov" 				    				    
				     + " WHERE imov.idImovel = :idImovel";
	
		imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral)session.createQuery(consulta)
										.setInteger("idImovel", idImovel)
										.setMaxResults(1).uniqueResult();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ap pesquisar imovel atualizacao cadastral");
		} finally {
	
			HibernateUtil.closeSession(session);
	
		}
	
		return imovelAtualizacaoCadastral;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ClienteImovelRetorno> pesquisarClienteImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		Collection<ClienteImovelRetorno> listaClienteImovel;
		try{
			String consulta = " select clienteImovel " 
					+ " from ClienteImovelRetorno clienteImovel "
					+ " WHERE clienteImovel.imovel.id = :idImovel ";
			listaClienteImovel = (Collection<ClienteImovelRetorno>) session.createQuery(consulta).setInteger("idImovel", idImovel).list();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao pesquisar cliente imovel retorno");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return listaClienteImovel;
	}
	
	public void liberarCadastroImovel(Integer idImovel) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update ImovelControleAtualizacaoCadastral tab ")
			.append(" set tab.situacaoAtualizacaoCadastral.id = :situacao ")
			.append(" , tab.dataAprovacao = :data")
			.append(" where tab.imovel.id = :idImovel");
			
			session.createQuery(sql.toString())
				.setInteger("situacao", SituacaoAtualizacaoCadastral.APROVADO)
				.setInteger("idImovel", idImovel)
				.setTimestamp("data", Calendar.getInstance().getTime())
				.executeUpdate();

		} catch (HibernateException e) {
			logger.error("Erro ao liberar cadastro do imovel", e);
			throw new ErroRepositorioException(e, "Erro ao liberar cadastro do imovel");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void apagarClienteImovelRetornoPorIdImovel(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String query = "DELETE FROM ClienteImovelRetorno ret WHERE ret.imovel.id = :idImovel ";
			session.createQuery(query).setInteger("idImovel", idImovel).executeUpdate();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro ao apagar cliente imovel retono");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}

	public void apagarClienteEnderecoRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String query = " DELETE FROM ClienteEnderecoRetorno clieImovel where clieImovel.idClienteRetorno in (:idsClientesRetorno) ";
			session.createQuery(query).setParameterList("idsClientesRetorno", idsClientesRetorno).executeUpdate();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao apagar cliente endereco retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void apagarClienteFoneRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String query = " DELETE FROM ClienteFoneRetorno clieImovel where clieImovel.idClienteRetorno in (:idsClientesRetorno) ";
			session.createQuery(query).setParameterList("idsClientesRetorno", idsClientesRetorno).executeUpdate();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao apagar cliente fone retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
	}

	public void apagarClienteRetorno(Collection<Integer> idsClientesRetorno) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String query = " DELETE FROM ClienteRetorno ret where ret.id in (:idsClientesRetorno) ";
			session.createQuery(query).setParameterList("idsClientesRetorno", idsClientesRetorno).executeUpdate();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao apagar cliente retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Collection<Integer> pesquisarIdsClienteRetorno(Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection<Integer> retorno = null;
		try {
			String consulta = "SELECT clieImovel.idClienteRetorno "
					+ " FROM ClienteImovelRetorno clieImovel "
					+ " WHERE clieImovel.imovel.id = :idImovel ";
			retorno = (Collection<Integer>)session.createQuery(consulta).setInteger("idImovel", idImovel).list();
		}catch(HibernateException e) {
			throw new ErroRepositorioException("Erro ao pesquisar ids de cliente retorno");
		}finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
}
