package edu.umb.cs681;

import java.util.LinkedList;

public class DJIAObservable extends Observable<Double>{
	private double quote;
//	private LinkedList<Observer<T>> observers = new LinkedList<>();
	public void changeQuote(double q) {
		quote = q;
		notifyObservers(quote);
	}
	
	public static void main(String args[]) {
		DJIAObservable observable = new DJIAObservable();
		Observer<Double> sri1 = ((Observable<Double> sender, Double event) -> {System.out.println(sender);});
		Observer<Double> sri2 = ((Observable<Double> sender, Double event) -> {System.out.println(sender);});
		observable.addObserver(sri1);
		Observer<Double> sri3 = ((Observable<Double> sender, Double event) -> {System.out.println(sender);});
		observable.addObserver(sri3);
		observable.addObserver(sri2);
		System.out.println(observable.countObservers());
		observable.changeQuote(23456.44);
		System.out.println(observable.countObservers());
		observable.removeObserver(sri1);
		
		System.out.println(observable.countObservers());
		observable.changeQuote(70055.99);
		

	}

}
