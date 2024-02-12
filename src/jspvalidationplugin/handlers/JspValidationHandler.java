package jspvalidationplugin.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import jspvalidationplugin.preferences.JspValidationPreferencePage;
import jspvalidationplugin.views.JspValidationView;

/** <b>Warning</b> : 
  As explained in <a href="http://wiki.eclipse.org/Eclipse4/RCP/FAQ#Why_aren.27t_my_handler_fields_being_re-injected.3F">this wiki page</a>, it is not recommended to define @Inject fields in a handler. <br/><br/>
  <b>Inject the values in the @Execute methods</b>
*/
public class JspValidationHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell s) throws PartInitException {

	    String path = PlatformUI.getPreferenceStore().getString(JspValidationPreferencePage.SETTINGS_PATH);

	    if(path == null || path.isEmpty()) {
	        MessageDialog.openError(s, "Configuration error", "Please configure the JSON settings file in: Preferences > JSP Validation");
	    }

	    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(JspValidationView.ID);

	}


}
