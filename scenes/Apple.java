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
import com.badlogic.gdx.scenes.scene2d.Action;
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

public class Apple implements Screen {

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
    private final float DISTANCE_100 = 100f;
    private float distanceBetweenAnswerLetters = 100f;
    private int j = 5;

    private Image btnA;
    private Image btnP1;
    private Image btnP2;
    private Image btnL;
    private Image btnE;


    public Apple(GameMain game) {
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
        createBtn();
        createWordpic();
        setBtnPosition();
        addListeners();

        stage.addActor(btnA);
        stage.addActor(btnP1);
        stage.addActor(btnP2);
        stage.addActor(btnL);
        stage.addActor(btnE);

    }

    void createBackground() {
        bg = new Sprite(new Texture("background/background.jpg"));
        bg.setPosition(0, 0);
    }

    void createWordpic() {
        wordPics = new WordPics(world, "apple");
        wordPics.setPosition(0, 0);
    }

    void createBtn() {
        btnA = new Image(new Texture("letters/lettera.jpg"));
        btnA.setSize(85f, 85f);
        btnP1 = new Image(new Texture("letters/letterp.jpg"));
        btnP1.setSize(85f, 85f);
        btnP2 = new Image(new Texture("letters/letterp.jpg"));
        btnP2.setSize(85f, 85f);
        btnL = new Image(new Texture("letters/letterl.jpg"));
        btnL.setSize(85f, 85f);
        btnE = new Image(new Texture("letters/lettere.jpg"));
        btnE.setSize(85f, 85f);
    }

    void setBtnPosition() {
        int option = (int) (Math.random() * 5);

        switch (option) {
            case 1:
                btnL.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_195, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnP1.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_100, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnE.setPosition(GameInfo.WIDTH / 2f, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnP2.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_100, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnA.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_195, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                break;
            case 2:
                btnP2.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_195, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnP1.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_100, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnA.setPosition(GameInfo.WIDTH / 2f, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnE.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_100, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnL.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_195, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                break;
            case 3:
                btnP2.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_195, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnA.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_100, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnP1.setPosition(GameInfo.WIDTH / 2f, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnE.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_100, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnL.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_195, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                break;
            case 4:
                btnL.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_195, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnP2.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_100, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnA.setPosition(GameInfo.WIDTH / 2f, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnE.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_100, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnP1.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_195, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                break;
            case 5:
                btnE.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_195, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnP1.setPosition((GameInfo.WIDTH / 2f) - DISTANCE_100, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnA.setPosition(GameInfo.WIDTH / 2f, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnL.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_100, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                btnP2.setPosition((GameInfo.WIDTH / 2f) + DISTANCE_195, (GameInfo.HEIGHT / 2f) - 20, Align.center);
                break;
        }
    }

    void addListeners() {
        btnA.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnA.addAction(Actions.fadeOut(0.5f));

                Answer a = new Answer(world, "a");
                answerArray.add(a);

                if (answerArray.size == 5) {
                    isAnswerCorrect();
                }

            }
        });

        btnP1.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnP1.addAction(Actions.fadeOut(0.5f));

                Answer p1 = new Answer(world, "p");
                answerArray.add(p1);

                if (answerArray.size == 5) {
                    isAnswerCorrect();
                }

            }
        });

        btnP2.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y) {
               btnP2.addAction(Actions.fadeOut(0.5f));

               Answer p2 = new Answer(world, "p");
               answerArray.add(p2);

               if (answerArray.size == 5) {
                   isAnswerCorrect();
               }

           }
        });

        btnL.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnL.addAction(Actions.fadeOut(0.5f));

                Answer l = new Answer(world, "l");
                answerArray.add(l);

                if (answerArray.size == 5) {
                    isAnswerCorrect();
                }

            }
        });

        btnE.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                btnE.addAction(Actions.fadeOut(0.5f));

                Answer e = new Answer(world, "e");
                answerArray.add(e);

                if (answerArray.size == 5) {
                    isAnswerCorrect();
                }

            }
        });

    }

    void isAnswerCorrect() {

        String[] appleArr = {"e", "l", "p", "p", "a"};
        
        for (int i = 0; i <= answerArray.size - 1; i++) {
            if (answerArray.get(i).getLetter().equals(appleArr[i])) {
                j++;
            } else {
                j--;
            }
        }

        if (j == 10) {
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
        stage.getBatch().draw(wordPics, wordPics.getX() + 150, wordPics.getY() + 550);

        float distanceOnX = GameInfo.WIDTH / 2f - 220 + distanceBetweenAnswerLetters;

        for (int i = 0; i <= answerArray.size - 1; i++) {
            if (i == 0) {
                answerArray.get(i).setPosition(GameInfo.WIDTH / 2f - 230, GameInfo.HEIGHT / 2f - 210);
                answerArray.get(i).setSize(85f, 85f);
                answerArray.get(i).draw(game.getBatch());
            } else {
                answerArray.get(i).setPosition(distanceOnX , GameInfo.HEIGHT / 2f - 210);
                answerArray.get(i).setSize(85f, 85f);
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
