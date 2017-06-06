package com.prairieKing.model.bodies;

import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.Constants;
import com.prairieKing.controller.entities.ProjectileController;

public class ProjectileBody extends EntityBody {

    /**Creates a body for a Projectile, based on a Model, as well as it's fixture.
     *
     * @param world needed for EntityBody's constructor.
     * @param model Model with which the body is going to be associated with.
     */
    public ProjectileBody(World world, ProjectileController model) {
        super(world, 0, model);
        createFixture((int) Constants.PROJECTILE_WIDTH, (int) Constants.PROJECTILE_WIDTH, Constants.CATEGORY_PROJECTILE, Constants.MASK_PROJECTILE, (short) 0);

    }
}