package algorithms.mazeGenerators;
/**
 * An interface that represent a maze generator algorithm.
 * @author Yarin Cohen
 */

public interface Maze3dGenerator {
	Maze3d generate(Position size);
	String measureAlgorithmTime(Position size);

}
