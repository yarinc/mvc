package controller;

import model.Model;
import view.View;

public class Dir extends AbstractCommand implements Command {
	
	public Dir(View view, Model model) {
		super(view, model);
	}
	@Override
	public void doCommand(String[] parameters) {
		model.getDir(parameters[0]);
	}
}
