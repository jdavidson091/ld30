package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Bob{
    final ConnectedWorld game;

    public BobState state;
    private Texture bobRight, bobLeft, bobAtDesk, desk, bed;
    private Sound stepSound;

    private Rectangle characterModel;
    private facingDirection direction;

    private long timeSinceLastStep = -1L;
    private int walkSpeed = 100;
    private int stepSoundDiff = 400;
    private int bobHeight = 150;
    private int bobWidth = 80;
    private int deskX;
    private int deskY;
    private int bedX = 20;
    private int bedY = 20;
    private long gameStart;

    private int deskXPos;
    private boolean inEndGame;

    public Bob(final ConnectedWorld game) {
        this.game = game;
        deskXPos = game.screenWidth - 50;
        direction = facingDirection.RIGHT;
        deskX = game.screenWidth - 300;
        deskY = 20;
        gameStart = TimeUtils.millis();
        inEndGame = false;

        //TODO: change his initial state to in bed, this is for testing
        state = BobState.SLEEPING;

        //load image textures
        bobRight = new Texture(Gdx.files.internal("bobright.png"));
        bobLeft = new Texture(Gdx.files.internal("bobleft.png"));
        bobAtDesk = new Texture(Gdx.files.internal("bobatdesk.png"));
        desk = new Texture(Gdx.files.internal("desk.png"));
        bed = new Texture(Gdx.files.internal("bed.png"));

        //load sound asses
        stepSound = Gdx.audio.newSound(Gdx.files.internal("step.wav"));

        characterModel = new Rectangle();
        characterModel.x = 50;
        characterModel.y = 20;     // by default, rendering is performed with y-axis pointing upwards
        characterModel.width = bobWidth;
        characterModel.height = bobHeight;
    }

    public void draw() {

        game.batch.draw(desk, deskX, deskY);
        game.batch.draw(bed, bedX, bedY);
        if (TimeUtils.timeSinceMillis(gameStart) < 2000) {
            game.inputWriter.renderInputMessage("Left and right to explore.", 20, 700);
        }

        if (state.equals(BobState.SEATED)) {
            game.batch.draw(bobAtDesk, deskX, deskY);
        }
        else if (direction.equals(facingDirection.LEFT)) {
            game.batch.draw(bobLeft, characterModel.x, characterModel.y);
        }
        else if (direction.equals(facingDirection.RIGHT)) {
            game.batch.draw(bobRight, characterModel.x, characterModel.y);
        }

        if (characterModel.x > deskX - 50 && characterModel.x < deskX + 50) {
            if (!state.equals(BobState.SEATED)) {
                game.inputWriter.renderInputMessage("spacebar", deskX - 200, deskY + 300);
            }
        }



    }
    public void render() {

        //first render "press space to sit down" if within distance
        if (characterModel.x > deskX - 50 && characterModel.x < deskX + 50) {
            if (!state.equals(BobState.SEATED)) {
//                game.inputWriter.renderInputMessage("spacebar", deskX - 200, deskY + 300);
                //keyboard input
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    state = BobState.SEATED;
                    if (game.mmo.hasntConnectedYet()) {
                        game.mmo.firstTimeConnect();
                    }
                    else {

                    }
                }
            }
        }


        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !state.equals(BobState.SEATED)) {
            characterModel.x -= walkSpeed * Gdx.graphics.getDeltaTime(); //ensures we move at constant speed
            playStep();
            direction = facingDirection.LEFT;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !state.equals(BobState.SEATED)) {
            characterModel.x += walkSpeed * Gdx.graphics.getDeltaTime();
            playStep();
            direction = facingDirection.RIGHT;
        }

        if (state.equals(BobState.SEATED)) {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                state = BobState.WALKING;
                direction = facingDirection.LEFT;
            }
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
        bobAtDesk.dispose();
        bobLeft.dispose();
        bobRight.dispose();
        stepSound.dispose();
    }

    private enum facingDirection {
        RIGHT, LEFT
    }
}