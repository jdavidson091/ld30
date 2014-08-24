package com.connectedworld.game;

import java.util.Random;

public class MMOGame {

    final ConnectedWorld game;
    private MMOState state;
    private String username;
    private String help1;
    private String help2;
    private Random rand;

    public MMOGame(ConnectedWorld game) {
        this.game = game;
        state = MMOState.AWAITING_INPUT;
        help1 = "you can do anything (sorta):";
        help2 = ">grind >level >pvp >dungeon >wander >help";
        rand = new Random();

        //TODO: below just for testing purposes...
//        firstTimeConnect();
    }

    public void firstTimeConnect() {
        state = MMOState.LOGIN;
//        game.outputWriter.clearText();
        game.outputWriter.writeOutputMessage("enter you username:");
    }

    public void acceptUserInput(String input) {
        /**
         * determine if user input was valid, and what to do
         * with it depending on the state of this game
         */
        input = input.toLowerCase();
        switch (state) {
            case LOGIN:
                username = input;
                state = MMOState.WAITING_FOR_OUTPUT;
//              game.outputWriter.clearText();
                game.outputWriter.writeOutputMessage("welcome back, " +
                        username + ", to... world of mmocraft!");
                game.outputWriter.writeOutputMessage("a whole new world for you to explore.");
                game.outputWriter.writeOutputMessage("a world where you belong.");
                game.outputWriter.writeOutputMessage(help1);
                game.outputWriter.writeOutputMessage(help2);

            case AWAITING_INPUT:
                if (input.equals("help")) {
                    processHelp();
                }
                else if (input.equals("grind"));
        }
//        if (state.equals(MMOState.LOGIN)) {
//            username = input;
//            state = MMOState.WAITING_FOR_OUTPUT;
////            game.outputWriter.clearText();
//            game.outputWriter.writeOutputMessage("welcome back, " +
//                username + ", to... world of mmocraft!");
//            game.outputWriter.writeOutputMessage("a whole new world for you to explore.");
//            game.outputWriter.writeOutputMessage("a world where you belong.");
//            game.outputWriter.writeOutputMessage(help1);
//            game.outputWriter.writeOutputMessage(help2);
//        }
//        else if (state.equals(MMOState.AWAITING_INPUT)) {
//            if (input.equals("help")) {
//                processHelp();
//            }
//            else if (input.equals("grind"));
//        }
    }

    /**
     * Called by writer once its done writing
     */
    public void canResumeGame() {
        state = MMOState.AWAITING_INPUT;
    }

    public void processHelp() {
        state = MMOState.WAITING_FOR_OUTPUT;
        game.outputWriter.writeOutputMessage(help1);
        game.outputWriter.writeOutputMessage(help2);
    }

    public void processGrind() {
        state = MMOState.WAITING_FOR_OUTPUT;

        //Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum == 1) {
            game.outputWriter.writeOutputMessage("you killed a kobold.");
        }
        else if (randomNum == 2) {
            game.outputWriter.writeOutputMessage("you killed a boar.");

        }
        else if (randomNum == 3) {
            game.outputWriter.writeOutputMessage("you killed a useless trash mob.");
        }
        game.outputWriter.writeOutputMessage("you gain a small amount of xp.");
    }

    public void processLevel() {

    }

    public void processPvp() {

    }

    public void processDungeon() {

    }

    public enum MMOState {
        LOGIN, AWAITING_INPUT, WAITING_FOR_OUTPUT
    }
}