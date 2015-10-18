package presenter;

import java.io.Serializable;

public class Properties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numberOfThreads;
	private String cacheFileLocation;
	private String viewType;
	private String solutionAlgorithm;
	
	public int getNumberOfThreads() {
		return numberOfThreads;
	}
	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}
	public String getCacheFileLocation() {
		return cacheFileLocation;
	}
	public void setCacheFileLocation(String cacheFileLocation) {
		this.cacheFileLocation = cacheFileLocation;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getSolutionAlgorithm() {
		return solutionAlgorithm;
	}
	public void setSolutionAlgorithm(String solutionAlgorithm) {
		this.solutionAlgorithm = solutionAlgorithm;
	}
}
