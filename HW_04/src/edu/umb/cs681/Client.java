package edu.umb.cs681;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Client {

	public static void main(String[] args) {
		
		ArrayList<Car> usedCars = new ArrayList<Car>();
		
		
		usedCars.add(new Car("Mercedes", "E class", 900, 2014, 68000));
		usedCars.add(new Car("BENZ", "3 series", 20000, 2017, 86000));
		usedCars.add(new Car("Toyota", "Camry", 7000, 2010, 33000));
		usedCars.add(new Car("Honda", "Acoord", 19000, 2020, 45000));
		usedCars.add(new Car("Audi", "A6", 200000, 2013, 60000));

		usedCars.forEach( (Car car)->System.out.println(car.getMake()+':'+car.getPrice()));
								
				
		Integer maximumPrice = usedCars.stream().map( (Car car)-> car.getPrice() ).max( Comparator.comparing((Integer price)-> price)).get();

	
		System.out.println("Highest price is : $" + maximumPrice);
		
		Integer minimumPrice = usedCars.stream().map( (Car car)-> car.getPrice() ).max( Comparator.comparing((Integer price)-> price)).get();
												
		System.out.println("Lowest price is : $" + minimumPrice);	
		
		
	}
}