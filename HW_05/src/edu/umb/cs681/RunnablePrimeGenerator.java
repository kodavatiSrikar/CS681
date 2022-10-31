package edu.umb.cs681;

import java.time.Instant;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {

	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {
		RunnablePrimeGenerator g = new RunnablePrimeGenerator(1L, 2000000L);
		Thread t = new Thread(g);
		long start_1 = Instant.now().toEpochMilli();
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
		}

		long end_1 = Instant.now().toEpochMilli();
		float time1 = (end_1 - start_1) / 1000F;

		g.getPrimes().forEach((Long prime) -> System.out.print(prime + ", "));
		long primeNum1 = g.getPrimes().size();
		
		
		
		RunnablePrimeGenerator rpg1 = new RunnablePrimeGenerator(1L, 1000000L);
		RunnablePrimeGenerator rpg2 = new RunnablePrimeGenerator(1000000L, 2000000L);
		Thread thread1 = new Thread(rpg1);
		Thread thread2 = new Thread(rpg2);

		long start_2 = Instant.now().toEpochMilli();
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
		}

		long end_2 = Instant.now().toEpochMilli();
		float time2 = (end_2 - start_2) / 1000F;

		long primeNum2 = rpg1.getPrimes().size() + rpg2.getPrimes().size();
		
		
		RunnablePrimeGenerator rpg3 = new RunnablePrimeGenerator(1L, 500000L);
		RunnablePrimeGenerator rpg4 = new RunnablePrimeGenerator(500000L, 1000000L);
		RunnablePrimeGenerator rpg5 = new RunnablePrimeGenerator(1000000L, 1500000L);
		RunnablePrimeGenerator rpg6 = new RunnablePrimeGenerator(1500000L, 2000000L);

		Thread thread3 = new Thread(rpg3);
		Thread thread4 = new Thread(rpg4);
		Thread thread5 = new Thread(rpg5);
		Thread thread6 = new Thread(rpg6);

		long start_3 = Instant.now().toEpochMilli();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		try {
			thread3.join();
			thread4.join();
			thread5.join();
			thread6.join();
		} catch (InterruptedException e) {
		}

		long end_3 = Instant.now().toEpochMilli();
		float time3 = (end_3 - start_3) / 1000F;

		long primeNum4 = rpg3.getPrimes().size() + rpg4.getPrimes().size() + rpg5.getPrimes().size() + rpg6.getPrimes().size();
		
	
		
		RunnablePrimeGenerator rpg7 = new RunnablePrimeGenerator(1L, 250000L);
		RunnablePrimeGenerator rpg8 = new RunnablePrimeGenerator(250000L, 500000L);
		RunnablePrimeGenerator rpg9 = new RunnablePrimeGenerator(500000L, 750000L);
		RunnablePrimeGenerator rpg10 = new RunnablePrimeGenerator(750000L, 1000000L);
		RunnablePrimeGenerator rpg11 = new RunnablePrimeGenerator(1000000L, 1250000L);
		RunnablePrimeGenerator rpg12 = new RunnablePrimeGenerator(1250000L, 1500000L);
		RunnablePrimeGenerator rpg13 = new RunnablePrimeGenerator(1500000L, 1750000L);
		RunnablePrimeGenerator rpg14 = new RunnablePrimeGenerator(1750000L, 2000000L);

		Thread thread7 = new Thread(rpg7);
		Thread thread8 = new Thread(rpg8);
		Thread thread9 = new Thread(rpg9);
		Thread thread10 = new Thread(rpg10);
		Thread thread11 = new Thread(rpg11);
		Thread thread12 = new Thread(rpg12);
		Thread thread13 = new Thread(rpg13);
		Thread thread14 = new Thread(rpg14);

		long start_4 = Instant.now().toEpochMilli();
		thread7.start();
		thread8.start();
		thread9.start();
		thread10.start();
		thread11.start();
		thread12.start();
		thread13.start();
		thread14.start();
		try {
			thread7.join();
			thread8.join();
			thread9.join();
			thread10.join();
			thread11.join();
			thread12.join();
			thread13.join();
			thread14.join();
		} catch (InterruptedException e) {
		}

		long end_4 = Instant.now().toEpochMilli();
		float time4 = (end_4 - start_4) / 1000F;

		long primeNum8 = rpg7.getPrimes().size() + rpg8.getPrimes().size() + rpg9.getPrimes().size() + rpg10.getPrimes().size()
				+ rpg11.getPrimes().size() + rpg12.getPrimes().size() + rpg13.getPrimes().size() + rpg14.getPrimes().size();
		
		
		RunnablePrimeGenerator rpg15 = new RunnablePrimeGenerator(1L, 125000L);
		RunnablePrimeGenerator rpg16 = new RunnablePrimeGenerator(125000L, 250000L);
		RunnablePrimeGenerator rpg17 = new RunnablePrimeGenerator(250000L, 375000L);
		RunnablePrimeGenerator rpg18 = new RunnablePrimeGenerator(375000L, 500000L);
		RunnablePrimeGenerator rpg19 = new RunnablePrimeGenerator(500000L, 625000L);
		RunnablePrimeGenerator rpg20 = new RunnablePrimeGenerator(625000L, 750000L);
		RunnablePrimeGenerator rpg21 = new RunnablePrimeGenerator(750000L, 875000L);
		RunnablePrimeGenerator rpg22 = new RunnablePrimeGenerator(875000L, 1000000L);
		RunnablePrimeGenerator rpg23 = new RunnablePrimeGenerator(1000000L, 1125000L);
		RunnablePrimeGenerator rpg24 = new RunnablePrimeGenerator(1125000L, 1250000L);
		RunnablePrimeGenerator rpg25 = new RunnablePrimeGenerator(1250000L, 1375000L);
		RunnablePrimeGenerator rpg26 = new RunnablePrimeGenerator(1375000L, 1500000L);
		RunnablePrimeGenerator rpg27 = new RunnablePrimeGenerator(1500000L, 1625000L);
		RunnablePrimeGenerator rpg28 = new RunnablePrimeGenerator(1625000L, 1750000L);
		RunnablePrimeGenerator rpg29 = new RunnablePrimeGenerator(1750000L, 1875000L);
		RunnablePrimeGenerator rpg30 = new RunnablePrimeGenerator(1875000L, 2000000L);

		Thread t15 = new Thread(rpg15);
		Thread t16 = new Thread(rpg16);
		Thread t17 = new Thread(rpg17);
		Thread t18 = new Thread(rpg18);
		Thread t19 = new Thread(rpg19);
		Thread t20 = new Thread(rpg20);
		Thread t21 = new Thread(rpg21);
		Thread t22 = new Thread(rpg22);
		Thread t23 = new Thread(rpg23);
		Thread t24 = new Thread(rpg24);
		Thread t25 = new Thread(rpg25);
		Thread t26 = new Thread(rpg26);
		Thread t27 = new Thread(rpg27);
		Thread t28 = new Thread(rpg28);
		Thread t29 = new Thread(rpg29);
		Thread t30 = new Thread(rpg30);

		long start_5 = Instant.now().toEpochMilli();
		t15.start();
		t16.start();
		t17.start();
		t18.start();
		t19.start();
		t20.start();
		t21.start();
		t22.start();
		t23.start();
		t24.start();
		t25.start();
		t26.start();
		t27.start();
		t28.start();
		t29.start();
		t30.start();
		try {
			t15.join();
			t16.join();
			t27.join();
			t18.join();
			t19.join();
			t20.join();
			t21.join();
			t22.join();
			t23.join();
			t24.join();
			t25.join();
			t26.join();
			t27.join();
			t28.join();
			t29.join();
			t30.join();
		} catch (InterruptedException e) {
		}

		long end_5 = Instant.now().toEpochMilli();
		float time5 = (end_5 - start_5) / 1000F;

		long primeNum16 = rpg15.getPrimes().size() + rpg16.getPrimes().size() + rpg17.getPrimes().size()
				+ rpg18.getPrimes().size() + rpg19.getPrimes().size() + rpg20.getPrimes().size() + rpg21.getPrimes().size()
				+ rpg22.getPrimes().size() + rpg23.getPrimes().size() + rpg24.getPrimes().size() + rpg25.getPrimes().size()
				+ rpg26.getPrimes().size() + rpg27.getPrimes().size() + rpg28.getPrimes().size() + rpg29.getPrimes().size()
				+ rpg30.getPrimes().size();
		System.out.println("\n" + primeNum16 + " prime numbers are found in total.");
		
		
		System.out.println("Num of Threads\t " + "Time in Seconds");
		System.out.println("\t 1 \t\t " + time1);
		System.out.println("\t 2 \t\t " + time2);
		System.out.println("\t 4 \t\t " + time3);
		System.out.println("\t 8 \t\t " + time4);
		System.out.println("\t 16 \t\t " + time5);

	}

}
