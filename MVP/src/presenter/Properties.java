package presenter;

import java.io.Serializable;

/**
 * The Class Properties.
 */
public class Properties implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The number of threads. */
	private int numberOfThreads;
	
	/** The cache file location. */
	private String cacheFileLocation;
	
	/** The view type. */
	private String viewType;
	
	/** The solution algorithm. */
	private String solutionAlgorithm;
	
	/**
	 * Gets the number of threads.
	 *
	 * @return the number of threads
	 */
	public int getNumberOfThreads() {
		return numberOfThreads;
	}
	
	/**
	 * Sets the number of threads.
	 *
	 * @param numberOfThreads the new number of threads
	 */
	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}
	
	/**
	 * Gets the cache file location.
	 *
	 * @return the cache file location
	 */
	public String getCacheFileLocation() {
		return cacheFileLocation;
	}
	
	/**
	 * Sets the cache file location.
	 *
	 * @param cacheFileLocation the new cache file location
	 */
	public void setCacheFileLocation(String cacheFileLocation) {
		this.cacheFileLocation = cacheFileLocation;
	}
	
	/**
	 * Gets the view type.
	 *
	 * @return the view type
	 */
	public String getViewType() {
		return viewType;
	}
	
	/**
	 * Sets the view type.
	 *
	 * @param viewType the new view type
	 */
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	
	/**
	 * Gets the solution algorithm.
	 *
	 * @return the solution algorithm
	 */
	public String getSolutionAlgorithm() {
		return solutionAlgorithm;
	}
	
	/**
	 * Sets the solution algorithm.
	 *
	 * @param solutionAlgorithm the new solution algorithm
	 */
	public void setSolutionAlgorithm(String solutionAlgorithm) {
		this.solutionAlgorithm = solutionAlgorithm;
	}
}
