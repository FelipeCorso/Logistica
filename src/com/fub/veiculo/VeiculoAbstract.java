package com.fub.veiculo;

import com.fub.veiculo.combustivel.EnCombustivel;
import com.fub.veiculo.motorista.Motorista;
import com.fub.veiculo.situacao.EnSituacao;

public abstract class VeiculoAbstract implements IVeiculo {

	private Motorista motorista;
	private int codigo;
	private EnCombustivel enCombustivel;
	private double consumo;
	private double capacidade;
	private double capacidadeM3;
	private EnSituacao enSituacao;

	public VeiculoAbstract(Motorista motorista, EnCombustivel enCombustivel, EnSituacao enSituacao) {
		this.motorista = motorista;
		this.enCombustivel = enCombustivel;
		this.enSituacao = enSituacao;
	}

	@Override
	public Motorista getMotorista() {
		return this.motorista;
	}

	@Override
	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	@Override
	public int getCodigo() {
		return this.codigo;
	}

	@Override
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public EnCombustivel getEnCombustivel() {
		return this.enCombustivel;
	}

	@Override
	public void setEnCombustivel(EnCombustivel enCombustivel) {
		this.enCombustivel = enCombustivel;
	}

	@Override
	public double getConsumo() {
		return this.consumo;
	}

	@Override
	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	@Override
	public double getCapacidadeKg() {
		return this.capacidade;
	}

	@Override
	public void setCapacidadeKg(double capacidade) {
		this.capacidade = capacidade;
	}

	@Override
	public double getCapacidadeM3() {
		return this.capacidadeM3;
	}

	@Override
	public void setCapacidadeM3(double capacidadeM3) {
		this.capacidadeM3 = capacidadeM3;
	}

	@Override
	public EnSituacao getEnSituacao() {
		return this.enSituacao;
	}

	@Override
	public void setEnSituacao(EnSituacao enSituacao) {
		this.enSituacao = enSituacao;
	}

}
