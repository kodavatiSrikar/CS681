package edu.umb.cs681;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable implements Runnable{
    private Map<String, Float> stock_quote = new HashMap<>();
    private ReentrantLock renlock = new ReentrantLock();

    public void setQuote(String s, Float q) {
    	try {
    	renlock.lock();
    	stock_quote.put(s, q);
        setChanged();
        notifyObservers(new StockEvent(s, q));
    	} finally {
    		renlock.unlock();
    	}
    }

    public void changeQuote(String s, Float q) {
    	try {
        	renlock.lock();
        	stock_quote.put(s, q);
        setChanged();
        notifyObservers(new StockEvent(s, q));
    	} finally {
    		renlock.unlock();
    	}
    }
    
    @Override
	public void run() {
    	StockQuoteObservable SQO = new StockQuoteObservable();

        SQO.addObserver((Observable o, Object obj) -> {
            String tick = ((StockEvent) obj).getTicker();
            Float quote = ((StockEvent) obj).getQuote();
            System.out.println("Observer 1 - Stock event: " + tick + " " + quote);
        });

       

        System.out.println("Number of observers: " + SQO.countObservers());

        String stockCode = "APPL";
        Float stockValue = 140.0f;
        SQO.setQuote(stockCode, stockValue);

        stockValue = 136.0f;
        System.out.println("price alert");
        SQO.changeQuote(stockCode, stockValue);
    }
		


    public static void main(String[] args) {
//        StockQuoteObservable SQO = new StockQuoteObservable();
        Thread[] stockthreads = new Thread[13];
        StockQuoteObservable[] stockobservable = new StockQuoteObservable[13];
        for (int i = 0; i < 13; i++) {
			System.out.println("Starting Thread " + i);
			stockobservable[i] = new StockQuoteObservable();
			stockthreads[i] = new Thread(stockobservable[i]);
			stockthreads[i].start();
		}

		
		
    }
    
}

