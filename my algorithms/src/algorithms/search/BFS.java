package algorithms.search;
import java.util.*;

/**
* The BFS class solve problem that their solving method is supported by the BFS algorithm. 
* In order use the class and solve the problem, the problem is required.
*
*/
public class BFS<T> extends BackTraceAlgos<T> {
	/** 
	 * search method receive a Searchable object
	 * and solve the Searchable object by BFS algorithm.
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
			//Add all n successors to the list and calculate their cost to the target.
			ArrayList<State<T>> successors=s.getAllPossibleStates(n, 1);
			//for each successor - check if the successor is not in open list or closed list
			for(State<T> state : successors){
				if(!closedSet.contains(state) && ! openList.contains(state)){
					//If doesn't exist in both data structures then set the successor 'cameFrom' attribute and add to the open list
					state.setCameFrom(n);
					addToOpenList(openList, state);
				} 
			}
		}
		return null;
	}
	/**
	 * addToOpenList is a method to add a state to the open list of the AStar algorithm.
	 * @param openList The list.
	 * @param start The added state.
	 */
	private void addToOpenList(PriorityQueue<State<T>> openList, State<T> startState) {
		openList.add(startState);
	}
	/**
	 * popOpenList is a method to poll a state from a list.
	 * @param openList The list to poll the object from.
	 * @return The polled state.
	 */
	private State<T> popOpenList(PriorityQueue<State<T>> openList) {
		return openList.poll();
	}
}

