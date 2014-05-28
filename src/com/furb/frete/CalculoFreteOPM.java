package com.furb.frete;

import jomp.runtime.OMP;

import com.furb.pedido.Pedido;
import com.furb.produto.Produto;
import com.furb.utils.LoadPedido;

public class CalculoFreteOPM {
	
	public static void main(String[] args) {
		Pedido pedido = new Pedido();
		// TODO Auto-generated method stub
		CalculoFreteOPM calculoFreteOPM = new CalculoFreteOPM();
		//calculoFreteOPM.calculaValorFrete();
		LoadPedido loadPedido = new LoadPedido();
		
		pedido = loadPedido.criaPedido();
		calculoFreteOPM.calculaValorFrete(pedido);
		System.out.println("Valor final de pedido:"+ pedido.getValorPedido());
		
	}
//	public void calculaValorFrete(){
//		int numThreads = 15;
//		int myId =0;
//		int n = 10;
//		int cont = 0;		
//		OMP.setNumThreads(numThreads);
//		
//		//omp parallel private(myId) reduction(+:cont)
//		{
//			myId = OMP.getThreadNum();
//			
//			for (int i = 0; i < n; i++) {
//				cont += myId * i;
//			}
//			
//			System.out.println("Thread " + myId + " total produto= " + cont);
//		}
//		
//		System.out.println("Valor final de pedido = " + cont);
//		
//	}
	
	public void calculaValorFrete(Pedido pedido){
		int numThreads = pedido.getListaProdutos().size();
		int myId =0;
		int cont = 0;		
		
		
		OMP.setNumThreads(numThreads);
		
		//omp parallel private(myId) reduction(+:cont)
		{
			myId = OMP.getThreadNum();
			// metodo calcula valor do frete
			cont += getFreteCalculado(pedido.getListaProdutos().get(myId));
						
			System.out.println("Thread " + myId + " total produto= " + cont);
		}
		
		System.out.println("Valor final de cont pedido = " + cont);
		pedido.setValorPedido(cont);
		
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
