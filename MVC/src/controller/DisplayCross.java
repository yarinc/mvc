package controller;

import model.Model;
import view.View;

public class DisplayCross extends AbstractCommand implements Command {

	public DisplayCross(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] parameters) {
		model.PrintCrossMaze(parameters);
	}

}
