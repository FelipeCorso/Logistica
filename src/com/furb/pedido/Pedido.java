package com.furb.pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.furb.endereco.Endereco;
import com.furb.pedido.pessoa.ClienteAbstract;
import com.furb.pedido.status.EnStatus;
import com.furb.produto.Produto;

public class Pedido extends Thread {
	private int codigo;
	private ClienteAbstract cliente;
	private Date dataEntrada;
	private Date dataEntrega;
	private Endereco enderecoPostoAtend;
	private Endereco enderecoEntrega;
	private List<Produto> listaProdutos;
	private double valorPedido;
	private EnStatus statusPedido;
	private double coordX;
	private double coordY;

	public Pedido() {

	}

	public Pedido(int codigo, ClienteAbstract cliente, Date dataEntrada, Date dataEntrega, Endereco enderecoPostoAtend, Endereco enderecoEntrega, List<Produto> listaProdutos, double valorPedido, double x, double y) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.dataEntrada = dataEntrada;
		this.dataEntrega = dataEntrega;
		this.enderecoPostoAtend = enderecoPostoAtend;
		this.enderecoEntrega = enderecoEntrega;
		this.listaProdutos = listaProdutos;
		this.valorPedido = valorPedido;
		this.coordX = x;
		this.coordY = y;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public double getCoordenadaX() {
		return this.coordX;
	}
	
	public double getCoordenadaY() {
		return this.coordY;
	}
	
	public void setCoordenadaX(double x) {
		this.coordX = x;
	}
	
	public void setCoordenadaY(double y) {
		this.coordY = y;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public ClienteAbstract getCliente() {
		return cliente;
	}

	public void setCliente(ClienteAbstract cliente) {
		this.cliente = cliente;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Endereco getEnderecoPostoAtend() {
		return enderecoPostoAtend;
	}

	public void setEnderecoPostoAtend(Endereco enderecoPostoAtend) {
		this.enderecoPostoAtend = enderecoPostoAtend;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public void addProduto(Produto produto) {
		if (this.getListaProdutos() == null) {
			this.setListaProdutos(new ArrayList<Produto>());
		}
		this.getListaProdutos().add(produto);
	}

	public double getValorPedido() {
		return valorPedido;
	}

	public synchronized void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}

	public synchronized void incrementarFrete(float valor) {
		this.valorPedido += valor;
	}

	public EnStatus getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(EnStatus statusPedido) {
		this.statusPedido = statusPedido;
	}

	// FIXME: REMOVER SE NÃO UTILIZAR
	public void calcularVlrPedido() {
		Thread thread = new Thread() {
			// SIMD
			@Override
			public void run() {
				for (Produto produto : getListaProdutos()) {
					setValorPedido(getValorPedido() + produto.getVlrUnitario());
				}
			}
		};
		thread.start();
	}

}
