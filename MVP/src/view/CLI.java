package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;

/**
 * The Class CLI starts in a new thread a CLI for the user.
 */
public class CLI extends Observable implements View, Runnable {
	
	private BufferedReader in; 
	private PrintWriter out;
	
	/**
	 * Gets in.
	 * @return the in
	 */
	public BufferedReader getIn() {
		return in;
	}

	/**
	 * Gets out.
	 * @return the out
	 */
	public PrintWriter getOut() {
		return out;
	}
	
	/**
	 * Instantiates a new CLI object.
	 * @param in the in object
	 * @param out the out object
	 */
	public CLI(BufferedReader in, PrintWriter out) { 
		this.in = in;
		this.out = out;
	}
	
	/**
	 * Start the new thread.
	 */
	public void start() { 
		new Thread(this).start();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		String line;
		try {
			//Getting the input from the BufferdReader as long as it's not 'exit'.
			while (!(line = in.readLine()).equals("exit")) {
				//sending the input to the presenter.
				this.setChanged();
				this.notifyObservers(line);
			}
			//'exit' has entered - sending it to the presenter.
			this.setChanged();
			this.notifyObservers(line);
		} catch (IOException e) {
			//In case an exception throws - send relevant message.
			this.setChanged();
			this.notifyObservers("I/O error occurred.");
		}
	}

	@Override
	public void PrintStringArray(String[] string) {
		for(String s:string)
			out.println(s);
		out.flush();
	}

	@Override
	public void printString(String string) {
		out.println(string); 
		out.flush();
	}

	@Override
	public void PrintCrossMaze(int[][] maze2d) {
		int i,j;
		for(i=0;i<maze2d.length;i++){
			out.print("[");
			for(j=0;j<maze2d[i].length;j++){
					out.print(maze2d[i][j]);
					if(j != maze2d[i].length - 1)
						out.print(", ");
				}
			out.println("]");
		}
	out.print("\n");
	out.flush();
	}
}
