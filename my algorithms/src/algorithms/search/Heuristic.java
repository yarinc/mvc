package algorithms.search;

/**
 * An interface that represent a heuristic.
 * @author Yarin Cohen
 */
public interface Heuristic<T> {
	public double cost(State<T> n, State<T> state);
}
