package com.furb.veiculo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.furb.pedido.Pedido;
import com.furb.transportadora.deposito.Deposito;
import com.furb.veiculo.combustivel.EnCombustivel;
import com.furb.veiculo.motorista.Motorista;
import com.furb.veiculo.situacao.EnSituacao;
import com.furb.veiculo.tipo.EnTipoVeiculo;

public class Veiculo implements IVeiculo, Serializable {

	private static final long serialVersionUID = 6886129015866866033L;
	private Motorista motorista;
	private Deposito deposito;
	private int codigo;
	private double consumo;
	private double capacidade;
	private double capacidadeM3;
	private EnTipoVeiculo enTipoVeiculo;
	private EnCombustivel enCombustivel;
	private EnSituacao enSituacao;
	private List<Pedido> listaPedidos;
	private String placa;

	public Veiculo(Motorista motorista, Deposito deposito, EnTipoVeiculo enTipoVeiculo, EnCombustivel enCombustivel, EnSituacao enSituacao) {
		this.motorista = motorista;
		this.deposito = deposito;
		this.enTipoVeiculo = enTipoVeiculo;
		this.enCombustivel = enCombustivel;
		this.enSituacao = enSituacao;
	}

	public Veiculo() {
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

	/**
	 * Utilizar o método <code>getPlaca()</code>
	 */
	@Deprecated
	@Override
	public int getCodigo() {
		return this.codigo;
	}

	@Override
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public EnTipoVeiculo getEnTipoVeiculo() {
		return enTipoVeiculo;
	}

	public void setEnTipoVeiculo(EnTipoVeiculo enTipoVeiculo) {
		this.enTipoVeiculo = enTipoVeiculo;
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
