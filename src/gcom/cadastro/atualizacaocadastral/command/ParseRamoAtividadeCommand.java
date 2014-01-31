package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTamanhoLinhaClienteCommand;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTamanhoLinhaRamoAtividadeCommand;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ErroRepositorioException;
import gcom.util.ParserUtil;

import java.util.Map;

public class ParseRamoAtividadeCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseRamoAtividadeCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, 
			ControladorAtualizacaoCadastralLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaRamoAtividade();
		AtualizacaoCadastralImovel imovel = atualizacao.getImovelAtual();
		
		new ValidadorTamanhoLinhaRamoAtividadeCommand(parser, imovel).execute();
		if(!imovel.cadastroInvalido()) {

			String matriculaImovelRamoAtividade = parser.obterDadoParser(9).trim();
			linha.put("matriculaImovelRamoAtividade", matriculaImovelRamoAtividade);

			String ramoAtividade = parser.obterDadoParser(3).trim();
			linha.put("ramoAtividade", ramoAtividade);

			validarCampos(atualizacao, imovel, linha);
		}
	}

	private void validarCampos(AtualizacaoCadastral atualizacao, AtualizacaoCadastralImovel imovel, Map<String, String> linha) throws ErroRepositorioException {
		int idRamoAtividade = Integer.parseInt(linha.get("ramoAtividade"));
		
		if (idRamoAtividade > 0) {
			if (imovel.getDadosImovel().contemApenasResidencial()){
				imovel.addMensagemErro("Categoria residencial n�o admite ramo de atividade");
				return;
			}
			
			boolean existeRamoAtividade = repositorioCadastro.existeRamoAtividade(idRamoAtividade);
			
			if (!existeRamoAtividade){
				imovel.addMensagemErro("C�digo inv�lido do ramo de atividade");
				return;
			}
			
			DadoAtualizacaoRamoAtividade ramo = new DadoAtualizacaoRamoAtividade();
			ramo.setId(idRamoAtividade);
			atualizacao.getImovelAtual().addDadoRamoAtividade(ramo);
		}
		
	}

}
