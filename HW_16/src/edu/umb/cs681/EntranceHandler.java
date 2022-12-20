package edu.umb.cs681;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class EntranceHandler implements Runnable {
	
	private AdmissionControl control;
	final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
	
	public EntranceHandler(AdmissionControl control) {
		this.control = control;
	}

	public void setDone() {
		atomicBoolean.set(true);
	}
	
	@Override
	public void run() {
		while(true) {
			
				if(atomicBoolean.compareAndSet(true, false)) {
					break;
				}
			
			control.enter();
			try{
				Thread.sleep(100);
			} catch(InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " " + e);
				continue;
			} 
		}
	}
}
