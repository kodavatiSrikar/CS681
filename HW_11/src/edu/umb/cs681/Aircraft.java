package edu.umb.cs681;

import java.util.concurrent.locks.ReentrantLock;

public class Aircraft implements Runnable {

	private Position position;

	private ReentrantLock reenlock = new ReentrantLock();

	public Aircraft(Position position) {
		reenlock.lock();
		try {
		this.position = position;
		}finally{ 
        	reenlock.unlock();
        	}
	}

	public void setPosition(double Lat, double Long, double Alt) {
		reenlock.lock();
		try {
		this.position = position.changeAlt(Alt).changeLat(Lat).changeLong(Long);
		}finally{ 
        	reenlock.unlock();
        	}
	}

	public Position getPosition() {
		reenlock.lock();
		try {
		return position;
		}finally{ 
        	reenlock.unlock();
        	}
		
	}

	@Override
	public void run() {
		
			Position point1 = new Position(45.813, -84.1875, 40);
			Position point2 = new Position(40.813, -88.1795, 40);
			

			Aircraft position1 = new Aircraft(point1);
			Aircraft position2 = new Aircraft(point2);
			

			position1.setPosition(40.813, -88.1795, 40);
			position2.setPosition(45.813, -84.1875, 40);
			

			System.out.println("New Position:- " + position1.getPosition());
			System.out.println("New Position:- " + position2.getPosition());

		
		

	}

	public static void main(String[] args) {

		Aircraft pos = new Aircraft(null);

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				pos.run();

			}).start();
		}
	}

}
