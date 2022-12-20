package edu.umb.cs681;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	HashMap<java.nio.file.Path, Integer> hashMap = new HashMap<java.nio.file.Path, Integer>();
	// non-static lock
	private ReentrantLock nonStatLock = new ReentrantLock();
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
		nonStatLock.lock();
		try {
			if(hashMap.containsKey(path)) {
				hashMap.put(path, hashMap.get(path)+1);
			} else {
				hashMap.put(path, 1);
			}
		}finally {
			nonStatLock.unlock();
		}
	}
	
	public int getCount(java.nio.file.Path path)
	{
		int count = 0;
		nonStatLock.lock();
		try {
			if(hashMap.containsKey(path)) {
				count = hashMap.get(path);
				
			} else {
				count=0;
				
			}
		} finally {
			nonStatLock.unlock();
		}
		return count;
	}
	
}
