package controller;

import model.Model;
import view.View;

public class FileSize extends AbstractCommand implements Command {

	public FileSize(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] parameters) {
		model.mazeSizeFile(parameters);

	}

}
