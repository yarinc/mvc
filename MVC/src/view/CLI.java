package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

public class CLI implements Runnable {
	private BufferedReader in; 
	private PrintWriter out;
	private HashMap<String,Command> map;
	
	public CLI(BufferedReader in, PrintWriter out,HashMap<String,Command> map) { 
		this.in = in;
		this.out = out;
		this.map = map;
	}
	public void start() throws IOException  { 
		String line; 
		while (!(line = in.readLine()).equals("exit")) { 
			if(map.containsValue(line)) 
				(map.get(line)).doCommand();
			else
				out.println("Command not Exists");
		}
		in.close();
		out.close();
	}
	@Override
	public void run() {
		try {
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
