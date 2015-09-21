package controller;


import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

/**
 * The Class MyController implements Controller interface 
 * and act as mediator between the view and the model.
 */
public class MyController implements Controller {
	
	private Model model; 
	private View view;
	private HashMap<String,Command> map;
	
	/**
	 * Instantiates a new MyController object.
	 * @param model the model
	 * @param view the view
	 */
	public MyController(Model model, View view) { 
		this.model = model; 
		this.view = view;
		//Initiate HashMap with all commands
		map = new HashMap<String,Command>();
		map.put("dir", new Dir(view, model));
		map.put("generate", new MazeGenerator(view,model));
		map.put("display", new Display(view,model));
		map.put("cross", new DisplayCross(view,model));
		map.put("save", new SaveMaze(view,model));
		map.put("load", new LoadMaze(view,model));
		map.put("maze", new MazeSize(view,model));
		map.put("file", new FileSize(view,model));
		map.put("solve", new Solve(view,model));
		map.put("solution", new DisplaySolution(view,model));
		map.put("exit", new Exit(view,model));
	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#fileListToView(java.lang.String[])
	 */
	@Override
	public void fileListToView(String[] list) {
		view.PrintStringArray(list);
	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#getView()
	 */
	public View getView() { 
		return view;
	}

	/* (non-Javadoc)
	 * @see controller.Controller#readyToPrint(java.lang.String)
	 */
	@Override
	public void readyToPrint(String string) {
		view.printString(string);
	}

	/* (non-Javadoc)
	 * @see controller.Controller#mazeToView(algorithms.mazeGenerators.Maze3d)
	 */
	@Override
	public void mazeToView(Maze3d maze) {
		view.PrintMaze(maze);
		
	}

	/* (non-Javadoc)
	 * @see controller.Controller#crossMazeToView(algorithms.mazeGenerators.Maze3d, int[][])
	 */
	@Override
	public void crossMazeToView(Maze3d maze, int[][] maze2d) {
		view.PrintCrossMaze(maze, maze2d);
	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#solutionToView(algorithms.search.Solution)
	 */
	@Override
	public void solutionToView(Solution<Position> solution) {
		view.solutionToPrint(solution);
		
	}

	/* (non-Javadoc)
	 * @see controller.Controller#activateCommand(java.lang.String, java.lang.String[])
	 */
	@Override
	public void activateCommand(String string, String[] parameters) {
		try { 
			map.get(string).doCommand(parameters);
		} catch(NullPointerException e)	{
			view.printString("Invalid object");
		}
	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#manipulateInput(java.lang.String)
	 */
	@Override
	public void manipulateInput(String line) {
		//If the input is not 'exit' and it contain one word than command not found.
		if((line.split(" ").length <= 1) && (!line.equals("exit")))
			view.printString("Command not found");
		else {
			//Separate input by space
			String command[] = line.split(" ");
			//Validate the input by 'validateCommand' method.
			switch (command[0]) { 
			case "dir" : validateCommand(line, "dir", 4, 1);
						break;
			case "generate" : validateCommand(line, "generate", 17, 4);
						break;
			case "save" : validateCommand(line, "save", 10, 2);
						break;
			case "load" : validateCommand(line, "load", 10, 2);
						break;
			case "maze" : validateCommand(line, "maze", 10, 1);
						break;
			case "file" : validateCommand(line, "file", 10, 1);
						break;
			case "solve" : validateCommand(line, "solve", 6, 2);
						break;
			case "display" : displayCommands(line, command);
						break;
			case "exit" : validateCommand(line, "exit", 4, 1);
						break;
			default : view.printString("Command not found");
			}
		}
	}
	
	/**
	 * Validate command checks that the input is a defined command.
	 * @param input the input from the user
	 * @param command the command identifier on the HashMap
	 * @param commandLength the command length
	 * @param numOfParameters the number of parameters
	 */
	public void validateCommand(String input, String command, int commandLength, int numOfParameters){
		String[] parameters;
		//If input starts with the command identifier.
		if((input.startsWith(command) || (input.startsWith("display " + command)))) {
			//Extract the parameters and validate their number.
			parameters = (input.substring(commandLength)).split(" ");
			//If parameter number is OK - activate the desired command.
			if(parameters.length == numOfParameters)
				activateCommand(command, parameters);
			//Else - send error.
			else
				view.printString("Parameters error");
		}
	}
	
	/**
	 * Display commands used to separate between all the 'display'
	 * commands and decide which command to initiate.
	 * @param input the input from the user
	 * @param splittedCommand the splitted command
	 */
	public void displayCommands(String input, String[] splittedCommand) {
		//If the second word is 'cross' and it's length is more then 2 - validate the display command.
		if((splittedCommand[1].equals("cross")) && (splittedCommand.length > 2)) 
			validateCommand(input, "cross", 25, 4);
		//If the length is 2 - validate the display command.
		else if(splittedCommand.length == 2)
			validateCommand(input, "display", 8, 1);
		//If the second word is 'solution' and it's length is 3 - validate the display command.
		else if ((splittedCommand[1].equals("solution")) && (splittedCommand.length == 3)) 
			validateCommand(input, "solution", 17, 1);
		//Else - print command not found.
		else
			view.printString("Command not found.");
	}
}
