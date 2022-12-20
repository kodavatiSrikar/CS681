package edu.umb.cs681;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Random;

public class Directory extends FSElement implements Runnable{

    public Directory(Directory parent, String name, int size, LocalDateTime creationtime) {
        super(parent, name, size, creationtime);
        if(parent!=null){
            parent.appendChild(this);
        }

    }

    @Override
    public boolean isDirectory() {
        try {
            if(this instanceof Directory) {
                return true;
            }
            return false;
        }
        finally {
            renlock.unlock();
        }
    }

    LinkedList<FSElement> children = new LinkedList<FSElement>();
    LinkedList<Directory> directory = new LinkedList<Directory>();
    LinkedList<File> file = new LinkedList<File>();

    public LinkedList<FSElement> getChildren(){
        renlock.lock();
        try{
            return this.children;
        }
        finally {
            renlock.unlock();
        }
    }

    public void appendChild(FSElement child ){
        renlock.lock();
        try {
            this.children.add(child);
        }
        finally {
            renlock.unlock();
        }
    }


    public int countChildren(){
        renlock.lock();
        try {
            int countChildren = 0;
            for (FSElement f : this.children)
                countChildren += 1;
            return countChildren;
        } finally {
            renlock.unlock();
        }
    }



    public LinkedList<Directory> getSubDirectories(){
        renlock.lock();
        try {
            LinkedList<Directory> directories = new LinkedList<>();
            for (FSElement fsElement : children) {
                if (fsElement instanceof Directory)
                    directories.add((Directory) fsElement);
            }
            return directories;
        } finally {
            renlock.unlock();
        }
    }


    public LinkedList<File> getFiles(){
        renlock.lock();
        try {

            for (FSElement fsElement : children) {
                if (fsElement instanceof File)
                    file.add((File) fsElement);
            }
            return file;
        } finally {
            renlock.unlock();
        }
    }

    public int getTotalSize(){
        renlock.lock();
        try{
            FSElement dir= this;
            Directory d=(Directory) dir;
            int i;
            int z=0;
            for(i=0;i<d.getChildren().size();i++){
                z=z+d.getChildren().get(i).getSize();
            }
            return z;
        }
        finally{
            renlock.unlock();
        }


    }
    @Override
	public void run() {
    	LocalDateTime creationTime=LocalDateTime.now();
        

        LocalDateTime creationTime1=LocalDateTime.now();
        Directory home=new Directory(parent,"home",0,creationTime1 );
        LocalDateTime creationTime2=LocalDateTime.now();
        Random r = new Random();
		int low = 1;
		int high = 4;
		int result = r.nextInt(high-low) + low;
		int result1 = r.nextInt(high-low) + low;
        File fileA=new File(home,"file:a",result,creationTime2 );
        File fileB=new File(home,"file:b",result1,creationTime2 );
        

        System.out.println("The Size of the directory home in "+Thread.currentThread().getName()+": " + home.getTotalSize());
        

    	
    }
    public static void main(String[] args){
    	Thread[] dirthreads = new Thread[13];
    	Directory[] dir = new Directory[13];
    	

		for (int i = 0; i < 13; i++) {
			System.out.println("Starting Thread " + i);
			LocalDateTime creationTime=LocalDateTime.now();
			dir[i] = new Directory(null,"Root",0,creationTime);
			dirthreads[i] = new Thread(dir[i]);
			dirthreads[i].start();
		}
		try {
			Thread.sleep(10);
		} catch(InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " " + e);
		}
		for(int i = 0; i < 13; i++) {
			dirthreads[i].interrupt();
			try {
				dirthreads[i].join();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}

