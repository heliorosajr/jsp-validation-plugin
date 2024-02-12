package jspvalidationplugin.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;

/**
 * Class used to initialize default preference values.
 */
public class JsonValidationPreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
	    IPreferenceStore store = PlatformUI.getPreferenceStore();
		store.setDefault(JspValidationPreferencePage.SETTINGS_PATH, "");
	}

}
