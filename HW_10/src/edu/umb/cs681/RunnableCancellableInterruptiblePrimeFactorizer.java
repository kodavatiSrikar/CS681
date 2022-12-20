package edu.umb.cs681;

import java.math.BigInteger;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer{
    private final ReentrantLock reenlock = new ReentrantLock();
    private boolean done = false;
    public RunnableCancellableInterruptiblePrimeFactorizer(long number){super (number);}

    public void run(){generatePrimeFactors();}
    public void setDone(){
        reenlock.lock();
        try {
            done = false;
        } finally {
            reenlock.unlock();
        }
    }
    
    public void generatePrimeFactors(){
        long k=number;
        long factor=2;
        while(k != 1 && factor <= to ){
            reenlock.lock();
            try {
                if (k % factor == 0) {
                    factors.add(factor);
                    k /= factor;
                } 
                else {
    		    	if(factor==2){ 
    		    		factor++; 
    		    		}
    		    	else{ 
    		    		factor += 2; 
    		    		}
    		    } 
                }finally{ 
            	reenlock.unlock();
            	}
        }
    }

    public static void main(String[] args) {
    	final ReentrantLock reenlock = new ReentrantLock();
        RunnableCancellableInterruptiblePrimeFactorizer sriFactor = new RunnableCancellableInterruptiblePrimeFactorizer(132);
        Thread thread = new Thread(sriFactor);
        thread.start();
        sriFactor.setDone();
        reenlock.lock();
        thread.interrupt();
        reenlock.unlock();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        sriFactor.getPrimeFactors().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + sriFactor.getPrimeFactors().size() + " prime factors are found.");
    }
}
