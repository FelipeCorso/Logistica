package com.furb.veiculo.tipo;

import com.furb.transportadora.deposito.Deposito;
import com.furb.veiculo.VeiculoAbstract;
import com.furb.veiculo.combustivel.EnCombustivel;
import com.furb.veiculo.motorista.Motorista;
import com.furb.veiculo.situacao.EnSituacao;

public class Moto extends VeiculoAbstract {

	public Moto(Motorista motorista, Deposito deposito, EnCombustivel enCombustivel, EnSituacao enSituacao) {
		super(motorista, deposito, enCombustivel, enSituacao);
	}

}