package edu.umb.cs681;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	
	ConcurrentHashMap <java.nio.file.Path, AtomicInteger> conhashMap = new ConcurrentHashMap <java.nio.file.Path, AtomicInteger>();
//	ConcurrentHashMap<java.nio.file.Path, LongAdder> conhashMap = new ConcurrentHashMap<>();

	private static ReentrantLock statLock = new ReentrantLock();
	private static AccessCounter inst = null;
	
	private AccessCounter(){}
	
	
	public static AccessCounter getInstance()
	{
		statLock.lock();
		try {
			if(inst==null)
			{
				inst = new AccessCounter();
			}
		} finally {
			statLock.unlock();
		}
		return inst;
	}

	public void increment(java.nio.file.Path path)
	{
//		conhashMap.computeIfPresent("key", k -> new LongAdder()).increment();
		conhashMap.compute(path, (java.nio.file.Path k, AtomicInteger ati) -> {
			if(ati == null) {
				
				return new AtomicInteger(1);
			} else {
				
				return new AtomicInteger(ati.incrementAndGet());
			}
		});
	}

	public int getCount(java.nio.file.Path path)
	{
		return conhashMap.compute(path, (java.nio.file.Path k, AtomicInteger ati) -> {
			if(ati == null) {
				
				return new AtomicInteger(0);
			} else {
				
				return ati;
			}
		}).get();
	}

	
}
