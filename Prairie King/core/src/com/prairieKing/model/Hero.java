package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hero extends Actor {
    private int x, y, lives;

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public int getLives() {
        return lives;
    }

    public void move() {

        // TODO AVERIGUAR SE SE PODE MOVIMENTAR PARA ALGUM LADO

        if (Gdx.input.isKeyPressed(Input.Keys.S))
            y= y-2;
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            x = x-2;
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            y = y+2;
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            x = x+ 2;
    }

    public Hero(int x, int y) {
        this.x = x;
        this.y = y;

        this.lives = 3;
    }

    public int die() {
        lives--;
        if (lives < 0)
            return -1; // Has Died
        return 0;  // Is still alive
    }
}
