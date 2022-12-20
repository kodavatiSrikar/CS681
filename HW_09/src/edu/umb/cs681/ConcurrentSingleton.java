package edu.umb.cs681;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton {
    private ConcurrentSingleton(){}
    private static ConcurrentSingleton new_instance = null;
    private static final ReentrantLock reenlock = new ReentrantLock();

    public static ConcurrentSingleton getInstance(){
    	reenlock.lock();
        try{
            if(new_instance==null)
                new_instance = new ConcurrentSingleton();
            return new_instance;
        }finally{
        	reenlock.unlock();
        }
    }

    public static void main(String[] args){
    	ConcurrentSingleton sriton= new ConcurrentSingleton();
        Thread thread1 = new Thread(()->{System.out.println(sriton.getInstance());});
        Thread thread2 = new Thread(()->{System.out.println(sriton.getInstance());});
        Thread thread3 = new Thread(()->{System.out.println(sriton.getInstance());});
        thread1.start();
		thread2.start();
		thread3.start();
    }
}
