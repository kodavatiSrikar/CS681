package edu.umb.cs681;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class NasaData {
	
	public void readData() throws IOException {
		Path path = Paths.get("src/edu/umb/cs681/data.csv");
		System.out.println(path);
		try( Stream<String> lines = Files.lines(path) ){
	        List<List<String>> matrix = lines.map(line -> {
	                    return Stream.of( line.split(",") )
	                            .map(value-> value)
	                            .collect( Collectors.toList() ); })
	                .collect( Collectors.toList() );
//	        System.out.println(matrix);
	        
	        
	        List<Integer> dateEntry = matrix.stream().map(row -> Integer.parseInt(row.get(1))).collect(Collectors.toList());
	        List<Double> temp2M = matrix.stream().map(row -> Double.parseDouble(row.get(4)) ).collect (Collectors.toList());
//	        
	        List<Double> precp = matrix.stream() .map(row -> Double.parseDouble(row.get(7))).collect(Collectors.toList());
	        List<Double> relHum = matrix.stream() .map(row -> Double.parseDouble(row.get(8))). collect (Collectors.toList ());
//	        
	        
	        
	        Optional<Integer> minDateEntry1= dateEntry.stream()
	                .min((i, j) -> i.compareTo(j));
	        System.out.println("minimum year"+minDateEntry1.get());
	        Optional<Integer> maxDateEntry1= dateEntry.stream()
	                .max((i, j) -> i.compareTo(j));
	        System.out.println("maximum year"+maxDateEntry1.get());
	        int meanDateEntry= (int) dateEntry.stream().mapToInt(value->value).average().getAsDouble();
	        System.out.println("average year"+meanDateEntry);
	        
	        Optional<Double> mintemp2M1= temp2M .stream()
	                .min((i, j) -> i.compareTo(j));
	        System.out.println("minimum temp"+mintemp2M1.get());
	        
	        Optional<Double> maxtemp2M1= temp2M .stream()
	                .max((i, j) -> i.compareTo(j));
	        System.out.println("maximum temp"+maxtemp2M1.get());
	        double meantemp2M= temp2M .stream().mapToDouble(value->value).average().getAsDouble();
	        System.out.println("average temp"+meantemp2M);
	        
	        Optional<Double> minprecp1= precp.stream()
	                .min((i, j) -> i.compareTo(j));
	        System.out.println("minimum precepitation"+minprecp1.get());
	        
	        Optional<Double> maxprecp1= precp.stream()
	                .max((i, j) -> i.compareTo(j));
	        System.out.println("maximum precepitation"+maxprecp1.get());
	        double meanprecp=  precp.stream().mapToDouble(value->value).average().getAsDouble();
	        System.out.println("average precepitation"+meanprecp);
	        
	        Optional<Double> minrelHum1= relHum.stream()
	                .min((i, j) -> i.compareTo(j));
	        System.out.println("minimum humidity"+minrelHum1.get());
	        
	        Optional<Double> maxrelHum1= relHum.stream()
	                .max((i, j) -> i.compareTo(j));
	        System.out.println("maximum humidity"+maxrelHum1.get());
	        double meanrelHum=  relHum.stream().mapToDouble(value->value).average().getAsDouble();
	        System.out.println("average humidity"+meanrelHum);
	        
		};
		}
	
	
	public static void main(String[] args) throws IOException {
		NasaData nd= new NasaData();
		nd.readData();
	}

}
