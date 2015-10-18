package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;


public class Maze2D extends MazeBoard{


	 public Maze2D(Composite parent,int style){
	        super(parent, style);
	 }
	 
	 public void paint() {
	    // set a white background   (red, green, blue)
	    setBackground(new Color(null, 255, 255, 255));
	    addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(new Color(null,0,0,0));
				e.gc.setBackground(new Color(null,0,0,0));

				int width=getSize().x;
				int height=getSize().y;
				mazeData = fullMaze.getCrossSectionByY(player.getLocation().getY());
				int w=width/mazeData.length;
				int h=height/mazeData[0].length;

				for(int i=0;i<mazeData.length;i++) {
					for(int j=0;j<mazeData[i].length;j++){
						int x=j*w;
					          int y=i*h;
					          if(mazeData[j][i]!=0)
					        	  e.gc.fillRectangle(x,y,w,h);
					}
				}
				player.paint(e, w,h, new Color(null,255,0,0));
				if(player.getLocation().getY() == start.getLocation().getY())
					start.paint(e, w, h, new Color(null,0,255,0));
				if(player.getLocation().getY() == end.getLocation().getY())
					end.paint(e, w, h, new Color(null,0,0,255));
				if(player.equals(end)) {
					Image image = new Image(getDisplay(), "resource\\win.jpg");
					e.gc.drawImage(image,0,0, 236,334,(width-236)/2, (height-334)/2,236,334);
				}
			}
	    });
	 }



	@Override
	public void setCharacterPosition(Position place) {
		player.SetLocation(place);
	}

	@Override
	public void moveUp() {
		if ((player.getLocation().getZ() > 0) && (mazeData[player.getLocation().getX()][player.getLocation().getZ() - 1] == 0))
			player.getLocation().setZ(player.getLocation().getZ() - 1);
	}

	@Override
	public void moveDown() {
		if ((player.getLocation().getZ() < (mazeData[player.getLocation().getZ()].length) - 1) && (mazeData[player.getLocation().getX()][player.getLocation().getZ() + 1] == 0))
			player.getLocation().setZ(player.getLocation().getZ() + 1);
		
	}

	@Override
	public void moveLeft() {
		if ((player.getLocation().getX() > 0) && (mazeData[player.getLocation().getX() - 1][player.getLocation().getZ()] == 0))
			player.getLocation().setX(player.getLocation().getX() - 1);
		
	}

	@Override
	public void moveRight() {
		if ((player.getLocation().getX() < (mazeData.length) - 1) && (mazeData[player.getLocation().getX() + 1][player.getLocation().getZ()] == 0))
			player.getLocation().setX(player.getLocation().getX() + 1);
		
	}

	@Override
	public void moveUpLvl() {
			player.getLocation().setY(player.getLocation().getY() + 1);
	}

	@Override
	public void moveDownLvl() {
		player.getLocation().setY(player.getLocation().getY() - 1);
		
	}
}
