package edu.umb.cs681;

public class RunnableInterruptiblePrimeGenerator extends RunnablePrimeGenerator {

	public RunnableInterruptiblePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			// Stop generating prime numbers if Thread.interrupted()==true
			if(Thread.interrupted()){
				System.out.println("Stopped generating primes due to a thread interruption.");
				this.primes.clear();
				break;
			}
			if( isPrime(n) ){ this.primes.add(n); }
		}
	}

	public static void main(String[] args) {
		RunnableInterruptiblePrimeGenerator gen = new RunnableInterruptiblePrimeGenerator(1,100);
		Thread thread = new Thread(gen);
		thread.start();
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	}
}
