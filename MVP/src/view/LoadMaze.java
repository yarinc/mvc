package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LoadMaze extends Dialog {
	
	private String name;
	private String file;
	
	  public LoadMaze(Shell parent) {
		  // Pass the default styles here
		  this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	  }
	public LoadMaze(Shell parant, int style) {
		super(parant, style);
		  setText("Save Maze.");
	}
	public String open() {
		 Shell parent = getParent();
		 Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		 shell.setSize(200, 150);
		 shell.setText(getText());
		 shell.setLayout(new GridLayout(2, false));
		 // Your code goes here (widget creation, set result, etc).
		 Label nameLabel = new Label(shell, SWT.NULL);
		 nameLabel.setText("Maze name: ");
		 Text nameText = new Text(shell, SWT.SINGLE | SWT.BORDER);
		 Button selectFile = new Button(shell, SWT.PUSH);
		 GridData bla = new GridData();
		 bla.horizontalSpan =2;
		 selectFile.setLayoutData(bla);
		 selectFile.setText("Choose file");
		 
		 selectFile.addSelectionListener(new SelectionListener() {
		   	  @Override
		   	  public void widgetSelected(SelectionEvent arg0) {
		   		  FileDialog selectFile = new FileDialog(shell);
		   		  String[] filterExt = {"*.maz"};
		   		  selectFile.setFilterExtensions(filterExt);
		   		  file = selectFile.open();
		   	  }
		   	  @Override
		   	  public void widgetDefaultSelected(SelectionEvent arg0) {}
		      });
		 
		 Button buttonOK = new Button(shell, SWT.PUSH);
	     buttonOK.setText("Ok");
	     buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
	     Button buttonCancel = new Button(shell, SWT.PUSH);
	     buttonCancel.setText("Cancel");
	        
	     buttonOK.addSelectionListener(new SelectionListener() {
	   	  @Override
	   	  public void widgetSelected(SelectionEvent arg0) {
	   		  name = nameText.getText();
	   		  if(!name.equals("") && (!file.equals("")))
	   			  shell.dispose();
	    		  else { 
	    			  MessageBox enterName = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
	    			  enterName.setText("Error!");
	    			  enterName.setMessage("Missing parameters");
	    			  enterName.open();		
	    		  }
	   	  }
	   	  @Override
	   	  public void widgetDefaultSelected(SelectionEvent arg0) {}
	      });

	      buttonCancel.addSelectionListener(new SelectionListener() {
	    	  @Override
	    	  public void widgetSelected(SelectionEvent arg0) {
	    		  shell.close();
	    	  }
	    	  @Override
	    	  public void widgetDefaultSelected(SelectionEvent arg0) {}
	      });
	          
	      shell.addListener(SWT.Traverse, new Listener() {
	    	  public void handleEvent(Event event) {
	    		  if(event.detail == SWT.TRAVERSE_ESCAPE)
	    			  event.doit = false;
	    	  }
	      });
	      shell.open();
	      Display display = parent.getDisplay();
	      while (!shell.isDisposed()) {
	    	  if (!display.readAndDispatch()) display.sleep();
	      }
	      if((file != null) && (name != null)) 
	      	return file + " " + name;
	      else
	    	  return null;
	 }
}
