package controller;

import algorithms.mazeGenerators.Maze3d;
import view.View;

public interface Controller {

	void fileListToView(String[] list);

	View getView();

	void Ready(String string);

	void MazeToView(Maze3d maze3d);
	
}
