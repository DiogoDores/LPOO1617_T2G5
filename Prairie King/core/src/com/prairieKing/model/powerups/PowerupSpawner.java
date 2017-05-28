package com.prairieKing.model.powerups;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.controller.PrairieKing;
import com.prairieKing.controller.bodies.PowerupBody;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;
import com.prairieKing.model.entities.HeroModel;
import com.prairieKing.model.entities.PowerupModel;
import com.prairieKing.model.powerups.GunPowerups;
import com.prairieKing.model.powerups.HeroPowerups;

import java.util.ArrayList;

/**
 * Created by petre on 28/05/2017.
 */

public class PowerupSpawner {

    private ArrayList<PowerupModel> powerupModels;
    private ArrayList<PowerupBody> powerupBodies;
   // private HeroModel hero;
   // private Gun gun;
    private float timeToSpawn;
    private World world;

    public PowerupSpawner(World world) {
        powerupModels = new ArrayList<>();
        powerupBodies = new ArrayList<>();

        this.world = world;
        timeToSpawn = 5;  // TODO Trocar este 5 por generateRandom(), só para testes
        System.out.println(timeToSpawn);
     //   this.hero = gameLogic.getHero();
      //  this.gun = gameLogic.getHero().getGun();
    }

    public void update() {
        timeToSpawn -= Gdx.graphics.getDeltaTime();
        checkPowerups();

        if (timeToSpawn <= 0) {
            spawn();
            timeToSpawn = generateRandom();
        }

    }

    public float generateRandom() {
        float rand = MathUtils.random(10.0f, 20.0f);
        return rand;
    }

    public void spawn() {

        // TODO ADICIONAR AQUI POWERUPS FUTUROS

        int r = MathUtils.random(3);
        Vector2 position = randomPos();
        r = 0;
        if (r == 0) { // Speed
            PowerupModel speed = new PowerupModel(position.x,position.y);
            powerupModels.add(speed);
            powerupBodies.add(new PowerupBody(world,0,speed));

            System.out.println("Fez spawn na posição " + position.x + " " + position.y);
        }

    }

    public Vector2 randomPos() {

        float x = MathUtils.random(10, PrairieKing.PPM -10);
        float y = MathUtils.random(10, PrairieKing.PPM -10);

        Vector2 pos = new Vector2(x,y);

        return pos;
    }

    public void checkPowerups() {
        for (int i = 0; i < powerupModels.size(); i++) {
            powerupModels.get(i).update();
            if (powerupModels.get(i).isFlaggedForDelete()) {
                System.out.println("Estou efetivamente morto.");
                for (int j = 0; j < powerupBodies.size(); j++) {
                       if (powerupBodies.get(j).getUserData() == powerupModels.get(i)) {
                           System.out.println("Estou a ser destruido");
                           powerupBodies.get(j).destroy();
                           powerupBodies.remove(powerupBodies.get(j));
                    }
                }
                powerupModels.remove(powerupModels.get(i));

            }
        }
    }

    public ArrayList<PowerupModel> getPowerupModels() {
        return powerupModels;
    }
}