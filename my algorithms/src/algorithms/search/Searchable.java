package algorithms.search;

import java.util.ArrayList;

/**
 * An interface that represent a Searchable object.
 * @author Yarin Cohen
 */
public interface Searchable<T> {
	public ArrayList<State<T>> getAllPossibleStates(State<T> state, double cost);
	public State<T> getGoalState();
	public State<T> getStartState();

}
