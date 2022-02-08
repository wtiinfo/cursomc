package com.wtiinfo.cursomc;

import com.wtiinfo.cursomc.domain.*;
import com.wtiinfo.cursomc.domain.enums.EstadoPagamento;
import com.wtiinfo.cursomc.domain.enums.TipoCliente;
import com.wtiinfo.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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

	@Autowired
	private EnderecoRepository enderecoRepo;

	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private PedidoRepository pedidoRepo;

	@Autowired
	private PagamentoRepository pagamentoRepo;

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

		Cliente cl1 = new Cliente(null, "Maria", "mari@gmail.com", "222.222.222-22", TipoCliente.PESSOA_FISICA);
		cl1.getTelefones().addAll(List.of("4444-22222", "9999-1111"));

		Endereco ed1 = new Endereco(null, "Rua Flores", "123", "Jardins", "Santa Luz", "09444-000", cl1, c1);
		Endereco ed2 = new Endereco(null, "Rua Domingos", "321", "Estrada Velha", "Luzitano", "09444-320", cl1, c2);

		cl1.getEnderecos().addAll(List.of(ed1, ed2));
		clienteRepo.saveAll(List.of(cl1));
		enderecoRepo.saveAll(List.of(ed1, ed2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido pd1 = new Pedido(null, sdf.parse("30/07/2021 10:32"), cl1, ed1);
		Pedido pd2 = new Pedido(null, sdf.parse("30/09/2021 11:11"), cl1, ed2);

		Pagamento pg1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pd1, 6);
		pd1.setPagamento(pg1);

		Pagamento pg2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pd2, sdf.parse("20/10/2021 00:00"), null);
		pd2.setPagamento(pg2);

		cl1.getPedidos().addAll(List.of(pd1, pd2));

		pedidoRepo.saveAll(List.of(pd1, pd2));
		pagamentoRepo.saveAll(List.of(pg1, pg2));

	}

}
