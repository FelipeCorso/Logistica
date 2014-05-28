package com.furb.frete;

import com.furb.pedido.Pedido;
import com.furb.utils.Mock;

public class CalcularFrete {
	Pedido vetPedido[];
	FreteOMP freteOMP;
	private static Mock mock;
	public void execCalcularFrete(int opcao){
		System.out.println("numero de pedidos: "+opcao);
		freteOMP = new FreteOMP();
		vetPedido = mock.GetPedidos(opcao);
		for (int i = 0; i < opcao; i++) {
			vetPedido[i].setValorPedido(freteOMP.getValorFretePedido(vetPedido[i]));
		}
	}
	
}
