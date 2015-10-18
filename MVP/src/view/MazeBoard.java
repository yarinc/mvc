package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public abstract class MazeBoard extends Canvas{
	
	protected Maze3d fullMaze;
	protected int[][] mazeData;
	protected Character player;
	protected Character start;
	protected Character end;
	
	public MazeBoard(Composite parent, int style) {
		super(parent, style);
	}
	public void setFullMaze(Maze3d maze){
		this.fullMaze = maze;
	}
	public void setMazeData(int[][] maze){
		this.mazeData = maze;
	}
	public void setPlayer(Position player){
		this.player = new Character(player);
	}
	public void setStart(Position start){
		this.start = new Character(start);
	}
	public void setEnd(Position end){
		this.end = new Character(end);
	}
	public Character GetStart() {
		return start;
	}
	public Character GetEnd() {
		return end;
	}
	
	public abstract void paint();
	
	public abstract void setCharacterPosition(Position place);

	public abstract void moveUp();

	public abstract void moveDown();

	public abstract void moveLeft();

	public abstract void moveRight();

	public abstract void moveUpLvl();
	
	public abstract void moveDownLvl();

}