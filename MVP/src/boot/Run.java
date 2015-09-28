package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import view.CLI;
import view.MyCLIView;

public class Run {

	public static void main(String[] args) {
		CLI cli = new CLI(new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(System.out));
		MyCLIView ui = new MyCLIView(cli);
		cli.setView(ui);
		MyModel m = new MyModel();
		Presenter p = new Presenter(ui,m);
		ui.addObserver(p);
		m.addObserver(p);
		ui.start();
	}
}
