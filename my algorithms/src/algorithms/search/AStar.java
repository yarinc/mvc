package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
* The AStar class solve problem that their solving method is supported by the AStar algorithm. 
* In order use the class and solve the problem, a heuristic and the problem is required.
* AStar class contain a heuristic as a data member.
*/

public class AStar<T> extends BackTraceAlgos <T>{
	private Heuristic<T> heuristic;
	
	/**
	 * Constructor for AStar class
	 * @param heuristic The desired heuristic
	 */
	public AStar(Heuristic<T> heuristic) {
		this.heuristic = heuristic;
	}
	/**
	 * Setter to the heuristic attribute
	 * @param heuristic The new heuristic
	 */
	public void setHeuristic(Heuristic<T> heuristic) {
		this.heuristic = heuristic;
	}
	/** 
	 * This method receive a Searchable object and Heuristic
	 * and solve the Searchable object by AStar algorithm combined with the given heuristic.
	 * @param s The Searchable object.
	 * @return The problem solution.
	 */
	public Solution<T> search(Searchable<T> s) {
		//Declare variables
		int nodesEvaluated = 0;
        PriorityQueue<State<T>> openList = new PriorityQueue<>();
		HashSet<State<T>> closedSet=new HashSet<>();
        //Add the start state to the open list
		addToOpenList(openList, s.getStartState());
		while(openList.size()>0){
			//Pop the best State<T> from the list and raise the nodes evaluated counter
			State<T> n=popOpenList(openList);// dequeue
			nodesEvaluated++;
			//Mark n as a visited node by moving it to the closed set
			closedSet.add(n);
			//If n is the exit point of the maze then backTrace to the starting point
			if(n.equals(s.getGoalState()))
				return backTrace(n, s.getStartState(),nodesEvaluated);
			//Add all n successors to the list and calculate their cost to the target by the heuristic
			ArrayList<State<T>> successors=s.getAllPossibleStates(n, heuristic.cost(n,s.getGoalState()));
			//for each successor - check if the successor is not in open list or closed list
			for(State<T> state : successors){
				if(!closedSet.contains(state) && !openList.contains(state)){
					//If doesn't exist in both data structures then set the successor 'cameFrom' attribute and add to the open list
					state.setCameFrom(n);
					addToOpenList(openList, state);
				} 
			}
		}
		return null;
	}
	/**
	 * popOpenList is a method to poll a state from a list.
	 * @param openList The list to poll the object from.
	 * @return The polled state.
	 */
	private  State<T> popOpenList(PriorityQueue<State<T>> openList) {
		return openList.poll();
	}

	/**
	 * addToOpenList is a method to add a state to the open list of the AStar algorithm.
	 * @param openList The list.
	 * @param start The added state.
	 */
	private  void addToOpenList(PriorityQueue<State<T>> openList, State<T> State) {
		openList.add(State);
	}
}