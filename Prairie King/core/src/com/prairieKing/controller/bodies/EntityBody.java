package com.prairieKing.controller.bodies;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.entities.EntityModel;

public class EntityBody extends Body{


    private float width, height;
    private Body body;
    private EntityModel model;
    private FixtureDef fixtureDef;
    private Fixture fixture;

    public EntityBody(World world, long addr, EntityModel model) {
        super(world, addr);
        this.model = model;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX() + width/2, model.getY() + height/2);
        bodyDef.linearDamping = 0.0f;
        body = world.createBody(bodyDef);
        body.setUserData(model);
    }

    @Override
    public void setUserData(Object userData) {
        super.setUserData(userData);
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setTransform(float x, float y) {
        body.setTransform(x, y, 0);
    }

    public void setLinearVelocity(float x, float y) {
        body.setLinearVelocity(x, y);
    }

    final void createFixture(Body body, int width, int height , short category, short mask, short group) {
        // Transform pixels into meters, center and invert the y-coordinate

        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(width/1.9f,height/1.9f, new Vector2(width/2 + 0.5f, height/2 + 0.5f), 0);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;
        fixtureDef.filter.groupIndex = group;

        fixture = body.createFixture(fixtureDef);

        polygon.dispose();
    }


    public void destroy() {
        body.destroyFixture(fixture);
        body.setUserData(null);
        body = null;
        fixtureDef = null;
    }



    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Body getBody() {
        return body;
    }




}
