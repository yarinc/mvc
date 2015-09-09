package controller;


import model.Model;
import view.View;

public class MyController implements Controller {
	private Model model; 
	private View view;
	
	public MyController(Model model, View view) { 
		this.model = model; 
		this.view = view;
	}
}
