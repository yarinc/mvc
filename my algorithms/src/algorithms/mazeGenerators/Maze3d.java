package algorithms.mazeGenerators;

import java.nio.ByteBuffer;

/**
 * Class that represent a 3 dimensions maze by 3 coordinate {x,y,z}.
 * The class contains bound position to mark the bounds of the maze,
 * Start and a goal position to mark the entrance end exit of the maze 
 * and a 3 dimensions integer array that represent the maze itself.
 * @author Yarin Cohen
 *
 */
public class Maze3d {
	
	//Declaring data members
	private Position bounds;
	private Position startPosition;
	private Position goalPosition;
	private int[][][] maze;
	
	/**
	 * Constructor for a 3 dimensions maze.
	 * The constructor gets a position that mark its size and creates the array.
	 * @param size The maze size.
	 * @exception IllegalArgumentException if the size is small or equal to {1,1,1}.
	 */
	public Maze3d(Position size) {
		try {
			if((size.getX() <=1) ||(size.getY() <=1) || (size.getZ() <=1))
				throw new IllegalArgumentException("Invalid maze size");
			bounds = size;
			maze = new int[size.getX()][size.getY()][size.getZ()];
		}
			catch (IllegalArgumentException e) { 
				throw e;
			}
	}
	public Maze3d(byte[] b) {
		// TODO Auto-generated constructor stub
		int x,y,z,i = 36;
		Position start = new Position();
		Position goal = new Position();
		Position size = new Position();
		ByteBuffer buffer = ByteBuffer.wrap(b, 0, 36);
		start.setX(buffer.getInt());
		start.setY(buffer.getInt());
		start.setZ(buffer.getInt());
		goal.setX(buffer.getInt());
		goal.setY(buffer.getInt());
		goal.setZ(buffer.getInt());
		size.setX(buffer.getInt());
		size.setY(buffer.getInt());
		size.setZ(buffer.getInt());
		startPosition = new Position(start);
		goalPosition = new Position(goal);
		bounds = new Position(size);
		maze = new int[size.getX()][size.getY()][size.getZ()];
		for(y = 0; y<bounds.getY();y++) {
			for(z = 0; z<bounds.getZ();z++) {
				for(x = 0; x<bounds.getX();x++) {
					maze[x][y][z] = (int) b[i];
					i++;
				}
			}
		}
	}
	public boolean equals(Object obj) {
		int x,y,z;
		if((((Maze3d)(obj)).getGoalPosition()).equals(this.getGoalPosition()) && (((Maze3d)(obj)).getStartPosition()).equals(this.getStartPosition()) && (((Maze3d)(obj)).getBounds()).equals(this.getBounds())) {
			for(y = 0; y<bounds.getY();y++) {
				for(z = 0; z<bounds.getZ();z++) {
					for(x = 0; x<bounds.getX();x++) {
						if((((Maze3d)(obj)).getMazeValue(new Position(x,y,z))) != (this.getMazeValue(new Position(x,y,z))))
						return false;
					}
				}
			}
			return true;
		}
		return false; 
		
	}
	/**
	 * Getter for the bounds attribute.
	 * @return The maze bounds.
	 */
	public Position getBounds() {
		return bounds;
	}
	/**
	 * Checking if the position is in the bounds of the maze.
	 * @param place The checked position.
	 * @return True if position is in the maze bounds, otherwise, false.
	 */
	public boolean inRange(Position place) { 
		if((place.getX() >= 0) && (place.getX() <= bounds.getX() - 1) && (place.getY() >= 0) && (place.getY() <= bounds.getY() - 1) && (place.getZ() >= 0) && (place.getZ() <= bounds.getZ() - 1))
				return true;
		return false;
	}
	/**
	 * Setting a value at the 3 dimensions maze. Value must be 1 or 0.
	 * @param place Which position to apply the change.
	 * @param value The desired value.
	 */
	public void setMazeValue(Position place, int value) {
		if((value == 0) || (value == 1))
			maze[place.getX()][place.getY()][place.getZ()] = value;
		else 
			System.out.println("invalid value");
	}
	/**
	 * Getting a value of position in the 3 dimensions maze.
	 * @param place Which position to retrieve.
	 * @return The value requested.
	 */
	public int getMazeValue(Position place) { 
		if(inRange(place))
			return maze[place.getX()][place.getY()][place.getZ()];
		else
			return -1;
	}
	/**
	 * Printing the maze.
	 */
	public void printMaze() {
		int i,j,k;
		for(i=0;i<bounds.getY();i++){
			for(k=0;k<bounds.getZ();k++){
				System.out.print ("[");
				for(j=0;j<bounds.getX(); j++) {
					System.out.print(getMazeValue(new Position(j,i,k)));
					if(j != bounds.getX() - 1)
						System.out.print(", ");
				}
				System.out.println("]");
			}
			System.out.print("\n");
		}
	}
	/**
	 * Getting a 2 dimensions maze slice determined by coordinate x.
	 * @param coordinate The desired x coordinate.
	 * @return An 2 dimensions array.
	 */
	public int[][] getCrossSectionByX(int coordinate) {
		if((coordinate < 0) ||(coordinate > bounds.getX())) 
			throw new IndexOutOfBoundsException("Invalid Parameter");
		else {
			int[][] crossX = new int[bounds.getY()][bounds.getZ()];
			int k,j;
			for(k=0; k< bounds.getY(); k++) {
				for(j=0;j<bounds.getZ();j++) 
					crossX[k][j] = maze[coordinate][k][j];	
				}
			return crossX;
		}
	}
	/**
	 * Getting a 2 dimensions maze slice determined by coordinate y.
	 * @param coordinate The desired y coordinate.
	 * @return An 2 dimensions array.
	 */
	public int[][] getCrossSectionByY(int coordinate) {
		if((coordinate<0) || (coordinate > bounds.getY())) 
			throw new IndexOutOfBoundsException("Invalid Parameter");
		else {
			int[][] crossY = new int[bounds.getX()][bounds.getZ()];
			int k,j;
			for(k=0; k < bounds.getX(); k++) {
				for(j=0;j<bounds.getZ();j++) {
					crossY[k][j] = maze[k][coordinate][j];
				}
			}
			return crossY;
		}
	}
	/**
	 * Getting a 2 dimensions maze slice determined by coordinate x.
	 * @param coordinate The desired z coordinate.
	 * @return An 22 dimensions array.
	 */
	public int[][] getCrossSectionByZ(int coordinate) {
		if((coordinate < 0) ||(coordinate > bounds.getZ())) 
			throw new IndexOutOfBoundsException("Invalid Parameter");
		else {
			int[][] crossZ = new int[bounds.getX()][bounds.getY()];
			int k,j;
			for(k=0; k < bounds.getX(); k++) {
				for(j=0;j<bounds.getY();j++) 
					crossZ[k][j] = maze[k][j][coordinate];
			}
			return crossZ;
		}
	}
	/**
	 * Getter to the startPosition attribute.
	 * @return The start Position.
	 */
	public Position getStartPosition() {
		return startPosition;
	}
	/**
	 * Setter to the startPosition attribute.
	 * @param start The start position.
	 */
	public void setStartPosition(Position start) {
		startPosition = start;
	}
	/**
	 * Getter to the goalPosition attribute.
	 * @return The goal Position.
	 */
	public Position getGoalPosition() {
		return goalPosition;
	}
	/**
	 * Setter to the goalPosition attribute.
	 * @param goalPosition The goal position.
	 */
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}
	/**
	 * Adding to a array of String all the possible move on the p parameter.
	 * @param p Position to get all its neighbors.
	 * @return Array of Strings contain the valid options.
	 */
	public String[] getPossibleMoves(Position p) {
		int i=0, count=0;
		String[] moves = new String[6];
		//if the (x+1,y,z) is still in the cube range - add the right neighbor
		if(p.getX() + 1 < bounds.getX())
			moves[0] = "Right";
		//if the(x-1,y,z) is still in the cube range - add the left neighbor
		if(p.getX() - 1 >= 0)
			moves[1] = "Left";
		//if the(x,y+1,z) is still in the cube range - add the upper neighbor
		if(p.getY() + 1 < bounds.getY())
			moves[2] = "Up";
		//if the(x,y-1,z) is still in the cube range - add the bottom neighbor
		if(p.getY() - 1 >= 0)
			moves[3] = "Down";
		//if the(x,y,z+1) is still in the cube range - add the neighbor 'behind'
		if(p.getZ() + 1 < bounds.getZ())
			moves[4] = "Back";
		//if the(x,y,z-1) is still in the cube range - add the neighbor 'after'
		if(p.getZ() - 1 >= 0)
			moves[5] = "Forward";
		for(String move : moves) { 
			if(move != null)
				count++;
		}
		//create array without nulls cells and return it
		String[] finalMoves= new String[count];
		for(String move : moves) { 
			if(move != null) {
				finalMoves[i] = move;
				i++;
			}
		}
		return finalMoves;
	}
	/**
	 * Print a 2 dimensions array.
	 * @param maze2d The 2 dimensions array.
	 */
	public void print2d(int[][] maze2d) {
		int i,j;
		for(i=0;i<maze2d.length;i++){
			System.out.print ("[");
			for(j=0;j<maze2d[i].length;j++){
					System.out.print(maze2d[i][j]);
					if(j != maze2d[i].length - 1)
						System.out.print(", ");
				}
			System.out.println("]");
		}
	System.out.print("\n");
	}
	/**
	 * Get the right neighbor of place.
	 * @param place The position to get its right neighbor.
	 * @return The right position.
	 */
	public Position getRight(Position place) {
		Position right = new Position(place.getX() + 1, place.getY(), place.getZ());
		return right;
	}
	/**
	 * Get the left neighbor of place.
	 * @param place The position to get its left neighbor.
	 * @return The left position.
	 */
	public Position getLeft(Position place) {
		Position left = new Position(place.getX() - 1, place.getY(), place.getZ());
		return left;
	}
	/**
	 * Get the upper neighbor of place.
	 * @param place The position to get its upper neighbor.
	 * @return The up position.
	 */
	public Position getUp(Position place) {
		Position up = new Position(place.getX(), place.getY() + 1, place.getZ());
		return up;
	}
	/**
	 * Get the down neighbor of place.
	 * @param place The position to get its down neighbor.
	 * @return The down position.
	 */
	public Position getDown(Position place) {
		Position down = new Position(place.getX(), place.getY() - 1, place.getZ());
		return down;
	}
	/**
	 * Get the back neighbor of place.
	 * @param place The position to get its back neighbor.
	 * @return The back position.
	 */
	public Position getBack(Position place) {
		Position back = new Position(place.getX(), place.getY(), place.getZ() + 1);
		return back;
	}
	/**
	 * Get the forward neighbor of place.
	 * @param place The position to get its forward neighbor.
	 * @return The forward position.
	 */
	public Position getForward(Position place) {
		Position forward = new Position(place.getX(), place.getY(), place.getZ() - 1);
		return forward;
	}
	public byte[] toByteArray() {
		
		int mazeLength = bounds.getX()*bounds.getY()*bounds.getZ();
		byte[] tempArray;
		byte[] b = new byte[mazeLength + 36];
		int x,z,y,k=0,i;
		ByteBuffer buffer = ByteBuffer.allocate(36);
		buffer.putInt(startPosition.getX()).putInt(startPosition.getY()).putInt(startPosition.getZ());
		buffer.putInt(goalPosition.getX()).putInt(goalPosition.getY()).putInt(goalPosition.getZ());
		buffer.putInt(bounds.getX()).putInt(bounds.getY()).putInt(bounds.getZ());
		tempArray = buffer.array();
		
		for(i=0;i<tempArray.length;i++){
			b[i] = tempArray[k];
					k++;
		}
		for(y = 0; y<bounds.getY();y++) {
			for(z = 0; z<bounds.getZ();z++) {
				for(x = 0; x<bounds.getX();x++) {
					b[i] = (byte) maze[x][y][z];
					i++;
				}
			}	
		}
		return b;
	}
}
