package com.fub.veiculo.situacao;

public enum EnSituacao {
	DISPONIVEL("Disponível"), INDISPONIVEL("Indisponível"), EM_CIRCULACAO("Em Circulação");

	private String descricao;

	EnSituacao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return this.descricao;
	}
}
