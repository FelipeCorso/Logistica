package com.furb.veiculo.tipo;

public enum EnTipoVeiculo {
	CAMINHAO("Caminhão"), CARRETA("Carreta"), CARRO("Carro"), MOTO("Moto");

	private String descricao;

	private EnTipoVeiculo(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return this.descricao;
	}
}
