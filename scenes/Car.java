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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sofija.sadzakov.GameMain;

import helpers.GameInfo;
import letters.Answer;
import wordpics.WordPics;

public class Car implements Screen {

    private GameMain game;
    private World world;
    private Stage stage;
    private Viewport viewport;
    private Sprite bg;
    private WordPics wp;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;
    private Array<Answer> answerArray = new Array<>();
    private final float DISTANCE_BETWEEN_LETTERS = 130f;
    private float distanceBetweenAnswerLetters = 130f;
    private int j = 5;

    private Image btnC;
    private Image btnA;
    private Image btnR;


    public Car(GameMain game) {
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
        createWordPics();
        createBtns();
        setBtnPosition();
        addListeners();

        stage.addActor(btnC);
        stage.addActor(btnA);
        stage.addActor(btnR);
    }

    void createBackground() {
        bg = new Sprite(new Texture("background/background.jpg"));
        bg.setPosition(0, 0);
    }

    void createWordPics() {
        wp = new WordPics(world, "car");
        wp.setPosition(0, 0);
    }

    void createBtns() {
        btnC = new Image(new Texture("letters/letterc.jpg"));
        btnC.setSize(100f, 100f);
        btnA = new Image(new Texture("letters/lettera.jpg"));
        btnA.setSize(100f, 100f);
        btnR = new Image(new Texture("letters/letterr.jpg"));
        btnR.setSize(100f, 100f);
    }

    void setBtnPosition() {
        int option = (int) (Math.random() * 4);

        switch (option) {
            case 1 :
                btnC.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_BETWEEN_LETTERS, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnR.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_BETWEEN_LETTERS, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                break;
            case 2 :
                btnR.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_BETWEEN_LETTERS, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnC.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_BETWEEN_LETTERS, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                break;
            case 3:
                btnA.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_BETWEEN_LETTERS, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnR.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnC.setPosition(GameInfo.WIDTH / 2f + DISTANCE_BETWEEN_LETTERS, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 4:
                btnA.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_BETWEEN_LETTERS, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnC.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnR.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_BETWEEN_LETTERS, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                break;
        }
    }

    void addListeners() {
        btnC.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y) {
               btnC.addAction(Actions.fadeOut(0.5f));

               Answer c = new Answer(world, "c");
               answerArray.add(c);

               if (answerArray.size == 3){
                   isAnswerCorrect();
               }
           }
        });

        btnA.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y) {
               btnA.addAction(Actions.fadeOut(0.5f));

               Answer a = new Answer(world, "a");
               answerArray.add(a);

               if (answerArray.size == 3) {
                   isAnswerCorrect();
               }
           }
        });

        btnR.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y) {
               btnR.addAction(Actions.fadeOut(0.5f));

               Answer r = new Answer(world, "r");
               answerArray.add(r);

               if (answerArray.size == 3) {
                   isAnswerCorrect();
               }
           }
        });
    }

    void isAnswerCorrect() {
        String[] carArr = {"r", "a", "c"};

        for (int i = 0; i <= answerArray.size - 1; i++) {
            if (answerArray.get(i).getLetter().equals(carArr[i])) {
                j++;
            } else {
                j--;
            }
        }

        if (j == 8) {
            System.out.println("You have the right answer!");
            game.setScreen(new RightAnswer(game));

        } else {
            System.out.println("You made a mistake! Try again!");
            game.setScreen(new WrongAnswer(game));
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
        stage.getBatch().draw(bg, bg.getX(), bg.getY());
        stage.getBatch().draw(wp, GameInfo.WIDTH / 2f - 160, GameInfo.HEIGHT / 2f + 130);

        float distanceOnX = GameInfo.WIDTH / 2f - 170 + distanceBetweenAnswerLetters;

        for (int i = 0; i <= answerArray.size - 1; i++) {
            if (i == 0) {
                answerArray.get(i).setPosition(GameInfo.WIDTH / 2f - 160, GameInfo.HEIGHT / 2f - 210);
                answerArray.get(i).setSize(100f, 100f);
                answerArray.get(i).draw(game.getBatch());
            } else {
                answerArray.get(i).setPosition(distanceOnX , GameInfo.HEIGHT / 2f - 210);
                answerArray.get(i).setSize(100f, 100f);
                answerArray.get(i).draw(game.getBatch());
                distanceOnX += distanceBetweenAnswerLetters;
            }
        }

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
