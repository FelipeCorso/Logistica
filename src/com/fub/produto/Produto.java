package com.fub.produto;

public class Produto {
	private int codigo;
	private double largura;
	private double altura;
	private double peso;
	private String descricao;

	public Produto(int codigo, double largura, double altura, double peso, String descricao) {
		this.codigo = codigo;
		this.largura = largura;
		this.altura = altura;
		this.peso = peso;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
