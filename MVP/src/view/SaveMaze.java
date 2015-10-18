package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SaveMaze extends Dialog {
	private String name;
	private String file;
	private String path;
	
	  public SaveMaze(Shell parent) {
		  // Pass the default styles here
		  this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	  }
	public SaveMaze(Shell parant, int style) {
		super(parant, style);
		  setText("Save Maze.");
	}
	 public String open() {
		 Shell parent = getParent();
		 Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		 shell.setSize(200, 200);
		 shell.setText(getText());
		 shell.setLayout(new GridLayout(2, false));
		 // Your code goes here (widget creation, set result, etc).
		 Label nameLabel = new Label(shell, SWT.NULL);
		 nameLabel.setText("Name: ");
		 Text nameText = new Text(shell, SWT.SINGLE | SWT.BORDER);
		 
		 Label fileLabel = new Label(shell, SWT.NULL);
		 fileLabel.setText("File name: ");
		 Text fileText = new Text(shell, SWT.SINGLE | SWT.BORDER);
		 
		 Button pathButton = new Button(shell, SWT.PUSH);
		 pathButton.setText("Choose folder");
		 GridData gridData = new GridData();
		 gridData.horizontalSpan = 2;
		 pathButton.setLayoutData(gridData);
		 pathButton.addSelectionListener(new SelectionListener() {
		   	  @Override
		   	  public void widgetSelected(SelectionEvent arg0) {
		 		 DirectoryDialog dir = new DirectoryDialog(shell);
				 path = dir.open();
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
	   		  file = fileText.getText();
	   		  if(!name.equals("") && (!file.equals("")))
	   			  shell.close();
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
	      if((name != null) && (path != null) && (file != null))
	    	  return name + " " + path + "\\" + file + ".maz";
	      else 
	    	  return null;
	 }
}
