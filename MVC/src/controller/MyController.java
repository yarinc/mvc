package controller;


import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

public class MyController implements Controller {
	private Model model; 
	private View view;
	
	public MyController(Model model, View view) { 
		this.model = model; 
		this.view = view;
		HashMap<String, Command> map = new HashMap<String, Command>();
		map.put("dir", (new Dir(view, model)));
		map.put("generate 3d maze", new MazeGenerator(view,model));
		view.TransferMap(map);
	}

	@Override
	public void fileListToView(String[] list) {
		view.PrintString(list);
	}
	public View getView() { 
		return view;
	}

	@Override
	public void Ready(String string) {
		view.MazeReady(string);
	}

	@Override
	public void MazeToView(Maze3d maze) {
		view.PrintMaze(maze);
		
	}
}
