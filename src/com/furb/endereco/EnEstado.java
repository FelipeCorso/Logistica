package com.furb.endereco;

public enum EnEstado {
	SC("Santa Catarina", "SC", "F");

	private final String nome;
	private final String uf;
	private String regiao;

	/**
	 * 
	 * @param nome
	 *            Nome do estado por extenso
	 * @param uf
	 *            Unidade Federativa
	 * @param regiao
	 *            Região a qual o estado pertence
	 */
	EnEstado(String nome, String uf, String regiao) {
		this.nome = nome;
		this.uf = uf;
		this.regiao = regiao;
	}

	public String getUf() {
		return uf;
	}

	public String getRegiao() {
		return regiao;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	public static EnEstado getObject(String uf) {
		for (EnEstado estado : EnEstado.values()) {
			if (estado.getUf().equalsIgnoreCase(uf)) {
				return estado;
			}
		}
		return null;
	}
}
