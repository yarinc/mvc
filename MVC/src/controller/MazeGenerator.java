package controller;

import model.Model;
import view.View;

public class MazeGenerator extends AbstractCommand implements Command, Runnable {

	public MazeGenerator(View view, Model model) {
		super(view, model);
		
	}

	@Override
	public void doCommand(String[] parameters) {
		model.CreateMaze(parameters);
	}

	@Override
	public void run() {
		model.MazeGen(parameters);
	}
}
