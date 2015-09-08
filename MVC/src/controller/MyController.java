package controller;

import model.MyModel;
import view.MyView;

public class MyController implements Controller {
	private MyModel model; 
	private MyView view;
	
	public MyController(MyModel model, MyView view) { 
		this.model = model; 
		this.view = view;
	}
}
