package model;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.demo.Maze3dSearch;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStar;
import algorithms.search.AirDistance;
import algorithms.search.BFS;
import algorithms.search.ManhattanDistance;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import presenter.MazeGenerator;
import presenter.Properties;
import presenter.Solve;

/**
 * The Class MyModel implements a Model interface
 * to do all the calculation and data manipulations .
 */
public class MyModel extends Observable implements Model {

	private HashMap<String, Maze3d> mazes;
	private HashMap<String, Solution<Position>> solutions;
	private HashMap<String,String> files;
	private HashMap<Maze3d,Solution<Position>> cachedMazes;
	private ArrayList<Thread> threads;
	private ExecutorService executor;
	private Properties properties;

	
	/**
	 * Instantiates a new MyModel object.
	 */
	@SuppressWarnings("unchecked")
	public MyModel() {
		mazes = new HashMap<String,Maze3d>();
		solutions = new HashMap<String,Solution<Position>>();
		files = new HashMap<String,String>();
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("resource\\properties.xml")));
			this.properties = (Properties)decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		try {
				FileInputStream fis=new FileInputStream(properties.getCacheFileLocation());
				cachedMazes = new HashMap<Maze3d,Solution<Position>>();
				GZIPInputStream gzis=new GZIPInputStream(fis);
				ObjectInputStream in=new ObjectInputStream(gzis);
				cachedMazes=(HashMap<Maze3d,Solution<Position>>)in.readObject();
				in.close();
		} catch (IOException | ClassNotFoundException e) { 
			cachedMazes = new HashMap<Maze3d,Solution<Position>> ();
		}

		threads = new ArrayList<Thread>();
		executor = Executors.newFixedThreadPool(properties.getNumberOfThreads());
	}
	
	public Properties getProperties() { 
		return properties;
	}

	/* (non-Javadoc)
	 * @see model.Model#getDir(java.lang.String)
	 */
	@Override
	public void getDir(String string) {
		File file = new File(string);
		this.setChanged();
		this.notifyObservers(file.list());
	}

	/* (non-Javadoc)
	 * @see model.Model#CreateMaze(java.lang.String[])
	 */
	@Override
	public void CreateMaze(String[] parameters, MazeGenerator mazeGenerator) {
		//Create object require to generate a maze on a different thread.
		mazeGenerator.setParameters(parameters);
		//Pack the object with Thread class, add the object to the threads list, and activate the thread.
		executor.submit(mazeGenerator);
	}

	/* (non-Javadoc)
	 * @see model.Model#MazeGen(java.lang.String[])
	 */
	@Override
	public Maze3d MazeGen(String[] parameters) {
		//Create Position object to mark the maze size, and create it.
		Position p = new Position(Integer.parseInt(parameters[1]),Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3]));
		Maze3d maze = new MyMaze3dGenerator().generate(p);
		//Add maze to the mazes HashMap.
		mazes.put(parameters[0], maze);
		//Send a relevant message.
		this.setChanged();
		this.notifyObservers("maze " + parameters[0] + " is ready.");
		return maze;
	}

	/* (non-Javadoc)
	 * @see model.Model#GetMaze(java.lang.String[])
	 */
	@Override
	public void GetMaze(String[] parameters) {
		this.setChanged();
		this.notifyObservers(mazes.get(parameters[0]));
	}

	/* (non-Javadoc)
	 * @see model.Model#PrintCrossMaze(java.lang.String[])
	 */
	@Override
	public void PrintCrossMaze(String[] parameters) {
		//Extract the cross section.
		Maze3d crossMaze = mazes.get(parameters[3]); 
		//Send the Cross maze to print depends on the cross section.
		try { 
			if(parameters[0].equals("X")) { 
				this.setChanged();
				this.notifyObservers(crossMaze.getCrossSectionByX(Integer.parseInt(parameters[1])));
			}
			else if(parameters[0].equals("Y")) {
				this.setChanged();
				this.notifyObservers(crossMaze.getCrossSectionByY(Integer.parseInt(parameters[1])));
			}
			else if(parameters[0].equals("Z")) { 
				this.setChanged();
				this.notifyObservers(crossMaze.getCrossSectionByZ(Integer.parseInt(parameters[1])));
			}
			else {
				this.setChanged();
				this.notifyObservers("Cross section can be defined by X,Y or Z.");
			}
		} catch (IndexOutOfBoundsException e) {
			this.setChanged();
			this.notifyObservers("Invalid index enterd.");
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#saveMaze(java.lang.String[])
	 */
	@Override
	public void saveMaze(String[] parameters) {
		try {
			//Create the outputStream with the file name and transfer the maze to byte array.
			MyCompressorOutputStream out=new MyCompressorOutputStream(new FileOutputStream(parameters[1]));
			byte[] check = mazes.get(parameters[0]).toByteArray();
			try {
				//Write the maze to the file received and close the file.
				out.write(check);
				out.flush();
				out.close();
				//Write the file path in the HashMap.
				files.put(parameters[0], parameters[1]);
				//Send a relevant message.
				this.setChanged();
				this.notifyObservers("The maze " + parameters[0] + " saved in: " + parameters[1] + ".");
			} catch (IOException e) {
				//In case an exception throws - send relevant message.
				this.setChanged();
				this.notifyObservers("I/O error occurred.");
			}
		} catch (FileNotFoundException e) {
			//In case an exception throws - send relevant message.
			this.setChanged();
			this.notifyObservers("The file doesn't exist.");
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#loadMaze(java.lang.String[])
	 */
	@Override
	public void loadMaze(String[] parameters) {
		try {
			//Create the outputStream with the file name and create a 36 byte array.
			MyDecompressorInputStream in=new MyDecompressorInputStream(new FileInputStream(parameters[0]));
			byte b[]=new byte[36];
			//Read 36 bytes.
			try {
				in.read(b, 0, 36);
			} catch (IOException e1) {
				//In case an exception throws - send relevant message.
				this.setChanged();
				this.notifyObservers("I/O error occurred");
			}
			int sum = 0;
			//Sum all odd cells on the 36 byte array.
			while(sum <= 36) {
				for(int i=1;i<b.length - 1;i=i+2) 
					sum += b[i];
			}
			int counter = 0;
			//Decompress the array to get the original parameters.
			byte length[] = new byte[sum];
			for(int i=1;i<b.length - 1;i=i+2){
				for(int loop=0;loop<b[i];loop++) {
					length[counter] = b[i-1];
					       counter++;
				}
			}
			//Extract the size from the parameters array.
			ByteBuffer buffer = ByteBuffer.wrap(length, 24, 12);
			//Multiply the size parameter to get the full byte array size.
			int mazeSize = buffer.getInt()*buffer.getInt()*buffer.getInt();
			//Reset The inputStream object and decompress the whole file.
			in=new MyDecompressorInputStream(new FileInputStream(parameters[0]));
			byte fullMaze[] = new byte[36+mazeSize];
			try {
				in.read(fullMaze);
				in.close();
				Maze3d loaded=new Maze3d(fullMaze);
				//Enter the maze and the file to the relevant HashMaps.
				mazes.put(parameters[1], loaded);
				files.put(parameters[1], parameters[0]);
				//Send relevant message.
				this.setChanged();
				this.notifyObservers("The maze from " + parameters[0] + " loaded to " + parameters[1] +".");
			} catch (IOException e) {
				//In case an exception throws - send relevant message.
				this.setChanged();
				this.notifyObservers("I/O error occurred.");
			}
		} catch (FileNotFoundException e) {
			//In case an exception throws - send relevant message.
			this.setChanged();
			this.notifyObservers("The file doesn't exist.");
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#mazeSizeRAM(java.lang.String[])
	 */
	@Override
	public void mazeSizeRAM(String[] parameters) {
		try {
			//Transfer maze to byte array and get it's length.
			int size = ((mazes.get(parameters[0])).toByteArray()).length;
			//Send relevant message.
			this.setChanged();
			this.notifyObservers("Maze size is " + size + " bytes.");
		} catch (NullPointerException e) { 
			//In case an exception throws - send relevant message.
			this.setChanged();
			this.notifyObservers("The maze doesn't exist.");
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#mazeSizeFile(java.lang.String[])
	 */
	@Override
	public void mazeSizeFile(String[] parameters) {
		try {
			//Get the maze file by it's name from the HashMap.
			File file = new File(files.get(parameters[0]));
			//Send relevant message with it's length.
			this.setChanged();
			this.notifyObservers("The file size is " + file.length() + " bytes.");
		} catch (NullPointerException e) {
			//In case an exception throws - send relevant message.
			this.setChanged();
			this.notifyObservers("Invalid maze name.");
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#solveMaze(java.lang.String[])
	 */
	@Override
	public void solveMaze(String[] parameters, Solve solve) {
		//Create object require to solve a maze on a different thread.
		solve.setParameters(parameters);
		//Pack the object with Thread class, add the object to the threads list, and activate the thread.
		executor.submit(solve);
	}

	/* (non-Javadoc)
	 * @see model.Model#solutionGenerator(java.lang.String[])
	 */
	@Override
	public Solution<Position> solutionGenerator(String[] parameters) {
		if(!cachedMazes.containsKey(mazes.get(parameters[0]))) {	
			try {
				//In case the algorithm is BFS.
				if(properties.getSolutionAlgorithm().toLowerCase().equals("bfs")) { 
					//Create a BFS object.
					BFS<Position> bfs = new BFS<Position>();
					//Wrap the maze with object adapter.
					Maze3dSearch search = new Maze3dSearch(mazes.get(parameters[0]));
					//Solve the maze.
					Solution<Position> solutionByBFS = bfs.search(search);
					//Add solution to the HashMap and send relevant message.
					solutions.put(parameters[0], solutionByBFS);
					cachedMazes.put(mazes.get(parameters[0]), solutionByBFS);
					this.zipAndSave();
					this.setChanged();
					this.notifyObservers("Solution for " + parameters[0] + " is ready.");
					return solutionByBFS;
				}
				//In case the algorithm is AStar.
				else if(properties.getSolutionAlgorithm().toLowerCase().equals("manhattan")) { 
					//Create a manhattanDistance object.
					ManhattanDistance manhattan = new ManhattanDistance();
					//Wrap the maze with object adapter.
					Maze3dSearch search = new Maze3dSearch(mazes.get(parameters[0]));
					//Create a AStar object.
					AStar<Position> aStar = new AStar<>(manhattan);
					//Solve the maze.
					Solution<Position> solutionByManhattan = aStar.search(search);
					//Add solution to the HashMap and send relevant message.
					solutions.put(parameters[0], solutionByManhattan);
					cachedMazes.put(mazes.get(parameters[0]), solutionByManhattan);
					this.zipAndSave();
					this.setChanged();
					this.notifyObservers("Solution for " + parameters[0] + " is ready.");
					return solutionByManhattan;
				}
				else if(properties.getSolutionAlgorithm().toLowerCase().equals("air")) { 
					//Create a AirDistance object.
					AirDistance air = new AirDistance();
					//Wrap the maze with object adapter.
					Maze3dSearch search = new Maze3dSearch(mazes.get(parameters[0]));
					//Create a AStar object.
					AStar<Position> aStar = new AStar<>(air);
					//Solve the maze.
					Solution<Position> solutionByAir = aStar.search(search);
					//Add solution to the HashMap and send relevant message.
					solutions.put(parameters[0], solutionByAir);
					cachedMazes.put(mazes.get(parameters[0]), solutionByAir);
					this.zipAndSave();
					this.setChanged();
					this.notifyObservers("Solution for " + parameters[0] + " is ready.");
					return solutionByAir;
				}
				//Any other case.
				else { 
					this.setChanged();
					this.notifyObservers("Algorithm not found.");
				}
			} catch(NullPointerException e) { 
				//In case an exception throws - send relevant message.
				this.setChanged();
				this.notifyObservers("Invalid maze name.");
			}
			return null;
		}
		else {
			this.setChanged();
			this.notifyObservers("Solution for " + parameters[0] + " is ready.");
			return cachedMazes.get(mazes.get(parameters[0]));
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#getSolution(java.lang.String[])
	 */
	@Override
	public void getSolution(String[] parameters) {
		try { 
			this.setChanged();
			this.notifyObservers(solutions.get(parameters[0]));
		} catch (NullPointerException e) {
			//In case an exception throws - send relevant message.
			this.setChanged();
			this.notifyObservers("Invalid maze name.");
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#waitForThreads()
	 */
	@Override
	public void waitForThreads() {
		//For each Thread in threads.
		for(Thread t : threads) {
			//If still alive - join.
			if(t.isAlive())
				try {
					t.join();
				} catch (InterruptedException e) {
					//In case an exception throws - send relevant message.
					this.setChanged();
					this.notifyObservers("Fatal Error.");
				}
		}
		//Send message.
		this.setChanged();
		this.notifyObservers("Goodbye.");
	}
	public void zipAndSave(){
		try {
			FileOutputStream fos=new FileOutputStream(properties.getCacheFileLocation());
		    GZIPOutputStream gzos=new GZIPOutputStream(fos);
		    ObjectOutputStream out=new ObjectOutputStream(gzos);
		    out.writeObject(cachedMazes);
		    out.flush();
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		  }
	}

	@Override
	public void solveFromPoint(String[] parameters) {
		Maze3d maze = new Maze3d(mazes.get(parameters[0]));
		Position start = maze.getStartPosition();
		maze.setStartPosition(new Position(Integer.parseInt(parameters[1]),Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3])));
		mazes.put(parameters[0], maze);
		String[] command = { parameters[0], "AStar" };
		Solution<Position> answer = this.solutionGenerator(command);
		maze.setStartPosition(start);
		mazes.put(parameters[0], maze);
		this.setChanged();
		this.notifyObservers(answer);
	}

	@Override
	public void setProperties(String[] parameters) {
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(parameters[0])));
			this.properties = (Properties)decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
	}
}