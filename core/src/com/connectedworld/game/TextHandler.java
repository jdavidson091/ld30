package com.connectedworld.game;

public class TextHandler {
    final ConnectedWorld game;

    public TextHandler(ConnectedWorld game) {
        this.game = game;
    }

    public void write(String message) {
        /**
         * writes the message to the writer
         * message is dynamically rendered based on the time passed
         * with the .render() method in the Writer, callked by the GameScreen (?)
         */
        game.writer.write(message);
    }
}