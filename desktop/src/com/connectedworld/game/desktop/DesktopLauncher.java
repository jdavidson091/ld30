package com.connectedworld.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.connectedworld.game.Bob;
import com.connectedworld.game.ConnectedWorld;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

    //set the title, width and height of the window
    config.title = "Connected World";
    config.width = 1024;
    config.height = 576;

//    new LwjglApplication(new ConnectedWorld(), config);
      new LwjglApplication(new ConnectedWorld(), config);
	}
}
