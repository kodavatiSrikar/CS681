package edu.umb.cs681;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {
	HashMap<java.nio.file.Path, Integer> hashMap = new HashMap<java.nio.file.Path, Integer>();
	// non-static lock
	private ReentrantReadWriteLock nonStatLock = new ReentrantReadWriteLock();
	// static lock
	private static ReentrantLock statLock = new ReentrantLock();
	// instance
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
		nonStatLock.writeLock().lock();
		try {
			if(hashMap.containsKey(path)) {
				hashMap.put(path, hashMap.get(path)+1);
			} else {
				hashMap.put(path, 1);
			}
		}finally {
			nonStatLock.writeLock().unlock();
		}
	}
	
	public int getCount(java.nio.file.Path path)
	{
		int count = 0;
		nonStatLock.writeLock().lock();
		try {
			if(hashMap.containsKey(path)) {
				count = hashMap.get(path);
				
			} else {
				count=0;
				
			}
		} finally {
			nonStatLock.writeLock().unlock();
		}
		return count;
	}
	
}
