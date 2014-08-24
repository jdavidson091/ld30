package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class MainMenuScreen implements Screen {

    final ConnectedWorld game;
    private long timePassed;
    private Rectangle titleScreen;
    private int screenCounter;
    private Sound screechSound;
    private ArrayList<Texture> titleTextures;

    OrthographicCamera camera;

    public MainMenuScreen(final ConnectedWorld gam) {
        game = gam;
        timePassed = TimeUtils.millis();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenWidth, game.screenHeight);
        screenCounter = 0;
        screechSound = Gdx.audio.newSound(Gdx.files.internal("screech.wav"));
//        screechSound = Gdx.audio.newSound(Gdx.files.internal("screech.wav"));

        titleScreen = new Rectangle();
        titleScreen.x = 0L;
        titleScreen.y = 0L;
        titleScreen.width = game.screenWidth;
        titleScreen.height = game.screenHeight;

        titleTextures = new ArrayList<Texture>();
        titleTextures.add(new Texture(Gdx.files.internal("titlemmo.png")));
        titleTextures.add(new Texture(Gdx.files.internal("titlecraft.png")));
        titleTextures.add(new Texture(Gdx.files.internal("titlea.png")));
        titleTextures.add(new Texture(Gdx.files.internal("titleconnected.png")));
        titleTextures.add(new Texture(Gdx.files.internal("titleworld.png")));
        titleTextures.add(new Texture(Gdx.files.internal("titleby.png")));
        titleTextures.add(new Texture(Gdx.files.internal("titlejohn.png")));
        titleTextures.add(new Texture(Gdx.files.internal("titledavid.png")));
        titleTextures.add(new Texture(Gdx.files.internal("titleson.png")));
        titleTextures.add(new Texture(Gdx.files.internal("wakeup.png")));

        screechSound.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (TimeUtils.timeSinceMillis(timePassed) > 800) {
            timePassed = TimeUtils.millis();
            screenCounter++;
            if (screenCounter >= titleTextures.size()) {
                screenCounter = titleTextures.size() - 1;
            }
            //play sound
            screechSound.play();

        }

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
//        game.font.draw(game.batch, "Connected World. ", 100, 150);
//        game.font.draw(game.batch, "Click to continue.", 100, 100);
//        if (TimeUtils.timeSinceMillis(timePassed) > 100)
        game.batch.draw(titleTextures.get(screenCounter), 0, 0);

        game.batch.end();

        if (Gdx.input.isTouched() && screenCounter == titleTextures.size() - 1) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        screechSound.dispose();
    }
}