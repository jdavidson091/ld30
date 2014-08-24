package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Letters {

    final ConnectedWorld game;
    private HashMap<Character, TextureRegion> letters;
    private Texture lettersTexture;
    private Texture numbersTexture;
    private int letterWidth;

    public Letters(final ConnectedWorld game) {
        this.game = game;
        lettersTexture = new Texture(Gdx.files.internal("letters.png"));
        numbersTexture = new Texture(Gdx.files.internal("numbers.png"));
        letterWidth = 30;
        letters = new HashMap<Character, TextureRegion>();

        buildLetter('a', 0);
        buildLetter('b', 1);
        buildLetter('c', 2);
        buildLetter('d', 3);
        buildLetter('e', 4);
        buildLetter('f', 5);
        buildLetter('g', 6);
        buildLetter('h', 7);
        buildLetter('i', 8);
        buildLetter('j', 9);
        buildLetter('k', 10);
        buildLetter('l', 11);
        buildLetter('m', 12);
        buildLetter('n', 13);
        buildLetter('o', 14);
        buildLetter('p', 15);
        buildLetter('q', 16);
        buildLetter('r', 17);
        buildLetter('s', 18);
        buildLetter('t', 19);
        buildLetter('u', 20);
        buildLetter('v', 21);
        buildLetter('w', 22);
        buildLetter('x', 23);
        buildLetter('y', 24);
        buildLetter('z', 25);
        buildLetter(' ', 26);
        buildLetter('!', 27);
        buildLetter('?', 28);
        buildLetter('.', 29);
        buildLetter(',', 30);
        buildLetter(':', 31);
        buildLetter('>', 32);
        buildLetter('-', 33);
        buildLetter('/', 34);
        buildLetter('#', 35);
        buildLetter('(', 36);
        buildLetter(')', 37);

        buildNumber('1', 0);
        buildNumber('2', 1);
        buildNumber('3', 2);
        buildNumber('4', 3);
        buildNumber('5', 4);
        buildNumber('6', 5);
        buildNumber('7', 6);
        buildNumber('8', 7);
        buildNumber('9', 8);
        buildNumber('0', 9);
    }

    public void buildLetter(char letter, int x) {
        TextureRegion letterTexture = new TextureRegion(lettersTexture, 30 * x, 0, 30, 30);
        letters.put(letter, letterTexture);
    }

    public void buildNumber(char number, int x) {
        TextureRegion numberTexture = new TextureRegion(numbersTexture, 30 * x, 0 , 30, 30);
        letters.put(number, numberTexture);
    }

    public void draw(Character character, int x, int y) {
        TextureRegion toDraw = letters.get(Character.toLowerCase(character));
        game.batch.draw(toDraw, x, y);
    }

    public int getLetterWidth() {
        return letterWidth;
    }

    //TODO: add dispose methods

}