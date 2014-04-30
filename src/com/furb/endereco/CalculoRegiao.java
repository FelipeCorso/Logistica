package com.furb.endereco;

import com.furb.transportadora.Transportadora;

public class CalculoRegiao extends Thread {
	Regiao regiao;
	Transportadora transportadora;

	public CalculoRegiao(Regiao regiao, Transportadora transportadora) {
		this.regiao = regiao;
		this.transportadora = transportadora;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int qtdFrotaParcial = 0;
		qtdFrotaParcial = regiao.AtualizarNumFrotaNecessaria(transportadora.getTotalDiasSemana(), transportadora.getPeriodoatendimentoclientes());

		try {
			this.transportadora.AdicinarFrota(qtdFrotaParcial);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.run();
	}
}
