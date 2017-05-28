package com.prairieKing.model.AI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.prairieKing.model.entities.EnemyModel;
import com.prairieKing.model.entities.HeroModel;

public class ChasingBehaviour implements Behaviour {

    private float enemySpeed = 400;

    // TODO adicionar movimento inicial em que ele se dirige para o meio no move

    @Override
    public void move(EnemyModel e, HeroModel h) {
        float x = e.getX();
        float y = e.getY();

        int r = MathUtils.random(40);


        if (Math.abs(x - h.getX()) < 3 && Math.abs(y - h.getY()) < 3) {  // Está próximo

            if (x > h.getX()) {
                e.setPosition(x - (1/(enemySpeed* Gdx.graphics.getDeltaTime())), y);
                e.setCurrentDirection('a');
            }
            else if (x < h.getX()) {
                e.setPosition(x + (1/(enemySpeed* Gdx.graphics.getDeltaTime())), y);
                e.setCurrentDirection('d');
            }
            if (y > h.getY()) {
                e.setPosition(x,y - (1/(enemySpeed* Gdx.graphics.getDeltaTime())));
                e.setCurrentDirection('s');
            }
            else if (y < h.getY()) {
                e.setPosition(x,y + (1/(enemySpeed* Gdx.graphics.getDeltaTime())));
                e.setCurrentDirection('w');
            }
        }


        else if (r == 2) {

            if (x > h.getX()) {
                e.setPosition(x - (1/(enemySpeed* Gdx.graphics.getDeltaTime())), y);
                e.setCurrentDirection('a');
            }
            else if (x < h.getX()) {
                e.setPosition(x + (1/(enemySpeed* Gdx.graphics.getDeltaTime())), y);
                e.setCurrentDirection('d');
            }
            if (y > h.getY()) {
                e.setPosition(x,y - (1/(enemySpeed*Gdx.graphics.getDeltaTime())));
                e.setCurrentDirection('s');
            }
            else if (y < h.getY()) {
                e.setPosition(x,y + (1/(enemySpeed*Gdx.graphics.getDeltaTime())));
                e.setCurrentDirection('w');
            }
        }
        else
            continueMove(e, h);
    }

    public void continueMove(EnemyModel e, HeroModel h) {

        float x = e.getX();
        float y = e.getY();
        float hx = h.getX();
        float hy = h.getY();
        char c = e.getCurrentDirection();
        //  System.out.println(c);
        if (c == 'a') {
            if (Math.abs(x - hx) < 5) {
                if (y < hy) {
                    e.setPosition(x,y + (1/(Gdx.graphics.getDeltaTime() *enemySpeed)));
                    e.setCurrentDirection('w');
                }
                else {
                    e.setPosition(x,y - (1/(Gdx.graphics.getDeltaTime() *enemySpeed)));
                    e.setCurrentDirection('s');
                }
            }
            else {
                e.setPosition(x - (1/(Gdx.graphics.getDeltaTime() *enemySpeed)),y);
                e.setCurrentDirection('a');
            }
        }
        else if (c == 'd') {
            if (Math.abs(x - hx) < 5) {
                if (y < hy) {
                    e.setPosition(x,y + (1/(Gdx.graphics.getDeltaTime() *enemySpeed)));
                    e.setCurrentDirection('w');
                }
                else {
                    e.setPosition(x,y - (1/(Gdx.graphics.getDeltaTime() *enemySpeed)));
                    e.setCurrentDirection('s');
                }
            }
            else {
                e.setPosition(x + (1/(Gdx.graphics.getDeltaTime() *enemySpeed)),y);
                e.setCurrentDirection('d');
            }
        }
        else if (c == 's') {
            if (Math.abs(y - hy) < 5) {
                if (x < hx) {
                    e.setPosition(x + (1/(Gdx.graphics.getDeltaTime() *enemySpeed)),y);
                    e.setCurrentDirection('d');
                }
                else {
                    e.setPosition(x - (1/(Gdx.graphics.getDeltaTime() *enemySpeed)),y);
                    e.setCurrentDirection('a');
                }
            }
            else {
                e.setPosition(x,y - (1/(Gdx.graphics.getDeltaTime() *enemySpeed)));
                e.setCurrentDirection('s');
            }
        }
        else if (c == 'w') {
            if (Math.abs(y - hy) < 5) {
                if (x < hx) {
                    e.setPosition(x + (1/(Gdx.graphics.getDeltaTime() *enemySpeed)),y);
                    e.setCurrentDirection('d');
                }
                else {
                    e.setPosition(x - (1/(Gdx.graphics.getDeltaTime() *enemySpeed)),y);
                    e.setCurrentDirection('a');
                }
            }
            else {
                e.setPosition(x,y + (1/(Gdx.graphics.getDeltaTime() *enemySpeed)));
                e.setCurrentDirection('w');
            }
        }
    }

    @Override
    public void attack(EnemyModel e, HeroModel h) {

    }

}