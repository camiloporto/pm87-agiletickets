package br.com.caelum.agiletickets.maiormenor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MaiorMenorTest {
	
	private MaiorMenor maiorMenor;

	@Before
	public void setup() {
		maiorMenor = new MaiorMenor();
		System.out.println("Oi!");
	}
	
	@Test
	public void numero_em_qualquer_ordem() {
		maiorMenor.encontra(new int[]{4, 15, 7, 8});
		
		Assert.assertEquals(15, maiorMenor.getMaior());
		Assert.assertEquals(4, maiorMenor.getMenor());
		
	}
	
	@Test
	public void numero_em_ordem_decrescente() {
		maiorMenor.encontra(new int[]{5, 4, 3});
		
		Assert.assertEquals(5, maiorMenor.getMaior());
		Assert.assertEquals(3, maiorMenor.getMenor());
		
	}
	
	@Test
	public void numero_em_ordem_crescente() {
		maiorMenor.encontra(new int[]{3, 4, 5});
		
		Assert.assertEquals(5, maiorMenor.getMaior());
		Assert.assertEquals(3, maiorMenor.getMenor());
		
	}
	
	@Test
	public void lista_com_apenas_um_elemento() {
		maiorMenor.encontra(new int[]{4});
		
		Assert.assertEquals(4, maiorMenor.getMaior());
		Assert.assertEquals(4, maiorMenor.getMenor());
		
	}

}
