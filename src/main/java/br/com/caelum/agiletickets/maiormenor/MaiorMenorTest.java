package br.com.caelum.agiletickets.maiormenor;

import org.junit.Assert;
import org.junit.Test;

public class MaiorMenorTest {
	
	@Test
	public void numero_em_qualquer_ordem() {
		MaiorMenor maiorMenor = new MaiorMenor();
		maiorMenor.encontra(new int[]{4, 15, 7, 8});
		
		Assert.assertEquals(15, maiorMenor.getMaior());
		Assert.assertEquals(4, maiorMenor.getMenor());
		
	}
	
	@Test
	public void numero_em_ordem_decrescente() {
		MaiorMenor maiorMenor = new MaiorMenor();
		maiorMenor.encontra(new int[]{5, 4, 3});
		
		Assert.assertEquals(5, maiorMenor.getMaior());
		Assert.assertEquals(3, maiorMenor.getMenor());
		
	}
	
	@Test
	public void numero_em_ordem_crescente() {
		MaiorMenor maiorMenor = new MaiorMenor();
		maiorMenor.encontra(new int[]{3, 4, 5});
		
		Assert.assertEquals(5, maiorMenor.getMaior());
		Assert.assertEquals(3, maiorMenor.getMenor());
		
	}
	
	@Test
	public void lista_com_apenas_um_elemento() {
		MaiorMenor maiorMenor = new MaiorMenor();
		maiorMenor.encontra(new int[]{4});
		
		Assert.assertEquals(4, maiorMenor.getMaior());
		Assert.assertEquals(4, maiorMenor.getMenor());
		
	}

}
