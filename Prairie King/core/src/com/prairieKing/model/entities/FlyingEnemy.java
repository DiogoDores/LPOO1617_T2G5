package com.prairieKing.model.entities;

import com.prairieKing.model.AI.Behaviour;
import com.prairieKing.model.AI.FlyingBehaviour;

public class FlyingEnemy extends EnemyModel {

    private Behaviour behaviour;

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    public FlyingEnemy(int x, int y) {
        super(x, y);
        super.setType("ENEMY");
        super.setEnemyType("FLYING");
        behaviour = new FlyingBehaviour();
    }

    @Override
    public void move(EnemyModel e, HeroModel h) {
        behaviour.move(this, h);
    }

    @Override
    public void setCurrentDirection(char currentDirection) {
        super.setCurrentDirection(currentDirection);
    }
}