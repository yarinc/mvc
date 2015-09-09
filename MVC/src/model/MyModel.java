package model;

import controller.Controller;

public class MyModel implements Model {
	private Controller controller; 
	
	public void SetController(Controller controller) { 
		this.controller = controller;
	}
}
