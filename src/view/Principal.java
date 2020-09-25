package view;

import java.util.Random;
import java.util.concurrent.Semaphore;
import controller.BancoThread;

public class Principal {

	public static void main(String[] args) {

		int permissao = 1, permissao2 = 1, Conta, Tipo;
		double Saldo, Valor;
		Semaphore Sacar = new Semaphore(permissao);
		Semaphore Depositar = new Semaphore(permissao2);
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			Conta = random.nextInt(100);
			Tipo = random.nextInt(2) + 1;
			Saldo = (double) (Math.random() * 5001) + 1000;
			Valor = (double) (Math.random() * 5001) + 1000;

			Thread bank = new BancoThread(Conta, Tipo, Saldo, Valor, Sacar, Depositar);
			bank.start();
		}
	}
}