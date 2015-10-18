package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;

import algorithms.mazeGenerators.Position;

public class Character {
	private Position location;
	
	
	public Character(Position position) {
		location = position;
	}
	public void paint(PaintEvent e,int w,int h, Color color){
		e.gc.setForeground(color);
		e.gc.setBackground(color);
		e.gc.fillOval(location.getX()*w,location.getZ()*h, w, h);
	}

	public Position getLocation() {
		return location;
	}
	public void SetLocation(Position place) { 
		location = place;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
}
