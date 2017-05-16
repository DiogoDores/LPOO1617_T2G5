package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.prairieKing.controller.HeroBody;
import com.prairieKing.controller.InputController;
import com.prairieKing.controller.ListenerClass;
import com.prairieKing.controller.PrairieKing;
import com.prairieKing.view.GameStage;

public class GameLogic {

    private InputController input;
    private AIManager AI;
    private PrairieKing myGame;
    private HeroModel hero;
    private HeroBody heroBody;
    private GameStage gameStage;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Gun gun;

    private TmxMapLoader mapLoader;
    private TiledMap map;

    public GameLogic(PrairieKing game) {
        world = new World(new Vector2(0,0), true);
        gun = new Gun(world);
        gun.update();
        AI = new AIManager(this);
        AI.spawn();
        myGame = game;
        hero = new HeroModel(PrairieKing.PPM/2, PrairieKing.PPM/2);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Mapas/Map.tmx");

        hero.setGun(gun);
        gameStage = new GameStage(this);
        input = new InputController(this);
        Gdx.input.setInputProcessor(input);
        world.setContactListener(new ListenerClass());
        heroBody = new HeroBody(world,hero);

        createBodies();
    }

    private void createBodies() {
        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body bod;

        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set((rect.getX() + rect.getWidth() / 2) / myGame.PPM * 22.34f, (rect.getY() + rect.getHeight() / 2 ) / myGame.PPM * 22.34f);

            bod = world.createBody(bDef);

            shape.setAsBox(rect.getWidth() / 2 / myGame.PPM * 22.34f, rect.getHeight() / 2 / myGame.PPM * 22.34f);
            fDef.shape = shape;
            bod.createFixture(fDef);
        }

        /*for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set((rect.getX() + rect.getWidth() / 2) / myGame.PPM * 22.34f, (rect.getY() + rect.getHeight() / 2) / myGame.PPM * 22.34f);

            bod = world.createBody(bDef);

            shape.setAsBox(rect.getWidth() / 2 / myGame.PPM * 22.34f, rect.getHeight() / 2 / myGame.PPM * 22.34f);
            fDef.shape = shape;
            bod.createFixture(fDef);
        }*/
    }

    public HeroModel getHero() {
        return hero;
    }

    public PrairieKing getMyGame() {
        return myGame;
    }

    public AIManager getAI() {
        return AI;
    }

    public void act() {
        world.step(1/300f,6,2);
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for(Body body : bodies){
            EntityModel test = ((EntityModel) body.getUserData());
            //.setPosition(body.getPosition().x, body.getPosition().y);
            if(test != null) {
                if (test.getType() == "ENEMY" || test.getType() == "HERO")
                    body.setTransform(test.getX(), test.getY(), 0);
                else
                    test.setPosition(body.getPosition().x, body.getPosition().y);
            }
        }
        gameStage.render(0);
        moveEntities();
    }


    public void moveEntities() {
        hero.move();
        AI.move();
    }

    public World getWorld() {
        return world;
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public TiledMap getMap() {
        return map;
    }
}
