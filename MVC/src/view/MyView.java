package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;
import controller.Controller;

public class MyView implements View {
	private Controller controller;
	private CLI cli;
	
	public void SetController(Controller controller) { 
		this.controller = controller;
	}

	@Override
	public void start() {
		cli.start();	
	}

	@Override
	public void PrintString(String[] string) {
		for(String s:string)
			System.out.println(s);
	}
	public void MazeReady(String string) { 
		System.out.println(string);
	}

	@Override
	public void PrintMaze(Maze3d maze) {
		maze.printMaze();	
	}

	@Override
	public void TransferMap(HashMap<String, Command> map) {
		cli = new CLI(new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(System.out),map);
	}
}
