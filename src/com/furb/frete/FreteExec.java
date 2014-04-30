package com.furb.frete;

import com.furb.pedido.Pedido;
import com.furb.produto.Produto;
import com.furb.utils.LoadPedido;

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
		LoadPedido loadProduto  = new LoadPedido();
		Pedido pedido  = new Pedido();
		pedido = loadProduto.criaPedido();
		//teste
		freteExec.chamaFrete(pedido);
	}
}
