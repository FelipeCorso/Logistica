package com.furb.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.furb.endereco.Regiao;
import com.furb.pedido.Pedido;
import com.furb.produto.Produto;

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
		List<Produto>listProduto;
		Pedido temp;
		Random random = new Random();
		for (int i = 0; i < pedidos.length; i++) {
			listProduto = new ArrayList<>();
			temp = new Pedido();
			temp.setCodigo(i);
			temp.setCoordenadaX(random.nextInt(25));
			temp.setCoordenadaY(i);
			
			//adiciona os produtos sequencialmente na lista de produtos do pedido.
			for(int j = 0; j < 3; j++){
				listProduto.add(LoadProdutos.getInstance().getProdutoSequencial(j));
			}
			temp.setListaProdutos(listProduto);
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
