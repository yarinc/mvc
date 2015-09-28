package view;

import java.util.Observable;


/**
 * The Class MyView communicate with the user.
 */
public class MyCLIView extends Observable implements View {

	private CLI cli;
	
	/**
	 * Instantiates a new MyView object.
	 * @param cli the CLI object
	 */
	public MyCLIView(CLI cli) { 
		this.cli = cli;
	}
	
	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		 cli.start();
	}

	@Override
	public void PrintStringArray(String[] string) {
		for(String s:string)
			cli.getOut().println(s);
		cli.getOut().flush();
	}

	@Override
	public void printString(String string) {
		cli.getOut().println(string); 
		cli.getOut().flush();
	}

	@Override
	public void PrintCrossMaze(int[][] maze2d) {
		int i,j;
		for(i=0;i<maze2d.length;i++){
			cli.getOut().print("[");
			for(j=0;j<maze2d[i].length;j++){
				cli.getOut().print(maze2d[i][j]);
					if(j != maze2d[i].length - 1)
						cli.getOut().print(", ");
				}
			cli.getOut().println("]");
		}
		cli.getOut().print("\n");
		cli.getOut().flush();
	}
	public void inputToPresenter(String line) {
		this.setChanged();
		this.notifyObservers(line);
	}
}
