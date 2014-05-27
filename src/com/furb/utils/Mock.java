package com.furb.utils;

import java.util.Random;

import com.furb.endereco.Regiao;
import com.furb.pedido.Pedido;

public class Mock {
	private Regiao[] regioes;
	
	public Regiao[] GetRegioes(){
		if(this.regioes != null){
			return this.regioes;
		}
		else
		{
			return GetRegiaoMock();
		}
	}
	
	public void SetRegioes(Regiao[] regioes){
		this.regioes = regioes;
	}
	
	public static Pedido[] GetPedidos(int nPedidos){
		Pedido[] pedidos = new Pedido[nPedidos];

		Pedido temp;
		Random random = new Random();
		for (int i = 0; i < pedidos.length; i++) {
			temp = new Pedido();
			temp.setCodigo(i);
			temp.setCoordenadaX(random.nextInt(25));
			temp.setCoordenadaY(i);
			pedidos[i] = temp;
		}
		
		return pedidos;
	}
	
	private Regiao[] GetRegiaoMock(){
		Regiao[] regioes = new Regiao[10];
		Regiao tempReg;

		for (int i = 0; i < regioes.length; i++) {
			tempReg = new Regiao("Região " + i);
			tempReg.setNumeroParadas(i);
			tempReg.setNumeroPontosASeremVisitados(i * 30);
			tempReg.setNumRoteirosDiarioVeiculo(2);
			regioes[i] = tempReg;
		}
		
		return regioes;
	}	
}
