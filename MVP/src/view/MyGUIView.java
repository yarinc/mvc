package view;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class MyGUIView extends BasicWindow implements View {
	
	private MazeBoard board;
	private Button displayButton;
	private Button hintButton;
	private Button solveButton;
	private String currentMaze;
	private Solution<Position> solution;
	Timer timer;
	TimerTask task;
	public MyGUIView(String title, int width, int height) {
		super(title, width, height);
	}

	@Override
	public void start() {
		this.run();
	}

	@Override
	public void PrintStringArray(String[] string) {
		

	}
	
	@Override
	public void printMaze(Maze3d maze) {
		board.setFullMaze(maze);
		board.setPlayer(maze.getStartPosition());
		board.setStart(new Position(maze.getStartPosition()));
		board.setEnd(maze.getGoalPosition());
		board.paint();
		board.redraw();
		board.addKeyListener(new KeyAdapter()
		{	
			public void keyPressed(KeyEvent e)
			{					
				if(e.keyCode == SWT.ARROW_UP) {
					board.moveUp();
					board.redraw();
					if(board.player.equals(board.end)) {
						board.removeKeyListener(this);
						hintButton.setEnabled(false);
						solveButton.setEnabled(false);
					}
				}
				
				else if(e.keyCode == SWT.ARROW_DOWN) {
					board.moveDown();
					board.redraw();
					if(board.player.equals(board.end)) {
						board.removeKeyListener(this);
						hintButton.setEnabled(false);
						solveButton.setEnabled(false);
					}
				}
				
				else if(e.keyCode == SWT.ARROW_LEFT) {
					board.moveLeft();
					board.redraw();
					if(board.player.equals(board.end)) {
						board.removeKeyListener(this);
						hintButton.setEnabled(false);
						solveButton.setEnabled(false);
					}
				}
				else if(e.keyCode == SWT.ARROW_RIGHT) {
					board.moveRight();
					board.redraw();
					if(board.player.equals(board.end)) {
						board.removeKeyListener(this);
						hintButton.setEnabled(false);
						solveButton.setEnabled(false);
					}
				}
				else if(e.keyCode == SWT.PAGE_UP) {
					if((board.player.getLocation().getY() < maze.getBounds().getY()) && (maze.getMazeValue(new Position(board.player.getLocation().getX(),board.player.getLocation().getY() + 1,board.player.getLocation().getZ())) == 0)) {
						board.moveUpLvl();
						board.redraw();
						if(board.player.equals(board.end)) { 
							board.removeKeyListener(this);
							hintButton.setEnabled(false);
							solveButton.setEnabled(false);
						}
					}
				}
				else if(e.keyCode == SWT.PAGE_DOWN) {
					if((board.player.getLocation().getY() > 0) && (maze.getMazeValue(new Position(board.player.getLocation().getX(),board.player.getLocation().getY() - 1,board.player.getLocation().getZ())) == 0)) {
						board.moveDownLvl();
						board.redraw();
						if(board.player.equals(board.end)) {
							board.removeKeyListener(this);
							hintButton.setEnabled(false);
							solveButton.setEnabled(false);
						}
					}
				}
			}
		});
	}

	@Override
	public void printString(String string) {
		if(!string.startsWith("Solution")) {
			display.syncExec(new Runnable() {
				public void run() {
					MessageBox message = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
					message.setMessage(string);
					message.setText("Message");
					message.open();
				}
			});
		}
	}

	@Override
	public void PrintCrossMaze(int[][] maze2d) {
		
	}

	@Override
	public void inputToPresenter(String line) {
		this.setChanged();
		this.notifyObservers(line);
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2, false));
		Menu bar = new Menu (shell, SWT.BAR);
		shell.setMenuBar(bar);
		MenuItem fileItem = new MenuItem (bar, SWT.CASCADE);
		fileItem.setText ("File");
		Menu submenu = new Menu (shell, SWT.DROP_DOWN );
		fileItem.setMenu (submenu);
		MenuItem openPropertiesItem = new MenuItem (submenu, SWT.PUSH);
		openPropertiesItem.setText("&Open properties");
		MenuItem exitItem = new MenuItem (submenu, SWT.PUSH);
		exitItem.setText("&Exit");
		
		MenuItem options = new MenuItem (bar, SWT.CASCADE);
		options.setText("Options");
		Menu subOptions = new Menu (shell, SWT.DROP_DOWN);
		options.setMenu(subOptions);
		MenuItem saveItem = new MenuItem (subOptions, SWT.PUSH);
		saveItem.setText("&Save");
		saveItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SaveMaze save = new SaveMaze(shell);
				String parameters = save.open();
				if(parameters != null) {
					String command = "save maze " + parameters;
					inputToPresenter(command);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		MenuItem loadItem = new MenuItem (subOptions, SWT.PUSH);
		loadItem.setText("&Load");
		loadItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				LoadMaze load = new LoadMaze(shell);
				String parameters = load.open();
				if(parameters != null) {
					String command = "load maze " + parameters;
					inputToPresenter(command);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		
		MenuItem SizeRAMItem = new MenuItem (subOptions, SWT.PUSH);
		SizeRAMItem.setText("&Size on RAM");
		SizeRAMItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MazeInfo sizeRAM = new MazeInfo(shell);
				String parameters = sizeRAM.open();
				if (parameters != null) {
					String command = "maze size " + parameters;
					inputToPresenter(command);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		MenuItem SizeDiskItem = new MenuItem (subOptions, SWT.PUSH);
		SizeDiskItem.setText("&Size on disk");
		SizeDiskItem.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MazeInfo sizeDisk = new MazeInfo(shell);
				String parameter = sizeDisk.open();
				if(parameter != null) { 
					String command = "file size " + parameter;
					inputToPresenter(command);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		
		
		
		
		openPropertiesItem.setText("Open properties");
		Button generateButton=new Button(shell, SWT.PUSH);
		generateButton.setText("Generate");
		generateButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		generateButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GenerateInput input = new GenerateInput(shell);
				String parameter = input.open();
				if (parameter != null) {
					String command = "generate 3d maze " + parameter;
					inputToPresenter(command);
					displayButton.setEnabled(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		MazeBoard mazeBoard = new Maze2D(shell, SWT.BORDER);
		mazeBoard.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,5));
		board = mazeBoard;
		
		displayButton=new Button(shell, SWT.PUSH);
		displayButton.setText("Display");
		displayButton.setEnabled(false);
		displayButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		displayButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MazeInfo maze = new MazeInfo(shell);
				String parameters = maze.open();
				if(parameters != null) {
					MazeBoard mazeBoard = new Maze2D(shell, SWT.BORDER);
					mazeBoard.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,5));
					String command = "display " + parameters;
					currentMaze = parameters;
					inputToPresenter(command);
					hintButton.setEnabled(true);
					solveButton.setEnabled(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		hintButton =new Button(shell, SWT.PUSH);
		hintButton.setText("Hint");
		hintButton.setEnabled(false);
		hintButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		hintButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String command = "from " + currentMaze + " " + board.player.getLocation().getX() + " " + board.player.getLocation().getY() + " " + board.player.getLocation().getZ();
				inputToPresenter(command);
				solution.getSolution().remove(0);
				board.setCharacterPosition(solution.getSolution().get(0));
				if(board.player.getLocation().equals(board.end.getLocation())) {
					hintButton.setEnabled(false);
					displayButton.setEnabled(false);
					solveButton.setEnabled(false);
				}
				board.redraw();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		solveButton =new Button(shell, SWT.PUSH);
		solveButton.setText("Solve");
		solveButton.setEnabled(false);
		solveButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		solveButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				hintButton.setEnabled(false);
				displayButton.setEnabled(false);
				solveButton.setEnabled(false);
				String command = "from " + currentMaze + " " + board.player.getLocation().getX() + " " + board.player.getLocation().getY() + " " + board.player.getLocation().getZ();
				inputToPresenter(command);
				solution.getSolution().remove(0);
				timer=new Timer();
						task=new TimerTask() {
							@Override
							public void run() {
								display.syncExec(new Runnable() {
									@Override
									public void run() {
										if(board.player.getLocation().equals(board.end.getLocation())) {
											task.cancel();
											timer.cancel();
										}
										else {
											board.setCharacterPosition(solution.getSolution().get(0));
											board.redraw();
											solution.getSolution().remove(0);
										}
									}
								});
							}
						};				
						timer.scheduleAtFixedRate(task, 0, 500);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
	}

	@Override
	public void handleSolution(Solution<Position> solution) {
		this.solution = solution;
	}
}
