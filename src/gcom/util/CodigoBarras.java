package gcom.util;

import java.math.BigDecimal;
import java.util.Date;

public class CodigoBarras {

	public static String obterEspecificacaoCodigoBarraFichaCompensacao(String codigoBanco, String codigoMoeda, BigDecimal valorCodigoBarra, String nossoNumeroSemDV, String carteira, String fatorVencimento) {
		
        // Cria a vari�vel que vai armazenar a representa��o n�merica do c�digo de barras
        StringBuilder representacaoNumericaCodigoBarra = new StringBuilder();

        // G.05.1 - C�digo do Banco
        codigoBanco = Util.adicionarZerosEsquedaNumero(3,codigoBanco);
        representacaoNumericaCodigoBarra.append(codigoBanco);

        // G.05.2 - C�digo da Moeda
        representacaoNumericaCodigoBarra.append(codigoMoeda);
        
        // G.05.4 - Fator de Vencimento
        fatorVencimento = Util.adicionarZerosEsquedaNumero(4,fatorVencimento);
        representacaoNumericaCodigoBarra.append(fatorVencimento);

        // G.05.5 - Valor do c�digo de barras
        String valorCodigoBarraFormatado = Util.adicionarZerosEsquedaNumero(10, valorCodigoBarra.setScale(2).toString().replace(".", ""));
        representacaoNumericaCodigoBarra.append(valorCodigoBarraFormatado);

        // G.05.6 - Zeros
        String zeros = "";
        zeros = Util.adicionarZerosEsquedaNumero(6,zeros);
        representacaoNumericaCodigoBarra.append(zeros);

        // G.05.7 Nosso n�mero sem o DV
        nossoNumeroSemDV = Util.adicionarZerosEsquedaNumero(17,nossoNumeroSemDV);
        representacaoNumericaCodigoBarra.append(nossoNumeroSemDV);

        // G.05.8 Tipo de Carteira
        carteira = Util.adicionarZerosEsquedaNumero(2,carteira);
        representacaoNumericaCodigoBarra.append(carteira);
        
        // G.05.3 - D�gito verificador geral
        // [SB0001] Obter D�gito verificador geral
        String digitoVerificadorGeral = (Util.obterDigitoVerificadorModulo11(representacaoNumericaCodigoBarra.toString())).toString();

        if(digitoVerificadorGeral.equalsIgnoreCase("0") ||
                digitoVerificadorGeral.equalsIgnoreCase("10") ||
                digitoVerificadorGeral.equalsIgnoreCase("11")){
            digitoVerificadorGeral = "1";
        }
        
        // Monta a representa�a� n�merica com todos os campos informados
        representacaoNumericaCodigoBarra = new StringBuilder(codigoBanco)
        		.append(codigoMoeda)
        		.append(digitoVerificadorGeral)
        		.append(fatorVencimento)
        		.append(valorCodigoBarraFormatado)
        		.append(zeros)
        		.append(nossoNumeroSemDV)
        		.append(carteira);

        // Retorna a representa��o n�merica do c�digo de barras
        return representacaoNumericaCodigoBarra.toString();
	}
	
	public static final String obterFatorVencimento(Date dataVencimento){
		Date dataBase = Util.criarData(07, 10, 1997);

		return String.valueOf(Util.obterQuantidadeDiasEntreDuasDatas(dataBase, dataVencimento));
	}
	
    public static String obterRepresentacaoNumericaCodigoBarraFichaCompensacao(String especificacaoCodigoBarra) {
        
        String representacaoNumericaCodigoBarra = especificacaoCodigoBarra;

        // Separa as 44 posi��es do c�digo de barras em 5 partes, 
        // sendo 3 primeiros constituidos por DV(m�dulo 10) e, entre cada campo, 
        // espa�o equivalente a uma posi��o.No quarto campo � indicado, isoladamente, 
        // o DV(m�dulo 11) do c�digo de barras.
        String codigoBarraCampo1 = representacaoNumericaCodigoBarra.substring(0, 4) + representacaoNumericaCodigoBarra.substring(20, 21);
        codigoBarraCampo1 = codigoBarraCampo1 + "." +  representacaoNumericaCodigoBarra.substring(21, 25);
        String codigoBarraDigitoVerificadorCampo1 = (Util.obterDigitoVerificadorModulo10(new Long(codigoBarraCampo1.replace(".","")))).toString();
        codigoBarraDigitoVerificadorCampo1 = codigoBarraDigitoVerificadorCampo1 + " ";
        
        String codigoBarraCampo2 = representacaoNumericaCodigoBarra.substring(24, 29);
        codigoBarraCampo2 = codigoBarraCampo2 + "." +  representacaoNumericaCodigoBarra.substring(29, 34);
        String codigoBarraDigitoVerificadorCampo2 = (Util.obterDigitoVerificadorModulo10(new Long(codigoBarraCampo2.replace(".","")))).toString();
        codigoBarraDigitoVerificadorCampo2 = codigoBarraDigitoVerificadorCampo2 + " ";
        
        String codigoBarraCampo3 = representacaoNumericaCodigoBarra.substring(34, 39);
        codigoBarraCampo3 = codigoBarraCampo3 + "." +  representacaoNumericaCodigoBarra.substring(39, 44);
        String codigoBarraDigitoVerificadorCampo3 = (Util.obterDigitoVerificadorModulo10(new Long(codigoBarraCampo3.replace(".","")))).toString();
        codigoBarraDigitoVerificadorCampo3 = codigoBarraDigitoVerificadorCampo3 + " ";
        
        String codigoBarraDigitoVerificadorCampo4 = representacaoNumericaCodigoBarra.substring(4,5) + " ";
        
        String codigoBarraCampo5 = representacaoNumericaCodigoBarra.substring(5, 19);
        
        // Monta a representa��o n�merica do c�digo de barras com os d�gitos verificadores
        representacaoNumericaCodigoBarra = codigoBarraCampo1
                + codigoBarraDigitoVerificadorCampo1 
                + codigoBarraCampo2
                + codigoBarraDigitoVerificadorCampo2 
                + codigoBarraCampo3
                + codigoBarraDigitoVerificadorCampo3 
                + codigoBarraDigitoVerificadorCampo4
                + codigoBarraCampo5;
        
        // Retorna a representa��o n�merica do c�digo de barras
        return representacaoNumericaCodigoBarra;
    }	
}
