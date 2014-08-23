package com.connectedworld.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ConnectedWorld extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public int screenWidth = 1024;              //game
    public int screenHeight = 576;              //game
    public Bob bob;

    public void create() {
        bob = new Bob(this);
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }
}