package com.furb.utils;

import java.util.Date;

import com.furb.endereco.EnEstado;
import com.furb.endereco.Endereco;
import com.furb.pedido.Pedido;
import com.furb.pedido.pessoa.ClientePessoaFisica;

public class LoadPedido {
	public Pedido criaPedido() {
		Endereco endereco = new Endereco(0, "Logradouro teste", 7987987, "nr 25", "Ponta Aguda", "Blumenau", EnEstado.SC, "Brasil");
		Endereco endereco1 = new Endereco(1, "Logradouro teste1", 243332, "nr 455", "Velha", "Blumenau",EnEstado.SC, "Brasil");
		Endereco endereco2 = new Endereco(2, "Logradouro teste2", 5432432, "nr 1000", "Vila Nova", "Blumenau", EnEstado.SC, "Brasil");
		ClientePessoaFisica cliente = new ClientePessoaFisica(0, "Jo�o da Silva", "Casado", 987654329, 76987687, endereco);

		return new Pedido(0, cliente, new Date(), new Date(), endereco1, endereco2, LoadProdutos.getInstance().getListaProdutos(), 0, 10, 15);
	}
}
