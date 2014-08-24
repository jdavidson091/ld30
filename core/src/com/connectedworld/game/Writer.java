package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.LinkedList;

public class Writer {

    final ConnectedWorld game;
    private Letters letters;
    private String displayedOutputMessage;
    private String fullOutputMessage;
//    private String inputMessage;
    private long timeOutputMessageStart;
    private long timeOutputMessageEnd;
    private long renderTimeSpeed;
    private int writingBoxX;
    private int writingBoxY;
    private int inputWritingBoxX;
    private int inputWritingBoxY;
    private boolean currentlyOutputting;

    private LinkedList<String> messageQueue;

    private Music typeSound;

    public Writer(final ConnectedWorld game) {
        this.game = game;
        letters = new Letters(game);
        writingBoxX = 75;
        writingBoxY = 500;
        inputWritingBoxX = 50;
        inputWritingBoxY = 380;
        messageQueue = new LinkedList<String>();
        currentlyOutputting = false;

        //for output style writing
        fullOutputMessage = "";
        displayedOutputMessage = "";
        timeOutputMessageStart = 0L;
        timeOutputMessageEnd = 0L;
        renderTimeSpeed = 50L;

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
         *
         * TODO: have the pausing here? once rendering is done?
         * TODO: have a queue of inputs? Take next in line?
         */
//        fullOutputMessage = message;
//        timeOutputMessageStart = TimeUtils.millis();
//        typeSound.play();

        messageQueue.add(message);
        if (!currentlyOutputting) {
            currentlyOutputting = true;
            fullOutputMessage = messageQueue.removeFirst();
            timeOutputMessageStart = TimeUtils.millis();
            typeSound.play();
        }

//        fullOutputMessage = message;
//        timeOutputMessageStart = TimeUtils.millis();
//        typeSound.play();

    }


    public void renderOutputMessageDynamic() {
        /**
         * "renders" whatever next is in the fullOutputMessage dynamically
         */
        if (fullOutputMessage.length() < 1) { return; }

        int renderLength = (int) (TimeUtils.timeSinceMillis(timeOutputMessageStart) / renderTimeSpeed) ;
        if (renderLength > fullOutputMessage.length()) {
            renderLength = fullOutputMessage.length();
            if (typeSound.isPlaying()) { //indicator that first time stopping
                typeSound.stop();
                timeOutputMessageEnd = TimeUtils.millis();
            }
            if (TimeUtils.timeSinceMillis(timeOutputMessageEnd) > 900) {
                //can resume output queue
                if (messageQueue.size() > 0) {
                    currentlyOutputting = true;
                    fullOutputMessage = messageQueue.removeFirst();
                    timeOutputMessageStart = TimeUtils.millis();
                    typeSound.play();
                    renderLength = 0;
                } else {
                    currentlyOutputting = false;

                    game.mmo.canResumeGame(); //lets the user continue input.
                }
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
            if (nextX > game.screenWidth - letters.getLetterWidth()) {
                nextX = writingBoxX;
                nextY = writingBoxY - letters.getLetterWidth();
            }
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
//        char[] msgChars = inputMessage.toCharArray();
//        int nextX = inputWritingBoxX;
//        int nextY = inputWritingBoxY;
//        //TODO: for now, just attempt to write at origin
//        for (char c : msgChars) {
//            letters.draw(c, nextX, nextY);
//            nextX += letters.getLetterWidth(); //todo: when to wrap to next line
//            //TODO: a test for when to move down on the page
//        }
        renderInputMessage(inputMessage, inputWritingBoxX, inputWritingBoxY);
    }

    public void renderInputMessage(String inputMessage, int x, int y) {
        /**
         * renders immediately the string that is past
         *      (Don't handle the sound here, this should render even when user isn't entering)
         */
        char[] msgChars = inputMessage.toCharArray();
        int nextX = x;
        int nextY = y;
        for (char c : msgChars) {
            letters.draw(c, nextX, nextY);
            nextX += letters.getLetterWidth();
        }
    }


    public void renderSingleChar(char c, int x, int y) {
        letters.draw(c, x, y);
    }

    /**
     * clears the text currently displayed
     */
    public void clearText() {
        fullOutputMessage = "";

    }
}