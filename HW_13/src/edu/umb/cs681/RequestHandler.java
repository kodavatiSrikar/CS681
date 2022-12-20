package edu.umb.cs681;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicBoolean;

public class RequestHandler implements Runnable {

	private ReentrantLock renlock = new ReentrantLock();
	final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
	private int id;
	
	public RequestHandler(int id) {
		this.id = id;
	}
	
	public void setDone()
	{
		atomicBoolean.set(true);
		
	}
	
	
	@Override
	public void run() {
		Random r = new Random();
		int low = 1;
		int high = 4;
		int result = r.nextInt(high-low) + low;
		while(true) {
				if(atomicBoolean.compareAndSet(true, false)) {
					break;
				}
		
			
			java.nio.file.Path path = null;
			switch(result){
			case 0:
				path = java.nio.file.Paths.get("One.html");
				break;
			case 1:
				path = java.nio.file.Paths.get("Two.html");
				break;
			case 2:
				path = java.nio.file.Paths.get("three.html");
				break;
			case 3:
				path = java.nio.file.Paths.get("four.html");
				break;
			
			default:
			}
			
			AccessCounter.getInstance().increment(path);
			AccessCounter.getInstance().getCount(path);
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " " + e);
				continue;
			}
	}
}

	
	public static void main(String[] args) {
			
			Thread[] handlerthreads = new Thread[13];
			RequestHandler[] requestHandlers = new RequestHandler[13];
	
	
			for (int i = 0; i < 13; i++) {
				System.out.println("Starting Thread " + i);
				requestHandlers[i] = new RequestHandler(i);
				handlerthreads[i] = new Thread(requestHandlers[i]);
				handlerthreads[i].start();
			}
	
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " " + e);
			}
			for(int i = 0; i < 13; i++) {
				requestHandlers[i].setDone();
				handlerthreads[i].interrupt();
				try {
					handlerthreads[i].join();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
}
