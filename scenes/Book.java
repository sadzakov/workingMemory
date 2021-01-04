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

public class Book implements Screen {

    private GameMain game;
    private World world;
    private Sprite bg;
    private WordPics wordPics;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;
    private Stage stage;
    private Viewport viewport;
    private Array<Answer> answerArray = new Array<>();
    private final float DISTANCE_65 = 65f;
    private final float DISTANCE_185 = 185f;
    private float distanceBetweenAnswerLetters = 130f;
    private int j = 5;

    private Image btnB;
    private Image btnO1;
    private Image btnO2;
    private Image btnK;


    public Book(GameMain game) {
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

        stage.addActor(btnB);
        stage.addActor(btnO1);
        stage.addActor(btnO2);
        stage.addActor(btnK);

    }

    void createBackground() {
        bg = new Sprite(new Texture("background/background.jpg"));
        bg.setPosition(0, 0);
    }

    void createWordPics() {
        wordPics = new WordPics(world, "book");
        wordPics.setPosition(0, 0);
    }

    void createBtns() {
        btnB = new Image(new Texture("letters/letterb.jpg"));
        btnB.setSize(100f, 100f);
        btnO1 = new Image(new Texture("letters/lettero.jpg"));
        btnO1.setSize(100f, 100f);
        btnO2 = new Image(new Texture("letters/lettero.jpg"));
        btnO2.setSize(100f, 100f);
        btnK = new Image(new Texture("letters/letterk.jpg"));
        btnK.setSize(100f, 100f);
    }

    void setBtnPosition() {
        int option = (int) (Math.random() * 5);

        switch (option) {
            case 1 :
                btnK.setPosition(GameInfo.WIDTH / 2f - DISTANCE_185, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnB.setPosition(GameInfo.WIDTH / 2f - DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO1.setPosition(GameInfo.WIDTH / 2f + DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO2.setPosition(GameInfo.WIDTH / 2f + DISTANCE_185, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 2 :
                btnB.setPosition(GameInfo.WIDTH / 2f - DISTANCE_185, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f - DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO1.setPosition(GameInfo.WIDTH / 2f + DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO2.setPosition(GameInfo.WIDTH / 2f + DISTANCE_185, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 3 :
                btnO2.setPosition(GameInfo.WIDTH / 2f - DISTANCE_185, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnB.setPosition(GameInfo.WIDTH / 2f - DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO1.setPosition(GameInfo.WIDTH / 2f + DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f + DISTANCE_185, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case  4 :
                btnO1.setPosition(GameInfo.WIDTH / 2f - DISTANCE_185, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnB.setPosition(GameInfo.WIDTH / 2f - DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f + DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO2.setPosition(GameInfo.WIDTH / 2f + DISTANCE_185, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 5 :
                btnB.setPosition(GameInfo.WIDTH / 2f - DISTANCE_185, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO2.setPosition(GameInfo.WIDTH / 2f - DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f + DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO2.setPosition(GameInfo.WIDTH / 2f + DISTANCE_185, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
        }
    }

    void addListeners() {
        btnB.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                btnB.addAction(Actions.fadeOut(0.5f));

                Answer b = new Answer(world, "b");
                answerArray.add(b);

                if (answerArray.size == 4) {
                    isAnswerCorrect();
                }
            }
        });

        btnO1.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                btnO1.addAction(Actions.fadeOut(0.5f));

                Answer o1 = new Answer(world, "o");
                answerArray.add(o1);

                if (answerArray.size == 4) {
                    isAnswerCorrect();
                }
            }
        });

        btnO2.addListener(new ClickListener(){
            public void clicked(InputEvent eve, float x, float y) {
                btnO2.addAction(Actions.fadeOut(0.5f));

                Answer o2 = new Answer(world, "o");
                answerArray.add(o2);

                if (answerArray.size == 4) {
                    isAnswerCorrect();
                }
            }
        });

        btnK.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnK.addAction(Actions.fadeOut(0.5f));

                Answer k = new Answer(world, "k");
                answerArray.add(k);

                if (answerArray.size == 4) {
                    isAnswerCorrect();
                }
            }
        });
    }

    void isAnswerCorrect() {

        String[] bookArr = {"k", "o", "o", "b"};

        for (int i = 0; i <= answerArray.size - 1; i++) {
            if (answerArray.get(i).getLetter().equals(bookArr[i])) {
                j++;
            } else {
                j--;
            }
        }

        if (j == 9) {
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
        stage.getBatch().draw(wordPics, GameInfo.WIDTH / 2f - 60, GameInfo.HEIGHT / 2f + 130);

        float distanceOnX = GameInfo.WIDTH / 2f - 220 + distanceBetweenAnswerLetters;

        for (int i = 0; i <= answerArray.size - 1; i++) {
            if (i == 0) {
                answerArray.get(i).setPosition(GameInfo.WIDTH / 2f - 230, GameInfo.HEIGHT / 2f - 210);
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
