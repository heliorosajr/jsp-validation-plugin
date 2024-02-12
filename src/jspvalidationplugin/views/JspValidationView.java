package jspvalidationplugin.views;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

import com.santander.jspvalidation.JspValidator;
import com.santander.jspvalidation.model.ValidationDTO;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class JspValidationView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "jspvalidationplugin.views.JspValidationView";

	@Inject IWorkbench workbench;
	
	private TableViewer viewer;
	 

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		@Override
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		@Override
		public Image getImage(Object obj) {
			return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	@Override
	public void createPartControl(Composite parent) {
	    Table table = new Table(parent, SWT.SINGLE);
	    table.setHeaderVisible(true);
	    table.setLinesVisible(true);

	    createHeaders(table);

	    List<ValidationDTO> dtos = null;
	    String root = "/Users/heliorosajr/Development/Workspaces/priceline/jspvalidation/web";

        try {
            JspValidator jspValidator = new JspValidator();
            jspValidator.setExport(true);
            dtos = jspValidator.validate(new File(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

	    if(dtos == null || dtos.isEmpty()) {
	        createLines(table, dtos);
	    }
	}
	
	private void createHeaders(Table table) {
	    TableColumn fileName = new TableColumn(table, SWT.LEFT);
        fileName.setText("File name");
        fileName.setWidth(100);

        TableColumn tag = new TableColumn(table, SWT.LEFT);
        tag.setText("Tag");
        tag.setWidth(100);

        TableColumn errorType = new TableColumn(table, SWT.LEFT);
        errorType.setText("Error type");
        errorType.setWidth(100);

        TableColumn description = new TableColumn(table, SWT.LEFT);
        description.setText("Description");
        description.setWidth(100);

        TableColumn filePath = new TableColumn(table, SWT.LEFT);
        filePath.setText("File path");
        filePath.setWidth(100);

        TableColumn element = new TableColumn(table, SWT.LEFT);
        element.setText("Element");
        element.setWidth(100);
	}

	private void createLines(Table table, List<ValidationDTO> dtos) {
	    for(ValidationDTO dto : dtos) {
	        TableItem row = new TableItem(table, SWT.NONE);
	        String[] data = new String[6];

	        // File name
	        data[0] = dto.getFileName();

	        // Tag
	        data[1] = dto.getTag();

	        // Error type
	        data[2] = dto.getErrorType();

	        // Description
	        data[3] = dto.getDescription();

	        // File path
	        data[4] = dto.getFilePath();

	        // Element
	        data[5] = dto.getElement();

	        row.setText(data);
	    }
	}
	
	@Override
	public void setFocus() {
		// do nothing
	}
}
