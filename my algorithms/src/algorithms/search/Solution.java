package algorithms.search;

import java.util.ArrayList;

/**
 * Solution class represent a solution to a Searchable object. 
 * It's contain a list of the moves required to solve the Searchable object.
 * @author Yarin Cohen
 * @param <T> - The type of solution.
 */
public class Solution<T> {
	private ArrayList<T> solution;
	private int nodesEvaluated;
	
	/**
	 * Constructor of Solution class. 
	 * Initializing with ArrayList and number of nodeEvaluated to zero.
	 */
	public Solution() { 
		solution = new ArrayList<T>();
		nodesEvaluated = 0;
	}
	/**
	 * Getter to the nodesEvaluated attribute.
	 * @return The value of nodesEvaluated.
	 */
	public int getNodesEvaluated() {
		return nodesEvaluated;
	}
	/**
	 * Setter to the node Evaluated attribute.
	 * @param nodesEvaluated The value to set the attribute.
	 */
	public void setNodesEvaluated(int nodesEvaluated) {
		this.nodesEvaluated = nodesEvaluated;
	}
	/**
	 * Getter to the ArrayList attribute.
	 * @return The ArrayList.
	 */
	public ArrayList<T> getSolution() {
		return solution;
	}
	/**
	 * Adding a node to the ArrayList.
	 * @param node The node to be added to the ArrayList.
	 */
	public void add(T node) { 
		solution.add(node); 
	}
	/**
	 * Prints the Solution.
	 * First prints the nodes in order and than the nodesEvaluated value.
	 */
	public void print() { 
		for(T place : solution)
			System.out.println(place);
		System.out.println("Number of nodes evaluated: " + nodesEvaluated + "\n");
	}
}
