package com.connectedworld.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.connectedworld.game.Bob;
import com.connectedworld.game.ConnectedWorld;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig () {
        return new GwtApplicationConfiguration(1024, 576);
    }

    @Override
    public ApplicationListener getApplicationListener () {
//        return new ConnectedWorld();
//        return new Bob();
        return new ConnectedWorld();
    }
}