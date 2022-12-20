package edu.umb.cs681;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount {
	private double balance = 0;
	private ReentrantLock renlock = new ReentrantLock();
	private Condition sufficientFunds = renlock.newCondition();
	private Condition belowUpperLimitFunds = renlock.newCondition();

	public ThreadSafeBankAccount() {
	}
	
	public void deposit(double amount) {
		renlock.lock();
		try {
			System.out.println(Thread.currentThread().getId() + " Current balance: " + balance);
			while (balance >= 400) {

				
				belowUpperLimitFunds.await();

			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + 
					" Deposit: new balance: " + balance);
			sufficientFunds.signalAll();
			Thread.sleep(100);
		} catch (InterruptedException exception) {
			System.out.println(exception);
		} finally {
			renlock.unlock();
			
		}
	}
	
	public void withdraw(double amount) {
		renlock.lock();
		try {
			System.out.println(Thread.currentThread().getId() + " current balance: " + balance);

			while (balance <= 0) {
				
				sufficientFunds.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + 
					" Withdraw: New balance: " + balance);
			belowUpperLimitFunds.signalAll();
			Thread.sleep(1000);
		} catch (InterruptedException exception) {
			System.out.println(exception);
		} finally {
			renlock.unlock();
			
		}
	}
	public static void main(String[] args) {
		ThreadSafeBankAccount bA = new ThreadSafeBankAccount();
		DepositRunnable depositRunnable= new DepositRunnable(bA);
		WithdrawRunnable withdrawRunnable = new WithdrawRunnable(bA);
		Thread[] depositethreads = new Thread[5];
		Thread[] withdrawthreads = new Thread[5];
		for (int i = 0; i < 5; i++) {
			Thread depositThread = new Thread(depositRunnable);
			depositThread.start();
			depositethreads[i] = depositThread;
			Thread withdrawThread = new Thread(withdrawRunnable);
			withdrawThread.start();
			withdrawthreads[i] = withdrawThread;
		}
		try {
			Thread.sleep(100);
		} catch (Exception e) {

		}
		depositRunnable.setDone();
		withdrawRunnable.setDone();
		for (int i = 0; i < 5; i++) {
			depositethreads[i].interrupt();
			withdrawthreads[i].interrupt();
			try {
				depositethreads[i].join();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
	
}
