package com.connectedworld.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Letters {

    final ConnectedWorld game;
    private HashMap<Character, TextureRegion> letters;
    private Texture lettersTexture;
    private int letterWidth;

    public Letters(final ConnectedWorld game) {
        this.game = game;
        lettersTexture = new Texture(Gdx.files.internal("letters.png"));
        letterWidth = 30;
        letters = new HashMap<Character, TextureRegion>();
//        TextureRegion a = new TextureRegion(lettersTexture, 0, 0, 30, 30);
//        letters.put('a', a);
//        TextureRegion b = new TextureRegion(lettersTexture, 30, 0, 30, 30);
//        letters.put('b', b);
//        TextureRegion c = new TextureRegion(lettersTexture, 30* 2, 0, 30, 30);
//        letters.put('c', c);
//        TextureRegion d = new TextureRegion(lettersTexture, 30* 2, 0, 30, 30);
//        letters.put('c', d);  //TODO: programmatically build this...
//        //loop over letters, call a method and pass the char and the multiple it should multiply 30 by
//        TextureRegion space = new TextureRegion(lettersTexture, 30 * 26, 0, 30, 30);
//        letters.put(' ', space);
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
    }

    public void buildLetter(char letter, int x) {
        TextureRegion letterTexture = new TextureRegion(lettersTexture, 30 * x, 0, 30, 30);
        letters.put(letter, letterTexture);
    }

    public void draw(Character character, int x, int y) {
        TextureRegion toDraw = letters.get(character);
        game.batch.draw(toDraw, x, y);
    }

    public int getLetterWidth() {
        return letterWidth;
    }

    //TODO: add dispose methods

}