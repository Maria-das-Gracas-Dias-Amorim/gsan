package gcom.cobranca.repositorios;

import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.List;

public interface IRepositorioCobrancaPorResultadoHBM {

	public Collection<Object[]> pesquisarQuantidadeContas(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel) throws ErroRepositorioException;
	
	public List<Integer> pesquisarImoveis(ComandoEmpresaCobrancaContaHelper helper, boolean agrupadoPorImovel, boolean percentualInformado) throws ErroRepositorioException;
}