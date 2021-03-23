package controller;

import java.util.concurrent.Semaphore;

public class threadTransacao extends Thread{
	
	private int id;
	private Semaphore semaforo;
	private Semaphore semaforoSaque;
	private Semaphore semaforoDeposito;
	private int valorConta;
	private int valorTransacao;
	private String tipo;
	
	public threadTransacao (int id, Semaphore semaforo, Semaphore semaforoSaque, Semaphore semaforoDeposito, int  valorConta, int valorTransacao, String tipo) {
		this.id = id;
		this.semaforo = semaforo;
		this.semaforoSaque  = semaforoSaque;
		this.semaforoDeposito = semaforoDeposito;
		this.valorConta = valorConta;
		this.valorTransacao = valorTransacao;
		this.tipo = tipo;
	}
	
	@Override
	public void run(){
		try {
			semaforo.acquire();
			if(tipo == "deposito"){
				try {
				   semaforoDeposito.acquire();
				   metodoTransacao(this.tipo);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
				  semaforoDeposito.release();
				}
			} else if(tipo == "saque"){
				try { 
				   semaforoSaque.acquire();
				   metodoTransacao(this.tipo);
			    } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					semaforoSaque.release();
				}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
		
	}
	
	public void metodoTransacao(String tipo){
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tipo == "deposito"){
			 System.out.println("O "+id+"º cliente fez um deposito de "+valorTransacao);
			 valorConta = valorConta + valorTransacao;
			 System.out.println("O valor na conta do "+id+"º cliente atual é "+ valorConta);
			
		}else if(tipo == "saque"){
			System.out.println("O "+id+"º cliente fez um saque de "+valorTransacao);
			 valorConta = valorConta - valorTransacao;
			 System.out.println("O valor na conta do "+id+"º cliente atual é "+ valorConta);	
		}
		
		
	}

}
