package com.furb.frete;

import java.util.ArrayList;
import java.util.List;

import com.furb.pedido.Pedido;
import com.furb.produto.Produto;
import com.furb.utils.LoadPedido;

public class FreteExec {
	public void chamaFrete(Pedido pedido) throws InterruptedException{
		List<CalculoFrete> listaThreads = new ArrayList<CalculoFrete>();
		CalculoFrete calculoFrete;
		for (Produto produto : pedido.getListaProdutos()) {
			calculoFrete = new CalculoFrete(produto, pedido);
			listaThreads.add(calculoFrete);
			calculoFrete.start();
		}		
		

		for (CalculoFrete calculoFreteI : listaThreads) {
			calculoFreteI.join();
		}
		 System.out.println("Valor final do pedido: "+pedido.getValorPedido());
		
		
	}
	public static void main(String[] args) throws InterruptedException {
		FreteExec freteExec = new FreteExec();
		LoadPedido loadProduto  = new LoadPedido();
		Pedido pedido  = new Pedido();
		pedido = loadProduto.criaPedido();
		//teste
		freteExec.chamaFrete(pedido);
	}
}
