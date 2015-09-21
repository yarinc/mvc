package model;

public interface Model {

	void getDir(String string);
	void CreateMaze(String[] parameters);
	void MazeGen(String[] parameters);
	void GetMaze(String[] parameters);
	void PrintCrossMaze(String[] parameters);
	void saveMaze(String[] parameters);
	void loadMaze(String[] parameters);
	void mazeSizeRAM(String[] parameters);
	void mazeSizeFile(String[] parameters);
	void solveMaze(String[] parameters);
	void solutionGenerator(String[] parameters);
	void getSolution(String[] parameters);
	void waitForThreads();
}
