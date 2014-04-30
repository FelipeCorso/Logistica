package com.furb.pedido.pessoa;

import com.furb.endereco.Endereco;

public class ClientePessoaFisica extends ClienteAbstract {

	public ClientePessoaFisica(int codigo, String nome, String razaoSocial, int cpf, int cnpj, Endereco endereco) {
		super(codigo, nome, razaoSocial, cpf, cnpj, endereco);
	}

}
