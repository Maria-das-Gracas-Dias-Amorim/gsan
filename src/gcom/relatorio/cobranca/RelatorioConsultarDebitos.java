package gcom.relatorio.cobranca;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioConsultarDebitos extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioConsultarDebitos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS);
	}

	@Deprecated
	public RelatorioConsultarDebitos() {
		super(null, "");
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	private Collection<RelatorioConsultarDebitosBean> inicializarBeanRelatorio(Collection<ContaValoresHelper> colecaoContas, Collection<DebitoACobrar> colecaoDebitosACobrar,
			Collection<CreditoARealizar> colecaoCreditosARealizar, Collection<GuiaPagamento> colecaoGuiasPagamento) {

		Collection<RelatorioConsultarDebitosBean> retorno = new ArrayList<RelatorioConsultarDebitosBean>();

		String valorTotalDebitos = (String) getParametro("valorTotalDebitos");
		String valorTotalDebitosAtualizado = (String) getParametro("valorTotalDebitosAtualizado");
		String pesquisaCliente = (String) getParametro("pesquisaCliente");
		String relatorioEndereco = (String) getParametro("relatorioEndereco");
		String codigoCliente = (String) getParametro("codigoCliente");
		String nomeCliente = (String) getParametro("nomeCliente");

		Fachada fachada = Fachada.getInstancia();

		// Conta
		if (colecaoContas != null && !colecaoContas.isEmpty()) {

			BigDecimal valorTotalContas = new BigDecimal("0.00");
			BigDecimal valorTotalAguaContas = new BigDecimal("0.00");
			Integer valorTotalAguaConsumoContas = new Integer("0");
			BigDecimal valorTotalEsgotoContas = new BigDecimal("0.00");
			Integer valorTotalEsgotoConsumoContas = new Integer("0");
			BigDecimal valorTotalDebitosContas = new BigDecimal("0.00");
			BigDecimal valorTotalCreditosContas = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimosImpontualidadeContas = new BigDecimal("0.00");
			Integer idImovelAnterior = null;
			int count = 0;
			BigDecimal valorSubtotalContas = new BigDecimal("0.00");
			BigDecimal valorSubtotalAguaContas = new BigDecimal("0.00");
			Integer valorSubtotalAguaConsumoContas = new Integer("0");
			BigDecimal valorSubtotalEsgotoContas = new BigDecimal("0.00");
			Integer valorSubtotalEsgotoConsumoContas = new Integer("0");
			BigDecimal valorSubtotalDebitosContas = new BigDecimal("0.00");
			BigDecimal valorSubtotalCreditosContas = new BigDecimal("0.00");
			BigDecimal valorSubtotalAcrescimosImpontualidadeContas = new BigDecimal("0.00");

			Iterator colecaoContasIterator = colecaoContas.iterator();
			while (colecaoContasIterator.hasNext()) {

				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) colecaoContasIterator.next();

				String imprimirEndereco = "";

				// Seta o im�vel anterior para verificar, posteriormente, se vai criar um subtotal para o im�vel.
				if (idImovelAnterior == null) {
					idImovelAnterior = contaValoresHelper.getConta().getImovel().getId();
					imprimirEndereco = "SIM";
				}

				// M�s/Ano
				String mesAnoConta = "" + contaValoresHelper.getFormatarAnoMesParaMesAno();

				// Vencimento
				String vencimento = "";
				if (contaValoresHelper.getConta().getDataVencimentoConta() != null) {
					vencimento = Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta());
				}

				// Valor �gua
				String valorAgua = "";
				if (contaValoresHelper.getConta().getValorAgua() != null) {
					valorAgua = Util.formatarMoedaReal(contaValoresHelper.getConta().getValorAgua());
					valorTotalAguaContas = valorTotalAguaContas.add(contaValoresHelper.getConta().getValorAgua());

					if (idImovelAnterior.equals(contaValoresHelper.getConta().getImovel().getId())) {
						valorSubtotalAguaContas = valorSubtotalAguaContas.add(contaValoresHelper.getConta().getValorAgua());
					}
				}

				// Consumo �gua
				String consumoAgua = "";
				if (contaValoresHelper.getConta().getConsumoAgua() != null) {
					consumoAgua = contaValoresHelper.getConta().getConsumoAgua().toString();
					valorTotalAguaConsumoContas = valorTotalAguaConsumoContas + contaValoresHelper.getConta().getConsumoAgua();

					if (idImovelAnterior.equals(contaValoresHelper.getConta().getImovel().getId())) {
						valorSubtotalAguaConsumoContas = valorSubtotalAguaConsumoContas + contaValoresHelper.getConta().getConsumoAgua();
					}
				}

				// Valor Esgoto
				String valorEsgoto = "";
				if (contaValoresHelper.getConta().getValorEsgoto() != null) {
					valorEsgoto = Util.formatarMoedaReal(contaValoresHelper.getConta().getValorEsgoto());
					valorTotalEsgotoContas = valorTotalEsgotoContas.add(contaValoresHelper.getConta().getValorEsgoto());

					if (idImovelAnterior.equals(contaValoresHelper.getConta().getImovel().getId())) {
						valorSubtotalEsgotoContas = valorSubtotalEsgotoContas.add(contaValoresHelper.getConta().getValorEsgoto());
					}

				}

				// Consumo Esgoto
				String consumoEsgoto = "";
				if (contaValoresHelper.getConta().getConsumoEsgoto() != null) {
					consumoEsgoto = contaValoresHelper.getConta().getConsumoEsgoto().toString();
					valorTotalEsgotoConsumoContas = valorTotalEsgotoConsumoContas + contaValoresHelper.getConta().getConsumoEsgoto();

					if (idImovelAnterior.equals(contaValoresHelper.getConta().getImovel().getId())) {
						valorSubtotalEsgotoConsumoContas = valorSubtotalEsgotoConsumoContas + contaValoresHelper.getConta().getConsumoEsgoto();
					}

				}

				// Valor dos D�bitos
				String valorDebitos = "";
				if (contaValoresHelper.getConta().getDebitos() != null) {
					valorDebitos = Util.formatarMoedaReal(contaValoresHelper.getConta().getDebitos());
					valorTotalDebitosContas = valorTotalDebitosContas.add(contaValoresHelper.getConta().getDebitos());

					if (idImovelAnterior.equals(contaValoresHelper.getConta().getImovel().getId())) {
						valorSubtotalDebitosContas = valorSubtotalDebitosContas.add(contaValoresHelper.getConta().getDebitos());
					}
				}

				// Valor dos Cr�ditos
				String valorCreditos = "";
				if (contaValoresHelper.getConta().getValorCreditos() != null) {
					valorCreditos = Util.formatarMoedaReal(contaValoresHelper.getConta().getValorCreditos());
					valorTotalCreditosContas = valorTotalCreditosContas.add(contaValoresHelper.getConta().getValorCreditos());

					if (idImovelAnterior.equals(contaValoresHelper.getConta().getImovel().getId())) {
						valorSubtotalCreditosContas = valorSubtotalCreditosContas.add(contaValoresHelper.getConta().getValorCreditos());
					}
				}

				// Valor da Conta
				String valorConta = "";
				if (contaValoresHelper.getConta().getValorTotal() != null) {
					valorConta = Util.formatarMoedaReal(contaValoresHelper.getConta().getValorTotal());
					valorTotalContas = valorTotalContas.add(contaValoresHelper.getConta().getValorTotal());

					if (idImovelAnterior.equals(contaValoresHelper.getConta().getImovel().getId())) {
						valorSubtotalContas = valorSubtotalContas.add(contaValoresHelper.getConta().getValorTotal());
					}
				}

				// Valor dos Acr�scimos por Impontualidade
				String acrescImpont = "";
				if (contaValoresHelper.getValorTotalContaValores() != null) {
					acrescImpont = Util.formatarMoedaReal(contaValoresHelper.getValorTotalContaValores());
					valorTotalAcrescimosImpontualidadeContas = valorTotalAcrescimosImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValores());

					if (idImovelAnterior.equals(contaValoresHelper.getConta().getImovel().getId())) {
						valorSubtotalAcrescimosImpontualidadeContas = valorSubtotalAcrescimosImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValores());
					}
				}

				// Situa��o
				String situacao = "";
				if (contaValoresHelper.getConta().getDebitoCreditoSituacaoAtual() != null) {
					situacao = "" + contaValoresHelper.getConta().getDebitoCreditoSituacaoAtual().getDescricaoDebitoCreditoSituacao();
				}

				RelatorioConsultarDebitosBean bean = null;

				if (pesquisaCliente != null && !pesquisaCliente.trim().equals("")) {

					boolean criarSubtotal = false;

					// Caso tenha mudado o im�vel, verifica se teve mais de uma
					// conta para o ele. Em caso afirmativo muda o valor da
					// vari�vel para criar um subtotal, e em caso negativo
					// reinicializa as vari�veis de totaliza��o.
					if (!idImovelAnterior.equals(contaValoresHelper.getConta().getImovel().getId())) {

						imprimirEndereco = "SIM";

						if (count > 1) {
							criarSubtotal = true;
						} else {
							count = 1;
							idImovelAnterior = contaValoresHelper.getConta().getImovel().getId();
							if (contaValoresHelper.getValorTotalConta() != null) {
								valorSubtotalContas = contaValoresHelper.getValorTotalConta();
							} else {
								valorSubtotalContas = new BigDecimal("0.00");
							}

							if (contaValoresHelper.getConta().getValorAgua() != null) {
								valorSubtotalAguaContas = contaValoresHelper.getConta().getValorAgua();
							} else {
								valorSubtotalAguaContas = new BigDecimal("0.00");
							}

							if (contaValoresHelper.getConta().getConsumoAgua() != null) {
								valorSubtotalAguaConsumoContas = contaValoresHelper.getConta().getConsumoAgua();
							} else {
								valorSubtotalAguaConsumoContas = new Integer("0");
							}

							if (contaValoresHelper.getConta().getValorEsgoto() != null) {
								valorSubtotalEsgotoContas = contaValoresHelper.getConta().getValorEsgoto();
							} else {
								valorSubtotalEsgotoContas = new BigDecimal("0.00");
							}

							if (contaValoresHelper.getConta().getConsumoEsgoto() != null) {
								valorSubtotalEsgotoConsumoContas = contaValoresHelper.getConta().getConsumoEsgoto();
							} else {
								valorSubtotalEsgotoConsumoContas = new Integer("0");
							}

							if (contaValoresHelper.getConta().getDebitos() != null) {
								valorSubtotalDebitosContas = contaValoresHelper.getConta().getDebitos();
							} else {
								valorSubtotalDebitosContas = new BigDecimal("0.00");
							}

							if (contaValoresHelper.getConta().getValorCreditos() != null) {
								valorSubtotalCreditosContas = contaValoresHelper.getConta().getValorCreditos();
							} else {
								valorSubtotalCreditosContas = new BigDecimal("0.00");
							}

							if (contaValoresHelper.getValorTotalContaValores() != null) {
								valorSubtotalAcrescimosImpontualidadeContas = contaValoresHelper.getValorTotalContaValores();
							} else {
								valorSubtotalAcrescimosImpontualidadeContas = new BigDecimal("0.00");
							}
						}
					} else {
						count = count + 1;
					}

					if (criarSubtotal) {
						bean = new RelatorioConsultarDebitosBean(
								"Subtotal", 
								"SUBTOTAL", 
								count + " doc(s)", 
								Util.formatarMoedaReal(valorSubtotalAguaContas),
								valorSubtotalAguaConsumoContas.toString(), 
								Util.formatarMoedaReal(valorSubtotalEsgotoContas), 
								valorSubtotalEsgotoConsumoContas.toString(),
								Util.formatarMoedaReal(valorSubtotalDebitosContas), 
								Util.formatarMoedaReal(valorSubtotalCreditosContas),
								Util.formatarMoedaReal(valorSubtotalContas), 
								Util.formatarMoedaReal(valorSubtotalAcrescimosImpontualidadeContas),
								"", 
								null, 
								null, 
								null, 
								null, 
								null,
								null, 
								null, 
								null, 
								null, 
								null, 
								null, 
								null, 
								null, 
								null, 
								null, 
								null, 
								null, 
								null, 
								null, 
								"");

						retorno.add(bean);

						// Reinicializa as vari�veis de totaliza��o e o contador. Al�m disso, muda o im�vel valor do im�vel
						// anterior para o do atual para verificar se ser� necess�rio criar um subtotal.
						count = 1;
						idImovelAnterior = contaValoresHelper.getConta().getImovel().getId();

						if (contaValoresHelper.getValorTotalConta() != null) {
							valorSubtotalContas = contaValoresHelper.getValorTotalConta();
						} else {
							valorSubtotalContas = new BigDecimal("0.00");
						}

						if (contaValoresHelper.getConta().getValorAgua() != null) {
							valorSubtotalAguaContas = contaValoresHelper.getConta().getValorAgua();
						} else {
							valorSubtotalAguaContas = new BigDecimal("0.00");
						}

						if (contaValoresHelper.getConta().getConsumoAgua() != null) {
							valorSubtotalAguaConsumoContas = contaValoresHelper.getConta().getConsumoAgua();
						} else {
							valorSubtotalAguaConsumoContas = new Integer("0");
						}

						if (contaValoresHelper.getConta().getValorEsgoto() != null) {
							valorSubtotalEsgotoContas = contaValoresHelper.getConta().getValorEsgoto();
						} else {
							valorSubtotalEsgotoContas = new BigDecimal("0.00");
						}

						if (contaValoresHelper.getConta().getConsumoEsgoto() != null) {
							valorSubtotalEsgotoConsumoContas = contaValoresHelper.getConta().getConsumoEsgoto();
						} else {
							valorSubtotalEsgotoConsumoContas = new Integer("0");
						}

						if (contaValoresHelper.getConta().getDebitos() != null) {
							valorSubtotalDebitosContas = contaValoresHelper.getConta().getDebitos();
						} else {
							valorSubtotalDebitosContas = new BigDecimal("0.00");
						}

						if (contaValoresHelper.getConta().getValorCreditos() != null) {
							valorSubtotalCreditosContas = contaValoresHelper.getConta().getValorCreditos();
						} else {
							valorSubtotalCreditosContas = new BigDecimal("0.00");
						}

						if (contaValoresHelper.getValorTotalContaValores() != null) {
							valorSubtotalAcrescimosImpontualidadeContas = contaValoresHelper.getValorTotalContaValores();
						} else {
							valorSubtotalAcrescimosImpontualidadeContas = new BigDecimal("0.00");
						}

					}

					if (relatorioEndereco != null && !relatorioEndereco.trim().equals("")) {

						String endereco = fachada.pesquisarEndereco(contaValoresHelper.getConta().getImovel().getId());

						String nomeUsuario = "";
						String cpfCnpjUsuario = "";
						Object[] dadosCliente = null;
						dadosCliente = fachada.consultarDadosClienteUsuarioImovel(contaValoresHelper.getConta().getImovel().getId() + "");

						if (dadosCliente[0] != null) {
							nomeUsuario = (String) dadosCliente[0];
						}

						if (dadosCliente[1] != null) {
							cpfCnpjUsuario = Util.formatarCpf((String) dadosCliente[1]);
						} else if (dadosCliente[2] != null) {
							cpfCnpjUsuario = Util.formatarCnpj((String) dadosCliente[2]);
						}

						bean = new RelatorioConsultarDebitosBean(
								contaValoresHelper.getConta().getId().toString(),
								mesAnoConta,
								vencimento,
								valorAgua,
								consumoAgua,
								valorEsgoto,
								consumoEsgoto,
								valorDebitos,
								valorCreditos,
								valorConta,
								acrescImpont,
								situacao,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								contaValoresHelper.getConta().getImovel().getId().toString(),
								endereco,
								nomeUsuario,
								imprimirEndereco,
								cpfCnpjUsuario);

						imprimirEndereco = "";

					} else {

						bean = new RelatorioConsultarDebitosBean(
								contaValoresHelper.getConta().getId().toString(),
								mesAnoConta,
								vencimento,
								valorAgua,
								consumoAgua,
								valorEsgoto,
								consumoEsgoto,
								valorDebitos,
								valorCreditos,
								valorConta,
								acrescImpont,
								situacao,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								contaValoresHelper.getConta().getImovel().getId().toString());
					}

				} else {
					bean = new RelatorioConsultarDebitosBean(
							mesAnoConta,
							vencimento,
							valorAgua,
							consumoAgua,
							valorEsgoto,
							consumoEsgoto,
							valorDebitos,
							valorCreditos,
							valorConta,
							acrescImpont,
							situacao,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null);
				}

				retorno.add(bean);

				if (!colecaoContasIterator.hasNext()) {

					if (count > 1) {
						bean = new RelatorioConsultarDebitosBean(
								"Subtotal",
								"SUBTOTAL",
								count + " doc(s)",
								Util.formatarMoedaReal(valorSubtotalAguaContas),
								valorSubtotalAguaConsumoContas.toString(),
								Util.formatarMoedaReal(valorSubtotalEsgotoContas),
								valorSubtotalEsgotoConsumoContas.toString(),
								Util.formatarMoedaReal(valorSubtotalDebitosContas),
								Util.formatarMoedaReal(valorSubtotalCreditosContas),
								Util.formatarMoedaReal(valorSubtotalContas),
								Util.formatarMoedaReal(valorSubtotalAcrescimosImpontualidadeContas),
								"",
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								"");

						retorno.add(bean);
					}

					bean = new RelatorioConsultarDebitosBean(
							"TOTAL",
							colecaoContas.size() + " doc(s)",
							Util.formatarMoedaReal(valorTotalAguaContas),
							valorTotalAguaConsumoContas.toString(),
							Util.formatarMoedaReal(valorTotalEsgotoContas),
							valorTotalEsgotoConsumoContas.toString(),
							Util.formatarMoedaReal(valorTotalDebitosContas),
							Util.formatarMoedaReal(valorTotalCreditosContas),
							Util.formatarMoedaReal(valorTotalContas),
							Util.formatarMoedaReal(valorTotalAcrescimosImpontualidadeContas),
							"",
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null);

					if (pesquisaCliente != null && !pesquisaCliente.equals("")) {
						bean.setIdConta("Total");
						bean.setIdImovel("");
					}

					retorno.add(bean);
				}

			}

		}

		// D�bito a Cobrar
		if (colecaoDebitosACobrar != null && !colecaoDebitosACobrar.isEmpty()) {

			BigDecimal valorTotalDebitoACobrar = new BigDecimal("0.00");

			Iterator colecaoDebitosACobrarIterator = colecaoDebitosACobrar.iterator();

			Integer idImovelAnterior = null;
			int count = 0;
			BigDecimal valorSubtotalDebitoACobrar = new BigDecimal("0.00");

			while (colecaoDebitosACobrarIterator.hasNext()) {

				DebitoACobrar debitoACobrar = (DebitoACobrar) colecaoDebitosACobrarIterator.next();

				String imprimirEndereco = "";

				// Seta o im�vel anterior para verificar, posteriormente, se vai criar um subtotal para o im�vel.
				if (idImovelAnterior == null) {
					idImovelAnterior = debitoACobrar.getImovel().getId();
					imprimirEndereco = "SIM";
				}

				String tipoDebito = debitoACobrar.getDebitoTipo().getDescricao();

				String mesAnoReferenciaDebito = "";
				if (debitoACobrar.getAnoMesReferenciaDebito() != null) {
					mesAnoReferenciaDebito = Util.formatarAnoMesParaMesAno(debitoACobrar.getAnoMesReferenciaDebito());
				}

				String mesAnoCobrancaDebito = "";
				if (debitoACobrar.getAnoMesCobrancaDebito() != null) {
					mesAnoCobrancaDebito = Util.formatarAnoMesParaMesAno(debitoACobrar.getAnoMesCobrancaDebito());
				}

				String parcelasCobrar = "";
				if (debitoACobrar.getParcelasRestanteComBonus() != 0) {
					parcelasCobrar = "" + debitoACobrar.getParcelasRestanteComBonus();
				}

				String valorCobrar = "";
				if (debitoACobrar.getValorTotalComBonus() != null) {
					valorCobrar = Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus());
					valorTotalDebitoACobrar = valorTotalDebitoACobrar.add(debitoACobrar.getValorTotalComBonus());

					if (idImovelAnterior.equals(debitoACobrar.getImovel().getId())) {
						valorSubtotalDebitoACobrar = valorSubtotalDebitoACobrar.add(debitoACobrar.getValorTotalComBonus());
					}
				}

				RelatorioConsultarDebitosBean bean = null;

				if (pesquisaCliente != null && !pesquisaCliente.trim().equals("")) {

					boolean criarSubtotal = false;

					// Caso tenha mudado o im�vel, verifica se teve mais de uma conta para o ele. Em caso afirmativo muda o valor da
					// vari�vel para criar um subtotal, e em caso negativo reinicializa as vari�veis de totaliza��o.
					if (!idImovelAnterior.equals(debitoACobrar.getImovel().getId())) {

						imprimirEndereco = "SIM";

						if (count > 1) {
							criarSubtotal = true;
						} else {
							count = 1;
							idImovelAnterior = debitoACobrar.getImovel().getId();
							if (debitoACobrar.getValorTotalComBonus() != null) {
								valorSubtotalDebitoACobrar = debitoACobrar.getValorTotalComBonus();
							} else {
								valorSubtotalDebitoACobrar = new BigDecimal("0.00");
							}
						}
					} else {
						count = count + 1;
					}

					// Cria o subtotal
					if (criarSubtotal) {
						bean = new RelatorioConsultarDebitosBean(
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								"Subtotal",
								"SUBTOTAL",
								count + "doc(s)",
								"",
								"",
								Util.formatarMoedaReal(valorSubtotalDebitoACobrar),
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								"");

						retorno.add(bean);

						// Reinicializa as vari�veis de totaliza��o e o contador. Al�m disso, muda o im�vel valor do im�vel
						// anterior para o do atual para verificar se ser� necess�rio criar um subtotal.
						count = 1;
						idImovelAnterior = debitoACobrar.getImovel().getId();

						if (debitoACobrar.getValorTotalComBonus() != null) {
							valorSubtotalDebitoACobrar = debitoACobrar.getValorTotalComBonus();
						} else {
							valorSubtotalDebitoACobrar = new BigDecimal("0.00");
						}
					}

					if (relatorioEndereco != null && !relatorioEndereco.trim().equals("")) {

						String endereco = fachada.pesquisarEndereco(debitoACobrar.getImovel().getId());
						String nomeUsuario = "";
						String cpfCnpjUsuario = "";
						Object[] dadosCliente = null;
						dadosCliente = fachada.consultarDadosClienteUsuarioImovel(debitoACobrar.getImovel().getId() + "");

						if (dadosCliente[0] != null) {
							nomeUsuario = (String) dadosCliente[0];
						}

						if (dadosCliente[1] != null) {
							cpfCnpjUsuario = Util.formatarCpf((String) dadosCliente[1]);
						} else if (dadosCliente[2] != null) {
							cpfCnpjUsuario = Util.formatarCnpj((String) dadosCliente[2]);
						}
						bean = new RelatorioConsultarDebitosBean(
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								debitoACobrar.getId().toString(),
								tipoDebito,
								mesAnoReferenciaDebito,
								mesAnoCobrancaDebito,
								parcelasCobrar,
								valorCobrar,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								debitoACobrar.getImovel().getId().toString(),
								endereco,
								nomeUsuario,
								imprimirEndereco,
								cpfCnpjUsuario);

						imprimirEndereco = "";

					} else {

						bean = new RelatorioConsultarDebitosBean(
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								debitoACobrar.getId().toString(),
								tipoDebito,
								mesAnoReferenciaDebito,
								mesAnoCobrancaDebito,
								parcelasCobrar,
								valorCobrar,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								debitoACobrar.getImovel().getId().toString());
					}

				} else {

					bean = new RelatorioConsultarDebitosBean(
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							debitoACobrar.getId().toString(),
							tipoDebito,
							mesAnoReferenciaDebito,
							mesAnoCobrancaDebito,
							parcelasCobrar,
							valorCobrar,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null);
				}

				retorno.add(bean);

				if (!colecaoDebitosACobrarIterator.hasNext()) {

					// Cria o subtotal
					if (count > 1) {
						bean = new RelatorioConsultarDebitosBean(
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								"Subtotal",
								"SUBTOTAL",
								count + "doc(s)",
								"",
								"",
								Util.formatarMoedaReal(valorSubtotalDebitoACobrar),
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								"");

						retorno.add(bean);

					}

					bean = new RelatorioConsultarDebitosBean(
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							"Total",
							"TOTAL",
							colecaoDebitosACobrar.size() + "doc(s)",
							"",
							"",
							Util.formatarMoedaReal(valorTotalDebitoACobrar),
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null);

					if (pesquisaCliente != null && !pesquisaCliente.equals("")) {
						bean.setIdImovel("");
					}

					retorno.add(bean);

				}

			}

		}

		// Cr�dito a Realizar
		if (colecaoCreditosARealizar != null && !colecaoCreditosARealizar.isEmpty()) {

			BigDecimal valorTotalCreditos = new BigDecimal("0.00");

			Iterator colecaoCreditosARealizarIterator = colecaoCreditosARealizar.iterator();

			Integer idImovelAnterior = null;

			while (colecaoCreditosARealizarIterator.hasNext()) {

				CreditoARealizar creditoARealizar = (CreditoARealizar) colecaoCreditosARealizarIterator.next();

				String imprimirEndereco = "";

				// Seta o im�vel anterior para, no relat�rio, verificar se vai exibir o endere�o
				if (idImovelAnterior == null) {
					idImovelAnterior = creditoARealizar.getImovel().getId();
					imprimirEndereco = "SIM";
				}

				String tipoCredito = creditoARealizar.getCreditoTipo().getDescricao();

				String mesAnoReferenciaCredito = "";
				if (creditoARealizar.getAnoMesReferenciaCredito() != null) {
					mesAnoReferenciaCredito = Util.formatarAnoMesParaMesAno(creditoARealizar.getAnoMesReferenciaCredito());
				}

				String mesAnoCobrancaCredito = "";
				if (creditoARealizar.getAnoMesCobrancaCredito() != null) {
					mesAnoCobrancaCredito = Util.formatarAnoMesParaMesAno(creditoARealizar.getAnoMesCobrancaCredito());
				}

				String parcelasCreditar = "";
				if (creditoARealizar.getParcelasRestanteComBonus() != 0) {
					parcelasCreditar = "" + creditoARealizar.getParcelasRestanteComBonus();
				}

				String valorCreditar = "";
				if (creditoARealizar.getValorTotalComBonus() != null) {
					valorCreditar = Util.formatarMoedaReal(creditoARealizar.getValorTotalComBonus());
					valorTotalCreditos = valorTotalCreditos.add(creditoARealizar.getValorTotalComBonus());
				}

				RelatorioConsultarDebitosBean bean = null;

				if (pesquisaCliente != null && !pesquisaCliente.trim().equals("")) {

					if (relatorioEndereco != null && !relatorioEndereco.trim().equals("")) {

						String endereco = fachada.pesquisarEndereco(creditoARealizar.getImovel().getId());

						String nomeUsuario = "";
						String cpfCnpjUsuario = "";
						Object[] dadosCliente = null;
						dadosCliente = fachada.consultarDadosClienteUsuarioImovel(creditoARealizar.getImovel().getId() + "");

						if (dadosCliente[0] != null) {
							nomeUsuario = (String) dadosCliente[0];
						}

						if (dadosCliente[1] != null) {
							cpfCnpjUsuario = Util.formatarCpf((String) dadosCliente[1]);
						} else if (dadosCliente[2] != null) {
							cpfCnpjUsuario = Util.formatarCnpj((String) dadosCliente[2]);
						}

						if (!creditoARealizar.getImovel().getId().equals(idImovelAnterior)) {
							imprimirEndereco = "SIM";
						}

						bean = new RelatorioConsultarDebitosBean(
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								creditoARealizar.getId().toString(),
								tipoCredito,
								mesAnoReferenciaCredito,
								mesAnoCobrancaCredito,
								parcelasCreditar,
								valorCreditar,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								creditoARealizar.getImovel().getId().toString(),
								endereco,
								nomeUsuario,
								imprimirEndereco,
								cpfCnpjUsuario);

						idImovelAnterior = creditoARealizar.getImovel().getId();

						imprimirEndereco = "";

					} else {

						bean = new RelatorioConsultarDebitosBean(
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								creditoARealizar.getId().toString(),
								tipoCredito,
								mesAnoReferenciaCredito,
								mesAnoCobrancaCredito,
								parcelasCreditar,
								valorCreditar,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								creditoARealizar.getImovel().getId().toString());
					}

				} else {

					bean = new RelatorioConsultarDebitosBean(
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							creditoARealizar.getId().toString(),
							tipoCredito,
							mesAnoReferenciaCredito,
							mesAnoCobrancaCredito,
							parcelasCreditar,
							valorCreditar,
							null,
							null,
							null,
							null,
							null,
							null,
							null);
				}

				retorno.add(bean);

				if (!colecaoCreditosARealizarIterator.hasNext()) {

					bean = new RelatorioConsultarDebitosBean(
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							"Total",
							"TOTAL",
							colecaoCreditosARealizar.size() + "doc(s)",
							"",
							"",
							Util.formatarMoedaReal(valorTotalCreditos),
							null,
							null,
							null,
							null,
							null,
							null,
							null);

					if (pesquisaCliente != null && !pesquisaCliente.equals("")) {
						bean.setIdImovel("");
					}

					retorno.add(bean);
				}

			}

		}

		// Guia Pagamento
		if (colecaoGuiasPagamento != null && !colecaoGuiasPagamento.isEmpty()) {

			BigDecimal valorTotalGuias = new BigDecimal("0.00");
			Integer idImovelAnterior = null;

			Iterator colecaoGuiasPagamentoIterator = colecaoGuiasPagamento.iterator();

			while (colecaoGuiasPagamentoIterator.hasNext()) {

				GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiasPagamentoIterator.next();

				FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, guiaPagamentoValoresHelper.getGuiaPagamento().getId()));

				Collection colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());

				GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(colecaoGuiaPagamento);

				String imprimirEndereco = "";

				String tipoDebito = guiaPagamentoValoresHelper.getGuiaPagamento().getDebitoTipo().getDescricao();

				String dataEmissaoGuia = "";
				if (guiaPagamentoValoresHelper.getGuiaPagamento().getDataEmissao() != null) {
					dataEmissaoGuia = Util.formatarData(guiaPagamentoValoresHelper.getGuiaPagamento().getDataEmissao());
				}

				String dataVencimentoGuia = "";
				if (guiaPagamentoValoresHelper.getGuiaPagamento().getDataVencimento() != null) {
					dataVencimentoGuia = Util.formatarData(guiaPagamentoValoresHelper.getGuiaPagamento().getDataVencimento());
				}

				String valorGuia = "";
				if (guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito() != null) {
					valorGuia = Util.formatarMoedaReal(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
					valorTotalGuias = valorTotalGuias.add(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
				}

				RelatorioConsultarDebitosBean bean = null;

				if (pesquisaCliente != null && !pesquisaCliente.trim().equals("")) {

					String idImovel = "";
					String endereco = "";
					String nomeUsuario = "";
					String cpfCnpjUsuario = "";

					if (guiaPagamento.getImovel() != null && guiaPagamento.getImovel().getId() != null) {

						idImovel = guiaPagamento.getImovel().getId().toString();

						// Seta o im�vel anterior para, no relat�rio, verificar se vai exibir o endere�o
						if (idImovelAnterior == null) {
							idImovelAnterior = guiaPagamento.getImovel().getId();
							imprimirEndereco = "SIM";
						}

						endereco = fachada.pesquisarEndereco(new Integer(idImovel));

						Object[] dadosCliente = null;

						dadosCliente = fachada.consultarDadosClienteUsuarioImovel(idImovel);

						if (dadosCliente[0] != null) {
							nomeUsuario = (String) dadosCliente[0];
						}

						if (dadosCliente[1] != null) {
							cpfCnpjUsuario = Util.formatarCpf((String) dadosCliente[1]);
						} else if (dadosCliente[2] != null) {
							cpfCnpjUsuario = Util.formatarCnpj((String) dadosCliente[2]);
						}

					} else {
						endereco = fachada.pesquisarEnderecoClienteAbreviado(new Integer(codigoCliente));
						nomeUsuario = nomeCliente;

						FiltroCliente filtroCliente = new FiltroCliente();
						filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, codigoCliente));
						Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());

						if (colecaoCliente != null && !colecaoCliente.equals("")) {
							Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);

							if (cpfCnpjUsuario != null) {
								cpfCnpjUsuario = cliente.getCpfFormatado();
							} else {
								cpfCnpjUsuario = cliente.getCnpjFormatado();
							}
						}

						// Seta o cliente anterior para, no relat�rio, verificar se vai exibir o endere�o
						if (idImovelAnterior == null) {
							idImovelAnterior = guiaPagamento.getCliente().getId();
							imprimirEndereco = "SIM";
						}
					}

					if (relatorioEndereco != null && !relatorioEndereco.trim().equals("")) {

						if (!idImovel.equals(idImovelAnterior.toString())) {
							imprimirEndereco = "SIM";
						}

						bean = new RelatorioConsultarDebitosBean(
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								guiaPagamentoValoresHelper.getGuiaPagamento().getId().toString(),
								tipoDebito,
								dataEmissaoGuia,
								dataVencimentoGuia,
								valorGuia,
								null,
								null,
								idImovel,
								endereco,
								nomeUsuario,
								imprimirEndereco,
								cpfCnpjUsuario);

						imprimirEndereco = "";

						if (guiaPagamento.getImovel() != null && guiaPagamento.getImovel().getId() != null) {
							idImovelAnterior = guiaPagamento.getImovel().getId();
						} else {
							idImovelAnterior = guiaPagamento.getCliente().getId();
						}

					} else {

						bean = new RelatorioConsultarDebitosBean(
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								guiaPagamentoValoresHelper.getGuiaPagamento().getId().toString(),
								tipoDebito,
								dataEmissaoGuia,
								dataVencimentoGuia,
								valorGuia,
								null,
								null,
								idImovel);
					}

				} else {

					bean = new RelatorioConsultarDebitosBean(
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							guiaPagamentoValoresHelper.getGuiaPagamento().getId().toString(),
							tipoDebito,
							dataEmissaoGuia,
							dataVencimentoGuia,
							valorGuia,
							null,
							null);

				}

				retorno.add(bean);

				if (!colecaoGuiasPagamentoIterator.hasNext()) {

					bean = new RelatorioConsultarDebitosBean(
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							"Total",
							"TOTAL",
							colecaoGuiasPagamento.size() + "doc(s)",
							"",
							Util.formatarMoedaReal(valorTotalGuias),
							null,
							null);

					bean.setIdImovel("");

					retorno.add(bean);

				}

			}

		}

		if (valorTotalDebitos != null) {
			RelatorioConsultarDebitosBean bean = new RelatorioConsultarDebitosBean(null, null, null, null, null, null, null, null, null, null, null, null, null, 
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, valorTotalDebitos, valorTotalDebitosAtualizado);
			retorno.add(bean);
		}

		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object executar() throws TarefaException {
		Collection colecaoContaValores = (Collection) getParametro("colecaoContaValores");
		Collection colecaoDebitoACobrar = (Collection) getParametro("colecaoDebitoACobrar");
		Collection colecaoCreditoARealizar = (Collection) getParametro("colecaoCreditoARealizar");
		Collection colecaoGuiaPagamento = (Collection) getParametro("colecaoGuiasPagamento");
		String pesquisaCliente = (String) getParametro("pesquisaCliente");
		String relatorioEndereco = (String) getParametro("relatorioEndereco");
		String codigoCliente = (String) getParametro("codigoCliente");
		String nomeCliente = (String) getParametro("nomeCliente");
		String cpfCnpj = (String) getParametro("cpfCnpj");
		String idImovel = (String) getParametro("idImovel");
		String inscricao = (String) getParametro("inscricao");
		String clienteUsuario = (String) getParametro("clienteUsuario");
		String cpfCnpjCliente = (String) getParametro("cpfCnpjCliente");
		String endereco = (String) getParametro("endereco");
		ClienteRelacaoTipo tipoRelacao = (ClienteRelacaoTipo) getParametro("tipoRelacao");
		String periodoReferenciaInicial = (String) getParametro("periodoReferenciaInicial");
		String periodoReferenciaFinal = (String) getParametro("periodoReferenciaFinal");
		String dataVencimentoInicial = (String) getParametro("dataVencimentoInicial");
		String dataVencimentoFinal = (String) getParametro("dataVencimentoFinal");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}

		Usuario usuario = this.getUsuario();

		String nomeUsuario = "";
		nomeUsuario = usuario.getNomeUsuario();

		parametros.put("nomeUsuario", nomeUsuario);
		parametros.put("nomeEmpresa", sistemaParametro.getNomeAbreviadoEmpresa());
		parametros.put("cnpjEmpresa", cnpjEmpresa);

		if (pesquisaCliente != null && !pesquisaCliente.trim().equals("")) {
			parametros.put("codigoCliente", codigoCliente);
			parametros.put("nomeCliente", nomeCliente);
			if (cpfCnpj != null && !cpfCnpj.equals("")) {
				parametros.put("cpfCnpj", cpfCnpj);
			} else {
				cpfCnpj = "";
				parametros.put("cpfCnpj", cpfCnpj);
			}

			if (periodoReferenciaInicial != null && !periodoReferenciaInicial.trim().equals("")) {
				parametros.put("periodoReferenciaDebito", periodoReferenciaInicial + " a " + periodoReferenciaFinal);
			}

			if (dataVencimentoInicial != null && !dataVencimentoInicial.trim().equals("")) {
				parametros.put("periodoVencimentoDebito", dataVencimentoInicial + " a " + dataVencimentoFinal);
			}

			if (tipoRelacao != null) {
				parametros.put("tipoRelacao", tipoRelacao.getDescricao());
			}

		} else {
			parametros.put("idImovel", idImovel);
			parametros.put("inscricao", inscricao);
			parametros.put("clienteUsuario", clienteUsuario);
			parametros.put("endereco", endereco);
			parametros.put("cpfCnpjCliente", cpfCnpjCliente);
		}

		Collection<RelatorioConsultarDebitosBean> colecaoBean = this.inicializarBeanRelatorio(colecaoContaValores, colecaoDebitoACobrar, colecaoCreditoARealizar, colecaoGuiaPagamento);

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		if (relatorioEndereco != null && !relatorioEndereco.trim().equals("")) {
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_CLIENTE_ENDERECO, parametros, ds, tipoFormatoRelatorio);
		} else if (pesquisaCliente != null && !pesquisaCliente.trim().equals("")) {
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_CLIENTE, parametros, ds, tipoFormatoRelatorio);
		} else {
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS, parametros, ds, tipoFormatoRelatorio);
		}

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarDebitos", this);
	}
}
