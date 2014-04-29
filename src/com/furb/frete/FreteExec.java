package com.furb.frete;

import com.fub.pedido.Pedido;
import com.fub.produto.Produto;

public class FreteExec {
	public void chamaFrete(Pedido pedido){
		CalculoFrete calculoFrete;
		for (Produto produto : pedido.getListaProdutos()) {
			calculoFrete = new CalculoFrete(produto, pedido);
			calculoFrete.start();
		}
		 System.out.println("Valor final do pedido: "+pedido.getValorPedido());
		
		
	}
	public static void main(String[] args) {
		FreteExec freteExec = new FreteExec();
		
		
		//freteExec.chamaFrete(pedido);
	}
}
