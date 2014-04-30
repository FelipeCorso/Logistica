package com.furb.endereco;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.furb.pedido.Pedido;

public class CriaRegioes {
	private List<Regiao> listaRegioes;
	private ReentrantLock lock;

	public CriaRegioes() {
		this.lock = new ReentrantLock();
	}

	public void criarRegioes(Map<EnEstado, List<Pedido>> mapaEstados) {
		this.lock.lock();

		try {
			for (Iterator<EnEstado> iterator = mapaEstados.keySet().iterator(); iterator.hasNext();) {
				EnEstado estado = iterator.next();
				int indexRegiao = this.getListaRegioes().indexOf(estado);
				Regiao regiao = this.getListaRegioes().get(indexRegiao);
				if (regiao == null) {
					regiao = new Regiao(estado.getRegiao());
				}
				regiao.addPedidosAEntregar(estado, mapaEstados.get(estado));
				this.getListaRegioes().add(regiao);
			}
		} finally {
			this.lock.unlock();
		}
	}

	public List<Regiao> getListaRegioes() {
		return listaRegioes;
	}

}
