package controller;

import model.Model;
import view.View;

public class LoadMaze extends AbstractCommand implements Command {

	public LoadMaze(View view, Model model) {
		super(view, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] parameters) {
		model.loadMaze(parameters);

	}

}
