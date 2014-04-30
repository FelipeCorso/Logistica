package com.furb.veiculo;

import com.furb.transportadora.deposito.Deposito;
import com.furb.veiculo.combustivel.EnCombustivel;
import com.furb.veiculo.motorista.Motorista;
import com.furb.veiculo.situacao.EnSituacao;

public interface IVeiculo {
	public Motorista getMotorista();

	public Deposito getDeposito();

	public void setMotorista(Motorista motorista);

	public int getCodigo();

	public void setCodigo(int codigo);

	public EnCombustivel getEnCombustivel();

	public void setEnCombustivel(EnCombustivel enCombustivel);

	public double getConsumo();

	public void setConsumo(double consumo);

	public double getCapacidadeKg();

	public void setCapacidadeKg(double capacidade);

	public double getCapacidadeM3();

	public void setCapacidadeM3(double capacidadeM3);

	public EnSituacao getEnSituacao();

	public void setEnSituacao(EnSituacao enSituacao);

}
