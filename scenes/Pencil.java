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

public class Pencil implements Screen {

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

    private Image btnP;
    private Image btnE;
    private Image btnN;
    private Image btnC;
    private Image btnI;
    private Image btnL;

    public Pencil(GameMain game) {
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

        stage.addActor(btnP);
        stage.addActor(btnE);
        stage.addActor(btnN);
        stage.addActor(btnC);
        stage.addActor(btnI);
        stage.addActor(btnL);
    }

    void createBackground() {
        bg = new Sprite(new Texture("background/background.jpg"));
        bg.setPosition(0, 0);
    }

    void createWordpic() {
        wordPics = new WordPics(world, "pencil");
        wordPics.setPosition(0, 0);
    }

    void createBtn() {
        btnP = new Image(new Texture("letters/letterp.jpg"));
        btnP.setSize(65f, 65f);
        btnE = new Image(new Texture("letters/lettere.jpg"));
        btnE.setSize(65f, 65f);
        btnN = new Image(new Texture("letters/lettern.jpg"));
        btnN.setSize(65f, 65f);
        btnC = new Image(new Texture("letters/letterc.jpg"));
        btnC.setSize(65f, 65f);
        btnI = new Image(new Texture("letters/letteri.jpg"));
        btnI.setSize(65f, 65f);
        btnL = new Image(new Texture("letters/letterl.jpg"));
        btnL.setSize(65f, 65f);
    }

    void setBtnPosition() {
        int option = (int) (Math.random() * 5);

        switch (option) {
            case 1:
                btnE.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnC.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnL.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnI.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 2:
                btnP.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnC.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnL.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnE.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnI.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 3:
                btnI.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnL.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnE.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnC.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 4:
                btnC.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnL.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnI.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnE.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
            case 5:
                btnI.setPosition(GameInfo.WIDTH / 2f - DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnP.setPosition(GameInfo.WIDTH / 2f - DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnN.setPosition(GameInfo.WIDTH / 2f - DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnE.setPosition(GameInfo.WIDTH / 2f + DISTANCE_40, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnC.setPosition(GameInfo.WIDTH / 2f + DISTANCE_120, GameInfo.HEIGHT / 2f - 20, Align.center);
                btnL.setPosition(GameInfo.WIDTH / 2f + DISTANCE_195, GameInfo.HEIGHT / 2f - 20, Align.center);
                break;
        }
    }

    void addListeners() {
        btnP.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnP.addAction(Actions.fadeOut(0.5f));

                Answer p = new Answer(world, "p");
                answerArray.add(p);

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

        btnC.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnC.addAction(Actions.fadeOut(0.5f));

                Answer c = new Answer(world, "c");
                answerArray.add(c);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnI.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnI.addAction(Actions.fadeOut(0.5f));

                Answer i = new Answer(world, "i");
                answerArray.add(i);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });

        btnL.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnL.addAction(Actions.fadeOut(0.5f));

                Answer l = new Answer(world, "l");
                answerArray.add(l);

                if (answerArray.size == 6) {
                    isAnswerCorrect();
                }

            }
        });
    }

    void isAnswerCorrect() {

        String[] appleArr = {"l", "i", "c", "n", "e", "p"};

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
