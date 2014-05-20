package com.furb.utils;

import com.furb.endereco.Regiao;

public class Mock {
	public static Regiao[] GetRegioes(int nRegioes){
		Regiao[] regioes = new Regiao[nRegioes];
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
