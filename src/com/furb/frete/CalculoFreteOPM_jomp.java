package com.furb.frete;

import jomp.runtime.OMP;

import com.furb.pedido.Pedido;
import com.furb.produto.Produto;
import com.furb.utils.LoadPedido;

public class CalculoFreteOPM_jomp {

	
	public static void main(String[] args) {
		Pedido pedido = new Pedido();
		// TODO Auto-generated method stub
		CalculoFreteOPM_jomp calculoFreteOPM = new CalculoFreteOPM_jomp();
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

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.numThreads = numThreads;
  __omp_Object0.pedido = pedido;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  cont  += __omp_Object0._rd_cont;
  // shared variables
  numThreads = __omp_Object0.numThreads;
  pedido = __omp_Object0.pedido;
}
// OMP PARALLEL BLOCK ENDS

		
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

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  int numThreads;
  Pedido pedido;
  // firstprivate variables
  // variables to hold results of reduction
  int _rd_cont;

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  int myId;
  // reduction variables, init to default
  int cont = 0;
    // OMP USER CODE BEGINS

		{
			myId = OMP.getThreadNum();
			// metodo calcula valor do frete
			cont += getFreteCalculado(pedido.getListaProdutos().get(myId));
						
			System.out.println("Thread " + myId + " total produto= " + cont);
		}
    // OMP USER CODE ENDS
  // call reducer
  cont = (int) jomp.runtime.OMP.doPlusReduce(__omp_me, cont);
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
    _rd_cont = cont;
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}

