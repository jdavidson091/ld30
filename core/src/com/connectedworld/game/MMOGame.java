package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import org.w3c.dom.css.Rect;

import java.util.Random;

public class MMOGame {

    final ConnectedWorld game;
    private MMOState state;
    private String username;
    private String help1;
    private String help2;
    private Random rand;
    private int xpAmount;
    private int xpLevelCap;
    private int levelNum;
    private int questsAvailable;
    private Texture unfilledXpTex;
    private Texture filledXpTex;
    private Rectangle unfilledXp;
    private Rectangle filledXp;

    public MMOGame(ConnectedWorld game) {
        this.game = game;
        state = MMOState.AWAITING_INPUT;
        help1 = "you can do anything (sorta):";
        help2 = ">grind >quest >pvp >dungeon    >wander >help";
        rand = new Random();
        xpAmount = 0;
        xpLevelCap = 100;
        levelNum = 1;
        unfilledXpTex = new Texture(Gdx.files.internal("unfilledbar.png"));
        filledXpTex = new Texture(Gdx.files.internal("filledbar.png"));
        unfilledXp = new Rectangle();
        filledXp = new Rectangle();

        filledXp.x = 375;
        filledXp.y = 100;
        filledXp.width = 200;
        filledXp.height = 30;

        unfilledXp.x = 375;
        unfilledXp.y = 375;
        unfilledXp.width = 0;
        unfilledXp.height = 30;
        questsAvailable = 10;

        //TODO: below just for testing purposes...
//        firstTimeConnect();
    }

    public int getLevelNum() { return levelNum; }
    public int getXpAmount() { return xpAmount; }
    public int getXpLevelCap() { return xpLevelCap; }

    public void firstTimeConnect() {
        state = MMOState.LOGIN;
//        game.outputWriter.clearText();
        game.outputWriter.writeOutputMessage("enter your username:");
    }

    public void acceptUserInput(String input) {
        /**
         * determine if user input was valid, and what to do
         * with it depending on the state of this game
         */
        input = input.toLowerCase();
        input = input.substring(1);
        switch (state) {
            case LOGIN:
                username = input;
                state = MMOState.WAITING_FOR_OUTPUT;
                game.outputWriter.writeOutputMessage("welcome back, " +
                        username + ", to...");
                game.outputWriter.writeOutputMessage("world of mmocraft!");
                game.outputWriter.writeOutputMessage("a whole other world for you to  explore.");
                game.outputWriter.writeOutputMessage("a world where you belong.");
                game.outputWriter.writeOutputMessage(help1);
                game.outputWriter.writeOutputMessage(help2);

            case AWAITING_INPUT:
                if (input.equals("help")) {
                    processHelp();
                }
                else if (input.equals("grind")) {
                    processGrind();
                }
                else if (input.equals("quest")) {
                    processQuest();
                }
                else if (input.equals("pvp")) {
                    processPvp();
                }
                else if (input.equals("dungeon")) {
                    processDungeon();
                }
                else if (input.equals("wander")) {
                    processWander();
                }
        }
    }

    /**
     * Called by writer once its done writing
     */
    public void canResumeGame() {
        if (!state.equals(MMOState.LOGIN)) {
            state = MMOState.AWAITING_INPUT;
        }
    }

    public void processHelp() {
        state = MMOState.WAITING_FOR_OUTPUT;
        game.outputWriter.writeOutputMessage(help1);
        game.outputWriter.writeOutputMessage(help2);
    }

    public void processGrind() {
        state = MMOState.WAITING_FOR_OUTPUT;
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
        gainXp(15);
    }

    public void processQuest() {
        state = MMOState.WAITING_FOR_OUTPUT;
        int randomNum = rand.nextInt((5 - 1) + 1) + 1;
        game.outputWriter.writeOutputMessage("Checking your quest log...");
        if (questsAvailable > 0) {
            if (randomNum == 1) {
                game.outputWriter.writeOutputMessage("fetching 25 mob guts...");
            }
            else if (randomNum == 2) {
                game.outputWriter.writeOutputMessage("delivering mail to orc dude...");

            }
            else if (randomNum == 3) {
                game.outputWriter.writeOutputMessage("killing 32 skele-nurds...");
            }
            else if (randomNum == 4) {
                game.outputWriter.writeOutputMessage("lol, deliver a package 2 hours away on mount...");
            }
            else if (randomNum == 5) {
                game.outputWriter.writeOutputMessage("collecting 50 copper ore...");
            }
            game.outputWriter.writeOutputMessage("you gain a medium amount of xp.");
            gainXp(25);
            questsAvailable --;
        }
        else {
            game.outputWriter.writeOutputMessage("Eh. No more quests.");
            game.outputWriter.writeOutputMessage("Maybe next level?");
        }

    }

