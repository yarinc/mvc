package controller;

import model.Model;
import view.View;

public class MazeSize extends AbstractCommand implements Command {

	public MazeSize(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] parameters) {
		model.mazeSizeRAM(parameters);

	}

}
