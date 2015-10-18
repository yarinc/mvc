package presenter;

import model.Model;
import view.View;

public class SolveFrom extends AbstractCommand implements Command {

	public SolveFrom(View view, Model model) {
		super(view, model);
	}

	@Override
	public void doCommand(String[] parameters) {
		model.solveFromPoint(parameters);
	}


}
