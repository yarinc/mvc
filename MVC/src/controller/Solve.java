package controller;

import model.Model;
import view.View;

public class Solve extends AbstractCommand implements Command, Runnable {

	public Solve(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] parameters) {
		model.solveMaze(parameters);

	}

	@Override
	public void run() {
		model.solutionGenerator(parameters);
		
	}

}
