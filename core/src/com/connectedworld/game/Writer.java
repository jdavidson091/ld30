package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.TimeUtils;

public class Writer {

    final ConnectedWorld game;
    private Letters letters;
    private String displayMessage;
    private String fullMessage;
    private long timeMessageStart;
    private long renderTimeSpeed;
    private int writingBoxX;
    private int writingBoxY;

    private Music typeSound;

    public Writer(final ConnectedWorld game) {
        this.game = game;
        letters = new Letters(game);
        writingBoxX = 100;
        writingBoxY = 100;
        fullMessage = "";
        displayMessage = "";
        timeMessageStart = 0L;
        renderTimeSpeed = 100L;

        typeSound = Gdx.audio.newMusic(Gdx.files.internal("typing.wav"));
    }

    /**
     * specify a "rectangle" to write to
     */
    public void setUpWritingBox(int xCoordinate, int yCoordinate) {
        writingBoxX = xCoordinate;
        writingBoxY = yCoordinate;
    }

    /**
     * you should be able to pass this class a string,
     * and it should write it out, fitted, int the specified rectangle.
     */
    public void write(String message) {
//        char[] msgChars = message.toCharArray();
//        int nextX = writingBoxX;
//        int nextY = writingBoxY;
//
//        //TODO: for now, just attempt to write at origin
//        for (char c : msgChars) {
//            letters.draw(c, nextX, nextY);
//            nextX += letters.getLetterWidth(); //todo: when to wrap to next line
//            //TODO: a test for when to move down on the page
//        }
        /**
         * sets the message to be written when rendering dynamically
         */
        fullMessage = message;
        timeMessageStart = TimeUtils.millis();
        typeSound.play();
    }

    public void render() {
        /**
         * "renders" whatever next is in the currentMessage dynamically
         */
        //TODO: test this
        int renderLength = (int) (TimeUtils.timeSinceMillis(timeMessageStart) / renderTimeSpeed) ;
        if (renderLength > fullMessage.length()) {
            renderLength = fullMessage.length();
            if (typeSound.isPlaying()) {
                typeSound.stop();
            }
        }

        displayMessage = fullMessage.substring(0, renderLength);

        char[] msgChars = displayMessage.toCharArray();
        int nextX = writingBoxX;
        int nextY = writingBoxY;

        //TODO: for now, just attempt to write at origin
        for (char c : msgChars) {
            letters.draw(c, nextX, nextY);
            nextX += letters.getLetterWidth(); //todo: when to wrap to next line
            //TODO: a test for when to move down on the page
        }
    }
}