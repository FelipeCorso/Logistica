package com.furb.produto;

import java.io.Serializable;

public class Produto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6015399968247359491L;
	private int codigo;
	private double largura;
	private double altura;
	private double peso;
	private double vlrUnitario;
	private String descricao;

	public Produto(int codigo, double largura, double altura, double peso, double vlrUnitario, String descricao) {
		this.codigo = codigo;
		this.largura = largura;
		this.altura = altura;
		this.peso = peso;
		this.vlrUnitario = vlrUnitario;
		this.descricao = descricao;
	}

	public Produto() {
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

	public double getVlrUnitario() {
		return vlrUnitario;
	}

	public void setVlrUnitario(double vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
