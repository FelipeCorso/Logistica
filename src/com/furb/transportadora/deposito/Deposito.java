package com.furb.transportadora.deposito;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Deposito {
	private Semaphore doca;

	public Deposito(int qtdDocas) {
		this.doca = new Semaphore(qtdDocas);
	}

	public boolean solicitaDoca() throws InterruptedException {
		return this.doca.tryAcquire(200, TimeUnit.MILLISECONDS);
	}

	public void liberaDoca() {
		this.doca.release();
	}
}
