package view;

import algorithms.mazeGenerators.Maze3d;
import controller.Controller;

/**
 * The Class MyView communicate with the user.
 */
public class MyView implements View {

	private Controller controller;
	private CLI cli;
	
	/**
	 * Instantiates a new MyView object.
	 * @param cli the CLI object
	 */
	public MyView(CLI cli) { 
		this.cli = cli;
	}
	
	/**
	 * Sets the controller.
	 * @param controller the controller
	 */
	public void SetController(Controller controller) { 
		this.controller = controller;
	}
	
	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		 cli.start();
	}

	/* (non-Javadoc)
	 * @see view.View#PrintStringArray(java.lang.String[])
	 */
	@Override
	public void PrintStringArray(String[] string) {
		for(String s:string)
			cli.getOut().println(s);
		cli.getOut().flush();
	}
	
	/* (non-Javadoc)
	 * @see view.View#printString(java.lang.String)
	 */
	@Override
	public void printString(String string) {
		cli.getOut().println(string); 
		cli.getOut().flush();	
	}
	
	/* (non-Javadoc)
	 * @see view.View#PrintCrossMaze(algorithms.mazeGenerators.Maze3d, int[][])
	 */
	@Override
	public void PrintCrossMaze(Maze3d maze, int[][] maze2d) {
		maze.print2d(maze2d);
	}
	
	/* (non-Javadoc)
	 * @see view.View#inputToController(java.lang.String)
	 */
	@Override
	public void inputToController(String line) {
		controller.manipulateInput(line);		
	}
}
