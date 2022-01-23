package br.com.alura.loja.modelo;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.alura.loja.util.JPAUtil;

public class TestProduto {
	
	EntityManager em = JPAUtil.getEntityManager();
	
	@Test
	public void testeDeletaObjeto() {
		Categoria computadores = new Categoria("COMPUTADOR");
		em.getTransaction().begin();
		
		em.persist(computadores);
		computadores.setNome("XPTO");
		
		em.flush();
		em.clear();
		
		computadores = em.merge(computadores);
		computadores.setNome("XPTO2");
		em.flush();
//		em.clear(); //Não lança exceção porém não realiza delete quando ativada esta linha
//		em.close(); //Não lança exceção porém não realiza delete quando ativada esta linha
		em.remove(computadores);
		em.flush();
		
		System.out.println("Fim testeDeletaObjeto");
	
	}
	
	
	@Test
	public void testeRecuperaEstadoGerenciadoDoObjeto() {
		Categoria computadores = new Categoria("COMPUTADOR");
//		Produto computador = new Produto("Computador", "Samsung", new BigDecimal(1500), computadores);
		
		
		em.getTransaction().begin();
		em.persist(computadores);

		computadores.setNome("XPTO");
		em.flush();
		em.clear();
		
		computadores = em.merge(computadores);
		computadores.setNome("XPTO2");
		em.flush();
		
		System.out.println("testeRecuperaEstadoGerenciadoDoObjeto");
	
	}
	
	@Test
	public void testeInsereUmaVezE_Nao_AtualizaProduto() {
		Categoria computadores = new Categoria("COMPUTADOR");
//		Produto computador = new Produto("Computador", "Samsung", new BigDecimal(1500), computadores);
		
		
		em.getTransaction().begin();
		// Na execução da linha abaixo o objeto computadores fica sendo gerenciado pelo banco de dados
		em.persist(computadores);

		computadores.setNome("XPTO");
		em.clear(); // Após esse ponto o objeto deixa de ser gerenciado pelo banco de dados
		computadores.setNome("XPTO2");
		em.flush();
		computadores.setNome("XPTO3");
	
	}
	
	@Test
	public void testeInsereUmaVezEAtualizaUmaVezProduto() {
		Categoria computadores = new Categoria("COMPUTADOR");
//		Produto computador = new Produto("Computador", "Samsung", new BigDecimal(1500), computadores);
		
		
		em.getTransaction().begin();
		// Na execução da linha abaixo o objeto computadores fica sendo gerenciado pelo banco de dados
		em.persist(computadores);
		// Abaixo a propriedade do nome é alterada pelo banco de dados após o commit
		computadores.setNome("XPTO");
		em.flush();
		em.getTransaction().commit();
		computadores.setNome("XPTO2");//Objeto não está mais gerenciado neste ponto
		
		em.close();
	
	}

}
