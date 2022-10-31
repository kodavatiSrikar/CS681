package edu.umb.cs681;

import java.util.*;

public class PrimeGenerator {
    protected long from, to;
    protected LinkedList<Long> primes = new LinkedList<Long>();
    public PrimeGenerator(long from,long to){
        if( from > 0 && to > 0 && to > from) {
            this.from = from;
            this.to = to;
        }else{
            System.out.println("Exception : Invalid Values Found!");
            System.exit(0);
        }
    }
    protected boolean isPrime(long p){
        if(p < 2 ||( p > 2 && p%2==0 )){ return false; }
        long i,n=(long) Math.sqrt(p);
        for (i = 3; i <= n && p%i != 0; i++);
        if(i>n) return true;
        else return false;
    }
    public void generatePrimes(){
        for(long n=from;n<=to;n++)
            if(isPrime(n)) primes.add(n);
    }
    public LinkedList<Long> getPrimes(){
        return primes;
    }
}