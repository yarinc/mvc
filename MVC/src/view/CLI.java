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
	public void start() { 
		String line;
		String input;
		String[] parameters;
		try {
			while (!(line = in.readLine()).equals("exit")) {
				input = extractCommand(line);
				if(!(input.equals("Command not found"))) {
					parameters = extratParameters(line, input);
					(map.get(input)).doCommand(parameters);
				}
				else
					out.println("Command not Exists");
			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String[] extratParameters(String line, String input) {
		String[] parameters;
		parameters = (line.substring(input.length()+1)).split(" ");
		return parameters;
	}
	@Override
	public void run() {
		start();
	}
	private String extractCommand(String input) {
		String[] splited = input.split(" ");
		int i;
		String command = new String();
		for(i=0;i<splited.length;i++) { 
			command = command + splited[i];
			if(map.containsKey(command)) { 
				return command; 
			}
			else
				command = command + " ";
		}
		return "Command not found"; 
		
	}
}
