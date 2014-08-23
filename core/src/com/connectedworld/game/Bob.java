package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Bob{
    final ConnectedWorld game;

    private Texture bobImage;
    private Sound stepSound;

    private Rectangle characterModel;

    private long timeSinceLastStep = -1L;
    private int walkSpeed = 100;
    private int stepSoundDiff = 400;
    private int bobHeight = 64;
    private int bobWidth = 64;

//    public void create() {          //TODO: make this the constructor, passed game object
    public Bob(final ConnectedWorld game) {
        this.game = game;

        //load image textures
        bobImage = new Texture(Gdx.files.internal("droplet.png"));

        //load sound asses
        stepSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));

        characterModel = new Rectangle();
        characterModel.x = game.screenWidth / 2 - bobWidth / 2;
        characterModel.y = 20;     // by default, rendering is performed with y-axis pointing upwards
        characterModel.width = bobWidth;
        characterModel.height = bobHeight;

    }

    public void draw() {
        game.batch.draw(bobImage, characterModel.x, characterModel.y);
    }
    public void render() {

        //keyboard input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            characterModel.x -= walkSpeed * Gdx.graphics.getDeltaTime(); //ensures we move at constant speed
            playStep();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            characterModel.x += walkSpeed * Gdx.graphics.getDeltaTime();
            playStep();
        }

        //keep model within screen limits
        if (characterModel.x < 0) { characterModel.x = 0; }
        if (characterModel.x > game.screenWidth - bobWidth) { characterModel.x = game.screenWidth - bobWidth; }
    }
    private void playStep() {

        if (timeSinceLastStep < 0 || TimeUtils.timeSinceMillis(timeSinceLastStep) > stepSoundDiff) {
            timeSinceLastStep = TimeUtils.millis();
            stepSound.play();
        }

    }

    public void dispose() {
        bobImage.dispose();
        stepSound.dispose();
    }
}