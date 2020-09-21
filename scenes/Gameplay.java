package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sofija.sadzakov.GameMain;

import helpers.GameInfo;
import player.Player;
import wordpics.WordPics;

public class Gameplay implements Screen {

    private GameMain game;
    private Sprite bg;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;
    private Player player;
    private World world;
    private WordPics wordPics;

    public Gameplay(GameMain game) {
        this.game = game;
        mainCamera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);

        createBackground();

        world = new World(new Vector2(0, -9.8f), true);
        player = new Player(world, GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f);
        wordPics = new WordPics(world, GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f);
    }

    void createBackground() {
        bg = new Sprite(new Texture("background/background.jpg"));
        bg.setPosition(bg.getWidth(), bg.getHeight());
    }

    void drawBackground() {
        game.getBatch().draw(bg, bg.getX() - 480, bg.getY() - 360);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();

        drawBackground();
        player.draw(game.getBatch());
        wordPics.draw(game.getBatch());

        game.getBatch().end();

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
