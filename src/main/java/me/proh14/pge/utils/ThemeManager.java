package me.proh14.pge.utils;

import javafx.collections.ObservableList;
import javafx.scene.Scene;

public final class ThemeManager {

    public static void setTheme(Theme theme, Scene scene) {
        ObservableList<String> stylesheets = scene.getStylesheets();

        if (stylesheets.isEmpty())
            stylesheets.add(theme.getPath());
        else
            stylesheets.set(0, theme.getPath());
    }

    public static void setTheme(Theme theme, Scene... scenes) {
        for (Scene scene : scenes)
            setTheme(theme, scene);
    }

}
