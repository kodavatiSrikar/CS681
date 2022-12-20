package edu.umb.cs681;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionControl {
	private int currentVisits = 0;
	private ReentrantLock renlock = new ReentrantLock();
	private Condition sufficient = renlock.newCondition();
	private Condition notSufficient = renlock.newCondition();
	
	public void enter() {
		renlock.lock();
		try{
			

			while(currentVisits >= 10){
				System.out.println("Please wait, customers in line to enter...");
				sufficient.await();
			}
			currentVisits ++;
			System.out.println(" VISITOR ENTER: current Visitors count = " + 
					currentVisits);
			notSufficient.signalAll();
		}
		catch (InterruptedException exception){
			
			System.out.println(exception);
		}
		finally{
			renlock.unlock();
			
		}

	}
	
	public void exit() {
		renlock.lock();
		
		try{
			
			while(currentVisits <= 0) {
				System.out.println(" no Vistior left to exit..");
				notSufficient.await();
			}
			
			if(!(currentVisits <= 0)) {
				currentVisits --;
			}
			System.out.println(" VISITOR Exit: current Visitors count =" + 
					currentVisits);
			sufficient.signalAll();
		}
		catch (InterruptedException exception){
			
			System.out.println(exception);
		}
		finally{
			renlock.unlock();
			
		}
	}
	
	public static void main(String[] args) {
		AdmissionControl adControl = new AdmissionControl();
		EntranceHandler enterHandler = new EntranceHandler(adControl);
		ExitHandler exitHandler = new ExitHandler(adControl);
		
		Thread entranceThread = new Thread(enterHandler);
		entranceThread.start();
		Thread exitThread = new Thread(exitHandler);
		exitThread.start();

		try{
			Thread.sleep(1000);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		enterHandler.setDone();
		exitHandler.setDone();
		entranceThread.interrupt();
		exitThread.interrupt();
		try {
			entranceThread.join();
			exitThread.join();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
