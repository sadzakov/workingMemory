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

public class Pumpkin implements Screen {

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
    private final float DISTANCE_130 = 130f;
    private final float DISTANCE_65 = 65f;
    private float distanceBetweenAnswerLetters = 80f;
    private int j = 5;

    private Image btnP1;
    private Image btnU;
    private Image btnM;
    private Image btnP2;
    private Image btnK;
    private Image btnI;
    private Image btnN;

    public Pumpkin(GameMain game) {
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

        stage.addActor(btnP1);
        stage.addActor(btnU);
        stage.addActor(btnM);
        stage.addActor(btnP2);
        stage.addActor(btnK);
        stage.addActor(btnI);
        stage.addActor(btnN);
    }

    void createBackground() {
        bg = new Sprite(new Texture("background/background.jpg"));
        bg.setPosition(0, 0);
    }

    void createWordpic() {
        wordPics = new WordPics(world, "pumpkin");
        wordPics.setPosition(0, 0);
    }

    void createBtn() {
        btnP1 = new Image(new Texture("letters/letterp.jpg"));
        btnP1.setSize(55f, 55f);
        btnU = new Image(new Texture("letters/letteru.jpg"));
        btnU.setSize(55f, 55f);
        btnM = new Image(new Texture("letters/letterm.jpg"));
        btnM.setSize(55f, 55f);
        btnP2 = new Image(new Texture("letters/letterp.jpg"));
        btnP2.setSize(55f, 55f);
        btnK = new Image(new Texture("letters/letterk.jpg"));
        btnK.setSize(55f, 55f);
        btnI = new Image(new Texture("letters/letteri.jpg"));
        btnI.setSize(55f, 55f);
        btnN = new Image(new Texture("letters/lettern.jpg"));
        btnN.setSize(55f, 55f);
    }

    void setBtnPosition() {
        int option = (int) (Math.random() * 5);

        switch (option) {
            case 1:
                btnM.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP1.setPosition(GameInfo.WIDTH / 2f - DISTANCE_130, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP2.setPosition(GameInfo.WIDTH / 2f - DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnU.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f + DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnI.setPosition(GameInfo.WIDTH / 2f + DISTANCE_130, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 2:
                btnP1.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f - DISTANCE_130, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f - DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP2.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnM.setPosition(GameInfo.WIDTH / 2f + DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnU.setPosition(GameInfo.WIDTH / 2f + DISTANCE_130, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnI.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 3:
                btnI.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP1.setPosition(GameInfo.WIDTH / 2f - DISTANCE_130, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnU.setPosition(GameInfo.WIDTH / 2f - DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP2.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f + DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnM.setPosition(GameInfo.WIDTH / 2f + DISTANCE_130, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 4:
                btnU.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f - DISTANCE_130, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f - DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP1.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP2.setPosition(GameInfo.WIDTH / 2f + DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnM.setPosition(GameInfo.WIDTH / 2f + DISTANCE_130, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnI.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 5:
                btnN.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnK.setPosition(GameInfo.WIDTH / 2f - DISTANCE_130, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnI.setPosition(GameInfo.WIDTH / 2f - DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP1.setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnU.setPosition(GameInfo.WIDTH / 2f + DISTANCE_65, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP2.setPosition(GameInfo.WIDTH / 2f + DISTANCE_130, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnM.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
        }
    }

    void addListeners() {
        btnP1.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnP1.addAction(Actions.fadeOut(0.5f));

                Answer p = new Answer(world, "p");
                answerArray.add(p);

                if (answerArray.size == 7) {
                    isAnswerCorrect();
                }

            }
        });

        btnU.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnU.addAction(Actions.fadeOut(0.5f));

                Answer u = new Answer(world, "u");
                answerArray.add(u);

                if (answerArray.size == 7) {
                    isAnswerCorrect();
                }

            }
        });

        btnM.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                btnM.addAction(Actions.fadeOut(0.5f));

                Answer m = new Answer(world, "m");
                answerArray.add(m);

                if (answerArray.size == 7) {
                    isAnswerCorrect();
                }

            }
        });

        btnP2.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnP2.addAction(Actions.fadeOut(0.5f));

                Answer p = new Answer(world, "p");
                answerArray.add(p);

                if (answerArray.size == 7) {
                    isAnswerCorrect();
                }

            }
        });

        btnK.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnK.addAction(Actions.fadeOut(0.5f));

                Answer k = new Answer(world, "k");
                answerArray.add(k);

                if (answerArray.size == 7) {
                    isAnswerCorrect();
                }

            }
        });

        btnI.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnI.addAction(Actions.fadeOut(0.5f));

                Answer i = new Answer(world, "i");
                answerArray.add(i);

                if (answerArray.size == 7) {
                    isAnswerCorrect();
                }

            }
        });

        btnN.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnN.addAction(Actions.fadeOut(0.5f));

                Answer n = new Answer(world, "n");
                answerArray.add(n);

                if (answerArray.size == 7) {
                    isAnswerCorrect();
                }

            }
        });
    }

    void isAnswerCorrect() {

        String[] appleArr = {"n","i", "k", "p", "m", "u", "p"};

        for (int i = 0; i <= answerArray.size - 1; i++) {
            if (answerArray.get(i).getLetter().equals(appleArr[i])) {
                j++;
            } else {
                j--;
            }
        }

        if (j == 12) {
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
