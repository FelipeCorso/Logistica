package com.fub.veiculo.tipo;

import com.fub.veiculo.VeiculoAbstract;
import com.fub.veiculo.combustivel.EnCombustivel;
import com.fub.veiculo.motorista.Motorista;
import com.fub.veiculo.situacao.EnSituacao;

public class Moto extends VeiculoAbstract {

	public Moto(Motorista motorista, EnCombustivel enCombustivel, EnSituacao enSituacao) {
		super(motorista, enCombustivel, enSituacao);
	}

}
