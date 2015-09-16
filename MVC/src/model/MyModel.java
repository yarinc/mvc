package model;

import java.io.File;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import controller.Controller;
import controller.MazeGenerator;

public class MyModel implements Model {
	private Controller controller;
	HashMap<String, Maze3d> mazes;
	
	public void SetController(Controller controller) { 
		this.controller = controller;
		mazes = new HashMap<String,Maze3d>();
	}

	@Override
	public void getDir(String string) {
		File file = new File(string);
		controller.fileListToView(file.list());
	}

	@Override
	public void CreateMaze() {
		MazeGenerator create = new MazeGenerator(controller.getView(), this);
		create.start();
	}

	@Override
	public void MazeGen(String[] parameters) {
		Position p = new Position(Integer.parseInt(parameters[1]),Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3]));
		Maze3d maze = new MyMaze3dGenerator().generate(p);
		mazes.put(parameters[0], maze);
		controller.Ready("maze " + parameters[0] +"is ready");
	}

	@Override
	public void GetMaze(String[] parameters) {
		controller.MazeToView(mazes.get(parameters[0]));
		
	}
}
