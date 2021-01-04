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

public class Monkey implements Screen {

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

    private Image btnM;
    private Image btnO;
    private Image btnN;
    private Image btnK;
    private Image btnE;
    private Image btnY;

    public Monkey(GameMain game) {
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

        stage.addActor(btnM);
        stage.addActor(btnO);
        stage.addActor(btnN);
        stage.addActor(btnK);
        stage.addActor(btnE);
        stage.addActor(btnY);
    }

    void createBackground() {
        bg = new Sprite(new Texture("background/background.jpg"));
        bg.setPosition(0, 0);
    }

    void createWordpic() {
        wordPics = new WordPics(world, "monkey");
        wordPics.setPosition(0, 0);
    }

    void createBtn() {
        btnM = new Image(new Texture("letters/letterm.jpg"));
        btnM.setSize(65f, 65f);
        btnO = new Image(new Texture("letters/lettero.jpg"));
        btnO.setSize(65f, 65f);
        btnN = new Image(new Texture("letters/lettern.jpg"));
        btnN.setSize(65f, 65f);
        btnK = new Image(new Texture("letters/letterk.jpg"));
        btnK.setSize(65f, 65f);
        btnE = new Image(new Texture("letters/lettere.jpg"));
        btnE.setSize(65f, 65f);
        btnY = new Image(new Texture("letters/lettery.jpg"));
        btnY.setSize(65f, 65f);
    }

    void setBtnPosition() {
        int option = (int) (Math.random() * 5);

        switch (option) {
            case 1:
                btnO.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnM.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnY.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnE.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 2:
                btnE.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnM.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnY.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 3:
                btnN.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnM.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnY.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnE.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 4:
                btnY.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnM.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnE.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 5:
                btnK.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnO.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnE.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnM.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnY.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
        }
    }

    void addListeners() {
        btnM.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnM.addAction(Actions.fadeOut(0.5f));

                Answer m = new Answer(world, "m");
                answerArray.add(m);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnO.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnO.addAction(Actions.fadeOut(0.5f));

                Answer o = new Answer(world, "o");
                answerArray.add(o);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnN.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                btnN.addAction(Actions.fadeOut(0.5f));

                Answer n = new Answer(world, "n");
                answerArray.add(n);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnK.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnK.addAction(Actions.fadeOut(0.5f));

                Answer k = new Answer(world, "k");
                answerArray.add(k);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnE.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnE.addAction(Actions.fadeOut(0.5f));

                Answer e = new Answer(world, "e");
                answerArray.add(e);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnY.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnY.addAction(Actions.fadeOut(0.5f));

                Answer y1 = new Answer(world, "y");
                answerArray.add(y1);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });
    }

    void isAnswerCorrect() {

        String[] appleArr = {"y", "e", "k", "n", "o", "m"};

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
