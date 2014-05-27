package com.furb.endereco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.furb.pedido.Pedido;

public class Regiao {
	private Map<EnEstado, List<Pedido>> pedidosAEntregarMap;
	private List<Pedido> pedidos;
	private int numeroPontosASeremVisitados;
	private int numeroParadas = 2;
	private int numRoteirosDiarioVeiculo = 1;
	private final String id;

	public void setNumeroPontosASeremVisitados(int numeroPontosASeremVisitados) {
		this.numeroPontosASeremVisitados = numeroPontosASeremVisitados;
	}

	public void setNumeroParadas(int numeroParadas) {
		this.numeroParadas = numeroParadas;
	}
	
	public Regiao(String id) {
		this.id = id;
		this.pedidosAEntregarMap = new HashMap<EnEstado, List<Pedido>>();
		this.pedidos = new ArrayList<Pedido>();
	}
	
	public Regiao() {
		this.id = "";
		this.pedidosAEntregarMap = new HashMap<EnEstado, List<Pedido>>();
		this.pedidos = new ArrayList<Pedido>();
	}
	
	public void addPedido(Pedido pedido){
		this.pedidos.add(pedido);
	}

	public List<Pedido> getPedidos(){
		return this.pedidos;
	}

	public int CalcularNumFrotaNecessaria(int numDiasUteis, int periodoAtendimento) {
		int nZonas = 0;
		int result = 0;
			
		if(pedidos != null){
			numeroPontosASeremVisitados = pedidos.size();
		}

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
