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

        //TODO: for testing
        game.outputWriter.writeOutputMessage("versace versace");
        game.mmo.firstTimeConnect();
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
//        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//            game.writer.clearText();
//        }
//        game.writer.render();

//        game.batch.draw(testTexture, testRectangle.x, testRectangle.y);
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