package com.furb.pedido;

public class PedidoUtil {
	public static Pedido findPedido(int cod, Pedido[] pl) {

		for (Pedido p : pl) {
			if (p.getCodigo() == cod)
				return p;
		}

		return null;
	}
}
