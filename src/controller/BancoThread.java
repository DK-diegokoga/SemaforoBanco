package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class BancoThread extends Thread {
	private int conta;
	private double saldo;
	private double saldoAtual;
	private double valor;
	private int tipo;
	private Semaphore saque;
	private Semaphore deposito;
	private String formato = "#,###.00";
	private DecimalFormat d = new DecimalFormat(formato);

	public BancoThread(int conta, int tipo, double saldo, double valor, Semaphore saque, Semaphore deposito) {
		this.conta = conta;
		this.tipo = tipo;
		this.saldo = saldo;
		this.valor = valor;
		this.saque = saque;
		this.deposito = deposito;

	}

	@Override
	public void run() {
		if (tipo == 1) {
			try {
				saque.acquire();
				Saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				saque.release();
			}
		} else {
			try {
				deposito.acquire();
				Deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				deposito.release();
			}
		}

	}

	private void Tempo() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void Saque() {
		if (valor > saldo) {
			Tempo();
			System.out.println("Conta de numero: " + conta + "# seu saldo atual R$ " + d.format(saldo)
					+ " Valor de saque requerido R$ " + d.format(valor));
			System.out.println("OPERAÇÃO NEGADA!! \n" + "Valor que foi requirido não é compativel com seu saldo. \n");
		} else {
			Tempo();
			System.out.println("Conta de numero: " + conta + "# seu saldo atual R$ " + d.format(saldo)
					+ " Valor de saque requerido R$ " + d.format(valor));
			saldoAtual = saldo - valor;
			System.out.println("Realizando saque...\n" + "Valor sacado R$ " + d.format(valor) + "\nSaldo atual R$ "
					+ d.format(saldoAtual) + "\n");
			
		}
	}

	public void Deposito() {
		System.out.println("Conta de numero: " + conta + "# seu saldo atual R$ " + d.format(saldo)
				+ " Valor de deposito R$ " + d.format(valor));
		saldo += valor;
		System.out.println("Efetuando o Deposito... \n" + "Saldo atual R$ " + d.format(saldo) + "\n");
		Tempo();
	}

}
