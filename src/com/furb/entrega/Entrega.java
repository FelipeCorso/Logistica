package com.furb.entrega;

import java.util.ArrayList;
import java.util.List;

import com.furb.pedido.Pedido;
import com.furb.transportadora.deposito.Deposito;
import com.furb.utils.LoadPedido;
import com.furb.veiculo.VeiculoAbstract;
import com.furb.veiculo.combustivel.EnCombustivel;
import com.furb.veiculo.motorista.Motorista;
import com.furb.veiculo.situacao.EnSituacao;
import com.furb.veiculo.tipo.Caminhao;
import com.furb.veiculo.tipo.Carreta;
import com.furb.veiculo.tipo.Carro;
import com.furb.veiculo.tipo.Moto;

public class Entrega {

	public static void efetuarEntrega(List<VeiculoAbstract> listaVeiculos) {
		for (VeiculoAbstract veiculo : listaVeiculos) {
			for (Pedido pedido : veiculo.getListaPedidos()) {
				StringBuffer sb = new StringBuffer();
				sb.append("Entregando Pedido: ");
				sb.append(pedido.getCodigo());
				sb.append(" do Veículo: ");
				sb.append(veiculo.getPlaca());

				System.out.println(sb.toString());
			}

		}
	}

	public static void main(String[] args) {

		Motorista motorista = new Motorista();
		Deposito deposito = new Deposito(4);
		List<VeiculoAbstract> listaVeiculos = new ArrayList<VeiculoAbstract>();

		VeiculoAbstract veiculo1 = new Moto(motorista, deposito, EnCombustivel.GASOLINA, EnSituacao.EM_CIRCULACAO);
		veiculo1.setPlaca("ABC-1234");
		listaVeiculos.add(veiculo1);

		VeiculoAbstract veiculo2 = new Carro(motorista, deposito, EnCombustivel.GASOLINA, EnSituacao.EM_CIRCULACAO);
		veiculo2.setPlaca("DEF-5678");
		listaVeiculos.add(veiculo2);

		VeiculoAbstract veiculo3 = new Caminhao(motorista, deposito, EnCombustivel.DIESEL, EnSituacao.EM_CIRCULACAO);
		veiculo3.setPlaca("GHI-9012");
		listaVeiculos.add(veiculo3);

		VeiculoAbstract veiculo4 = new Carreta(motorista, deposito, EnCombustivel.DIESEL, EnSituacao.EM_CIRCULACAO);
		veiculo4.setPlaca("JKL-3456");
		listaVeiculos.add(veiculo4);

		// *************************************

		// FIXME: TESTE
		for (VeiculoAbstract veiculo : listaVeiculos) {
			for (int i = 0; i < 4; i++) {
				Pedido pedido = new LoadPedido().criaPedido();
				pedido.setCodigo(i);
				veiculo.addPedido(pedido);
			}
		}

		Entrega.efetuarEntrega(listaVeiculos);
	}
}
