package edu.umb.cs681;

public abstract class RunnablePrimeGenerator extends PrimeGenerator implements Runnable{
    public RunnablePrimeGenerator(long from,long to){
        super(from,to);
    }
}