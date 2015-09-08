package algorithms.search;

/**
 * Class that represent a state, which contain the following:
 * state - A location.
 * cost -  Cost of the location.
 * cameFrom - State that we came from to this state.
 * The class implements Comparable in order to override the comparTo method.
 */
public class State<T> implements Comparable<State<T>> {
    private T state; 
    private double cost;
    private State<T> cameFrom;
    
    /**
     * Constructor that gets a state and initialize its state attribute.
     * @param state the given state to initialize.
     */
    public State(T state) {   
        this.state = state;
    }
    /**
     * Constructor that gets a state, a cameFrom and a cost
     * then initialize its relevant attribute.
     * @param state The state to initialize.
     * @param cameFrom The cameFrom to initialize.
     * @param cost The cost to initialize.
     */
    public State(T state, State<T> cameFrom ,double cost) {    // CTOR    
        this.state = state;
        this.cameFrom = cameFrom;
        this.cost = cameFrom.getCost() + cost;
    }
    /**
     * Copy constructor for the class.
     * @param copyState Which object to copy.
     */
    public State(State<T> copyState) { 
        this.state = copyState.state;
        this.cameFrom = copyState.cameFrom;
        this.cost = copyState.cost;
    }
    /**
     * Overriding object's equal method. 
     * @param obj The object to check if he is equal.
     * @return boolean True if the states are equal, else - false.
     */
	@Override
    public boolean equals(Object obj){
		return state.equals(((State<?>)obj).state);
    }
	/**
	 * Getter to the state attribute.
	 * @return The state.
	 */
	public T getState() {
		return state;
	}
	/**
	 * Setter to the state attribute.
	 * @param state The state to set.
	 */
	public void setState(T state) {
		this.state = state;
	}
	/**
	 * Getter to the cost attribute.
	 * @return The cost value.
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * Setter to the cost attribute.
	 * @param cost The cost to set.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * Getter to the attribute cameFrome.
	 * @return The object cameFrom.
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}
	/**
	 * Setter to the cameFrom attribute.
	 * @param cameFrom The object we came from.
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	/**
	 * Overriding comparTo allow the user to compare
	 * two State object by the cost attribute.
	 * @param secondState The compared State.
	 * @return The bigger State.
	 */
	@Override
	public int compareTo(State<T> secondState) {
		// TODO Auto-generated method stub
		if (this.getCost()-secondState.getCost() == 0)
			return 0;
		else if(this.getCost()-secondState.getCost() > 0)
			return 1;
		else 
			return -1;
	} 

}
