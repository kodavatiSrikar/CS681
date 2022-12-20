package edu.umb.cs681;

import java.time.LocalDateTime;

public class File extends FSElement {

    public File(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        parent.appendChild(this);
    }

    @Override
    public boolean isDirectory() {
    	renlock.lock();
        try{
        return false;
        }
        finally {
            renlock.unlock();
        }
    }
}