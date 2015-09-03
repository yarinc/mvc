package algorithms.mazeGenerators;

import java.util.Random;

/**
 * Class that extends Maze3dAbstract and overriding generate method. 
 * The class creating a maze by a simple algorithm.
 * @author Yarin Cohen
 */
public class SimpleMaze3dGenerator extends Maze3dAbstract {
	/**
	 * Overriding the generate method and generating a 3d maze.
	 * The maze is created by a simple algorithm.
	 * The algorithm equalizing each coordinate and set a passage 
	 * until reached to the goal position. 
	 * @param size The size of the requested maze.
	 * @return A maze.
	 */
	//Generate maze
	public Maze3d generate(Position size) {
		//define variables
		int i,j,k; 
		Random randomGeneretor = new Random();
		Maze3d maze = new Maze3d(size);
		//create start and exit positions 
		maze.setStartPosition(chooseExit(size));
		maze.setGoalPosition(chooseExit(size));
		//if the start and exit are not the same position change goal position
		while ((maze.getStartPosition().getX() == maze.getGoalPosition().getX()) && (maze.getStartPosition().getY() == maze.getGoalPosition().getY()) && (maze.getStartPosition().getZ() == maze.getGoalPosition().getZ()))
			maze.setGoalPosition(chooseExit(size));
		Position mover = new Position (maze.getStartPosition());
		//fill the maze with 1 or 0
		for(i = 0; i < size.getX(); i++) {
			for(j = 0; j < size.getY(); j++) {
				for(k = 0; k < size.getZ(); k++) {
					maze.setMazeValue(new Position(i,j,k), randomGeneretor.nextInt(2));
				}
			}
		}
		//mark start and goal positions as 0
		maze.setMazeValue(maze.getStartPosition(), 0);
		maze.setMazeValue(maze.getGoalPosition(), 0);
		//equalize x coordinate and for each move - mark as passage
		while (mover.getX() != maze.getGoalPosition().getX()) { 
			if(mover.getX() < maze.getGoalPosition().getX()) {
				mover.setX(mover.getX() + 1);
				maze.setMazeValue(mover, 0);
			}
			else {
				mover.setX(mover.getX() - 1);
				maze.setMazeValue(mover, 0);
			}
		}
		//equalize y coordinate and for each move - mark as passage
		while (mover.getY() != maze.getGoalPosition().getY()) { 
			if(mover.getY() < maze.getGoalPosition().getY()) {
				mover.setY(mover.getY() + 1);
				maze.setMazeValue(mover, 0);
			}
			else {
				mover.setY(mover.getY() - 1);
				maze.setMazeValue(mover, 0);
			}
		}
		//equalize z coordinate and for each move - mark as passage
		while (mover.getZ() != maze.getGoalPosition().getZ()) { 
			if(mover.getZ() < maze.getGoalPosition().getZ()) {
				mover.setZ(mover.getZ() + 1);
				maze.setMazeValue(mover, 0);
			}
			else {
				mover.setZ(mover.getZ() - 1);
				maze.setMazeValue(mover, 0);
			}
		}
		return maze;
	}
	/**
	 * The method randomly choose a exit or start point to the maze.
	 * @param size The size of the maze.
	 * @return A position that will function as a exit or start point.
	 */
	public Position chooseExit(Position size) {

		Random randomGenerator = new Random();
		int startWall = randomGenerator.nextInt(6);
		switch (startWall) {
		//Left wall
		case 0: return (new Position(0,randomGenerator.nextInt(size.getY()),randomGenerator.nextInt(size.getZ())));
		//Right wall
		case 1: return (new Position(size.getX() - 1,randomGenerator.nextInt(size.getY()),randomGenerator.nextInt(size.getZ())));
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
}
