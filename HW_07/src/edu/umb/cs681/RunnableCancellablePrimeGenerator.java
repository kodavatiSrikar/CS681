package edu.umb.cs681;

import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
    private ReentrantLock reenlock = new ReentrantLock();
    private boolean done = false;
    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }
    public void run(){generatePrimes();}
    public void setDone(){
    	reenlock.lock();
        try {
            done = false;
        } finally {
        	reenlock.unlock();
        }
    }

    public void generatePrimes(){
        for (long n = from; n <= to; n++) {
        	reenlock.lock();
            try {
                if (done) {
                    System.out.println("Stopped prime number generation.");
                    this.primes.clear();
                    break;
                }
                if (isPrime(n)) {
                    this.primes.add(n);
                }
            }finally{
            	reenlock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        RunnableCancellablePrimeGenerator sriGen = new RunnableCancellablePrimeGenerator(101,898);
        
        Thread thread = new Thread(sriGen);
        thread.start();
        sriGen.setDone();
        try {
        	thread.join();
        } catch (InterruptedException e) {
			e.printStackTrace();
        }
        
        sriGen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + sriGen.getPrimes().size() + " prime numbers are found.");
        
    }
}