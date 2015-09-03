package algorithms.mazeGenerators;

/**
 * Class that represent a single position by {x,y,z} coordinates.
 * @author Yarinc Cohen
 */
public class Position {

	private int x;
	private int y;
	private int z;
	/**
	 * Default constructor.
	 */
	public Position() {}
	
	/**
	 * Constructor by x,y,z coordinates.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param z The z coordinate.
	 */
	public Position(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
	}
	/**
	 * Copy constructor.
	 * @param location The copied position.
	 */
	public Position(Position location){
		this.x = location.x;
		this.y = location.y;
		this.z = location.z;
	}
	/**
	 * Override the equal method.
	 * Checking of two position have the same coordinates.
	 * @param obj The checked position.
	 * @return True if equal, otherwise false.
	 */
	@Override
	public boolean equals(Object obj) {
		if((x == ((Position)obj).x) && (y == ((Position)obj).y) && (z == ((Position)obj).z))
			return true;
		return false; 
	}
	/**
	 * Getter for the x coordinate attribute.
	 * @return The value of x.
	 */
	//Get x
	public int getX() {
		return x;
	}
	/**
	 * Setter for the x coordinate attribute.
	 * @param x The value.
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Getter for the y coordinate attribute.
	 * @return The value of y.
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setter for the y coordinate attribute.
	 * @param y The value.
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Getter for the z coordinate attribute.
	 * @return The value of z.
	 */
	public int getZ() {
		return z;
	}
	/**
	 * Setter for the z coordinate attribute.
	 * @param z The value.
	 */
	public void setZ(int z) {
		this.z = z;
	}
	/**
	 * Override toString method of Position.
	 * Transfer the position to a '{x,y,z}' template.
	 */
	@Override
	public String toString() {
		return "{" + x + "," + y + "," + z + "}";
	}

}

