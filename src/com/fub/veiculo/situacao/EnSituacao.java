package com.fub.veiculo.situacao;

public enum EnSituacao {
	DISPONIVEL("Dispon�vel"), INDISPONIVEL("Indispon�vel"), EM_CIRCULACAO("Em Circula��o");

	private String descricao;

	EnSituacao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return this.descricao;
	}
}
