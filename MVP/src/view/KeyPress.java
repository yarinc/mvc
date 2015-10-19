package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Button;

import algorithms.mazeGenerators.Position;

public class KeyPress implements KeyListener {
	private MazeBoard board;
	private Button hintButton;
	private Button solveButton;
	
	
	public KeyPress(MazeBoard board, Button hintButton, Button solveButton) {
		this.board = board;
		this.hintButton = hintButton;
		this.solveButton = solveButton;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.keyCode == SWT.ARROW_UP) {
			board.moveUp();
			board.redraw();
			if(board.player.equals(board.end)) {
				board.removeKeyListener(this);
				hintButton.setEnabled(false);
				solveButton.setEnabled(false);
			}
		}
		
		else if(e.keyCode == SWT.ARROW_DOWN) {
			board.moveDown();
			board.redraw();
			if(board.player.equals(board.end)) {
				board.removeKeyListener(this);
				hintButton.setEnabled(false);
				solveButton.setEnabled(false);
			}
		}
		
		else if(e.keyCode == SWT.ARROW_LEFT) {
			board.moveLeft();
			board.redraw();
			if(board.player.equals(board.end)) {
				board.removeKeyListener(this);
				hintButton.setEnabled(false);
				solveButton.setEnabled(false);
			}
		}
		else if(e.keyCode == SWT.ARROW_RIGHT) {
			board.moveRight();
			board.redraw();
			if(board.player.equals(board.end)) {
				board.removeKeyListener(this);
				hintButton.setEnabled(false);
				solveButton.setEnabled(false);
			}
		}
		else if(e.keyCode == SWT.PAGE_UP) {
			if((board.player.getLocation().getY() < board.fullMaze.getBounds().getY()) && (board.fullMaze.getMazeValue(new Position(board.player.getLocation().getX(),board.player.getLocation().getY() + 1,board.player.getLocation().getZ())) == 0)) {
				board.moveUpLvl();
				board.redraw();
				if(board.player.equals(board.end)) { 
					board.removeKeyListener(this);
					hintButton.setEnabled(false);
					solveButton.setEnabled(false);
				}
			}
		}
		else if(e.keyCode == SWT.PAGE_DOWN) {
			if((board.player.getLocation().getY() > 0) && (board.fullMaze.getMazeValue(new Position(board.player.getLocation().getX(),board.player.getLocation().getY() - 1,board.player.getLocation().getZ())) == 0)) {
				board.moveDownLvl();
				board.redraw();
				if(board.player.equals(board.end)) {
					board.removeKeyListener(this);
					hintButton.setEnabled(false);
					solveButton.setEnabled(false);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

}
