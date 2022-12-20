package edu.umb.cs681;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private LinkedList<Observer> observers;
    private ReentrantLock lockObs = new ReentrantLock();

    Observable() {
        observers = new LinkedList<>();
    }

    public void addObserver(Observer o) {
    	try {
            lockObs.lock();
        this.observers.add(o);
    	} finally {
            lockObs.unlock();
        }
    }

    public void deleteObserver(Observer o) {
    	try {
            lockObs.lock();
        this.observers.remove(o);
    	} finally {
            lockObs.unlock();
        }
    }

    protected void setChanged() {
    	atomicBoolean.set(true);
    }

    protected void clearChanged() {
    	atomicBoolean.set(false);
    }

    public boolean hasChanged() {
        return atomicBoolean.get();
    }

    public void notifyObservers(Object obj) {
        Object[] localObjs;

        
        if (atomicBoolean.compareAndSet(false, true)) return;
        try {
            lockObs.lock();
        localObjs = observers.toArray();
        clearChanged();
        } finally {
            lockObs.unlock();
        }

      
        for (Object localObj : localObjs) {
            ((Observer) localObj).update(this, obj);
        }
    }

    public int countObservers() {
        try {
            lockObs.lock();
            return observers.size();
        } finally {
            lockObs.unlock();
        }

    }

	public void run() {
		// TODO Auto-generated method stub
		
	}
}
