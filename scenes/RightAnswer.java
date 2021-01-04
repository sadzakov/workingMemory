package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sofija.sadzakov.GameMain;

import helpers.CounterRightAnswer;
import helpers.CounterWrongAnswer;
import helpers.GameInfo;


public class RightAnswer implements Screen {

    private World world;
    private GameMain game;
    private Sprite bg;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;
    private Stage stage;
    private Viewport viewport;
    public int counter;

    private Image btn;

    public RightAnswer(GameMain game) {
        this.game = game;

        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        box2DCamera.position.set(GameInfo.WIDTH, GameInfo.HEIGHT, 0);
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0), true);

        viewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, box2DCamera);
        stage = new Stage(viewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);

        createBackground();
        createAndPositionBtn();
        addListener();

        stage.addActor(btn);
    }

    void createBackground() {
        bg = new Sprite(new Texture("background/bravo.jpg"));
        bg.setPosition(0, 0);
    }

    void createAndPositionBtn() {
        btn = new Image(new Texture("menu/ok.jpg"));
        btn.setSize(60f, 60f);
        btn.setPosition(GameInfo.WIDTH / 2f + 100, GameInfo.HEIGHT / 2f - 200, Align.center);
    }

    void addListener() {
        btn.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                counter = CounterRightAnswer.getRightInstance().getResult();
                changeScreen();
            }
        });
    }

    void changeScreen() {
        switch (counter) {
            case 1 :
                game.setScreen(new Bear(game));
                CounterWrongAnswer.getWrongInstance().getResult();
                break;
            case 2 :
                game.setScreen(new Book(game));
                CounterWrongAnswer.getWrongInstance().getResult();
                break;
            case 3 :
                game.setScreen(new Apple(game));
                CounterWrongAnswer.getWrongInstance().getResult();
                break;
            case 4 :
                game.setScreen(new Cloud(game));
                CounterWrongAnswer.getWrongInstance().getResult();
                break;
            case 5 :
                game.setScreen(new House(game));
                CounterWrongAnswer.getWrongInstance().getResult();
                break;
            case 6 :
                game.setScreen(new Banana(game));
                CounterWrongAnswer.getWrongInstance().getResult();
                break;
            case 7 :
                game.setScreen(new Monkey(game));
                CounterWrongAnswer.getWrongInstance().getResult();
                break;
            case 8 :
                game.setScreen(new Pencil(game));
                CounterWrongAnswer.getWrongInstance().getResult();
                break;
            case 9 :
                game.setScreen(new Orange(game));
                CounterWrongAnswer.getWrongInstance().getResult();
                break;
            case 10 :
                game.setScreen(new Pumpkin(game));
                CounterWrongAnswer.getWrongInstance().getResult();
                break;
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);

        stage.getBatch().begin();

        stage.getBatch().setColor(Color.WHITE);
        stage.getBatch().draw(bg, bg.getX() - 5, bg.getY() + 130);

        stage.getBatch().end();
        stage.draw();
        stage.act();
        debugRenderer.render(world, box2DCamera.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        stage.dispose();
        game.dispose();
        world.dispose();
        debugRenderer.dispose();
    }
}
