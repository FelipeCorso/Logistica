package com.furb.pedido.pessoa;

import com.furb.endereco.Endereco;

public abstract class ClienteAbstract {
	private int codigo;
	private String nome;
	private String razaoSocial;
	private int cpf;
	private int cnpj;
	private Endereco endereco;

	public ClienteAbstract(int codigo, String nome, String razaoSocial, int cpf, int cnpj, Endereco endereco) {
		this.codigo = codigo;
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.endereco = endereco;
	}

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

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
