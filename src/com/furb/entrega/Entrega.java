package com.furb.entrega;

import java.util.List;

import jomp.runtime.OMP;

import com.furb.pedido.Pedido;
import com.furb.produto.Produto;
import com.furb.veiculo.Veiculo;

public class Entrega {

	public static void efetuarEntrega(List listaVeiculos) {
		System.out.println(listaVeiculos.size());
		OMP.setNumThreads(listaVeiculos.size());
		int inx;
		
		int qtdEntregasEfetuadas = 0;
		//omp parallel reduction(+:qtdEntregasEfetuadas)
		{
			//omp for ordered
			for (inx = 0; inx < listaVeiculos.size(); inx++) {

				int idThread = OMP.getThreadNum();
				Veiculo veiculo = (Veiculo) listaVeiculos.get(idThread);
				System.out.println("ID THREAD: " + idThread);
				System.out.println("REALIZANDO ENTREGA DOS PEDIDO DO VEÍCULO: " + veiculo.getPlaca());
				for (int index = 0; index < veiculo.getListaPedidos().size(); index++) {

					Pedido pedido = veiculo.getListaPedidos().get(index);

					System.out.println("PEDIDO: " + pedido.getCodigo());
					for (int i = 0; i < pedido.getListaProdutos().size(); i++) {
						Produto produto = pedido.getListaProdutos().get(i);
						System.out.println("EFETUANDO ENTREGA");
						System.out.println("PRODUTO: " + produto.getCodigo() + " - " + produto.getDescricao());
						System.out.println("ENTREGA EFETUADA");
						qtdEntregasEfetuadas++;
					}
				}
				System.out.println("QUANTIDADE DE ENTREGAS EFETUADAS: " + qtdEntregasEfetuadas);
			}
		}
	}

}
