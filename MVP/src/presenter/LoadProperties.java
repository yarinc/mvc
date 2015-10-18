package presenter;

import model.Model;
import view.View;

public class LoadProperties extends AbstractCommand implements Command {

	public LoadProperties(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] parameters) {	
		model.setProperties(parameters);
	}

}
