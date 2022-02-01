package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		cadastrarProduto();
	}

	public static void cadastrarProduto() {
		Categoria celurares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal",new BigDecimal("800"), celurares );
		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celurares);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}

}
