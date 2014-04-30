package com.furb.endereco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.furb.pedido.Pedido;

public class Regiao {
	private int codigo;
	private List<Pedido> pedidosEntregues;
	private Map<EnEstado, List<Pedido>> pedidosAEntregarMap;
	private int numeroPontosASeremVisitados;
	private int numeroParadas;
	private int numRoteirosDiarioVeiculo;
	private final String id;

	public Regiao(String id) {
		this.id = id;
		this.pedidosAEntregarMap = new HashMap<>();
		this.pedidosEntregues = new ArrayList<Pedido>();
	}

	public int AtualizarNumFrotaNecessaria(int numDiasUteis, int periodoAtendimento) {
		int nZonas = 0;
		int result = 0;

		nZonas = numeroPontosASeremVisitados / numeroParadas;

		result = nZonas / (numRoteirosDiarioVeiculo * numDiasUteis * (periodoAtendimento / 7));

		return result;
	}

	public void addPedidosAEntregar(EnEstado estado, List<Pedido> listaPedidosParam) {
		List<Pedido> listaPedidos = this.getPedidosAEntregarMap().get(estado);

		if (listaPedidos == null) {
			this.getPedidosAEntregarMap().put(estado, listaPedidosParam);
		} else {
			listaPedidos.addAll(listaPedidosParam);
		}

		this.getPedidosAEntregarMap().putAll(pedidosAEntregarMap);
	}

	public Map<EnEstado, List<Pedido>> getPedidosAEntregarMap() {
		return pedidosAEntregarMap;
	}

	public String getId() {
		return id;
	}

}
