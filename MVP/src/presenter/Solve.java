package presenter;

import model.Model;
import view.View;

/**
 * The Class Solve gets a maze name
 * and an algorithm and solves the maze.
 */
public class Solve extends AbstractCommand implements Command, Runnable {

	/**
	 * Instantiates a new Solve object.
	 * @param view the view
	 * @param model the model
	 */
	public Solve(View view, Model model) {
		super(view, model);
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.solveMaze(parameters, this);

	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		model.solutionGenerator(parameters);
		
	}

}
