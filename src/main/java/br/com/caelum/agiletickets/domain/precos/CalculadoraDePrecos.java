package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {
	
	private static final double PERCENTUAL_LIMITE_BALLET_OU_ORQUESTRA = 0.5;
	private static final double PERCENTUAL_10 = 0.10;
	private static final double PERCENTUAL_LIMITE_CINEMA_OU_SHOW = 0.05;
	private static final double PERCENTUAL_20 = 0.2;

	private static boolean ehCinema(Sessao sessao) {
		return sessaoEhDoTipo(sessao, TipoDeEspetaculo.CINEMA);
	}
	
	private static boolean ehShow(Sessao sessao) {
		return sessaoEhDoTipo(sessao, TipoDeEspetaculo.SHOW);
	}
	
	private static boolean ehBallet(Sessao sessao) {
		return sessaoEhDoTipo(sessao, TipoDeEspetaculo.BALLET);
	}
	
	private static boolean ehOrquestra(Sessao sessao) {
		return sessaoEhDoTipo(sessao, TipoDeEspetaculo.ORQUESTRA);
	}
	
	private static boolean sessaoEhDoTipo(Sessao sessao, TipoDeEspetaculo tipoEspetaculo) {
		return sessao.getEspetaculo().getTipo().equals(tipoEspetaculo);
	}
	
	private static boolean percentualIngressosRestantesMenorQue(Sessao sessao, double percentual) {
		return (sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= percentual;
	}

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		if(ehCinema(sessao) || ehShow(sessao)) {
			//quando estiver acabando os ingressos... 
			if(percentualIngressosRestantesMenorQue(sessao, PERCENTUAL_LIMITE_CINEMA_OU_SHOW)) { 
				preco = aumentaPrecoIngresso(sessao.getPreco(), calculaAumento(sessao.getPreco(), PERCENTUAL_10));
			} else {
				preco = sessao.getPreco();
			}
		} else if(ehBallet(sessao)) {
			if(percentualIngressosRestantesMenorQue(sessao, PERCENTUAL_LIMITE_BALLET_OU_ORQUESTRA)) { 
				preco = aumentaPrecoIngresso(sessao.getPreco(), calculaAumento(sessao.getPreco(), PERCENTUAL_20));
			} else {
				preco = sessao.getPreco();
			}
			
			boolean sessaoTemMaisDeUmaHora = sessao.getDuracaoEmMinutos() > 60;
			if(sessaoTemMaisDeUmaHora){
				preco = aumentaPrecoIngresso(preco, calculaAumento(preco, PERCENTUAL_10));
			}
		} else if(ehOrquestra(sessao)) {
			if(percentualIngressosRestantesMenorQue(sessao, PERCENTUAL_LIMITE_BALLET_OU_ORQUESTRA)) { 
				preco = aumentaPrecoIngresso(sessao.getPreco(), calculaAumento(sessao.getPreco(), PERCENTUAL_20));
			} else {
				preco = sessao.getPreco();
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = aumentaPrecoIngresso(preco, calculaAumento(sessao.getPreco(), PERCENTUAL_10));
			}
		}  else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calculaAumento(BigDecimal precoAtual, double percentualDesconto) {
		return precoAtual.multiply(BigDecimal.valueOf(percentualDesconto));
	}
	
	@Deprecated
	private static BigDecimal aumentaPrecoIngresso(BigDecimal precoAtual, double percentualAumento) {
		return precoAtual.add(precoAtual.multiply(BigDecimal.valueOf(percentualAumento)));
	}
	
	private static BigDecimal aumentaPrecoIngresso(BigDecimal precoAtual, BigDecimal aumento) {
		return precoAtual.add(aumento);
	}

}