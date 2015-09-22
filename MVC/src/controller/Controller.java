package controller;

import algorithms.mazeGenerators.Maze3d;
import view.View;

/**
 * The Interface Controller.
 */
public interface Controller {
	
	/**
	 * Sending file list to the view.
	 * @param list the list of file and folders
	 */
	void fileListToView(String[] list);
	
	/**
	 * Gets the view.
	 * @return the view
	 */
	View getView();
	
	/**
	 * Sending a string to the view.
	 * @param string the string
	 */
	void readyToPrint(String string);
	
	/**
	 * Sending a 2D maze to the view.
	 * @param crossMaze the 3D maze
	 * @param maze2d the 2D maze
	 */
	void crossMazeToView(Maze3d crossMaze, int[][] maze2d);
	
	/**
	 * Activating the relevant command.
	 * @param string the input from user
	 * @param parameters the parameters
	 */
	void activateCommand(String string, String[] parameters);
	
	/**
	 * Manipulate input to decide which command to issue.
	 * @param line the input 
	 */
	void manipulateInput(String line);
	
}
