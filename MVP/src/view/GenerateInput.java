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
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

public class GenerateInput extends Dialog {
	private int width;
	private int length;
	private int height;
	private String name;
	
	  public GenerateInput(Shell parent) {
		  // Pass the default styles here
		  this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	  }

	  public GenerateInput(Shell parent, int style) {
		  // Let users override the default styles
		  super(parent, style);
		  setText("Generate");
	  }

	public String open() {
        Shell parent = getParent();
        Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        shell.setSize(200, 200);
        shell.setText(getText());
        shell.setLayout(new GridLayout(2, false));
        // Your code goes here (widget creation, set result, etc).
        Label lenLabel = new Label(shell, SWT.NULL);
        lenLabel.setText("Length: ");
        Spinner lenSpinner =new Spinner(shell, SWT.BORDER);
        lenSpinner.setMinimum(3);
        Label heightLabel = new Label(shell, SWT.NULL);
        heightLabel.setText("Height: ");
        Spinner widthSpinner = new Spinner(shell, SWT.BORDER);
        widthSpinner.setMinimum(3);
        Label widthLabel = new Label(shell, SWT.NULL);
        widthLabel.setText("Width: ");
        Spinner heightSpinner = new Spinner(shell, SWT.BORDER);
        heightSpinner.setMinimum(3);
        Label nameLabel = new Label(shell, SWT.NULL);
        nameLabel.setText("Name: ");
        Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);
        
        Button buttonOK = new Button(shell, SWT.PUSH);
        buttonOK.setText("Ok");
        buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
        Button buttonCancel = new Button(shell, SWT.PUSH);
        buttonCancel.setText("Cancel");
        
        text.addListener(SWT.Modify, new Listener() {
            public void handleEvent(Event event) {
              try {
                name = new String(text.getText());
                buttonOK.setEnabled(true);
              } catch (Exception e) {
                buttonOK.setEnabled(false);
              }
            }
          });
        
        buttonOK.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				length = Integer.parseInt(lenSpinner.getText());
				width = Integer.parseInt(widthSpinner.getText());
				height = Integer.parseInt(heightSpinner.getText());
				if(!text.getText().equals("")) {
					name = new String(text.getText());
					shell.dispose();
				}
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
				name = null;
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
        if (name == null)
        	return null;
        else
        	return name + " " + length + " " + height + " " + width;
	}
}
