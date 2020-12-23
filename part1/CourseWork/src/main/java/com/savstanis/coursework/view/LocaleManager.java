package com.savstanis.coursework.view;

import java.util.ResourceBundle;

public class LocaleManager {
    private static LocaleManager localeManager;
    private ResourceBundle resourceBundle;

    private LocaleManager() {
        resourceBundle = ResourceBundle.getBundle("Messages");
    }

    public static LocaleManager getInstance() {
        if (localeManager == null) {
            localeManager = new LocaleManager();
        }
        return localeManager;
    }

    public void updateLocale() {
        resourceBundle = ResourceBundle.getBundle("Messages");
    }

    public String getString(String message) {
        return resourceBundle.getString(message);
    }
}
