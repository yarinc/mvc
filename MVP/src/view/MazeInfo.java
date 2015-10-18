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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MazeInfo extends Dialog {
	private String name;
	
	  public MazeInfo(Shell parent) {
		  // Pass the default styles here
		  this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	  }

	  public MazeInfo(Shell parent, int style) {
		  // Let users override the default styles
		  super(parent, style);
		  setText("Maze Information");
	  }
	  public String open() {
		  Shell parent = getParent();
		  Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	      shell.setSize(100, 100);
	      shell.setText(getText());
	      shell.setLayout(new GridLayout(2, false));
	      // Your code goes here (widget creation, set result, etc).
	      Label nameLabel = new Label(shell, SWT.NULL);
	      nameLabel.setText("Name: ");
	      Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);
	        
	      Button buttonOK = new Button(shell, SWT.PUSH);
	      buttonOK.setText("Ok");
	      buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
	      Button buttonCancel = new Button(shell, SWT.PUSH);
	      buttonCancel.setText("Cancel");
	        
	      buttonOK.addSelectionListener(new SelectionListener() {
	    	  @Override
	    	  public void widgetSelected(SelectionEvent arg0) {
	    		  name = text.getText();
	    		  if(!name.equals(""))
	    			  shell.dispose();
	    		  else { 
	    			  MessageBox enterName = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
	    			  enterName.setText("Error!");
	    			  enterName.setMessage("Invalid name.");
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
	      if (name != null)
	    	  return name;
	      else 
	    	  return null;
		}
}
