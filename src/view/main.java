package view;

import java.util.concurrent.Semaphore;

import controller.threadTransacao ;


public class main {

	public static void main(String[] args) {
		String tipo;
		Semaphore semaforo = new Semaphore(2);
		Semaphore semaforoDeposito = new Semaphore(1);
		Semaphore semaforoSaque = new Semaphore(1);
		for(int i = 1; i<21; i++) {
			int transacao = (int)((Math.random()*99)+1);
			if(transacao%2 == 0) {
				tipo = "deposito";
			}else {
				tipo = "saque";
			}
			int valorConta = i*10;
			int valorTransacao = valorConta/2;
			threadTransacao  threadTransacao  = new threadTransacao (i,semaforo, semaforoSaque, semaforoDeposito, valorConta, valorTransacao, tipo);
			threadTransacao.start();
		}

	}

}
