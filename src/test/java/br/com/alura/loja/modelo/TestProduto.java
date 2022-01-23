package br.com.alura.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.alura.loja.util.JPAUtil;

public class TestProduto {
	
	EntityManager em = JPAUtil.getEntityManager();
	
	@Test
	public void testeInserePorduto() {
		Categoria computadores = new Categoria("COMPUTADOR");
//		Produto computador = new Produto("Computador", "Samsung", new BigDecimal(1500), computadores);
		
		
		em.getTransaction().begin();
		// Na execução da linha abaixo o objeto computadores fica sendo gerenciado pelo banco de dados
		em.persist(computadores);
		// Abaixo a propriedade do nome é alterada pelo banco de dados após o commit
		computadores.setNome("XPTO");
//		em.flush();
		em.getTransaction().commit();
//		computadores.setNome("XPTO2");
		
		em.close();
	
	}

}
