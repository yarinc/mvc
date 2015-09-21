package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface View {
	public void start();
	void PrintStringArray(String[] string);
	void printString(String string);
	public void PrintMaze(Maze3d maze);
	public void PrintCrossMaze(Maze3d maze, int[][] maze2d);
	public void solutionToPrint(Solution<Position> solution);
	public void inputToController(String line);
}
