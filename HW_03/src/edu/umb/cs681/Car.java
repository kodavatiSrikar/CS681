package edu.umb.cs681;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Car {

	private String make, model;
	private int mileage, year;
	private int price;
	private int dominationCount;

	public Car(String make, String model, int mileage, int year, int price) {
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getMileage() {
		return mileage;
	}

	public int getYear() {
		return year;
	}

	public int getPrice() {
		return price;
	}

	public void setDominationCount(List<Car> cars) {
		for (Car car : cars) {
			if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
					&& (car.getYear() <= this.getYear())) {
				this.dominationCount++;
			}
		}
		this.dominationCount--;
	}

	public int getDominationCount() {
		return this.dominationCount;
	}

	public static void main(String args[]) {
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("Mer=cedes", "E class", 900, 2014, 68000));
		cars.add(new Car("BENZ", "3 series", 20000, 2017, 86000));
		cars.add(new Car("Toyota", "Camry", 7000, 2010, 33000));
		cars.add(new Car("Honda", "Acoord", 19000, 2020, 45000));
		cars.add(new Car("Audi", "A6", 200000, 2013, 60000));

		cars.forEach((Car car) -> car.setDominationCount(cars));

		ArrayList<Car> sortPrice = cars.stream().sorted(Comparator.comparing(Car::getPrice)).collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Car> sortYear = cars.stream().sorted(Comparator.comparing(Car::getYear)).collect(Collectors.toCollection(ArrayList::new));

		ArrayList<Car> sortMileage = cars.stream().sorted(Comparator.comparing(Car::getMileage))
				.collect(Collectors.toCollection(ArrayList::new));

		ArrayList<Car> sortDominationCount = cars.stream().sorted(Comparator.comparing(Car::getDominationCount))
				.collect(Collectors.toCollection(ArrayList::new));

		System.out.println("\nPrice");
		System.out.println(sortPrice.get(0).getMake());
		double median_price=sortPrice.stream().mapToInt(Car::getPrice).average().getAsDouble();
		System.out.println(median_price);
		System.out.println("\nYear");
		System.out.println(sortYear.get(0).getMake());

		System.out.println("\nMileage");
		System.out.println(sortMileage.get(0).getMake());
		double median_mil=sortPrice.stream().mapToInt(Car::getMileage).average().getAsDouble();
		System.out.println(median_mil);


		System.out.println("\nSort by Domination");
		System.out.println(sortDominationCount.get(0).getMake());
	}
}