package KarnMenuTest.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import KarnMenuTest.Screens.GdxMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        new LwjglApplication(new GdxMenu(), config);
    }
}
