package edu.umb.cs681;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Observable {
	private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private ConcurrentLinkedQueue<Observer> observers;

    Observable() {
        observers = new ConcurrentLinkedQueue<>();
    }

    public void addObserver(Observer o) {
        this.observers.offer(o);
    }

    public void deleteObserver(Observer o) {
        this.observers.remove(o);
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
        if (hasChanged()) {
            observers.forEach(observer -> observer.update(this, obj));
            clearChanged();
        }
    }

    public int countObservers() {
        return observers.size();
    }
}
