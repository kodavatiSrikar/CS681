package edu.umb.cs681;

import java.math.BigInteger;
import java.util.*;

public class PrimeFactorizer {

    protected long number, from, to;
    protected LinkedList<Long> factors = new LinkedList<>();

    public PrimeFactorizer(long number){
        if(number >= 2){
            this.number = number;
            this.from = 2;
            this.to = number;
        }
        else{
            throw new RuntimeException("Factorisation not possible for such a small number!");
        }
    }
    public LinkedList<Long> getPrimeFactors(){ return factors; }

    public void generatePrimeFactors() {
        long k=number;
        for(long factor=2; k != 1 && factor <= to ;){
            if(k % factor == 0) {
                factors.add(factor);
                k /= factor;
            }
            else {
                factor= Long.parseLong( BigInteger.valueOf(factor).nextProbablePrime().toString() );
            }
        }
    }
}