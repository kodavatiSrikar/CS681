package edu.umb.cs681;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ExitHandler implements Runnable {

	private AdmissionControl control;
	final AtomicBoolean atomicBoolean = new AtomicBoolean(false);


	public ExitHandler(AdmissionControl control) {
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
			
			control.exit();
			try{
				Thread.sleep(150);
			} catch(InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " " + e);
				continue;
			} 
		}
	}

}
