package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.TimeUtils;

public class Writer {

    final ConnectedWorld game;
    private Letters letters;
    private String displayedOutputMessage;
    private String fullOutputMessage;
//    private String inputMessage;
    private long timeOutputMessageStart;
    private long renderTimeSpeed;
    private int writingBoxX;
    private int writingBoxY;
    private int inputWritingBoxX;
    private int inputWritingBoxY;

    private Music typeSound;

    public Writer(final ConnectedWorld game) {
        this.game = game;
        letters = new Letters(game);
        writingBoxX = 100;
        writingBoxY = 400;
        inputWritingBoxX = 100;
        inputWritingBoxY = 100;

        //for output style writing
        fullOutputMessage = "";
        displayedOutputMessage = "";
        timeOutputMessageStart = 0L;
        renderTimeSpeed = 100L;

        //for input style writing
//        inputMessage = "";        //TODO: remove this, confirm it is in TextHandler

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
    public void writeOutputMessage(String message) {
        /**
         * sets the message to be written when rendering dynamically
         */
        fullOutputMessage = message;
        timeOutputMessageStart = TimeUtils.millis();
        typeSound.play();
    }


    public void renderOutputMessageDynamic() {
        /**
         * "renders" whatever next is in the fullOutputMessage dynamically
         */
        //TODO: test this
        if (fullOutputMessage.length() < 1) { return; }

        int renderLength = (int) (TimeUtils.timeSinceMillis(timeOutputMessageStart) / renderTimeSpeed) ;
        if (renderLength > fullOutputMessage.length()) {
            renderLength = fullOutputMessage.length();
            if (typeSound.isPlaying()) {
                typeSound.stop();
            }
        }

        displayedOutputMessage = fullOutputMessage.substring(0, renderLength);
        char[] msgChars = displayedOutputMessage.toCharArray();
        int nextX = writingBoxX;
        int nextY = writingBoxY;

        //TODO: for now, just attempt to write at origin
        for (char c : msgChars) {
            letters.draw(c, nextX, nextY);
            nextX += letters.getLetterWidth(); //todo: when to wrap to next line
            //TODO: a test for when to move down on the page
        }
    }

//    public void addCharToInput(Character c) {        //TODO: This should be in the TextHandler
//
//    }

    //TODO: condense this method with above somehow?
    public void renderInputMessage(String inputMessage) {
        /**
         * renders immediately the string that is past
         *      (Don't handle the sound here, this should render even when user isn't entering)
         */
        //TODO: write this out, go through each char perhaps?
        char[] msgChars = inputMessage.toCharArray();
        int nextX = inputWritingBoxX;
        int nextY = inputWritingBoxY;
        //TODO: for now, just attempt to write at origin
        for (char c : msgChars) {
            letters.draw(c, nextX, nextY);
            nextX += letters.getLetterWidth(); //todo: when to wrap to next line
            //TODO: a test for when to move down on the page
        }
    }

    /**
     * clears the text currently displayed
     */
    public void clearText() {
        fullOutputMessage = "";

    }
}