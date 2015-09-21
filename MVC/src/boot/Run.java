package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.MyController;
import model.MyModel;
import view.CLI;
import view.MyView;

public class Run {

	public static void main(String[] args) {
		CLI cli = new CLI(new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(System.out));
		MyModel model = new MyModel();
		MyView view = new MyView(cli);
		cli.setView(view);
		MyController controller = new MyController(model, view); 
		model.SetController(controller);
		view.SetController(controller);
		view.start();
	}

}
