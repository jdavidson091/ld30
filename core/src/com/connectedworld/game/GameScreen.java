package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen {
    final ConnectedWorld game;
    private Music backgroundMusic;
    private OrthographicCamera camera;



    public GameScreen(final ConnectedWorld gam) {
        this.game = gam;

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        //begin music
        backgroundMusic.setLooping(true);

        //create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenWidth, game.screenHeight);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);  //set the clear color to blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //instructs OpenGL to clear the screen

        camera.update(); //updates the camera once per frame

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        //METHOD FOR HANDLING TEXT WRITING
        game.textHandler.handle();
        //render the writer

//        if (game.mmo.getLevelNum() < 2) {
            if (game.bob.state.equals(BobState.SEATED)) {
                game.mmo.drawXp();
            }

            game.bob.draw();
            game.batch.end();  //submit all drawing requests at once, speeding up rendering
            game.bob.render();
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