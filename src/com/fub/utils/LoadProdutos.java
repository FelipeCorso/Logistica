package com.fub.utils;

import java.util.ArrayList;
import java.util.List;

import com.fub.produto.Produto;

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
		criaProduto(180, 100, 20, "MESA COM 6 CADEIRAS");
		criaProduto(180, 150, 30, "CONJUNTO DE ESTOFADOS");
		criaProduto(120, 100, 0.2, "CONJUNTO DE TOALHAS DE BANHO");
		criaProduto(80, 40, 10, "TV DE LCD");
		criaProduto(45, 30, 2, "VENTILADOR");
		criaProduto(35, 20, 0.5, "LIQUIDIFICADOR");
		criaProduto(40, 25, 1, "SANDUICHEIRA");
		criaProduto(45, 30, 15, "MICROONDAS");
		criaProduto(130, 80, 40, "FREEZER");
		criaProduto(35, 40, 1.5, "BATEDEIRA");
		criaProduto(80, 45, 20, "LAVADORA DE ROUPAS");
		criaProduto(60, 45, 30, "LAVADORA DE LOUÇAS");
		criaProduto(120, 100, 2, "MESAS DE PLÁSTICO");
		criaProduto(100, 40, 2, "CADEIRA");
		criaProduto(100, 50, 10, "CONJUNTO DE PANELAS");
		criaProduto(180, 100, 20, "GELADEIRA");
		criaProduto(80, 40, 10, "CENTRÍFUGA");
		criaProduto(35, 20, 2, "CAFETEIRA");
		criaProduto(50, 30, 2, "ASPIRADOR DE PÓ");
		criaProduto(80, 40, 7, "CIRCULADOR DE AR");
		criaProduto(60, 45, 9, "FORNO ELÉTRICO");
		criaProduto(75, 55, 17, "FOGÃO");
		criaProduto(30, 15, 0.3, "SECADOR DE CABELO");
		criaProduto(100, 35, 3, "ENCERADEIRA");
		criaProduto(25, 15, 0.530, "FILMADORA");
		criaProduto(55, 35, 1.530, "HOME THEATER");
		criaProduto(55, 35, 3.530, "VIDEOCASSETE");
		criaProduto(65, 45, 23.530, "AR CONDICIONADO");
		criaProduto(45, 25, 2.530, "AQUECEDOR");

	}

	private void criaProduto(double largura, double altura, double peso, String descricao) {
		this.listaProdutos.add(new Produto(this.listaProdutos.size() + 1, largura, altura, peso, descricao));
	}
}
