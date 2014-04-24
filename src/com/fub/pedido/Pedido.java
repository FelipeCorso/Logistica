package com.fub.pedido;

import java.util.List;

import com.fub.endereco.Endereco;
import com.fub.pedido.pessoa.PessoaAbstract;
import com.fub.produto.Produto;

public class Pedido {
	private int codigo;
	private PessoaAbstract pessoa;
	private Endereco enderecoEntrega;
	private List<Produto> listaProdutos;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public PessoaAbstract getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaAbstract pessoa) {
		this.pessoa = pessoa;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

}
