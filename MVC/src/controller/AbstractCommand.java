package controller;

import model.Model;
import view.View;

public abstract class AbstractCommand {
	protected View view;
	protected Model model;
	protected String[] parameters;
	
	public AbstractCommand(View view, Model model) { 
		this.view = view;
		this.model = model; 
	}
	public void setParameters(String[] parameters) { 
		this.parameters = parameters; 
	}
}
