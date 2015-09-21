package controller;

import model.Model;
import view.View;

public class DisplaySolution extends AbstractCommand implements Command {

	public DisplaySolution(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] parameters) {
		model.getSolution(parameters);

	}

}
