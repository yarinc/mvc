package algorithms.mazeGenerators;

/**
 * Abstract class that implements Maze3dGenerator. 
 * The only implemented method is measureAlgorithmTime.
 * @author Yarin Cohen
 */

public abstract class Maze3dAbstract implements Maze3dGenerator {
	/**
	 * Calculate the running time of the creation algorithm of the maze.
	 * @param size of the requested maze.
	 * @return String holds the time method took to run.
	 */
	//Method to calculate the algorithm run time
	public String measureAlgorithmTime(Position size) {
		//begin time and call generate method
		long startTime = System.currentTimeMillis();
		generate(size);
		//end time
		long endTime = System.currentTimeMillis();
		//converting to String type and return total runtime
		String time = Long.toString(endTime - startTime) + " Milliseconds";
		return (time);
	}
}
