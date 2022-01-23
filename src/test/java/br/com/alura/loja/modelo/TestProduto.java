package br.com.alura.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.alura.loja.util.JPAUtil;

public class TestProduto {
	@Test
	public void testeInserePorduto() {
		EntityManager em = JPAUtil.getEntityManager();
		
//		em.persist(new Produto("Computador", "Samsung", new BigDecimal(1500), Categoria));
		
	
	}

}
