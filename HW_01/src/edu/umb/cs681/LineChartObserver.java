package edu.umb.cs681;

public class LineChartObserver implements Observer<Double>{
	public void update(Observable<Double> sender, Double event) {
		System.out.println("Event: " + event + ", Sender: " + sender);
	}
}
