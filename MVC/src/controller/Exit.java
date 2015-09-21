package controller;

import model.Model;
import view.View;

public class Exit extends AbstractCommand implements Command {

	public Exit(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] parameters) {
		model.waitForThreads();
	}

}
