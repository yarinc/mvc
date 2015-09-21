package controller;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import view.View;

public interface Controller {

	void fileListToView(String[] list);
	View getView();
	void readyToPrint(String string);
	void mazeToView(Maze3d maze3d);
	void crossMazeToView(Maze3d crossMaze, int[][] maze2d);
	void solutionToView(Solution<Position> solution);
	void activateCommand(String string, String[] parameters);
	void manipulateInput(String line);
	
}
