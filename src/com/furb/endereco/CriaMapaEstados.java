package com.furb.endereco;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

import com.furb.pedido.Pedido;

public class CriaMapaEstados {
	private Map<EnEstado, List<Pedido>> mapaEstados;
	private ReentrantLock lock;

	public CriaMapaEstados() {
		this.mapaEstados = new TreeMap<EnEstado, List<Pedido>>();
		this.lock = new ReentrantLock();
	}

	public void addPedido(Pedido pedido) {
		this.lock.lock();

		try {
			EnEstado estado = EnEstado.getObject(pedido.getEnderecoEntrega().getEstado().getUf());
			List<Pedido> listPedido = this.getMapaEstados().get(estado);

			if (listPedido == null) {
				listPedido = new ArrayList<Pedido>();
				listPedido.add(pedido);
				this.getMapaEstados().put(estado, listPedido);
			} else {
				listPedido.add(pedido);
			}
		} finally {
			this.lock.unlock();
		}
	}

	public Map<EnEstado, List<Pedido>> getMapaEstados() {
		return mapaEstados;
	}

}
