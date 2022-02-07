package com.wtiinfo.cursomc;

import com.wtiinfo.cursomc.domain.Categoria;
import com.wtiinfo.cursomc.domain.Cidade;
import com.wtiinfo.cursomc.domain.Estado;
import com.wtiinfo.cursomc.domain.Produto;
import com.wtiinfo.cursomc.repositories.CategoriaRepository;
import com.wtiinfo.cursomc.repositories.CidadeRepository;
import com.wtiinfo.cursomc.repositories.EstadoRepository;
import com.wtiinfo.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepo;

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private EstadoRepository estadoRepo;

	@Autowired
	private CidadeRepository cidadeRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 1998.99);
		Produto p2 = new Produto(null, "Impressora", 890.00);
		Produto p3 = new Produto(null, "Mouse", 78.90);

		cat1.getProdutos().addAll(List.of(p1, p2, p3));
		cat2.getProdutos().addAll(List.of(p2));

		p1.getCategorias().addAll(List.of(cat1));
		p2.getCategorias().addAll(List.of(cat1, cat2));
		p3.getCategorias().addAll(List.of(cat1));

		categoriaRepo.saveAll(List.of(cat1, cat2));
		produtoRepo.saveAll(List.of(p1, p2, p3));

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", e1);
		Cidade c2 = new Cidade(null, "São Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().addAll(List.of(c1));
		e2.getCidades().addAll(List.of(c2, c3));

		estadoRepo.saveAll(List.of(e1, e2));
		cidadeRepo.saveAll(List.of(c1, c2, c3));

	}

}
