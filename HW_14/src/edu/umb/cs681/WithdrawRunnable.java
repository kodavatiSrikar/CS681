package edu.umb.cs681;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable {

	private ThreadSafeBankAccount bankAccount = null;
	final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
	private ReentrantLock renlock = new ReentrantLock();
	
	public WithdrawRunnable(ThreadSafeBankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public void setDone() {
		atomicBoolean.set(true);
	}
	@Override
	public void run() {
		while (true) {
			
				if (atomicBoolean.compareAndSet(true, false)) {
					break;
				}
			bankAccount.withdraw(400);
			try{
				Thread.sleep(1000);
			} catch (InterruptedException exception) {
				System.out.println(exception);
				continue;
			}
		}
	}
}
