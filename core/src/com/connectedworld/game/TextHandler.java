package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;

public class TextHandler {
    final ConnectedWorld game;
    private String enteredText;
    private StringBuffer inputBuilder;
    private Sound keyPress;

    public TextHandler(ConnectedWorld game) {
        this.game = game;
        inputBuilder = new StringBuffer();
        inputBuilder.append('>');

        keyPress = Gdx.audio.newSound(Gdx.files.internal("keypress.wav"));
    }

    public void write(String message) {
        /**                  TODO: clean this method up/out
         * writes the message to the writer
         * message is dynamically rendered based on the time passed
         * with the .render() method in the Writer, callked by the GameScreen (?)
         */
        game.outputWriter.writeOutputMessage(message);
    }

    public void clearOutputText() { game.outputWriter.clearText(); }
    public void clearInputText() {
        game.inputWriter.clearText();  //TODO: is this needed? probably not
        inputBuilder.delete(0, inputBuilder.length());
        inputBuilder.setLength(0);
        inputBuilder.append('>');
    }

    public void handle() {
        /**
         * What this method should do:
         *
         * 1. Find out the state of Bob (if at desk)
         * 2. If bob at desk, then check for key presses
         *
         *    2.1 If in an enteringText state, look for key presses and call addCharToInput
         *
         *    2.2 If bob presses ENTER, determine if he entered something valid
         *
         *          2.2.1 -> check state to see what options are, how to handle specific
         *                  options by calling methods ( grind(), pvp(), etc.)
         *
         *    2.3 If ESCAPE, then clear text, change Bob's state to standing up,
         *          return before rendering text
         *
         *    2.4 Lastly render text
         */
        if (game.bob.state == BobState.SEATED) {
            handleTextInput();
            handleEnter();
            handleExit();
        }

        game.outputWriter.renderOutputMessageDynamic();
        game.inputWriter.renderInputMessage(inputBuilder.toString());

        //for testing purposes
//        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//            game.writer.clearText();
//        }
//
//        game.writer.render();
    }

    public void handleTextInput() {
        /**
         * check for a-z key press, add it to the current text input
         */
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) { addCharToInput('a'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.B)) { addCharToInput('b'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.C)) { addCharToInput('c'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) { addCharToInput('d'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) { addCharToInput('e'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.F)) { addCharToInput('f'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.G)) { addCharToInput('g'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.H)) { addCharToInput('h'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.I)) { addCharToInput('i'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.J)) { addCharToInput('j'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.K)) { addCharToInput('k'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.L)) { addCharToInput('l'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.M)) { addCharToInput('m'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.N)) { addCharToInput('n'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.O)) { addCharToInput('o'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.P)) { addCharToInput('p'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) { addCharToInput('q'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.R)) { addCharToInput('r'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) { addCharToInput('s'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.T)) { addCharToInput('t'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.U)) { addCharToInput('u'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.V)) { addCharToInput('v'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) { addCharToInput('w'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.X)) { addCharToInput('x'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) { addCharToInput('y'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) { addCharToInput('z'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) { addCharToInput('0'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) { addCharToInput('1'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) { addCharToInput('2'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) { addCharToInput('3'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) { addCharToInput('4'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) { addCharToInput('5'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) { addCharToInput('6'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) { addCharToInput('7'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) { addCharToInput('8'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)) { addCharToInput('9'); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { addCharToInput(' '); }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
            removeLastInputChar();
        }

    }

    public void handleEnter() {
        /**
         * check to see if enter was pressed, handle text validation
         */
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.mmo.acceptUserInput(inputBuilder.toString());
            clearInputText();
        }
    }

    public void handleExit() {
        /**
         * check to see if ESC was pressed, clear current text
         */
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            //ENTER is pressed
            clearOutputText(); //TODO: this needed?
            clearInputText();
        }

    }

    protected void addCharToInput(Character c) {
        inputBuilder.append(c);
        keyPress.play();
        //NEVERMIND below, will want to render every frame anyway even if button wasn't pressed
//        game.inputWriter.renderInputMessage(inputBuilder.toString());
    }
    protected void removeLastInputChar() {
        if (inputBuilder.length() > 1) {
            inputBuilder.deleteCharAt(inputBuilder.length() - 1);
        }

        keyPress.play();
    }
}