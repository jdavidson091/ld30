package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {
    final ConnectedWorld game;

    private Music backgroundMusic;

    private OrthographicCamera camera;

    private Texture testTexture;
    private Rectangle testRectangle;

    public GameScreen(final ConnectedWorld gam) {
        this.game = gam;

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        //begin music
        backgroundMusic.setLooping(true);

        //create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenWidth, game.screenHeight);

        //TODO: for testing
        game.writer.write("aba aaa abb");
    }

    @Override
    public void render(float delta) {
        testTexture = new Texture(Gdx.files.internal("a.png"));
        testRectangle = new Rectangle();
        testRectangle.x = game.screenWidth / 2 - 20 / 2;
        testRectangle.y = 250;     // by default, rendering is performed with y-axis pointing upwards
        testRectangle.width = 20;
        testRectangle.height = 20;

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);  //set the clear color to blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //instructs OpenGL to clear the screen

        camera.update(); //updates the camera once per frame
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();



        game.writer.render();
        game.batch.draw(testTexture, testRectangle.x, testRectangle.y);




        game.bob.draw(); //TODO: make a method that draws all the assets in the game?
        game.batch.end();  //submit all drawing requests at once, speeding up rendering

        game.bob.render(); //TODO: make a render method for all the game assets?
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        backgroundMusic.play();
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
        game.bob.dispose();
        backgroundMusic.dispose();
    }

}