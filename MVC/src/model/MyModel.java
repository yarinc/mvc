package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import algorithms.demo.Maze3dSearch;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.ManhattanDistance;
import algorithms.search.Solution;
import controller.Controller;
import controller.MazeGenerator;
import controller.Solve;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel implements Model {
	private Controller controller;
	HashMap<String, Maze3d> mazes;
	HashMap<String, Solution<Position>> solutions;
	HashMap<String,String> files;
	ArrayList<Thread> threads; 
	
	public void SetController(Controller controller) { 
		this.controller = controller;
		mazes = new HashMap<String,Maze3d>();
		solutions = new HashMap<String,Solution<Position>>();
		files = new HashMap<String,String>();
		threads = new ArrayList<Thread>();
	}

	@Override
	public void getDir(String string) {
		File file = new File(string);
		controller.fileListToView(file.list());
	}

	@Override
	public void CreateMaze(String[] parameters) {
		MazeGenerator mazeCreator = new MazeGenerator(controller.getView(), this);
		mazeCreator.setParameters(parameters);
		Thread mazeGen = new Thread(mazeCreator);
		threads.add(mazeGen);
		mazeGen.start();
	}

	@Override
	public void MazeGen(String[] parameters) {
		Position p = new Position(Integer.parseInt(parameters[1]),Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3]));
		Maze3d maze = new MyMaze3dGenerator().generate(p);
		mazes.put(parameters[0], maze);
		controller.readyToPrint("maze " + parameters[0] + " is ready.");
	}

	@Override
	public void GetMaze(String[] parameters) {
		controller.mazeToView(mazes.get(parameters[0]));
		
	}

	@Override
	public void PrintCrossMaze(String[] parameters) {
		Maze3d crossMaze = mazes.get(parameters[3]); 
		if(parameters[0].equals("X"))
			controller.crossMazeToView(crossMaze, crossMaze.getCrossSectionByX(Integer.parseInt(parameters[1])));
		else if(parameters[0].equals("Y"))
			controller.crossMazeToView(crossMaze, crossMaze.getCrossSectionByY(Integer.parseInt(parameters[1])));
		else if(parameters[0].equals("Z"))
			controller.crossMazeToView(crossMaze, crossMaze.getCrossSectionByZ(Integer.parseInt(parameters[1])));
	}

	@Override
	public void saveMaze(String[] parameters) {
		try {
			MyCompressorOutputStream out=new MyCompressorOutputStream(new FileOutputStream(parameters[1]));
			byte[] check = mazes.get(parameters[0]).toByteArray();
			try {
				out.write(check);
				out.flush();
				out.close();
			} catch (IOException e) {
				controller.readyToPrint("I/O error occurred.");
			}
		} catch (FileNotFoundException e) {
			controller.readyToPrint("The file doesn't exist.");
		}
		files.put(parameters[0], parameters[1]);
		controller.readyToPrint("The maze " + parameters[0] + " saved in: " + parameters[1] + ".");
	}

	@Override
	public void loadMaze(String[] parameters) {
		try {
			MyDecompressorInputStream in=new MyDecompressorInputStream(new FileInputStream(parameters[0]));
			byte b[]=new byte[36];
			try {
				in.read(b, 0, 36);
			} catch (IOException e1) {
				controller.readyToPrint("I/O error occurred");
			}
			int sum = 0;
			while(sum <= 36) {
				for(int i=1;i<b.length - 1;i=i+2) 
					sum += b[i];
			}
			int counter = 0;
			byte length[] = new byte[sum];
			for(int i=1;i<b.length - 1;i=i+2){
				for(int loop=0;loop<b[i];loop++) {
					length[counter] = b[i-1];
					       counter++;
				}
			}
			ByteBuffer buffer = ByteBuffer.wrap(length, 24, 12);
			int mazeSize = buffer.getInt()*buffer.getInt()*buffer.getInt();
			in=new MyDecompressorInputStream(new FileInputStream(parameters[0]));
			byte fullMaze[] = new byte[36+mazeSize];
			try {
				in.read(fullMaze);
				in.close();
				Maze3d loaded=new Maze3d(fullMaze);
				mazes.put(parameters[1], loaded);
				files.put(parameters[1], parameters[0]);
				controller.readyToPrint("The maze from " + parameters[0] + " loaded to " + parameters[1] +".");
			} catch (IOException e) {
				controller.readyToPrint("I/O error occurred.");
			}
		} catch (FileNotFoundException e) {
			controller.readyToPrint("The file doesn't exist.");
		}
	}

	@Override
	public void mazeSizeRAM(String[] parameters) {
		try {
			int size = ((mazes.get(parameters[0])).toByteArray()).length;
			controller.readyToPrint("Maze size is " + size + " bytes.");
		} catch (NullPointerException e) { 
			controller.readyToPrint("The maze doesn't exist.");
		}
	}

	@Override
	public void mazeSizeFile(String[] parameters) {
		try {
			File file = new File(files.get(parameters[0]));
			controller.readyToPrint("The file size is " + file.length() + " bytes.");
		} catch (NullPointerException e) { 
			controller.readyToPrint("Invalid maze name.");
		}
	}

	@Override
	public void solveMaze(String[] parameters) {
		Solve solution = new Solve(controller.getView(), this);
		solution.setParameters(parameters);
		Thread solutionGen = new Thread(solution);
		threads.add(solutionGen);
		solutionGen.start();
	}

	@Override
	public void solutionGenerator(String[] parameters) {
		try {
			if(parameters[1].equals("BFS")) { 
				BFS<Position> bfs = new BFS<Position>();
				Maze3dSearch search = new Maze3dSearch(mazes.get(parameters[0]));
				Solution<Position> solutionByBFS = bfs.search(search);
				solutions.put(parameters[0], solutionByBFS);
				controller.readyToPrint("Solution for " + parameters[0] + " is ready.");
			}
			else if(parameters[1].equals("AStar")) { 
				ManhattanDistance manhattan = new ManhattanDistance();
				Maze3dSearch search = new Maze3dSearch(mazes.get(parameters[0]));
				AStar<Position> aStar = new AStar<>(manhattan);
				Solution<Position> solutionByManhattan = aStar.search(search);
				solutions.put(parameters[0], solutionByManhattan);
				controller.readyToPrint("Solution for " + parameters[0] + " is ready.");
			}
			else
				controller.readyToPrint("Algorithm not found.");
		} catch(NullPointerException e) { 
			controller.readyToPrint("Invalid maze name.");
		}
	}

	@Override
	public void getSolution(String[] parameters) {
		try { 
			controller.solutionToView(solutions.get(parameters[0]));
		} catch (NullPointerException e) {
			controller.readyToPrint("invalid maze name.");
		}
	}

	@Override
	public void waitForThreads() {
		for(Thread t : threads) { 
			if(t.isAlive())
				try {
					t.join();
				} catch (InterruptedException e) {
					controller.readyToPrint("Fatal Error.");
				}
		}
		controller.readyToPrint("Goodbye.");
	}
}