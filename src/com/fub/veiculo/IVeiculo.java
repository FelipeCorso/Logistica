package com.fub.veiculo;

import com.fub.veiculo.combustivel.EnCombustivel;
import com.fub.veiculo.motorista.Motorista;
import com.fub.veiculo.situacao.EnSituacao;

public interface IVeiculo {
	public Motorista getMotorista();

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
