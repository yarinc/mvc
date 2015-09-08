package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that extends Maze3dAbstract and overriding generate method. 
 * The class creating a maze by prim's randomized algorithm.
 * @author Yarin Cohen
 */
public class MyMaze3dGenerator extends Maze3dAbstract {
	/**
	 * Overriding the generate method and generating a 3d maze.
	 * The maze is created by prim's randomized algorithm.
	 * @param size The size of the requested maze.
	 * @return A perfect maze.
	 */
	@Override
	public Maze3d generate(Position size) {
		int i,j,k;
		int randomWall;
		Position opposite;
		Position wall[];
		List<Position[]> list = new ArrayList<Position[]>();
		Maze3d maze = new Maze3d(size);
		//Filling the maze with "1" - walls
		for(i = 0; i < size.getX(); i++) {
			for(j = 0; j < size.getY(); j++) {
				for(k = 0; k < size.getZ(); k++) {
					maze.setMazeValue(new Position(i,j,k), 1);
				}
			}
		}
		//Selecting random start point
		Random randomGenerator = new Random();
	    Position startAlgorithm = new Position(randomGenerator.nextInt(size.getX()), randomGenerator.nextInt(size.getY()),randomGenerator.nextInt(size.getZ()));
	    //Mark it as "0" - passage
	    maze.setMazeValue(startAlgorithm, 0);
	    //Add it's neighbors to the list
	    addNearWalls(maze, startAlgorithm, list);
	    while (!(list.isEmpty())) {
	    	//Choose random wall from list
	    	randomWall = randomGenerator.nextInt(list.size());
	    	wall = list.get(randomWall);
	    	//Get opposite side
	    	opposite = getOpposite(maze, wall);
	    	//if opposite is in the maze bounds and it's value is 1
	    	if((opposite != null) && (maze.getMazeValue(opposite) == 1)) {
	    		//Set opposite and the wall to 0 and add the opposite neighbors to the list
	    		maze.setMazeValue(opposite, 0);
	    		maze.setMazeValue(wall[1], 0);
	    		addNearWalls(maze, opposite, list);
	    	}
	    	//Remove wall from list
	    	list.remove(wall);
	    }
	   //Set entrance and exit to the maze and make sure they are not the same position
		maze.setGoalPosition(chooseExit(size));
		maze.setStartPosition(chooseExit(size));
		do {
			//Make sure the value of the entrance and exit is zero
			if(maze.getMazeValue(maze.getStartPosition()) != 0)
				maze.setStartPosition(chooseExit(size));
			if(maze.getMazeValue(maze.getGoalPosition()) != 0)
				maze.setGoalPosition(chooseExit(size));
			//Make sure that startPosition and GoalPosition is not the same position
			if(maze.getGoalPosition() == maze.getStartPosition())
				maze.setGoalPosition(chooseExit(size));
		} while ((maze.getMazeValue(maze.getStartPosition()) != 0) || (maze.getMazeValue(maze.getGoalPosition()) != 0) || (maze.getGoalPosition() == maze.getStartPosition()));
		return maze;
	}
	/**
	 * The method randomly choose a exit or start point to the maze.
	 * @param size The size of the maze.
	 * @return A position that will function as a exit or start point.
	 */
	public Position chooseExit(Position size) {
		//select random number between 0-5. each represent different edge
		Random randomGenerator = new Random();
		int startWall = randomGenerator.nextInt(6);
		switch (startWall) {
		//Left wall
		case 0: return (new Position(0,randomGenerator.nextInt(size.getY()),randomGenerator.nextInt(size.getZ())));
		//Right wall
		case 1: return (new Position(size.getX() -1 ,randomGenerator.nextInt(size.getY()),randomGenerator.nextInt(size.getZ())));
		//Up wall
		case 2: return (new Position(randomGenerator.nextInt(size.getX()),size.getY() - 1,randomGenerator.nextInt(size.getZ())));
		//Down wall
		case 3: return (new Position(randomGenerator.nextInt(size.getX()),0,randomGenerator.nextInt(size.getZ())));
		//Forward wall
		case 4: return (new Position(randomGenerator.nextInt(size.getX()),randomGenerator.nextInt(size.getY()),0));
		//Back wall
		default: return (new Position(randomGenerator.nextInt(size.getX()),randomGenerator.nextInt(size.getY()),size.getZ() - 1));
		}
	}
	/**
	 * Adding all Positions of place that are neighbors of him and their value is 1 to a list.  
	 * @param maze The manipulated maze.
	 * @param place The requested place.
	 * @param list The list of all position.
	 */
	public void addNearWalls(Maze3d maze,Position place, List<Position[]> list) {
		//if the (x+1,y,z) is still in the cube range - add the right neighbor
		if((place.getX() + 1 <= maze.getBounds().getX() - 1)  && (maze.getMazeValue(maze.getRight(place)) == 1))
			list.add(new Position[] {place, maze.getRight(place)});
		//if the(x-1,y,z) is still in the cube range - add the left neighbor
		if((place.getX() - 1 >= 0) && (maze.getMazeValue(maze.getLeft(place)) == 1))
			list.add(new Position[] {place, maze.getLeft(place)});
		//if the(x,y+1,z) is still in the cube range - add the upper neighbor
		if((place.getY() + 1 <= maze.getBounds().getY() - 1) && (maze.getMazeValue(maze.getUp(place)) == 1))
			list.add(new Position[] {place, maze.getUp(place)});
		//if the(x,y-1,z) is still in the cube range - add the bottom neighbor
		if((place.getY() - 1 >= 0) && (maze.getMazeValue(maze.getDown(place)) == 1))
			list.add(new Position[] {place, maze.getDown(place)});
		//if the(x,y,z+1) is still in the cube range - add the neighbor 'behind'
		if((place.getZ() + 1 <= maze.getBounds().getZ() - 1) && (maze.getMazeValue(maze.getBack(place)) == 1))
			list.add(new Position[] {place,maze.getBack(place)});
		//if the(x,y,z-1) is still in the cube range - add the neighbor 'after'
		if((place.getZ() - 1 >= 0) && (maze.getMazeValue(maze.getForward(place)) == 1))
			list.add(new Position[] {place,maze.getForward(place)});
	}
	/**
	 * Retrieve the opposite position of a given position,
	 * using a array of 2 positions to determined the opposite position.
	 * @param maze The manipulated maze.
	 * @param wall The array of two wall.
	 * @return The opposite position.
	 */
	public Position getOpposite(Maze3d maze, Position[] wall) {
		Position opposite;
		//check if x is the coordinate that changed
		if(wall[0].getX() != wall[1].getX()) {
			//check which x coordinate is smaller to know if opposite is in the left or right
			if (wall[0].getX() < wall[1].getX()) { 
				//if the right coordinate is still in the cube boundaries - get it
				if (wall[1].getX() + 1 <= maze.getBounds().getX() - 1) {
					opposite = maze.getRight(wall[1]);
					return opposite;
				}
			}
			else {
				//if the left coordinate is still in the cube boundaries - get it
				if (wall[1].getX() - 1 >= 0) {
					opposite = maze.getLeft(wall[1]);
					return opposite;
				}
			}
		}
		//check if y is the coordinate that changed
		else if(wall[0].getY() != wall[1].getY()) {
			//check which y coordinate is smaller to know if opposite is in the up or down
			if (wall[0].getY() < wall[1].getY()) { 
				//if the up coordinate is still in the cube boundaries - get it
				if (wall[1].getY() + 1 <= maze.getBounds().getY() - 1) {
					opposite = maze.getUp(wall[1]);
					return opposite;
				}
			}
			else {
				//if the down coordinate is still in the cube boundaries - get it
				if (wall[1].getY() - 1 >= 0) {
					opposite = maze.getDown(wall[1]);
					return opposite;
				}
			}
		}
		
		//z must be the coordinate that changed
		else {
			//check which z coordinate is smaller to know if opposite is in the forward or back
			if (wall[0].getZ() < wall[1].getZ()) { 
				//if the back coordinate is still in the cube boundaries - get it
				if (wall[1].getZ() + 1 <= maze.getBounds().getZ() - 1) {
					opposite = maze.getBack(wall[1]);
					return opposite;
				}
			}
			else {
				//if the forward coordinate is still in the cube boundaries - get it
				if (wall[1].getZ() - 1 >= 0) {
					opposite = maze.getForward(wall[1]);
					return opposite;
				}				
			}
		}
		return null;
	}
}
