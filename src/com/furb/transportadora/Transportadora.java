package com.furb.transportadora;

import java.util.ArrayList;
import java.util.List;

import com.furb.endereco.CalculoRegiao;
import com.furb.endereco.Regiao;

public class Transportadora {
	private final static int TotalDiasSemana = 6;
	private final static int PeriodoAtendimentoClientes = 2;
	private int numeroFrota;
	private List<Regiao> regioes;
	private boolean alterandoFrota = false;

	public Transportadora(ArrayList<Regiao> regioes) {
		this.regioes = regioes;
	}

	public int getTotalDiasSemana() {
		return TotalDiasSemana;
	}

	public int getPeriodoatendimentoclientes() {
		return PeriodoAtendimentoClientes;
	}

	public synchronized void AdicinarFrota(int frotaParcial) throws InterruptedException {

		if (alterandoFrota) {
			wait();
		}

		alterandoFrota = true;

		this.numeroFrota += frotaParcial;

		alterandoFrota = false;

		notify();
	}

	public void CalcularFrotaNecessaria() throws InterruptedException {
		List<CalculoRegiao> listaThreads = new ArrayList<CalculoRegiao>();
		CalculoRegiao calcRegiao;
		for (Regiao regiao : regioes) {
			calcRegiao = new CalculoRegiao(regiao, this);
			listaThreads.add(calcRegiao);
			calcRegiao.start();
		}

		for (CalculoRegiao calculoRegiao : listaThreads) {
			calculoRegiao.join();
		}

		System.out.println("Frota necessária: " + this.numeroFrota);
	}
}
