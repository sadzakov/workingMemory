package letters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;

public class Answer extends Sprite {

    private World world;
    private Body body;
    private String letter;

    public Answer(World world, String letter) {
        super(new Texture("letters/letter" + letter + ".jpg"));
        this.world = world;
        this.letter = letter;

        createBody();
    }

    void createBody() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((getX() - 130) / GameInfo.PPM, (getY() + 150) / GameInfo.PPM);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth() / 2f) / GameInfo.PPM, (getHeight() / 2f) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 4;
        fixtureDef.friction = 2;

        Fixture fixture = body.createFixture(fixtureDef);
        

        shape.dispose();
        world.dispose();
    }

    public String getLetter() {
        return this.letter;
    }






}
