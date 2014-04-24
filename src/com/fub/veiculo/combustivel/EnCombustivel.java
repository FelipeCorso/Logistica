package com.fub.veiculo.combustivel;

public enum EnCombustivel {
	DIESEL("Diesel", 0.0), GASOLINA("Gasolina", 0.0), ETANOL("Etanol", 0.0), GNV("Gás Natural Veicular", 0.0);

	private String descricao;
	private double preco;

	EnCombustivel(String descricao, double preco) {
		this.descricao = descricao;
		this.preco = preco;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return this.descricao;
	}

}
