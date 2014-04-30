package com.furb.frete;

import com.furb.pedido.Pedido;
import com.furb.produto.Produto;

public class CalculoFrete extends Thread {
	
	Produto produto;
	Pedido pedido;
	
	public CalculoFrete(Produto produto, Pedido pedido){
		this.produto = produto;
		this.pedido = pedido;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		float valorFrete = 0;
		valorFrete  = (float) ((produto.getAltura() * produto.getLargura()) * 0.001);
		
		this.pedido.incrementarFrete(valorFrete);
		
		super.run();
	}
}
