package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * The Interface View.
 */
public interface View {
	
	/**
	 * Start a new thread for the CLI.
	 */
	public void start();
	
	/**
	 * Prints the given string array.
	 * @param string the string
	 */
	void PrintStringArray(String[] string);
	
	/**
	 * Prints the given string.
	 * @param string the string
	 */
	void printString(String string);
	
	void printMaze(Maze3d maze);
	/**
	 * Prints the given 3D maze.
	 * @param maze the 3D maze
	 * @param maze2d the 2D maze
	 */
	public void PrintCrossMaze(int[][] maze2d);
	
	/**
	 * Sending input to the presenter.
	 * @param line the input
	 */
	public void inputToPresenter(String line);

	public void handleSolution(Solution<Position> obj);
}
