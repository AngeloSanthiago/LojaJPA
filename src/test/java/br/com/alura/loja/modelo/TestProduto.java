package br.com.alura.loja.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.testes.CadastroDeProduto;
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
		CategoriaDao compDao = new CategoriaDao(em);
//		Produto computador = new Produto("Computador", "Samsung", new BigDecimal(1500), computadores);
		
		
		em.getTransaction().begin();
		// Na execução da linha abaixo o objeto computadores fica sendo gerenciado pelo banco de dados
//		em.persist(computadores);
		compDao.cadastrar(computadores);
		// Abaixo a propriedade do nome é alterada pelo banco de dados após o commit
		computadores.setNome("XPTO");
		em.flush();
		em.getTransaction().commit();
		computadores.setNome("XPTO2");//Objeto não está mais gerenciado neste ponto
		
		em.close();
	}
	
	@Test
	public void test01_RecuperaUmProduto() {
		CadastroDeProduto.cadastrarProduto();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto p = produtoDao.buscarPorId(1l);
		
		assertEquals(new BigDecimal("800.00"), p.getPreco());
		
	}
	
	@Test
	public void test02_TodosOsProdutos() {
		CadastroDeProduto.cadastrarProduto();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		List<Produto> produtos = produtoDao.buscarTodos();
		
		Stream<Produto> produtosFiltrados = produtos.stream().filter(p -> p.getNome() == "Xiaomi Redmi");
		produtos.forEach(p -> System.out.println(p.getNome()));
		
		assertTrue(produtosFiltrados.count() > 0L);
	}

}