    public void processPvp() {
        state = MMOState.WAITING_FOR_OUTPUT;
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum == 1) {
            game.outputWriter.writeOutputMessage("playing dps.");
            game.outputWriter.writeOutputMessage("your k/d was terrible.");
        }
        else if (randomNum == 2) {
            game.outputWriter.writeOutputMessage("playing support.");
            game.outputWriter.writeOutputMessage("no one thanks you for healing.");
        }
        else if (randomNum == 3) {
            game.outputWriter.writeOutputMessage("you try to capture the flag.");
            game.outputWriter.writeOutputMessage("you get killed on your way.");
        }
        game.outputWriter.writeOutputMessage("you gain a small amount of xp.");
        gainXp(15);
    }

    public void processDungeon() {
        state = MMOState.WAITING_FOR_OUTPUT;
        int randomNum = rand.nextInt((100 - 1) + 1) + 1;
        game.outputWriter.writeOutputMessage("Looking for Group...");
        if (randomNum > 70) {
            if (randomNum > 95) {
                game.outputWriter.writeOutputMessage("Running as healer...");
                game.outputWriter.writeOutputMessage("..and the tank is standing in  the fire.");
                game.outputWriter.writeOutputMessage("goddammit.");
            }
            else if (randomNum > 90) {
                game.outputWriter.writeOutputMessage("Running as dps...");
                game.outputWriter.writeOutputMessage("your healer insults your sexuality.");
                game.outputWriter.writeOutputMessage("you get hot with anger.");

            }
            else if (randomNum > 85) {
                game.outputWriter.writeOutputMessage("Running as tank...");
                game.outputWriter.writeOutputMessage("the rouge rushes ahead. group wipe.");
                game.outputWriter.writeOutputMessage("you call him a fucking retard.");
                game.outputWriter.writeOutputMessage("did that make you feel better?");
            }
            else if (randomNum > 80) {
                game.outputWriter.writeOutputMessage("Running as dps...");
                game.outputWriter.writeOutputMessage("Your priest keeps pulling trash mobs.");
                game.outputWriter.writeOutputMessage("you get called a fag.");
                game.outputWriter.writeOutputMessage("youre unmotivated and depressed.");
            }
            else {
                game.outputWriter.writeOutputMessage("Running as dps...");
                game.outputWriter.writeOutputMessage("You manage to get to get average loot.");
                game.outputWriter.writeOutputMessage("no one in your group is talkative.");
                game.outputWriter.writeOutputMessage("youre going through the motions.");
            }
            game.outputWriter.writeOutputMessage("you gain a decent chunk of xp.");
            gainXp(50);
        }
        else {
            game.outputWriter.writeOutputMessage("jesus, nobody needs dps.");
            game.outputWriter.writeOutputMessage("try again later? ");
        }
    }

    public void processWander() {
        state = MMOState.WAITING_FOR_OUTPUT;
        game.outputWriter.writeOutputMessage("You jump around the city aimlessly.");
        game.outputWriter.writeOutputMessage("You try and get on top of the blacksmith.");
        game.outputWriter.writeOutputMessage("After 30 minutes you deem it impossible.");
        game.outputWriter.writeOutputMessage("You show off your mount.");
        game.outputWriter.writeOutputMessage("You pretend like people care about you.");
    }

    public void gainXp(int amount) {
        xpAmount += amount;
        if (xpAmount >= xpLevelCap) {
            int carryOverXp = xpAmount - xpLevelCap;
            levelUp();
            gainXp(carryOverXp);
        }
    }

    public void levelUp() {
        levelNum ++;
        game.outputWriter.writeOutputMessage("you leveled up! gratz!");
        game.outputWriter.writeOutputMessage("you are now level " + levelNum + "!");

        xpAmount = 0;
        xpLevelCap = (int) (xpLevelCap * 1.3);
        resetQuests();
    }

    public void drawXp() {
        game.inputWriter.renderInputMessage("Level:", 50, 230);
        game.inputWriter.renderInputMessage(Integer.toString(levelNum), 50, 200);
        game.inputWriter.renderInputMessage("xp:", 50, 150);
        String levelProgress = Integer.toString(xpAmount) + "/" + Integer.toString(xpLevelCap);
        game.inputWriter.renderInputMessage(levelProgress, 50, 120);

    }

    private void resetQuests() { questsAvailable = 10; }

    public enum MMOState {
        LOGIN, AWAITING_INPUT, WAITING_FOR_OUTPUT
    }
}