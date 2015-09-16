package controller;

public interface Command {
	void doCommand(String[] parameters);
	void setParameters(String[] parameters);
}
