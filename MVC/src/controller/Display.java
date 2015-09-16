package controller;

import model.Model;
import view.View;

public class Display extends AbstractCommand implements Command {

	public Display(View view, Model model) {
		super(view, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String[] parameters) {
		model.GetMaze(parameters);

	}
}
