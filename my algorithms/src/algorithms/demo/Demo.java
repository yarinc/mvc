package algorithms.demo;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStar;
import algorithms.search.AirDistance;
import algorithms.search.BFS;
import algorithms.search.ManhattanDistance;
import algorithms.search.Solution;
/**
 * Class to demonstrate the genetate and solve algorithms in action
 * @author Yarin Cohen
 */
public class Demo {
	/**
	 * Method using the generate and solve algorithms
	 */
	public void run() { 
		MyMaze3dGenerator mazeGen = new MyMaze3dGenerator();
		Maze3d maze=mazeGen.generate(new Position(10,10,10));
		maze.printMaze();
		//Solve with BFS 
		BFS<Position> bfs = new BFS<Position>();
		Maze3dSearch search = new Maze3dSearch(maze);
		Solution<Position> solutionByBFS = bfs.search(search);
		System.out.println("BFS solution:");
		solutionByBFS.print();
		//Create Heuristics
		ManhattanDistance manhattan = new ManhattanDistance();
		AirDistance air = new AirDistance();
		//Solve with AStar using air distance heuristic
		AStar<Position> aStar = new AStar<>(air);
		Solution<Position> solutionByAir = aStar.search(search);
		System.out.println("AStar with air distance heuristic solution:");
		solutionByAir.print();
		//Solve with AStar using Manhattan distance heuristic
		aStar.setHeuristic(manhattan);
		Solution<Position> solutionByManhattan = aStar.search(search);
		System.out.println("AStar with manhattan distance heuristic solution:");
		solutionByManhattan.print();
			
	}
	/**
	 * Main that test the algorithms
	 * @param args The desired arguments
	 */
	public static void main(String[] args) {
		Demo test = new Demo();
		test.run();
	}
}

