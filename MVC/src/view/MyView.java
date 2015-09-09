package view;

import controller.Controller;

public class MyView implements View {
	private Controller controller; 
	
	public void SetController(Controller controller) { 
		this.controller = controller; 
	}
}
