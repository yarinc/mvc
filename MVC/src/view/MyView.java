package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Controller;

public class MyView implements View {
	private Controller controller;
	private CLI cli;
	
	public MyView(CLI cli) { 
		this.cli = cli;
	}
	
	public void SetController(Controller controller) { 
		this.controller = controller;
	}
	@Override
	public void start() {
		 cli.start();
	}

	@Override
	public void PrintStringArray(String[] string) {
		for(String s:string)
			cli.getOut().println(s);
		cli.getOut().flush();
	}
	@Override
	public void printString(String string) {
		cli.getOut().println(string); 
		cli.getOut().flush();
		
	}
	@Override
	public void PrintMaze(Maze3d maze) {
		maze.printMaze();
		cli.getOut().flush();
	}
	@Override
	public void PrintCrossMaze(Maze3d maze, int[][] maze2d) {
		maze.print2d(maze2d);
	}
	@Override
	public void solutionToPrint(Solution<Position> solution) {
		solution.print();
	}
	@Override
	public void inputToController(String line) {
		controller.manipulateInput(line);		
	}
}
