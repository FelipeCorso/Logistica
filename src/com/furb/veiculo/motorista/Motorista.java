package com.furb.veiculo.motorista;

import java.io.Serializable;

public class Motorista implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7023933202231482985L;
	private int codigo;
	private String nome;
	private String categorias;
	private String rg;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return this.getNome();
	}
}
