package algorithms.search;

import algorithms.mazeGenerators.Position;
/**
 * Class that represent a ManhattanDistance heuristic.
 * @author Yarin Cohen
 */

public class ManhattanDistance implements Heuristic<Position> {
	
	public double cost(State<Position> state, State<Position> goalState) {
		/** 
		 * Method cost calculating the cost of each State by
		 * Manhattan distance between the state location and the goal state.
		 * @param state The state that need to get its cost.
		 * @param goalState The state that represent the final state.
		 * @return The Manhattan distance between state and goalState.
		 */
		int sum=0, xDistance, yDistance, zDistance;
		//find the distance between each coordinate(x,y,z) and calculate it's Manhattan distance to the goal state
		//Sum the distance between each coordinate
		xDistance = (state.getState()).getX() - (goalState.getState()).getX();
		xDistance = Math.abs(xDistance); 
		yDistance = (state.getState()).getY() - (goalState.getState()).getY();
		yDistance = Math.abs(yDistance); 
		zDistance = (state.getState()).getZ() - (goalState.getState()).getZ();
		zDistance = Math.abs(zDistance); 
		sum = xDistance + yDistance + zDistance; 
		return sum; 	
	}
}
