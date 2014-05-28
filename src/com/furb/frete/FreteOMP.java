package com.furb.frete;

import jomp.runtime.OMP;

import com.furb.pedido.Pedido;
import com.furb.produto.Produto;

public class FreteOMP {
	
	public double getValorFretePedido(Pedido pedido){
		int numThreads = pedido.getListaProdutos().size();
		int myId =0;
		int cont = 0;		
		System.out.println("Quantidade de itens");		
		OMP.setNumThreads(numThreads);
		//omp parallel private(myId) reduction(+:cont)
		{
			myId = OMP.getThreadNum();
			// metodo calcula valor do frete
			cont += getFreteCalculado(pedido.getListaProdutos().get(myId));
			System.out.println("Thread " + myId + " total produto= " + cont);
		}		
		System.out.println("Valor final de pedido = " + cont);
		pedido.setValorPedido(cont);
		return cont;
	}
	
	/**
	 * metodo para calcular e retornar o valor do frete.
	 * @param produto
	 * @return
	 */
	public double getFreteCalculado(Produto produto){		
		return (produto.getAltura() * produto.getLargura()) + (produto.getPeso() * 0.05);
	}
	
}
