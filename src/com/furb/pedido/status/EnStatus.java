package com.furb.pedido.status;

public enum EnStatus {
	ENTREGUE("Entregue"), A_ENTREGAR("À entregar");
	private String status;

	EnStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return status;
	}
}
