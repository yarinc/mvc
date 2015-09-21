package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class CLI implements Runnable {
	private BufferedReader in; 
	private PrintWriter out;
	private View view;
	
	public BufferedReader getIn() {
		return in;
	}

	public PrintWriter getOut() {
		return out;
	}
	public void setView(View view) { 
		this.view = view;
	}
	public CLI(BufferedReader in, PrintWriter out) { 
		this.in = in;
		this.out = out;
	}
	public void start() { 
		new Thread(this).start();
	}
	@Override
	public void run() {
		String line;
		try {
			while (!(line = in.readLine()).equals("exit")) {
				view.inputToController(line);
			}
			view.inputToController(line);
		} catch (IOException e) {
			view.printString("I/O error occurred.");
		}
	}
}
