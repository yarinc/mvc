package algorithms.search;

import java.util.Collections;
/**
 * Class that abstracting all algorithms that using the backTrace method
 * to get the full solution.
 * @author Yarin Cohen
 */

public abstract class BackTraceAlgos<T> {
	
	/**
	 * BackTrace is a method that by a given goal and a start states
	 * building a data structure of the solution of the problem.  
	 * @param goalState The target state of the problem.
	 * @param startState The begin state of the problem.
	 * @param nodesEvaluated The number of states that checked if they are the goal state.
	 * @return The solution
	 */
	public Solution<T> backTrace(State<T> goalState, State<T> startState, int nodesEvaluated) {
		//Declare variables
		Solution<T> solution = new Solution<T>();
		//Set number of evaluated nodes and add the goal state 
		solution.setNodesEvaluated(nodesEvaluated);
		solution.add(goalState.getState());
		//Set back as the node before the goalState
		State<T> back = new State<T>(goalState.getCameFrom());
		//While back is not the start point - get the previous node of back
		while(!(back.equals(startState))) {
			solution.add(back.getState());
			back = back.getCameFrom();
		} 
		//Add the start state, reverse the solution, and return it 
		solution.add(startState.getState());
		Collections.reverse(solution.getSolution());
		return solution;
	}
}
