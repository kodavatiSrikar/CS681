package edu.umb.cs681;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;


public abstract class FSElement {

    protected ReentrantLock renlock = new ReentrantLock();

    private String name;
    private int size;
    private static LocalDateTime creationTime;
    protected Directory parent;

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime){
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public Directory getParent() {
    	renlock.lock();
        try{
        return this.parent;
        }
        finally {
            renlock.unlock();
        }
    }

    public int getSize() {
    	renlock.lock();
        try{
        return this.size;
        }
        finally {
            renlock.unlock();
        }
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public String getName() {
    	renlock.lock();
        try{
    	return this.name;
    }
    finally {
        renlock.unlock();
    }
    }

    public abstract boolean isDirectory() ;

}
