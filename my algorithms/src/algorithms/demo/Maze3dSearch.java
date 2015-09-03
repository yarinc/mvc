package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

/**
 * Class that  implements Searchable to wrap a 3 dimensions maze
 * in order to send it to the solving class.
 * @author Yarin Cohen
 *
 */
public class Maze3dSearch implements Searchable<Position> {

	private Maze3d maze;
	/**
	 * A constructor.
	 * @param maze The maze to wrap.
	 */
	public Maze3dSearch(Maze3d maze) {
		this.maze = maze;
	}
	/**
	 * Getting the goalState of maze.
	 * @return The goal state.
	 */
	@Override
	public State<Position> getGoalState() {
		State<Position> goalState = new State<Position>(maze.getGoalPosition());
		return goalState;
	}
	/**
	 * Getting the stateState of maze.
	 * @return The start state.
	 */
	@Override
	public State<Position> getStartState() {
		State<Position> startState = new State<Position>(maze.getStartPosition());
		startState.setCost(0);
		return startState;
	}
	/**
	 * Getting all successors of a State, without the 'cameFrom' state.
	 * @param state Getting all its successors.
	 * @param cost The cost of each successor.
	 * @return ArrayList with all the successors as a State objects.
	 */
	@Override
	public  ArrayList<State<Position>> getAllPossibleStates(State<Position> state,double cost) {
		ArrayList<State<Position>> successors = new ArrayList<>();
		//check if Left position is a possible state
		if(maze.getMazeValue(maze.getLeft((Position)state.getState())) == 0) {
			if(state.getCameFrom() == null)
				successors.add(new State<Position>(maze.getLeft((Position)state.getState()), (State<Position>)state, cost));
			else if(!(state.getCameFrom().getState().equals(maze.getLeft(state.getState()))))
				successors.add(new State<Position>(maze.getLeft((Position)state.getState()), (State<Position>)state, cost));
		}
		//check if Right position is a possible state
		if(maze.getMazeValue(maze.getRight((Position)state.getState())) == 0) {
			if(state.getCameFrom() == null)
				successors.add(new State<Position>(maze.getRight((Position)state.getState()), (State<Position>)state, cost));
			else if(!(state.getCameFrom().getState().equals(maze.getRight(state.getState()))))
				successors.add(new State<Position>(maze.getRight((Position)state.getState()), (State<Position>)state, cost));
		}
		//check if Up position is a possible state
		if(maze.getMazeValue(maze.getUp((Position)state.getState())) == 0) {
			if(state.getCameFrom() == null)
				successors.add(new State<Position>(maze.getUp((Position)state.getState()), (State<Position>)state, cost));
			else if(!(state.getCameFrom().getState().equals(maze.getUp(state.getState()))))
				successors.add(new State<Position>(maze.getUp((Position)state.getState()), (State<Position>)state, cost));
		}
		//check if Down position is a possible state
		if(maze.getMazeValue(maze.getDown((Position)state.getState())) == 0) {
			if(state.getCameFrom() == null)
				successors.add(new State<Position>(maze.getDown((Position)state.getState()), (State<Position>)state, cost));
			else if(!(state.getCameFrom().getState().equals(maze.getDown(state.getState()))))
				successors.add(new State<Position>(maze.getDown((Position)state.getState()), (State<Position>)state, cost));
		}
		//check if Back position is a possible state
		if(maze.getMazeValue(maze.getBack((Position)state.getState())) == 0) {
			if(state.getCameFrom() == null)
				successors.add(new State<Position>(maze.getBack((Position)state.getState()), (State<Position>)state, cost));
			else if(!(state.getCameFrom().getState().equals(maze.getBack(state.getState()))))
				successors.add(new State<Position>(maze.getBack((Position)state.getState()), (State<Position>)state, cost));
		}
		//check if Forward position is a possible state
		if(maze.getMazeValue(maze.getForward((Position)state.getState())) == 0) {
			if(state.getCameFrom() == null)
				successors.add(new State<Position>(maze.getForward((Position)state.getState()), (State<Position>)state, cost));
			else if(!(state.getCameFrom().getState().equals(maze.getForward(state.getState()))))
				successors.add(new State<Position>(maze.getForward((Position)state.getState()), (State<Position>)state, cost));
		}
		return successors;
	}

}
