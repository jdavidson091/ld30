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
        TextureRegion a = new TextureRegion(lettersTexture, 0, 0, 30, 30);
        letters.put('a', a);
        TextureRegion b = new TextureRegion(lettersTexture, 30, 0, 30, 30);
        letters.put('b', b);
        TextureRegion c = new TextureRegion(lettersTexture, 30* 2, 0, 30, 30);
        letters.put('c', c);
        TextureRegion d = new TextureRegion(lettersTexture, 30* 2, 0, 30, 30);
        letters.put('c', d);  //TODO: programmatically build this...
        //loop over letters, call a method and pass the char and the multiple it should multiply 30 by
        TextureRegion space = new TextureRegion(lettersTexture, 30 * 26, 0, 30, 30);
        letters.put(' ', space);

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