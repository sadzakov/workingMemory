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

public class Banana implements Screen {

    private World world;
    private GameMain game;
    private WordPics wordPics;
    private Sprite bg;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;
    private Stage stage;
    private Viewport viewport;
    private Array<Answer> answerArray = new Array<>();
    private final float DISTANCE_195 = 195f;
    private final float DISTANCE_120 = 120f;
    private final float DISTANCE_40 = 40f;
    private float distanceBetweenAnswerLetters = 70f;
    private int j = 5;

    private Image btnB;
    private Image btnA1;
    private Image btnN1;
    private Image btnA2;
    private Image btnN2;
    private Image btnA3;

    public Banana(GameMain game) {
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
        createWordpic();
        createBtn();
        setBtnPosition();
        addListeners();

        stage.addActor(btnB);
        stage.addActor(btnA1);
        stage.addActor(btnN1);
        stage.addActor(btnA2);
        stage.addActor(btnN2);
        stage.addActor(btnA3);
    }

    void createBackground() {
        bg = new Sprite(new Texture("background/background.jpg"));
        bg.setPosition(0, 0);
    }

    void createWordpic() {
        wordPics = new WordPics(world, "banana");
        wordPics.setPosition(0, 0);
    }

    void createBtn() {
        btnB = new Image(new Texture("letters/letterb.jpg"));
        btnB.setSize(65f, 65f);
        btnA1 = new Image(new Texture("letters/lettera.jpg"));
        btnA1.setSize(65f, 65f);
        btnN1 = new Image(new Texture("letters/lettern.jpg"));
        btnN1.setSize(65f, 65f);
        btnA2 = new Image(new Texture("letters/lettera.jpg"));
        btnA2.setSize(65f, 65f);
        btnN2 = new Image(new Texture("letters/lettern.jpg"));
        btnN2.setSize(65f, 65f);
        btnA3 = new Image(new Texture("letters/lettera.jpg"));
        btnA3.setSize(65f, 65f);
    }

    void setBtnPosition() {
        int option = (int) (Math.random() * 5);

        switch (option) {
            case 1:
                btnB.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA1.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN1.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN2.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA2.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA3.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 2:
                btnA2.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA1.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnB.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN1.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN2.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA3.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 3:
                btnN1.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA1.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN2.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnB.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA2.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA3.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 4:
                btnA1.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA2.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN2.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN1.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnB.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA3.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 5:
                btnN2.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnB.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN1.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA2.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA1.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnA3.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
        }
    }

    void addListeners() {
        btnB.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnB.addAction(Actions.fadeOut(0.5f));

                Answer b = new Answer(world, "b");
                answerArray.add(b);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnA1.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnA1.addAction(Actions.fadeOut(0.5f));

                Answer a1 = new Answer(world, "a");
                answerArray.add(a1);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnN1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                btnN1.addAction(Actions.fadeOut(0.5f));

                Answer n1 = new Answer(world, "n");
                answerArray.add(n1);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnA2.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnA2.addAction(Actions.fadeOut(0.5f));

                Answer a2 = new Answer(world, "a");
                answerArray.add(a2);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnN2.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnN2.addAction(Actions.fadeOut(0.5f));

                Answer n2 = new Answer(world, "n");
                answerArray.add(n2);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnA3.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnA3.addAction(Actions.fadeOut(0.5f));

                Answer a3 = new Answer(world, "a");
                answerArray.add(a3);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });
    }

    void isAnswerCorrect() {

        String[] appleArr = {"a", "n", "a", "n", "a", "b"};

        for (int i = 0; i <= answerArray.size - 1; i++) {
            if (answerArray.get(i).getLetter().equals(appleArr[i])) {
                j++;
            } else {
                j--;
            }
        }

        if (j == 11) {
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
        stage.getBatch().draw(bg, bg.getX() - 5, bg.getY() + 17);
        stage.getBatch().draw(wordPics, wordPics.getX() + 100, wordPics.getY() + 550);

        float distanceOnX = GameInfo.WIDTH / 2f - 220 + distanceBetweenAnswerLetters;

        for (int i = 0; i <= answerArray.size - 1; i++) {
            if (i == 0) {
                answerArray.get(i).setPosition(GameInfo.WIDTH / 2f - 230, GameInfo.HEIGHT / 2f - 200);
                answerArray.get(i).setSize(65f, 65f);
                answerArray.get(i).draw(game.getBatch());
            } else {
                answerArray.get(i).setPosition(distanceOnX , GameInfo.HEIGHT / 2f - 200);
                answerArray.get(i).setSize(65f, 65f);
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
