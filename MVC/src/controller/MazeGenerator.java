package controller;

import model.Model;
import view.View;

/**
 * The Class MazeGenerator generate a new maze on a different thread.
 */
public class MazeGenerator extends AbstractCommand implements Command, Runnable {

	/**
	 * Instantiates a new MazeGenerator object.
	 * @param view the view
	 * @param model the model
	 */
	public MazeGenerator(View view, Model model) {
		super(view, model);
		
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] parameters) {
		model.CreateMaze(parameters);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		model.MazeGen(parameters);
	}
}
