package com.furb.frete;

import com.furb.pedido.Pedido;
import com.furb.utils.Mock;

public class CalcularFrete {
	Pedido vetPedido[];
	FreteOMP_jomp freteOMP;
	private static Mock mock;
	
	public void execCalcularFrete(int pedido, int produto){
		System.out.println("Quantidade de Pedidos: "+pedido);
		freteOMP = new FreteOMP_jomp();
		vetPedido = mock.GetPedidosProdutos(pedido,produto);
		for (int i = 0; i < pedido; i++) {
			vetPedido[i].setValorPedido(freteOMP.getValorFretePedido(vetPedido[i]));
		}
	}
	
}
