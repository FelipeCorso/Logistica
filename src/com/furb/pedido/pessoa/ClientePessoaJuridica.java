package com.furb.pedido.pessoa;

import com.furb.endereco.Endereco;

public class ClientePessoaJuridica extends ClienteAbstract {

	public ClientePessoaJuridica(int codigo, String nome, String razaoSocial, int cpf, int cnpj, Endereco endereco) {
		super(codigo, nome, razaoSocial, cpf, cnpj, endereco);
	}

}
