package view;

import java.io.IOException;

import controller.MyController;

public class MyView implements View {
	private MyController controller;

	public void start() throws IOException {
		CLI cli = new CLI(null, null, null);
		cli.start();
	} 
}
