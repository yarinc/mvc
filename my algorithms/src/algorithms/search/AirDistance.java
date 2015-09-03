package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * Class that represent a AirDistance heuristic.
 * @author Yarin Cohen
 */
public class AirDistance implements Heuristic<Position> {

	/** 
	 * Method cost calculating the cost of each State by
	 * air distance between the state location and the goal state.
	 * @param state The state that need to get its cost.
	 * @param goalState The state that represent the final state.
	 * @return The air distance between state and goalState.
	 */
	public double cost(State<Position> state, State<Position> goalState) {
		int sum=0, xDistance, yDistance, zDistance;
		//find the distance between each coordinate(x,y,z) and calculate it's air distance to the goal state; 
		xDistance = (state.getState()).getX() - (goalState.getState()).getX();
		xDistance = (int) Math.pow(xDistance, 2);
		yDistance = (state.getState()).getY() - (goalState.getState()).getY();
		yDistance = (int) Math.pow(yDistance, 2);
		zDistance = (state.getState()).getZ() - (goalState.getState()).getZ();
		zDistance = (int) Math.pow(zDistance, 2);
		sum = xDistance + yDistance + zDistance; 
		return Math.sqrt(sum); 	
	}
}
