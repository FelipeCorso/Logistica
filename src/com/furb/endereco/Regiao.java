package com.furb.endereco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.furb.pedido.Pedido;

public class Regiao {
	private Map<EnEstado, List<Pedido>> pedidosAEntregarMap;
	private int numeroPontosASeremVisitados;
	private int numeroParadas;
	private int numRoteirosDiarioVeiculo;
	private final String id;

	public void setNumeroPontosASeremVisitados(int numeroPontosASeremVisitados) {
		this.numeroPontosASeremVisitados = numeroPontosASeremVisitados;
	}

	public void setNumeroParadas(int numeroParadas) {
		this.numeroParadas = numeroParadas;
	}

	public Regiao(String id) {
		this.id = id;
		this.pedidosAEntregarMap = new HashMap<>();
	}

	public int AtualizarNumFrotaNecessaria(int numDiasUteis, int periodoAtendimento) {
		int nZonas = 0;
		int result = 0;

		nZonas = (int) Math.ceil((double) numeroPontosASeremVisitados / numeroParadas);
		result = (int) Math.ceil(nZonas / ((float) numRoteirosDiarioVeiculo * (float) numDiasUteis * ((float) periodoAtendimento / 7)));

		return result;
	}

	public void setNumRoteirosDiarioVeiculo(int numRoteirosDiarioVeiculo) {
		this.numRoteirosDiarioVeiculo = numRoteirosDiarioVeiculo;
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
