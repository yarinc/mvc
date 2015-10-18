package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import view.CLI;
import view.MyCLIView;
import view.MyGUIView;

public class Run {

	public static void main(String[] args) {
//		CLI cli = new CLI(new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(System.out));
//		MyCLIView ui = new MyCLIView(cli);
		MyGUIView ui = new MyGUIView("Mazer", 500, 500);
//		cli.setView(ui);
		MyModel m = new MyModel();
		Presenter p = new Presenter(ui,m);
		ui.addObserver(p);
		m.addObserver(p);
		ui.start();
	}
}
