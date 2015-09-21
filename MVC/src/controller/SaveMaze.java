package controller;

import model.Model;
import view.View;

public class SaveMaze extends AbstractCommand implements Command {

	public SaveMaze(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] parameters) {
		model.saveMaze(parameters);
	}

}
