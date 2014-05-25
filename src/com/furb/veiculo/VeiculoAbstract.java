package com.furb.veiculo;

import java.util.ArrayList;
import java.util.List;

import com.furb.pedido.Pedido;
import com.furb.transportadora.deposito.Deposito;
import com.furb.veiculo.combustivel.EnCombustivel;
import com.furb.veiculo.motorista.Motorista;
import com.furb.veiculo.situacao.EnSituacao;

public abstract class VeiculoAbstract implements IVeiculo {

	private Motorista motorista;
	private Deposito deposito;
	private int codigo;
	private double consumo;
	private double capacidade;
	private double capacidadeM3;
	private EnCombustivel enCombustivel;
	private EnSituacao enSituacao;
	private List<Pedido> listaPedidos;
	private String placa;

	public VeiculoAbstract(Motorista motorista, Deposito deposito, EnCombustivel enCombustivel, EnSituacao enSituacao) {
		this.motorista = motorista;
		this.deposito = deposito;
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
	public Deposito getDeposito() {
		return this.deposito;
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

	@Override
	public List<Pedido> getListaPedidos() {
		if (this.listaPedidos == null) {
			this.listaPedidos = new ArrayList<Pedido>();
		}
		return listaPedidos;
	}

	@Override
	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	@Override
	public void addPedido(Pedido pedido) {
		this.getListaPedidos().add(pedido);
	}

	@Override
	public String getPlaca() {
		return placa;
	}

	@Override
	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
