package controller;


import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

public class MyController implements Controller {
	private Model model; 
	private View view;
	private HashMap<String,Command> map;
	
	public MyController(Model model, View view) { 
		this.model = model; 
		this.view = view;
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
	
	@Override
	public void fileListToView(String[] list) {
		view.PrintStringArray(list);
	}
	public View getView() { 
		return view;
	}

	@Override
	public void readyToPrint(String string) {
		view.printString(string);
	}

	@Override
	public void mazeToView(Maze3d maze) {
		view.PrintMaze(maze);
		
	}

	@Override
	public void crossMazeToView(Maze3d maze, int[][] maze2d) {
		view.PrintCrossMaze(maze, maze2d);
	}
	@Override
	public void solutionToView(Solution<Position> solution) {
		view.solutionToPrint(solution);
		
	}

	@Override
	public void activateCommand(String string, String[] parameters) {
		try { 
			map.get(string).doCommand(parameters);
		} catch(NullPointerException e)	{
			view.printString("Invalid object");
		}
	}
	@Override
	public void manipulateInput(String line) {
		if((line.split(" ").length <= 1) && (!line.equals("exit")))
			view.printString("Command not found");
		else {
			String command[] = line.split(" ");
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
	public void validateCommand(String input, String command, int commandLength, int numOfParameters){
		String[] parameters;
		if(input.startsWith(command)) {
			parameters = (input.substring(commandLength)).split(" ");
			if(parameters.length == numOfParameters)
				activateCommand(command, parameters);
			else
				view.printString("Parameters error");
		}
	}
	public void displayCommands(String input, String[] splitedCommand) {
		if((splitedCommand[1].equals("cross")) && (splitedCommand.length > 2)) 
			validateDisplayCommand(input, "cross", 25, 4);
		else if(splitedCommand.length == 2)
			validateDisplayCommand(input, "display", 8, 1);
		else if ((splitedCommand[1].equals("solution")) && (splitedCommand.length == 3)) 
			validateDisplayCommand(input, "solution", 17, 1);
	}
	public void validateDisplayCommand(String input, String command, int commandLength, int numOfParameters){
		String[] parameters;
		if((input.startsWith(command)|| (input.startsWith("display " + command)))) {
			parameters = (input.substring(commandLength)).split(" ");
			if(parameters.length == numOfParameters)
				activateCommand(command, parameters);
			else
				view.printString("Parameters error");
		}
	}
}
