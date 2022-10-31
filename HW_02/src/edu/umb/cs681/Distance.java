package edu.umb.cs681;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Distance {
	public static double get(List<Double> p1, List<Double> p2) {
		return Distance.get(p1, p2, new Euclidean());
	}
	
	public static double get(List<Double> p1, List<Double> p2, DistanceMetric metric) {
		return metric.distance(p1, p2);
	}
	
	public static List<List<Double>> matrix(List<List<Double>> points) {
			return Distance.matrix(points, new Euclidean());
	}; 
	
	public static List<List<Double>> matrix(List<List<Double>> points, DistanceMetric metric) {
		// TODO Make this method more efficient by taking advantage of 
		// the symmetric nature of a distance matrix. For example, reduce
		// the number of distances to compute, from (numOfPoints)*(numOfPoints)
		// to ((numOfPoints-1)*(numOfPoints-1))/2.  
		int numOfPoints = points.size();
		List<List<Double>> distanceMatrix = Distance.initDistanceMatrix(numOfPoints);
		List<Double> current, peer;

		for(int i=0; i < numOfPoints; i++) {
			current = points.get(i);
			for(int j=0; j < numOfPoints; j++) {
				peer = points.get(j);
				double distance = Distance.get(current, peer, metric);
				distanceMatrix.get(i).set(j, distance);
			}
		}
		return distanceMatrix;
	}
	
	
	private static List<List<Double>> initDistanceMatrix(int numOfPoints){
		List<List<Double>> distanceMatrix = new ArrayList<>(numOfPoints);
		for(int i=0; i < numOfPoints; i++) {
			Double[] vector = new Double[numOfPoints];
			Arrays.fill(vector, 0.0);
			distanceMatrix.add( Arrays.asList(vector) );
		}
		return distanceMatrix;
	}
	
	public static double getManhattanDistance(List<Double> point1,List<Double> point2) {
		
			double actual = Distance.get(point1, point2, (List<Double> p1, List<Double> p2) ->{
			double distance = 0;
			for (int i = 0; 1 < p1.size(); i++) {
			distance += Math.abs(p1.get(1) - p2.get(1));
			}
			return distance;
			
			});
			return actual;
	}
	public static List<List<Double>> matrixManhattan(List<List<Double>> listofpoints) {
		
		List<List<Double>> actual = Distance.matrix(listofpoints, (List<Double> p1, List<Double> p2) ->{
		double distance = 0;
		for (int i=0;i<p1.size();i++) {
		distance += Math.abs(p1.get(1) - p2.get(1));
		}
		return distance;
		});
		return actual;
		}
	public static void main(String args[]) {
		List<Double> p1 = Arrays.asList(2.0, 4.0, 7.0);
		List<Double> p2 = Arrays.asList(2.0, 5.0, 6.0);
		double dist= getManhattanDistance(p1,p2);
		System.out.println(dist);
		List<List<Double>> point_list=Arrays.asList(p1,p2);
		List<List<Double>> dist_mat=matrixManhattan(point_list);
		System.out.println(dist_mat);	
	}

		
	}



