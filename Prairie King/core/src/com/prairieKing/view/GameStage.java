package com.prairieKing.view;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.prairieKing.controller.PrairieKing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.prairieKing.model.Enemy;
import com.prairieKing.model.GameLogic;

public class GameStage extends ScreenAdapter {
    private Stage stage;
    private PrairieKing game;
    private Sprite background;
    private Sprite hero, enemyToDraw;
    private SpriteBatch batch;
    private FitViewport view;
    private GameLogic gameLogic;
    private Enemy[] enemies;
    private AssetManager assetManager;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera cam;

    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime = 0f;

    public GameStage(GameLogic gameLogic) {
        this.game = gameLogic.getMyGame();
        batch = new SpriteBatch();
        stage = new Stage();
        enemies = gameLogic.getAI().getEnemies();
         view = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        //cam = new OrthographicCamera();

        loadAssets();

        this.gameLogic = gameLogic;
    }


    public void loadAssets() {
        Texture map1 = game.getAssetManager().get("Mapas/MapaTeste1.png", Texture.class);
        Texture mainSprite = game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class);
        background = new Sprite(map1);
        hero = new Sprite(mainSprite,367,96,16,16);

        textureAtlas = new TextureAtlas(Gdx.files.internal("Sprites/SpriteAtlas.atlas"));
        animation = new Animation(1f/15f, textureAtlas.getRegions());

        //TmxMapLoader loader = new TmxMapLoader();
        //map = loader.load("Mapas/Map.tmx");

        //renderer = new OrthogonalTiledMapRenderer(map, 1/32f);
    }

    @Override
    public void render(float delta) {

        elapsedTime += Gdx.graphics.getDeltaTime();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

       /* renderer.setView(cam);
        renderer.render();*/

        batch.begin();
        background.setSize(view.getWorldWidth(),view.getWorldHeight());
        hero.setSize(view.getWorldWidth()/32,view.getWorldHeight()/18);
        hero.setX(gameLogic.getHero().getX());
        hero.setY(gameLogic.getHero().getY());

        background.draw(batch);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 0, 0);
        hero.draw(batch);
        drawEnemies();
        batch.end();

    }

    public void drawEnemies() {

        for (int i = 0 ; i < enemies.length ; i++) {
            if (enemies[i].getType() == "basicWalker") {
                enemyToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class),304,80,16,16);
                enemyToDraw.setSize(view.getWorldWidth()/32,view.getWorldHeight()/18);
                enemyToDraw.setX(enemies[i].getX());
                enemyToDraw.setY(enemies[i].getY());
                enemyToDraw.draw(batch);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        /*cam.viewportWidth = width;
        cam.viewportHeight = height;
        cam.update();*/
        view.update(width,height);
    }

    @Override
    public void dispose() {
        map.dispose();
        batch.dispose();
        stage.dispose();
        textureAtlas.dispose();
    }

}