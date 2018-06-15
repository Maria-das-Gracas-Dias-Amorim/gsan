package gcom.cadastro.endereco;

import gcom.cadastro.geografico.Bairro;
import gcom.util.ControladorException;

import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Declara��o p�blica de servi�os do Session Bean de ControladorEndere�o
 * 
 * @author S�vio Luiz
 * @created 23 de Agosto de 2005
 */
public interface ControladorEnderecoRemote extends javax.ejb.EJBObject {

	public Integer inserirLogradouro(Logradouro logradouro, Bairro bairro) throws RemoteException;

	@SuppressWarnings("rawtypes")
	public void inserirCepImportados(Collection cepsImportados) throws RemoteException;

}
