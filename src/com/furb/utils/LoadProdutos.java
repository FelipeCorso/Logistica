package com.furb.utils;

import java.util.ArrayList;
import java.util.List;

import com.furb.produto.Produto;

public class LoadProdutos {
	private List<Produto> listaProdutos;
	private static LoadProdutos loadProdutos;

	private LoadProdutos() {
		// Singleton
	}

	public static LoadProdutos getInstance() {
		if (loadProdutos == null) {
			loadProdutos = new LoadProdutos();
		}
		return loadProdutos;
	}

	/**
	 * Retorna de forma aleatória um objeto da lista de produtos.
	 * 
	 * @return um objeto da classe {@link Produto}
	 */
	public Produto getProduto() {
		return this.getListaProdutos().get((int) (Math.random() * this.getListaProdutos().size()));
	}

	/**
	 * Retorna uma lista de objetos da classe {@link Produto}.
	 * 
	 * @return uma lista de {@link Produto}
	 */
	public List<Produto> getListaProdutos() {
		if (this.listaProdutos == null) {
			this.listaProdutos = new ArrayList<Produto>();
			populaLista();
		}

		return listaProdutos;
	}

	private void populaLista() {

		// double largura, double altura, double peso, String descricao)
		criaProduto(180, 100, 20, 450, "MESA COM 6 CADEIRAS");
		criaProduto(180, 150, 30, 1359, "CONJUNTO DE ESTOFADOS");
		criaProduto(120, 100, 0.2, 69, "CONJUNTO DE TOALHAS DE BANHO");
		criaProduto(80, 40, 10, 1999, "TV DE LCD");
		criaProduto(45, 30, 2, 75, "VENTILADOR");
		criaProduto(35, 20, 0.5, 45, "LIQUIDIFICADOR");
		criaProduto(40, 25, 1, 35, "SANDUICHEIRA");
		criaProduto(45, 30, 15, 415, "MICROONDAS");
		criaProduto(130, 80, 40, 750, "FREEZER");
		criaProduto(35, 40, 1.5, 120, "BATEDEIRA");
		criaProduto(80, 45, 20, 999, "LAVADORA DE ROUPAS");
		criaProduto(60, 45, 30, 1345, "LAVADORA DE LOUÇAS");
		criaProduto(120, 100, 2, 150, "MESAS DE PLÁSTICO");
		criaProduto(100, 40, 2, 45, "CADEIRA");
		criaProduto(100, 50, 10, 89, "CONJUNTO DE PANELAS");
		criaProduto(180, 100, 20, 700, "GELADEIRA");
		criaProduto(80, 40, 10, 350, "CENTRÍFUGA");
		criaProduto(35, 20, 2, 39, "CAFETEIRA");
		criaProduto(50, 30, 2, 185, "ASPIRADOR DE PÓ");
		criaProduto(80, 40, 7, 178, "CIRCULADOR DE AR");
		criaProduto(60, 45, 9, 357, "FORNO ELÉTRICO");
		criaProduto(75, 55, 17, 457, "FOGÃO");
		criaProduto(30, 15, 0.3, 67, "SECADOR DE CABELO");
		criaProduto(100, 35, 3, 123, "ENCERADEIRA");
		criaProduto(25, 15, 0.530, 700, "FILMADORA");
		criaProduto(55, 35, 1.530, 1235, "HOME THEATER");
		criaProduto(55, 35, 3.530, 100, "VIDEOCASSETE");
		criaProduto(65, 45, 23.530, 799, "AR CONDICIONADO");
		criaProduto(45, 25, 2.530, 199, "AQUECEDOR");

	}

	private void criaProduto(double largura, double altura, double peso, double vlrUnitario, String descricao) {
		this.listaProdutos.add(new Produto(this.listaProdutos.size() + 1, largura, altura, peso, vlrUnitario, descricao));
	}
}
