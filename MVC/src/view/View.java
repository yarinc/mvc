package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;

public interface View {
	public void start();
	void PrintString(String[] string);
	void MazeReady(String string);
	public void PrintMaze(Maze3d maze);
	public void TransferMap(HashMap<String, Command> map);
}
